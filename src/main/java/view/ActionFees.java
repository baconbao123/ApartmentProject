package view;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

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
	private JButton report;
	private Boolean status;
	/**
	 * Create the panel.
	 */
	public ActionFees(Boolean status) {
		
		btnEdit = new JButton("Edit");
		
		
		btnPay = new JButton("Pay");
		
		report = new JButton("Report");
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnPay)
					.addGap(10)
					.addComponent(btnEdit)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(report)
					.addContainerGap(229, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnEdit)
						.addComponent(btnPay)
						.addComponent(report))
					.addContainerGap(266, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		if(status) {
//			JOptionPane.showMessageDialog(null, status);
			btnPay.setEnabled(!status);
			btnEdit.setEnabled(!status);
		}

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
		report.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				event.report(row);
			}
		});
		
		
	}



}
