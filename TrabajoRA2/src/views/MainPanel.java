package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class MainPanel extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton btnProducts, btnExit, btnProviders, btnInfo;

	@SuppressWarnings("serial")
	public MainPanel() {
		setTitle("Mercadona");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 315, 239);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		btnProducts = new JButton("Products");
		btnProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnProducts) {
					
				}
			}
		});
		btnProducts.setBounds(335, 11, 89, 23);
		contentPane.add(btnProducts);
		
		btnProviders = new JButton("Providers");
		btnProviders.setBounds(335, 45, 89, 23);
		contentPane.add(btnProviders);
		
		btnInfo = new JButton("More info");
		btnInfo.setBounds(335, 79, 89, 23);
		contentPane.add(btnInfo);
		
		btnExit = new JButton("Exit");
		btnExit.setBounds(335, 227, 89, 23);
		contentPane.add(btnExit);
		
		ButtonManager handler = new ButtonManager();
		btnProviders.addActionListener(handler);
		btnProducts.addActionListener(handler);
		btnExit.addActionListener(handler);
		btnInfo.addActionListener(handler);
		
		
		

		setVisible(true);
	}
	
	private class ButtonManager implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnProviders) {
				
				dispose();
				new viewsProviders.SeeProv();
				
			}else if(e.getSource() == btnProducts) {
				
				dispose();
				new viewsProducts.SeeProd();
				
			}else if(e.getSource() == btnInfo) {
				
				JOptionPane.showMessageDialog(null, "Oh yeah boi", "BOI", JOptionPane.INFORMATION_MESSAGE);
				
			}else if(e.getSource() == btnExit) {
				
				dispose();
				new Login();
//				LogedInUser = null;
//				O algo así, no sé.
				
			} 
		}
		
	}
}
