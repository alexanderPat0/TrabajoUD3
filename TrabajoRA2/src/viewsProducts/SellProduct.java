package viewsProducts;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import methods.ImageUtilities;
import models.Product;
import services.Conexion;
import services.ProductService;
import test.Test;

public class SellProduct extends JFrame {

	private JPanel contentPane, panel, panel2;
	private JLabel lblImage, lblTittle, lblSelectAmount;
	private JComboBox cbAmount;
	private List<Integer> listAmount = new ArrayList<Integer>();
	private JLabel lblInfo, lblPriceUnit, lbl_1, lbl_2, lbl_3, lblTotalPrice, lblStocks, lblSave, lblCancel;
	private JTextField UnitPrice, TotalPrice, textStock;
	private Product p = new Product();
	private int amount, available = 1, finalStock;
	private ProductService ps = new ProductService();

	/**
	 * Create the frame.
	 */
	public SellProduct(Product p2) {
		super("Sell a Product");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 562, 419);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		p = p2;
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel() {
			@Override
			public Dimension getPreferredSize() {
				return new Dimension(200, 200);
			}

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g.create();

				RoundRectangle2D border = new RoundRectangle2D.Double(0, 0, 480, 310, 20, 20);
				ImageUtilities.applyQualityRenderingHints(g2d);
				g2d.drawImage(ImageUtilities.applyShadow(border, 2, getBackground(), Color.BLACK, 0.25f), 5, 5, this);
				g2d.setColor(Color.DARK_GRAY);
				g2d.translate(5, 5);
				g2d.draw(border);
				g2d.dispose();
			}
		};
		panel.setBounds(32, 27, 506, 335);
		contentPane.add(panel);
		panel.setLayout(null);

		lblTittle = new JLabel("Selling the product: " + p.getName());
		lblTittle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTittle.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		lblTittle.setBounds(20, 10, 448, 22);
		panel.add(lblTittle);

		lblImage = new JLabel(new ImageIcon(p.getImage()));
		lblImage.setBounds(20, 42, 173, 142);
		panel.add(lblImage);

		for (int i = 1; i <= p.getAmount(); i++) {
			listAmount.add(i);
		}

		cbAmount = new JComboBox(listAmount.toArray());
		cbAmount.setBounds(20, 219, 173, 21);
		cbAmount.setEditable(true);
		panel.add(cbAmount);

		cbAmount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedValue = (int) cbAmount.getSelectedItem();
				if (selectedValue > p.getAmount()) {
					cbAmount.setSelectedItem(p.getAmount());
				}
					amount = p.getAmount() - selectedValue;
					System.out.println(amount);
					textStock.setText(String.valueOf(amount));
					finalStock = amount;
				int selectedValue2 = (int) cbAmount.getSelectedItem();
				float price = p.getPrice();

				TotalPrice.setText(String.valueOf(price * selectedValue));
			}
		});

		lblSelectAmount = new JLabel("Select the Amount");
		lblSelectAmount.setBounds(20, 196, 173, 13);
		panel.add(lblSelectAmount);

		panel2 = new JPanel();
		panel2.setBounds(216, 42, 252, 253);
		panel.add(panel2);
		panel2.setLayout(null);

		lblInfo = new JLabel("Information of the product");
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setBounds(10, 10, 232, 13);
		panel2.add(lblInfo);
		lblInfo.setBackground(new Color(255, 255, 255));

		lblPriceUnit = new JLabel("Price by unit:");
		lblPriceUnit.setBounds(10, 43, 173, 13);
		panel2.add(lblPriceUnit);

		lbl_1 = new JLabel("----------------------------------------------------------");
		lbl_1.setBounds(10, 22, 232, 13);
		panel2.add(lbl_1);

		UnitPrice = new JTextField(String.valueOf(p.getPrice()));
		UnitPrice.setEditable(false);
		UnitPrice.setBounds(10, 66, 96, 19);
		panel2.add(UnitPrice);
		UnitPrice.setColumns(10);

		lbl_2 = new JLabel("----------------------------------------------------------");
		lbl_2.setBounds(10, 95, 232, 13);
		panel2.add(lbl_2);

		lblTotalPrice = new JLabel("Total price:");
		lblTotalPrice.setBounds(10, 116, 173, 13);
		panel2.add(lblTotalPrice);

		TotalPrice = new JTextField(
				String.valueOf(p.getPrice() * Float.parseFloat(cbAmount.getSelectedItem().toString())));
		TotalPrice.setEditable(false);
		TotalPrice.setColumns(10);
		TotalPrice.setBounds(10, 139, 96, 19);
		panel2.add(TotalPrice);

		lbl_3 = new JLabel("----------------------------------------------------------");
		lbl_3.setBounds(10, 168, 232, 13);
		panel2.add(lbl_3);

		lblStocks = new JLabel("Remaining stock: ");
		lblStocks.setBounds(10, 191, 173, 13);
		panel2.add(lblStocks);

		textStock = new JTextField(
				String.valueOf(p.getAmount() - Integer.parseInt(cbAmount.getSelectedItem().toString())));
		textStock.setEditable(false);
		textStock.setColumns(10);
		textStock.setBounds(10, 214, 96, 19);
		panel2.add(textStock);
		
		MouseListen ml = new MouseListen();
		
		lblSave = new JLabel(new ImageIcon("images/icons/add.png"));
		lblSave.setToolTipText("Save");
		lblSave.setBounds(20, 250, 60, 60);
		lblSave.addMouseListener(ml);
		panel.add(lblSave);
		
		lblCancel = new JLabel(new ImageIcon("images/icons/red_x.png"));
		lblCancel.setToolTipText("Cancel");
		lblCancel.setBounds(133, 250, 60, 60);
		lblCancel.addMouseListener(ml);
		panel.add(lblCancel);

		setVisible(true);
	}
	
	public class MouseListen implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			Object o = e.getSource();
			amount = Integer.parseInt(textStock.getText());
			if(o == lblSave) {
				
				System.out.println("Amount: " + amount);
				System.out.println("Available: "+available);
					Product p2 = new Product(p.getId(), p.getId_prov(), p.getName(), p.getDescription(), p.getPrice(), amount, p.getCategory(),
							p.getImage(), p.getExpire_date(), available);
					System.out.println(p2);
					try {
						Test.product.save(Conexion.obtain(), p2);
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "You have sold the product", "Selling...", JOptionPane.INFORMATION_MESSAGE);
					dispose();
					new SeeProd();
			}else if(o == lblCancel) {
				JOptionPane.showMessageDialog(null, "You have cancelled the sale", "Cancelling...", JOptionPane.ERROR_MESSAGE);
				dispose();
				new SeeProd();
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
