package view;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingUtilities;

import component.Login;
import main.AdminMain;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;

public class MenuBottom extends JPanel {

	private static final long serialVersionUID = 1L;
	private MenuImageAvatar menuImageAvatar;
	private JLabel LblName;
	private JLabel LblStatus;
	private JLabel lblIcon;
	
	

	public JLabel getLblIcon() {
		return lblIcon;
	}

	/**
	 * Create the panel.
	 */
	public MenuBottom() {
		setOpaque(false);
		setBounds(0, 0, 230, 65);
		
		
		
		menuImageAvatar = new MenuImageAvatar();
		menuImageAvatar.setIcon(new ImageIcon(MenuBottom.class.getResource("/icon/avatar7.png")));
		
		LblName = new JLabel("Baconbao");
		LblName.setForeground(SystemColor.controlShadow);
		LblName.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 15));
		
		LblStatus = new JLabel("Admin");
		LblStatus.setForeground(SystemColor.controlShadow);
		LblStatus.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		
		lblIcon = new JLabel("");
		lblIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblIcon.setIcon(new ImageIcon(MenuBottom.class.getResource("/icon/logout_1.png")));
		lblIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showLoginScreen();
			}

		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(2)
					.addComponent(menuImageAvatar, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(LblName)
						.addComponent(LblStatus, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
					.addComponent(lblIcon)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(1)
					.addComponent(menuImageAvatar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(5))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblIcon, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(LblName)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(LblStatus, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		setLayout(groupLayout);
		
		
	}

	private void showLoginScreen() {
		AdminMain main = (AdminMain) SwingUtilities.getWindowAncestor(this);
	    Login login = new Login();
	    login.setVisible(true);
	    login.setLocationRelativeTo(main);
	    main.dispose();
	}

	
	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(SystemColor.controlShadow);
	    g.drawLine(0, 0, getWidth(), 0);
		super.paintComponent(g);
	}
}
