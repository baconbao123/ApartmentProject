package view;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingUtilities;

import dao.FeesDao;
import event.EventLoadTable;

import java.awt.event.ActionListener;
import java.awt.Window;
import java.awt.event.ActionEvent;

public class ShowPayment extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnNewButton;
	private JButton btnHadFinished;
	/**
	 * Create the panel.
	 */
	public ShowPayment(int id, EventLoadTable  event) {
		
		btnNewButton = new JButton("Haven't finished");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var dao = new FeesDao();
				var rs = dao.changeStatus(id, false);
				if(rs>0) {
					JOptionPane.showMessageDialog(null, "Update status successfully");
					 Window window = SwingUtilities.getWindowAncestor(ShowPayment.this);
					 event.loadDataTable();
					 window.dispose();
				}
				else {
					 JOptionPane.showMessageDialog(null, "Some thing went wrong", "Error", JOptionPane.ERROR_MESSAGE);
			    }
			}
		});
		
		btnHadFinished = new JButton("Had finished");
		btnHadFinished.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var dao = new FeesDao();
				var rs = dao.changeStatus(id, true);
				if(rs>0) {
					JOptionPane.showMessageDialog(null, "Update status successfully");
					 Window window = SwingUtilities.getWindowAncestor(ShowPayment.this);
					 event.loadDataTable();
					 window.dispose();
				}
				else {
					 JOptionPane.showMessageDialog(null, "Some thing went wrong", "Error", JOptionPane.ERROR_MESSAGE);
			    }
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewButton)
					.addPreferredGap(ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
					.addComponent(btnHadFinished, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
					.addGap(18))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
						.addComponent(btnHadFinished, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		setLayout(groupLayout);

	}

}
