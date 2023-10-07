package viewsProducts;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import methods.Method;
import models.Product;
import services.Conexion;
import services.ProductService;
import test.Test;
import views.MainPanel;

@SuppressWarnings("serial")
public class SeeProd extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JLabel lblCreate, lblEdit, lblDelete, lblUndo, lblImage, lblSell, lblSearch;
	private int idRow,row;
	private Product p = null;
	private ProductService ps = new ProductService();
	private List<String> listSearch = new ArrayList<String>();
	private JLabel lblNewLabel;

	public SeeProd() {
		super("CRUD products");
		getListSerch();
		try {
			Test.productList=Test.product.getAllProducts(Conexion.obtain());
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
				// Integer id, int id_prov, String name, String description, float price, String category, String image, Date expire_date
				row=table.getSelectedRow();
				idRow=Test.productList.get(row).getId();
				System.out.println(idRow);
				try {
					p = ps.getProduct(Conexion.obtain(), idRow);
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
		
		JComboBox comboBox = new JComboBox(listSearch.toArray());
		comboBox.setBounds(443, 25, 140, 20);
		contentPane.add(comboBox);
		
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

		setVisible(true);
	}
	
	public class MouseListen implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			Object o = e.getSource();
			
			if(lblEdit.isEnabled()) {
				if(o == lblEdit) {
					
					dispose();
					EditProd ep = new EditProd(p);
					ep.setVisible(true);
					
				}
			}
			
			if(lblDelete.isEnabled()) {
				if(o == lblDelete) {
					
					int option = JOptionPane.showConfirmDialog(null, "Do you want to delete this provider?", "Delete provider",
							JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);

					if (option == 1) {
						System.out.println("You didn't delete the team");
					} else {
						try {
							Test.product.remove(Conexion.obtain(), idRow);
							table = new JTable(Method.refreshTableProduct());
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					
				}
			}
			
			if(lblSell.isEnabled()) {
				if(o == lblSell) {
					JOptionPane.showConfirmDialog(null, "A");
					
				}
			}
			
			if(o == lblCreate) {
				
				dispose();
				AddProd ap = new AddProd();
				ap.setVisible(true);
				
			}else if(o == lblUndo){
				dispose();
				new MainPanel();
			}else if(o == lblSearch) {
				JOptionPane.showInputDialog("Elige");
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
	
	public List<String> getListSerch(){
		listSearch.add("Name");
		listSearch.add("Category");
		listSearch.add("Provider name");
		listSearch.add("Price");
		
		return listSearch;
	}
}
