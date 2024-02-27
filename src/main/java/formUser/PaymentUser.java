package formUser;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;

import com.toedter.calendar.JDateChooser;

import component.Login;
import dao.FeesDao;
import event.EventShowFeeUser;
import formAdmin.Bill;
import model.tableBillEditor;
import model.tableBillRender;
import view.tableFeeUserRender;
import view.tableUserEditor;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class PaymentUser extends JPanel {

	private static final long serialVersionUID = 1L;
	private static JLabel title;
	private static JTable table;
	private static JTextField inputPage;
	private static Boolean filter = false;
	private static Integer pageNumber = 1;
	private static Integer rowOfPage = 20;
	private static Double totalOfPage = 0.0;
	private static Integer totalOfRow= 0;
	private static JComboBox selectStatus;
	private static Integer user_id;
	private static JButton btnNext;
	private static JButton btnLast;
	private static JDateChooser to;
	private static JDateChooser from;
	private static JComboBox selectRow;
	private static JButton btnPrev;
	private static JButton btnFirst;
	private static JLabel lblDisplay;
	private static JTextField inputRoom;

	/**
	 * Create the panel.
	 */
	public PaymentUser() {
		setBounds(0, 0, 1100, 800);
		filter = false;
		title = new JLabel("Payment ");
		title.setFont(new Font("Arial", Font.BOLD, 20));
		
		JScrollPane scrollPane = new JScrollPane();
		
		selectStatus = new JComboBox();
		selectStatus.setModel(new DefaultComboBoxModel(new String[] {"Haven't finished", "Finished"}));
		selectStatus.setBorder(new TitledBorder(null, "Status", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		from = new JDateChooser();
		from.setDateFormatString("yyyy-MM-dd");
		from.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "From", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		
		to = new JDateChooser();
		to.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		to.setDateFormatString("yyyy-MM-dd");
		to.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "To", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		
		JButton btnFilter = new JButton("Filter");
		btnFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filter = true;
				loadData();
			}
		});
		btnFilter.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
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
				loadData();
			}
		});
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		lblDisplay = new JLabel("Display  0 to 20 in 16 rows");
		lblDisplay.setFont(new Font("Dialog", Font.BOLD, 15));
		
		btnFirst = new JButton("First");
		btnFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pageNumber = 1 ;
				loadData();
			}
		});
		btnFirst.setFont(new Font("Dialog", Font.BOLD, 15));
		
		btnPrev = new JButton("");
		btnPrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(pageNumber > 1) {
					pageNumber--;
				}
				loadData();
			}
		});
		btnPrev.setIcon(new ImageIcon(PaymentUser.class.getResource("/icon/icons8-double-arrow-left.png")));
		
		inputPage = new JTextField();
		inputPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (inputPage.getText().matches("-?\\d+")  ) {
					if (Integer.parseInt(inputPage.getText())<totalOfPage.intValue()&&Integer.parseInt(inputPage.getText())>0) {
						pageNumber = Integer.parseInt(inputPage.getText());
						loadData();
					}
					else {
						JOptionPane.showMessageDialog(null, "The page isn't exist");
						inputPage.setText(pageNumber.toString());
					}
					
				}
				else  {
					JOptionPane.showMessageDialog(null, "The page isn't exist");
					inputPage.setText(pageNumber.toString());
				}
			}
		});
		inputPage.setText("1");
		inputPage.setColumns(10);
		
		btnNext = new JButton("");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(pageNumber>=1 && pageNumber<totalOfPage.intValue()) {
					pageNumber++;
				}
				loadData();
			}
		});
		btnNext.setIcon(new ImageIcon(PaymentUser.class.getResource("/icon/icons8-double-arrow-right .png")));
		
		btnLast = new JButton("Last");
		btnLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pageNumber = totalOfPage.intValue();
				loadData();
			}
		});
		btnLast.setFont(new Font("Dialog", Font.BOLD, 15));
		
		selectRow = new JComboBox();
		selectRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table!=null) {
					pageNumber = 1;
					rowOfPage = Integer.parseInt(selectRow.getSelectedItem().toString());
					loadData();
				}
			}
		});
		selectRow.setModel(new DefaultComboBoxModel(new String[] {"20", "50", "100", "500"}));
		selectRow.setFont(new Font("Dialog", Font.BOLD, 15));
		selectRow.setBorder(null);
		
		inputRoom = new JTextField();
		((AbstractDocument) inputRoom.getDocument()).setDocumentFilter(new DocumentFilter() {
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
		inputRoom.setColumns(10);
		inputRoom.setBorder(new TitledBorder(null, "Room", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		table = new JTable();
		scrollPane.setViewportView(table);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(36)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(title, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(selectStatus, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
							.addGap(48)
							.addComponent(inputRoom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(36)
							.addComponent(from, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addGap(27)
							.addComponent(to, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
							.addGap(41)
							.addComponent(btnFilter, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 1040, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblDisplay, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(btnFirst)
							.addGap(57)
							.addComponent(btnPrev)
							.addGap(29)
							.addComponent(inputPage, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
							.addGap(33)
							.addComponent(btnNext)
							.addGap(50)
							.addComponent(btnLast, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
							.addGap(225)
							.addComponent(selectRow, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(title, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(selectStatus, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
								.addComponent(inputRoom, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(51)
							.addComponent(from, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(51)
							.addComponent(to, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(62)
							.addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(62)
							.addComponent(btnFilter, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)))
					.addGap(29)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 592, GroupLayout.PREFERRED_SIZE)
					.addGap(14)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(7)
							.addComponent(lblDisplay))
						.addComponent(btnFirst, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPrev, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(14)
							.addComponent(inputPage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnNext, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLast, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(4)
							.addComponent(selectRow, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
		);
		setLayout(groupLayout);
		init();
	}
	
	public void init() {
		var model = new DefaultTableModel(){
			@Override
			 public Class<?> getColumnClass(int column){
				 switch (column) {
//				case 0: return Integer.class;
//				case 1: return Float.class;
//				case 2: return Float.class;
//				case 3: return Float.class;
//				case 4: return Float.class;
//				case 5: return Float.class;
//				case 6: return LocalDate.class;
//				case 7: return String.class;
				case 9: return Boolean.class;
//				case 9: return String.class;
//				case 10 : return Integer.class;
				default: return String.class;
			 }
		}
		@Override
		public boolean isCellEditable (int row, int col) {
			switch (col) {
			case 10: return true;
			default: return false;
				
			}
		}
		};
		model.addColumn("Id");
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
		user_id = Login.getId();
		var dao = new FeesDao();
		var status = selectStatus.getSelectedItem().toString().equals("Finished") ? "1":"0";
		totalOfRow = dao.totalUserFees(user_id,status, from.getDate(), to.getDate(),filter, inputRoom.getText());
		totalOfPage = Math.ceil(totalOfRow.doubleValue() / rowOfPage.doubleValue());
		lblDisplay.setText("Display  "+(pageNumber*rowOfPage-rowOfPage)+" to "+((pageNumber*rowOfPage)>totalOfRow? totalOfRow :pageNumber*rowOfPage)+ " in "+ totalOfRow + " rows");
		var room = inputRoom.getText().isEmpty()? null : inputRoom.getText();
		dao.getFeesUser(pageNumber, rowOfPage,  null,  null,  null, filter, user_id,null).stream().forEach(fee-> {
			model.addRow(new Object[] {fee.getId(),fee.getRoom(),fee.getTotal(),fee.getElectric(),fee.getWater(),fee.getRent(),fee.getOther(),fee.getTime(),fee.getNote(),fee.getStatus(),"Action"});
			
		});
		
		var event = new EventShowFeeUser() {
			
			@Override
			public void view(int row) {
				Integer id = (Integer) table.getValueAt(row, 0);
				JFrame frame = new JFrame();
				Bill.showReport(frame,id);
				frame.setVisible(true);
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		};
		
		
		table.setModel(model);
		table.getColumnModel().getColumn(10).setCellEditor(new tableUserEditor(event));
		table.getColumnModel().getColumn(10).setCellRenderer(new tableFeeUserRender());
		table.getColumnModel().getColumn(10).setPreferredWidth(70);
		table.setRowHeight(50);
	}
	
	public void loadData() {
		var model =(DefaultTableModel) table.getModel();	
		var status = selectStatus.getSelectedItem().toString().equals("Finished") ? "1":"0";
		model.setRowCount(0);
		var dao = new FeesDao();
		totalOfRow = dao.totalUserFees(user_id,status, from.getDate(), to.getDate(),filter, inputRoom.getText());
		totalOfPage = Math.ceil(totalOfRow.doubleValue() / rowOfPage.doubleValue());
		var room = inputRoom.getText().isEmpty()? null : inputRoom.getText();
		lblDisplay.setText("Display  "+(pageNumber*rowOfPage-rowOfPage)+" to "+((pageNumber*rowOfPage)>totalOfRow? totalOfRow :pageNumber*rowOfPage)+ " in "+ totalOfRow + " rows");
		dao.getFeesUser(pageNumber, rowOfPage,status, from.getDate(), to.getDate(),filter, user_id, room).stream().forEach(fee-> {
			model.addRow(new Object[] {fee.getId(),fee.getRoom(),fee.getTotal(),fee.getElectric(),fee.getWater(),fee.getRent(),fee.getOther(),fee.getTime(),fee.getNote(),fee.getStatus(),"Action"});
		});
		table.setModel(model);
		table.setRowHeight(50);
	}
}
