package views;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import methods.TestPane;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField passwordField;

	public Login() {
		
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 301, 303);
		setLocationRelativeTo(null);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNoAcc = new JLabel("New account?");
		lblNoAcc.setBounds(65, 230, 83, 14);
		contentPane.add(lblNoAcc);

		JLabel lblRegister = new JLabel("Register");
		lblRegister.setBounds(160, 230, 59, 14);
		lblRegister.setForeground(Color.BLUE.darker());
		lblRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(lblRegister);
		
		JPanel panel = new TestPane();
		panel.setBounds(31, 11, 223, 208);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(75, 42, 83, 14);
		panel.add(lblUsername);
				
		txtUsername = new JTextField();
		txtUsername.setBounds(38, 64, 139, 20);
		panel.add(txtUsername);
		txtUsername.setColumns(10);
						
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(75, 95, 83, 14);
		panel.add(lblPassword);
								
		passwordField = new JPasswordField();
		passwordField.setBounds(38, 117, 139, 20);
		panel.add(passwordField);
		
		JButton btn_ = new JButton("New button");
		btn_.setBounds(0, 0, 89, 23);
		panel.add(btn_);

		lblRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				new Register();
			}
		});

	}
}
