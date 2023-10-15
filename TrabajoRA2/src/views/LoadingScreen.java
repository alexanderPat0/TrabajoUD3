package views;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class LoadingScreen extends JFrame {

	private JPanel contentPane;
	private Timer timer;
	private int progress = 0;

	public LoadingScreen() {
		setTitle("Loading...");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		JFrameIcon();
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImageIcon imgIcon = new ImageIcon("images/MercadonaLogo.png");
		Image image = imgIcon.getImage().getScaledInstance(193, 145, Image.SCALE_SMOOTH);
		ImageIcon imgLogo = new ImageIcon(image);
		
		JLabel lblLogo = new JLabel(imgLogo);
		lblLogo.setBounds(120, 11, 193, 145);
		contentPane.add(lblLogo);
		
		JProgressBar progressBar = new JProgressBar( 0, 100 );
		progressBar.setBounds(39, 167, 362, 21);
		contentPane.add(progressBar);
		
		progressBar.setStringPainted(true);
        getContentPane().add(progressBar, BorderLayout.CENTER);

        timer = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	progress += 5;
                progressBar.setValue(progress);
                if (progress == 100) {
                    timer.stop();
                    dispose();
                    new Login();
                }
            }
        });

        timer.start();
    	
		setVisible(true);
	}
	private void JFrameIcon() {
		Image icon = new ImageIcon("images/MercadonaLogo.png").getImage().getScaledInstance(80, 60, Image.SCALE_SMOOTH);
		setIconImage(icon);
	}
}
