package component;

import java.awt.EventQueue;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.border.SoftBevelBorder;

import dao.UserDao;
import main.AdminMain;
import main.UserMain;
import view.ShowForgetPwd;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelPic;
	private JLabel lblLogoutLogin;
	private JLabel lblImageLogin;
	private JLabel lblLable;
	private JLabel lblLogo;
	private JLabel lblSign;
	private JLabel lblEnterYourEmail;
	private JLabel lblUsername;
	private JTextField txtUser;
	private JLabel lblPassword;
	private JButton btnSignIn;
	private Splash splash;
	private AdminMain adMain;
	private UserMain useMain;
	private JPasswordField txtPass;
	private JLabel lblStatusNoti;
	private JLabel lblStatusLoad;
	private JLabel lblStatus;
	private  static Integer id;
	private JLabel lblNewLabel;
	

	

	public final static Integer getId() {
		return id;
	}

	public  final static void setId(Integer id) {
		Login.id = id;
	}

	public Splash getSplash() {
		return splash;
	}

	public void setSplash(Splash splash) {
		this.splash = splash;
	}
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 700);
		contentPane = new JPanel();
		contentPane.setBorder(null);

		setContentPane(contentPane);
		
		panelPic = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D)g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				GradientPaint gp = new GradientPaint(0, 0, Color.decode("#3de67a"), getWidth(), 0, Color.decode("#00c570"));
				g2.setPaint(gp);
				g2.fillRect(0, 0, getWidth(), getHeight());
				super.paintComponent(g);
			}
		};
		panelPic.setBorder(null);
		panelPic.setOpaque(false);
		
		lblLogoutLogin = new JLabel("");
		lblLogoutLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblLogoutLoginMouseClicked(e);
			}
		});
		lblLogoutLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblLogoutLogin.setIcon(new ImageIcon(Login.class.getResource("/icon/logout.png")));
		
		lblLable = new JLabel("Apartment Management");
		lblLable.setForeground(new Color(39, 179, 170));
		lblLable.setFont(new Font("Arial Black", Font.PLAIN, 16));
		
		lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(Login.class.getResource("/icon/logo_2.png")));
		
		lblSign = new JLabel("Sign In");
		lblSign.setForeground(new Color(0, 0, 0));
		lblSign.setFont(new Font("Arial", Font.BOLD, 16));
		
		lblEnterYourEmail = new JLabel("Enter your email and password for signing in");
		lblEnterYourEmail.setForeground(Color.BLACK);
		lblEnterYourEmail.setFont(new Font("Arial", Font.PLAIN, 12));
		
		lblUsername = new JLabel("Username");
		lblUsername.setForeground(Color.BLACK);
		lblUsername.setFont(new Font("Arial", Font.BOLD, 13));
		
		txtUser = new JTextField();
		txtUser.setFont(new Font("Arial", Font.PLAIN, 12));
		txtUser.setBorder(new EmptyBorder(1, 2, 1, 1));
		txtUser.setColumns(10);
		
		lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.BLACK);
		lblPassword.setFont(new Font("Arial", Font.BOLD, 13));
		
		btnSignIn = new JButton("Sign In");
		btnSignIn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSignInActionPerformed(e);
			}
		});
		btnSignIn.setForeground(Color.WHITE);
		btnSignIn.setFont(new Font("Arial", Font.BOLD, 12));
		btnSignIn.setBackground(new Color(42, 86, 159));
		btnSignIn.setFocusable(false);
		btnSignIn.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(42, 86, 159), new Color(42, 86, 159), new Color(42, 86, 159), new Color(42, 86, 159)));
		
		txtPass = new JPasswordField();
		txtPass.setBorder(new EmptyBorder(1, 2, 1, 1));
		
		lblStatusNoti = new JLabel("");
		lblStatusNoti.setForeground(Color.RED);
		lblStatusNoti.setFont(new Font("Arial", Font.BOLD, 12));
		
		lblStatusLoad = new JLabel("");
		lblStatusLoad.setForeground(Color.RED);
		lblStatusLoad.setFont(new Font("Arial", Font.BOLD, 15));
		
		lblStatus = new JLabel("Status:");
		lblStatus.setFont(new Font("Arial", Font.BOLD, 16));
		lblStatus.setForeground(new Color(81, 162, 162));
		lblNewLabel = new JLabel("Forgot Password ?");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblNewLabelMouseClicked(e);
			}
		});
		
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panelPic, GroupLayout.PREFERRED_SIZE, 501, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 445, Short.MAX_VALUE)
							.addComponent(lblLogoutLogin, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(95)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblLable, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(lblLogo, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE))
									.addComponent(lblSign, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblEnterYourEmail, GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
									.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE)
									.addComponent(txtUser, GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
									.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE))
								.addComponent(txtPass, GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
								.addComponent(btnSignIn, GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
								.addComponent(lblStatusNoti, GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
									.addComponent(lblStatus, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblStatusLoad, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)))
							.addGap(106))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panelPic, GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(51)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblLogo, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
							.addGap(50))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblLable, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addGap(42)))
					.addGap(14)
					.addComponent(lblSign, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblEnterYourEmail, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtUser, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtPass, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addGap(9)
					.addComponent(lblStatusNoti, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblStatusLoad, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addComponent(lblStatus, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)))
					.addGap(32)
					.addComponent(btnSignIn, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
					.addComponent(lblLogoutLogin, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
		);
		
		lblImageLogin = new JLabel("");
		lblImageLogin.setIcon(new ImageIcon(Login.class.getResource("/icon/logo_login.png")));
		GroupLayout gl_panelPic = new GroupLayout(panelPic);
		gl_panelPic.setHorizontalGroup(
			gl_panelPic.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelPic.createSequentialGroup()
					.addGap(80)
					.addComponent(lblImageLogin, GroupLayout.PREFERRED_SIZE, 340, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(81, Short.MAX_VALUE))
		);
		gl_panelPic.setVerticalGroup(
			gl_panelPic.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelPic.createSequentialGroup()
					.addGap(116)
					.addComponent(lblImageLogin, GroupLayout.PREFERRED_SIZE, 468, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(116, Short.MAX_VALUE))
		);
		panelPic.setLayout(gl_panelPic);
		contentPane.setLayout(gl_contentPane);
	}
	
	protected void lblLogoutLoginMouseClicked(MouseEvent e) {
		System.exit(0);
	}
	
	protected void btnSignInActionPerformed(ActionEvent e) {
		var dao = new UserDao();
		
		String pass = String.valueOf(txtPass.getPassword());
		String userName =  txtUser.getText();
		var rs =  dao.login(userName, pass);
		if(!rs.isEmpty()) {
			lblStatusLoad.setText("Success");
			showDashboardScreen( (boolean) rs.get(1));
//			id = Integer.parseInt( rs.get(0).toString()) ;
			System.out.println(rs.toString());
		} else {
			lblStatusLoad.setText("Fail");
			lblStatusNoti.setText("Username or Password is incorrect");
		}
	}
	
	private void showDashboardScreen(boolean isAmin) {
		if(isAmin) {
			adMain = new AdminMain();
			adMain.setVisible(true);
			adMain.setLogin(this);
			adMain.setLocationRelativeTo(null);
			adMain.initMoving(adMain);
			this.setVisible(false);
		} else {
			useMain = new UserMain();
			useMain.setVisible(true);
			useMain.setLogin(this);
			useMain.setLocationRelativeTo(null);
			useMain.getAdMain().initMoving(useMain);
			this.setVisible(false);
		}
		
	}

	protected void lblNewLabelMouseClicked(MouseEvent e) {
		ShowForgetPwd JframeForgetPwd = new ShowForgetPwd();
		JframeForgetPwd.setVisible(true);
	}
}
