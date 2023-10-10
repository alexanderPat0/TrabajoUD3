package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Bill extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bill frame = new Bill();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Bill() {
		super("Bill Prodcuts");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 616, 691);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(189, 239, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		
		
		
		
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(70, 49, 473, 560);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblTittle = new JLabel("BILL");
		lblTittle.setFont(new Font("Monospaced", Font.PLAIN, 24));
		lblTittle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTittle.setBounds(10, 10, 453, 40);
		panel.add(lblTittle);
		
		JLabel lbl_1 = new JLabel("-----------------------------------------------------------------------------------------------------------------");
		lbl_1.setBounds(10, 51, 453, 13);
		panel.add(lbl_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 82, 453, 360);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lbl_1_1 = new JLabel("-----------------------------------------------------------------------------------------------------------------");
		lbl_1_1.setBounds(10, 452, 453, 13);
		panel.add(lbl_1_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(237, 213, 139));
		panel_1.setBounds(242, 475, 221, 75);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblTotalPrice = new JLabel("Total Price:");
		lblTotalPrice.setBounds(10, 10, 86, 28);
		panel_1.add(lblTotalPrice);
		
		JPanel panel2 = new JPanel();
		panel2.setBounds(60, 37, 473, 560);
		panel2.setLayout(null);
		panel2.setBackground(new Color(236, 236, 236));
		contentPane.add(panel2);
		
		JPanel panel3 = new JPanel();
		panel3.setLayout(null);
		panel3.setBackground(new Color(222, 222, 222));
		panel3.setBounds(50, 27, 473, 560);
		contentPane.add(panel3);
	}
}
