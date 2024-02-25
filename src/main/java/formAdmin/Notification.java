package formAdmin;

import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import component.Login;
import dao.NotiDao;
import entity.Noti;
import event.EventTableNotiAction;
import scrollbar.ScrollBarCustom;
import table.CheckBoxRenderer;
import table.CustomTable;
import table.TableActionCellEditor;
import table.TableActionCellEditorNoti;
import table.TableActionCellNoti;
import table.TableActionCellRender;
import table.TableHeader;
import view.ShowCompose;
import view.ShowSend;
import view.ViewNoti;

import java.awt.Color;
import java.awt.Component;

import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Scrollbar;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.net.ssl.SSLContext;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;

public class Notification extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblNewLabel;
	private JButton btnCompose;
	private JScrollPane scrollPane;
	private JPanel TablePanel;
	private JLabel txtPageNumber;
	private Integer pageNumber = 1;
	private Integer rowOfPage = 15;
	private Integer totalOfRow = 0;
	private Double totalPage = 0.0;
	private JTextField txtSearchName;
	private JTable table;
	private JPanel panel;
	private JScrollPane scrollPane_1;
	private JTable table_1;
	private JButton btnPer;
	private JButton btnNext;
	private JTextField textNumber;
	private JComboBox comboBox;
	private JButton btnLoadData;
	private JButton btnSend;

	/**
	 * Create the panel.
	 */
	public Notification() {
		setBackground(SystemColor.menu);
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		setBounds(0, 0, 1050, 800);
		{
			lblNewLabel = new JLabel("Notification");
			lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		}

		btnCompose = new JButton("Compose");
		btnCompose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnComposeActionPerformed(e);
			}
		});
		btnCompose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCompose.setBorder(null);
		btnCompose.setBackground(new Color(194, 231, 255));
		btnCompose.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCompose.setIcon(new ImageIcon(Notification.class.getResource("/icon/solar_pen-bold.png")));
		panel = new JPanel();
		panel.setBorder(null);

		btnPer = new JButton("<");
		btnPer.setVisible(false);
		btnPer.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnPer.setBackground(new Color(135, 206, 235));

		btnNext = new JButton(">");
		btnNext.setVisible(false);
		btnNext.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnNext.setBackground(new Color(135, 206, 235));
		textNumber = new JTextField();
		textNumber.setVisible(false);
		textNumber.setText("1");
		textNumber.setHorizontalAlignment(SwingConstants.CENTER);
		textNumber.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textNumber.setColumns(10);
		comboBox = new JComboBox();
		comboBox.setVisible(false);
		comboBox.setFont(new Font("Dialog", Font.PLAIN, 12));
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "10", "20", "25" }));

		btnLoadData = new JButton("Load data");
		btnLoadData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLoadDataActionPerformed(e);
			}
		});
		btnLoadData.setIcon(new ImageIcon(Notification.class.getResource("/icon/reload.png")));
		btnLoadData.setForeground(Color.WHITE);
		btnLoadData.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnLoadData.setBorder(null);
		btnLoadData.setBackground(new Color(128, 128, 255));
		btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSendActionPerformed(e);
			}
		});
		btnSend.setIcon(new ImageIcon(Notification.class.getResource("/icon/material-symbols_send-outline.png")));
		btnSend.setForeground(new Color(0, 0, 0));
		btnSend.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSend.setBorder(null);
		btnSend.setBackground(new Color(255, 215, 0));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnLoadData, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
							.addGap(26)
							.addComponent(btnSend, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnCompose, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addGroup(groupLayout.createSequentialGroup()
								.addContainerGap()
								.addComponent(btnPer)
								.addGap(28)
								.addComponent(textNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGap(26)
								.addComponent(btnNext, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
								.addGap(261)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
							.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
								.addGap(92)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
									.addComponent(panel, GroupLayout.DEFAULT_SIZE, 879, Short.MAX_VALUE)))))
					.addGap(79))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCompose, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSend, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLoadData, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
					.addGap(42)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 516, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPer)
						.addComponent(btnNext)
						.addComponent(textNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(94, Short.MAX_VALUE))
		);
		panel.setLayout(new BorderLayout(0, 0));
		{
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setVerticalScrollBar(new ScrollBarCustom());
			panel.add(scrollPane_1, BorderLayout.CENTER);

			table_1 = new JTable();
//			table_1.addMouseListener(new MouseAdapter() {
//				@Override
//				public void mouseClicked(MouseEvent e) {
//					mouseClicked(e);
//				}
//			});
			loadData();
			scrollPane_1.setViewportView(table_1);
		}
		TablePanel = new JPanel();
		TablePanel.setBackground(SystemColor.menu);
		TablePanel.setBorder(null);

		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBar(new ScrollBarCustom());

		txtPageNumber = new JLabel("");
		txtPageNumber.setText(pageNumber.toString());
		txtSearchName = new JTextField();

		table = new JTable();
		table.setBorder(null);
		table.setRowSelectionAllowed(true);
		table.setColumnSelectionAllowed(false);
		table.setShowHorizontalLines(true);
		table.setGridColor(new Color(230, 230, 230));
		table.setRowHeight(40);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setVisible(true);
		table.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				TableHeader header = new TableHeader(value + "");

				header.setHorizontalAlignment(JLabel.CENTER);
				return header;

			}

		});
		setLayout(groupLayout);

	}

	// Compose noti
	protected void btnComposeActionPerformed(ActionEvent e) {
		ShowCompose sc = new ShowCompose();
		sc.setVisible(true);
	}

	// load data
	public void loadData() {

		var model = new DefaultTableModel() {
			public java.lang.Class<?> getColumnClass(int columnIndex) {
				switch (columnIndex) {
				default:
					return String.class;
				}

			};

			@Override
			public boolean isCellEditable(int row, int column) {
				switch (column) {
				case 5:
					return true;
				default:
					return false;
				}
			}
		};
		model.addColumn("No.");
		model.addColumn("Sender Email");
		model.addColumn("Role");
		model.addColumn("Message");
		model.addColumn("Time");
		model.addColumn("Action");
		table_1.setBorder(null);
		table_1.setRowSelectionAllowed(false);
		table_1.setColumnSelectionAllowed(false);
		table_1.setModel(model);
		table_1.setRowHeight(35);
		EventTableNotiAction event = new EventTableNotiAction() {

			@Override
			public void view(int row) {

				String email = (String) table_1.getValueAt(row, 1);
				LocalDateTime dateTime = (LocalDateTime) table_1.getValueAt(row, 4);
				String time = dateTime.toString().replace("T", "  ");
				String mess = (String) table_1.getValueAt(row, 3);
				ViewNoti showViewNoti = new ViewNoti(email, time, mess);
				showViewNoti.setVisible(true);
			}

			@Override
			public void edit(int row) {
				Integer id = Login.getId();
				// TODO Auto-generated method stub
				var notiDao = new NotiDao();

				List<Noti> notiList = notiDao.selAllNoti(id);

				Noti selectedNoti = notiList.get(row);
				int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure to delete?", "Confirm",
						JOptionPane.YES_NO_OPTION);
				if (confirmation == JOptionPane.YES_OPTION) {
					notiDao.deleteNoti(selectedNoti.getId());
					loadData();

				}

			}
		};
		AtomicInteger sttCounter = new AtomicInteger(1);
		NotiDao notificationDao = new NotiDao();
		Integer id = Login.getId();
		notificationDao.selAllNoti(id).stream().forEach(noti -> {
			String roleValue = "";

			if (noti.getSender_role()) {
				roleValue = "Admin";
			} else {
				roleValue = "Renter";
			}
			model.addRow(new Object[] { sttCounter.getAndIncrement(), noti.getSender_email(), roleValue, noti.getMess(),
					noti.getCreated_at() });
		});

		table_1.getColumnModel().getColumn(5).setCellRenderer(new TableActionCellNoti());
		table_1.getColumnModel().getColumn(5).setCellEditor(new TableActionCellEditorNoti(event));

	}

	protected void tableMouseClicked(MouseEvent e) {
		int rowindex = table_1.getSelectedRow();
	}

	// load data
	protected void btnLoadDataActionPerformed(ActionEvent e) {
		loadData();
	}
	protected void btnSendActionPerformed(ActionEvent e) {
		ShowSend sh = new ShowSend();
		sh.setVisible(true);
	}
}