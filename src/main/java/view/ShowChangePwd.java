package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import component.Login;
import dao.UserDao;
import entity.Users;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.Font;

public class ShowChangePwd extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField passwordField;
	private JLabel lblNewLabel;
	private JLabel lblPasswordComfin;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;
	private JLabel lblConfirmPassword;
	private JCheckBox chckbxShowPass;
	private JButton btnChangePwd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowChangePwd frame = new ShowChangePwd();
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
	public ShowChangePwd() {
		setTitle("change password");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 522, 368);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		{
			passwordField = new JPasswordField();
			passwordField.setEchoChar('*');
			passwordField.setEditable(false);
			passwordField.setBounds(178, 41, 225, 31);
			contentPane.add(passwordField);
		}
		{
			lblNewLabel = new JLabel("Old password:");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel.setBounds(28, 52, 140, 14);
			contentPane.add(lblNewLabel);
		}
		{
			lblPasswordComfin = new JLabel(" New password:");
			lblPasswordComfin.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblPasswordComfin.setBounds(28, 100, 140, 14);
			contentPane.add(lblPasswordComfin);
		}
		{
			passwordField_1 = new JPasswordField();
			passwordField_1.setEchoChar('*');
			passwordField_1.setBounds(178, 93, 225, 31);
			contentPane.add(passwordField_1);
		}
		{
			passwordField_2 = new JPasswordField();
			passwordField_2.setEchoChar('*');
			passwordField_2.setBounds(178, 145, 225, 31);
			contentPane.add(passwordField_2);
		}
		{
			lblConfirmPassword = new JLabel("Confirm password:");
			lblConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblConfirmPassword.setBounds(28, 156, 140, 14);
			contentPane.add(lblConfirmPassword);
		}
		{
			chckbxShowPass = new JCheckBox("Show pass");
			chckbxShowPass.setFont(new Font("Tahoma", Font.PLAIN, 12));

			chckbxShowPass.setBounds(178, 193, 108, 31);
			contentPane.add(chckbxShowPass);
		}
		{
			btnChangePwd = new JButton("Change Password");
			btnChangePwd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnChangePwdActionPerformed(e);
				}
			});
			btnChangePwd.setForeground(Color.BLUE);
			btnChangePwd.setBounds(190, 243, 174, 40);
			contentPane.add(btnChangePwd);
		}
		chckbxShowPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Handle the checkbox state change event
				if (chckbxShowPass.isSelected()) {
					passwordField.setEchoChar((char) 0);
					passwordField_2.setEchoChar((char) 0);
					passwordField_1.setEchoChar((char) 0);
				} else {
					passwordField.setEchoChar('*');
					passwordField_1.setEchoChar('*');
					passwordField_2.setEchoChar('*');
				}
			}
		});
		UserDao user = new UserDao();
		Integer userId = Login.getId();
		Users info = user.selUser(userId);
		if (info != null) {
			passwordField.setText(info.getPw() != null ? info.getPw() : "");
		}

	}

	@SuppressWarnings("deprecation")
	protected void btnChangePwdActionPerformed(ActionEvent e) {
		UserDao user = new UserDao();
		Integer userId = Login.getId();
		Users info = user.selUser(userId);
		String newPassword = passwordField_1.getText().trim();
	    String confirmPassword = passwordField_2.getText().trim();
	    
	    if (newPassword.equals(confirmPassword)) {
	        if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
	            JOptionPane.showMessageDialog(null, "The new password or confirmation password field cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
	        } else if (newPassword.contains(" ")) {
	            JOptionPane.showMessageDialog(null, "The new password cannot contain spaces", "Error", JOptionPane.ERROR_MESSAGE);
	        } else {
	            // Update info
	            Users info1 = user.updateInforUser(userId, null, null, null,
	                    null, null, newPassword, null, null,
	                    null, null, null);
	            JOptionPane.showMessageDialog(null, "Update successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
	            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
	            frame.dispose();
	        }
	    } else {
	        JOptionPane.showMessageDialog(null, "The reconfirmation password does not match the new password", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}

}
