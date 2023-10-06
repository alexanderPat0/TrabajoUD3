package viewsProducts;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.text.ParseException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import methods.ImageUtilities;
import models.Product;
import services.Conexion;
import test.Test;

@SuppressWarnings("serial")
public class EditProd extends JFrame {

	private JPanel contentPane;
	private JButton btnReturn, btnCreate, btnImage;
	private JTextField txtNombre, txtPrice, txtExpDate;
	private JLabel lblPrice, lblExpDate, lblNewLabel;
	private JComboBox<String>comboBox;
	private List<String>provNames;

	/**
	 * Create the frame.
	 */
	public EditProd(Product p) {
		setTitle("Edit Product");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 564, 302);
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
		                
		        RoundRectangle2D border = new RoundRectangle2D.Double(0, 0, 510, 200, 20, 20);
		        ImageUtilities.applyQualityRenderingHints(g2d);
		        g2d.drawImage(ImageUtilities.applyShadow(border, 2, getBackground(), Color.BLACK, 0.25f), 5, 5, this);
		        g2d.setColor(Color.DARK_GRAY);
		        g2d.translate(5, 5);
		        g2d.draw(border);
		        g2d.dispose();
		    }
		};
		panel.setBounds(10, 11, 527, 213);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblProvider = new JLabel("Provider:");
		lblProvider.setBounds(26, 11, 67, 14);
		panel.add(lblProvider);
		
		try {
			provNames=Test.provider.getNameProvs(Conexion.obtain());
		} catch (Exception e) {
			e.printStackTrace();
		} 
		comboBox = new JComboBox<>();
		comboBox.setBounds(26, 27, 120, 22);
		for(String a: provNames) {
			comboBox.addItem(a);
		}
		panel.add(comboBox);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(26, 60, 46, 14);
		panel.add(lblName);
		
		txtNombre = new JTextField(p.getName());
		txtNombre.setBounds(26, 74, 120, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		lblPrice = new JLabel("Price:");
		lblPrice.setBounds(26, 105, 46, 14);
		panel.add(lblPrice);
		
		txtPrice = new JTextField(String.valueOf(p.getPrice()));
		txtPrice.setBounds(26, 120, 120, 20);
		panel.add(txtPrice);
		txtPrice.setColumns(10);
		
		lblExpDate = new JLabel("Expiration date:");
		lblExpDate.setBounds(26, 151, 107, 14);
		panel.add(lblExpDate);
		
		txtExpDate = new JTextField();
		txtExpDate.setText(String.valueOf(p.getExpire_date()));
		txtExpDate.setColumns(10);
		txtExpDate.setBounds(26, 167, 120, 20);
		txtExpDate.setEnabled(false);
		panel.add(txtExpDate);
		
		lblNewLabel = new JLabel("Description:");
		lblNewLabel.setBounds(170, 11, 81, 14);
		panel.add(lblNewLabel);
		
		JTextArea textArea = new JTextArea(p.getDescription());
		textArea.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setBounds(172, 27, 156, 160);
		panel.add(textArea);
		
		JLabel lblImage = new JLabel("Image:");
		lblImage.setBounds(358, 11, 46, 14);
		panel.add(lblImage);
		
		btnImage = new JButton("Add image");
		btnImage.setBounds(354, 27, 145, 160);
		panel.add(btnImage);
		
		btnReturn = new JButton("Return");
		btnReturn.setBounds(128, 235, 103, 23);
		contentPane.add(btnReturn);
		
		btnCreate = new JButton("Create");
		btnCreate.setBounds(359, 235, 103, 23);
		contentPane.add(btnCreate);
		ButtonManager handler=new ButtonManager();
		btnCreate.addActionListener(handler);
		btnImage.addActionListener(handler);
		btnReturn.addActionListener(handler);
		
		
		setVisible(true);
	}
	public class ButtonManager implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == btnCreate) {
				
				
				
			}else if(e.getSource() == btnReturn) {
				dispose();
				new SeeProd();
			}
			
		}

	}
}
