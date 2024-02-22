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
import view.ShowEditFees;
import view.ShowPayment;

import java.awt.event.InputMethodListener;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.awt.event.InputMethodEvent;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

import com.toedter.calendar.JDateChooser;
import javax.swing.border.LineBorder;

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

	/*
	 * Create the panel.
	 */
	public Bill() {
		setBackground(new Color(240, 240, 240));
		setForeground(new Color(255, 255, 255));
		setBorder(null);
		setBounds(0, 0, 1100, 800);

		lblTitle = new JLabel("Payment Management");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 30));

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

		JButton btnFilter = new JButton("Filter");
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
				loadData();
			}
		});
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 15));
		groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(279).addComponent(btnFirst).addGap(34)
								.addComponent(btnPrev).addGap(141).addComponent(btnNext).addGap(35)
								.addComponent(btnLast, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
						.addGroup(
								groupLayout.createSequentialGroup().addGap(34)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 381,
														GroupLayout.PREFERRED_SIZE)
												.addGroup(groupLayout.createSequentialGroup()
														.addComponent(inputRoom, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addGap(34)
														.addComponent(selectStatus, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addGap(18)
														.addComponent(from, GroupLayout.PREFERRED_SIZE, 100,
																GroupLayout.PREFERRED_SIZE)
														.addGap(29)
														.addComponent(to, GroupLayout.PREFERRED_SIZE, 100,
																GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED, 194,
																Short.MAX_VALUE)
														.addComponent(btnFilter, GroupLayout.PREFERRED_SIZE, 107,
																GroupLayout.PREFERRED_SIZE)
														.addGap(24)
														.addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 107,
																GroupLayout.PREFERRED_SIZE)
														.addGap(30).addComponent(
																btnHistory, GroupLayout.PREFERRED_SIZE, 104,
																GroupLayout.PREFERRED_SIZE))
												.addGroup(groupLayout.createSequentialGroup()
														.addComponent(lblDisplay, GroupLayout.PREFERRED_SIZE, 234,
																GroupLayout.PREFERRED_SIZE)
														.addGap(197)
														.addComponent(inputPage, GroupLayout.PREFERRED_SIZE, 84,
																GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED, 435,
																Short.MAX_VALUE)
														.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 81,
																GroupLayout.PREFERRED_SIZE))
												.addComponent(scrollTable, GroupLayout.DEFAULT_SIZE, 1031,
														Short.MAX_VALUE))))
				.addGap(35)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(55)
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(
						groupLayout.createSequentialGroup().addComponent(lblTitle).addGap(33).addGroup(groupLayout
								.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnFilter, GroupLayout.PREFERRED_SIZE, 34,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 34,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnHistory, GroupLayout.PREFERRED_SIZE, 30,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(from, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
										.addComponent(to, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))))
						.addGroup(
								groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(inputRoom, GroupLayout.PREFERRED_SIZE, 44,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(selectStatus, GroupLayout.PREFERRED_SIZE, 48,
												GroupLayout.PREFERRED_SIZE)))
				.addGap(38).addComponent(scrollTable, GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE).addGap(41)
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnNext, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnPrev, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnFirst, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
								.addComponent(lblDisplay))
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnLast, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addComponent(inputPage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(77)));

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
	public void init() {
		var model = new DefaultTableModel() {
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
		dao.getFees(pageNumber, rowOfPage, null, null, null, null, filter).stream().forEach(fee -> {
			model.addRow(new Object[] { fee.getId(), fee.getRoom(), fee.getTotal(), fee.getElectric(), fee.getWater(),
					fee.getRent(), fee.getOther(), fee.getTime(), fee.getNote(), fee.getStatus(), "Action" });
		});

		tableBill.setModel(model);
		event = new TableActionEvent() {

			@Override
			public void pay(int row) {
				// TODO Auto-generated method stub
				Integer id = (Integer) tableBill.getValueAt(row, 0);
				JFrame frame = new JFrame();
				showPay(frame, id);
				frame.setVisible(true);
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}

			@Override
			public void edit(int row) {
				// TODO Auto-generated method stub
				Integer id = (Integer) tableBill.getValueAt(row, 0);
				JFrame frame = new JFrame();
				showEditFees(frame, id);
				frame.setVisible(true);
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		};
		tableBill.getColumnModel().getColumn(10).setCellEditor(new tableBillEditor(event));
		tableBill.getColumnModel().getColumn(10).setCellRenderer(new tableBillRender());
		tableBill.getColumnModel().getColumn(10).setPreferredWidth(133);
		tableBill.setRowHeight(50);
	}

	public static void loadData() {
		var model = (DefaultTableModel) tableBill.getModel();
		var status = selectStatus.getSelectedItem().toString().equals("finished") ? "1" : "0";
		model.setRowCount(0);
		var dao = new FeesDao();
		totalOfRow = dao.total(inputRoom.getText(), status, from.getDate(), to.getDate(), filter);
		totalOfPage = Math.ceil(totalOfRow.doubleValue() / rowOfPage.doubleValue());
		lblDisplay.setText("Display  " + (pageNumber * rowOfPage - rowOfPage) + " to "
				+ ((pageNumber * rowOfPage) > totalOfRow ? totalOfRow : pageNumber * rowOfPage) + " in " + totalOfRow
				+ " rows");
		dao.getFees(pageNumber, rowOfPage, inputRoom.getText(), status, from.getDate(), to.getDate(), filter).stream()
				.forEach(fee -> {
					model.addRow(new Object[] { fee.getId(), fee.getRoom(), fee.getTotal(), fee.getElectric(),
							fee.getWater(), fee.getRent(), fee.getOther(), fee.getTime(), fee.getNote(),
							fee.getStatus(), "Action" });
				});
		tableBill.setModel(model);
		inputPage.setText(pageNumber.toString());
		tableBill.getColumnModel().getColumn(10).setCellEditor(new tableBillEditor(event));
		tableBill.getColumnModel().getColumn(10).setCellRenderer(new tableBillRender());
		tableBill.getColumnModel().getColumn(10).setPreferredWidth(133);
		tableBill.setRowHeight(50);
	}

	public static void showEditFees(Frame frameParent, int id) {
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
	}

	public static void showPay(Frame frameParent, int id) {
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
	}

	public void syncFees() {
		var feeDao = new FeesDao();

		LocalDate currentDate = LocalDate.now();
//		LocalDate currentDate = LocalDate.parse("2023-11-29",DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate futureDate = currentDate.plus(5, ChronoUnit.DAYS);
		boolean isNewMonth = futureDate.getMonth() != currentDate.getMonth();
		var dao = new RoomsDao();
		dao.getRooms();
		if (isNewMonth) {
			feeDao.syncNewFeeMonth(futureDate);
		}

	}
}
