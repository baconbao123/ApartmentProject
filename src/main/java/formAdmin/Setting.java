package formAdmin;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.border.LineBorder;

import component.Login;
import dao.UserDao;
import entity.Users;
import view.ShowEditSetting;

import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;

public class Setting extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblNewLabel;
	private JPanel panel_2;
	private JPanel panel;
	private JLabel lblNameUser;
	private JLabel lblAdmin;
	private JLabel lblNewLabel_1;
	private JTextField textField;
	private JLabel lblFullName;
	private JTextField txtFullName;
	private JTextField txtAddress;
	private JLabel lblAddress;
	private JTextField txtNIC;
	private JLabel lblPhone;
	private JTextField txtPhone;
	private JLabel lblDob;
	private JTextField txtDob;
	private JLabel lblEmail;
	private JTextField txtEmail;
	private JLabel lblPwd;
	private JLabel lblGender;
	private JRadioButton rdbtnMale;
	private JRadioButton rdbtnFemale;
	private JButton btnEditInfo;
	private JPasswordField txtPwd;
	private JLabel lblIa;
	private JTextField txtiAu;
	private JLabel lblImgauthority;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnReloadData;
	private JLabel lblAvatar;
	private JLabel lblFrontSide;
	private JLabel lblBackSide;

	/**
	 * Create the panel.
	 */
	public Setting() {
		setBounds(0, 0, 1050, 800);
		{
			lblNewLabel = new JLabel("Setting");
			lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		}

		panel_2 = new JPanel();
		panel_2.setForeground(new Color(134, 134, 134));
		panel_2.setBackground(new Color(255, 255, 255));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout
				.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(56)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 973,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel))
								.addContainerGap(21, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(52).addComponent(lblNewLabel).addGap(53)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 641, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(30, Short.MAX_VALUE)));
		panel_2.setLayout(null);
		{
			panel = new JPanel() {
				@Override
				public void paintComponent(Graphics g) {
					Graphics2D g2 = (Graphics2D) g;
					g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					GradientPaint gp = new GradientPaint(0, 0, Color.decode("#F66E74"), getWidth(), 0,
							Color.decode("#FFA17D"));
					g2.setPaint(gp);
					g2.fillRect(0, 0, getWidth(), getHeight());
					super.paintComponents(g);
				}
			};
			panel.setBorder(null);
			panel.setBounds(0, 0, 370, 641);
			panel_2.add(panel);
			panel.setLayout(null);
			{
				lblNameUser = new JLabel("");
				lblNameUser.setForeground(new Color(255, 255, 255));
				lblNameUser.setHorizontalAlignment(SwingConstants.CENTER);
				lblNameUser.setVerticalAlignment(SwingConstants.BOTTOM);
				lblNameUser.setFont(new Font("Tahoma", Font.PLAIN, 24));
				lblNameUser.setBounds(73, 295, 242, 34);
				panel.add(lblNameUser);
			}
			{
				lblAdmin = new JLabel("ADMIN");
				lblAdmin.setForeground(new Color(255, 255, 255));
				lblAdmin.setVerticalAlignment(SwingConstants.BOTTOM);
				lblAdmin.setHorizontalAlignment(SwingConstants.CENTER);
				lblAdmin.setFont(new Font("Tahoma", Font.PLAIN, 24));
				lblAdmin.setBounds(73, 339, 242, 34);
				panel.add(lblAdmin);
			}
			{
				lblAvatar = new JLabel("");
				lblAvatar.setBackground(new Color(255, 255, 255));
				lblAvatar.setBounds(118, 124, 150, 150);
				panel.add(lblAvatar);
			}
		}
		{
			lblNewLabel_1 = new JLabel("Infomation");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 20));
			lblNewLabel_1.setBounds(404, 11, 110, 35);
			panel_2.add(lblNewLabel_1);
		}
		{
			textField = new JTextField();
			textField.setBackground(new Color(192, 192, 192));
			textField.setBounds(404, 46, 512, 2);
			panel_2.add(textField);
			textField.setColumns(10);
		}
		{
			lblFullName = new JLabel("FullName:");
			lblFullName.setForeground(new Color(128, 128, 128));
			lblFullName.setHorizontalAlignment(SwingConstants.LEFT);
			lblFullName.setFont(new Font("Arial", Font.PLAIN, 12));
			lblFullName.setBounds(404, 127, 74, 24);
			panel_2.add(lblFullName);
		}
		{
			txtFullName = new JTextField();
			txtFullName.setEnabled(false);
			txtFullName.setForeground(new Color(0, 0, 0));
			txtFullName.setFont(new Font("Arial", Font.PLAIN, 14));
			txtFullName.setBounds(533, 120, 383, 31);
			panel_2.add(txtFullName);
			txtFullName.setColumns(10);
		}
		{
			txtAddress = new JTextField();
			txtAddress.setFont(new Font("Arial", Font.PLAIN, 14));
			txtAddress.setEnabled(false);
			txtAddress.setColumns(10);
			txtAddress.setBounds(533, 162, 383, 31);
			panel_2.add(txtAddress);
		}
		{
			lblAddress = new JLabel("Address:");
			lblAddress.setForeground(new Color(128, 128, 128));
			lblAddress.setHorizontalAlignment(SwingConstants.LEFT);
			lblAddress.setFont(new Font("Arial", Font.PLAIN, 12));
			lblAddress.setBounds(404, 165, 74, 24);
			panel_2.add(lblAddress);
		}
		{
			JLabel lblNIC = new JLabel("NIC:");
			lblNIC.setForeground(new Color(128, 128, 128));
			lblNIC.setHorizontalAlignment(SwingConstants.LEFT);
			lblNIC.setFont(new Font("Arial", Font.PLAIN, 12));
			lblNIC.setBounds(404, 209, 74, 24);
			panel_2.add(lblNIC);
		}
		{
			txtNIC = new JTextField();
			txtNIC.setFont(new Font("Arial", Font.PLAIN, 14));
			txtNIC.setEnabled(false);
			txtNIC.setColumns(10);
			txtNIC.setBounds(533, 206, 383, 31);
			panel_2.add(txtNIC);
		}
		{
			lblPhone = new JLabel("Phone:");
			lblPhone.setForeground(new Color(128, 128, 128));
			lblPhone.setHorizontalAlignment(SwingConstants.LEFT);
			lblPhone.setFont(new Font("Arial", Font.PLAIN, 12));
			lblPhone.setBounds(404, 248, 74, 24);
			panel_2.add(lblPhone);
		}
		{
			txtPhone = new JTextField();
			txtPhone.setFont(new Font("Arial", Font.PLAIN, 14));
			txtPhone.setEnabled(false);
			txtPhone.setColumns(10);
			txtPhone.setBounds(533, 248, 383, 31);
			panel_2.add(txtPhone);
		}
		{
			lblDob = new JLabel("Dob:");
			lblDob.setForeground(new Color(128, 128, 128));
			lblDob.setHorizontalAlignment(SwingConstants.LEFT);
			lblDob.setFont(new Font("Arial", Font.PLAIN, 12));
			lblDob.setBounds(404, 290, 74, 24);
			panel_2.add(lblDob);
		}
		{
			txtDob = new JTextField();
			txtDob.setFont(new Font("Arial", Font.PLAIN, 14));
			txtDob.setEnabled(false);
			txtDob.setColumns(10);
			txtDob.setBounds(533, 290, 383, 31);
			panel_2.add(txtDob);
		}
		{
			lblEmail = new JLabel("Email:");
			lblEmail.setForeground(new Color(128, 128, 128));
			lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
			lblEmail.setFont(new Font("Arial", Font.PLAIN, 12));
			lblEmail.setBounds(404, 532, 74, 24);
			panel_2.add(lblEmail);
		}
		{
			txtEmail = new JTextField();
			txtEmail.setFont(new Font("Arial", Font.PLAIN, 14));
			txtEmail.setEnabled(false);
			txtEmail.setColumns(10);
			txtEmail.setBounds(533, 532, 383, 31);
			panel_2.add(txtEmail);
		}
		{
			lblPwd = new JLabel("Password:");
			lblPwd.setForeground(new Color(128, 128, 128));
			lblPwd.setHorizontalAlignment(SwingConstants.LEFT);
			lblPwd.setFont(new Font("Arial", Font.PLAIN, 12));
			lblPwd.setBounds(404, 574, 74, 24);
			panel_2.add(lblPwd);
		}
		{
			lblGender = new JLabel("Gender:");
			lblGender.setForeground(new Color(128, 128, 128));
			lblGender.setHorizontalAlignment(SwingConstants.LEFT);
			lblGender.setFont(new Font("Arial", Font.PLAIN, 12));
			lblGender.setBounds(404, 328, 74, 24);
			panel_2.add(lblGender);
		}
		{
			rdbtnMale = new JRadioButton("Male");
			buttonGroup.add(rdbtnMale);
			rdbtnMale.setEnabled(false);
			rdbtnMale.setBackground(new Color(255, 255, 255));
			rdbtnMale.setBounds(533, 333, 62, 23);
			panel_2.add(rdbtnMale);
		}
		{
			rdbtnFemale = new JRadioButton("Female");
			buttonGroup.add(rdbtnFemale);
			rdbtnFemale.setEnabled(false);
			rdbtnFemale.setBackground(Color.WHITE);
			rdbtnFemale.setBounds(610, 333, 86, 23);
			panel_2.add(rdbtnFemale);
		}
		{
			btnEditInfo = new JButton("Edit infor");
			btnEditInfo.setIcon(new ImageIcon(Setting.class.getResource("/icon/edit.png")));
			btnEditInfo.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btnEditInfo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnEditInfoActionPerformed(e);
				}
			});
			btnEditInfo.setForeground(new Color(255, 255, 255));

			btnEditInfo.setBackground(new Color(128, 0, 255));
			btnEditInfo.setBounds(806, 76, 110, 33);
			panel_2.add(btnEditInfo);
		}
		{
			txtPwd = new JPasswordField();
			txtPwd.setFont(new Font("Arial", Font.PLAIN, 14));
			txtPwd.setEnabled(false);
			txtPwd.setBounds(533, 574, 383, 31);
			panel_2.add(txtPwd);
		}
		{
			lblIa = new JLabel("iAuthority");
			lblIa.setHorizontalAlignment(SwingConstants.LEFT);
			lblIa.setForeground(Color.GRAY);
			lblIa.setFont(new Font("Arial", Font.PLAIN, 12));
			lblIa.setBounds(404, 363, 74, 24);
			panel_2.add(lblIa);
		}
		{
			txtiAu = new JTextField();
			txtiAu.setFont(new Font("Arial", Font.PLAIN, 14));
			txtiAu.setEnabled(false);
			txtiAu.setColumns(10);
			txtiAu.setBounds(533, 363, 383, 31);
			panel_2.add(txtiAu);
		}
		{
			lblImgauthority = new JLabel("imgAuthority:");
			lblImgauthority.setHorizontalAlignment(SwingConstants.LEFT);
			lblImgauthority.setForeground(Color.GRAY);
			lblImgauthority.setFont(new Font("Arial", Font.PLAIN, 12));
			lblImgauthority.setBounds(404, 434, 74, 24);
			panel_2.add(lblImgauthority);
		}
		{
			lblNewLabel_2 = new JLabel("Front side");
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_2.setBounds(579, 503, 74, 14);
			panel_2.add(lblNewLabel_2);
		}
		{
			lblNewLabel_3 = new JLabel("Back side");
			lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_3.setBounds(763, 503, 74, 14);
			panel_2.add(lblNewLabel_3);
		}
		{
			btnReloadData = new JButton("Reload data");
			btnReloadData.setIcon(new ImageIcon(Setting.class.getResource("/icon/reload.png")));
			btnReloadData.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnReloadDataActionPerformed(e);
				}
			});
			btnReloadData.setForeground(Color.WHITE);
			btnReloadData.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnReloadData.setBackground(new Color(255, 128, 0));
			btnReloadData.setBounds(647, 76, 125, 33);
			panel_2.add(btnReloadData);
		}
		{
			lblFrontSide = new JLabel("");
			lblFrontSide.setBounds(533, 407, 178, 92);
			panel_2.add(lblFrontSide);
		}
		{
			lblBackSide = new JLabel("");
			lblBackSide.setBounds(738, 407, 178, 92);
			panel_2.add(lblBackSide);
		}
		setLayout(groupLayout);
		// show info
		UserDao user = new UserDao();
		Integer userId = Login.getId();
		Users info = user.selUser(userId);

		if (info != null) {
			lblNameUser.setText(info.getName() != null ? info.getName().toUpperCase() : "null");
			txtFullName.setText(info.getName() != null ? info.getName() : "null");
			txtAddress.setText(info.getAddress() != null ? info.getAddress() : "null");
			txtNIC.setText(info.getNic() != null ? info.getNic() : "null");
			txtPhone.setText(info.getPhone() != null ? info.getPhone() : "null");
			txtiAu.setText(info.getiAuthority() != null ? info.getiAuthority() : "null");
			if (info.getDob() != null) {
				txtDob.setText(info.getDob().toString());
			} else {
				txtDob.setText("null");
			}
			// check gender
			String gender = info.getGender();
			if (gender != null) {
				if (gender.equals("female")) {
					rdbtnFemale.setSelected(true);
				} else {
					rdbtnMale.setSelected(true);
				}
			} else {
				rdbtnMale.setSelected(false);
				rdbtnFemale.setSelected(false);
			}

			txtEmail.setText(info.getEmail() != null ? info.getEmail() : "null");
			txtPwd.setText(info.getPw() != null ? info.getPw() : "null");
		} else {
			// Set all text fields to display "null"
			txtFullName.setText("null");
			txtAddress.setText("null");
			txtNIC.setText("null");
			txtPhone.setText("null");
			txtDob.setText("null");
			txtEmail.setText("null");
			txtPwd.setText("null");
			txtiAu.setText("null");
		}
		if (info.getAvatar() != null && info.getAvatar() != "") {
			ImageIcon originAvatarIcon = new ImageIcon(info.getAvatar());
			Image imgAvatar = originAvatarIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
			// Load the image
			ImageIcon imageIcon = new ImageIcon(imgAvatar);

			// Set the image icon on the label
			lblAvatar.setIcon(imageIcon);
		} else {
			ImageIcon originAvatarIcon = new ImageIcon("images/avatarDefaut.jpg");
			Image imgAvatar = originAvatarIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
			// Load the image
			ImageIcon imageIcon = new ImageIcon(imgAvatar);

			// Set the image icon on the label
			lblAvatar.setIcon(imageIcon);
		}
		// show imgIAuth
		String avatarPaths = info.getImgIAuthority();
		String[] avatarPathArray = avatarPaths.split(";");

		if (avatarPathArray.length >= 2) {
			String img1 = avatarPathArray[0];
			String img2 = avatarPathArray[1];

			ImageIcon icon1 = new ImageIcon(img2);
			ImageIcon icon2 = new ImageIcon(img1);

			Image scaledImage1 = icon1.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
			Image scaledImage2 = icon2.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);

			ImageIcon scaledIcon1 = new ImageIcon(scaledImage1);
			ImageIcon scaledIcon2 = new ImageIcon(scaledImage2);

			lblFrontSide.setIcon(scaledIcon1);
			lblBackSide.setIcon(scaledIcon2);
		}
	}

	protected void loadData() {
		UserDao user = new UserDao();
		Integer userId = Login.getId();
		Users info = user.selUser(userId);
		if (info != null) {

			lblNameUser.setText(info.getName() != null ? info.getName().toUpperCase() : "null");
			txtFullName.setText(info.getName() != null ? info.getName() : "null");
			txtAddress.setText(info.getAddress() != null ? info.getAddress() : "null");
			txtNIC.setText(info.getNic() != null ? info.getNic() : "null");
			txtPhone.setText(info.getPhone() != null ? info.getPhone() : "null");
			txtiAu.setText(info.getiAuthority() != null ? info.getiAuthority() : "null");
			txtEmail.setText(info.getEmail() != null ? info.getEmail() : "null");
			txtPwd.setText(info.getPw() != null ? info.getPw() : "null");
			if (info.getDob() != null) {
				txtDob.setText(info.getDob().toString());
			} else {
				txtDob.setText("null");
			}
			if (info.getGender().equals("female")) {
				rdbtnFemale.setSelected(true);
			} else {
				rdbtnMale.setSelected(true);
			}
			if (info.getAvatar() != null && info.getAvatar() != "") {
				ImageIcon originAvatarIcon = new ImageIcon(info.getAvatar());
				Image imgAvatar = originAvatarIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
				// Load the image
				ImageIcon imageIcon = new ImageIcon(imgAvatar);

				// Set the image icon on the label
				lblAvatar.setIcon(imageIcon);
			} else {
				ImageIcon originAvatarIcon = new ImageIcon("images/avatarDefaut.jpg");
				Image imgAvatar = originAvatarIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
				// Load the image
				ImageIcon imageIcon = new ImageIcon(imgAvatar);

				// Set the image icon on the label
				lblAvatar.setIcon(imageIcon);
			}
			// load imgIAuth
			String avatarPaths = info.getImgIAuthority();
			String[] avatarPathArray = avatarPaths.split(";");

			if (avatarPathArray.length >= 2) {
				String img1 = avatarPathArray[0];
				String img2 = avatarPathArray[1];

				ImageIcon icon1 = new ImageIcon(img2);
				ImageIcon icon2 = new ImageIcon(img1);

				Image scaledImage1 = icon1.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
				Image scaledImage2 = icon2.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);

				ImageIcon scaledIcon1 = new ImageIcon(scaledImage1);
				ImageIcon scaledIcon2 = new ImageIcon(scaledImage2);

				lblFrontSide.setIcon(scaledIcon1);
				lblBackSide.setIcon(scaledIcon2);
			}
		} else {
			// Set all text fields to display "null"
			txtFullName.setText("null");
			txtAddress.setText("null");
			txtNIC.setText("null");
			txtPhone.setText("null");
			txtDob.setText("null");
			txtEmail.setText("null");
			txtPwd.setText("null");
		}

	}

	protected void btnEditInfoActionPerformed(ActionEvent e) {
		ShowEditSetting JframeShowEditSetting = new ShowEditSetting();

	}

	protected void btnReloadDataActionPerformed(ActionEvent e) {
		try {
			loadData();
			JOptionPane.showMessageDialog(null, "Data loaded successfully!", "Success",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Data loaded faid!", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}
}
