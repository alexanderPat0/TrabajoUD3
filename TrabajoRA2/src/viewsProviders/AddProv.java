package viewsProviders;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import methods.ImageUtilities;
import models.Provider;
import services.Conexion;
import test.Test;

@SuppressWarnings("serial")
public class AddProv extends JFrame {

	private JPanel contentPane;
	private JTextField txtName, txtLocation, txtMail, txtPhone;
	private JLabel lblLocation, lblMail, lblPhone, lblCreate, lblCancel, lblName;
	private String name, location, mail, phone;
	private int phoneInt;
	private Provider provider;

	public AddProv() {
		setTitle("Create Provider");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 281, 392);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel() {
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

		txtName = new JTextField();
		txtName.setBounds(26, 30, 120, 20);
		panel.add(txtName);
		txtName.setColumns(10);

		lblLocation = new JLabel("Location:");
		lblLocation.setHorizontalAlignment(SwingConstants.LEFT);
		lblLocation.setBounds(26, 69, 120, 14);
		panel.add(lblLocation);

		txtLocation = new JTextField();
		txtLocation.setBounds(26, 84, 120, 20);
		panel.add(txtLocation);
		txtLocation.setColumns(10);

		lblMail = new JLabel("Mail:");
		lblMail.setHorizontalAlignment(SwingConstants.LEFT);
		lblMail.setBounds(26, 123, 120, 14);
		panel.add(lblMail);

		txtMail = new JTextField();
		txtMail.setColumns(10);
		txtMail.setBounds(26, 138, 120, 20);
		panel.add(txtMail);

		lblPhone = new JLabel("Phone:");
		lblPhone.setHorizontalAlignment(SwingConstants.LEFT);
		lblPhone.setBounds(26, 177, 120, 14);
		panel.add(lblPhone);

		txtPhone = new JTextField();
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

				if (name.isEmpty() || location.isEmpty() || mail.isEmpty() || phone.isEmpty()) {
					JOptionPane.showMessageDialog(null, "You need to complete all the fields!", "Error",
							JOptionPane.WARNING_MESSAGE);

				} else {
					if (phone.matches("\\d+")) {
						phoneInt = Integer.parseInt(phone);
						provider = new Provider(name, location, mail, phoneInt);
						try {
							Test.provider.save(Conexion.obtain(), provider);
						} catch (ClassNotFoundException | SQLException e1) {
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "You have created a new Provider!", "Creating...",
								JOptionPane.INFORMATION_MESSAGE);
						dispose();
						new SeeProv();
					} else {
						JOptionPane.showMessageDialog(null, "Phone must be a number!", "Error",
								JOptionPane.WARNING_MESSAGE);
					}
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
				JOptionPane.showMessageDialog(null, "You have cancelled the creation", "Cancelling...",
						JOptionPane.ERROR_MESSAGE);
				dispose();
				new SeeProv();
			}
		});

		setVisible(true);
	}
}
