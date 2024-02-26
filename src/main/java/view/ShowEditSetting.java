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
import regex.Regex;
import helper.Regex1;
import regex.Valid;

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

public class ShowEditSetting extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JLabel lblFullName;
	private JTextField txtFullName;
	private JTextField txtAddress;
	private JLabel lblAddress;
	private JLabel lblNIC;
	private JLabel lblPhone;
	private JTextField txtPhone;
	private JLabel lblDob;
	private JLabel lblEmail;
	private JTextField txtEmail;
	private JLabel lblGender;
	private JRadioButton rdbtnMale;
	private JRadioButton rdbtnFemale;
	private JTextField txtNIC;
	private JButton btnNewButton;
	private JLabel lblNewLabel;
	private JLabel lblAvatar;
	private JButton btnNewButton_1;
	private JLabel lblImgauthority;
	private JButton btnNewButton_2;
	private JLabel lblIauthority;
	private JTextField txtiAuthority;
	private JDateChooser dateDob;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
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
	private JLabel lblErrPhone;
	private JLabel lblErrEmail;
	private JLabel lblErrNIC;
	private JLabel lblErrDob;

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
					ShowEditSetting frame = new ShowEditSetting();
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
	public ShowEditSetting() {
		setTitle("Edit info");
		setBounds(300, 150, 600, 763);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// create new Jpanel
		JPanel panelSub = new JPanel();
		panelSub.setBackground(Color.WHITE);
		panelSub.setBounds(0, 0, 600, 700);

		// Thêm JPanel vào JFrame
		getContentPane().add(panelSub);

		setVisible(true);
		{
			lblFullName = new JLabel("FullName:");
			lblFullName.setForeground(new Color(128, 128, 128));
			lblFullName.setHorizontalAlignment(SwingConstants.LEFT);
			lblFullName.setFont(new Font("Arial", Font.PLAIN, 12));
			lblFullName.setBounds(28, 61, 57, 14);
		}
		{
			txtFullName = new JTextField();
			txtFullName.setBounds(148, 53, 394, 31);
			txtFullName.setColumns(10);
		}
		{
			txtAddress = new JTextField();
			txtAddress.setColumns(10);
			txtAddress.setBounds(148, 93, 394, 31);
		}
		{
			lblAddress = new JLabel("Address:");
			lblAddress.setForeground(new Color(128, 128, 128));
			lblAddress.setHorizontalAlignment(SwingConstants.LEFT);
			lblAddress.setFont(new Font("Arial", Font.PLAIN, 12));
			lblAddress.setBounds(28, 101, 49, 14);
		}
		{
			lblNIC = new JLabel("NIC:");
			lblNIC.setForeground(new Color(128, 128, 128));
			lblNIC.setHorizontalAlignment(SwingConstants.LEFT);
			lblNIC.setFont(new Font("Arial", Font.PLAIN, 12));
			lblNIC.setBounds(28, 550, 24, 14);
		}
		{
			JTextField textField_3 = new JTextField();
			textField_3.setColumns(10);
			textField_3.setBounds(533, 206, 383, 31);
		}
		{
			lblPhone = new JLabel("Phone:");
			lblPhone.setForeground(new Color(128, 128, 128));
			lblPhone.setHorizontalAlignment(SwingConstants.LEFT);
			lblPhone.setFont(new Font("Arial", Font.PLAIN, 12));
			lblPhone.setBounds(28, 143, 39, 14);
		}
		{
			txtPhone = new JTextField();
			txtPhone.setColumns(10);
			txtPhone.setBounds(148, 135, 394, 31);
		}
		{
			lblDob = new JLabel("Dob:");
			lblDob.setForeground(new Color(128, 128, 128));
			lblDob.setHorizontalAlignment(SwingConstants.LEFT);
			lblDob.setFont(new Font("Arial", Font.PLAIN, 12));
			lblDob.setBounds(28, 191, 26, 14);
		}
		{
			lblEmail = new JLabel("Email:");
			lblEmail.setForeground(new Color(128, 128, 128));
			lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
			lblEmail.setFont(new Font("Arial", Font.PLAIN, 12));
			lblEmail.setBounds(28, 287, 35, 14);
		}
		{
			txtEmail = new JTextField();
			txtEmail.addKeyListener(new KeyAdapter() {
			});
			txtEmail.setColumns(10);
			txtEmail.setBounds(148, 284, 394, 31);
		}
		{
			lblGender = new JLabel("Gender:");
			lblGender.setForeground(new Color(128, 128, 128));
			lblGender.setHorizontalAlignment(SwingConstants.LEFT);
			lblGender.setFont(new Font("Arial", Font.PLAIN, 12));
			lblGender.setBounds(28, 253, 44, 14);
		}
		{
			rdbtnMale = new JRadioButton("Male");
			rdbtnMale.setFont(new Font("Arial", Font.BOLD, 12));
			buttonGroup.add(rdbtnMale);
			rdbtnMale.setBackground(new Color(255, 255, 255));
			rdbtnMale.setBounds(148, 249, 70, 23);
		}
		{
			rdbtnFemale = new JRadioButton("Female");
			rdbtnFemale.setFont(new Font("Arial", Font.BOLD, 12));
			buttonGroup.add(rdbtnFemale);
			rdbtnFemale.setBackground(Color.WHITE);
			rdbtnFemale.setBounds(220, 249, 89, 23);
		}
		panelSub.setLayout(null);
		panelSub.add(lblFullName);
		panelSub.add(txtFullName);
		panelSub.add(txtAddress);
		panelSub.add(lblAddress);
		panelSub.add(lblNIC);
		panelSub.add(lblPhone);
		panelSub.add(txtPhone);
		panelSub.add(lblDob);
		panelSub.add(lblEmail);
		panelSub.add(txtEmail);
		panelSub.add(lblGender);
		panelSub.add(rdbtnMale);
		panelSub.add(rdbtnFemale);
		{
			txtNIC = new JTextField();
			txtNIC.setBounds(148, 542, 394, 31);
			panelSub.add(txtNIC);
			txtNIC.setColumns(10);
		}
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
			btnNewButton.setBounds(409, 651, 133, 37);
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
			lblAvatar.setBounds(33, 368, 59, 14);
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
			btnNewButton_1.setBounds(151, 364, 89, 23);
			panelSub.add(btnNewButton_1);
		}
		{
			lblImgauthority = new JLabel("imgAuthority:");
			lblImgauthority.setHorizontalAlignment(SwingConstants.LEFT);
			lblImgauthority.setForeground(Color.GRAY);
			lblImgauthority.setFont(new Font("Arial", Font.PLAIN, 12));
			lblImgauthority.setBounds(31, 450, 82, 14);
			panelSub.add(lblImgauthority);
		}
		{
			btnNewButton_2 = new JButton("Upload");
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnNewButton_2ActionPerformed(e);
				}
			});
			btnNewButton_2.setFont(new Font("Dialog", Font.PLAIN, 12));
			btnNewButton_2.setBackground(new Color(238, 238, 238));
			btnNewButton_2.setBounds(151, 447, 89, 23);
			panelSub.add(btnNewButton_2);
		}
		{
			lblIauthority = new JLabel("issue Authority:");
			lblIauthority.setHorizontalAlignment(SwingConstants.LEFT);
			lblIauthority.setForeground(Color.GRAY);
			lblIauthority.setFont(new Font("Arial", Font.PLAIN, 12));
			lblIauthority.setBounds(28, 619, 101, 14);
			panelSub.add(lblIauthority);
		}
		{
			txtiAuthority = new JTextField();
			txtiAuthority.setColumns(10);
			txtiAuthority.setBounds(148, 608, 394, 31);
			panelSub.add(txtiAuthority);
		}
		{
			dateDob = new JDateChooser();
			dateDob.setDateFormatString("yyyy-MM-dd");
			dateDob.setBounds(148, 189, 394, 31);
			dateDob.getJCalendar().setMaxSelectableDate(Calendar.getInstance().getTime());
			panelSub.add(dateDob);
		}
		{
			lblNewLabel_1 = new JLabel("(Up to 1 image(.png or .jpg))");
			lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 12));
			lblNewLabel_1.setForeground(new Color(192, 192, 192));
			lblNewLabel_1.setBounds(27, 395, 176, 16);
			panelSub.add(lblNewLabel_1);
		}
		{
			lblNewLabel_2 = new JLabel("(Up to 2 image(.png or .jpg))");
			lblNewLabel_2.setForeground(Color.LIGHT_GRAY);
			lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 12));
			lblNewLabel_2.setBounds(28, 484, 176, 16);
			panelSub.add(lblNewLabel_2);
		}
		{
			lblAvatarImg = new JLabel("");
			lblAvatarImg.setBackground(Color.ORANGE);
			lblAvatarImg.setBounds(272, 345, 89, 76);
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
			lblErrPhone = new JLabel("Error! Only contains numbers");
			lblErrPhone.setVisible(false);
			lblErrPhone.setForeground(new Color(255, 0, 0));
			lblErrPhone.setFont(new Font("Arial", Font.PLAIN, 12));
			lblErrPhone.setBounds(148, 167, 176, 16);
			panelSub.add(lblErrPhone);
		}
		{
			lblErrEmail = new JLabel("Error! Email syntax is incorrect");
			lblErrEmail.setVisible(false);
			lblErrEmail.setForeground(Color.RED);
			lblErrEmail.setFont(new Font("Arial", Font.PLAIN, 12));
			lblErrEmail.setBounds(148, 318, 320, 16);
			panelSub.add(lblErrEmail);
		}
		{
			lblErrNIC = new JLabel("Error! Only contains numbers");
			lblErrNIC.setVisible(false);
			lblErrNIC.setForeground(Color.RED);
			lblErrNIC.setFont(new Font("Arial", Font.PLAIN, 12));
			lblErrNIC.setBounds(147, 578, 176, 16);
			panelSub.add(lblErrNIC);
		}
		{
			lblErrDob = new JLabel("Error! Dob fomat is incorrect (yyyy-MM-dd)");
			lblErrDob.setVisible(false);
			lblErrDob.setLabelFor(lblErrPhone);
			lblErrDob.setForeground(Color.RED);
			lblErrDob.setFont(new Font("Arial", Font.PLAIN, 12));
			lblErrDob.setBounds(148, 225, 320, 16);
			panelSub.add(lblErrDob);
		}

		// --------------Show info -----------------------------------
		UserDao user = new UserDao();
		Integer userId = Login.getId();
		Users info = user.selUser(userId);

		if (info != null) {
			txtFullName.setText(info.getName() != null ? info.getName() : "");
			txtAddress.setText(info.getAddress() != null ? info.getAddress() : "");
			txtNIC.setText(info.getNic() != null ? info.getNic() : "");
			txtPhone.setText(info.getPhone() != null ? info.getPhone() : "");
			txtEmail.setText(info.getEmail() != null ? info.getEmail() : "");
			txtiAuthority.setText(info.getiAuthority() != null ? info.getiAuthority() : "");
			if (info.getDob() != null) {
				dateDob.setDate(info.getDob());
			} else {
				dateDob.setDate(null);
			}
			String gender = info.getGender();
			if (gender != null) {
				if (gender.equals("female")) {
					rdbtnFemale.setSelected(true);
				} else {
					rdbtnMale.setSelected(true);
				}
			} else {
				rdbtnMale.setSelected(false);
				rdbtnFemale.setSelected(false);
			}

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
					if (!Regex1.isValidEmail(currentValue)) {
						lblErrEmail.setVisible(true);
						isEmail = false;
					} else {
						lblErrEmail.setVisible(false);
						isEmail = true;
					}
					
				}

			});
			txtPhone.getDocument().addDocumentListener((javax.swing.event.DocumentListener) new DocumentListener() {
				private String previousValue = txtPhone.getText();

				private void valueChanged() {
					String currentValue = txtPhone.getText();

					boolean isValueChanged = !currentValue.equals(previousValue);
					previousValue = currentValue;
					System.out.println(currentValue);
					if (!Regex1.isValidNumber(currentValue)) {
						lblErrPhone.setVisible(true);
						isPhone = false;
					} else {
						lblErrPhone.setVisible(false);
						isPhone = true;
					}

				}

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

			});
			txtNIC.getDocument().addDocumentListener((javax.swing.event.DocumentListener) new DocumentListener() {
				private String previousValue = txtNIC.getText();

				private void valueChanged() {
					String currentValue = txtNIC.getText();
					boolean isValueChanged = !currentValue.equals(previousValue);
					previousValue = currentValue;
					System.out.println("nic" + isNic);
					if (!Regex1.isValidNumber(currentValue)) {
						lblErrNIC.setVisible(true);
						isNic = false;
					} else {
						isNic = true;
						lblErrNIC.setVisible(false);

					}

				}

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

			});

			// ---------------------------------------------------
			if (info.getAvatar() == null || info.getAvatar().equals("") ) {
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

					ImageIcon icon1 = new ImageIcon(img2);
					ImageIcon icon2 = new ImageIcon(img1);

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
			txtFullName.setText("");
			txtAddress.setText("");
			txtNIC.setText("");
			txtPhone.setText("");
			dateDob.setDate(null);
			txtEmail.setText("");
		}

		rdbtnMale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gender = "male";
			}
		});

		rdbtnFemale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gender = "female";
			}
		});

	}

	protected void btnNewButtonActionPerformed(ActionEvent e) {
		// Update btn
		UserDao user = new UserDao();
		Integer userId = Login.getId();
	
		Users infor = user.selUser(userId);
		int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure to update the information?", "Confirm",
				JOptionPane.YES_NO_OPTION);
		java.util.Date utilDate = dateDob.getDate();
		java.sql.Date sqlDate = null;
		if (utilDate != null) {
			Date currentDate = new Date();
			// check date of birth is later than the current date
			if (utilDate.after(currentDate)) {
				utilDate = currentDate;
				JOptionPane.showMessageDialog(null, "The chosen date of birth is later than the current date.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}

			 sqlDate = new java.sql.Date(utilDate.getTime());
			// Proceed with the rest of your code using sqlDate
		} 
		try {

			if (confirmation == JOptionPane.YES_OPTION) {
				// update info
				String email = txtEmail.getText();
				
				String error = Valid.validateInputWithNOEmpty(email, Regex.EMAIL_REGEX, "Email ", " Please enter in the format: example@gmail.com");
				if(error!=null) {
					JOptionPane.showMessageDialog(null, error, "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;
				}
				var rs = user.emailExist(email);
				if(email.equals(infor.getEmail())) {
					Users info = user.updateInforUser(userId, txtAddress.getText(), txtPhone.getText(), gender,
							newAvatarPath, txtFullName.getText(), null, txtEmail.getText(), imgNicPathsString,
							txtiAuthority.getText(), txtNIC.getText(), sqlDate);
					JOptionPane.showMessageDialog(null, "Update successfull!", "Success", JOptionPane.INFORMATION_MESSAGE);
					JFrame frame = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
					frame.dispose();
				}else if (!rs.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Email exist already", "Error", JOptionPane.ERROR_MESSAGE);
				}else if(email.trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Email not be empty", "Error", JOptionPane.ERROR_MESSAGE);
				} else if(email.contains(" ")) {
					JOptionPane.showMessageDialog(null, "Email not be spaces", "Error", JOptionPane.ERROR_MESSAGE);
				}else{
					Users info = user.updateInforUser(userId, txtAddress.getText(), txtPhone.getText(), gender,
							newAvatarPath, txtFullName.getText(), null, txtEmail.getText(), imgNicPathsString,
							txtiAuthority.getText(), txtNIC.getText(), sqlDate);
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
		int result = fileAvatarChooser.showOpenDialog(ShowEditSetting.this);
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

	// upload multi imgAuthority
	protected void btnNewButton_2ActionPerformed(ActionEvent e) {
		JFileChooser fileAvatarChooser = new JFileChooser();
		fileAvatarChooser.setCurrentDirectory(new File("C:\\Users"));
		fileAvatarChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileAvatarChooser.setMultiSelectionEnabled(true);

		if (lastSelectedFiles != null) {
			fileAvatarChooser.setSelectedFiles(lastSelectedFiles);
		}

		int result = fileAvatarChooser.showOpenDialog(ShowEditSetting.this);
		if (result == JFileChooser.APPROVE_OPTION) {
			File[] selectedFiles = fileAvatarChooser.getSelectedFiles();
			if (selectedFiles != null && selectedFiles.length > 0) {
				if (selectedFiles.length == 2) {
					lastSelectedFiles = selectedFiles;

					File file1 = selectedFiles[0];
					File file2 = selectedFiles.length >= 2 ? selectedFiles[1] : null;

					displayImageOnLabel(file1, lblFront);
					displayImageOnLabel(file2, lblBack);
				} else {
					JOptionPane.showMessageDialog(null, "Please select up to 2 images", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		} else if (result == JFileChooser.CANCEL_OPTION) {
		}
	}

	private void displayImageOnLabel(File file, JLabel label) {
		String getFileName = file.getName();
		String extension = getFileName.substring(getFileName.lastIndexOf(".") + 1);

		if (extension.equalsIgnoreCase("png") || extension.equalsIgnoreCase("jpg")) {
			String filePath = file.getAbsolutePath();
			String newAvatarFilePath = "NIC_" + System.currentTimeMillis() + "." + extension;

			String imagesFolderPath = "images/";
			String newAvatarPath = imagesFolderPath + newAvatarFilePath;

			ImageIcon originAvatarIcon = new ImageIcon(filePath);
			Image imgAvatar = originAvatarIcon.getImage().getScaledInstance(130, 90, Image.SCALE_SMOOTH);
			ImageIcon imgAvatarParse = new ImageIcon(imgAvatar);
			label.setIcon(imgAvatarParse);

			try {
				Files.copy(new File(filePath).toPath(), new File(newAvatarPath).toPath(),
						StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
			}

			imgNicPaths.add(newAvatarPath);
			System.out.println(imgNicPaths);
			imgNicPathsString = String.join(";", imgNicPaths);
			System.out.println(imgNicPathsString);
		}

	}
	private void addMouseListenerImg(JLabel label, ImageIcon img) {
		label.addMouseListener((MouseListener) new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JDialog dialog = new JDialog();
				JLabel imgLabel = new JLabel(new ImageIcon(img.getImage().getScaledInstance(600, 600, Image.SCALE_SMOOTH)));
				dialog.getContentPane().add(imgLabel);
				dialog.pack();
				dialog.setVisible(true);
				dialog.setLocationRelativeTo(null);
				super.mouseClicked(e);
			}
		});
	}
}
