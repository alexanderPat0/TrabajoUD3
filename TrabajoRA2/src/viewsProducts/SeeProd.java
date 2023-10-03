package viewsProducts;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import methods.Method;
import models.Product;
import services.Conexion;
import services.ProductService;
import test.Test;

@SuppressWarnings("serial")
public class SeeProd extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton btnCreate, btnEdit, btnDelete, btnReturn;
	private int idRow,row;
	private Product p;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SeeProd frame = new SeeProd();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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
		
		btnCreate = new JButton("Create");
		btnCreate.setBounds(335, 11, 89, 23);
		contentPane.add(btnCreate);
		
		btnEdit = new JButton("Edit");
		btnEdit.setBounds(335, 45, 89, 23);
		btnEdit.setEnabled(false);
		contentPane.add(btnEdit);
		
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(335, 79, 89, 23);
		btnDelete.setEnabled(false);
		contentPane.add(btnDelete);
		
		btnReturn = new JButton("Return");
		btnReturn.setBounds(335, 227, 89, 23);
		contentPane.add(btnReturn);
		
		ButtonManager buttonMan = new ButtonManager();
		btnCreate.addActionListener(buttonMan);
		btnEdit.addActionListener(buttonMan);
		btnDelete.addActionListener(buttonMan);
		btnReturn.addActionListener(buttonMan);
		table.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				// Integer id, int id_prov, String name, String description, float price, String category, String image, Date expire_date
				row=table.getSelectedRow();
				idRow=Test.productList.get(row).getId();
				System.out.println(idRow);
				if(idRow>=0) {
					btnEdit.setEnabled(true);
					btnDelete.setEnabled(true);
					
				}
			
		}});
		
		
		
		
		setVisible(true);
	}
	
	public class ButtonManager implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == btnCreate) {
				
				dispose();
				new AddProd();
				
			}else if(e.getSource() == btnDelete) {
				try {
					Test.product.remove(Conexion.obtain(), idRow);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}else if(e.getSource() == btnEdit) {
				
			}else if(e.getSource() == btnReturn) {
				
				dispose();
				new views.MainPanel();
				
			}
			
		}

	}

}
