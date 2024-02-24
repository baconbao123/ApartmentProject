package formAdView;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

import view.MenuImageAvatar;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import java.awt.Cursor;

public class ViewInfoRenter extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JLabel lblImgAvatar;
	private JLabel lblName;
	private JLabel lblGender;
	private JLabel lblPhone;
	private JLabel lblDateOfBirth;
	private JLabel lblAddress;
	private JLabel lblNationalIdCard;
	private JLabel lblIssue;
	private JLabel lblReadName;
	private JLabel lblImgCIC1;
	private JLabel lblImgCIC1_2;
	private JLabel lblImgCIC1_1;
	private JLabel lblImgCIC1_1_1;
	private JLabel lblReadGender;
	private JLabel lblReadPhone;
	private JLabel lblReadDOB;
	private JLabel lblReadAddress;
	private JLabel lblReadNIC;
	private JLabel lblReadCIss;
	private JLabel lblEmail;
	private JLabel ReadEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewInfoRenter frame = new ViewInfoRenter();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param nic 
	 * @param address 
	 * @param dob 
	 * @param phone 
	 * @param gender 
	 * @param fullName 
	 * @param avatar 
	 */
	public ViewInfoRenter(ImageIcon avatar, String fullName, String email, String gender, String phone, String dob, String address, String nic, String iAuthority, ImageIcon img1, ImageIcon img2) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 454, 639);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setFont(new Font("Arial", Font.PLAIN, 14));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		lblNewLabel = new JLabel("Renter Information");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));
		
		lblImgAvatar = new JLabel("");
		lblName = new JLabel("FullName");
		lblGender = new JLabel("Gender");
		lblPhone = new JLabel("Phone");
		lblDateOfBirth = new JLabel("Date Of Birth");
		lblAddress = new JLabel("Address");
		lblNationalIdCard = new JLabel("National ID Card");
		lblIssue = new JLabel("ID Card Issuer");
		lblReadName = new JLabel("Le Thi Phuong Anh");
		ReadEmail = new JLabel();
		lblImgCIC1 = new JLabel("");
		lblImgCIC1_1 = new JLabel("");
		lblReadGender = new JLabel("Female");
		lblReadPhone = new JLabel("098765432");
		lblReadDOB = new JLabel("2001/12/12");
		lblReadAddress = new JLabel("Hồ Chí Minh");
		lblReadNIC = new JLabel("098765432");
		lblReadCIss = new JLabel("Cục quản lý cư dân");
		lblImgCIC1_2 = new JLabel("");
		lblImgCIC1_1_1 = new JLabel("");
		lblImgCIC1_2.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblImgCIC1_1_1.setCursor(new Cursor(Cursor.HAND_CURSOR));

		
		lblImgAvatar.setIcon(avatar);
		lblReadName.setText(fullName);
		lblReadGender.setText(gender);
		lblReadPhone.setText(phone);
		lblReadDOB.setText(dob);	
		lblReadAddress.setText(address);
		lblReadNIC.setText(nic);
		lblReadCIss.setText(iAuthority);
		ReadEmail.setText(email);
		
		ImageIcon icon = new ImageIcon();
		
		lblImgCIC1_2.setIcon(new ImageIcon(
			img1.getImage().getScaledInstance(80, 70, Image.SCALE_SMOOTH)
		));
		lblImgCIC1_1_1.setIcon(new ImageIcon(
			img2.getImage().getScaledInstance(80, 70, Image.SCALE_SMOOTH)		
		));
		
		addMouseListenerImg(lblImgCIC1_2, img1);
		addMouseListenerImg(lblImgCIC1_1_1, img2);
		
		initComponent();
		
	}
	public ViewInfoRenter(){};
	
	private void addMouseListenerImg(JLabel label, ImageIcon img) {
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JDialog dialog = new JDialog();
				JLabel imgLabel = new JLabel(new ImageIcon(img.getImage().getScaledInstance(600, 600, Image.SCALE_SMOOTH)));
				dialog.getContentPane().add(imgLabel);
				dialog.pack();
				dialog.setVisible(true);
				dialog.setLocationRelativeTo(null);
				super.mouseClicked(e);
			}
		});
	}
	

	private void initComponent() {
//		lblImgAvatar.setOpaque(true);
//		lblImgAvatar.setBackground(Color.LIGHT_GRAY);
		
		lblName.setFont(new Font("Arial", Font.BOLD, 16));
		
		lblGender.setFont(new Font("Arial", Font.BOLD, 14));
		
		lblPhone.setFont(new Font("Arial", Font.BOLD, 14));
		
		lblDateOfBirth.setFont(new Font("Arial", Font.BOLD, 14));
		
		lblAddress.setFont(new Font("Arial", Font.BOLD, 14));
		
		lblNationalIdCard.setFont(new Font("Arial", Font.BOLD, 14));
		
		lblIssue.setFont(new Font("Arial", Font.BOLD, 14));
		
		lblReadName.setFont(new Font("Arial", Font.PLAIN, 15));
		
//		lblImgCIC1.setOpaque(true);
//		lblImgCIC1.setBackground(Color.LIGHT_GRAY);
//		
//		lblImgCIC1_1.setOpaque(true);
//		lblImgCIC1_1.setBackground(Color.LIGHT_GRAY);
		
		lblReadGender.setFont(new Font("Arial", Font.PLAIN, 15));
		
		lblReadPhone.setFont(new Font("Arial", Font.PLAIN, 15));
		
		lblReadDOB.setFont(new Font("Arial", Font.PLAIN, 15));
		
		lblReadAddress.setFont(new Font("Arial", Font.PLAIN, 15));
		
		lblReadNIC.setFont(new Font("Arial", Font.PLAIN, 15));
		
		lblReadCIss.setFont(new Font("Arial", Font.PLAIN, 15));
		
		lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Arial", Font.BOLD, 14));
		
		
		ReadEmail.setFont(new Font("Arial", Font.PLAIN, 15));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(35)
					.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
					.addGap(47)
					.addComponent(lblReadName, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(35)
					.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
					.addGap(47)
					.addComponent(ReadEmail, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(35)
					.addComponent(lblGender, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
					.addGap(47)
					.addComponent(lblReadGender, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(35)
					.addComponent(lblPhone, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
					.addGap(47)
					.addComponent(lblReadPhone, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(35)
					.addComponent(lblDateOfBirth, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
					.addGap(47)
					.addComponent(lblReadDOB, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(35)
					.addComponent(lblAddress, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
					.addGap(47)
					.addComponent(lblReadAddress, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(35)
					.addComponent(lblNationalIdCard, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
					.addGap(47)
					.addComponent(lblReadNIC, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(35)
					.addComponent(lblIssue, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
					.addGap(47)
					.addComponent(lblReadCIss, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(197)
					.addComponent(lblImgCIC1_2, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(lblImgCIC1_1_1, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(131)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(lblImgAvatar, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
							.addGap(130))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
							.addGap(120))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(6)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblImgAvatar, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
					.addGap(21)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblReadName, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(ReadEmail, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblGender, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblReadGender, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPhone, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblReadPhone, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDateOfBirth, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblReadDOB, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAddress, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblReadAddress, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNationalIdCard, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblReadNIC, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblIssue, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblReadCIss, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblImgCIC1_2, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblImgCIC1_1_1, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
