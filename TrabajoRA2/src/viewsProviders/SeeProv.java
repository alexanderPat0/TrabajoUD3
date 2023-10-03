package viewsProviders;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import methods.Method;
import services.ProviderService;
import viewsProducts.AddProd;

public class SeeProv extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton btnCreate, btnEdit, btnDelete, btnReturn;
	private ProviderService provider;
	
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

		table = new JTable(Method.UploadProviderList());
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

		setVisible(true);
	}
	
	public class Handler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Object o = e.getSource();
			
			if(o == btnCreate) {
				
				dispose();
				AddProd ap = new AddProd();
				ap.setVisible(true);
				
			}else if(o == btnCreate) {
				
				
			}else if(o == btnDelete) {
				
				
			}else if(o == btnEdit) {
				
				
			}else if(o == btnReturn) {
				
				dispose();
				new views.MainPanel();
				
			}
			
		}

	}

}
