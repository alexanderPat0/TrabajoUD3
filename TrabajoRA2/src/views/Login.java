package views;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import methods.ImageUtilities;
import methods.RoundedBorderPane;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField passwordField;

	public Login() {
		
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 301, 328);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNoAcc = new JLabel("You haven't registered yet?");
		lblNoAcc.setBounds(68, 230, 169, 14);
		contentPane.add(lblNoAcc);

		JLabel lblRegister = new JLabel("Register");
		lblRegister.setBounds(112, 255, 59, 14);
		lblRegister.setForeground(Color.BLUE.darker());
		lblRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(lblRegister);
		
		JPanel panel = new RoundedBorderPane();
		panel.setBounds(31, 11, 223, 208);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUsername.setBounds(38, 22, 105, 24);
		panel.add(lblUsername);
				
		txtUsername = new JTextField();
		txtUsername.setBounds(38, 48, 139, 28);
		panel.add(txtUsername);
		txtUsername.setColumns(10);
						
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPassword.setBounds(38, 87, 105, 24);
		panel.add(lblPassword);
								
		passwordField = new JPasswordField();
		passwordField.setBounds(38, 114, 139, 28);
		panel.add(passwordField);
		
		JButton btnLogin = new JButton("Log in");
		btnLogin.setBackground(Color.BLUE);
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLogin.setBounds(38, 153, 139, 28);
		panel.add(btnLogin);

		lblRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				new Register();
			}
		});
		setVisible(true);

	}
}
