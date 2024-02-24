package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import component.Login;
import dao.NotiDao;
import dao.UserDao;
import entity.Noti;
import entity.Users;
import entity.UsersOtherId;

import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.Choice;
import java.awt.TextArea;
import java.awt.Label;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;

public class ShowCompose extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JLabel lblMessage;
	// private JComboBox comboBox;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JButton btnNewButton;
	private JScrollPane scrollPane_1;
	private List<String> selectedUsers;
	private JComboBox<String> comboBox;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JLabel lblNewLabel_1;
	private JList<String> listSelectedUsers; // JList để hiển thị danh sách người nhận đã chọn
	private DefaultListModel<String> selectedUsersModel; // Model cho JList
	private JButton btnNewButton_3;
	private Map<String, Integer> emailToUserMap = new HashMap<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowCompose frame = new ShowCompose();
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
	public ShowCompose() {
		setTitle("Compose");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(600, 250, 780, 536);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		{
			lblNewLabel = new JLabel("To");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNewLabel.setBounds(71, 38, 46, 14);
			contentPane.add(lblNewLabel);
		}
		{
			lblMessage = new JLabel("Message");
			lblMessage.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblMessage.setBounds(71, 145, 115, 20);
			contentPane.add(lblMessage);
		}
		{
			comboBox = new JComboBox();
			comboBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
			comboBox.setBounds(112, 36, 299, 22);
			contentPane.add(comboBox);
		}
		{
			scrollPane = new JScrollPane();
			scrollPane.setBounds(71, 176, 631, 258);
			contentPane.add(scrollPane);
			{
				textArea = new JTextArea();
				textArea.setWrapStyleWord(true);
				textArea.setBackground(new Color(226, 226, 226));
				scrollPane.setViewportView(textArea);
			}
		}
		{
			btnNewButton = new JButton("Send");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnNewButtonActionPerformed(e);
				}
			});
			btnNewButton.setBorder(null);
			btnNewButton.setFont(new Font("Arial", Font.PLAIN, 12));
			btnNewButton.setForeground(new Color(255, 255, 255));
			btnNewButton.setBackground(new Color(30, 144, 255));
			btnNewButton
					.setIcon(new ImageIcon(ShowCompose.class.getResource("/icon/material-symbols_send-outline 1.png")));
			btnNewButton.setBounds(71, 444, 107, 27);
			contentPane.add(btnNewButton);
		}
		{
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(445, 38, 257, 92);
			contentPane.add(scrollPane_1);
		}
		{
			btnNewButton_1 = new JButton("Select User");
			btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnNewButton_1ActionPerformed(e);
				}
			});
			btnNewButton_1.setBackground(Color.WHITE);
			btnNewButton_1.setBounds(113, 83, 100, 27);
			contentPane.add(btnNewButton_1);
		}
		{
			btnNewButton_2 = new JButton("Select All User");
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnNewButton_2ActionPerformed(e);
				}
			});
			btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnNewButton_2.setBackground(new Color(255, 255, 255));
			btnNewButton_2.setBounds(226, 83, 115, 27);
			contentPane.add(btnNewButton_2);
		}
		{
			lblNewLabel_1 = new JLabel("List selected user");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel_1.setBounds(445, 15, 131, 14);
			contentPane.add(lblNewLabel_1);
		}
		{
			btnNewButton_3 = new JButton("Remove");
			btnNewButton_3.setIcon(new ImageIcon(ShowCompose.class.getResource("/icon/trash.png")));
			btnNewButton_3.setForeground(new Color(255, 255, 255));
			btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnNewButton_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnNewButton_3ActionPerformed(e);
				}
			});
			btnNewButton_3.setBackground(new Color(255, 0, 0));
			btnNewButton_3.setBounds(596, 11, 107, 23);
			contentPane.add(btnNewButton_3);
		}
		showSelect();
		selectedUsers = new ArrayList<>();
		selectedUsersModel = new DefaultListModel<>();
		listSelectedUsers = new JList<>(selectedUsersModel);
		scrollPane_1.setViewportView(listSelectedUsers);

		System.out.println(selectedUsers);
	}

	public void showSelect() {
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
		var dao = new UserDao();
		Integer id = Login.getId();
		Boolean currentUserIsAdmin = Login.getIsAdmin();
		System.out.println(currentUserIsAdmin);
		Set<String> addedEmails = new HashSet<>();
		List<UsersOtherId> renterInfo = dao.selAllUserOtherId(id);
		for (UsersOtherId renter : renterInfo) {
			String email = renter.getReceiver_email();
			var renterIsAdmin = renter.isRole();
			System.out.println(renterIsAdmin);
			if (!currentUserIsAdmin && renterIsAdmin && !addedEmails.contains(email)) {
				model.addElement(email);
				addedEmails.add(email);
				// Lưu ánh xạ từ email đến to_user
				emailToUserMap.put(email, renter.getId());
			} else if (currentUserIsAdmin) {
				// Nếu người dùng hiện tại là admin, thêm mọi người
				if (!addedEmails.contains(email)) {
					model.addElement(email);
					addedEmails.add(email);
					// Lưu ánh xạ từ email đến to_user
					emailToUserMap.put(email, renter.getId());
				}
			}

		}
		comboBox.setModel(model); // Đặt model cho JComboBox
	}

	// Thêm người nhận được chọn vào danh sách
	private void addSelectedUser() {
		String selectedUser = (String) comboBox.getSelectedItem();
		if (selectedUser != null && !selectedUsers.contains(selectedUser)) {
			selectedUsers.add(selectedUser);
			selectedUsersModel.addElement(selectedUser); // Thêm người nhận vào model của JList
		}
	}

	// Chọn tất cả người dùng
	private void selectAllUsers() {
		DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) comboBox.getModel();
		for (int i = 0; i < model.getSize(); i++) {
			String user = model.getElementAt(i);
			if (!selectedUsers.contains(user)) {
				selectedUsers.add(user);
				selectedUsersModel.addElement(user); // Thêm người nhận vào model của JList
			}
		}
	}

	private void removeSelectedUser() {
		int selectedIndex = listSelectedUsers.getSelectedIndex();
		if (selectedIndex != -1) {
			String removedUser = selectedUsersModel.remove(selectedIndex);
			selectedUsers.remove(removedUser);
		}
	}

	// btn send
	protected void btnNewButtonActionPerformed(ActionEvent e) {
		NotiDao notiDao = new NotiDao();
		var dao = new UserDao();
		var id = Login.getId();

		if (selectedUsers.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please select user receiver", "Warring", JOptionPane.ERROR_MESSAGE);
		} else if (textArea.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Message content is not be empty", "Warring",
					JOptionPane.ERROR_MESSAGE);
		} else {
			for (String email : selectedUsers) {
				// Lấy to_user sử dụng email từ map
				Integer toUser = emailToUserMap.get(email);
				notiDao.insertNoti(id, toUser, textArea.getText(), true);
			}
			textArea.setText("");
			JOptionPane.showMessageDialog(null, "Send success", "Success", JOptionPane.INFORMATION_MESSAGE);
		}

	}

	protected void btnNewButton_1ActionPerformed(ActionEvent e) {
		addSelectedUser();
	}

	protected void btnNewButton_2ActionPerformed(ActionEvent e) {
		selectAllUsers();
	}

	protected void btnNewButton_3ActionPerformed(ActionEvent e) {
		removeSelectedUser();
	}
}
