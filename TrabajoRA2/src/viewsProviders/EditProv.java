package viewsProviders;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import methods.ImageUtilities;
import models.Action;
import models.Provider;
import services.Conexion;
import test.Test;

@SuppressWarnings("serial")
public class EditProv extends JFrame {

	private JPanel contentPane;
	private JTextField txtName,  txtLocation, txtMail, txtPhone;
	private JLabel lblLocation, lblMail, lblPhone, lblCreate, lblCancel, lblName;
	private String name, location, mail, phone;
	private int phoneInt;
	private Provider provider;

	public EditProv(Provider p) {
		setTitle("Edit Provider");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 281, 392);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel(){
			@Override
		    public Dimension getPreferredSize() {
		        return new Dimension(200, 200);
		    }

		    @Override
		    protected void paintComponent(Graphics g) {
		        super.paintComponent(g);
		        Graphics2D g2d = (Graphics2D) g.create();
		                
		        RoundRectangle2D border = new RoundRectangle2D.Double(0, 0, 170, 225, 20, 20);
		        ImageUtilities.applyQualityRenderingHints(g2d);
		        g2d.drawImage(ImageUtilities.applyShadow(border, 2, getBackground(), Color.BLACK, 0.25f), 5, 5, this);
		        g2d.setColor(Color.DARK_GRAY);
		        g2d.translate(5, 5);
		        g2d.draw(border);
		        g2d.dispose();
		    }
		};
		panel.setBounds(51, 24, 180, 234);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblName = new JLabel("Name:");
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		lblName.setBounds(26, 14, 120, 14);
		panel.add(lblName);
		
		txtName = new JTextField(p.getName());
		txtName.setBounds(26, 30, 120, 20);
		panel.add(txtName);
		txtName.setColumns(10);
		
		lblLocation = new JLabel("Location:");
		lblLocation.setHorizontalAlignment(SwingConstants.LEFT);
		lblLocation.setBounds(26, 69, 120, 14);
		panel.add(lblLocation);
		
		txtLocation = new JTextField(p.getLocation());
		txtLocation.setBounds(26, 84, 120, 20);
		panel.add(txtLocation);
		txtLocation.setColumns(10);
		
		lblMail = new JLabel("Mail:");
		lblMail.setHorizontalAlignment(SwingConstants.LEFT);
		lblMail.setBounds(26, 123, 120, 14);
		panel.add(lblMail);
		
		txtMail = new JTextField(p.getMail());
		txtMail.setColumns(10);
		txtMail.setBounds(26, 138, 120, 20);
		panel.add(txtMail);
		
		lblPhone = new JLabel("Phone:");
		lblPhone.setHorizontalAlignment(SwingConstants.LEFT);
		lblPhone.setBounds(26, 177, 120, 14);
		panel.add(lblPhone);
		
		txtPhone = new JTextField(String.valueOf(p.getPhone()));
		txtPhone.setColumns(10);
		txtPhone.setBounds(26, 192, 120, 20);
		panel.add(txtPhone);
		
		lblCreate = new JLabel(new ImageIcon("images/icons/add.png"));
		lblCreate.setBounds(51, 280, 50, 50);
		lblCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(lblCreate);
		lblCreate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				name = txtName.getText();
				location = txtLocation.getText();
				mail = txtMail.getText();
				phone = txtPhone.getText();
				
				boolean providerExists = false;
				Provider existingProvider = new Provider();
				
				if (!name.isEmpty()) {
					for (Provider p : Test.providerList) {
						if (name.equalsIgnoreCase(p.getName())) {
							existingProvider = p;
							providerExists = true;
						}
					}
					if (providerExists) {

						if (existingProvider.getAvailable() == 1)
							JOptionPane.showMessageDialog(null, "That provider has already been added", "Error",
									JOptionPane.WARNING_MESSAGE);
						else {
							JOptionPane.showMessageDialog(null, "That provider was created and deleted, restoring...",
									"Error", JOptionPane.WARNING_MESSAGE);
							
							Provider p = new Provider(existingProvider.getId(),  existingProvider.getName(), existingProvider.getLocation(),
									existingProvider.getMail(), existingProvider.getPhone(),  1);
							try {
								Test.provider.save(Conexion.obtain(), p);
							} catch (ClassNotFoundException | SQLException e1) {
								e1.printStackTrace();
							}
							Action a = new Action(Test.LogedInUser.getId(), 0 ,existingProvider.getId(), 4, Date.valueOf(LocalDate.now()));
							System.out.println(a);
							try {
								Test.action.save(Conexion.obtain(), a);
							} catch (ClassNotFoundException | SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							Test.actionList.add(a);
						}

					} else {

						if (location.isEmpty() || mail.isEmpty() || phone.isEmpty())
							JOptionPane.showMessageDialog(null, "You need to complete all the fields!", "Error",
									JOptionPane.WARNING_MESSAGE);
						else {

							if (phone.matches("\\d+")) {
								if (mail.matches("^[A-Za-z0-9+_.-]+@\\w+\\.\\w+$")) {
									phoneInt = Integer.parseInt(phone);
									provider = new Provider(name, location, mail, phoneInt, 1);
									try {
										Test.provider.save(Conexion.obtain(), provider);
									} catch (ClassNotFoundException | SQLException e1) {
										e1.printStackTrace();
									}
									
									int provId = 0;
									try {
										provId = Test.provider.getProviderID(Conexion.obtain(), name);
									} catch (ClassNotFoundException | SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									
									Action a = new Action(Test.LogedInUser.getId(), 0 , provId, 4, Date.valueOf(LocalDate.now()));
									System.out.println(a);
									try {
										Test.action.save(Conexion.obtain(), a);
									} catch (ClassNotFoundException | SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									Test.actionList.add(a);
									JOptionPane.showMessageDialog(null, "You have edited the provider!",
											"Editing...", JOptionPane.INFORMATION_MESSAGE);
									dispose();
									new SeeProv();
								} else
									JOptionPane.showMessageDialog(null, "Mail not valid!", "Error",
											JOptionPane.WARNING_MESSAGE);
							} else {
								JOptionPane.showMessageDialog(null, "Phone must be a number!", "Error",
										JOptionPane.WARNING_MESSAGE);
							}
						}
					}

				} else {

					JOptionPane.showMessageDialog(null, "Please fill at least the name field", "Error",
							JOptionPane.WARNING_MESSAGE);

				}
				
			}
		});
		
		lblCancel = new JLabel(new ImageIcon("images/icons/red_x.png"));
		lblCancel.setBounds(181, 280, 50, 50);
		lblCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(lblCancel);
		lblCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "You have cancelled the edition", "Cancelling...", JOptionPane.ERROR_MESSAGE);
				dispose();
				new SeeProv();
			}
		});		
		
		setVisible(true);
	}
}
