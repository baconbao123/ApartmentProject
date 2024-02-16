package action;

import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;

import event.EventTableRenterAction;
import java.awt.Color;

public class ActionRenter extends JPanel {

	private static final long serialVersionUID = 1L;
	private ActionButton btnEdit;
	private ActionButton btnView;

	/**
	 * Create the panel.
	 */
	public ActionRenter() {
		setBorder(null);
		setBackground(new Color(255, 255, 255));
		setBounds(0, 0, 80, 40);
		btnEdit = new ActionButton();
		btnEdit.setIcon(new ImageIcon(ActionRenter.class.getResource("/icon/edit-button.png")));
		btnEdit.setFocusable(false);
		
		btnView = new ActionButton();
		btnView.setIcon(new ImageIcon(ActionRenter.class.getResource("/icon/360-view.png")));
		btnView.setFocusable(false);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnView, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnEdit, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(36, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
					.addComponent(btnEdit, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
					.addComponent(btnView, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
	
	public void initEvent(EventTableRenterAction event, int row) {
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
