package viewsProviders;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import methods.Method;
import services.Conexion;
import services.ProviderService;
import test.Test;
import viewsProducts.AddProd;

public class SeeProv extends JFrame {

	private JPanel contentPane;
	public static JTable table;
	private JButton btnCreate, btnEdit, btnDelete, btnReturn;
	private int idRow, row;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SeeProv frame = new SeeProv();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SeeProv() {
		try {
			Test.providerList=Test.provider.getAllProviders(Conexion.obtain());
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

		table = new JTable(Method.UploadProviderList()) {
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
		contentPane.add(btnEdit);
		
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(335, 79, 89, 23);
		contentPane.add(btnDelete);
		
		btnReturn = new JButton("Return");
		btnReturn.setBounds(335, 227, 89, 23);
		contentPane.add(btnReturn);
		
		Handler h = new Handler();
		btnCreate.addActionListener(h);
		btnEdit.addActionListener(h);
		btnDelete.addActionListener(h);
		btnReturn.addActionListener(h);
		
		table.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				row=table.getSelectedRow();
				idRow=Test.providerList.get(row).getId();
				System.out.println(idRow);
				if(idRow>=0) {
					btnEdit.setEnabled(true);
					btnDelete.setEnabled(true);	
				}
		}});

		setVisible(true);
	}
	
	public class Handler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Object o = e.getSource();
			
			if(o == btnCreate) {
				
				dispose();
				AddProv ap = new AddProv();
				ap.setVisible(true);
				
			}else if(o == btnDelete) {
				try {
					Test.provider.remove(Conexion.obtain(), idRow);
					Method.refreshTableProvider();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}else if(o == btnEdit) {
				
				
			}else if(o == btnReturn) {
				
				dispose();
				new views.MainPanel();
				
			}
			
		}

	}

}
