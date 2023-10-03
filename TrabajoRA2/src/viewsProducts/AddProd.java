package viewsProducts;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import methods.ImageUtilities;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class AddProd extends JFrame {

	private JPanel contentPane;
	private JButton btnReturn, btnCreate;


	public AddProd() {
		setTitle("Oh boi new peoduct lets go");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 289);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel(){
			@Override
		    public Dimension getPreferredSize() {
		        return new Dimension(200, 200);
		    }

		    @Override
		    protected void paintComponent(Graphics g) {
		        super.paintComponent(g);
		        Graphics2D g2d = (Graphics2D) g.create();
		                
		        RoundRectangle2D border = new RoundRectangle2D.Double(0, 0, 410, 189, 20, 20);
		        ImageUtilities.applyQualityRenderingHints(g2d);
		        g2d.drawImage(ImageUtilities.applyShadow(border, 2, getBackground(), Color.BLACK, 0.25f), 5, 5, this);
		        g2d.setColor(Color.DARK_GRAY);
		        g2d.translate(5, 5);
		        g2d.draw(border);
		        g2d.dispose();
		    }
		};
		panel.setBounds(10, 11, 414, 193);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblProvider = new JLabel("Provider:");
		lblProvider.setBounds(10, 11, 67, 14);
		panel.add(lblProvider);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(74, 7, 95, 22);
		panel.add(comboBox);
		
		btnReturn = new JButton("Return");
		btnReturn.setBounds(85, 215, 89, 23);
		contentPane.add(btnReturn);
		
		btnCreate = new JButton("Create");
		btnCreate.setBounds(259, 215, 89, 23);
		contentPane.add(btnCreate);
		
		
		
		
		setVisible(true);
	}
}
