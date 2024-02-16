package formEnterAd;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Color;
import java.awt.Component;
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
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import view.AppStateManager;
import view.CardRoom;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;

public class FrameAddRenter extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane, cardAdRenter; // cardAdRenter chứa các card CardRenter
	private JPanel CardRenter; // các card trong cardAdRenter
	private JLabel lblAddRenter, lblFullname, lblAddress, lblPhone, JLabel, lblDob, lblGender, lblCIC, lblIAuthority, lblAvatar, lblImgAvatar, lblImgCIC1, lblImgCIC2, lblQuantityAvatar,
			lblQuantityImgCIC;
	private JTextField txtFullname, txtAddress, txtPhone, txtCIC, txtIAuthority;
	private JDateChooser dateDob;
	private JRadioButton rdMale, rdFemale;
	private JButton btnSaveAndNext, btnUpAvatar, btnUpCIC, btnSaveForm;
	
	private File lastSelectedFile;
	private String getFileName;
	private String newAvatarPath, filePath;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private List<String> imgNICList = new ArrayList<>();
	private List<String> pathNICList = new ArrayList<>();
	
	private int numRenterd;
	private CardRoom currentCardRoom;


	public void setCurrentCardRoom(CardRoom cardRoom) {
        currentCardRoom = cardRoom;
    }

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameAddRenter frame = new FrameAddRenter();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FrameAddRenter() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 748);
		contentPane = new JPanel();
		contentPane.setFont(new Font("Arial", Font.PLAIN, 11));
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		lblAddRenter = new JLabel("Add Renters");
		contentPane.add(lblAddRenter);

		cardAdRenter = new JPanel();
		contentPane.add(cardAdRenter);

		btnSaveForm = new JButton("Save");
		btnSaveForm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSaveFormActionPerformed(e);
			}
		});
		contentPane.add(btnSaveForm);
		
		var dao = new UserDao();

		CardRenter = new JPanel();
		CardRenter.setBackground(Color.WHITE);
		cardAdRenter.add(CardRenter, "Rented");
		CardRenter.setLayout(null);

		// avatar
		lblAvatar = new JLabel("Avatar");
		CardRenter.add(lblAvatar);

		lblQuantityAvatar = new JLabel("(Up to 1 image(.png or .jpg))");
		CardRenter.add(lblQuantityAvatar);

		var fileAvatarChooser = new JFileChooser();
		var fileAvatarFilter = new FileNameExtensionFilter("Image files", "png", "jpg");
		fileAvatarChooser.setFileFilter(fileAvatarFilter);

		btnUpAvatar = new JButton("Upload");
		btnUpAvatar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fileAvatarChooser.setCurrentDirectory(new File("C:\\Users")); // set default display folder in file selection dialog
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
						filePath = selectedFile.getAbsolutePath(); // Retrieve the absolute path of
																			// the selected file
						String newAvatarFilePath = "avatar_" + System.currentTimeMillis() + "." + extension; // Create a new file name for the avatar

						String imagesFolderPath = "images/"; // Path to the "images" directory in the project.
						// "Path to the new file in the "images" folder."
						newAvatarPath = imagesFolderPath + newAvatarFilePath;

						ImageIcon originAvatarIcon = new ImageIcon(filePath);
						Image imgAvatar = originAvatarIcon.getImage().getScaledInstance(30, 30,
								Image.SCALE_SMOOTH);
						ImageIcon imgAvatarParse = new ImageIcon(imgAvatar);
						lblImgAvatar.setIcon(imgAvatarParse);

						
					}
				}

			}
		});
		CardRenter.add(btnUpAvatar);

		// avatar
		lblImgAvatar = new JLabel("");
		CardRenter.add(lblImgAvatar);

		// fullname
		lblFullname = new JLabel("Fullname");
		CardRenter.add(lblFullname);

		txtFullname = new JTextField();
		CardRenter.add(txtFullname);

		// address
		txtAddress = new JTextField();
		CardRenter.add(txtAddress);

		lblAddress = new JLabel("Temporary address");
		CardRenter.add(lblAddress);

		// phone
		lblPhone = new JLabel("Phone");
		CardRenter.add(lblPhone);

		txtPhone = new JTextField();
		CardRenter.add(txtPhone);

		// dob
		lblDob = new JLabel("Date Of Birth");
		CardRenter.add(lblDob);

		dateDob = new JDateChooser();
		dateDob.setDateFormatString(new SimpleDateFormat("yyyy-MM-dd").toPattern());
		dateDob.setBounds(234, 207, 207, 20);
		CardRenter.add(dateDob);

		// gender
		ButtonGroup group = new ButtonGroup();
		final String[] genderFinal = { "" }; //
		lblGender = new JLabel("Gender");
		CardRenter.add(lblGender);

		rdMale = new JRadioButton("Male");
		group.add(rdMale);
		rdMale.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				genderFinal[0] = "Male";

			}
		});
		CardRenter.add(rdMale);

		rdFemale = new JRadioButton("Female");
		group.add(rdFemale);
		rdFemale.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				genderFinal[0] = "Female";

			}
		});
		CardRenter.add(rdFemale);

		lblCIC = new JLabel("Citizen Identification Card");
		lblCIC.setForeground(Color.GRAY);
		lblCIC.setFont(new Font("Arial", Font.BOLD, 14));
		lblCIC.setBounds(9, 286, 198, 20);
		CardRenter.add(lblCIC);

		txtCIC = new JTextField();
		CardRenter.add(txtCIC);

		lblIAuthority = new JLabel("Issuing Authority");
		CardRenter.add(lblIAuthority);

		txtIAuthority = new JTextField();
		CardRenter.add(txtIAuthority);

		lblQuantityImgCIC = new JLabel("(Up to 2 image(.png or .jpg))");
		CardRenter.add(lblQuantityImgCIC);

		btnUpCIC = new JButton("Upload");
		btnUpCIC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var fileChooser = new JFileChooser();
				fileChooser.setMultiSelectionEnabled(true);
				fileChooser.setFileFilter(new FileNameExtensionFilter("Image files", "png", "jpg"));
				fileChooser.setCurrentDirectory(new File("C:\\Users"));
				int result = fileChooser.showOpenDialog(FrameAddRenter.this);
				
				if(result == JFileChooser.APPROVE_OPTION) {
					File[] selectedFiles = fileChooser.getSelectedFiles();
					
					for(int i=0; i<Math.min(selectedFiles.length, 2); i++) {
						File file = selectedFiles[i];
						String fileName = file.getName();
						String extension = fileName.substring(fileName.lastIndexOf(".")+1);
						String newImgNICPath = "images/nic_" + System.currentTimeMillis() + "." + extension;
						String filePath = file.getAbsolutePath();
						System.out.println(filePath);
						
						pathNICList.add(filePath);
						imgNICList.add(newImgNICPath);
						
						switch(i) {
							case 0: lblImgCIC1.setIcon(new ImageIcon(
								new ImageIcon(filePath).getImage().getScaledInstance(60, 50, Image.SCALE_SMOOTH)
							)); 
							break;
							
							case 1: lblImgCIC2.setIcon(new ImageIcon(
								new ImageIcon(filePath).getImage().getScaledInstance(60, 50, Image.SCALE_SMOOTH)
							));
							break;
						}
					}
					
				}
			}
		});
		CardRenter.add(btnUpCIC);

		lblImgCIC1 = new JLabel("");
		CardRenter.add(lblImgCIC1);

		lblImgCIC2 = new JLabel("");
		CardRenter.add(lblImgCIC2);

		
		btnSaveAndNext = new JButton("Save");
		btnSaveAndNext.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				var renter = new Users();

				renter.setAvatar(newAvatarPath);
				renter.setName(txtFullname.getText());
				java.sql.Date sqlDate = new java.sql.Date(dateDob.getDate().getTime()); // sql of Date
				renter.setDob(sqlDate);
				if (rdMale.isSelected()) {
					genderFinal[0] = "Male";
				} else if (rdFemale.isSelected()) {
					genderFinal[0] = "Female";
				}
				
				String imgNICPathStr = String.join(";", imgNICList);
				
				
				renter.setGender(genderFinal[0]);
				renter.setAddress(txtAddress.getText());
				renter.setPhone(txtPhone.getText());
				renter.setNic(txtCIC.getText());
				renter.setiAuthority(txtIAuthority.getText());
				renter.setImgIAuthority(imgNICPathStr);
				dao.insertRenter(renter);
				
				// Copy the avatar file to the images folder.
				try {
					Files.copy(new File(filePath).toPath(), new File(newAvatarPath).toPath(),
							StandardCopyOption.REPLACE_EXISTING);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
				// Copy the nic file to images folder
				for(int i=0; i<Math.min(pathNICList.size(), 2); i++) {
					try {
						Files.copy(new File(pathNICList.get(i)).toPath(), new File(imgNICList.get(i)).toPath(), StandardCopyOption.REPLACE_EXISTING);
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
				
			
				lblImgAvatar.setIcon(null);
				txtFullname.setText("");
				txtAddress.setText("");
				txtPhone.setText("");
				dateDob.setDate(null);
				group.clearSelection();
				txtCIC.setText("");
				txtIAuthority.setText("");
				lblImgCIC1.setIcon(null);
				lblImgCIC2.setIcon(null);
			}
		});
		CardRenter.add(btnSaveAndNext);
		
		initComponentCardAddPerson();
		initComponentCardForm();

	}
	



	public void btnSaveFormActionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(this, "Data saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
		
		Window window = SwingUtilities.windowForComponent((Component) e.getSource());
		window.dispose();
		
		
		if(currentCardRoom != null) {
//			int roomNumber = Integer.parseInt(txtRentApNum.getText());
//		    currentCardRoom.setBackgroundColor(new Color(39, 158, 255));
//		    
//		    CardLayout cardLayout = (CardLayout) currentCardRoom.CardButton.getLayout();
//		    cardLayout.show(currentCardRoom.CardButton, "rented");
//		    
//		    AppStateManager.saveAppState(roomNumber, new Color(39, 158, 255), "rented");
		}

		dispose();
	}

