package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Register extends JFrame {

	private JPanel contentPane;
	private JTextField textName, textAge,  textUsername, textPassword, textConfirmPassword;
	private JButton btnSave, btnCancel;
	
	public Register() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 385, 434);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 123, 206));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(202, 234, 255));
		panel.setBounds(68, 23, 239, 345);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNombre = new JLabel("Name");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setBounds(35, 10, 172, 13);
		panel.add(lblNombre);
		
		JLabel lblEdad = new JLabel("Age");
		lblEdad.setHorizontalAlignment(SwingConstants.CENTER);
		lblEdad.setBounds(35, 62, 172, 13);
		panel.add(lblEdad);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setBounds(35, 122, 172, 13);
		panel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setBounds(35, 179, 172, 13);
		panel.add(lblPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfirmPassword.setBounds(35, 237, 172, 13);
		panel.add(lblConfirmPassword);
		
		textName = new JTextField();
		textName.setBounds(35, 33, 172, 19);
		panel.add(textName);
		textName.setColumns(10);
		
		textAge = new JTextField();
		textAge.setColumns(10);
		textAge.setBounds(35, 85, 172, 19);
		panel.add(textAge);
		
		textUsername = new JTextField();
		textUsername.setColumns(10);
		textUsername.setBounds(35, 145, 172, 19);
		panel.add(textUsername);
		
		textPassword = new JTextField();
		textPassword.setColumns(10);
		textPassword.setBounds(35, 202, 172, 19);
		panel.add(textPassword);
		
		textConfirmPassword = new JTextField();
		textConfirmPassword.setColumns(10);
		textConfirmPassword.setBounds(35, 260, 172, 19);
		panel.add(textConfirmPassword);
		
		handler h = new handler();
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(h);
		btnSave.setBounds(20, 303, 85, 32);
		panel.add(btnSave);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(h);
		btnCancel.setBounds(144, 303, 85, 32);
		panel.add(btnCancel);
		
		setVisible(true);
		
	}
	
	public class handler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object o = e.getSource();
			
			if(o == btnSave) {
				
				
			}else if(o == btnCancel) {
				
				
			}
			
		}
		
	}
}
