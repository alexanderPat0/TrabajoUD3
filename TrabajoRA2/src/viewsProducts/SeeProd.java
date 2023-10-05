package viewsProducts;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import methods.Method;
import models.Product;
import services.Conexion;
import test.Test;
import views.MainPanel;
import viewsProviders.AddProv;

@SuppressWarnings("serial")
public class SeeProd extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JLabel lblCreate, lblEdit, lblDelete, lblUndo;
	private int idRow,row;
	private Product p;

	public SeeProd() {
		try {
			Test.productList=Test.product.getAllProducts(Conexion.obtain());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 318, 239);
		contentPane.add(scrollPane);

		table = new JTable(Method.UploadProductList()) {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
	            return false;
	        
			}
		};
		scrollPane.setViewportView(table);

		table.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				// Integer id, int id_prov, String name, String description, float price, String category, String image, Date expire_date
				row=table.getSelectedRow();
				idRow=Test.productList.get(row).getId();
				System.out.println(idRow);
				if (idRow >= 0) {
					lblEdit.setEnabled(true);
					lblDelete.setEnabled(true);
				}
			}
		});
		
		MouseListen m = new MouseListen();
		
		lblCreate = new JLabel(new ImageIcon("images/icons/create.png"));
		lblCreate.setBounds(361, 11, 50, 50);
		lblCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		add(lblCreate);
		lblCreate.addMouseListener(m);
		
		lblEdit = new JLabel(new ImageIcon("images/icons/edit.png"));
		lblEdit.setBounds(361, 74, 50, 50);
		lblEdit.setEnabled(false);
		lblEdit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		add(lblEdit);
		lblEdit.addMouseListener(m);
		
		lblDelete = new JLabel(new ImageIcon("images/icons/delete.png"));
		lblDelete.setBounds(361, 137, 50, 50);
		lblDelete.setEnabled(false);
		lblDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		add(lblDelete);
		lblDelete.addMouseListener(m);

		lblUndo = new JLabel(new ImageIcon("images/icons/undo.png"));
		lblUndo.setBounds(361, 203, 50, 50);
		lblUndo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		add(lblUndo);
		lblUndo.addMouseListener(m);

		setVisible(true);
	}
	
	public class MouseListen implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			Object o = e.getSource();
			
			if(lblEdit.isEnabled()) {
				if(o == lblEdit) {
					
					dispose();
//					EditProd ep = new EditProd(p);
//					ep.setVisible(true);
					
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
							Method.refreshTableProvider();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					
				}
			}
			
			if(o == lblCreate) {
				
				dispose();
				AddProd ap = new AddProd();
				ap.setVisible(true);
				
			}else if(o == lblUndo){
				dispose();
				new MainPanel();
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

}
