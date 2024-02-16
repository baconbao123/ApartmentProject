package formEnterAd;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.border.BevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import dao.UserDao;
import entity.Users;
import view.CardRoom;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ButtonGroup;

public class FormAddRenter extends JPanel {
	private JLabel lblIAuthority;
	private JLabel lblQuantityImgCIC;
	private JLabel lblAvatar;
	private JLabel lblQuantityAvatar;
	private JLabel lblFullname;
	private JLabel lblAddress;
	private JLabel lblPhone;
	private JLabel lblDob;
	private JLabel lblGender;
	private JLabel lblCIC;
	private JButton btnUpAvatar;
	private JLabel lblImgAvatar;
	private JRadioButton rdMale;
	private JRadioButton rdFemale;
	private JTextField txtFullname;
	private JTextField txtAddress;
	private JTextField txtPhone;
	private JDateChooser dateDob;
	private JTextField txtCIC;
	private JTextField txtIAuthority;
	private JButton btnSaveAndNext;
	private JButton btnUpCIC;
	private JLabel lblImgCIC1;
	private JLabel lblImgCIC2;
	private JLabel lblAddRenter;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	private int numRenterd;
	private File lastSelectedFile;
	private String getFileName;
	private String newAvatarPath, filePath;
	private List<String> imgNICList = new ArrayList<>();
	private List<String> pathNICList = new ArrayList<>();
	private CardRoom currentCardRoom;


	public void setCurrentCardRoom(CardRoom cardRoom) {
        currentCardRoom = cardRoom;
    }
	
