package views;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import methods.ImageUtilities;
import models.User;
import services.Conexion;
import services.UserService;

public class Register extends JFrame {

	private JPanel contentPane;
	private JTextField textName,  textUsername;
	private JPasswordField textPassword, textConfirmPassword;
	private JComboBox<Integer> cbAge;
	private JLabel lblSave, lblCancel;
	private JProgressBar progressBar;
	private String name, username, password, confirmPassword, regex;
	private int age, security = 0;
	
	public Register() {
		super("Registration Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 387, 495);
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
		                
		        RoundRectangle2D border = new RoundRectangle2D.Double(0, 0, 227, 390, 20, 20);
		        ImageUtilities.applyQualityRenderingHints(g2d);
		        g2d.drawImage(ImageUtilities.applyShadow(border, 2, getBackground(), Color.BLACK, 0.25f), 5, 5, this);
		        g2d.setColor(Color.DARK_GRAY);
		        g2d.translate(5, 5);
		        g2d.draw(border);
		        g2d.dispose();
		    }
		};
		panel.setBackground(new Color(240, 240, 240));;
		panel.setBounds(68, 23, 235, 401);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNombre = new JLabel("Name");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setBounds(35, 10, 162, 13);
		panel.add(lblNombre);
		
		JLabel lblEdad = new JLabel("Age");
		lblEdad.setHorizontalAlignment(SwingConstants.CENTER);
		lblEdad.setBounds(35, 62, 162, 13);
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
		lblConfirmPassword.setBounds(35, 261, 172, 13);
		panel.add(lblConfirmPassword);
		
		progressBar = new JProgressBar();
		progressBar.setBounds(35, 231, 172, 11);
		progressBar.setValue(security);
		panel.add(progressBar);
		
		textName = new JTextField();
		textName.setBounds(35, 33, 172, 19);
		panel.add(textName);
		textName.setColumns(10);
		
		textUsername = new JTextField();
		textUsername.setColumns(10);
		textUsername.setBounds(35, 145, 172, 19);
		panel.add(textUsername);
		
		textPassword = new JPasswordField();
		textPassword.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
            	verifyPassword();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            	verifyPassword();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            	verifyPassword();
            }

            private void verifyPassword() {
                String pass = new String(textPassword.getPassword());
                if (pass.length() < 8 || pass.length() > 20) {
//					System.out.println("Password must be between 8-20 characters");
					progressBar.setForeground(Color.RED);
	            }else {
	            	security+=25;
	            	progressBar.setValue(100);
	            }
	            
	            if (!pass.matches(".*[a-zA-Z].*")) {
//	            	System.out.println("Password must contain a letter");
	            	progressBar.setForeground(Color.ORANGE);
	            }else {
	            	security+=25;
	            	progressBar.setValue(25);
	            }
	            
	            if (!pass.matches(".*\\d.*")) {
//	            	System.out.println("Password must contain a number");
	            	progressBar.setForeground(Color.YELLOW);
	            }else {
	            	security+=25;
	            	progressBar.setValue(50);
	            }
	            
	            if (!pass.matches(".*[@#$%^&+=!].*")) {
//	            	System.out.println("Password must contain a special caracter @#$%^&+=!");
	            	progressBar.setForeground(Color.GREEN);
	            }else {
	            	security+=25;
	            	progressBar.setValue(75);
	            }
            }
        });
		textPassword.setColumns(10);
		textPassword.setBounds(35, 202, 172, 19);
		panel.add(textPassword);
		
		textConfirmPassword = new JPasswordField();
		textConfirmPassword.setColumns(10);
		textConfirmPassword.setBounds(35, 284, 172, 19);
		panel.add(textConfirmPassword);
		
		cbAge = new JComboBox<>();
		cbAge.setBounds(35, 85, 172, 27);
		for(int i = 18; i<=65; i++) {
			cbAge.addItem(i);
		}
		((JLabel)cbAge.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(cbAge);
		
		lblSave = new JLabel(new ImageIcon("images/icons/green_tick.png"));
		lblSave.setBounds(45, 328, 45, 45);
		lblSave.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel.add(lblSave);
		lblSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				name = textName.getText();
				age = (int) cbAge.getSelectedItem();
				username = textUsername.getText();
				char[] pass = textPassword.getPassword();
				char[] confirmP = textConfirmPassword.getPassword();
				
				password = String.valueOf(pass);
				confirmPassword = String.valueOf(confirmP);
				
				regex = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,20}$";
				
				if(name.isEmpty() || age == 0 || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
					JOptionPane.showMessageDialog(null, "You need to complete all the fields!", "Error", JOptionPane.WARNING_MESSAGE);
					
				}else if(!password.equals(confirmPassword)) {
						JOptionPane.showMessageDialog(null, "Both passwords didn't match!", "Error", JOptionPane.WARNING_MESSAGE);
				}else {
				
				User user = new User(name, age, username, password);
				
				try {
					
					if(!password.matches(regex)) {
						System.out.println("Incorrect Password");
						
						if (password.length() < 8 || password.length() > 20) {
							System.out.println("The password must contain between 8 and 20 characters");
							security+=25;
			            }else {
			            	security-=25;
			            }
			            
			            if (!password.matches(".*[a-zA-Z].*")) {
			            	System.out.println("The password must contain a letter");
			            	security+=25;
			            }
			            
			            if (!password.matches(".*\\d.*")) {
			            	System.out.println("The password must contain a number");
			            	security-=25;
			            }
			            
			            if (!password.matches(".*[@#$%^&+=!].*")) {
			            	System.out.println("The password must contain a special caracter like this: @#$%^&+=!");
			            	security-=25;
			            }
					}else {
						System.out.println("Correct Password!");
						JOptionPane.showMessageDialog(null, "Your User have been created succesfully!", "Saving...", JOptionPane.INFORMATION_MESSAGE);
						dispose();
						new Login();
						UserService.save(Conexion.obtain(), user);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				}
			}
		});
		
		lblCancel = new JLabel(new ImageIcon("images/icons/red_x.png"));
		lblCancel.setBounds(152, 328, 45, 45);
		lblCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel.add(lblCancel);
		lblCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "You have cancelled the registration", "Cancelling...", JOptionPane.ERROR_MESSAGE);
				dispose();
				new Login();
			}
		});		
		
		setVisible(true);
	}
	
}
