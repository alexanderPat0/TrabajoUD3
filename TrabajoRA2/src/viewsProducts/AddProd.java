package viewsProducts;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import methods.ImageUtilities;
import methods.Method;
import models.Action;
import models.Product;
import services.Conexion;
import test.Test;
import viewsProviders.SeeProv;

@SuppressWarnings("serial")
public class AddProd extends JFrame {

	private JPanel contentPane;
	private JTextField txtName, txtPrice, txtExpDate, txtAmount;
	private JLabel lblName, lblCategory, lblImage, lblSetImage, lblAmount, lblPrice, lblExpDate, lblDescription, lblCreate, lblCancel;
	private JComboBox<String> comboBox, cbCategory;
	private List<String> provNames;
	private List<String> listCategories = new ArrayList<String>();
	private int id, amountInt;
	private String name, description, category, image = "", price, amount;
	private Date date = null;
	private float priceFloat;
	private JTextArea textArea;
	private Product p = new Product();

	/**
	 * Create the frame.
	 */
	public AddProd() {
		setTitle("Add a new Product");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 564, 343);
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
		                
		        RoundRectangle2D border = new RoundRectangle2D.Double(0, 0, 510, 200, 20, 20);
		        ImageUtilities.applyQualityRenderingHints(g2d);
		        g2d.drawImage(ImageUtilities.applyShadow(border, 2, getBackground(), Color.BLACK, 0.25f), 5, 5, this);
		        g2d.setColor(Color.DARK_GRAY);
		        g2d.translate(5, 5);
		        g2d.draw(border);
		        g2d.dispose();
		    }
		};
		panel.setBounds(10, 11, 527, 213);
		contentPane.add(panel);
		panel.setLayout(null);
		
		MouseListen ml = new MouseListen();
		
		JLabel lblProvider = new JLabel("Provider:");
		lblProvider.setBounds(26, 11, 67, 14);
		panel.add(lblProvider);
		
		try {
			provNames=Test.provider.getNameProvs(Conexion.obtain());
		} catch (Exception e) {
			e.printStackTrace();
		} 
		comboBox = new JComboBox<>();
		comboBox.setBounds(26, 27, 135, 22);
		for(String a: provNames) {
			comboBox.addItem(a);
		}
		panel.add(comboBox);
		
