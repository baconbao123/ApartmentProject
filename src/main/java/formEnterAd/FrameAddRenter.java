package formEnterAd;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingUtilities;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import java.awt.CardLayout;
import com.toedter.calendar.JDateChooser;

import component.CardApartment;
import dao.UserDao;
import entity.Users;
import formAdmin.RenterList;
import formUpdateAd.FrameUpRenter;
import regex.Regex;
import regex.Valid;
import view.AppStateManager;
import view.CardRoom;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

public class FrameAddRenter extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane; // cardAdRenter chứa các card CardRenter
	private JLabel lblAddRenter, JLabel;
	private JButton btnSaveRenter;
	private CardRoom currentCardRoom;
	private JLabel lblAvatar;
	private JLabel lblQuantityAvatar;
	private JButton btnUpAvatar;
	private JLabel lblImgAvatar;
	private JLabel lblFullname;
	private JTextField txtFullname;
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
	private JTextField txtIssAuth;
	private JLabel lblQuantityImgCIC;
	private JButton btnUpCIC;
	private JLabel lblImgCIC1;
	private JLabel lblImgCIC2;
	private JLabel lblEmail;
	private JTextField txtEmail;

	private File lastSelectedFile;
	private String getFileName;
	private String newAvatarPath, filePath;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private List<String> imgNICList = new ArrayList<>();
	private List<String> pathNICList = new ArrayList<>();
	private RenterList renterlist;

	private int numRenterd;

	public void setCurrentCardRoom(CardRoom cardRoom) {
		currentCardRoom = cardRoom;
	}




	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RenterList renterList = new RenterList();
					FrameAddRenter frame = new FrameAddRenter(renterList);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FrameAddRenter(RenterList renterList) {
		this.renterlist = renterList;
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 494, 560);
		contentPane = new JPanel();
		contentPane.setFont(new Font("Arial", Font.PLAIN, 11));
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		lblAddRenter = new JLabel("Add Renters");

		rdMale = new JRadioButton("Male");
		buttonGroup.add(rdMale);
		rdFemale = new JRadioButton("Female");
		buttonGroup.add(rdFemale);

		final String[] genderFinal = { "" };
		rdFemale.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				genderFinal[0] = "Female";

			}
		});
		rdMale.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				genderFinal[0] = "Male";

			}
		});

		btnSaveRenter = new JButton("Save");
		btnSaveRenter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var user = new Users();
				var dao = new UserDao();

				if (rdMale.isSelected()) {
					genderFinal[0] = "Male";
				} else {
					genderFinal[0] = "Female";
				}
				String imgNICPathStr = String.join(";", imgNICList);

				String errorMessage = "";

				// lbl icon
				if (lblImgAvatar.getIcon() == null) {
					JOptionPane.showMessageDialog(null, "Please upload an avatar", "Invalid Input",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// txt
				errorMessage = Valid.validateInputWithNOEmpty(txtFullname.getText(), Regex.CHAR, "Fullname ",
						"Invalid. Please input letter only");
				if (errorMessage != null) {
					JOptionPane.showMessageDialog(null, errorMessage, "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				String emailCheck = txtEmail.getText().trim();	
				
				if (!emailCheck.isEmpty()) {
				    errorMessage = Valid.validateInputWithEmpty(emailCheck, Regex.EMAIL_REGEX, "Email ", "Please enter in the format: example@gmail.com");
				    if(errorMessage != null) {
				        JOptionPane.showMessageDialog(null, errorMessage, "Invalid Input", JOptionPane.ERROR_MESSAGE);
				        return;
				    }
				    
				    var daoCheck = new UserDao();
				    List<Object> result = daoCheck.emailExist(emailCheck);
				    if(!result.isEmpty()) {
				        JOptionPane.showMessageDialog(null, "Email already exists.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
				        return;
				    }
				}
				
				
				errorMessage = Valid.validateInputWithNOEmpty(txtAddress.getText(), Regex.CHAR_NUM_UP, "Address ",
						"Invalid. Please input letter only");
				if (errorMessage != null) {
					JOptionPane.showMessageDialog(null, errorMessage, "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;
				}

				errorMessage = Valid.validateInputWithNOEmpty(txtPhone.getText(), Regex.NUM, "Phone ",
						"Invalid. Please enter only numbers");
				if (errorMessage != null) {
					JOptionPane.showMessageDialog(null, errorMessage, "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;
				}

				errorMessage = Valid.validateInputWithNOEmpty(txtCIC.getText(), Regex.NUM, "National ID Card ",
						"Invalid. Please enter only numbers");
				if (errorMessage != null) {
					JOptionPane.showMessageDialog(null, errorMessage, "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;
				}

				errorMessage = Valid.validateInputWithNOEmpty(txtIssAuth.getText(), Regex.CHAR,
						"ID card issuing place ", "Invalid. Please input letter only");
				if (errorMessage != null) {
					JOptionPane.showMessageDialog(null, errorMessage, "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;
				}

				// gender
				if (!rdMale.isSelected() && !rdFemale.isSelected()) {
					JOptionPane.showMessageDialog(null, "Please chooser gender ", "Invalid Input",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (lblImgCIC1.getIcon() == null || lblImgCIC2.getIcon() == null) {
					JOptionPane.showMessageDialog(null, "Please upload 2 photos of your national ID card",
							"Invalid Input", JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (dateDob.getDate() == null) {
					JOptionPane.showMessageDialog(FrameAddRenter.this, "Enter date of birth(yyyy-MM-dd)",
							"Invalid Input", JOptionPane.ERROR_MESSAGE);

				} else {

					java.sql.Date sqlDate = new java.sql.Date(dateDob.getDate().getTime());
					user.setAvatar(newAvatarPath);
					user.setName(txtFullname.getText().trim());
					user.setAddress(txtAddress.getText().trim());
					user.setDob(sqlDate);
					user.setGender(genderFinal[0]);
					user.setPhone(txtPhone.getText().trim());
					user.setEmail(emailCheck);
					user.setNic(txtCIC.getText().trim());
					user.setiAuthority(txtIssAuth.getText().trim());
					user.setImgIAuthority(imgNICPathStr);
					dao.insertRenter(user);
					JOptionPane.showMessageDialog(null, "Successfully added renter");
					
					try {
						Files.copy(new File(filePath).toPath(), new File(newAvatarPath).toPath(),
								StandardCopyOption.REPLACE_EXISTING);
					} catch (Exception e2) {
						e2.printStackTrace();
					}

					// Copy the nic file to images folder

					List<Integer> indexesToRemove = new ArrayList<>();
					for (int i = 0; i < Math.min(pathNICList.size(), 2); i++) {
						try {
							Files.copy(new File(pathNICList.get(i)).toPath(), new File(imgNICList.get(i)).toPath(),
									StandardCopyOption.REPLACE_EXISTING);
							indexesToRemove.add(i);
						} catch (Exception e2) {
							e2.printStackTrace();
						}
					}

					for (int i = indexesToRemove.size() - 1; i >= 0; i--) {
						int index = indexesToRemove.get(i);
						pathNICList.remove(index);
						imgNICList.remove(index);
					}
					
					

					newAvatarPath = null;
					lblImgAvatar.setIcon(null);
					txtFullname.setText("");
					txtAddress.setText("");
					txtPhone.setText("");
					txtEmail.setText("");
					dateDob.setDate(null);
					buttonGroup.clearSelection();
					txtCIC.setText("");
					txtIssAuth.setText("");
					lblImgCIC1.setIcon(null);
					lblImgCIC2.setIcon(null);

					renterList.reloadTable();
				}

			}
		});

		initComponent();

	}

	protected void btnUpAvatarActionPerformed(ActionEvent e) {
		var fileAvatarChooser = new JFileChooser();
		fileAvatarChooser.setAcceptAllFileFilterUsed(false);
		var fileAvatarFilter = new FileNameExtensionFilter("Image files", "png", "jpg");
		fileAvatarChooser.setFileFilter(fileAvatarFilter);

		fileAvatarChooser.setCurrentDirectory(new File("C:\\Users"));

		if (lastSelectedFile != null) {
			fileAvatarChooser.setSelectedFile(lastSelectedFile);
		}
		int result = fileAvatarChooser.showOpenDialog(FrameAddRenter.this);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileAvatarChooser.getSelectedFile();
			if (selectedFile != null) {
				lastSelectedFile = selectedFile;
			}

			getFileName = selectedFile.getName();
			String extension = getFileName.substring(getFileName.lastIndexOf(".") + 1);
			if (extension.equalsIgnoreCase("png") || extension.equalsIgnoreCase("jpg")) {

				filePath = selectedFile.getAbsolutePath();
				String newAvatarFilePath = "avatar_" + System.currentTimeMillis() + "." + extension;
				String imagesFolderPath = "images/";
				newAvatarPath = imagesFolderPath + newAvatarFilePath;
				ImageIcon originAvatarIcon = new ImageIcon(filePath);
				Image imgAvatar = originAvatarIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
				ImageIcon imgAvatarParse = new ImageIcon(imgAvatar);
				lblImgAvatar.setIcon(imgAvatarParse);
				lblImgAvatar.setCursor(new Cursor(Cursor.HAND_CURSOR));
				for (MouseListener listener : lblImgAvatar.getMouseListeners()) {
					lblImgAvatar.removeMouseListener(listener);
				}
				addMouseListenerImg(lblImgAvatar, new ImageIcon(filePath));
			}
		}

	}

	protected void btnUpCICActionPerformed(ActionEvent e) {
		var fileChooser = new JFileChooser();
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setMultiSelectionEnabled(true);
		fileChooser.setFileFilter(new FileNameExtensionFilter("Image files", "png", "jpg"));
		fileChooser.setCurrentDirectory(new File("C:\\Users"));
		int result = fileChooser.showOpenDialog(FrameAddRenter.this);

		if (result == JFileChooser.APPROVE_OPTION) {
			File[] selectedFiles = fileChooser.getSelectedFiles();

			pathNICList.clear();
			imgNICList.clear();

			if (selectedFiles.length != 2) {
				JOptionPane.showMessageDialog(null, "Please upload 2 photos of your national ID card", "Input Invalid",
						JOptionPane.ERROR_MESSAGE);
			} else {
				for (int i = 0; i < 2; i++) {
					File file = selectedFiles[i];
					String fileName = file.getName();
					String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
					String newImgNICPath = "images/nic_" + System.currentTimeMillis() + "." + extension;
					String filePath = file.getAbsolutePath();

					pathNICList.add(filePath);
					imgNICList.add(newImgNICPath);

					switch (i) {
					case 0:
						ImageIcon icon = new ImageIcon(filePath);
						lblImgCIC1
								.setIcon(new ImageIcon(icon.getImage().getScaledInstance(60, 50, Image.SCALE_SMOOTH)));
						lblImgCIC1.setCursor(new Cursor(Cursor.HAND_CURSOR));
						for (MouseListener listener : lblImgCIC1.getMouseListeners()) {
							lblImgCIC1.removeMouseListener(listener);
						}
						addMouseListenerImg(lblImgCIC1, icon);
						break;

					case 1:
						ImageIcon icon1 = new ImageIcon(filePath);
						lblImgCIC2
								.setIcon(new ImageIcon(icon1.getImage().getScaledInstance(60, 50, Image.SCALE_SMOOTH)));
						lblImgCIC2.setCursor(new Cursor(Cursor.HAND_CURSOR));
						for (MouseListener listener : lblImgCIC2.getMouseListeners()) {
							lblImgCIC2.removeMouseListener(listener);
						}
						addMouseListenerImg(lblImgCIC2, icon1);
						break;
					}
				}
			}

		}
	};

	private void addMouseListenerImg(JLabel label, ImageIcon img) {
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JDialog dialog = new JDialog();
				JLabel imgLabel = new JLabel(
						new ImageIcon(img.getImage().getScaledInstance(600, 600, Image.SCALE_SMOOTH)));
				dialog.add(imgLabel);
				dialog.pack();
				dialog.setVisible(true);
				dialog.setLocationRelativeTo(null);
				super.mouseClicked(e);
			}
		});
	}

	public void initComponent() {
		lblAddRenter.setForeground(Color.GRAY);
		lblAddRenter.setFont(new Font("Arial", Font.BOLD, 15));

		// btn Save Form
		btnSaveRenter.setFocusable(false);
		btnSaveRenter.setFont(new Font("Arial", Font.BOLD, 12));
		btnSaveRenter.setForeground(Color.WHITE);
		btnSaveRenter.setBorder(null);
		btnSaveRenter.setBackground(new Color(38, 185, 154));

		lblAvatar = new JLabel("Avatar");
		lblAvatar.setForeground(Color.GRAY);
		lblAvatar.setFont(new Font("Arial", Font.BOLD, 14));
		lblAvatar.setBackground(Color.ORANGE);

		lblQuantityAvatar = new JLabel("(Up to 1 image(.png or .jpg))");
		lblQuantityAvatar.setForeground(Color.GRAY);
		lblQuantityAvatar.setFont(new Font("Arial", Font.PLAIN, 11));

		btnUpAvatar = new JButton("Upload");
		btnUpAvatar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnUpAvatarActionPerformed(e);
			}
		});
		btnUpAvatar.setForeground(Color.BLACK);
		btnUpAvatar.setFont(new Font("Arial", Font.BOLD, 11));
		btnUpAvatar.setFocusable(false);
		btnUpAvatar.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY,

				Color.LIGHT_GRAY));
		btnUpAvatar.setBackground(Color.LIGHT_GRAY);

		lblImgAvatar = new JLabel("");
		lblImgAvatar.setOpaque(true);
		lblImgAvatar.setBackground(Color.WHITE);

		lblFullname = new JLabel("Fullname");
		lblFullname.setForeground(Color.GRAY);
		lblFullname.setFont(new Font("Arial", Font.BOLD, 14));

		txtFullname = new JTextField();
		txtFullname.setColumns(10);
		txtFullname.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY,

				Color.LIGHT_GRAY));

		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		txtAddress.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY,

				Color.LIGHT_GRAY));

		lblAddress = new JLabel("Temporary address");
		lblAddress.setForeground(Color.GRAY);
		lblAddress.setFont(new Font("Arial", Font.BOLD, 14));

		lblPhone = new JLabel("Phone");
		lblPhone.setForeground(Color.GRAY);
		lblPhone.setFont(new Font("Arial", Font.BOLD, 14));

		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		txtPhone.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY,

				Color.LIGHT_GRAY));

		lblDob = new JLabel("Date Of Birth");
		lblDob.setForeground(Color.GRAY);
		lblDob.setFont(new Font("Arial", Font.BOLD, 14));

		dateDob = new JDateChooser();
		dateDob.setDateFormatString("yyyy-MM-dd");
