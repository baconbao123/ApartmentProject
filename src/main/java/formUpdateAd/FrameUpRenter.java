package formUpdateAd;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import dao.UserDao;
import entity.Users;
import event.EventTableRenterAction;
import formAdmin.RenterList;
import regex.Regex;
import regex.Valid;

import javax.swing.JRadioButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.awt.event.ActionEvent;

public class FrameUpRenter extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblAvatar;
	private JLabel lblQuantityAvatar;
	private JButton btnUpAvatar;
	private JLabel lblImgAvatar;
	private JLabel lblFullname;
	private JTextField txtFullName;
	private JTextField txtAddress;
	private JLabel lblAddress;
	private JLabel lblPhone;
	private JTextField txtPhone;
	private JLabel lblDob;
	private JDateChooser dateDob;
	private JLabel lblGender;
	private JRadioButton rdMale;
	private JRadioButton rdFemale;
	private JLabel lblCIC;
	private JTextField txtCIC;
	private JLabel lblIAuthority;
	private JTextField txtIssAu;
	private JLabel lblQuantityImgCIC;
	private JButton btnUpCIC;
	private JLabel lblImgCIC1;
	private JLabel lblImgCIC2;
	private JButton btnSave;
	private JLabel lblUpdateRenter;
	private JLabel lblWarning;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	// avatr
	private File lastSelectedAvatar;
	private String avatarPathAb;
	private String avatarPath, avatarImg;

	// cic
	private List<String> cicPaths = new ArrayList<>();
	private List<String> cicImgs = new ArrayList<>();

	// gender
	final String[] genderFinal = { "" };

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameUpRenter frame = new FrameUpRenter();
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
	public FrameUpRenter() {
	};

	public FrameUpRenter(String id, String fullname, Date dob) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 510, 585);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		lblAvatar = new JLabel("New Avatar");
		btnUpAvatar = new JButton("Upload");
		btnUpAvatar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnUpAvatarActionPerformed(e);
			}
		});

		lblUpdateRenter = new JLabel("Update Renter Information for Mr/Ms " + fullname);

		lblImgAvatar = new JLabel("");

		txtFullName = new JTextField();
		txtAddress = new JTextField();
		txtPhone = new JTextField();
		dateDob = new JDateChooser();
		dateDob.setDateFormatString("yyyy-MM-dd");
		dateDob.setDate(dob);
		dateDob.getJCalendar().setMaxSelectableDate(Calendar.getInstance().getTime()); // Allow selecting dates from today onwards


		
		rdMale = new JRadioButton("Male");
		buttonGroup.add(rdMale);
		rdMale.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				genderFinal[0] = "Male";
			}
		});

		rdFemale = new JRadioButton("Female");
		buttonGroup.add(rdFemale);
		rdFemale.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				genderFinal[0] = "Female";
			}
		});

		txtCIC = new JTextField();
		txtIssAu = new JTextField();

		btnUpCIC = new JButton("Upload");
		btnUpCIC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnUpCICActionPerformed(e);
			}
		});
		lblImgCIC1 = new JLabel("");
		lblImgCIC2 = new JLabel("");

		Integer idInt = Integer.parseInt(id);

		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var renter = new Users();
				var dao = new UserDao();

				

				String imgNICPathSaveDB = String.join(";", cicImgs);

				// regex
				String errorMessage = "";
				errorMessage = Valid.validateInputWithEmpty(txtFullName.getText(), Regex.CHAR, "Fullname ",
						"Invalid. Please input letter only");
				if (errorMessage != null) {
					JOptionPane.showMessageDialog(null, errorMessage, "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;
				}

				errorMessage = Valid.validateInputWithEmpty(txtAddress.getText(), Regex.CHAR, "Address ",
						"Invalid. Please input letter only");
				if (errorMessage != null) {
					JOptionPane.showMessageDialog(null, errorMessage, "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;
				}

				errorMessage = Valid.validateInputWithEmpty(txtPhone.getText(), Regex.NUM, "Phone ",
						"Invalid. Please enter only numbers");
				if (errorMessage != null) {
					JOptionPane.showMessageDialog(null, errorMessage, "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;
				}

				errorMessage = Valid.validateInputWithEmpty(txtCIC.getText(), Regex.NUM, "National ID Card ",
						"Invalid. Please enter only numbers");
				if (errorMessage != null) {
					JOptionPane.showMessageDialog(null, errorMessage, "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;
				}

				errorMessage = Valid.validateInputWithEmpty(txtIssAu.getText(), Regex.CHAR, "ID card issuing place ",
						"Invalid. Please input letter only");
				if (errorMessage != null) {
					JOptionPane.showMessageDialog(null, errorMessage, "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;
				}

				
				if(dateDob.getDate() == null) {
					JOptionPane.showMessageDialog(FrameUpRenter.this, "Enter date of birth(yyyy-MM-dd)", "Invalid Input", JOptionPane.ERROR_MESSAGE);
				} else {
					java.sql.Date dobSql = new java.sql.Date(dateDob.getDate().getTime());
					
					// save
					renter.setId(idInt);
					renter.setAvatar(avatarPath);
					renter.setName(txtFullName.getText());
					renter.setDob(dobSql);
					if (rdMale.isSelected()) {
						genderFinal[0] = "Male";
					} else if (rdFemale.isSelected()) {
						genderFinal[0] = "Female";
					}
					renter.setGender(genderFinal[0]);
					renter.setAddress(txtAddress.getText());
					renter.setPhone(txtPhone.getText());
					renter.setNic(txtCIC.getText());
					renter.setiAuthority(txtIssAu.getText());
					renter.setImgIAuthority(imgNICPathSaveDB);
					dao.updateUser(renter);

					try {
						if (avatarPathAb != null) {
							Files.copy(new File(avatarPathAb).toPath(), new File(avatarPath).toPath(),
									StandardCopyOption.REPLACE_EXISTING);
						}

					} catch (Exception e2) {
						e2.printStackTrace();
					}

					for (int i = 0; i < Math.min(cicPaths.size(), 2); i++) {
						try {
							if (cicPaths != null) {
								Files.copy(new File(cicPaths.get(i)).toPath(), new File(cicImgs.get(i)).toPath(),
										StandardCopyOption.REPLACE_EXISTING);
							}
						} catch (Exception e2) {
							e2.printStackTrace();
						}
					}

					dispose();
				}
				

			}
		});

		initComponent();
	}

	protected void btnUpAvatarActionPerformed(ActionEvent e) {
		long maxSizeKB = 1024;

		var chooser = new JFileChooser();
		var filterChooser = new FileNameExtensionFilter("Image files", "png", "jpg");
		chooser.setFileFilter(filterChooser);

		chooser.setCurrentDirectory(new File("C:\\Users"));
		if (lastSelectedAvatar != null) {
			chooser.setSelectedFile(lastSelectedAvatar);
		}

		int result = chooser.showOpenDialog(FrameUpRenter.this);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = chooser.getSelectedFile();

			long fileSizeInKB = selectedFile.length() / 1024;

			if (fileSizeInKB > 1024) {
				JOptionPane.showMessageDialog(FrameUpRenter.this,
						"File is too large. Please select a file with a smaller size. " + maxSizeKB + "KB.");
			} else {
				if (selectedFile != null) {
					lastSelectedAvatar = selectedFile;
				}
				String getFileName = selectedFile.getName();
				String extension = getFileName.substring(getFileName.lastIndexOf(".") + 1);
				if (extension.equalsIgnoreCase("png") || extension.equalsIgnoreCase("jpg")) {
					avatarPathAb = selectedFile.getAbsolutePath();

					String newAvatarFilePath = "avatar_" + System.currentTimeMillis() + "." + extension;
					String imgFolderSave = "images/";

					avatarPath = imgFolderSave + newAvatarFilePath;

					ImageIcon originAvatar = new ImageIcon(avatarPathAb);
					Image avatarResize = originAvatar.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
					ImageIcon avatarShow = new ImageIcon(avatarResize);
					lblImgAvatar.setIcon(avatarShow);

					lblImgAvatar.setCursor(new Cursor(Cursor.HAND_CURSOR));
					addMouseListenerImg(lblImgAvatar, originAvatar);
				}
			}

		}
	}

	protected void btnUpCICActionPerformed(ActionEvent e) {
		var chooser = new JFileChooser();
		chooser.setMultiSelectionEnabled(true);
		var filterChooser = new FileNameExtensionFilter("Image files", "png", "jpg");
		chooser.setFileFilter(filterChooser);
		chooser.setCurrentDirectory(new File("C:\\Users"));

		int result = chooser.showOpenDialog(FrameUpRenter.this);
		if (result == JFileChooser.APPROVE_OPTION) {
			File[] selectedFiles = chooser.getSelectedFiles();

			for (int i = 0; i < Math.min(selectedFiles.length, 2); i++) {
				File file = selectedFiles[i];
				String fileName = file.getName();
				String extension = fileName.substring(fileName.lastIndexOf(".") + 1);

				String folderSave = "images/nic_" + System.currentTimeMillis() + "." + extension;
				String cicNewPath = file.getAbsolutePath();

				cicPaths.add(cicNewPath);
				cicImgs.add(folderSave);

				switch (i) {
				case 0:
					var icon = new ImageIcon(cicNewPath);
					lblImgCIC1.setIcon(new ImageIcon(icon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
					lblImgCIC1.setCursor(new Cursor(Cursor.HAND_CURSOR));
					addMouseListenerImg(lblImgCIC1, icon);
					break;

				case 1:
					var icon1 = new ImageIcon(cicNewPath);
					lblImgCIC2.setIcon(new ImageIcon(icon1.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
					lblImgCIC2.setCursor(new Cursor(Cursor.HAND_CURSOR));
					addMouseListenerImg(lblImgCIC2, icon1);
					break;
				}
			}
		}
	}

	private void addMouseListenerImg(JLabel label, ImageIcon cicNewPath) {
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				var dialog = new JDialog();
				var imgLable = new JLabel(
						new ImageIcon(cicNewPath.getImage().getScaledInstance(600, 600, Image.SCALE_SMOOTH)));
				dialog.add(imgLable);
				dialog.pack();
				dialog.setVisible(true);
				dialog.setLocationRelativeTo(null);
				super.mouseClicked(e);
			}
		});
	}

	private void initComponent() {
		lblAvatar.setForeground(Color.GRAY);
		lblAvatar.setFont(new Font("Arial", Font.BOLD, 14));
		lblAvatar.setBackground(Color.ORANGE);
		lblQuantityAvatar = new JLabel("(Up to 1 image(.png or .jpg))");
		lblQuantityAvatar.setForeground(Color.GRAY);
		lblQuantityAvatar.setFont(new Font("Arial", Font.PLAIN, 11));

		btnUpAvatar.setBackground(Color.LIGHT_GRAY);
		btnUpAvatar.setForeground(Color.BLACK);
		btnUpAvatar.setFont(new Font("Arial", Font.BOLD, 11));
		btnUpAvatar.setFocusable(false);
		btnUpAvatar.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY,
				Color.LIGHT_GRAY));

		lblImgAvatar.setOpaque(true);
		lblImgAvatar.setBackground(Color.WHITE);

		lblFullname = new JLabel("New Fullname");
		lblFullname.setForeground(Color.GRAY);
		lblFullname.setFont(new Font("Arial", Font.BOLD, 14));

		txtFullName.setColumns(10);
		txtFullName.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY,
				Color.LIGHT_GRAY));

		lblAddress = new JLabel("New Temporary address");
		lblAddress.setForeground(Color.GRAY);
		lblAddress.setFont(new Font("Arial", Font.BOLD, 14));

		lblPhone = new JLabel("New Phone Number");
		lblPhone.setForeground(Color.GRAY);
		lblPhone.setFont(new Font("Arial", Font.BOLD, 14));

		txtAddress.setColumns(10);
		txtAddress.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY,
				Color.LIGHT_GRAY));

		txtPhone.setColumns(10);
		txtPhone.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY,
				Color.LIGHT_GRAY));

		lblDob = new JLabel("New Date Of Birth");
		lblDob.setForeground(Color.GRAY);
		lblDob.setFont(new Font("Arial", Font.BOLD, 14));

		lblGender = new JLabel("New Gender");
		lblGender.setForeground(Color.GRAY);
		lblGender.setFont(new Font("Arial", Font.BOLD, 14));

		rdMale.setFont(new Font("Arial", Font.PLAIN, 12));
		rdMale.setBackground(Color.WHITE);

		rdFemale.setFont(new Font("Arial", Font.PLAIN, 12));
		rdFemale.setBackground(Color.WHITE);

		lblCIC = new JLabel("New National ID Card");
		lblCIC.setForeground(Color.GRAY);
		lblCIC.setFont(new Font("Arial", Font.BOLD, 14));

		txtCIC.setColumns(10);
		txtCIC.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY,
				Color.LIGHT_GRAY));

		lblIAuthority = new JLabel("New Issuing Authority");
		lblIAuthority.setForeground(Color.GRAY);
		lblIAuthority.setFont(new Font("Arial", Font.BOLD, 14));

		txtIssAu.setColumns(10);
		txtIssAu.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY,
				Color.LIGHT_GRAY));

		lblQuantityImgCIC = new JLabel("(Up to 2 image(.png or .jpg))");
		lblQuantityImgCIC.setForeground(Color.GRAY);
		lblQuantityImgCIC.setFont(new Font("Arial", Font.PLAIN, 11));

		btnUpCIC.setForeground(Color.BLACK);
		btnUpCIC.setFont(new Font("Arial", Font.BOLD, 11));
		btnUpCIC.setFocusable(false);
		btnUpCIC.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY,
				Color.LIGHT_GRAY));
		btnUpCIC.setBackground(Color.LIGHT_GRAY);

		btnSave.setForeground(Color.WHITE);
		btnSave.setFont(new Font("Arial", Font.BOLD, 14));
		btnSave.setFocusable(false);
		btnSave.setBorder(null);
		btnSave.setBackground(new Color(38, 185, 154));

		lblUpdateRenter.setForeground(Color.GRAY);
		lblUpdateRenter.setFont(new Font("Arial", Font.BOLD, 14));
		lblUpdateRenter.setBackground(Color.ORANGE);

		lblWarning = new JLabel(
				"<html>If you have any information that you don't want to update, please leave it blank.</br> The system will automatically retrieve the old information.</html>");
		lblWarning.setForeground(new Color(220, 20, 60));
		lblWarning.setFont(new Font("Arial", Font.BOLD, 12));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(78)
						.addComponent(lblUpdateRenter, GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE).addGap(68))
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap().addGroup(gl_contentPane
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup().addGroup(gl_contentPane
								.createParallelGroup(Alignment.LEADING)
								.addComponent(lblAvatar, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblQuantityAvatar, GroupLayout.PREFERRED_SIZE, 135,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblFullname)
								.addComponent(lblAddress, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPhone, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDob, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
								.addComponent(lblGender, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCIC, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblIAuthority, GroupLayout.PREFERRED_SIZE, 161,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblQuantityImgCIC, GroupLayout.PREFERRED_SIZE, 135,
										GroupLayout.PREFERRED_SIZE))
								.addGap(36)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(txtFullName, 207, 207, 207)
										.addComponent(txtAddress, GroupLayout.PREFERRED_SIZE, 207,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(txtPhone, GroupLayout.PREFERRED_SIZE, 207,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(dateDob, GroupLayout.PREFERRED_SIZE, 207,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(txtCIC, GroupLayout.PREFERRED_SIZE, 207,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(txtIssAu, GroupLayout.PREFERRED_SIZE, 207,
												GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(rdMale, GroupLayout.PREFERRED_SIZE, 55,
														GroupLayout.PREFERRED_SIZE)
												.addGap(28).addComponent(rdFemale, GroupLayout.PREFERRED_SIZE, 69,
														GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(btnUpCIC, GroupLayout.PREFERRED_SIZE, 55,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(lblImgCIC1, GroupLayout.PREFERRED_SIZE, 55,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblImgCIC2,
														GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(btnUpAvatar, GroupLayout.PREFERRED_SIZE, 55,
														GroupLayout.PREFERRED_SIZE)
												.addGap(10).addComponent(lblImgAvatar, GroupLayout.PREFERRED_SIZE, 55,
														GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(176)
								.addComponent(btnSave, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE).addGap(152)))
						.addGap(21))
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addComponent(lblWarning, GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE).addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addComponent(lblUpdateRenter, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE).addGap(9)
				.addComponent(lblWarning, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE).addGap(18)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblAvatar, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblQuantityAvatar).addGap(30)
								.addComponent(lblFullname, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(lblAddress, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(btnUpAvatar, GroupLayout.PREFERRED_SIZE, 39,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblImgAvatar, GroupLayout.PREFERRED_SIZE, 39,
												GroupLayout.PREFERRED_SIZE))
								.addGap(23)
								.addComponent(txtFullName, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
								.addGap(17)
								.addComponent(txtAddress, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)))
				.addGap(18)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPhone, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 19,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(txtPhone, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 19,
								GroupLayout.PREFERRED_SIZE))
				.addGap(23)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDob, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 20,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(dateDob, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(17)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblGender, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 20,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(rdMale, Alignment.TRAILING).addComponent(rdFemale, Alignment.TRAILING))
				.addGap(17)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(txtCIC, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCIC, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 20,
								GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblIAuthority, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(21).addComponent(lblQuantityImgCIC))
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(txtIssAu, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblImgCIC1, GroupLayout.PREFERRED_SIZE, 42,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblImgCIC2, GroupLayout.PREFERRED_SIZE, 42,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnUpCIC, GroupLayout.PREFERRED_SIZE, 42,
												GroupLayout.PREFERRED_SIZE))))
				.addGap(47).addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
				.addContainerGap()));
		contentPane.setLayout(gl_contentPane);

	}

}
