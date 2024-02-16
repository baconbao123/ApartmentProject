package formUser;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import menu.Menu;
import net.miginfocom.swing.MigLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;

public class MainForm extends JPanel {
	private Menu menu;
	private MigLayout layout;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	
	
	
	public MainForm() {
		setOpaque(true);
		setBackground(SystemColor.menu);
		setBounds(0, 0, 1100, 800);
		setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		
		lblNewLabel = new JLabel("Hello Admin");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(Color.PINK);
		lblNewLabel.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 36));
		
		lblNewLabel_1 = new JLabel("Hello Admin");
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 36));
		lblNewLabel_1.setBackground(Color.PINK);
		
		lblNewLabel_2 = new JLabel("Hello Admin");
		lblNewLabel_2.setOpaque(true);
		lblNewLabel_2.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 36));
		lblNewLabel_2.setBackground(Color.PINK);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(23)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 834, Short.MAX_VALUE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 378, GroupLayout.PREFERRED_SIZE))
					.addGap(38))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(351)
					.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
					.addGap(166))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(32)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 444, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		
	}
	
	public void showForm(Component form) {
		removeAll();
		add(form);
		repaint();
		revalidate();
	}
}
