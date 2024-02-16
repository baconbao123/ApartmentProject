package formUser;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;

public class SettingUser extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblNewLabel;

	/**
	 * Create the panel.
	 */
	public SettingUser() {
		setBounds(0, 0, 1100, 800);
		
		lblNewLabel = new JLabel("Setting");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(503)
					.addComponent(lblNewLabel)
					.addContainerGap(551, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(32)
					.addComponent(lblNewLabel)
					.addContainerGap(754, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
}
