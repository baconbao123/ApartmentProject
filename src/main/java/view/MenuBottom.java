package view;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingUtilities;

import component.Login;
import dao.UserDao;
import entity.Users;
import main.AdminMain;
import main.UserMain;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;

public class MenuBottom extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel LblName;
	private JLabel LblStatus;
	private JLabel lblIcon;
	private JLabel lblAvatar;
	private JPanel panel;
	
	
	private int id = Login.getId();

	public JLabel getLblIcon() {
		return lblIcon;
	}

	/**
	 * Create the panel.
	 */
	public MenuBottom() {
		setOpaque(false);
		setBounds(0, 0, 230, 65);
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 54, Short.MAX_VALUE))
		);
		panel.setLayout(null);
		
		LblName = new JLabel("Baconbao");
		LblName.setBounds(77, 5, 72, 19);
		panel.add(LblName);
		LblName.setForeground(SystemColor.controlShadow);
		LblName.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		LblStatus = new JLabel("Admin");
		LblStatus.setBounds(77, 27, 46, 19);
		panel.add(LblStatus);
		LblStatus.setForeground(SystemColor.controlShadow);
		LblStatus.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		
		lblIcon = new JLabel("");
		lblIcon.setBounds(182, 0, 24, 46);
		panel.add(lblIcon);
		lblIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblIcon.setIcon(new ImageIcon(MenuBottom.class.getResource("/icon/logout_1.png")));
		lblAvatar = new JLabel("");
		lblAvatar.setBounds(10, 5, 46, 41);
		panel.add(lblAvatar);
		lblIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(id == 2) {
					showLoginScreen();
				} else {
					showLoginUser();
				}
				
				
			}

		});
		setLayout(groupLayout);
		
		UserDao user = new UserDao();
		Integer userId = Login.getId();
		Users info = user.selUser(userId);
		if (info != null) {

			LblName.setText(info.getEmail() != null ? info.getEmail() : "");
			
			if (info.getAvatar() == null || info.getAvatar().isEmpty()) {
				ImageIcon originAvatarIcon = new ImageIcon("images/avatarDefaut.jpg");
				Image imgAvatar = originAvatarIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
				// Load the image
				ImageIcon imageIcon = new ImageIcon(imgAvatar);

				// Set the image icon on the label
				lblAvatar.setIcon(imageIcon);
			} else {
				ImageIcon originAvatarIcon = new ImageIcon(info.getAvatar());
				Image imgAvatar = originAvatarIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
				// Load the image
				ImageIcon imageIcon = new ImageIcon(imgAvatar);

				// Set the image icon on the label
				lblAvatar.setIcon(imageIcon);
			}
			
		} 
		
	}

	private void showLoginScreen() {
		AdminMain main = (AdminMain) SwingUtilities.getWindowAncestor(this);
	    Login login = new Login();
	    login.setVisible(true);
	    login.setLocationRelativeTo(main);
	    main.dispose();
	}
	
	private void showLoginUser() {
		UserMain main = (UserMain) SwingUtilities.getWindowAncestor(this);
		Login login = new Login();
		login.setVisible(true);
		login.setLocationRelativeTo(null);
		main.dispose();
	}

	
	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(SystemColor.controlShadow);
	    g.drawLine(0, 0, getWidth(), 0);
		super.paintComponent(g);
	}
}
