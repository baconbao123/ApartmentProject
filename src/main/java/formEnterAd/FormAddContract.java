package formEnterAd;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.SystemColor;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import com.toedter.calendar.JDateChooser;

import dao.ApartmentDao;
import dao.ContractDao;
import dao.UserDao;
import entity.Contract;
import entity.Users;
import view.CardRoom;

import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import javax.swing.JScrollPane;
import java.awt.CardLayout;

public class FormAddContract extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblAddContract;
	private JLabel lblApartNum;
	private JLabel lblOwnerNum;
	private JComboBox cbbOwner;
	private JLabel lblContractImgs;
	private JLabel lblUpTo;
	private JButton btnUploadContract;
	private JLabel lblImgCon1;
	private JLabel lblImgCon2;
	private JLabel lblImgCon3;
	private JLabel lblImgCon4;
	private JLabel lblImgCon5;
	private JLabel lblStatus;
	private JRadioButton rdbOnContract;
	private JRadioButton rdbtnOffContract;
	private JLabel lblFromDate;
	private JDateChooser dateFromDate;
	private JLabel lblToDate;
	private JDateChooser dateToDate;
	private JLabel lblRoomates;
	private JComboBox cbbRoomates;
	private JButton btnSave;
	private List<String> roomatesList = new ArrayList<>();
	private List<String> filePathList = new ArrayList<>();
	private List<String> newAvatarFilePathList = new ArrayList<>();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblReadApartNum;
	private JPanel panelRoomate;
	private JPanel panelFourRenter;
	private JPanel panelFiveRenter;
	private JLabel lblInfoRoomates1;
	private JLabel lblInfoRoomates2;
	private JLabel lblInfoRoomates3;
	private JLabel lblInfoRoomates4;
	private JLabel lblBtnDelete1;
	private JLabel lblBtnDelete2;
	private JLabel lblBtnDelete3;
	private JLabel lblBtnDelete4;
	private JLabel lblInfoRoomates2_1;
	private JLabel lblInfoRoomates2_2;
	private JLabel lblInfoRoomates2_3;
	private JLabel lblInfoRoomates2_4;
	private JLabel lblInfoRoomates2_5;
	private JLabel lblBtnDelete2_1;
	private JLabel lblBtnDelete2_2;
	private JLabel lblBtnDelete2_3;
	private JLabel lblBtnDelete2_4;
	private JLabel lblBtnDelete2_5;
	private JLabel lblApartmentId;
	private JLabel ReadIDApart;

	/**
	 * Create the panel.
	 */
