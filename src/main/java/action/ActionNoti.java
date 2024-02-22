package action;

import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;

import event.EventTableNotiAction;
import event.EventTableRenterAction;
import java.awt.Color;
import java.awt.Cursor;

public class ActionNoti extends JPanel {

	private static final long serialVersionUID = 1L;
	private ActionButton btnEdit;
	private ActionButton btnView;

	/**
	 * Create the panel.
	 */
	public ActionNoti() {
		setBorder(null);
		setBackground(new Color(255, 255, 255));
		setBounds(0, 0, 80, 40);
		btnEdit = new ActionButton();
		btnEdit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEdit.setIcon(new ImageIcon(ActionNoti.class.getResource("/icon/trash_1.png")));
		btnEdit.setFocusable(false);

		btnView = new ActionButton();
		btnView.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnView.setIcon(new ImageIcon(ActionNoti.class.getResource("/icon/show.png")));
		btnView.setFocusable(false);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(btnView, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(btnEdit, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(36, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnEdit, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
						.addComponent(btnView, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)));
		setLayout(groupLayout);

	}


	public void initEvent(EventTableNotiAction event, int row) {
		btnEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				event.edit(row);

			}
		});

		btnView.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				event.view(row);

			}
		});

	}
}
