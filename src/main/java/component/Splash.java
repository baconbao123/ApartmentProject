package component;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JProgressBar;
import javax.swing.Timer;
public class Splash extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblLogo;
	private JProgressBar progressBar;
	private Login login;
	 private static boolean isSplashShown = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				if(!isSplashShown) {
					try {
					Splash frame = new Splash();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.startLoading();
					isSplashShown = true;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Splash() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 660, 282);
		contentPane = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D)g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				GradientPaint gp = new GradientPaint(0, 0, Color.decode("#3de67a"), getWidth(), 0, Color.decode("#00c570"));
				g2.setPaint(gp);
				g2.fillRect(0, 0, getWidth(), getHeight());
				super.paintComponents(g);
			}
		};
		contentPane.setBorder(null);

		setContentPane(contentPane);
		
		lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(Splash.class.getResource("/icon/logo_1.png")));
		
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setBorder(null);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(108)
					.addComponent(lblLogo, GroupLayout.PREFERRED_SIZE, 445, Short.MAX_VALUE)
					.addGap(107))
				.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 660, GroupLayout.PREFERRED_SIZE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(50)
					.addComponent(lblLogo, GroupLayout.PREFERRED_SIZE, 182, Short.MAX_VALUE)
					.addGap(28)
					.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	protected void startLoading() {
		Timer timer = new Timer(30, new ActionListener() {
			int counter =  0;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				progressBar.setValue(counter);
				counter++;
				if (counter > 100) {
	                ((Timer) e.getSource()).stop();
	                showLoginScreen();
	            }
			}
		});
		timer.start();
	}
	
	private void showLoginScreen() {
		 if (isSplashShown) {
            login = new Login();
            login.setVisible(true);
            login.setLocationRelativeTo(null);
            login.setSplash(this);
            this.setVisible(false);
	     }
	}
	
	
}