//	public FormAddContract() {};
	public FormAddContract() {
		setBackground(Color.WHITE);
		var daoUser = new UserDao();
		var dao = new ApartmentDao();
		
		lblReadApartNum = new JLabel("");
		lblReadApartNum.setBounds(168, 49, 55, 20);
		
		// onwer name
		cbbOwner = new JComboBox();
		cbbOwner.setBounds(168, 89, 315, 19);
		cbbOwner.setModel(new DefaultComboBoxModel(new String[] { "" }));
		List<Object> renterInfo = daoUser.selRenterName();
		for (Object renter : renterInfo) {
			cbbOwner.addItem(renter);
		}
		
		
		btnUploadContract = new JButton("Upload");
		btnUploadContract.setBounds(168, 126, 55, 38);
		btnUploadContract.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnUploadContractActionPerformed(e);
			}
		});
		
		
		
		ReadIDApart = new JLabel("");

		
		rdbOnContract = new JRadioButton("On");
		rdbOnContract.setBounds(168, 185, 33, 15);
		buttonGroup.add(rdbOnContract);
		
		rdbtnOffContract = new JRadioButton("Off");
		rdbtnOffContract.setBounds(235, 182, 35, 15);
		buttonGroup.add(rdbtnOffContract);
		
		
		
		// from date
		dateFromDate = new JDateChooser();
		dateFromDate.setBounds(168, 212, 315, 19);
		dateFromDate.setName("JDateFromDate");
		dateFromDate.setDateFormatString("yyyy-MM-dd");
		
		// to date
		dateToDate = new JDateChooser();
		dateToDate.setBounds(168, 249, 315, 19);
		dateToDate.setName("JDateToDate");
		dateToDate.setDateFormatString("yyyy-MM-dd");
		
		
		// roomates
		panelRoomate = new JPanel();
		panelRoomate.setBounds(168, 324, 321, 137);
		panelRoomate.setLayout(new CardLayout(0, 0));
		panelFourRenter = new JPanel();
		panelRoomate.add(panelFourRenter, "panelFourRoomate");
		panelFiveRenter = new JPanel();
		panelRoomate.add(panelFiveRenter, "panelFiveRoomate");
		
		CardLayout layout = (CardLayout) panelRoomate.getLayout();
		layout.show(panelRoomate, "panelFourRoomate");
		
		Map<Integer, Users> renterMap = new HashMap<>();
		cbbRoomates = new JComboBox();
		cbbRoomates.setBounds(168, 287, 315, 19);
		cbbRoomates.setModel(new DefaultComboBoxModel(new String[] { "" }));
		for (Object renter : renterInfo) {
			Users renterObject = (Users) renter;
			cbbRoomates.addItem(renterObject.getId() + "-" + renterObject.getName() + "-" + renterObject.getPhone()
					+ "-" + renterObject.getDob() + "-" + renterObject.getNic());
			renterMap.put(renterObject.getId(), renterObject);
		}
		
		cbbRoomates.addActionListener(new ActionListener() {
			int selectCount = 0;

			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox combobox = (JComboBox) e.getSource();
				String selectRoomates = (String) cbbRoomates.getSelectedItem();
				String[] parts = selectRoomates.split("-");

				if (selectCount < 4) {
					int roomatesID = Integer.parseInt(parts[0]);

					switch (selectCount) {
						case 0: {
							lblInfoRoomates1.setText(selectRoomates);
							lblBtnDelete1.setVisible(true);
							lblBtnDelete1.addMouseListener(new MouseAdapter() {
								public void mouseClicked(java.awt.event.MouseEvent e) {
									lblInfoRoomates1.setText("");
									lblBtnDelete1.setVisible(false);
									selectCount = 0;
									roomatesList.remove(String.valueOf(roomatesID));
								};
							});
							break;
						}
						case 1: {
							lblInfoRoomates2.setText(selectRoomates);
							lblBtnDelete2.setVisible(true);
							lblBtnDelete2.addMouseListener(new MouseAdapter() {
								public void mouseClicked(java.awt.event.MouseEvent e) {
									lblInfoRoomates2.setText("");
									lblBtnDelete2.setVisible(false);
									selectCount = 1;
									roomatesList.remove(String.valueOf(roomatesID));
								};
							});
							break;
						}
						case 2: {
							lblInfoRoomates3.setText(selectRoomates);
							lblBtnDelete3.setVisible(true);
							lblBtnDelete3.addMouseListener(new MouseAdapter() {
								public void mouseClicked(java.awt.event.MouseEvent e) {
									lblInfoRoomates3.setText("");
									lblBtnDelete3.setVisible(false);
									selectCount = 2;
									roomatesList.remove(String.valueOf(roomatesID));
								};
							});
							break;
						}
						case 3: {
							lblInfoRoomates4.setText(selectRoomates);
							lblBtnDelete4.setVisible(true);
							lblBtnDelete4.addMouseListener(new MouseAdapter() {
								public void mouseClicked(java.awt.event.MouseEvent e) {
									lblInfoRoomates4.setText("");
									lblBtnDelete4.setVisible(false);
									selectCount = 3;
									roomatesList.remove(String.valueOf(roomatesID));
								};
							});
							break;
						}
					}
					roomatesList.add(String.valueOf(roomatesID));
					selectCount++;
				}

			}

		});
		
		
		
		
		
		btnSave = new JButton("Save Contract");
		btnSave.setBounds(260, 486, 133, 29);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSaveActionPerformed(e);
			}
		});
		
		
		


		initComponent();
	}
	
	protected void btnUploadContractActionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setMultiSelectionEnabled(true);
		fileChooser.setFileFilter(new FileNameExtensionFilter("Image files", "png", "jpg"));
		fileChooser.setCurrentDirectory(new File("C:\\Users"));
		int result = fileChooser.showOpenDialog(FormAddContract.this);

		if (result == JFileChooser.APPROVE_OPTION) {
			File[] selectedFiles = fileChooser.getSelectedFiles();

			for (int i = 0; i < Math.min(selectedFiles.length, 5); i++) {
				File file = selectedFiles[i];
				String fileName = file.getName();
				String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
				String newAvatarFilePath = "images/contract_" + System.currentTimeMillis() + "." + extension;
				String filePath = file.getAbsolutePath();

				filePathList.add(filePath);
				newAvatarFilePathList.add(newAvatarFilePath);

				switch (i) {
				case 0:
					lblImgCon1.setIcon(new ImageIcon(
							new ImageIcon(filePath).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
					break;

				case 1:
					lblImgCon2.setIcon(new ImageIcon(
							new ImageIcon(filePath).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
					break;

				case 2:
					lblImgCon3.setIcon(new ImageIcon(
							new ImageIcon(filePath).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
					break;

				case 3:
					lblImgCon4.setIcon(new ImageIcon(
							new ImageIcon(filePath).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
					break;

				case 4:
					lblImgCon5.setIcon(new ImageIcon(
							new ImageIcon(filePath).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
					break;
				}

			}
		}
	}
	
	protected void btnSaveActionPerformed(ActionEvent e) {
		var contract = new Contract();
		var dao = new ContractDao();


		Users ownerApart = (Users) cbbOwner.getSelectedItem();
		int ownerID = ownerApart.getId();

		java.sql.Date sqlFromDate = new java.sql.Date(dateFromDate.getDate().getTime());
		java.sql.Date sqlToDate = new java.sql.Date(dateToDate.getDate().getTime());

		contract.setApartNum(Integer.parseInt(ReadIDApart.getText())); // id room
		contract.setOwnerID(ownerID);

		if (rdbOnContract.isSelected()) {
			contract.setStatus(true);
		} else {
			contract.setStatus(false);
		}
		
		contract.setFormDate(sqlFromDate);
		contract.setToDate(sqlToDate);
		
		String romateString = String.join(";", roomatesList);
		contract.setRoomates(romateString);
		
		String imgFilePathString = String.join(";", newAvatarFilePathList);
		contract.setImgContracs(imgFilePathString);
		dao.insertContract(contract);

		System.out.println("success insert contract");

		for (int i = 0; i < Math.min(filePathList.size(), 5); i++) {
			try {
				Files.copy(new File(filePathList.get(i)).toPath(), new File(newAvatarFilePathList.get(i)).toPath(),
						StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	// nhận số phòng từ cardRoom
	public void setApartNumber(String apartNumber) {
        lblReadApartNum.setText(apartNumber);
    }
	
	public void setAparId(String id) {
		ReadIDApart.setText(id);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	private void initComponent() {	
		lblImgCon1 = new JLabel("");
		lblImgCon1.setBounds(233, 126, 43, 38);
		lblImgCon2 = new JLabel("");
		lblImgCon2.setBounds(280, 126, 43, 38);
		lblImgCon3 = new JLabel("");
		lblImgCon3.setBounds(329, 126, 43, 38);
		lblImgCon4 = new JLabel("");
		lblImgCon4.setBounds(378, 126, 43, 38);
		lblImgCon5 = new JLabel("");
		lblImgCon5.setBounds(427, 126, 43, 38);
		
		lblAddContract = new JLabel("Add Contract");
		lblAddContract.setBounds(200, 11, 118, 26);
		lblAddContract.setForeground(Color.GRAY);
		lblAddContract.setFont(new Font("Arial", Font.BOLD, 16));
		
		lblApartNum = new JLabel("Apartment Number");
		lblApartNum.setBounds(20, 49, 138, 19);
		lblApartNum.setForeground(Color.GRAY);
		lblApartNum.setFont(new Font("Arial", Font.BOLD, 14));
		
		lblOwnerNum = new JLabel("New Owner Name");
		lblOwnerNum.setBounds(20, 88, 138, 19);
		lblOwnerNum.setForeground(Color.GRAY);
		lblOwnerNum.setFont(new Font("Arial", Font.BOLD, 14));
		
		cbbOwner.setFont(new Font("Arial", Font.PLAIN, 13));
		cbbOwner.setBorder(null);
		cbbOwner.setBackground(SystemColor.window);
		
		lblContractImgs = new JLabel("New Contract Images");
		lblContractImgs.setBounds(20, 126, 138, 19);
		lblContractImgs.setForeground(Color.GRAY);
		lblContractImgs.setFont(new Font("Arial", Font.BOLD, 14));
		
		lblUpTo = new JLabel("Up to 5 images(png or jpg)");
		lblUpTo.setBounds(20, 151, 138, 13);
		lblUpTo.setForeground(Color.GRAY);
		lblUpTo.setFont(new Font("Arial", Font.PLAIN, 11));
		
		btnUploadContract.setForeground(SystemColor.window);
		btnUploadContract.setFont(new Font("Arial", Font.BOLD, 12));
		btnUploadContract.setBorder(null);
		btnUploadContract.setBackground(SystemColor.activeCaption);
		
		lblImgCon1.setOpaque(true);
		lblImgCon1.setBackground(SystemColor.info);
		
		lblImgCon2.setOpaque(true);
		lblImgCon2.setBackground(SystemColor.info);
		
		lblImgCon3.setOpaque(true);
		lblImgCon3.setBackground(SystemColor.info);
		
		lblImgCon4.setOpaque(true);
		lblImgCon4.setBackground(SystemColor.info);

		lblImgCon5.setOpaque(true);
		lblImgCon5.setBackground(SystemColor.info);
		
		lblStatus = new JLabel("Status");
		lblStatus.setBounds(20, 182, 138, 19);
		lblStatus.setForeground(Color.GRAY);
		lblStatus.setFont(new Font("Arial", Font.BOLD, 14));

		rdbOnContract.setFont(new Font("Arial", Font.BOLD, 12));
		rdbOnContract.setBorder(null);
		rdbOnContract.setBackground(SystemColor.window);
		
		rdbtnOffContract.setFont(new Font("Arial", Font.BOLD, 12));
		rdbtnOffContract.setBorder(null);
		rdbtnOffContract.setBackground(SystemColor.window);
		
		lblFromDate = new JLabel("From Date");
		lblFromDate.setBounds(20, 212, 138, 19);
		lblFromDate.setForeground(Color.GRAY);
		lblFromDate.setFont(new Font("Arial", Font.BOLD, 14));
		
		lblToDate = new JLabel("To Date");
		lblToDate.setBounds(20, 249, 138, 19);
		lblToDate.setForeground(Color.GRAY);
		lblToDate.setFont(new Font("Arial", Font.BOLD, 14));
		
		lblRoomates = new JLabel("Roomates");
		lblRoomates.setBounds(20, 286, 138, 19);
		lblRoomates.setForeground(Color.GRAY);
		lblRoomates.setFont(new Font("Arial", Font.BOLD, 14));
		
		cbbRoomates.setFont(new Font("Arial", Font.PLAIN, 13));
		cbbRoomates.setBorder(null);
		cbbRoomates.setBackground(SystemColor.window);
		
		btnSave.setForeground(SystemColor.window);
		btnSave.setFont(new Font("Arial", Font.BOLD, 12));
		btnSave.setFocusable(false);
		btnSave.setBorder(null);
		btnSave.setBackground(new Color(64, 128, 128));
		
		
		lblReadApartNum.setForeground(Color.BLACK);
		lblReadApartNum.setFont(new Font("Arial", Font.PLAIN, 12));
		
		
		panelRoomate.setBackground(SystemColor.controlHighlight);
		
		
		panelFourRenter.setBackground(SystemColor.controlHighlight);
		panelFourRenter.setLayout(null);
		
		lblInfoRoomates1 = new JLabel("");
		lblInfoRoomates1.setOpaque(true);
		lblInfoRoomates1.setBackground(SystemColor.controlHighlight);
		lblInfoRoomates1.setBounds(10, 0, 284, 24);
		panelFourRenter.add(lblInfoRoomates1);
		
		lblInfoRoomates2 = new JLabel("");
		lblInfoRoomates2.setOpaque(true);
		lblInfoRoomates2.setBackground(SystemColor.controlHighlight);
		lblInfoRoomates2.setBounds(10, 30, 284, 24);
		panelFourRenter.add(lblInfoRoomates2);
		
		lblInfoRoomates3 = new JLabel("");
		lblInfoRoomates3.setOpaque(true);
		lblInfoRoomates3.setBackground(SystemColor.controlHighlight);
		lblInfoRoomates3.setBounds(10, 60, 284, 24);
		panelFourRenter.add(lblInfoRoomates3);
		
		lblInfoRoomates4 = new JLabel("");
		lblInfoRoomates4.setOpaque(true);
		lblInfoRoomates4.setBackground(SystemColor.controlHighlight);
		lblInfoRoomates4.setBounds(10, 90, 284, 24);
		panelFourRenter.add(lblInfoRoomates4);
		
		lblBtnDelete1 = new JLabel("");
		lblBtnDelete1.setVisible(false);
		lblBtnDelete1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblBtnDelete1.setIcon(new ImageIcon(FormAddContract.class.getResource("/icon/cross.png")));
		lblBtnDelete1.setBounds(299, 0, 22, 24);
		panelFourRenter.add(lblBtnDelete1);
		
		lblBtnDelete2 = new JLabel("");
		lblBtnDelete2.setVisible(false);
		lblBtnDelete2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblBtnDelete2.setIcon(new ImageIcon(FormAddContract.class.getResource("/icon/cross.png")));
		lblBtnDelete2.setBounds(299, 30, 22, 24);
		panelFourRenter.add(lblBtnDelete2);
		
		lblBtnDelete3 = new JLabel("");
		lblBtnDelete3.setVisible(false);
		lblBtnDelete3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblBtnDelete3.setIcon(new ImageIcon(FormAddContract.class.getResource("/icon/cross.png")));
		lblBtnDelete3.setBounds(299, 60, 22, 24);
		panelFourRenter.add(lblBtnDelete3);
		
		lblBtnDelete4 = new JLabel("");
		lblBtnDelete4.setVisible(false);
		lblBtnDelete4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblBtnDelete4.setIcon(new ImageIcon(FormAddContract.class.getResource("/icon/cross.png")));
		lblBtnDelete4.setBounds(299, 90, 22, 24);
		panelFourRenter.add(lblBtnDelete4);
		
		
		panelFiveRenter.setBackground(SystemColor.controlHighlight);
		panelFiveRenter.setLayout(null);
		
		lblInfoRoomates2_1 = new JLabel("");
		lblInfoRoomates2_1.setOpaque(true);
		lblInfoRoomates2_1.setBackground(SystemColor.controlHighlight);
		lblInfoRoomates2_1.setBounds(10, 0, 284, 24);
		panelFiveRenter.add(lblInfoRoomates2_1);
		
		lblInfoRoomates2_2 = new JLabel("");
		lblInfoRoomates2_2.setOpaque(true);
		lblInfoRoomates2_2.setBackground(SystemColor.controlHighlight);
		lblInfoRoomates2_2.setBounds(10, 28, 284, 24);
		panelFiveRenter.add(lblInfoRoomates2_2);
		
		lblInfoRoomates2_3 = new JLabel("");
		lblInfoRoomates2_3.setOpaque(true);
		lblInfoRoomates2_3.setBackground(SystemColor.controlHighlight);
		lblInfoRoomates2_3.setBounds(10, 56, 284, 24);
		panelFiveRenter.add(lblInfoRoomates2_3);
		
		lblInfoRoomates2_4 = new JLabel("");
		lblInfoRoomates2_4.setOpaque(true);
		lblInfoRoomates2_4.setBackground(SystemColor.controlHighlight);
		lblInfoRoomates2_4.setBounds(10, 84, 284, 24);
		panelFiveRenter.add(lblInfoRoomates2_4);
		
		lblInfoRoomates2_5 = new JLabel("");
		lblInfoRoomates2_5.setOpaque(true);
		lblInfoRoomates2_5.setBackground(SystemColor.controlHighlight);
		lblInfoRoomates2_5.setBounds(10, 112, 284, 24);
		panelFiveRenter.add(lblInfoRoomates2_5);
		
		lblBtnDelete2_1 = new JLabel("");
		lblBtnDelete2_1.setVisible(false);
		lblBtnDelete2_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblBtnDelete2_1.setIcon(new ImageIcon(FormAddContract.class.getResource("/icon/cross.png")));
		lblBtnDelete2_1.setBounds(299, 0, 22, 24);
		panelFiveRenter.add(lblBtnDelete2_1);
		
		lblBtnDelete2_2 = new JLabel("");
		lblBtnDelete2_2.setVisible(false);
		lblBtnDelete2_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblBtnDelete2_2.setIcon(new ImageIcon(FormAddContract.class.getResource("/icon/cross.png")));
		lblBtnDelete2_2.setBounds(299, 30, 22, 22);
		panelFiveRenter.add(lblBtnDelete2_2);
		
		lblBtnDelete2_3 = new JLabel("");
		lblBtnDelete2_3.setVisible(false);
		lblBtnDelete2_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblBtnDelete2_3.setIcon(new ImageIcon(FormAddContract.class.getResource("/icon/cross.png")));
		lblBtnDelete2_3.setBounds(299, 56, 22, 24);
		panelFiveRenter.add(lblBtnDelete2_3);
		
		lblBtnDelete2_4 = new JLabel("");
		lblBtnDelete2_4.setVisible(false);
		lblBtnDelete2_4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblBtnDelete2_4.setIcon(new ImageIcon(FormAddContract.class.getResource("/icon/cross.png")));
		lblBtnDelete2_4.setBounds(299, 84, 22, 24);
		panelFiveRenter.add(lblBtnDelete2_4);
		
		lblBtnDelete2_5 = new JLabel("");
		lblBtnDelete2_5.setVisible(false);
		lblBtnDelete2_5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblBtnDelete2_5.setIcon(new ImageIcon(FormAddContract.class.getResource("/icon/cross.png")));
		lblBtnDelete2_5.setBounds(299, 112, 22, 24);
		panelFiveRenter.add(lblBtnDelete2_5);
		setLayout(null);
		add(lblAddContract);
		add(lblFromDate);
		add(dateFromDate);
		add(lblToDate);
		add(dateToDate);
		add(lblStatus);
		add(rdbOnContract);
		add(rdbtnOffContract);
		add(lblContractImgs);
		add(lblUpTo);
		add(btnUploadContract);
		add(lblImgCon1);
		add(lblImgCon2);
		add(lblImgCon3);
		add(lblImgCon4);
		add(lblImgCon5);
		add(lblApartNum);
		add(lblReadApartNum);
		add(lblOwnerNum);
		add(cbbOwner);
		add(lblRoomates);
		add(panelRoomate);
		add(cbbRoomates);
		add(btnSave);
		
		lblApartmentId = new JLabel("Apartment ID");
		lblApartmentId.setForeground(Color.GRAY);
		lblApartmentId.setFont(new Font("Arial", Font.BOLD, 14));
		lblApartmentId.setBounds(246, 49, 90, 19);
		add(lblApartmentId);
		
		
		ReadIDApart.setForeground(Color.BLACK);
		ReadIDApart.setFont(new Font("Arial", Font.PLAIN, 12));
		ReadIDApart.setBounds(344, 49, 90, 19);
		add(ReadIDApart);
	}
}
