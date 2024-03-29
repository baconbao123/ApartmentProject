package formEnterAd;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.SystemColor;
import java.awt.Window;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import com.toedter.calendar.JDateChooser;

import dao.ApartmentDao;
import dao.ContractDao;
import dao.UserDao;
import entity.Apartment;
import entity.Contract;
import entity.Users;
import event.EventLoadTable;
import formAdmin.ContractList;
import view.AppStateManager;
import view.CardRoom;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import java.awt.CardLayout;

public class FrameAddContract extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblAddContract;
	private JLabel lblApartNum;
	private JComboBox cbbApart;
	private JLabel lblOwnerNum;
	private JComboBox cbbOwner;
	private JLabel lblContractImgs;
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
	private JLabel lblInfoRoomates1;
	private JLabel lblInfoRoomates2;
	private JLabel lblInfoRoomates3; 
	private JLabel lblInfoRoomates4;
	private List<String> roomatesList = new ArrayList<>();
	private JButton btnSave;
	private JLabel lblUpTo;
	private List<String> filePathList = new ArrayList<>();
	private List<String> newAvatarFilePathList = new ArrayList<>();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JPanel panelRoomate;
	private JPanel FourRoomate;
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
	private JPanel panel;
	private JPanel FiveRoomate;
	private JLabel lblInfoRoomates1_1;
	private JLabel lblInfoRoomates2_6;
	private JLabel lblInfoRoomates3_1;
	private JLabel lblInfoRoomates4_1;
	private JLabel lblInfoRoomates4_2;
	private JLabel lblBtnDelete1_1;
	private JLabel lblBtnDelete2_6;
	private JLabel lblBtnDelete3_1;
	private JLabel lblBtnDelete4_1;
	private JLabel lblBtnDelete4_2;
	private CardRoom cardRoom;
	private ContractList contractList;
	private EventLoadTable eventLoad;
	
	
	public void setCardRoom(CardRoom cardRoom) {
		this.cardRoom = cardRoom;
	}
	
	public FrameAddContract(CardRoom cardRoom, EventLoadTable event) {
        this.cardRoom = cardRoom;	
        this.eventLoad = event;
        initCode(event);
        
    }

