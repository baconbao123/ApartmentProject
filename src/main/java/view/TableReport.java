package view;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.awt.event.ActionEvent;

public class TableReport extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tableReport;

	/**
	 * Create the panel.
	 */
	public TableReport(JTable tableParent) {
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					MessageFormat header = new MessageFormat("REPORT");
					MessageFormat footer = new MessageFormat("Page {0, number, integer}");
					try {
						tableReport.print(JTable.PrintMode.FIT_WIDTH, header, footer);
					} catch (Exception e2) {
						// TODO: handle exception
						e2.printStackTrace();
					}
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnPrint, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 951, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(26, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(19, Short.MAX_VALUE)
					.addComponent(btnPrint)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 398, GroupLayout.PREFERRED_SIZE)
					.addGap(22))
		);
		
		tableReport = tableParent;
		scrollPane.setViewportView(tableReport);
		setLayout(groupLayout);

	}
}
