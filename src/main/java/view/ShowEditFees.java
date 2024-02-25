package view;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;

import component.Login;
import dao.FeesDao;
import event.EventLoadTable;
import model.Fees;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import component.Login;
public class ShowEditFees extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField inputWater;
	private JTextField inputElectric;
	private JTextField inputRent;
	private JTextField inputOther;
	private Fees fee;
	private JLabel lblTitle;
	private JLabel lblTime;
	private JTextArea textNote;
	private JLabel lblTotal;
	private  static FeesDao dao = new FeesDao();
	/**
	 * Create the panel.
	 */
	public ShowEditFees(int id, EventLoadTable event) {
		
		lblTitle = new JLabel("Edit fees detail");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		inputWater = new JTextField();
		 ((AbstractDocument) inputWater.getDocument()).setDocumentFilter(new DocumentFilter() {
	            @Override
	            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
	                String newText = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;
	                if (newText.matches("-?\\d*\\.?\\d{0,3}")) { // chỉ cho phép nhập số
	                    super.replace(fb, offset, length, text, attrs);
	                } else {
	                    Toolkit.getDefaultToolkit().beep(); // phát âm thanh cảnh báo khi nhập không hợp lệ
	                }
	            }

	            @Override
	            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
	                String newText = fb.getDocument().getText(0, fb.getDocument().getLength()) + string;
	                if (newText.matches("-?\\d*\\.?\\d{0,3}")) { // chỉ cho phép nhập số
	                    super.insertString(fb, offset, string, attr);
	                } else {
	                    Toolkit.getDefaultToolkit().beep(); // phát âm thanh cảnh báo khi nhập không hợp lệ
	                }
	            }
	        });
		inputWater.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Water fees", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		inputWater.setColumns(10);
		
		lblTime = new JLabel("From this to that");
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		inputElectric = new JTextField();
		inputElectric.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		 ((AbstractDocument) inputElectric.getDocument()).setDocumentFilter(new DocumentFilter() {
	            @Override
	            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
	                String newText = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;
	                if (newText.matches("-?\\d*\\.?\\d{0,3}")) { // chỉ cho phép nhập số
	                    super.replace(fb, offset, length, text, attrs);
	                } else {
	                    Toolkit.getDefaultToolkit().beep(); // phát âm thanh cảnh báo khi nhập không hợp lệ
	                }
	            }

	            @Override
	            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
	                String newText = fb.getDocument().getText(0, fb.getDocument().getLength()) + string;
	                if (newText.matches("-?\\d*\\.?\\d{0,3}")) { // chỉ cho phép nhập số
	                    super.insertString(fb, offset, string, attr);
	                } else {
	                    Toolkit.getDefaultToolkit().beep(); // phát âm thanh cảnh báo khi nhập không hợp lệ
	                }
	            }
	        });
		inputElectric.setColumns(10);
		inputElectric.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Electric fees", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		inputRent = new JTextField();
		inputRent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		 ((AbstractDocument) inputRent.getDocument()).setDocumentFilter(new DocumentFilter() {
	            @Override
	            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
	                String newText = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;
	                if (newText.matches("-?\\d*\\.?\\d{0,3}")) { // chỉ cho phép nhập số
	                    super.replace(fb, offset, length, text, attrs);
	                } else {
	                    Toolkit.getDefaultToolkit().beep(); // phát âm thanh cảnh báo khi nhập không hợp lệ
	                }
	            }

	            @Override
	            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
	                String newText = fb.getDocument().getText(0, fb.getDocument().getLength()) + string;
	                if (newText.matches("-?\\d*\\.?\\d{0,3}")) { // chỉ cho phép nhập số
	                    super.insertString(fb, offset, string, attr);
	                } else {
	                    Toolkit.getDefaultToolkit().beep(); // phát âm thanh cảnh báo khi nhập không hợp lệ
	                }
	            }
	        });
		inputRent.setColumns(10);
		inputRent.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Next month  rental fees", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		inputOther = new JTextField();
		inputOther.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		 ((AbstractDocument) inputOther.getDocument()).setDocumentFilter(new DocumentFilter() {
	            @Override
	            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
	                String newText = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;
	                if (newText.matches("-?\\d*\\.?\\d{0,3}")) { // chỉ cho phép nhập số
	                    super.replace(fb, offset, length, text, attrs);
	                } else {
	                    Toolkit.getDefaultToolkit().beep(); // phát âm thanh cảnh báo khi nhập không hợp lệ
	                }
	            }

	            @Override
	            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
	                String newText = fb.getDocument().getText(0, fb.getDocument().getLength()) + string;
	                if (newText.matches("-?\\d*\\.?\\d{0,3}")) { // chỉ cho phép nhập số
	                    super.insertString(fb, offset, string, attr);
	                } else {
	                    Toolkit.getDefaultToolkit().beep(); // phát âm thanh cảnh báo khi nhập không hợp lệ
	                }
	            }
	        });
		inputOther.setColumns(10);
		inputOther.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Other fees", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		lblTotal = new JLabel("Total money");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 Window window = SwingUtilities.getWindowAncestor(ShowEditFees.this);
				 window.dispose();
			}
		});
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				update water
				var waterUpdate = dao.updateMoney(fee.getWater_id(),inputWater.getText().isEmpty()? 0: Float.parseFloat(inputWater.getText()));