//	public ContractList getContractList() {
//		contractList.loadTableContract();
//		return contractList;
//	}

	public void setContractList(ContractList contractList) {
		this.contractList = contractList;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					ContractList con = new ContractList();
					FrameAddContract frame = new FrameAddContract();
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
	
	private void initCode(EventLoadTable event) {
		setBackground(SystemColor.window);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 510, 589);
		contentPane = new JPanel();
		contentPane.setFont(new Font("Arial", Font.PLAIN, 11));
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		var dao = new ApartmentDao();
		var daoUser = new UserDao();

		// apart number
		cbbApart = new JComboBox();
		cbbApart.setBounds(163, 43, 315, 19);

		List<Apartment> apartNumber = dao.selectApartNum(); 
		for (Apartment number : apartNumber) {
			cbbApart.addItem(number);
		}
		

		// onwer name
		Map<Integer, Users> renterMapOwner = new HashMap<>();
		Map<Integer, Users> renterMapRoomate = new HashMap<>();

		cbbOwner = new JComboBox();
		cbbOwner.setBounds(163, 82, 315, 19);
		cbbOwner.setModel(new DefaultComboBoxModel(new String[] { "" }));

		List<Object> renterInfo = daoUser.selRenterName();

		for (Object renter : renterInfo) {
			if (renter instanceof Users) {
				Users renterObject = (Users) renter;
				cbbOwner.addItem(renterObject.getId() + "-" + renterObject.getName() + "-" + renterObject.getPhone()
						+ "-" + renterObject.getDob() + "-" + renterObject.getNic());
				renterMapOwner.put(renterObject.getId(), renterObject);
			}
		}

		cbbOwner.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JComboBox combobox = (JComboBox) e.getSource();
				String selectRoomates = (String) cbbOwner.getSelectedItem();
				String[] parts = selectRoomates.split("-");
				int roomatesID = Integer.parseInt(parts[0]);
				
				// Xóa chủ sở hữu trước đó khỏi danh sách roomatesList
				if (!roomatesList.isEmpty()) {
				    roomatesList.remove(0); // Xóa owner trước đó khỏi danh sách
				}
		        
				lblInfoRoomates1.setText(selectRoomates);
				lblBtnDelete1.setVisible(true);
				lblBtnDelete1.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						super.mouseClicked(e);
						lblInfoRoomates1.setText("");
						lblBtnDelete1.setVisible(false);
						roomatesList.remove(String.valueOf(roomatesID));
						
					}
				});
				roomatesList.add(String.valueOf(roomatesID));

			}
		});

		// roomate 
		panelRoomate = new JPanel();
		panelRoomate.setLayout(new CardLayout(0, 0));

		FourRoomate = new JPanel();
		FourRoomate.setBackground(Color.LIGHT_GRAY);
		panelRoomate.add(FourRoomate, "fourRoomate");

		FiveRoomate = new JPanel();
		FiveRoomate.setBackground(Color.LIGHT_GRAY);
		panelRoomate.add(FiveRoomate, "fiveRoomate");
		FiveRoomate.setLayout(null);

		CardLayout layout = (CardLayout) panelRoomate.getLayout();
		layout.show(panelRoomate, "fourRoomate");

		cbbRoomates = new JComboBox();
		cbbRoomates.setBounds(163, 291, 315, 19);

		cbbRoomates.setModel(new DefaultComboBoxModel(new String[] { "" }));
		for (Object renter : renterInfo) {
			Users renterObject = (Users) renter;
			cbbRoomates.addItem(renterObject.getId() + "-" + renterObject.getName() + renterObject.getPhone() + "-"
					+ renterObject.getDob() + "-" + renterObject.getNic());
			renterMapRoomate.put(renterObject.getId(), renterObject);
		}
		cbbRoomates.addActionListener(new ActionListener() {
			int selectCount = 1;

			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox combobox = (JComboBox) e.getSource();
				String selectRoomates = (String) cbbRoomates.getSelectedItem();
				String[] parts = selectRoomates.split("-");

				if (selectCount < 3) {
					int roomatesID = Integer.parseInt(parts[0]);

					switch (selectCount) {
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

		// contract img
		btnUploadContract = new JButton("Upload");
		btnUploadContract.setBounds(163, 119, 55, 38);
		btnUploadContract.setFocusable(false);
		btnUploadContract.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnUploadContractActionPerformed(e);
			}
		});

		// status
		rdbOnContract = new JRadioButton("On");
		rdbOnContract.setBounds(163, 182, 33, 15);
		rdbOnContract.setFocusable(false);
		buttonGroup.add(rdbOnContract);
		rdbtnOffContract = new JRadioButton("Off");
		rdbtnOffContract.setBounds(236, 182, 35, 15);
		rdbtnOffContract.setFocusable(false);
		buttonGroup.add(rdbOnContract);

		// fromdate - todate
		dateFromDate = new JDateChooser();
		dateFromDate.setBounds(163, 216, 315, 19);
		dateFromDate.setDateFormatString("yyyy-MM-dd");
		dateFromDate.setName("JDateFromDate");
		dateFromDate.getJCalendar().setMinSelectableDate(Calendar.getInstance().getTime()); // Allow selecting dates
																							// from today onwards

		dateFromDate.addPropertyChangeListener("date", new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if (dateFromDate.getDate() != null && dateToDate.getDate() != null) {
					if (dateFromDate.getDate().after(dateToDate.getDate())) {
						JOptionPane.showMessageDialog(null, "The check-out date cannot be after the check-in date");
						dateFromDate.setDate(dateToDate.getDate());
					}
				}

			}
		});

		dateToDate = new JDateChooser();
		dateToDate.setBounds(163, 253, 315, 19);
		dateToDate.setDateFormatString("yyyy-MM-dd");
		dateToDate.setName("JDateToDate");
		dateToDate.getJCalendar().setMinSelectableDate(Calendar.getInstance().getTime());

		dateToDate.addPropertyChangeListener("date", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if (dateFromDate.getDate() != null && dateToDate.getDate() != null) {
					if (dateToDate.getDate().before(dateFromDate.getDate())) {

						JOptionPane.showMessageDialog(null, "The check-out date cannot be before the check-in date");
						dateToDate.setDate(dateFromDate.getDate());
					}
				}
			}
		}); 

		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var contract = new Contract();
				var dao = new ContractDao();

				Apartment selectedApartment = (Apartment) cbbApart.getSelectedItem();
				int selectedApartId = selectedApartment.getId();

				if (dateFromDate.getDate() == null || dateToDate.getDate() == null) {
					JOptionPane.showMessageDialog(FrameAddContract.this, "Please enter from date or to date",
							"Invalid Input", JOptionPane.ERROR_MESSAGE);

				} else {
					java.sql.Date sqlFromDate = new java.sql.Date(dateFromDate.getDate().getTime());
					java.sql.Date sqlToDate = new java.sql.Date(dateToDate.getDate().getTime());

					// regex
					if(!rdbOnContract.isSelected() && !rdbtnOffContract.isSelected()) {
						JOptionPane.showMessageDialog(FrameAddContract.this, "Please choose status", "Invalid Input",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					if(lblImgCon1.getIcon()==null || lblImgCon2.getIcon()==null || lblImgCon3.getIcon()==null || lblImgCon4.getIcon()==null) {
						JOptionPane.showMessageDialog(FrameAddContract.this, "Please select at least 4 contract photos", "Invalid Input",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					if (cbbOwner.getSelectedItem() == null || cbbOwner.getSelectedItem().toString().isEmpty()
							|| cbbOwner.getSelectedItem().toString().trim().equalsIgnoreCase("")) {
						JOptionPane.showMessageDialog(FrameAddContract.this, "Please choose an owner", "Invalid Input",
								JOptionPane.ERROR_MESSAGE);
						return;
					} else {
						String selectedOwnerInfo = (String) cbbOwner.getSelectedItem();
						int ownerID = Integer.parseInt(selectedOwnerInfo.split("-")[0]);
						Users ownerApart = renterMapOwner.get(ownerID);

						// save to db
						if (ownerApart != null) {
							contract.setOwnerID(ownerID);
						}
						contract.setApartNum(selectedApartId);
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
								Files.copy(new File(filePathList.get(i)).toPath(),
										new File(newAvatarFilePathList.get(i)).toPath(),
										StandardCopyOption.REPLACE_EXISTING);
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
						
						if(cardRoom!=null) {
							int selectedApartNum = selectedApartment.getRoomNumber();
							System.out.println("Apartment num" + selectedApartNum);
							cardRoom.setBackgroundColor(new Color(39, 158, 255));
							
							CardLayout layout = (CardLayout) cardRoom.CardButton.getLayout();
							layout.show(cardRoom.CardButton, "rented");
							cardRoom.setBackgroundColor(new Color(39, 158, 255));
							cardRoom.repaint();
							AppStateManager.saveAppState(selectedApartNum, new Color(39, 158, 255), "rented");
						} else if (cardRoom == null) {
							System.out.println("card room null");
						}
						
						dispose();
						
						event.loadDataTable();
					}
				}
			}
		});

		initComponent();
		
	}

	public FrameAddContract() {
		setBackground(SystemColor.window);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 510, 589);
		contentPane = new JPanel();
		contentPane.setFont(new Font("Arial", Font.PLAIN, 11));
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);


	}
 
	protected void btnUploadContractActionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setMultiSelectionEnabled(true);
		fileChooser.setFileFilter(new FileNameExtensionFilter("Image files", "png", "jpg"));
		fileChooser.setCurrentDirectory(new File("C:\\Users"));
		int result = fileChooser.showOpenDialog(FrameAddContract.this);

		if (result == JFileChooser.APPROVE_OPTION) {
			File[] selectedFiles = fileChooser.getSelectedFiles();


			filePathList.clear();
			newAvatarFilePathList.clear();
			 
			if(selectedFiles.length!=4) {
				JOptionPane.showMessageDialog(null, "Please upload 4 photos of your contract", "Input Invalid",
						JOptionPane.ERROR_MESSAGE);
			} else {
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
						var icon = new ImageIcon(filePath);
						lblImgCon1.setIcon(new ImageIcon(icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
						lblImgCon1.setCursor(new Cursor(Cursor.HAND_CURSOR));
						addMouseListenerImg(lblImgCon1, icon);
						
						
						break;

					case 1:
						var icon2 = new ImageIcon(filePath);
						lblImgCon2.setIcon(new ImageIcon(icon2.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
						lblImgCon2.setCursor(new Cursor(Cursor.HAND_CURSOR));
						addMouseListenerImg(lblImgCon2, icon2);
						break;

					case 2:
						var icon3 = new ImageIcon(filePath);
						lblImgCon3.setIcon(new ImageIcon(icon3.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
						lblImgCon3.setCursor(new Cursor(Cursor.HAND_CURSOR));
						addMouseListenerImg(lblImgCon3, icon3);
						break;

					case 3:
						var icon4 = new ImageIcon(filePath);
						lblImgCon4.setIcon(new ImageIcon(icon4.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
						lblImgCon4.setCursor(new Cursor(Cursor.HAND_CURSOR));
						addMouseListenerImg(lblImgCon4, icon4);
						break;

					case 4:
						var icon5 = new ImageIcon(filePath);
						lblImgCon5.setIcon(new ImageIcon(icon5.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
						lblImgCon5.setCursor(new Cursor(Cursor.HAND_CURSOR));
						addMouseListenerImg(lblImgCon5, icon5);
						break;
					}

				}
			}
			
			
		}
	}

	private void addMouseListenerImg(JLabel label, ImageIcon img) {
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				var dialog = new JDialog();
				var labelImg = new JLabel(
						new ImageIcon(img.getImage().getScaledInstance(600, 600, Image.SCALE_SMOOTH)));
				dialog.getContentPane().add(labelImg);
				dialog.pack();
				dialog.setVisible(true);
				dialog.setLocationRelativeTo(null);
				super.mouseClicked(e);
			}
		});
	}

	private void initComponent() {
		lblAddContract = new JLabel("Add Contract");
		lblAddContract.setBounds(191, 5, 111, 26);
		lblAddContract.setForeground(Color.GRAY);
		lblAddContract.setFont(new Font("Arial", Font.BOLD, 16));

		lblApartNum = new JLabel("Apartment Number");
		lblApartNum.setBounds(15, 42, 138, 19);
		lblApartNum.setForeground(Color.GRAY);
		lblApartNum.setFont(new Font("Arial", Font.BOLD, 14));

		cbbApart.setBorder(null);
		cbbApart.setBackground(SystemColor.window);
		cbbApart.setFont(new Font("Arial", Font.PLAIN, 13));

		lblOwnerNum = new JLabel("Owner Name");
		lblOwnerNum.setBounds(15, 81, 138, 19);
		lblOwnerNum.setForeground(Color.GRAY);
		lblOwnerNum.setFont(new Font("Arial", Font.BOLD, 14));

		cbbOwner.setFont(new Font("Arial", Font.PLAIN, 13));
		cbbOwner.setBorder(null);
		cbbOwner.setBackground(SystemColor.window);

		// contract img
		lblContractImgs = new JLabel("Contract Images");
		lblContractImgs.setBounds(15, 119, 138, 19);
		lblContractImgs.setForeground(Color.GRAY);
		lblContractImgs.setFont(new Font("Arial", Font.BOLD, 14));

		btnUploadContract.setForeground(SystemColor.window);
		btnUploadContract.setFont(new Font("Arial", Font.BOLD, 12));
		btnUploadContract.setBorder(null);
		btnUploadContract.setBackground(SystemColor.activeCaption);

		lblImgCon1 = new JLabel("");
		lblImgCon1.setBounds(228, 119, 43, 38);

		lblImgCon2 = new JLabel("");
		lblImgCon2.setBounds(275, 119, 43, 38);

		lblImgCon3 = new JLabel("");
		lblImgCon3.setBounds(324, 119, 43, 38);

		lblImgCon4 = new JLabel("");
		lblImgCon4.setBounds(373, 119, 43, 38);

		lblImgCon5 = new JLabel("");
		lblImgCon5.setBounds(422, 119, 43, 38);

//		lblImgCon1.setOpaque(true);
//		lblImgCon1.setBackground(SystemColor.info);
//
//		lblImgCon2.setOpaque(true);
//		lblImgCon2.setBackground(SystemColor.info);
//
//		lblImgCon3.setOpaque(true);
//		lblImgCon3.setBackground(SystemColor.info);
//
//		lblImgCon4.setOpaque(true);
//		lblImgCon4.setBackground(SystemColor.info);
//
//		lblImgCon5.setOpaque(true);
//		lblImgCon5.setBackground(SystemColor.info);

		lblStatus = new JLabel("Status");
		lblStatus.setBounds(15, 179, 138, 19);
		lblStatus.setForeground(Color.GRAY);
		lblStatus.setFont(new Font("Arial", Font.BOLD, 14));

		rdbOnContract.setFont(new Font("Arial", Font.BOLD, 12));
		rdbOnContract.setBackground(SystemColor.window);
		rdbOnContract.setBorder(null);

		rdbtnOffContract.setFont(new Font("Arial", Font.BOLD, 12));
		rdbtnOffContract.setBorder(null);
		rdbtnOffContract.setBackground(SystemColor.window);

		lblFromDate = new JLabel("From Date");
		lblFromDate.setBounds(15, 216, 138, 19);
		lblFromDate.setForeground(Color.GRAY);
		lblFromDate.setFont(new Font("Arial", Font.BOLD, 14));

		lblToDate = new JLabel("To Date");
		lblToDate.setBounds(15, 253, 138, 19);
		lblToDate.setForeground(Color.GRAY);
		lblToDate.setFont(new Font("Arial", Font.BOLD, 14));

		lblRoomates = new JLabel("Roomates");
		lblRoomates.setBounds(15, 290, 138, 19);
		lblRoomates.setForeground(Color.GRAY);
		lblRoomates.setFont(new Font("Arial", Font.BOLD, 14));

		cbbRoomates.setFont(new Font("Arial", Font.PLAIN, 13));
		cbbRoomates.setBorder(null);
		cbbRoomates.setBackground(SystemColor.window);

		btnSave.setBounds(191, 494, 133, 29);

		btnSave.setFocusable(false);
		btnSave.setForeground(SystemColor.window);
		btnSave.setFont(new Font("Arial", Font.BOLD, 12));
		btnSave.setBorder(null);
		btnSave.setBackground(new Color(64, 128, 128));

		lblUpTo = new JLabel("Up to 5 images(png or jpg)");
		lblUpTo.setBounds(15, 144, 138, 13);
		lblUpTo.setForeground(Color.GRAY);
		lblUpTo.setFont(new Font("Arial", Font.PLAIN, 11));
		contentPane.setLayout(null);
		contentPane.add(lblContractImgs);
		contentPane.add(lblApartNum);
		contentPane.add(lblOwnerNum);
		contentPane.add(lblStatus);
		contentPane.add(lblFromDate);
		contentPane.add(lblRoomates);
		contentPane.add(lblUpTo);
		contentPane.add(lblToDate);
		contentPane.add(rdbOnContract);
		contentPane.add(btnUploadContract);
		contentPane.add(rdbtnOffContract);
		contentPane.add(lblImgCon1);
		contentPane.add(lblImgCon2);
		contentPane.add(lblImgCon3);
		contentPane.add(lblImgCon4);
		contentPane.add(lblImgCon5);
		contentPane.add(dateToDate);
		contentPane.add(cbbRoomates);
		contentPane.add(dateFromDate);
		contentPane.add(cbbOwner);
		contentPane.add(cbbApart);
		contentPane.add(btnSave);
		contentPane.add(lblAddContract);

		panelRoomate.setBounds(163, 330, 315, 145);
		contentPane.add(panelRoomate);

		lblInfoRoomates1 = new JLabel("");
		lblInfoRoomates1.setBounds(10, 0, 279, 24);
		lblInfoRoomates1.setBackground(Color.LIGHT_GRAY);
		lblInfoRoomates1.setOpaque(true);

		lblInfoRoomates2 = new JLabel("");
		lblInfoRoomates2.setBounds(10, 30, 279, 24);
		lblInfoRoomates2.setBackground(Color.LIGHT_GRAY);
		lblInfoRoomates2.setOpaque(true);

		lblInfoRoomates3 = new JLabel("");
		lblInfoRoomates3.setBounds(10, 60, 279, 24);
		lblInfoRoomates3.setBackground(Color.LIGHT_GRAY);
		lblInfoRoomates3.setOpaque(true);

		lblInfoRoomates4 = new JLabel("");
		lblInfoRoomates4.setBounds(10, 90, 279, 24);
		lblInfoRoomates4.setBackground(Color.LIGHT_GRAY);
		lblInfoRoomates4.setOpaque(true);
		FourRoomate.setLayout(null);

		lblBtnDelete1 = new JLabel("");
		lblBtnDelete1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblBtnDelete1.setVisible(false);
		lblBtnDelete1.setIcon(new ImageIcon(FrameAddContract.class.getResource("/icon/cross.png")));
		lblBtnDelete1.setBounds(295, 0, 20, 24);
		FourRoomate.add(lblBtnDelete1);
		FourRoomate.add(lblInfoRoomates1);
		FourRoomate.add(lblInfoRoomates2);
		FourRoomate.add(lblInfoRoomates3);
		FourRoomate.add(lblInfoRoomates4);

		lblBtnDelete2 = new JLabel("");
		lblBtnDelete2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblBtnDelete2.setVisible(false);
		lblBtnDelete2.setIcon(new ImageIcon(FrameAddContract.class.getResource("/icon/cross.png")));
		lblBtnDelete2.setBounds(295, 30, 20, 24);
		FourRoomate.add(lblBtnDelete2);

		lblBtnDelete3 = new JLabel("");
		lblBtnDelete3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblBtnDelete3.setVisible(false);
		lblBtnDelete3.setIcon(new ImageIcon(FrameAddContract.class.getResource("/icon/cross.png")));
		lblBtnDelete3.setBounds(295, 60, 20, 24);
		FourRoomate.add(lblBtnDelete3);

		lblBtnDelete4 = new JLabel("");
		lblBtnDelete4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblBtnDelete4.setVisible(false);
		lblBtnDelete4.setIcon(new ImageIcon(FrameAddContract.class.getResource("/icon/cross.png")));
		lblBtnDelete4.setBounds(295, 90, 20, 24);
		FourRoomate.add(lblBtnDelete4);

		lblInfoRoomates1_1 = new JLabel("");
		lblInfoRoomates1_1.setOpaque(true);
		lblInfoRoomates1_1.setBackground(Color.LIGHT_GRAY);
		lblInfoRoomates1_1.setBounds(10, 0, 279, 24);
		FiveRoomate.add(lblInfoRoomates1_1);

		lblInfoRoomates2_6 = new JLabel("");
		lblInfoRoomates2_6.setOpaque(true);
		lblInfoRoomates2_6.setBackground(Color.LIGHT_GRAY);
		lblInfoRoomates2_6.setBounds(10, 30, 279, 24);
		FiveRoomate.add(lblInfoRoomates2_6);

		lblInfoRoomates3_1 = new JLabel("");
		lblInfoRoomates3_1.setOpaque(true);
		lblInfoRoomates3_1.setBackground(Color.LIGHT_GRAY);
		lblInfoRoomates3_1.setBounds(10, 60, 279, 24);
		FiveRoomate.add(lblInfoRoomates3_1);

		lblInfoRoomates4_1 = new JLabel("");
		lblInfoRoomates4_1.setOpaque(true);
		lblInfoRoomates4_1.setBackground(Color.LIGHT_GRAY);
		lblInfoRoomates4_1.setBounds(10, 90, 279, 24);
		FiveRoomate.add(lblInfoRoomates4_1);

		lblInfoRoomates4_2 = new JLabel("");
		lblInfoRoomates4_2.setOpaque(true);
		lblInfoRoomates4_2.setBackground(Color.LIGHT_GRAY);
		lblInfoRoomates4_2.setBounds(10, 121, 279, 24);
		FiveRoomate.add(lblInfoRoomates4_2);

		lblBtnDelete1_1 = new JLabel("");
		lblBtnDelete1_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblBtnDelete1_1.setVisible(false);
		lblBtnDelete1_1.setIcon(new ImageIcon(FrameAddContract.class.getResource("/icon/cross.png")));
		lblBtnDelete1_1.setBounds(295, 0, 20, 24);
		FiveRoomate.add(lblBtnDelete1_1);

		lblBtnDelete2_6 = new JLabel("");
		lblBtnDelete2_6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblBtnDelete2_6.setVisible(false);
		lblBtnDelete2_6.setIcon(new ImageIcon(FrameAddContract.class.getResource("/icon/cross.png")));
		lblBtnDelete2_6.setBounds(295, 30, 20, 24);
		FiveRoomate.add(lblBtnDelete2_6);

		lblBtnDelete3_1 = new JLabel("");
		lblBtnDelete3_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblBtnDelete3_1.setVisible(false);
		lblBtnDelete3_1.setIcon(new ImageIcon(FrameAddContract.class.getResource("/icon/cross.png")));
		lblBtnDelete3_1.setBounds(295, 60, 20, 24);
		FiveRoomate.add(lblBtnDelete3_1);

		lblBtnDelete4_1 = new JLabel("");
		lblBtnDelete4_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblBtnDelete4_1.setVisible(false);
		lblBtnDelete4_1.setIcon(new ImageIcon(FrameAddContract.class.getResource("/icon/cross.png")));
		lblBtnDelete4_1.setBounds(295, 90, 20, 24);
		FiveRoomate.add(lblBtnDelete4_1);

		lblBtnDelete4_2 = new JLabel("");
		lblBtnDelete4_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblBtnDelete4_2.setVisible(false);
		lblBtnDelete4_2.setIcon(new ImageIcon(FrameAddContract.class.getResource("/icon/cross.png")));
		lblBtnDelete4_2.setBounds(295, 121, 20, 24);
		FiveRoomate.add(lblBtnDelete4_2);

	}
}
