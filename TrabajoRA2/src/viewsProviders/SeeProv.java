package viewsProviders;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;

public class SeeProv extends JFrame {

	private JPanel contentPane;
	private JTable table;

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
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.setBounds(335, 11, 89, 23);
		contentPane.add(btnCreate);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setBounds(335, 45, 89, 23);
		contentPane.add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(335, 79, 89, 23);
		contentPane.add(btnDelete);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.setBounds(335, 227, 89, 23);
		contentPane.add(btnReturn);
		
		
		
		
		setVisible(true);
	}

}
