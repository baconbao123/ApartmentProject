package view;

import javax.swing.JPanel;

import event.EventShowFeeUser;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ActionFeesUser extends JPanel {

	private static final long serialVersionUID = 1L;
	private static JButton View;
	/**
	 * Create the panel.
	 */
	public ActionFeesUser() {
		
		View = new JButton("View");
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addComponent(View, GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
					.addGap(200))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(View)
					.addContainerGap(26, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
	public void initEvent(EventShowFeeUser event, int row) {
		View.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				event.view(row);
			}
		});
	}
}
