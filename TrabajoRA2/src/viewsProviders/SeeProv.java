package viewsProviders;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import methods.Method;
import models.Provider;
import services.Conexion;
import services.ProviderService;
import test.Test;
import views.MainPanel;

public class SeeProv extends JFrame {

	private JPanel contentPane;
	public static JTable table;
	private JLabel lblUndo, lblCreate, lblEdit, lblDelete; 
	private int idRow, row;
	private Provider p = null;
	private ProviderService ps = new ProviderService();

	public SeeProv() {
		super("Providers");
		try {
			Test.providerList = Test.provider.getAllProviders(Conexion.obtain());
		} catch (Exception e) {
			e.printStackTrace();
		}
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

		table = new JTable(Method.UploadProviderList()) {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;

			}
		};
		scrollPane.setViewportView(table);

		table.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				row = table.getSelectedRow();
				idRow = Test.providerList.get(row).getId();
				System.out.println(idRow);
				try {
					p = ps.getProvider(Conexion.obtain(), idRow);
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}

				if (idRow >= 0) {
					lblEdit.setEnabled(true);
					lblDelete.setEnabled(true);
				}
			}
		});
		
		MouseListen m = new MouseListen();
		
		lblCreate = new JLabel(new ImageIcon("images/icons/create.png"));
		lblCreate.setBounds(361, 11, 50, 50);
		lblCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		add(lblCreate);
		lblCreate.addMouseListener(m);
		
		lblEdit = new JLabel(new ImageIcon("images/icons/edit.png"));
		lblEdit.setBounds(361, 74, 50, 50);
		lblEdit.setEnabled(false);
		lblEdit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		add(lblEdit);
		lblEdit.addMouseListener(m);
		
		lblDelete = new JLabel(new ImageIcon("images/icons/delete.png"));
		lblDelete.setBounds(361, 137, 50, 50);
		lblDelete.setEnabled(false);
		lblDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		add(lblDelete);
		lblDelete.addMouseListener(m);

		lblUndo = new JLabel(new ImageIcon("images/icons/undo.png"));
		lblUndo.setBounds(361, 203, 50, 50);
		lblUndo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		add(lblUndo);
		lblUndo.addMouseListener(m);

		setVisible(true);
	}
	
	public class MouseListen implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			Object o = e.getSource();
			
			if(lblEdit.isEnabled()) {
				if(o == lblEdit) {
					
					dispose();
					EditProv ep = new EditProv(p);
					ep.setVisible(true);
					
				}
			}
			
			if(lblDelete.isEnabled()) {
				if(o == lblDelete) {
					
					int option = JOptionPane.showConfirmDialog(null, "Do you want to delete this provider?", "Delete provider",
							JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);

					if (option == 1) {
						System.out.println("You didn't delete the team");
					} else {
						try {
							Test.provider.remove(Conexion.obtain(), idRow);
							Method.refreshTableProvider();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					
				}
			}
			
			if(o == lblCreate) {
				
				dispose();
				AddProv ap = new AddProv();
				ap.setVisible(true);
				
			}else if(o == lblUndo){
				dispose();
				new MainPanel();
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
