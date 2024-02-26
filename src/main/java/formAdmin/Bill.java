package formAdmin;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.print.attribute.standard.Chromaticity;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import dao.FeesDao;
import dao.RoomsDao;
import event.EventLoadTable;
import model.TableActionEvent;
import model.tableBillEditor;
import model.tableBillRender;
import view.History;
import view.ReportFee;
import view.ShowEditFees;
import view.ShowPayment;
import view.TableReport;

import java.awt.event.InputMethodListener;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.text.MessageFormat;
import java.awt.event.InputMethodEvent;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

import com.toedter.calendar.JDateChooser;



import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class Bill extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblTitle;
	private static JLabel lblDisplay;
	private static JTable tableBill;
	private static JTextField inputPage;
	private static Integer pageNumber = 1;
	private static Integer rowOfPage = 20;
	private static Double totalOfPage = 0.0;
	private static Integer totalOfRow = 0;
	private JScrollPane scrollTable;
	private static JButton button = new JButton("EDIT");
	private static JTextField inputRoom;
	private static TableActionEvent event;
	private static Boolean filter = false;
	private static JComboBox selectStatus;
	private static JDateChooser to;
	private static JDateChooser from;
	private static GroupLayout groupLayout;
	private static JTable tableReport = new JTable();
	private static JTextField inputId;
	private JButton btnFilter;
	/*
	 * Create the panel. 
	 */
	public Bill() {
		setBackground(new Color(240, 240, 240));
		setForeground(new Color(255, 255, 255));
		setBorder(null);
		setBounds(0, 0, 1100, 800);

		lblTitle = new JLabel("Payment Management");
		lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
		
		JButton btnHistory = new JButton("History");
		btnHistory.setForeground(new Color(255, 255, 255));
		btnHistory.setBorder(null);
		btnHistory.setBackground(new Color(15, 160, 206));
		btnHistory.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var dao = new FeesDao();
				var history = new History(dao.getModule());
				history.setVisible(true);
				history.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//				var frame = new JFrame();
//				JDialog popup = new JDialog(frame, "Hitory payments ", true);
//				popup.getContentPane().add(history);
//				popup.setSize(460, 500);
//				popup.setVisible(true);
//				frame.add(history);
//				frame.setVisible(true);
			}
		});

		scrollTable = new JScrollPane();
		scrollTable.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollTable.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollTable.setAutoscrolls(true);

		inputPage = new JTextField();
		inputPage.setHorizontalAlignment(SwingConstants.CENTER);
		inputPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (inputPage.getText().matches("-?\\d+")) {
					if (Integer.parseInt(inputPage.getText()) < totalOfPage.intValue()
							&& Integer.parseInt(inputPage.getText()) > 0) {
						pageNumber = Integer.parseInt(inputPage.getText());
						loadData();
					} else {
						JOptionPane.showMessageDialog(null, "The page isn't exist");
						inputPage.setText(pageNumber.toString());
					}

				} else {
					JOptionPane.showMessageDialog(null, "The page isn't exist");
					inputPage.setText(pageNumber.toString());
				}
			}
		});

		inputPage.setColumns(10);

		JButton btnPrev = new JButton("");
		btnPrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pageNumber > 1) {
					pageNumber--;
				}
				loadData();
			}
		});
		btnPrev.setIcon(new ImageIcon(Bill.class.getResource("/icon/icons8-double-arrow-left.png")));

		JButton btnNext = new JButton("");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pageNumber >= 1 && pageNumber < totalOfPage.intValue()) {
					pageNumber++;
				}
				loadData();
			}
		});
		btnNext.setIcon(new ImageIcon(Bill.class.getResource("/icon/icons8-double-arrow-right .png")));

		JButton btnFirst = new JButton("First");
		btnFirst.setFont(new Font("Dialog", Font.BOLD, 15));
		btnFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pageNumber = 1;
				loadData();
			}
		});

		JButton btnLast = new JButton("Last");
		btnLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pageNumber = totalOfPage.intValue();
				loadData();
			}
		});
		btnLast.setFont(new Font("Dialog", Font.BOLD, 15));
		
		lblDisplay = new JLabel("Display");
		lblDisplay.setFont(new Font("Dialog", Font.BOLD, 15));

		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tableBill != null) {
					pageNumber = 1;
					rowOfPage = Integer.parseInt(comboBox.getSelectedItem().toString());
					loadData();
				}
			}
		});
		comboBox.setBorder(null);
		comboBox.setFont(new Font("Dialog", Font.BOLD, 15));
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "20", "50", "100", "500" }));

		inputRoom = new JTextField();
		inputRoom.setBorder(new TitledBorder(null, "Room", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		inputRoom.setColumns(10);

		selectStatus = new JComboBox();
		selectStatus.setModel(new DefaultComboBoxModel(new String[] { "not finished", "finished" }));
		selectStatus.setBorder(new TitledBorder(null, "Status", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		btnFilter = new JButton("Filter");
		btnFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filter = true;
				loadData();
			}
		});
		btnFilter.setFont(new Font("Tahoma", Font.PLAIN, 15));

		from = new JDateChooser();
		from.setDateFormatString("yyyy-MM-dd");
		from.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "From", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(51, 51, 51)));

		to = new JDateChooser();
		to.setDateFormatString("yyyy-MM-dd");
		to.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "To", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(51, 51, 51)));

		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rowOfPage = 20;
				pageNumber = 1;
				filter = false;
				selectStatus.setSelectedIndex(0);
				loadData();
				to.setDate(null);
				from.setDate(null);
				inputRoom.setText(null);
				inputId.setText("");
			}
		});
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JButton view = new JButton("View");
		view.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var frame = new JFrame();
				JDialog popup = new JDialog(frame, "Report fees ", true);
				var reportForm = new TableReport(tableReport);
				popup.getContentPane().add(reportForm);
				popup.setSize(1000, 480);
		        popup.setLocationRelativeTo(frame);
		        popup.setVisible(true);
			}
		});
		view.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		inputId = new JTextField();
		 ((AbstractDocument) inputId.getDocument()).setDocumentFilter(new DocumentFilter() {
	            @Override
	            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
	                String newText = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;
	                if (newText.matches("\\d*")) { // chỉ cho phép nhập số
	                    super.replace(fb, offset, length, text, attrs);
	                } else {
	                    Toolkit.getDefaultToolkit().beep(); // phát âm thanh cảnh báo khi nhập không hợp lệ
	                }
	            }

	            @Override
	            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
	                String newText = fb.getDocument().getText(0, fb.getDocument().getLength()) + string;
	                if (newText.matches("\\d*")) { // chỉ cho phép nhập số
	                    super.insertString(fb, offset, string, attr);
	                } else {
	                    Toolkit.getDefaultToolkit().beep(); // phát âm thanh cảnh báo khi nhập không hợp lệ
	                }
	            }
	        });
		inputId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		inputId.setColumns(10);
		inputId.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Id", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(279)
							.addComponent(btnFirst)
							.addGap(34)
							.addComponent(btnPrev)
							.addGap(141)
							.addComponent(btnNext)
							.addGap(35)
							.addComponent(btnLast, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(34)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblDisplay, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE)
									.addGap(195)
									.addComponent(inputPage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 435, Short.MAX_VALUE)
									.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(inputId, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
									.addGap(28)
									.addComponent(inputRoom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(selectStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(from, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(to, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(view, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnFilter, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnReset, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnHistory, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
								.addComponent(scrollTable, GroupLayout.DEFAULT_SIZE, 1031, Short.MAX_VALUE)
								.addComponent(lblTitle, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE))))
					.addGap(35))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(36)
							.addComponent(lblTitle, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(btnHistory, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnFilter, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(to, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
									.addComponent(from, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(64)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(selectStatus, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(4)
											.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(inputRoom, GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
												.addComponent(inputId, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))))))
							.addGap(38))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(view, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addGap(39)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollTable, GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
					.addGap(41)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(inputPage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNext, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnPrev, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnFirst, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
							.addComponent(lblDisplay))
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnLast, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(77))
		);

		tableBill = new JTable();
		tableBill.setRowSelectionAllowed(false);
		scrollTable.setViewportView(tableBill);
		tableBill.setColumnSelectionAllowed(true);
		tableBill.setShowHorizontalLines(true);
		tableBill.setGridColor(new Color(230, 230, 230));
		setLayout(groupLayout);
		syncFees();

		init();
	}

	@SuppressWarnings("null")
	public  void init() {
		filter = false;
		var modelReport = new DefaultTableModel();
		modelReport.addColumn("Room");
		modelReport.addColumn("Total");
		modelReport.addColumn("Electric fee");
		modelReport.addColumn("Water fee");
		modelReport.addColumn("Rental fee");
		modelReport.addColumn("Other fee");
		modelReport.addColumn("Time");
		modelReport.addColumn("Note");
		modelReport.addColumn("Status");
		var model = new DefaultTableModel(){
			@Override
			public Class<?> getColumnClass(int column) {
				switch (column) {
//				case 0: return Integer.class;
//				case 1: return Float.class;
//				case 2: return Float.class;
//				case 3: return Float.class;
//				case 4: return Float.class;
//				case 5: return Float.class;
//				case 6: return LocalDate.class;
//				case 7: return String.class;
				case 9:
					return Boolean.class;
//				case 9: return String.class;
//				case 10 : return Integer.class;
				default:
					return String.class;
				}
			}

			@Override
			public boolean isCellEditable(int row, int col) {
				switch (col) {
				case 10:
					return true;
				default:
					return false;

				}
			}
		};
		model.addColumn("id");
		model.addColumn("Room");
		model.addColumn("Total");
		model.addColumn("Electric fee");
		model.addColumn("Water fee");
		model.addColumn("Rental fee");
		model.addColumn("Other fee");
		model.addColumn("Time");
		model.addColumn("Note");
		model.addColumn("Status");
		model.addColumn("Action");
		var dao = new FeesDao();
		var status = selectStatus.getSelectedItem().toString().equals("finished") ? "1" : "0";
		totalOfRow = dao.total(null, status, null, null, filter);
		totalOfPage = Math.ceil(totalOfRow.doubleValue() / rowOfPage.doubleValue());
		lblDisplay.setText("Display  " + (pageNumber * rowOfPage - rowOfPage) + " to "
				+ ((pageNumber * rowOfPage) > totalOfRow ? totalOfRow : pageNumber * rowOfPage) + " in " + totalOfRow
				+ " rows");
		inputPage.setText(pageNumber.toString());
		dao.getFees(pageNumber, rowOfPage, null,  null,  null,  null, filter, null).stream().forEach(fee-> {
			model.addRow(new Object[] {fee.getId(),fee.getRoom(),fee.getTotal(),fee.getElectric(),fee.getWater(),fee.getRent(),fee.getOther(),fee.getTime(),fee.getNote(),fee.getStatus(),"Action"});
			modelReport.addRow(new Object[] {fee.getRoom(),fee.getTotal(),fee.getElectric(),fee.getWater(),fee.getRent(),fee.getOther(),fee.getTime(),fee.getNote(),fee.getStatus()?"Finished":"Haven't finished"});
		});

		tableBill.setModel(model);
		tableReport.setModel(modelReport);
		event = new TableActionEvent() {

			@Override
			public void pay(int row) {
				// TODO Auto-generated method stub
				Integer id = (Integer) tableBill.getValueAt(row, 0);
				JFrame frame = new JFrame();
				showPay(frame, id);
			
			}

			@Override
			public void edit(int row) {
				
				// TODO Auto-generated method stub
				Integer id = (Integer) tableBill.getValueAt(row, 0);
				JFrame frame = new JFrame();
				showEditFees(frame, id);
				
			}
			@Override 
			public void report(int row) {
				Integer id = (Integer) tableBill.getValueAt(row, 0);
				JFrame frame = new JFrame();
				showReport(frame,id);
			}
		};
		var statusButton = selectStatus.getSelectedItem().toString().equals("finished") ? true :false ;
		var tableEvent = new tableBillEditor(event);
		tableEvent.setStatus(statusButton);
		tableBill.getColumnModel().getColumn(10).setCellEditor(tableEvent);
		var tableRender = new tableBillRender();
		tableRender.setStatus(statusButton);
		tableBill.getColumnModel().getColumn(10).setCellRenderer(tableRender);
		tableBill.getColumnModel().getColumn(10).setPreferredWidth(220);
		tableBill.setRowHeight(50);
		
	}
	
	public static void loadData () {
		var model =(DefaultTableModel) tableBill.getModel();	
		var modelReport =(DefaultTableModel) tableReport.getModel();
		var status = selectStatus.getSelectedItem().toString().equals("finished") ? "1":"0";
		model.setRowCount(0);
		modelReport.setRowCount(0);
		var room = inputRoom.getText().isEmpty() ? null : inputRoom.getText();
		var dao = new FeesDao();
		totalOfRow = dao.total(room, status, from.getDate(), to.getDate(), filter);
		totalOfPage = Math.ceil(totalOfRow.doubleValue() / rowOfPage.doubleValue());
		lblDisplay.setText("Display  "+(pageNumber*rowOfPage-rowOfPage)+" to "+((pageNumber*rowOfPage)>totalOfRow? totalOfRow :pageNumber*rowOfPage)+ " in "+ totalOfRow + " rows");
		var id = inputId.getText().isEmpty() ? null : Integer.parseInt(inputId.getText());
		
		dao.getFees(pageNumber, rowOfPage,  room,status, from.getDate(), to.getDate(),filter, id).stream().forEach(fee-> {
			model.addRow(new Object[] {fee.getId(),fee.getRoom(),fee.getTotal(),fee.getElectric(),fee.getWater(),fee.getRent(),fee.getOther(),fee.getTime(),fee.getNote(),fee.getStatus(),"Action"});
			modelReport.addRow(new Object[] {fee.getRoom(),fee.getTotal(),fee.getElectric(),fee.getWater(),fee.getRent(),fee.getOther(),fee.getTime(),fee.getNote(),fee.getStatus()?"Finished":"Haven't finished"});
		});
		tableBill.setModel(model);
		tableReport.setModel(modelReport);
		inputPage.setText(pageNumber.toString());
		var statusButton = selectStatus.getSelectedItem().toString().equals("finished") ? true :false ;
		var tableEvent = new tableBillEditor(event);
		tableEvent.setStatus(statusButton);
		tableBill.getColumnModel().getColumn(10).setCellEditor(tableEvent);
		var tableRender = new tableBillRender();
		tableRender.setStatus(statusButton);
		tableBill.getColumnModel().getColumn(10).setCellRenderer(tableRender);
		tableBill.getColumnModel().getColumn(10).setPreferredWidth(220);
		tableBill.setRowHeight(50);
	}

	public static void showEditFees(Frame frameParent, int id) {
		var status = selectStatus.getSelectedItem().toString().equals("finished") ? true :false ;
		if(!status) {
			JDialog popup = new JDialog(frameParent, "Edit fees ", true);
			var eventLoad = new EventLoadTable() {

				@Override
				public void loadDataTable() {
					loadData();
				}
			};
			
			var popupPanel = new ShowEditFees(id, eventLoad);
			popup.getContentPane().add(popupPanel);
			popup.setSize(460, 500);
			popup.setLocationRelativeTo(frameParent);
			popup.setVisible(true);
			frameParent.setVisible(true);
			frameParent.dispatchEvent(new WindowEvent(frameParent, WindowEvent.WINDOW_CLOSING));
		}
		
	}
	public static void showPay(Frame frameParent, int id) {
		var status = selectStatus.getSelectedItem().toString().equals("finished") ? true :false ;
		if(!status) {
			JDialog popup = new JDialog(frameParent, "What's status of the payment ? ", true);
			var eventLoad = new EventLoadTable() {
				
				@Override
				public void loadDataTable() {
					loadData();
				}
			};
			var popupPanel = new ShowPayment(id, eventLoad);
			popup.getContentPane().add(popupPanel);
			popup.setSize(300, 100);
			popup.setLocationRelativeTo(frameParent);
			popup.setVisible(true);
			frameParent.setVisible(true);
			frameParent.dispatchEvent(new WindowEvent(frameParent, WindowEvent.WINDOW_CLOSING));
		}
	}
	public static void showReport(Frame frameParent , int id) {
		 JDialog popup = new JDialog(frameParent, "View report ", true);
		   	var popupPanel = new ReportFee(id);
	        popup.getContentPane().add(popupPanel);
	        popup.setSize(800, 420);
	        popup.setLocationRelativeTo(frameParent);
	        popup.setVisible(true);
	        frameParent.setVisible(true);
	        frameParent.dispatchEvent(new WindowEvent(frameParent, WindowEvent.WINDOW_CLOSING));
	}
	
	public void syncFees() {
		var feeDao = new FeesDao();
		
//		LocalDate currentDate = LocalDate.now();
		LocalDate currentDate = LocalDate.parse("2024-02-30",DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate futureDate = currentDate.plus(2,ChronoUnit.DAYS);
		boolean isNewMonth = futureDate.getMonth() != currentDate.getMonth();
		if(isNewMonth) {
			feeDao.syncNewFeeMonth(futureDate);
		}

	}
}
