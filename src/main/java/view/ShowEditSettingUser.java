package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import component.Login;
import dao.UserDao;
import entity.Users;

import helper.Regex;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Cursor;

public class ShowEditSettingUser extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JLabel lblEmail;
	private JTextField txtEmail;
	private JButton btnNewButton;
	private JLabel lblNewLabel;
	private JLabel lblAvatar;
	private JButton btnNewButton_1;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblNewLabel_1;
	private JLabel lblAvatarImg;
	private JLabel lblFront;
	private JLabel lblBack;
	private String gender;
	private File lastSelectedFile;
	private File[] lastSelectedFiles;
	private String getFileName;
	private String newAvatarPath;
	private String newAvatarPath1;
	private List<String> imgNicPaths = new ArrayList<>();
	private String imgNicPathsString;
	private JLabel lblErrEmail;

	private boolean isEmail = true;
	private boolean isPhone = true;
	private boolean isNic = true;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowEditSettingUser frame = new ShowEditSettingUser();
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
	public ShowEditSettingUser() {
		setTitle("Edit info renter");
		setBounds(300, 150, 600, 344);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// create new Jpanel
		JPanel panelSub = new JPanel();
		panelSub.setBackground(Color.WHITE);
		panelSub.setBounds(0, 0, 600, 700);

		// Thêm JPanel vào JFrame
		getContentPane().add(panelSub);

		setVisible(true);
		{
			JTextField textField_3 = new JTextField();
			textField_3.setColumns(10);
			textField_3.setBounds(533, 206, 383, 31);
		}
		{
			lblEmail = new JLabel("Email:");
			lblEmail.setForeground(new Color(128, 128, 128));
			lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
			lblEmail.setFont(new Font("Arial", Font.PLAIN, 12));
			lblEmail.setBounds(22, 89, 35, 14);
		}
		{
			txtEmail = new JTextField();
			txtEmail.addKeyListener(new KeyAdapter() {
			});
			txtEmail.setColumns(10);
			txtEmail.setBounds(142, 86, 394, 31);
		}
		panelSub.setLayout(null);
		panelSub.add(lblEmail);
		panelSub.add(txtEmail);
		{
			btnNewButton = new JButton("Update");
			btnNewButton.setVisible(true);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnNewButtonActionPerformed(e);
				}
			});
			btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
			btnNewButton.setBackground(new Color(0, 128, 0));
			btnNewButton.setForeground(new Color(255, 255, 255));
			btnNewButton.setBounds(403, 232, 133, 37);
			panelSub.add(btnNewButton);
		}
		{
			lblNewLabel = new JLabel("Edit Information");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblNewLabel.setBounds(22, 11, 190, 31);
			panelSub.add(lblNewLabel);
		}
		{
			lblAvatar = new JLabel("Avatar:");
			lblAvatar.setHorizontalAlignment(SwingConstants.LEFT);
			lblAvatar.setForeground(Color.GRAY);
			lblAvatar.setFont(new Font("Arial", Font.PLAIN, 12));
			lblAvatar.setBounds(28, 180, 59, 14);
			panelSub.add(lblAvatar);
		}
		{
			btnNewButton_1 = new JButton("Upload");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnNewButton_1ActionPerformed(e);
				}
			});
			btnNewButton_1.setFont(new Font("Dialog", Font.PLAIN, 12));
			btnNewButton_1.setBackground(new Color(238, 238, 238));
			btnNewButton_1.setBounds(146, 176, 89, 23);
			panelSub.add(btnNewButton_1);
		}
		{
			lblNewLabel_1 = new JLabel("(Up to 1 image(.png or .jpg))");
			lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 12));
			lblNewLabel_1.setForeground(new Color(192, 192, 192));
			lblNewLabel_1.setBounds(22, 207, 176, 16);
			panelSub.add(lblNewLabel_1);
		}
		{
			lblAvatarImg = new JLabel("");
			lblAvatarImg.setBackground(Color.ORANGE);
			lblAvatarImg.setBounds(266, 160, 89, 76);
			panelSub.add(lblAvatarImg);
		}
		{
			lblFront = new JLabel("");
			lblFront.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			lblFront.setBackground(Color.ORANGE);
			lblFront.setBounds(250, 432, 138, 86);
			panelSub.add(lblFront);
		}
		{
			lblBack = new JLabel("");
			lblBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			lblBack.setBackground(Color.ORANGE);
			lblBack.setBounds(398, 432, 138, 86);
			panelSub.add(lblBack);
		}
		{
			lblErrEmail = new JLabel("Error! Email syntax is incorrect");
			lblErrEmail.setVisible(false);
			lblErrEmail.setForeground(Color.RED);
			lblErrEmail.setFont(new Font("Arial", Font.PLAIN, 12));
			lblErrEmail.setBounds(142, 120, 320, 16);
			panelSub.add(lblErrEmail);
		}

		// --------------Show info -----------------------------------
		UserDao user = new UserDao();
		Integer userId = Login.getId();
		Users info = user.selUser(userId);

		if (info != null) {

			txtEmail.setText(info.getEmail() != null ? info.getEmail() : "");

			// -----------------------isValid--------------------------------------------//
			txtEmail.getDocument().addDocumentListener((javax.swing.event.DocumentListener) new DocumentListener() {
				private String previousValue = txtEmail.getText();

				@Override
				public void insertUpdate(DocumentEvent e) {
					// TODO Auto-generated method stub
					valueChanged();
				}

				@Override
				public void removeUpdate(DocumentEvent e) {
					// TODO Auto-generated method stub
					valueChanged();
				}

				@Override
				public void changedUpdate(DocumentEvent e) {
					// TODO Auto-generated method stub
					valueChanged();
				}

				public void valueChanged() {
					String currentValue = txtEmail.getText();
					boolean isValueChanged = !currentValue.equals(previousValue);
					previousValue = currentValue;
					System.out.println(currentValue);
					System.out.println("Email: " + isEmail);
					if (!Regex.isValidEmail(currentValue)) {
						lblErrEmail.setVisible(true);
						isEmail = false;
					} else {
						lblErrEmail.setVisible(false);
						isEmail = true;
					}
				}

			});

			// ---------------------------------------------------
			if (info.getAvatar() == null || info.getAvatar().equals("")) {
				ImageIcon originAvatarIcon = new ImageIcon("images/avatarDefaut.jpg");
				Image imgAvatar = originAvatarIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
				// Load the image
				ImageIcon imageIcon = new ImageIcon(imgAvatar);

				// Set the image icon on the label
				lblAvatarImg.setIcon(imageIcon);
			} else {
				ImageIcon originAvatarIcon = new ImageIcon(info.getAvatar());
				Image imgAvatar = originAvatarIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
				// Load the image
				ImageIcon imageIcon = new ImageIcon(imgAvatar);

				// Set the image icon on the label
				lblAvatarImg.setIcon(imageIcon);
			}
			String imgNicPaths = info.getImgIAuthority();

			if (imgNicPaths != null) {
				String[] nicPathArray = imgNicPaths.split(";");
				if (nicPathArray.length >= 2) {

					String img1 = nicPathArray[0];
					String img2 = nicPathArray[1];

					ImageIcon icon1 = new ImageIcon(img1);
					ImageIcon icon2 = new ImageIcon(img2);

					Image scaledImage1 = icon1.getImage().getScaledInstance(130, 90, Image.SCALE_SMOOTH);
					Image scaledImage2 = icon2.getImage().getScaledInstance(130, 90, Image.SCALE_SMOOTH);

					ImageIcon scaledIcon1 = new ImageIcon(scaledImage1);
					ImageIcon scaledIcon2 = new ImageIcon(scaledImage2);

					lblFront.setIcon(scaledIcon1);
					lblBack.setIcon(scaledIcon2);
					addMouseListenerImg(lblFront, icon1);
					addMouseListenerImg(lblBack, icon2);
				}
			}

		} else {
			// Set all text fields to display "null"

			txtEmail.setText("");
		}

	}

	protected void btnNewButtonActionPerformed(ActionEvent e) {
		// Update btn
		UserDao user = new UserDao();
		Integer userId = Login.getId();
		Users infor = user.selUser(userId);
	
		int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure to update the information?", "Confirm",
				JOptionPane.YES_NO_OPTION);

		try {

			if (confirmation == JOptionPane.YES_OPTION) {
				// update info
				var rs = user.emailExist(txtEmail.getText());
				String email = txtEmail.getText();
				if (txtEmail.getText().equals(infor.getEmail())) {
					Users info = user.updateInforUser(userId, null, null, null, newAvatarPath, null, null,
							txtEmail.getText(), null, null, null, null);
					JOptionPane.showMessageDialog(null, "Update successfull!", "Success",
							JOptionPane.INFORMATION_MESSAGE);
					JFrame frame = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
					frame.dispose();
				} else if (!rs.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Email exist", "Error", JOptionPane.ERROR_MESSAGE);
				} else if (email.trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Email not be empty", "Error", JOptionPane.ERROR_MESSAGE);
				} else if (email.contains(" ")) {
					JOptionPane.showMessageDialog(null, "Email not be spaces", "Error", JOptionPane.ERROR_MESSAGE);
				}else {
					Users info = user.updateInforUser(userId, null, null, null, newAvatarPath, null, null,
							txtEmail.getText(), null, null, null, null);
					JOptionPane.showMessageDialog(null, "Update successfull!", "Success",
							JOptionPane.INFORMATION_MESSAGE);
					JFrame frame = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
					frame.dispose();
				}

			} else {
				JOptionPane.showMessageDialog(null, "Update Failed!", "Error", JOptionPane.ERROR_MESSAGE);
			}

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Update Failed!", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	// upload img Avatar
	protected void btnNewButton_1ActionPerformed(ActionEvent e) {
		JFileChooser fileAvatarChooser = new JFileChooser();
		fileAvatarChooser.setCurrentDirectory(new File("C:\\Users"));
		if (lastSelectedFile != null) {
			fileAvatarChooser.setSelectedFile(lastSelectedFile);
		}
		int result = fileAvatarChooser.showOpenDialog(ShowEditSettingUser.this);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileAvatarChooser.getSelectedFile();
			if (selectedFile != null) {
				lastSelectedFile = selectedFile;
			}

			getFileName = selectedFile.getName();
			String extension = getFileName.substring(getFileName.lastIndexOf(".") + 1);
			if (extension.equalsIgnoreCase("png") || extension.equalsIgnoreCase("jpg")) {
				String filePath = selectedFile.getAbsolutePath(); // Retrieve the absolute path of
																	// the selected file
				String newAvatarFilePath = "imgAuth_" + System.currentTimeMillis() + "." + extension; // Create a new
																										// file name for
																										// the avatar

				String imagesFolderPath = "images/"; // Path to the "images" directory in the project.
				// "Path to the new file in the "images" folder."
				newAvatarPath = imagesFolderPath + newAvatarFilePath;

				ImageIcon originAvatarIcon = new ImageIcon(filePath);
				Image imgAvatar = originAvatarIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
				ImageIcon imgAvatarParse = new ImageIcon(imgAvatar);
				lblAvatarImg.setIcon(imgAvatarParse);

				// Copy the avatar file to the "images" folder.
				try {
					Files.copy(new File(filePath).toPath(), new File(newAvatarPath).toPath(),
							StandardCopyOption.REPLACE_EXISTING);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}

	}

	private void addMouseListenerImg(JLabel label, ImageIcon img) {
		label.addMouseListener((MouseListener) new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JDialog dialog = new JDialog();
				JLabel imgLabel = new JLabel(
						new ImageIcon(img.getImage().getScaledInstance(600, 600, Image.SCALE_SMOOTH)));
				dialog.getContentPane().add(imgLabel);
				dialog.pack();
				dialog.setVisible(true);
				dialog.setLocationRelativeTo(null);
				super.mouseClicked(e);
			}
		});
	}
}