//		listCategories = Method.createCategories();
		System.out.println(createCategories());
		
		lblName = new JLabel("Name:");
		lblName.setBounds(26, 60, 46, 14);
		panel.add(lblName);
		
		txtName = new JTextField();
		txtName.setBounds(26, 74, 135, 20);
		panel.add(txtName);
		txtName.setColumns(10);
		
		lblPrice = new JLabel("Price:");
		lblPrice.setBounds(26, 105, 46, 14);
		panel.add(lblPrice);
		
		txtPrice = new JTextField();
		txtPrice.setBounds(26, 120, 135, 20);
		panel.add(txtPrice);
		txtPrice.setColumns(10);
		
		lblExpDate = new JLabel("Expiration date:");
		lblExpDate.setBounds(26, 151, 107, 14);
		panel.add(lblExpDate);
		
		txtExpDate = new JTextField();
		txtExpDate.setText("yyyy/mm/dd");
		txtExpDate.setColumns(10);
		txtExpDate.setBounds(26, 167, 135, 20);
		txtExpDate.setEnabled(false);
		panel.add(txtExpDate);
		
		lblDescription = new JLabel("Description:");
		lblDescription.setBounds(338, 11, 81, 14);
		panel.add(lblDescription);
		
		textArea = new JTextArea();
		textArea.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setBounds(338, 26, 156, 160);
		panel.add(textArea);
		
		lblImage = new JLabel("Image:");
		lblImage.setBounds(180, 105, 46, 14);
		panel.add(lblImage);
		
		lblSetImage = new JLabel(new ImageIcon("images/icons/addImg.png"));
		lblSetImage.setBounds(180, 120, 138, 70);
		lblSetImage.addMouseListener(ml);
		panel.add(lblSetImage);
		
		lblCategory = new JLabel("Category:");
		lblCategory.setBounds(180, 11, 120, 14);
		panel.add(lblCategory);
		
		lblAmount = new JLabel("Amount:");
		lblAmount.setBounds(180, 60, 46, 14);
		panel.add(lblAmount);
		
		txtAmount = new JTextField();
		txtAmount.setColumns(10);
		txtAmount.setBounds(180, 74, 138, 20);
		panel.add(txtAmount);
		
		cbCategory = new JComboBox(listCategories.toArray());
		cbCategory.setBounds(180, 28, 138, 22);
		panel.add(cbCategory);
		
		lblCreate = new JLabel(new ImageIcon("images/icons/add.png"));
		lblCreate.setBounds(152, 234, 60, 60);
		contentPane.add(lblCreate);
		lblCreate.addMouseListener(ml);
		
		lblCancel = new JLabel(new ImageIcon("images/icons/red_x.png"));
		lblCancel.setBounds(316, 234, 60, 60);
		contentPane.add(lblCancel);		
		lblCancel.addMouseListener(ml);	
		
		setVisible(true);
	}
	
	public class MouseListen implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			Object o = e.getSource();
			
			if(o == lblCreate) {
				// int id_prov, String name, String description,float price, int amount, String category, String image, Date expire_date
				name = txtName.getText();
				description = textArea.getText();
				price = txtPrice.getText();
				amount = txtAmount.getText();
				category = String.valueOf(cbCategory.getSelectedItem());
				int available = 1;
//				date = Date.valueOf(txtExpDate.getText());
				
					try {
						id = Test.provider.getProviderID(Conexion.obtain(), comboBox.getSelectedItem().toString());
						
					} catch (Exception e1) {
						e1.printStackTrace();
					} 
				if(image.isEmpty()) {
					image="images/MercadonaLogo.png";
				}else {
				if(name.isEmpty() || description.isEmpty() || price.isEmpty() || amount.isEmpty() || category.isEmpty()) {
					JOptionPane.showMessageDialog(null, "You need to complete all the fields!", "Error", JOptionPane.WARNING_MESSAGE);
					
				}else {
					
					if(price.matches("^-?\\d+(\\.\\d+)?$") && amount.matches("\\d+")) {
						priceFloat = Float.parseFloat(txtPrice.getText());
						amountInt = Integer.parseInt(txtAmount.getText());
						
						p = new Product(id, name, description, priceFloat, amountInt, category, image, date, available);
						try {
							Test.product.save(Conexion.obtain(), p);
						} catch (ClassNotFoundException | SQLException e1) {
							e1.printStackTrace();
						}
						int prodId = 0;
						 try {
							prodId=  Test.product.getProductID(Conexion.obtain(), name);
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						Action a = new Action(Test.LogedInUser.getId() , prodId , p.getId_prov() , 1 , Date.valueOf(LocalDate.now()));
						try {
							Test.action.save(Conexion.obtain(), a);
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "You have created the Product!", "Creating...", JOptionPane.INFORMATION_MESSAGE);
						dispose();
						new SeeProd();
					}else {
						JOptionPane.showMessageDialog(null, "Price and Amount must be numbers!", "Error", JOptionPane.WARNING_MESSAGE);
					}
				}
				}
			}else if(o == lblCancel) {
				JOptionPane.showMessageDialog(null, "You have cancelled the creation", "Cancelling...", JOptionPane.ERROR_MESSAGE);
				dispose();
				new SeeProd();
			}else if(o == lblSetImage) {
				image = Method.FileChooserImage();
				if(image != null) {
					lblSetImage.setIcon(new ImageIcon(image));
				}
			}
			
		}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}
		
		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}
	}
	
	public List<String> createCategories() {
		listCategories.add("Food");
		listCategories.add("Drinks");
		listCategories.add("Fruits");
		listCategories.add("Yogurt");
		listCategories.add("Vegetables");
		listCategories.add("Sweets");
		listCategories.add("Other");
		
		return listCategories;
	}

	
	
}