//				update elect
				var elecUpdate = dao.updateMoney(fee.getElectric_id(),inputElectric.getText().isEmpty() ? 0: Float.parseFloat(inputElectric.getText()));
//				update rent
				var rentUpdate = dao.updateMoney(fee.getRent_id(),inputRent.getText().isEmpty() ? 0 : Float.parseFloat(inputRent.getText()));
//				update rent
				var otherUpdate = dao.updateMoney(fee.getOther_id(), inputOther.getText().isEmpty() ? 0 : Float.parseFloat(inputOther.getText()));
//				update fee
				var feeUpdate = dao.updateFees(fee.getId(), textNote.getText() );
				var history = dao.getDaoHistory();
				if(waterUpdate==0) {
					 JOptionPane.showMessageDialog(null, "Update water fee failed", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else if(elecUpdate==0) {
					 JOptionPane.showMessageDialog(null, "Update electric fee failed", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else if(otherUpdate==0) {
					 JOptionPane.showMessageDialog(null, "Update other fee failed", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else if(rentUpdate==0) {
					JOptionPane.showMessageDialog(null, "Update rent fee  failed", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else if(feeUpdate==0) {
					 JOptionPane.showMessageDialog(null, "Update note of fee failed", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else {
					var user_id = Login.getId();
					history.insertHistory(dao.getModule(), "updated id "+id,user_id );
					JOptionPane.showMessageDialog(null, "Update success");
					 Window window = SwingUtilities.getWindowAncestor(ShowEditFees.this);
					 event.loadDataTable();
					 window.dispose();
				}
			}
		});
		
		textNote = new JTextArea();
		textNote.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Note", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(78)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textNote, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
						.addComponent(inputElectric, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE)
						.addComponent(inputWater, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE)
						.addComponent(inputRent, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblTotal, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
						.addComponent(inputOther, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE))
					.addGap(81))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(127, Short.MAX_VALUE)
					.addComponent(lblTitle)
					.addGap(118))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(192, Short.MAX_VALUE)
					.addComponent(lblTime, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
					.addGap(100))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTitle)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblTime)
					.addGap(18)
					.addComponent(inputWater, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(inputElectric, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(inputRent, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(inputOther, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(textNote, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblTotal, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(36, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		init(id);
	}
	
	public void init(int id) {
		
		fee = dao.showEditItem(id);
		inputElectric.setText( String.valueOf(fee.getElectric()));
		inputOther.setText(String.valueOf(fee.getOther()));
		inputWater.setText(String.valueOf(fee.getWater()));
		inputRent.setText(String.valueOf(fee.getRent()));
		lblTitle.setText("Edit fees detail of room "+String.valueOf(fee.getRoom()));
		lblTime.setText(String.valueOf((fee.getTime()!= null)? fee.getTime() : ""));
		textNote.setText(String.valueOf((fee.getNote()!= null)? fee.getNote() : ""));
		lblTotal.setText("Total money : "+fee.getTotal());
	}
}
