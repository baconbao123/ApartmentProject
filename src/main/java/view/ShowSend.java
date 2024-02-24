package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import component.Login;
import dao.NotiDao;
import entity.Noti;
import event.EventTableNotiAction;
import table.TableActionCellEditorNoti;
import table.TableActionCellNoti;
import table.TableHeader;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JTable;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Component;

public class ShowSend extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JLabel lblNewLabel;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowSend frame = new ShowSend();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ShowSend() {
		setTitle("View send");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 889, 490);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 255, 255));
		lblNewLabel = new JLabel("NOTIFICATION SEND");
		lblNewLabel.setForeground(new Color(0, 64, 0));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 12));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(79)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 714, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(70, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 362, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(30, Short.MAX_VALUE))
		);
		{
			table = new JTable();
			table.setBackground(new Color(255, 255, 255));
		
			scrollPane.setViewportView(table);
			scrollPane.setBorder(null);
			loadData();
			
		}
		contentPane.setLayout(gl_contentPane);
		
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
					case 6:
						return true;
					default:
						return false;
					}
				}
			};
			model.addColumn("No.");
			model.addColumn("Sender Email");
			model.addColumn("Receiver Email");
			model.addColumn("Role receiver");
			model.addColumn("Message");
			model.addColumn("Time");
			model.addColumn("Action");
			table.setBorder(null);
			table.setRowSelectionAllowed(false);
			table.setColumnSelectionAllowed(false);
			table.setModel(model);
			table.setRowHeight(35);
			EventTableNotiAction event = new EventTableNotiAction() {

				@Override
				public void view(int row) {

					String email = (String) table.getValueAt(row, 1);
					LocalDateTime dateTime = (LocalDateTime) table.getValueAt(row, 5);
					String time = dateTime.toString().replace("T", "  ");
					String mess = (String) table.getValueAt(row, 4);
					ViewNoti showViewNoti = new ViewNoti(email, time, mess);
					showViewNoti.setVisible(true);
				}

				@Override
				public void edit(int row) {
					Integer id = Login.getId();
					// TODO Auto-generated method stub
					var notiDao = new NotiDao();

					List<Noti> notiList = notiDao.selAllNotiSend(id);

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
			notificationDao.selAllNotiSend(id).stream().forEach(noti -> {
				String roleValue = "";

				if (!noti.getSender_role()) {
					roleValue = "Admin";
				} else {
					roleValue = "Renter";
				}
				model.addRow(new Object[] { sttCounter.getAndIncrement(), noti.getSender_email(), noti.getReceiver_email(),roleValue, noti.getMess(),
						noti.getCreated_at() });
			});

			table.getColumnModel().getColumn(6).setCellRenderer(new TableActionCellNoti());
			table.getColumnModel().getColumn(6).setCellEditor(new TableActionCellEditorNoti(event));
		}
}
