package formUpdateAd;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JButton;
import java.awt.SystemColor;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JRadioButton;
import com.toedter.calendar.JDateChooser;

import dao.ApartmentDao;
import dao.ContractDao;
import dao.UserDao;
import entity.Contract;
import entity.Users;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;

public class FrameUpContract extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblUpdateContractInformation;
	private JLabel lblApartNum;
	private JLabel lblOwnerNum;
	private JComboBox cbbOwner;
	private JLabel lblContractImgs;
	private JButton btnUploadContract;
	private JLabel lblUpTo;
	private JLabel lblImgCon1;
	private JLabel lblImgCon2;
	private JLabel lblImgCon3;
	private JLabel lblImgCon4;
	private JLabel lblImgCon5;
	private JLabel lblStatus;
	private JRadioButton rdbOnContract;
	private JRadioButton rdbtnOffContract;
	private JLabel lblFromDate;
	private JLabel lblToDate;
	private JLabel lblRoomates;
	private JDateChooser dateFromDate;
	private JDateChooser dateToDate;
	private JComboBox cbbRoomates;
	private JButton btnSave;
	private JLabel lblReadNum;
	private JLabel lblWarning;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	private List<String> conPathList = new ArrayList<>();
	private List<String> conImgList = new ArrayList<>();
	private JPanel panelRoomate;
	private JPanel FourRoomate;
	private JPanel FiveRoomate;
	private JLabel lblInfoRoomates1;
	private JLabel lblInfoRoomates2;
	private JLabel lblInfoRoomates3;
	private JLabel lblInfoRoomates4;
	private JLabel lblDelete1;
	private JLabel lblDelete2;
	private JLabel lblDelete3;
	private JLabel lblDelete4;
	private JLabel lblInfoRoomates2_1;
	private JLabel lblInfoRoomates2_2;
	private JLabel lblInfoRoomates2_3;
	private JLabel lblInfoRoomates2_4;
	private JLabel lblDelete2_1;
	private JLabel lblDelete2_2;
	private JLabel lblDelete2_3;
	private JLabel lblDelete2_4;
	private JLabel lblInfoRoomates2_5;
	private JLabel lblDelete2_5;

	private List<String> roomatesList = new ArrayList<>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameUpContract frame = new FrameUpContract();
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
	public FrameUpContract() {};
	public FrameUpContract(Integer id, String apartNum, Date dateFrom, Date dateTo, Boolean status, Integer ownerID) {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 504, 663);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		var daoAp = new ApartmentDao();
		var daoUser = new UserDao();
		
		List<Object> renterInfor = daoUser.selRenterName();
				
		lblReadNum = new JLabel("101");
		
		
		

		//owner
		cbbOwner = new JComboBox();
		cbbOwner.setModel(new DefaultComboBoxModel());
		for(Object renter: renterInfor) {
			Users renterObj = (Users) renter; 
	        cbbOwner.addItem(renterObj); 
	        if (renterObj.getId() == ownerID) {
	            cbbOwner.setSelectedItem(renterObj); 
	        }
		}

		
		btnUploadContract = new JButton("Upload");
		btnUploadContract.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnUploadContractActionPerformed(e);
			}
		});
		
		lblImgCon1 = new JLabel("");
		lblImgCon2 = new JLabel("");
		lblImgCon3 = new JLabel("");
		lblImgCon4 = new JLabel("");
		lblImgCon5 = new JLabel("");
		
		rdbOnContract = new JRadioButton("On");
		buttonGroup.add(rdbOnContract);
		
		rdbtnOffContract = new JRadioButton("Off");
		buttonGroup.add(rdbtnOffContract);
		
		if(status) {
			rdbOnContract.setSelected(true);
		} else {
			rdbtnOffContract.setSelected(true);
		}

		
		dateFromDate = new JDateChooser();
		dateFromDate.setDateFormatString("yyyy-MM-dd");
		
		dateToDate = new JDateChooser();
		dateToDate.setDateFormatString("yyyy-MM-dd");
		
		//roomate
		Map<Integer, Users> renterMap = new HashMap<>();
		cbbRoomates = new JComboBox();
		cbbRoomates.setModel(new DefaultComboBoxModel(new String[] {""}));
		for(Object renter: renterInfor) {
			Users renterObject = (Users) renter;
			cbbRoomates.addItem(renterObject.getId() + "-" + renterObject.getName() + "-" + renterObject.getPhone()
				+ "-" + renterObject.getDob() + "-" + renterObject.getNic());
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
						lblDelete1.setVisible(true);
						lblDelete1.addMouseListener(new MouseAdapter() {
							public void mouseClicked(java.awt.event.MouseEvent e) {
								lblInfoRoomates1.setText("");
								lblDelete1.setVisible(false);
								selectCount = 0;
								roomatesList.remove(String.valueOf(roomatesID));
							};
						});
						break;
					}
					case 1: {
						lblInfoRoomates2.setText(selectRoomates);
						lblDelete2.setVisible(true);
						lblDelete2.addMouseListener(new MouseAdapter() {
							public void mouseClicked(java.awt.event.MouseEvent e) {
								lblInfoRoomates2.setText("");
								lblDelete2.setVisible(false);
								selectCount = 1;
								roomatesList.remove(String.valueOf(roomatesID));
							};
						});
						break;
					}
					case 2: {
						lblInfoRoomates3.setText(selectRoomates);
						lblDelete3.setVisible(true);
						lblDelete3.addMouseListener(new MouseAdapter() {
							public void mouseClicked(java.awt.event.MouseEvent e) {
								lblInfoRoomates3.setText("");
								lblDelete3.setVisible(false);
								selectCount = 2;
								roomatesList.remove(String.valueOf(roomatesID));
							};
						});
						break;
					}
					case 3: {
						lblInfoRoomates4.setText(selectRoomates);
						lblDelete4.setVisible(true);
						lblDelete4.addMouseListener(new MouseAdapter() {
							public void mouseClicked(java.awt.event.MouseEvent e) {
								lblInfoRoomates4.setText("");
								lblDelete4.setVisible(false);
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
		
		panelRoomate = new JPanel();
		panelRoomate.setBackground(SystemColor.controlHighlight);
		panelRoomate.setLayout(new CardLayout(0, 0));
		FourRoomate = new JPanel();
		FourRoomate.setBackground(SystemColor.controlHighlight);
		panelRoomate.add(FourRoomate, "fourRoomate");
		FiveRoomate = new JPanel();
		FiveRoomate.setBackground(SystemColor.controlHighlight);
		panelRoomate.add(FiveRoomate, "fiveRoomate");
		
		CardLayout layout = (CardLayout) panelRoomate.getLayout();
		layout.show(panelRoomate, "fourRoomate");
		
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var contract = new Contract();
				var dao = new ContractDao();
				
				Users ownerApUsers = (Users) cbbOwner.getSelectedItem();
				int ownerID = ownerApUsers.getId();
				
				java.sql.Date sqlFromDate = new java.sql.Date(dateFromDate.getDate().getTime());
				java.sql.Date sqlToDate = new java.sql.Date(dateToDate.getDate().getTime());
				
				String romateString = String.join(";", roomatesList);
				
				String imgFilePathString = String.join(";", conImgList);
				
				contract.setId(id);
				contract.setOwnerID(ownerID);
				
				if(rdbOnContract.isSelected()) {
					contract.setStatus(true);
				} else if (rdbtnOffContract.isSelected()) {
					contract.setStatus(false);
				}
				contract.setFormDate(sqlFromDate);
				contract.setToDate(sqlToDate);
				contract.setRoomates(romateString);
				contract.setImgContracs(imgFilePathString);
				
				dao.updateContract(contract);
				System.out.println("success insert contract");
				
				for (int i = 0; i < Math.min(conPathList.size(), 5); i++) {
					try {
						Files.copy(new File(conPathList.get(i)).toPath(), new File(conImgList.get(i)).toPath(),
								StandardCopyOption.REPLACE_EXISTING);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				dispose();
			}
			
		});
		
		initComponent();
		
		
		lblReadNum.setText(apartNum);
		
		dateFromDate.setDate(dateFrom);
		dateToDate.setDate(dateTo);
		
		
		
	}
	
	protected void btnUploadContractActionPerformed(ActionEvent e) {
		var chooser = new JFileChooser();
		chooser.setMultiSelectionEnabled(true);
		chooser.setFileFilter(new FileNameExtensionFilter("Image files", "png", "jpg"));
		chooser.setCurrentDirectory(new File("C:\\Users"));
		int result = chooser.showOpenDialog(FrameUpContract.this);
		
		if(result==JFileChooser.APPROVE_OPTION) {
			File[] selectedFiles = chooser.getSelectedFiles();
			
			for(int i=0; i<Math.min(selectedFiles.length, 5); i++) {
				File file = selectedFiles[i];
				String filename = file.getName();
				String extension = filename.substring(filename.lastIndexOf(".")+1);
				String newConFilePath = "images/contract_" + System.currentTimeMillis() + "." + extension;
				String filePath = file.getAbsolutePath();
				
				conPathList.add(filePath);
				conImgList.add(newConFilePath);
				
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
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	private void initComponent() {
		lblReadNum.setBounds(158, 101, 195, 20);
		
		cbbOwner.setBounds(158, 141, 315, 19);
		
		lblUpdateContractInformation = new JLabel("Update Contract Information");
		lblUpdateContractInformation.setBounds(131, 16, 246, 24);
		lblUpdateContractInformation.setFont(new Font("Arial", Font.BOLD, 18));
		
		lblApartNum = new JLabel("Apartment Number");
		lblApartNum.setBounds(10, 102, 138, 19);
		lblApartNum.setForeground(Color.GRAY);
		lblApartNum.setFont(new Font("Arial", Font.BOLD, 14));
		
		
		lblOwnerNum = new JLabel("Owner Name");
		lblOwnerNum.setBounds(10, 140, 138, 19);
		lblOwnerNum.setForeground(Color.GRAY);
		lblOwnerNum.setFont(new Font("Arial", Font.BOLD, 14));
		
		cbbOwner.setFont(new Font("Arial", Font.PLAIN, 13));
		cbbOwner.setBorder(null);
		cbbOwner.setBackground(SystemColor.window);
		
		lblContractImgs = new JLabel("Contract Images");
		lblContractImgs.setBounds(10, 178, 138, 19);
		lblContractImgs.setForeground(Color.GRAY);
		lblContractImgs.setFont(new Font("Arial", Font.BOLD, 14));

		btnUploadContract.setBounds(158, 178, 55, 38);
		btnUploadContract.setForeground(SystemColor.window);
		btnUploadContract.setFont(new Font("Arial", Font.BOLD, 12));
		btnUploadContract.setBorder(null);
		btnUploadContract.setBackground(SystemColor.activeCaption);
		
		lblUpTo = new JLabel("Up to 5 images(png or jpg)");
		lblUpTo.setBounds(10, 203, 138, 13);
		lblUpTo.setForeground(Color.GRAY);
		lblUpTo.setFont(new Font("Arial", Font.PLAIN, 11));
		
		
		lblImgCon1.setBounds(223, 178, 43, 38);
		lblImgCon1.setOpaque(true);
		lblImgCon1.setBackground(SystemColor.info);
		
		lblImgCon2.setBounds(270, 178, 43, 38);
		lblImgCon2.setOpaque(true);
		lblImgCon2.setBackground(SystemColor.info);
		
		
		lblImgCon3.setBounds(319, 178, 43, 38);
		lblImgCon3.setOpaque(true);
		lblImgCon3.setBackground(SystemColor.info);
		
		lblImgCon4.setBounds(368, 178, 43, 38);
		lblImgCon4.setOpaque(true);
		lblImgCon4.setBackground(SystemColor.info);
		
		
		lblImgCon5.setBounds(417, 178, 43, 38);
		lblImgCon5.setOpaque(true);
		lblImgCon5.setBackground(SystemColor.info);

		lblStatus = new JLabel("Status");
		lblStatus.setBounds(10, 238, 138, 19);
		lblStatus.setForeground(Color.GRAY);
		lblStatus.setFont(new Font("Arial", Font.BOLD, 14));

		rdbOnContract.setBounds(158, 241, 33, 15);
		rdbOnContract.setFont(new Font("Arial", Font.BOLD, 12));
		rdbOnContract.setBorder(null);
		rdbOnContract.setBackground(SystemColor.window);
		
		rdbtnOffContract.setBounds(231, 241, 35, 15);
		rdbtnOffContract.setFont(new Font("Arial", Font.BOLD, 12));
		rdbtnOffContract.setBorder(null);
		rdbtnOffContract.setBackground(SystemColor.window);
		
		lblFromDate = new JLabel("From Date");
		lblFromDate.setBounds(10, 275, 138, 19);
		lblFromDate.setForeground(Color.GRAY);
		lblFromDate.setFont(new Font("Arial", Font.BOLD, 14));
		
		lblToDate = new JLabel("To Date");
		lblToDate.setBounds(10, 312, 138, 19);
		lblToDate.setForeground(Color.GRAY);
		lblToDate.setFont(new Font("Arial", Font.BOLD, 14));
		
		lblRoomates = new JLabel("Roomates");
		lblRoomates.setBounds(10, 349, 138, 19);
		lblRoomates.setForeground(Color.GRAY);
		lblRoomates.setFont(new Font("Arial", Font.BOLD, 14));
		
		dateToDate.setBounds(158, 312, 315, 19);
		dateToDate.setName("JDateToDate");
		
		dateFromDate.setBounds(158, 275, 315, 19);
		dateFromDate.setName("JDateFromDate");
		
		cbbRoomates.setFont(new Font("Arial", Font.PLAIN, 13));
		cbbRoomates.setBorder(null);
		cbbRoomates.setBackground(SystemColor.window);
		cbbRoomates.setBounds(158, 350, 315, 19);
		
		btnSave.setBounds(172, 556, 133, 29);
		btnSave.setForeground(SystemColor.window);
		btnSave.setFont(new Font("Arial", Font.BOLD, 12));
		btnSave.setFocusable(false);
		btnSave.setBorder(null);
		btnSave.setBackground(new Color(64, 128, 128));
		
		lblReadNum.setForeground(Color.BLACK);
		lblReadNum.setFont(new Font("Arial", Font.PLAIN, 12));
		contentPane.setLayout(null);
		contentPane.add(lblOwnerNum);
		contentPane.add(cbbOwner);
		contentPane.add(lblContractImgs);
		contentPane.add(lblUpTo);
		contentPane.add(btnUploadContract);
		contentPane.add(lblImgCon1);
		contentPane.add(lblImgCon2);
		contentPane.add(lblImgCon3);
		contentPane.add(lblImgCon4);
		contentPane.add(lblImgCon5);
		contentPane.add(lblStatus);
		contentPane.add(rdbOnContract);
		contentPane.add(rdbtnOffContract);
		contentPane.add(lblFromDate);
		contentPane.add(dateFromDate);
		contentPane.add(lblToDate);
		contentPane.add(dateToDate);
		contentPane.add(lblApartNum);
		contentPane.add(lblReadNum);
		contentPane.add(lblRoomates);
		contentPane.add(cbbRoomates);
		contentPane.add(lblUpdateContractInformation);
		contentPane.add(btnSave);
		
		lblWarning = new JLabel("<html>If you have any information that you don't want to update, please leave it blank. The </br>system will automatically retrieve the old information.</html>");
		lblWarning.setFont(new Font("Arial", Font.BOLD, 12));
		lblWarning.setForeground(new Color(220, 20, 60));
		lblWarning.setBounds(10, 51, 468, 29);
		contentPane.add(lblWarning);
		
		
		panelRoomate.setBounds(158, 386, 320, 144);
		contentPane.add(panelRoomate);
		
		
		
		
		lblInfoRoomates1 = new JLabel("");
		lblInfoRoomates1.setBounds(10, 0, 284, 24);
		lblInfoRoomates1.setBackground(SystemColor.controlHighlight);
		lblInfoRoomates1.setOpaque(true);
		
		lblInfoRoomates2 = new JLabel("");
		lblInfoRoomates2.setBounds(10, 30, 284, 24);
		lblInfoRoomates2.setBackground(SystemColor.controlHighlight);
		lblInfoRoomates2.setOpaque(true);
		
		lblInfoRoomates3 = new JLabel("");
		lblInfoRoomates3.setBounds(10, 60, 284, 24);
		lblInfoRoomates3.setBackground(SystemColor.controlHighlight);
		lblInfoRoomates3.setOpaque(true);
		
		lblInfoRoomates4 = new JLabel("");
		lblInfoRoomates4.setBounds(10, 90, 284, 24);
		lblInfoRoomates4.setBackground(SystemColor.controlHighlight);
		lblInfoRoomates4.setOpaque(true);
		
		lblDelete1 = new JLabel("");
		lblDelete1.setBackground(SystemColor.controlHighlight);
		lblDelete1.setBounds(299, 0, 21, 24);
		lblDelete1.setIcon(new ImageIcon(FrameUpContract.class.getResource("/icon/cross.png")));
		lblDelete1.setOpaque(true);
		lblDelete1.setVisible(false);
		lblDelete1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		lblDelete2 = new JLabel("");
		lblDelete2.setBackground(SystemColor.controlHighlight);
		lblDelete2.setBounds(298, 30, 22, 24);
		lblDelete2.setIcon(new ImageIcon(FrameUpContract.class.getResource("/icon/cross.png")));
		lblDelete2.setOpaque(true);
		lblDelete2.setVisible(false);
		lblDelete2.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		lblDelete3 = new JLabel("");
		lblDelete3.setBackground(SystemColor.controlHighlight);
		lblDelete3.setBounds(298, 60, 22, 24);
		lblDelete3.setIcon(new ImageIcon(FrameUpContract.class.getResource("/icon/cross.png")));
		lblDelete3.setOpaque(true);
		lblDelete3.setVisible(false);
		lblDelete3.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		lblDelete4 = new JLabel("");
		lblDelete4.setBackground(SystemColor.controlHighlight);
		lblDelete4.setBounds(298, 90, 22, 24);
		lblDelete4.setIcon(new ImageIcon(FrameUpContract.class.getResource("/icon/cross.png")));
		lblDelete4.setOpaque(true);
		lblDelete4.setVisible(false);
		lblDelete4.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		FourRoomate.setLayout(null);
		FourRoomate.add(lblInfoRoomates1);
		FourRoomate.add(lblInfoRoomates2);
		FourRoomate.add(lblInfoRoomates3);
		FourRoomate.add(lblInfoRoomates4);
		FourRoomate.add(lblDelete1);
		FourRoomate.add(lblDelete2);
		FourRoomate.add(lblDelete3);
		FourRoomate.add(lblDelete4);
		
		
		
		lblInfoRoomates2_1 = new JLabel("");
		lblInfoRoomates2_1.setBounds(10, 0, 284, 24);
		lblInfoRoomates2_1.setOpaque(true);
		lblInfoRoomates2_1.setBackground(SystemColor.controlHighlight);
		
		lblInfoRoomates2_2 = new JLabel("");
		lblInfoRoomates2_2.setBounds(10, 30, 284, 24);
		lblInfoRoomates2_2.setOpaque(true);
		lblInfoRoomates2_2.setBackground(SystemColor.controlHighlight);
		
		lblInfoRoomates2_3 = new JLabel("");
		lblInfoRoomates2_3.setBounds(10, 60, 284, 24);
		lblInfoRoomates2_3.setOpaque(true);
		lblInfoRoomates2_3.setBackground(SystemColor.controlHighlight);
		
		lblInfoRoomates2_4 = new JLabel("");
		lblInfoRoomates2_4.setBounds(10, 90, 284, 24);
		lblInfoRoomates2_4.setOpaque(true);
		lblInfoRoomates2_4.setBackground(SystemColor.controlHighlight);
		
		lblDelete2_1 = new JLabel("");
		lblDelete2_1.setBounds(301, 0, 16, 24);
		lblDelete2_1.setBackground(SystemColor.controlHighlight);
		lblDelete2_1.setIcon(new ImageIcon(FrameUpContract.class.getResource("/icon/cross.png")));
		lblDelete2_1.setOpaque(true);
		lblDelete2_1.setVisible(false);
		lblDelete2_1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		lblDelete2_2 = new JLabel("");
		lblDelete2_2.setBounds(300, 30, 17, 24);
		lblDelete2_2.setBackground(SystemColor.controlHighlight);
		lblDelete2_2.setIcon(new ImageIcon(FrameUpContract.class.getResource("/icon/cross.png")));
		lblDelete2_2.setOpaque(true);
		lblDelete2_2.setVisible(false);
		lblDelete2_2.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		lblDelete2_3 = new JLabel("");
		lblDelete2_3.setBounds(300, 60, 17, 24);
		lblDelete2_3.setBackground(SystemColor.controlHighlight);
		lblDelete2_3.setIcon(new ImageIcon(FrameUpContract.class.getResource("/icon/cross.png")));
		lblDelete2_3.setOpaque(true);
		lblDelete2_3.setVisible(false);
		lblDelete2_3.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		lblDelete2_4 = new JLabel("");
		lblDelete2_4.setBounds(300, 90, 17, 24);
		lblDelete2_4.setBackground(SystemColor.controlHighlight);
		lblDelete2_4.setIcon(new ImageIcon(FrameUpContract.class.getResource("/icon/cross.png")));
		lblDelete2_4.setOpaque(true);
		lblDelete2_4.setVisible(false);
		lblDelete2_4.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		lblInfoRoomates2_5 = new JLabel("");
		lblInfoRoomates2_5.setBounds(10, 120, 284, 24);
		lblInfoRoomates2_5.setOpaque(true);
		lblInfoRoomates2_5.setBackground(SystemColor.controlHighlight);
		
		
		lblDelete2_5 = new JLabel("");
		lblDelete2_5.setBounds(300, 120, 17, 24);
		lblDelete2_5.setBackground(SystemColor.controlHighlight);
		lblDelete2_5.setIcon(new ImageIcon(FrameUpContract.class.getResource("/icon/cross.png")));
		lblDelete2_5.setOpaque(true);
		lblDelete2_5.setVisible(false);
		lblDelete2_5.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		
		FiveRoomate.setLayout(null);
		FiveRoomate.add(lblInfoRoomates2_1);
		FiveRoomate.add(lblInfoRoomates2_2);
		FiveRoomate.add(lblInfoRoomates2_3);
		FiveRoomate.add(lblInfoRoomates2_4);
		FiveRoomate.add(lblDelete2_1);
		FiveRoomate.add(lblDelete2_2);
		FiveRoomate.add(lblDelete2_3);
		FiveRoomate.add(lblDelete2_4);
		FiveRoomate.add(lblInfoRoomates2_5);
		FiveRoomate.add(lblDelete2_5);
	}
	
	
}
