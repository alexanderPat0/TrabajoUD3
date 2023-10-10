package views;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import methods.Method;
import models.Action;
import test.Test;

@SuppressWarnings("serial")
public class MainPanel extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JLabel lblProducts, lblExit, lblProviders, lblInfo;

	public MainPanel() {
		setTitle("Mercadona");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 555, 315);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 453, 239);
		contentPane.add(scrollPane);
		

		DefaultTableModel model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		model.setColumnIdentifiers(
				new String[] { "Date", "Log" });

		for (Action a : Test.actionList) {
			String stringAction = "";
			try {
				stringAction = Method.getActionString(a);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			model.addRow(new Object[] { a.getDate() , stringAction});
		}

		table = new JTable(model);
		
		scrollPane.setViewportView(table);

		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setReorderingAllowed(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(70);
		table.getColumnModel().getColumn(1).setPreferredWidth(380);

		
		lblProducts = new JLabel(new ImageIcon("images/icons/products.png"));
		lblProducts.setToolTipText("Products");
		lblProducts.setBounds(473, 11, 50, 50);
		contentPane.add(lblProducts);
		
		lblProviders = new JLabel(new ImageIcon("images/icons/provider.png"));
		lblProviders.setToolTipText("Providers");
		lblProviders.setBounds(473, 76, 50, 50);
		contentPane.add(lblProviders);
		
		lblInfo = new JLabel(new ImageIcon("images/icons/information.png"));
		lblInfo.setToolTipText("More Information");
		lblInfo.setBounds(473, 141, 50, 50);
		contentPane.add(lblInfo);
		
		lblExit = new JLabel(new ImageIcon("images/icons/exit.png"));
		lblExit.setToolTipText("Exit");
		lblExit.setBounds(473, 211, 50, 50);
		contentPane.add(lblExit);
		
		MouseListen ml = new MouseListen();
		lblProviders.addMouseListener(ml);
		lblProducts.addMouseListener(ml);
		lblExit.addMouseListener(ml);
		lblInfo.addMouseListener(ml);

		setVisible(true);
	}
	
	public class MouseListen implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getSource() == lblProviders) {
				
				dispose();
				new viewsProviders.SeeProv();
				
			}else if(e.getSource() == lblProducts) {
				
				dispose();
				new viewsProducts.SeeProd();
				
			}else if(e.getSource() == lblInfo) {
				
				dispose();
				new Bill();
				
			}else if(e.getSource() == lblExit) {
				
				dispose();
				new Login();
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
