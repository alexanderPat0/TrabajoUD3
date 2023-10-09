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
import models.Product;
import services.Conexion;
import test.Test;

@SuppressWarnings("serial")
public class EditProd extends JFrame {

	private JPanel contentPane;
	private JTextField txtName, txtPrice, txtExpDate, txtAmount;
	private JTextArea txtArea;
	private JComboBox<String> cbCategory, comboBox;
	private JLabel lblName, lblDescription, lblPrice, lblExpDate, lblImage, lblSetImage, lblCategory, lblAmount,
			lblCreate, lblCancel;
	private String name, description, amount, price, category, image;
	private Date date;
//	private Date date = new Date(20231210);
	private int id, id_prov, amountInt;
	private float priceFloat;
	private List<String> provNames;
	private List<String> listCategories = new ArrayList<String>();
	private Product p = new Product();

	/**
	 * Create the frame.
	 */
	public EditProd(Product p) {
		setTitle("Edit Product");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 564, 343);
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

		createCategories();

		id_prov = p.getId_prov();

		try {
			Test.productList = Test.product.getAllProducts(Conexion.obtain());
		} catch (Exception e) {
			e.printStackTrace();
		}

//		try {
//			id = Test.provider.getProviderID(Conexion.obtain(), name);
//			System.out.println(id);
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		} 

		MouseListen ml = new MouseListen();

		JLabel lblProvider = new JLabel("Provider:");
		lblProvider.setBounds(26, 11, 67, 14);
		panel.add(lblProvider);

		try {
			provNames = Test.provider.getNameProvs(Conexion.obtain());
		} catch (Exception e) {
			e.printStackTrace();
		}
		comboBox = new JComboBox<>();
		comboBox.setBounds(26, 27, 135, 22);
		for (String a : provNames) {
			comboBox.addItem(a);
		}
		panel.add(comboBox);

		lblName = new JLabel("Name:");
		lblName.setBounds(26, 60, 46, 14);
		panel.add(lblName);

		txtName = new JTextField(p.getName());
		txtName.setBounds(26, 74, 135, 20);
		panel.add(txtName);
		txtName.setColumns(10);

		lblPrice = new JLabel("Price:");
		lblPrice.setBounds(26, 105, 46, 14);
		panel.add(lblPrice);

		txtPrice = new JTextField(String.valueOf(p.getPrice()));
		txtPrice.setBounds(26, 120, 135, 20);
		panel.add(txtPrice);
		txtPrice.setColumns(10);

		lblExpDate = new JLabel("Expiration date:");
		lblExpDate.setBounds(26, 151, 107, 14);
		panel.add(lblExpDate);

		txtExpDate = new JTextField();
		txtExpDate.setText(String.valueOf(p.getExpire_date()));
		txtExpDate.setColumns(10);
		txtExpDate.setBounds(26, 167, 135, 20);
		txtExpDate.setEnabled(false);
		panel.add(txtExpDate);

		lblDescription = new JLabel("Description:");
		lblDescription.setBounds(338, 11, 81, 14);
		panel.add(lblDescription);

		txtArea = new JTextArea(p.getDescription());
		txtArea.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtArea.setLineWrap(true);
		txtArea.setWrapStyleWord(true);
		txtArea.setBounds(338, 26, 156, 160);
		panel.add(txtArea);

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

		cbCategory = new JComboBox(listCategories.toArray());
		cbCategory.setBounds(180, 28, 138, 22);
		panel.add(cbCategory);

		lblAmount = new JLabel("Amount:");
		lblAmount.setBounds(180, 60, 46, 14);
		panel.add(lblAmount);

		txtAmount = new JTextField(String.valueOf(p.getAmount()));
		txtAmount.setColumns(10);
		txtAmount.setBounds(180, 74, 138, 20);
		panel.add(txtAmount);

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

	public class MouseListen implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			Object o = e.getSource();

			if (o == lblCreate) {
				// int id_prov, String name, String description,float price, int amount, String
				// category, String image, Date expire_date
				name = txtName.getText();
				description = txtArea.getText();
				price = txtPrice.getText();
				amount = txtAmount.getText();
				category = String.valueOf(cbCategory.getSelectedItem());
				date = Date.valueOf(txtExpDate.getText());
				System.out.println(date);

				try {
					id = Test.product.getProductID(Conexion.obtain(), name);
					System.out.println(id);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				System.out.println(id);

				if (name.isEmpty() || description.isEmpty() || price.isEmpty() || amount.isEmpty() || category.isEmpty()
						|| image == null) {
					JOptionPane.showMessageDialog(null, "You need to complete all the fields!", "Error",
							JOptionPane.WARNING_MESSAGE);

				} else {
					if (price.matches("^-?\\d+(\\.\\d+)?$") && amount.matches("\\d+")) {
						priceFloat = Float.parseFloat(txtPrice.getText());
						amountInt = Integer.parseInt(txtAmount.getText());

						System.out.println(date);
						p = new Product(id, id_prov, name, description, priceFloat, amountInt, category, image, date);
						try {
							System.out.println("Llegas aquii");
							Test.product.save(Conexion.obtain(), p);
						} catch (ClassNotFoundException | SQLException e1) {
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "You have updated the Product!", "Updating...",
								JOptionPane.INFORMATION_MESSAGE);
						dispose();
						new SeeProd();
					} else {
						JOptionPane.showMessageDialog(null, "Price and Amount must be numbers!", "Error",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			} else if (o == lblCancel) {
				JOptionPane.showMessageDialog(null, "You have cancelled the edition", "Cancelling...",
						JOptionPane.ERROR_MESSAGE);
				dispose();
				new SeeProd();
			} else if (o == lblSetImage) {
				image = Method.FileChooserImage();
				if (image != null) {
					lblSetImage.setIcon(new ImageIcon(image));
				}
			}

		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}
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
