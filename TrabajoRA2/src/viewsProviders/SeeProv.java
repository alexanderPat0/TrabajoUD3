package viewsProviders;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import methods.Method;
import models.Action;
import models.Provider;
import services.Conexion;
import test.Test;
import views.MainPanel;

@SuppressWarnings("serial")
public class SeeProv extends JFrame {

	private JPanel contentPane;
	public static JTable table;
	private JLabel lblUndo, lblCreate, lblEdit, lblDelete; 
	private int idRow, row;
	private Provider p = null;

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

		table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setReorderingAllowed(false);
		table.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				row = table.getSelectedRow();
				
				String name = (String) table.getValueAt(row, 0);
				idRow = Test.providerList.get(row).getId();
				try {
					Method.UploadProviderList();
					p = Test.provider.getProvider(Conexion.obtain(), Test.provider.getProviderID(Conexion.obtain(), name));
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
		lblCreate.setToolTipText("Create provider");
		lblCreate.setBounds(361, 11, 50, 50);
		lblCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		getContentPane().add(lblCreate);
		lblCreate.addMouseListener(m);
		
		lblEdit = new JLabel(new ImageIcon("images/icons/edit.png"));
		lblEdit.setToolTipText("Edit provider");
		lblEdit.setBounds(361, 74, 50, 50);
		lblEdit.setEnabled(false);
		lblEdit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		getContentPane().add(lblEdit);
		lblEdit.addMouseListener(m);
		
		lblDelete = new JLabel(new ImageIcon("images/icons/delete.png"));
		lblDelete.setToolTipText("Delete provider");
		lblDelete.setBounds(361, 137, 50, 50);
		lblDelete.setEnabled(false);
		lblDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		getContentPane().add(lblDelete);
		lblDelete.addMouseListener(m);

		lblUndo = new JLabel(new ImageIcon("images/icons/undo.png"));
		lblUndo.setToolTipText("Return");
		lblUndo.setBounds(361, 203, 50, 50);
		lblUndo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		getContentPane().add(lblUndo);
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
			
			if (lblDelete.isEnabled()) {
				if (o == lblDelete) {

					int option = JOptionPane.showConfirmDialog(null, "Do you want to delete this provider?",
							"Delete provider", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);

					if (option  == JOptionPane.YES_OPTION) {
						try {
							Test.provider.remove(Conexion.obtain(), p.getId());

							Method.refreshTableProvider();

							Action a = new Action(Test.LogedInUser.getId(), 0 , p.getId(), 3, Date.valueOf(LocalDate.now()));
							try {
								Test.action.save(Conexion.obtain(), a);
							} catch (ClassNotFoundException | SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							Test.actionList.add(a);
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
