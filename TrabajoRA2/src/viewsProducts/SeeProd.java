package viewsProducts;

import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import methods.Method;
import models.Action;
import models.Product;
import services.Conexion;
import services.ProductService;
import test.Test;
import views.MainPanel;

@SuppressWarnings("serial")
public class SeeProd extends JFrame {

	private JPanel contentPane;
	public static JTable table;
	private JLabel lblCreate, lblEdit, lblDelete, lblUndo, lblImage, lblSell, lblSearch, lblNewLabel;
	private JComboBox cbSearch;
	private int idRow, row;
	private float inputFloat;
	private String option, inputString, image;
	private Product p = null;
	private ProductService ps = new ProductService();
	private List<String> listSearch = new ArrayList<String>();
	private List<String> listCategories = createCategories();

	public SeeProd() {
		super("CRUD products");
		getListSerch();
		try {
			Test.productList = Test.product.getAllProducts(Conexion.obtain());
		} catch (Exception e) {
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 647, 367);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 423, 239);
		contentPane.add(scrollPane);

		table = new JTable(Method.UploadProductList()) {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;

			}
		};
		scrollPane.setViewportView(table);

		table.getTableHeader().setResizingAllowed(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {

				row = table.getSelectedRow();

				String name = (String) table.getValueAt(row, 1);

				idRow = Test.productList.get(row).getId();
				image = Test.productList.get(row).getImage();
				lblImage.setIcon(new ImageIcon(image));
				try {
					System.out.println(idRow);
					Method.UploadProductList();
					p = ps.getProduct(Conexion.obtain(), ps.getProductID(Conexion.obtain(), name));
					System.out.println(p);

				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
				if (idRow >= 0) {
					lblEdit.setEnabled(true);
					lblDelete.setEnabled(true);
					lblSell.setEnabled(true);
				}

			}
		});

		MouseListen m = new MouseListen();

		lblCreate = new JLabel(new ImageIcon("images/icons/create.png"));
		lblCreate.setBounds(30, 268, 50, 50);
		lblCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		getContentPane().add(lblCreate);
		lblCreate.addMouseListener(m);

		lblEdit = new JLabel(new ImageIcon("images/icons/edit.png"));
		lblEdit.setBounds(110, 268, 50, 50);
		lblEdit.setEnabled(false);
		lblEdit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		getContentPane().add(lblEdit);
		lblEdit.addMouseListener(m);

		lblDelete = new JLabel(new ImageIcon("images/icons/delete.png"));
		lblDelete.setBounds(190, 268, 50, 50);
		lblDelete.setEnabled(false);
		lblDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		getContentPane().add(lblDelete);
		lblDelete.addMouseListener(m);

		lblUndo = new JLabel(new ImageIcon("images/icons/undo.png"));
		lblUndo.setBounds(350, 268, 50, 50);
		lblUndo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		getContentPane().add(lblUndo);
		lblUndo.addMouseListener(m);

		lblImage = new JLabel(new ImageIcon("images/products/Image_not_available.png"));
		lblImage.setBounds(443, 70, 180, 180);
		contentPane.add(lblImage);

		cbSearch = new JComboBox(listSearch.toArray());
		cbSearch.setBounds(443, 25, 140, 20);
		contentPane.add(cbSearch);

		lblSell = new JLabel(new ImageIcon("images/icons/sell.png"));
		lblSell.setBounds(270, 268, 50, 50);
		lblSell.setEnabled(false);
		contentPane.add(lblSell);
		lblSell.addMouseListener(m);

		lblNewLabel = new JLabel("Search products by: ");
		lblNewLabel.setBounds(443, 8, 140, 13);
		contentPane.add(lblNewLabel);

		lblSearch = new JLabel(new ImageIcon("images/icons/lupa.png"));
		lblSearch.setBounds(593, 20, 25, 25);
		contentPane.add(lblSearch);
		lblSearch.addMouseListener(m);
		lblImage.repaint();

		setVisible(true);
	}

	public class MouseListen implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			Object o = e.getSource();

			if (lblEdit.isEnabled()) {
				if (o == lblEdit) {

					dispose();
					EditProd ep = new EditProd(p);
					ep.setVisible(true);

				}
			}

			if (lblDelete.isEnabled()) {
				if (o == lblDelete) {

					int option = JOptionPane.showConfirmDialog(null, "Do you want to delete this provider?",
							"Delete provider", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);

					if (option == 1) {
						System.out.println("You didn't delete the team");
					} else {
						try {
							Test.product.remove(Conexion.obtain(), p.getId());

							Method.refreshTableProduct();

							Action a = new Action(Test.LogedInUser.getId(), p.getId(), p.getId_prov(), 3,
									Date.valueOf(LocalDate.now()));
							try {
								Test.action.remove(Conexion.obtain(), p.getId());
							} catch (ClassNotFoundException | SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							Test.actionList.add(a);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}

				}
			}

			if (lblSell.isEnabled()) {
				if (o == lblSell) {
					new SellProduct(p);
					dispose();
				}
			}

			if (o == lblCreate) {

				dispose();
				AddProd ap = new AddProd();
				ap.setVisible(true);

			} else if (o == lblUndo) {
				dispose();
				new MainPanel();
			} else if (o == lblSearch) {

				option = String.valueOf(cbSearch.getSelectedItem());
				if (option.equals("Name") || option.equals("Category") || option.equals("Provider name")) {

					// FILTRAR POR NOMBRE DE PROVEEDORES
					if (option.equalsIgnoreCase("Provider Name")) {

						inputString = JOptionPane.showInputDialog(null, "Write here the " + option + ": ",
								"Searching...", JOptionPane.QUESTION_MESSAGE);

						if (inputString != null && !inputString.trim().isEmpty()) {
							try {
								List<Product> sortedProv = ps.getProductsByProvider(Conexion.obtain(), inputString);
								Method.refreshTableProduct2(sortedProv);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}

						// FILTRAR POR NOMBRE DE PRODUCTOS
					} else if (option.equalsIgnoreCase("Name")) {

						inputString = JOptionPane.showInputDialog(null, "Write here the " + option + ": ",
								"Searching...", JOptionPane.QUESTION_MESSAGE);
						if (inputString != null && !inputString.trim().isEmpty()) {
							try {
								List<Product> sortedName = ps.getProductsByName(Conexion.obtain(), inputString);
								Method.refreshTableProduct2(sortedName);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
						// FILTRAR POR CATEGORÍA DE PRODUCTOS
					} else if (option.equalsIgnoreCase("Category")) {

						JComboBox<String> comboBox = new JComboBox<>(listCategories.toArray(new String[0]));
						comboBox.setSelectedIndex(0);

						JPanel panel = new JPanel();
						panel.setLayout(new GridLayout(2, 1));
						panel.add(new JLabel("Select a " + option + ":"));
						panel.add(comboBox);

						int result = JOptionPane.showConfirmDialog(null, panel, "Select Category",
								JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

						if (result == JOptionPane.OK_OPTION) {
							String selectedCategory = (String) comboBox.getSelectedItem();
							try {
								List<Product> sortedCategory = ps.getProductsByCategory(Conexion.obtain(),
										selectedCategory);
								Method.refreshTableProduct2(sortedCategory);
							} catch (Exception e1) {
								e1.printStackTrace();
							}

						} 
					}
//					FILTRAR POR PRECIOS DE PRODUCTOS
				} else {
					try {
						
						String input = JOptionPane.showInputDialog(null, "Escribe el valor " + option + ":", "Búsqueda...",
								JOptionPane.QUESTION_MESSAGE);
						if (input != null && !input.trim().isEmpty()) {
							
							inputFloat = Float.parseFloat(input);
							int slashedNumba = (int) ((inputFloat / 10) * 10);
							int aboveLimit = (int) (Math.ceil(slashedNumba / 10) * 10) + 10;
							int belowLimit = aboveLimit - 10;
							
							try {
								List<Product> sortedPrice = ps.getProductsByPrice(Conexion.obtain(),
										belowLimit, aboveLimit);
								Method.refreshTableProduct2(sortedPrice);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							
							
						}
					}catch(Exception e3) {
						JOptionPane.showMessageDialog(null, "Price inserted invalid", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {}

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

	public List<String> getListSerch() {
		listSearch.add("Name");
		listSearch.add("Category");
		listSearch.add("Provider name");
		listSearch.add("Price");

		return listSearch;
	}

	public static List<String> createCategories() {
		List<String> listCategories = new ArrayList<>();
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