//	public void setRoomNumber(int currentApartNum) {
//		txtRentApNum.setText(String.valueOf(currentApartNum));
//
//	}
	
	
	
	
	
	
	
	

	public void initComponentCardAddPerson() {
		// avatar
		lblAvatar.setBackground(Color.ORANGE);
		lblAvatar.setForeground(Color.GRAY);
		lblAvatar.setFont(new Font("Arial", Font.BOLD, 14));
		lblAvatar.setBounds(10, 39, 69, 39);

		lblImgAvatar.setOpaque(true);
		lblImgAvatar.setBackground(Color.WHITE);
		lblImgAvatar.setBounds(297, 39, 55, 39);

		lblQuantityAvatar.setForeground(Color.GRAY);
		lblQuantityAvatar.setFont(new Font("Arial", Font.PLAIN, 11));
		lblQuantityAvatar.setBounds(10, 64, 135, 14);

		btnUpAvatar.setForeground(Color.BLACK);
		btnUpAvatar.setFont(new Font("Arial", Font.BOLD, 11));
		btnUpAvatar.setFocusable(false);
		btnUpAvatar.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY,
				Color.LIGHT_GRAY));
		btnUpAvatar.setBackground(Color.LIGHT_GRAY);
		btnUpAvatar.setBounds(232, 39, 55, 39);

		// fullname
		txtFullname.setBounds(234, 96, 207, 19);
		txtFullname.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY,
				Color.LIGHT_GRAY));
		txtFullname.setColumns(10);

		lblFullname.setBounds(9, 98, 69, 19);
		lblFullname.setForeground(Color.GRAY);
		lblFullname.setFont(new Font("Arial", Font.BOLD, 14));

		// address
		lblAddress.setBounds(9, 135, 161, 19);
		lblAddress.setForeground(Color.GRAY);
		lblAddress.setFont(new Font("Arial", Font.BOLD, 14));

		txtAddress.setBounds(234, 134, 207, 19);
		txtAddress.setColumns(10);
		txtAddress.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY,
				Color.LIGHT_GRAY));

		// phone
		lblPhone.setForeground(Color.GRAY);
		lblPhone.setFont(new Font("Arial", Font.BOLD, 14));
		lblPhone.setBounds(9, 167, 124, 19);

		txtPhone.setColumns(10);
		txtPhone.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY,
				Color.LIGHT_GRAY));
		txtPhone.setBounds(234, 166, 207, 19);

		// dob
		lblDob.setForeground(Color.GRAY);
		lblDob.setFont(new Font("Arial", Font.BOLD, 14));
		lblDob.setBounds(9, 209, 124, 20);

		// gender
		lblGender.setForeground(Color.GRAY);
		lblGender.setFont(new Font("Arial", Font.BOLD, 14));
		lblGender.setBounds(9, 249, 124, 20);

		rdMale.setFont(new Font("Arial", Font.PLAIN, 12));
		rdMale.setBackground(Color.WHITE);
		rdMale.setBounds(232, 246, 55, 23);

		rdFemale.setFont(new Font("Arial", Font.PLAIN, 12));
		rdFemale.setBackground(Color.WHITE);
		rdFemale.setBounds(315, 246, 69, 23);

		// CIC
		txtCIC.setColumns(10);
		txtCIC.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY,
				Color.LIGHT_GRAY));
		txtCIC.setBounds(234, 284, 207, 20);

		// IAuthority
		lblIAuthority.setForeground(Color.GRAY);
		lblIAuthority.setFont(new Font("Arial", Font.BOLD, 14));
		lblIAuthority.setBounds(9, 323, 161, 19);

		txtIAuthority.setColumns(10);
		txtIAuthority.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, Color.LIGHT_GRAY,
				Color.LIGHT_GRAY, Color.LIGHT_GRAY));
		txtIAuthority.setBounds(234, 321, 207, 19);

		lblQuantityImgCIC.setForeground(Color.GRAY);
		lblQuantityImgCIC.setFont(new Font("Arial", Font.PLAIN, 11));
		lblQuantityImgCIC.setBounds(10, 341, 135, 14);

		btnUpCIC.setForeground(Color.BLACK);
		btnUpCIC.setFont(new Font("Arial", Font.BOLD, 11));
		btnUpCIC.setFocusable(false);
		btnUpCIC.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY,
				Color.LIGHT_GRAY));
		btnUpCIC.setBackground(Color.LIGHT_GRAY);
		btnUpCIC.setBounds(233, 354, 55, 42);

		lblImgCIC1.setOpaque(true);
		lblImgCIC1.setBackground(Color.ORANGE);
		lblImgCIC1.setBounds(295, 354, 55, 42);

		lblImgCIC2.setOpaque(true);
		lblImgCIC2.setBackground(Color.ORANGE);
		lblImgCIC2.setBounds(357, 354, 55, 42);

		// btn save and next
		btnSaveAndNext.setForeground(Color.BLACK);
		btnSaveAndNext.setFont(new Font("Arial", Font.BOLD, 11));
		btnSaveAndNext.setFocusable(false);
		btnSaveAndNext.setBackground(Color.LIGHT_GRAY);
		btnSaveAndNext.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, Color.LIGHT_GRAY,
				Color.LIGHT_GRAY, Color.LIGHT_GRAY));
		btnSaveAndNext.setBounds(332, 440, 109, 31);
	}

	public void initComponentCardForm() {

		lblAddRenter.setBounds(227, 11, 95, 19);
		lblAddRenter.setForeground(Color.GRAY);
		lblAddRenter.setFont(new Font("Arial", Font.BOLD, 15));
		contentPane.setLayout(null);
		
		cardAdRenter.setBackground(Color.WHITE);
		cardAdRenter.setBounds(11, 81, 464, 500);
		cardAdRenter.setLayout(new CardLayout(0, 0));
		
		// btn Save Form
		btnSaveForm.setFocusable(false);
		btnSaveForm.setFont(new Font("Arial", Font.BOLD, 12));
		btnSaveForm.setForeground(Color.WHITE);
		btnSaveForm.setBorder(null);
		btnSaveForm.setBackground(new Color(38, 185, 154));
		btnSaveForm.setBounds(210, 639, 112, 31);
	}
	
}