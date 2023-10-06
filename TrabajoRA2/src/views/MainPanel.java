package views;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class MainPanel extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JLabel lblProducts, lblExit, lblProviders, lblInfo;

	@SuppressWarnings("serial")
	public MainPanel() {
		setTitle("Mercadona");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 475, 315);
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
		
		lblProducts = new JLabel(new ImageIcon("images/icons/products.png"));
		lblProducts.setToolTipText("Products");
		lblProducts.setBounds(375, 10, 50, 50);
		contentPane.add(lblProducts);
		
		lblProviders = new JLabel(new ImageIcon("images/icons/provider.png"));
		lblProviders.setToolTipText("Providers");
		lblProviders.setBounds(375, 75, 50, 50);
		contentPane.add(lblProviders);
		
		lblInfo = new JLabel(new ImageIcon("images/icons/information.png"));
		lblInfo.setToolTipText("More Information");
		lblInfo.setBounds(375, 140, 50, 50);
		contentPane.add(lblInfo);
		
		lblExit = new JLabel(new ImageIcon("images/icons/exit.png"));
		lblExit.setToolTipText("Exit");
		lblExit.setBounds(375, 210, 50, 50);
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
			Object o = e.getSource();
			if(e.getSource() == lblProviders) {
				
				dispose();
				new viewsProviders.SeeProv();
				
			}else if(e.getSource() == lblProducts) {
				
				dispose();
				new viewsProducts.SeeProd();
				
			}else if(e.getSource() == lblInfo) {
				
				JOptionPane.showMessageDialog(null, "Oh yeah boi", "BOI", JOptionPane.INFORMATION_MESSAGE);
				
			}else if(e.getSource() == lblExit) {
				
				dispose();
				new Login();
//				LogedInUser = null;
//				O algo así, no sé.
				
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
