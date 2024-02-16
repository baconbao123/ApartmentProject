package view;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import model.TableActionEvent;
import java.awt.event.ActionEvent;

public class ActionFees extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnEdit;
	private JButton btnPay;
	/**
	 * Create the panel.
	 */
	public ActionFees() {
		
		btnEdit = new JButton("Edit");
		
		
		btnPay = new JButton("Pay");
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnPay)
					.addGap(10)
					.addComponent(btnEdit)
					.addContainerGap(20, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnEdit)
						.addComponent(btnPay))
					.addContainerGap(13, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}


	public void initEvent(TableActionEvent event, int row) {
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				event.pay(row);
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				event.edit(row);
			}
		});
		
	}



}