	public FormAddRenter() {
		setBackground(Color.WHITE);
		var dao = new UserDao();
		
		//avatar
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
				int result = fileAvatarChooser.showOpenDialog(FormAddRenter.this);
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
						Image imgAvatar = originAvatarIcon.getImage().getScaledInstance(50, 50,
								Image.SCALE_SMOOTH);
						ImageIcon imgAvatarParse = new ImageIcon(imgAvatar);
						lblImgAvatar.setIcon(imgAvatarParse);

						
					}
				}

			}
		});
		
		lblImgAvatar = new JLabel("");
		
		//dob
		dateDob = new JDateChooser();
		dateDob.setDateFormatString("yyyy-MM-dd");
		
		//gender
		final String[] genderFinal = { "" };
		rdMale = new JRadioButton("Male");
		rdFemale = new JRadioButton("Female");
		rdMale.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				genderFinal[0] = "Male";

			}
		});
		rdFemale.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				genderFinal[0] = "Female";

			}
		});
		
		// cic
		btnUpCIC = new JButton("Upload");
		btnUpCIC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var fileChooser = new JFileChooser();
				fileChooser.setMultiSelectionEnabled(true);
				fileChooser.setFileFilter(new FileNameExtensionFilter("Image files", "png", "jpg"));
				fileChooser.setCurrentDirectory(new File("C:\\Users"));
				int result = fileChooser.showOpenDialog(FormAddRenter.this);
				
				if(result == JFileChooser.APPROVE_OPTION) {
					File[] selectedFiles = fileChooser.getSelectedFiles();
					
					for(int i=0; i<Math.min(selectedFiles.length, 2); i++) {
						File file = selectedFiles[i];
						String fileName = file.getName();
						String extension = fileName.substring(fileName.lastIndexOf(".")+1);
						String newImgNICPath = "images/nic_" + System.currentTimeMillis() + "." + extension;
						String filePath = file.getAbsolutePath();
						
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
		lblImgCIC1 = new JLabel("");
		lblImgCIC2 = new JLabel("");
		
		
		btnSaveAndNext = new JButton("Save The Renter");
		btnSaveAndNext.setBackground(new Color(64, 128, 128));
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
				buttonGroup.clearSelection();
				txtCIC.setText("");
				txtIAuthority.setText("");
				lblImgCIC1.setIcon(null);
				lblImgCIC2.setIcon(null);
			}
		});

		initComponent();
	}
	
	
	private void initComponent() {
		lblAddRenter = new JLabel("Form 1: Add Renters");
		lblAddRenter.setForeground(Color.GRAY);
		lblAddRenter.setFont(new Font("Arial", Font.BOLD, 16));
		
		lblIAuthority = new JLabel("Issuing Authority");
		lblIAuthority.setForeground(Color.GRAY);
		lblIAuthority.setFont(new Font("Arial", Font.BOLD, 14));
		
		lblQuantityImgCIC = new JLabel("(Up to 2 image(.png or .jpg))");
		lblQuantityImgCIC.setForeground(Color.GRAY);
		lblQuantityImgCIC.setFont(new Font("Arial", Font.PLAIN, 11));
		
		lblAvatar = new JLabel("Avatar");
		lblAvatar.setForeground(Color.GRAY);
		lblAvatar.setFont(new Font("Arial", Font.BOLD, 14));
		lblAvatar.setBackground(Color.ORANGE);
		
		lblQuantityAvatar = new JLabel("(Up to 1 image(.png or .jpg))");
		lblQuantityAvatar.setForeground(Color.GRAY);
		lblQuantityAvatar.setFont(new Font("Arial", Font.PLAIN, 11));
		
		lblFullname = new JLabel("Fullname");
		lblFullname.setForeground(Color.GRAY);
		lblFullname.setFont(new Font("Arial", Font.BOLD, 14));
		
		lblAddress = new JLabel("Temporary address");
		lblAddress.setForeground(Color.GRAY);
		lblAddress.setFont(new Font("Arial", Font.BOLD, 14));
		
		lblPhone = new JLabel("Phone");
		lblPhone.setForeground(Color.GRAY);
		lblPhone.setFont(new Font("Arial", Font.BOLD, 14));
		
		lblDob = new JLabel("Date Of Birth");
		lblDob.setForeground(Color.GRAY);
		lblDob.setFont(new Font("Arial", Font.BOLD, 14));
		
		lblGender = new JLabel("Gender");
		lblGender.setForeground(Color.GRAY);
		lblGender.setFont(new Font("Arial", Font.BOLD, 14));
		
		lblCIC = new JLabel("Citizen Identification Card");
		lblCIC.setForeground(Color.GRAY);
		lblCIC.setFont(new Font("Arial", Font.BOLD, 14));
		
		btnUpAvatar.setForeground(Color.BLACK);
		btnUpAvatar.setFont(new Font("Arial", Font.BOLD, 11));
		btnUpAvatar.setFocusable(false);
		btnUpAvatar.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY));
		btnUpAvatar.setBackground(Color.LIGHT_GRAY);
		
		lblImgAvatar.setOpaque(true);
		lblImgAvatar.setBackground(Color.WHITE);
		
		buttonGroup.add(rdFemale);
		rdFemale.setFont(new Font("Arial", Font.BOLD, 12));
		rdFemale.setBackground(Color.WHITE);
		
		buttonGroup.add(rdMale);
		rdMale.setFont(new Font("Arial", Font.BOLD, 12));
		rdMale.setBackground(Color.WHITE);
		
		txtFullname = new JTextField();
		txtFullname.setColumns(10);
		txtFullname.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY));
		
		btnSaveAndNext.setForeground(Color.WHITE);
		btnSaveAndNext.setFont(new Font("Arial", Font.BOLD, 12));
		btnSaveAndNext.setFocusable(false);
		btnSaveAndNext.setBorder(null);
		
		lblImgCIC2.setOpaque(true);
		lblImgCIC2.setBackground(Color.ORANGE);
		
		lblImgCIC1.setOpaque(true);
		lblImgCIC1.setBackground(Color.ORANGE);
		
		txtCIC = new JTextField();
		txtCIC.setColumns(10);
		txtCIC.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY,Color.LIGHT_GRAY));
		
		txtIAuthority = new JTextField();
		txtIAuthority.setColumns(10);
		txtIAuthority.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, Color.LIGHT_GRAY,Color.LIGHT_GRAY, Color.LIGHT_GRAY));
		
		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		txtAddress.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY,Color.LIGHT_GRAY));
		
		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		txtPhone.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY,Color.LIGHT_GRAY));
		
		btnUpCIC.setForeground(Color.BLACK);
		btnUpCIC.setFont(new Font("Arial", Font.BOLD, 11));
		btnUpCIC.setFocusable(false);
		btnUpCIC.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY));
		btnUpCIC.setBackground(Color.LIGHT_GRAY);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblFullname, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
							.addGap(149)
							.addComponent(txtFullname, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblAddress, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
							.addGap(57)
							.addComponent(txtAddress, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblPhone, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
							.addGap(94)
							.addComponent(txtPhone, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblDob, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
							.addGap(94)
							.addComponent(dateDob, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblGender, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
							.addGap(92)
							.addComponent(rdMale, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
							.addGap(28)
							.addComponent(rdFemale, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblCIC, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
							.addGap(20)
							.addComponent(txtCIC, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblIAuthority, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(1)
									.addComponent(lblQuantityImgCIC, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)))
							.addGap(57)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtIAuthority, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addComponent(btnSaveAndNext, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(btnUpCIC, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
										.addGap(10)
										.addComponent(lblImgCIC1, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
										.addGap(10)
										.addComponent(lblImgCIC2, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblAddRenter, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblQuantityAvatar, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblAvatar, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE))
									.addGap(80)
									.addComponent(btnUpAvatar, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(lblImgAvatar, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)))))
					.addGap(20))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAddRenter, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(25)
							.addComponent(lblQuantityAvatar))
						.addComponent(lblAvatar, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(btnUpAvatar, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(lblImgAvatar, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)))
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblFullname, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(txtFullname, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)))
					.addGap(17)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAddress, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtAddress, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(lblPhone, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtPhone, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addGap(17)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(6)
							.addComponent(lblDob, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
						.addComponent(dateDob, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(14)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(6)
							.addComponent(lblGender, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
						.addComponent(rdMale)
						.addComponent(rdFemale))
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblCIC, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtCIC, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(7)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblIAuthority, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(18)
									.addComponent(lblQuantityImgCIC))))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(txtIAuthority, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnUpCIC, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblImgCIC1, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblImgCIC2, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))))
					.addGap(27)
					.addComponent(btnSaveAndNext, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(28, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		
	}
	
	
}
