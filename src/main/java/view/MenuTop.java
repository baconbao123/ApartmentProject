package view;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;

public class MenuTop extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblName;
	private Button cmdMenu;

	/**
	 * Create the panel.
	 */
	public MenuTop() {
		setBorder(null);
		setBackground(Color.WHITE);
		
//		lblName = new JLabel("<html>Apartment<br>Management</html>");
		lblName = new JLabel("APARTMENT");
		lblName.setForeground(new Color(50, 199, 135));
		lblName.setFont(new Font("Calibri", Font.BOLD, 24));
		
		cmdMenu = new Button();
		cmdMenu.setIcon(new ImageIcon(MenuTop.class.getResource("/icon/logo.png")));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(8)
					.addComponent(cmdMenu, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblName)
					.addContainerGap(25, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(20, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(cmdMenu, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addComponent(lblName, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)))
		);
		setLayout(groupLayout);
		setOpaque(false);
	}
	
	public void addMenuEvent(ActionListener event) {
		cmdMenu.addActionListener(event);
	}
}