//		dateDob.getJCalendar().setMaxSelectableDate(Calendar.getInstance().getTime());
		Calendar currentDate = Calendar.getInstance();
		currentDate.add(Calendar.YEAR, -18);
		dateDob.getJCalendar().setMaxSelectableDate(currentDate.getTime());

		lblGender = new JLabel("Gender");
		lblGender.setForeground(Color.GRAY);
		lblGender.setFont(new Font("Arial", Font.BOLD, 14));

		rdMale.setFont(new Font("Arial", Font.PLAIN, 12));
		rdMale.setBackground(Color.WHITE);

		rdFemale.setFont(new Font("Arial", Font.PLAIN, 12));
		rdFemale.setBackground(Color.WHITE);

		lblCIC = new JLabel("National ID Card");
		lblCIC.setForeground(Color.GRAY);
		lblCIC.setFont(new Font("Arial", Font.BOLD, 14));

		txtCIC = new JTextField();
		txtCIC.setColumns(10);
		txtCIC.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY,

				Color.LIGHT_GRAY));

		lblIAuthority = new JLabel("Issuing Authority");
		lblIAuthority.setForeground(Color.GRAY);
		lblIAuthority.setFont(new Font("Arial", Font.BOLD, 14));

		txtIssAuth = new JTextField();
		txtIssAuth.setColumns(10);
		txtIssAuth.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, Color.LIGHT_GRAY,

				Color.LIGHT_GRAY, Color.LIGHT_GRAY));

		lblQuantityImgCIC = new JLabel("(Up to 2 image(.png or .jpg))");
		lblQuantityImgCIC.setForeground(Color.GRAY);
		lblQuantityImgCIC.setFont(new Font("Arial", Font.PLAIN, 11));

		btnUpCIC = new JButton("Upload");
		btnUpCIC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnUpCICActionPerformed(e);
			}
		});
		btnUpCIC.setForeground(Color.BLACK);
		btnUpCIC.setFont(new Font("Arial", Font.BOLD, 11));
		btnUpCIC.setFocusable(false);
		btnUpCIC.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY,

				Color.LIGHT_GRAY));
		btnUpCIC.setBackground(Color.LIGHT_GRAY);

		lblImgCIC1 = new JLabel("");

		lblImgCIC2 = new JLabel("");

		lblEmail = new JLabel("Email");
		lblEmail.setForeground(Color.GRAY);
		lblEmail.setFont(new Font("Arial", Font.BOLD, 14));

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY,

				Color.LIGHT_GRAY));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane
				.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(17)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblAvatar, GroupLayout.PREFERRED_SIZE, 69,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblQuantityAvatar))
								.addGap(87)
								.addComponent(btnUpAvatar, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addComponent(lblImgAvatar, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(17)
								.addComponent(lblFullname, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
								.addGap(156)
								.addComponent(txtFullname, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE))
						.addGroup(
								Alignment.TRAILING,
								gl_contentPane
										.createSequentialGroup().addGap(192)
										.addComponent(lblAddRenter, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
										.addGap(181))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(17)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
														.addGroup(gl_contentPane.createSequentialGroup()
																.addComponent(lblAddress, GroupLayout.PREFERRED_SIZE,
																		161, GroupLayout.PREFERRED_SIZE)
																.addGap(64).addComponent(txtAddress,
																		GroupLayout.PREFERRED_SIZE, 207,
																		GroupLayout.PREFERRED_SIZE))
														.addGroup(gl_contentPane.createSequentialGroup()
																.addComponent(lblPhone, GroupLayout.PREFERRED_SIZE, 124,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(101)
																.addComponent(txtPhone, GroupLayout.PREFERRED_SIZE, 207,
																		GroupLayout.PREFERRED_SIZE))
														.addGroup(gl_contentPane.createSequentialGroup()
																.addComponent(lblDob, GroupLayout.PREFERRED_SIZE, 124,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(101).addComponent(dateDob,
																		GroupLayout.PREFERRED_SIZE, 207,
																		GroupLayout.PREFERRED_SIZE))
														.addGroup(gl_contentPane.createSequentialGroup()
																.addComponent(lblGender, GroupLayout.PREFERRED_SIZE,
																		124, GroupLayout.PREFERRED_SIZE)
																.addGap(99)
																.addComponent(rdMale, GroupLayout.PREFERRED_SIZE, 55,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(28)
																.addComponent(rdFemale, GroupLayout.PREFERRED_SIZE, 69,
																		GroupLayout.PREFERRED_SIZE))
														.addGroup(gl_contentPane.createSequentialGroup()
																.addComponent(lblCIC, GroupLayout.PREFERRED_SIZE, 198,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(27)
																.addComponent(txtCIC, GroupLayout.PREFERRED_SIZE, 207,
																		GroupLayout.PREFERRED_SIZE))
														.addGroup(gl_contentPane.createSequentialGroup()
																.addGroup(gl_contentPane
																		.createParallelGroup(Alignment.LEADING)
																		.addComponent(lblIAuthority,
																				GroupLayout.PREFERRED_SIZE, 161,
																				GroupLayout.PREFERRED_SIZE)
																		.addGroup(gl_contentPane
																				.createSequentialGroup().addGap(1)
																				.addComponent(lblQuantityImgCIC)))
																.addGap(63)
																.addGroup(gl_contentPane
																		.createParallelGroup(Alignment.LEADING)
																		.addGroup(gl_contentPane.createSequentialGroup()
																				.addGap(1).addComponent(txtIssAuth,
																						GroupLayout.PREFERRED_SIZE, 207,
																						GroupLayout.PREFERRED_SIZE))
																		.addGroup(gl_contentPane.createSequentialGroup()
																				.addComponent(btnUpCIC,
																						GroupLayout.PREFERRED_SIZE, 55,
																						GroupLayout.PREFERRED_SIZE)
																				.addGap(7)
																				.addComponent(lblImgCIC1,
																						GroupLayout.PREFERRED_SIZE, 55,
																						GroupLayout.PREFERRED_SIZE)
																				.addGap(7).addComponent(lblImgCIC2,
																						GroupLayout.PREFERRED_SIZE, 55,
																						GroupLayout.PREFERRED_SIZE)))))
												.addContainerGap())
										.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 69,
														GroupLayout.PREFERRED_SIZE)
												.addGap(156).addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 207,
														GroupLayout.PREFERRED_SIZE))))
						.addGroup(Alignment.TRAILING,
								gl_contentPane.createSequentialGroup().addGap(184)
										.addComponent(btnSaveRenter, GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
										.addGap(161)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addGap(6)
				.addComponent(lblAddRenter, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE).addGap(19)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAvatar, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(20).addComponent(lblQuantityAvatar,
								GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnUpAvatar, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblImgAvatar, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
				.addGap(10)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(2).addComponent(lblFullname,
								GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtFullname, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
				.addGap(11)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(2).addComponent(lblEmail,
								GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(1).addComponent(lblAddress,
								GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtAddress, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
				.addGap(12)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(1).addComponent(lblPhone,
								GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtPhone, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
				.addGap(21)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(2).addComponent(lblDob,
								GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
						.addComponent(dateDob, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(17)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(3).addComponent(lblGender,
								GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
						.addComponent(rdMale).addComponent(rdFemale))
				.addGap(15)
				.addGroup(
						gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup().addGap(2).addComponent(lblCIC,
										GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
								.addComponent(txtCIC, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
				.addGap(15)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
						.createSequentialGroup().addGap(2)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblIAuthority, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup().addGap(18).addComponent(
										lblQuantityImgCIC, GroupLayout.PREFERRED_SIZE, 14,
										GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(txtIssAuth, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
								.addGap(14)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(btnUpCIC, GroupLayout.PREFERRED_SIZE, 42,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblImgCIC1, GroupLayout.PREFERRED_SIZE, 42,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblImgCIC2, GroupLayout.PREFERRED_SIZE, 42,
												GroupLayout.PREFERRED_SIZE))))
				.addGap(34).addComponent(btnSaveRenter, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
				.addGap(27)));
		contentPane.setLayout(gl_contentPane);
	}

}