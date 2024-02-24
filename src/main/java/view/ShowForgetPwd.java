package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.UserDao;
import entity.Users;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.Font;
import javax.swing.JButton;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;

public class ShowForgetPwd extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtEmail;
	private JLabel lblNewLabel;
	private JButton btnNewButton;
	private JLabel lblLoading;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowForgetPwd frame = new ShowForgetPwd();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ShowForgetPwd() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(300, 200, 450, 259);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setFont(new Font("Tahoma", Font.PLAIN, 12));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		{
			txtEmail = new JTextField();
			txtEmail.setBounds(119, 49, 256, 30);
			contentPane.add(txtEmail);
			txtEmail.setColumns(10);
		}
		{
			lblNewLabel = new JLabel("Email:");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel.setBounds(36, 57, 73, 14);
			contentPane.add(lblNewLabel);
		}
		{
			btnNewButton = new JButton("Send Mail");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnNewButtonActionPerformed(e);
				}
			});
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnNewButton
					.setIcon(new ImageIcon(ShowForgetPwd.class.getResource("/icon/material-symbols_send-outline.png")));
			btnNewButton.setBounds(170, 107, 125, 30);
			contentPane.add(btnNewButton);
		}
		{
			lblLoading = new JLabel("Sending email, please wait...");
			lblLoading.setVisible(false);
			lblLoading.setForeground(new Color(60, 179, 113));
			lblLoading.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblLoading.setBounds(137, 153, 208, 24);
			contentPane.add(lblLoading);
		}
	}

	protected void btnNewButtonActionPerformed(ActionEvent e) {
		final String fromEmail = "nguyenphilongls2k4@gmail.com";
		final String pwd = "awlt jdml ztin xuel";
		Users info = new Users();
		UserDao list = new UserDao();
		var prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
		
		Session session = Session.getInstance(prop, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, pwd);

			}
		});

		List<Users> userList = list.selAllUser();
		boolean emailFound = false;


		for (Users user : userList) {
			if (user.getEmail().equals(txtEmail.getText())) {
				emailFound = true;
				info = user; 
				break;
			}
		}
		try {
			if (emailFound) {

				
				Message mes = new MimeMessage(session);
				mes.setFrom(new InternetAddress(fromEmail));
				mes.setRecipients(Message.RecipientType.TO, InternetAddress.parse(txtEmail.getText()));
				mes.setSubject("Dear " + info.getName()+" , the apartment management system sends the login password");
				String html = "<div>\r\n"
						+ "    <p>Password: <strong>" + info.getPw() + "</strong>\r\n"
						+ "      <br/> <br/> <br/> "
						+ "      Please, Do not share your password with others\r\n"
						+ "      <br/><br/><br/>"
						+ "      Please do not reply to email\r\n"
						+ "    </p>\r\n"
						+ "  </div>";
				mes.setContent(html, "text/html; chartset=UTF-8");
				Transport.send(mes);
				
				JOptionPane.showMessageDialog(null, "Send mail success", "Success", JOptionPane.INFORMATION_MESSAGE);
				JFrame frame = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				frame.dispose();
			} else {
				JOptionPane.showMessageDialog(null, "Email not exist!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e2) {
			// TODO: handle exception
			e2.printStackTrace();
		} 

	}
}
