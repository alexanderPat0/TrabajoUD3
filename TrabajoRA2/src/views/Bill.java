package views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import methods.DrawPDF;
import methods.Method;
import models.Transaction;
import services.Conexion;
import test.Test;
import javax.swing.Icon;

public class Bill extends JFrame {

	private JPanel contentPane, panel, panel2, panel3;
	private JLabel lblBack, lblTittle, lbl_1, lbl_1_1, lblTotalPrice, lblPdf;
	private JTable table;
	private JTextField txtDollars;
	private float totalPrice;
	private JLabel lblCompany;
	private JLabel lblLocationCadiz;
	private JLabel lblEmail;
	private JLabel lblNumber;
	private JPanel panel_2;

	/**
	 * Create the frame.
	 */
	public Bill() {
		super("Bill Prodcuts");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 633, 720);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(189, 239, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		try {
			Test.transactionList = Test.transaction.getAllTransactions(Conexion.obtain());
		} catch (Exception e) {
			e.printStackTrace();
		}

		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(70, 49, 473, 560);
		contentPane.add(panel);
		panel.setLayout(null);

		lblTittle = new JLabel("BILLS");
		lblTittle.setFont(new Font("Monospaced", Font.PLAIN, 24));
		lblTittle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTittle.setBounds(10, 10, 453, 40);
		panel.add(lblTittle);

		lbl_1 = new JLabel(
				"-----------------------------------------------------------------------------------------------------------------");
		lbl_1.setBounds(10, 51, 453, 13);
		panel.add(lbl_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 74, 453, 360);
		panel.add(scrollPane);

		table = new JTable(Method.UploadTransactionList()) {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}
		};
		table.getTableHeader().setResizingAllowed(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(90);
		table.getColumnModel().getColumn(1).setPreferredWidth(160);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(70);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		scrollPane.setViewportView(table);

		DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				comp.setBackground(new Color(220, 220, 220));
				return comp;
			}
		};

		DefaultTableCellRenderer lastColumnRenderer = new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				if (column == table.getColumnCount() - 1) {
					comp.setBackground(new Color(255, 200, 150));
				}
				return comp;
			}
		};

		DefaultTableCellRenderer columnRenderer = new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				if (column != 0 && column != table.getColumnCount() - 1) {
					comp.setBackground(new Color(251, 242, 221));
				}
				return comp;
			}
		};

		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
		}

		table.getColumnModel().getColumn(table.getColumnCount() - 1).setCellRenderer(lastColumnRenderer);

		for (int i = 1; i < table.getColumnCount() - 1; i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(columnRenderer);
		}

		lbl_1_1 = new JLabel("----------------------------------------------------------------");
		lbl_1_1.setBounds(205, 444, 258, 13);
		panel.add(lbl_1_1);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 200, 150));
		panel_1.setBounds(242, 467, 210, 68);
		panel.add(panel_1);
		panel_1.setLayout(null);

		lblTotalPrice = new JLabel("Total Price:");
		lblTotalPrice.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTotalPrice.setBounds(6, 18, 86, 28);
		panel_1.add(lblTotalPrice);

		for (Transaction t : Test.transactionList) {
			totalPrice += (t.getPrice() * t.getAmount());
		}

		txtDollars = new JTextField();
		txtDollars.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtDollars.setBackground(new Color(251, 242, 221));
		txtDollars.setHorizontalAlignment(SwingConstants.CENTER);
		txtDollars.setEditable(false);
		txtDollars.setText(totalPrice + " $");
		txtDollars.setBounds(98, 18, 105, 28);
		panel_1.add(txtDollars);
		txtDollars.setColumns(10);

		panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(10, 452, 222, 98);
		panel.add(panel_2);
		panel_2.setLayout(null);

		lblCompany = new JLabel("Company: Mercadona");
		lblCompany.setBounds(10, 9, 202, 13);
		panel_2.add(lblCompany);

		lblLocationCadiz = new JLabel("Location: Cadiz");
		lblLocationCadiz.setBounds(10, 31, 202, 13);
		panel_2.add(lblLocationCadiz);

		lblEmail = new JLabel("Email: mercadona@gmai.com");
		lblEmail.setBounds(10, 53, 202, 13);
		panel_2.add(lblEmail);

		lblNumber = new JLabel("Number: 956 46 38 22");
		lblNumber.setBounds(10, 75, 202, 13);
		panel_2.add(lblNumber);

		panel2 = new JPanel();
		panel2.setBounds(60, 37, 473, 560);
		panel2.setLayout(null);
		panel2.setBackground(new Color(236, 236, 236));
		contentPane.add(panel2);

		panel3 = new JPanel();
		panel3.setLayout(null);
		panel3.setBackground(new Color(222, 222, 222));
		panel3.setBounds(50, 27, 473, 560);
		contentPane.add(panel3);
		
		MouseListen ml = new MouseListen();

		lblBack = new JLabel(new ImageIcon("images/icons/undo.png"));
		lblBack.setBounds(20, 614, 45, 40);
		lblBack.setToolTipText("Undo");
		contentPane.add(lblBack);
		lblBack.addMouseListener(ml);
		
		lblPdf = new JLabel(new ImageIcon("images/icons/pdf.png"));
		lblPdf.setToolTipText("PDF");
		lblPdf.setBounds(559, 614, 50, 50);
		contentPane.add(lblPdf);
		lblPdf.addMouseListener(ml);

		setVisible(true);
	}
	
	public class MouseListen implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			Object o = e.getSource();
			
			if(o == lblBack) {
				dispose();
				new MainPanel();
				
			}else if(o == lblPdf) {
				DrawPDF.writePDF();
			}
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
