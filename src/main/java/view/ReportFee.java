package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import dao.FeesDao;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ReportFee extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JTable table;
	private int id;
	private JLabel note;
	private JLabel Rooms;
	private JLabel Time ;
	private JLabel lblBill;
	/**
	 * Create the panel.
	 */
	public ReportFee(int id ) {
		this.id = id;
		JLabel reportTitle = new JLabel("Report");
		reportTitle.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		JButton print = new JButton("Print");
		print.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrinterJob pj = PrinterJob.getPrinterJob();
				 pj.setJobName(" Print Component ");
				 pj.setPrintable ((Printable) new Printable() {    
					    public int print(Graphics pg, PageFormat pf, int pageNum){
					      if (pageNum > 0){
					      return Printable.NO_SUCH_PAGE;
					      }

					      Graphics2D g2 = (Graphics2D) pg;
					      g2.translate(pf.getImageableX(), pf.getImageableY());
					      panel.paint(g2);
					      return Printable.PAGE_EXISTS;
					    }
					  });
					  if (pj.printDialog() == false)
					  return;

					  try {
					        pj.print();
					  } catch (PrinterException ex) {
					        // handle exception
					  }
				
			}
		});
		
		 panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(38)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 716, GroupLayout.PREFERRED_SIZE)
								.addComponent(print, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(339)
							.addComponent(reportTitle)))
					.addContainerGap(46, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(15)
					.addComponent(reportTitle)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(print)
					.addGap(18)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(24, Short.MAX_VALUE))
		);
		
		 Time = new JLabel("Time");
		
		JScrollPane scrollPane = new JScrollPane();
		
		 note = new JLabel("NOTE:");
		
		 Rooms = new JLabel("Room");
		
		lblBill = new JLabel("Bill");
		lblBill.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
								.addComponent(note)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(Rooms)
									.addGap(76)
									.addComponent(Time))))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(347)
							.addComponent(lblBill)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblBill, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(Rooms)
						.addComponent(Time))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
					.addGap(19)
					.addComponent(note)
					.addContainerGap())
		);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);
		init();
	}
	public void init() {
		var model = new DefaultTableModel();
		var dao = new FeesDao();
		var fee = dao.selFees(id);
		model.addColumn("Fees");
		model.addColumn("Money");
		model.addRow(new Object[] {"Room  ",fee.getRent()});
		model.addRow(new Object[] {"Electric  ",fee.getElectric()});
		model.addRow(new Object[] {"Water  ",fee.getWater()});
		model.addRow(new Object[] {"Other  ",fee.getOther()});
		model.addRow(new Object[] {"Total ",fee.getTotal()});
		table.setModel(model);
		note.setText("NOTE :"+(fee.getNote()==null?"":fee.getNote()));
		Rooms.setText("Room :"+fee.getRoom().toString());
		Time.setText("Time "+ fee.getTime());
	}
}
