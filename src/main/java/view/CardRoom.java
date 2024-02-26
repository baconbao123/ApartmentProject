package view;

import javax.swing.JPanel;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import dao.ApartmentDao;
import entity.Apartment;
import event.apartmentEvent;
import formAdView.ViewRoomApartNoBtn;
import formAdView.ViewRoomOfUser;
import formEnterAd.FrameAddContract;
import formEnterAd.FrameAddMoney;
import formEnterAd.FrameAddRenter;
import formEnterAd.FrameContract;
import formEnterAd.FrameAddRoom;
import formUpdateAd.FrameContractDisconnect;
import formUpdateAd.FrameUpApart;
import formUpdateAd.FrameUpContract;
import model.Fees;

import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.SystemColor;

public class CardRoom extends JPanel {
 
	private static final long serialVersionUID = 1L;
	private JLabel lblIconRoom;
	private JLabel lblRoomNumber;
	private JLabel lblIconOwner;
	private JLabel lblOwner;
	private JLabel lblIconMoney;
	private JLabel lblPrice;
	private JLabel lblIconPeople;
	public JPanel CardButton;
	private JPanel CardBtnAvailable;
	private JButton btnViewRoom;
	private JButton btnEditRoom;
	private JButton btnAddRenter;
	private JPanel CardBtnRented;
	private JButton btnCheckOut;
	private JButton btnView;
	private JButton btnPayment;
	private CardRoom cardRoom;
	private JLabel roomNum;

	private int currentApartNum;
	private int currentapfloor;
	private int idCurrent;
	private String typeRoom;
	private int peopleMax;
	private String inforRoomate;
	private String imgContracts;
	private Boolean conStatus;
	private Date fromDateCon;
	private Date toDateCon;
	private int idCon;
	private String idFeeAll;
//	private int idFee;
	private float totalMoneyMoth;
	private String nameCard;
	private JLabel lblPeopleInRoom;
	private JLabel lblR;
	private JLabel lblMaxPeople;
	private JLabel lblRenter;
	private FrameUpContract frameUpCon;
	private FrameContract contractFrame;
	private FrameAddRoom frameAddRoom; 
	private ViewRoomApartNoBtn frameViewRoomApartNoBtn;
	private FrameAddMoney frameFrameAddMoney;
	private FrameContractDisconnect frameFrameContractDisconnect;
	private ViewRoomOfUser frameViewRoomOfUser;
	
	private apartmentEvent event;
	
	private boolean isVisibleFrame = false;
	
	
	

	public apartmentEvent getEvent() {
		return event;
	}


	public void setEvent(apartmentEvent event) {
		this.event = event;
	}


	public String getLblPrice() {
		return lblPrice.getText();
	}


	// name Card
	public String getNameCard() {
		return nameCard;
	}

	public void setNameCard(String nameCard) {
		this.nameCard = nameCard;
	}

	public CardRoom getCardRoom() {
		return cardRoom;
	}
 
	public void setCardRoom(CardRoom cardRoom) {
		this.cardRoom = cardRoom;
	}

	// get apart room number in card apartment
	public void setCurrentApartNum(int currentApartNum) {
		this.currentApartNum = currentApartNum;
	}

	public int getCurrentApartNum() {
		return currentApartNum;
	}

	// get floor apart in card apartment
	public void setCurrentFloor(int currentapfloor) {
		this.currentapfloor = currentapfloor;
	}

	// get id room apart in card apartment
	public void setIdApartRoom(int idCurrent) {
		this.idCurrent = idCurrent;
	}
	
	//get Type Room
	public String getTypeRoom() {
		return typeRoom;
	}
	
	public void setTypeRoom(String typeRoom) {
		this.typeRoom = typeRoom;
	}
	
	//get Max People
	public int getPeopleMax() {
		return peopleMax;
	}

	public void setPeopleMax(int peopleMax) {
		this.peopleMax = peopleMax;
	}
	
	// get id Con
	public int getIdCon() {
		return idCon;
	}

	public void setIdCon(int idCon) {
		this.idCon = idCon;
	}
	

	//get id Roomate
	public String getInforRoomate() {
		return inforRoomate;
	}
	public void setInforRoomate(String inforRoomate) {
		this.inforRoomate = inforRoomate;
	}
	
	// get status contract
	public Boolean getConStatus() {
		return conStatus;
	}

	public void setConStatus(Boolean conStatus) {
		this.conStatus = conStatus;
	}
	
	//get from date contract
	public Date getFromDateCon() {
		return fromDateCon;
	}

	public void setFromDateCon(Date fromDateCon) {
		this.fromDateCon = fromDateCon;
	}
	
	// get to Date contract
	public Date getToDateCon() {
		return toDateCon;
	}

	public void setToDateCon(Date toDateCon) {
		this.toDateCon = toDateCon;
	}
	
	
	//get imgs contract
	public String getImgContracts() {
		return imgContracts;
	}

	public void setImgContracts(String imgContracts) {
		this.imgContracts = imgContracts;
	}
	
	// get id fee all
	public String getIdFeeAll() {
		return idFeeAll;
	}

	public void setIdFeeAll(String idFeeAll) {
		this.idFeeAll = idFeeAll;
	}
	
	

	public CardRoom() {
		setBackground(Color.WHITE);
		setBorder(null);
		setBounds(0, 0, 210, 180);

		lblOwner = new JLabel("None");
		lblPrice = new JLabel("0");
		
		CardButton = new JPanel();
		CardButton.setBorder(null);
		CardButton.setLayout(new CardLayout(0, 0));

		CardBtnAvailable = new JPanel();
		CardBtnAvailable.setBackground(Color.WHITE); 
		CardButton.add(CardBtnAvailable, "available");

		btnViewRoom = new JButton("View");
		btnEditRoom = new JButton("Edit");
		btnAddRenter = new JButton("Add Contract");

		CardBtnRented = new JPanel();
		CardBtnRented.setBackground(Color.WHITE);
		CardButton.add(CardBtnRented, "rented");

		btnCheckOut = new JButton("Check Out");
		btnView = new JButton("View");
		btnPayment = new JButton("Payment");

		
		
		
		initComponent();
//		callStateCard();
//		JOptionPane.showMessageDialog(null, nameCard);
		
		if(Float.parseFloat(lblPrice.getText()) == 0 || Float.parseFloat(lblPrice.getText()) == 0.0) {
			CardLayout layout = (CardLayout) CardButton.getLayout();
		    if ("rented".equals(layout.toString())) { // Kiểm tra nếu layout là "rented"

				layout.show(CardButton, "rented");
		        setBackgroundColor(Color.PINK);
		    	int roomNumber = cardRoom.getCurrentApartNum();
		    	System.out.println(cardRoom.getCurrentApartNum());
		    	AppStateManager.saveAppState(roomNumber, new Color(46, 204, 113), "rented");
		    }
		}
		
//		} else if (Integer.parseInt(lblPrice.getText()) > 0){
//			CardLayout layout = (CardLayout) CardButton.getLayout();
//			if ("rented".equals(layout.toString())) { 
//				int roomNumber = cardRoom.getCurrentApartNum();
//		    	AppStateManager.saveAppState(roomNumber, new Color(39, 158, 255), "rented");
//			}
//		}
	}
	
	

	

	



	// owner name
	public void setOwnerTex(String ownerName) {
		if (ownerName != null) {
			lblOwner.setText(ownerName);
		} else {
			lblOwner.setText("None");
		}

	}

	// apart number
	public void setApartmenNumber(int roomNumber) {
		String roomNumStr = String.valueOf(roomNumber);
		roomNum.setText(roomNumStr);
	} 

	// set people
	public void setPeopleNumber(int peopleMaximum) {
		lblMaxPeople.setText(String.valueOf(peopleMaximum));
	}
	
	// people in room
	public void setPeopleInRoom(String number) {
		lblPeopleInRoom.setText(number);
	}

	// -----------------------------Available-------------------------------------------------
	// btn add renter
	protected void btnAddRenterActionPerformed(ActionEvent e) {
		
//		var contract = new FrameContract();
//		contract.setE(event);
//		contract.setVisible(true);
//		contract.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		contract.setLocationRelativeTo(null);
//		contract.setNumApart(String.valueOf(currentApartNum));
//		contract.setApartID(String.valueOf(idCurrent));
//		contract.setCardRoom(this);
//		contract.setMaxPeople(lblMaxPeople.getText());
			
		if (contractFrame == null) {
	        contractFrame = new FrameContract();
	        contractFrame.setE(event);
	        contractFrame.setVisible(true);
	        contractFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        contractFrame.setLocationRelativeTo(null);
	        contractFrame.setNumApart(String.valueOf(currentApartNum));
	        contractFrame.setApartID(String.valueOf(idCurrent));
	        contractFrame.setCardRoom(this);
	        contractFrame.setMaxPeople(lblMaxPeople.getText());
	    } else {
	        contractFrame.setVisible(true);
	        contractFrame.setExtendedState(JFrame.NORMAL); 
	    }
			
			
		
	}

	// btn edit room
	protected void btnEditRoomActionPerformed(ActionEvent e) {
		if(frameAddRoom==null) {
			frameAddRoom = new FrameAddRoom();
			frameAddRoom.setVisible(true);
			frameAddRoom.setLocationRelativeTo(null);
			frameAddRoom.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frameAddRoom.setRoomNum(currentApartNum);
			frameAddRoom.setFloorApart(String.valueOf(currentapfloor));
			frameAddRoom.setIDCurrent(String.valueOf(idCurrent));
	
			
			var dao = new ApartmentDao();
			List<Apartment> apartments = dao.selectApartment();
			for(Apartment apart: apartments) {
				if(apart.getRoomNumber() == currentApartNum) {
					frameAddRoom.displayConvenient(apart.getConvenient());
					frameAddRoom.displayUlti(apart.getUtilities());
					break;
				}
				
			}
		}else {
			frameAddRoom.setVisible(true);
			frameAddRoom.setExtendedState(JFrame.NORMAL); 
	    }
		


	}

	// btn view room
	protected void btnViewRoomActionPerformed(ActionEvent e) {
		if(frameViewRoomApartNoBtn==null) {
			frameViewRoomApartNoBtn = new ViewRoomApartNoBtn();
			frameViewRoomApartNoBtn.setVisible(true);
			frameViewRoomApartNoBtn.setLocationRelativeTo(null);
			frameViewRoomApartNoBtn.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frameViewRoomApartNoBtn.setRoomNum(currentApartNum);
			frameViewRoomApartNoBtn.setFloorApart(String.valueOf(currentapfloor));
			frameViewRoomApartNoBtn.setIDCurrent(String.valueOf(idCurrent));
			
	
			var dao = new ApartmentDao();
			List<Apartment> apartments = dao.selectApartment();
			for (Apartment apartment : apartments) {
				if (apartment.getRoomNumber() == currentApartNum) {
					frameViewRoomApartNoBtn.displayConvenient(apartment.getConvenient());
					frameViewRoomApartNoBtn.showMaxPeople(apartment.getPeopleMaximun());
					frameViewRoomApartNoBtn.showTypeRoom(apartment.getType());
					frameViewRoomApartNoBtn.displayUlti(apartment.getUtilities());
					break; 
				}
			}
		}else {
			frameViewRoomApartNoBtn.setVisible(true);
			frameViewRoomApartNoBtn.setExtendedState(JFrame.NORMAL); 
	    }
		

	}
	
	
	
	// -----------------------------Rented-------------------------------------------------
	// btn payment
	protected void btnPaymentActionPerformed(ActionEvent e) {
		if(frameFrameAddMoney==null) {
			frameFrameAddMoney = new FrameAddMoney();
			frameFrameAddMoney.setVisible(true);
			frameFrameAddMoney.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frameFrameAddMoney.setLocationRelativeTo(null);
			frameFrameAddMoney.setCurrentCardRoom(currentApartNum);
			frameFrameAddMoney.setBtnPayMonthListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		            frameFrameAddMoney.btnPayMonthActionPerformed(null);
		        }
		    });
			
			frameFrameAddMoney.setBtnPayAllListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					frameFrameAddMoney.setBtnPayAllListener(null);
					
				}
			});
			frameFrameAddMoney.setMoneyAll(Float.parseFloat(lblPrice.getText()));
			frameFrameAddMoney.setIdFeeAll(idFeeAll);
		} else {
			frameFrameAddMoney.setVisible(true);
			frameFrameAddMoney.setExtendedState(JFrame.NORMAL); 
	    }
		
		
	}
 
	// btn checkout
	protected void btnCheckOutActionPerformed(ActionEvent e) {
		if(frameFrameContractDisconnect==null) {
			String apartNum = String.valueOf(currentApartNum);
			String priceStr = lblPrice.getText();
			frameFrameContractDisconnect = new FrameContractDisconnect();
			frameFrameContractDisconnect.setEvent(event);
			frameFrameContractDisconnect.setVisible(true);
			frameFrameContractDisconnect.setLocationRelativeTo(null);
			frameFrameContractDisconnect.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			frameFrameContractDisconnect.setApartNum(apartNum);
			frameFrameContractDisconnect.setOwneraName(lblOwner.getText());
			frameFrameContractDisconnect.setImgCon(imgContracts);
			frameFrameContractDisconnect.setFromDateCon(fromDateCon);
			frameFrameContractDisconnect.setToDateCon(toDateCon);
			frameFrameContractDisconnect.setRoomateRoom(inforRoomate);
			frameFrameContractDisconnect.setIDCon(String.valueOf(idCon));
			frameFrameContractDisconnect.setCardRoom(this);
			frameFrameContractDisconnect.setPriceStr(priceStr);
		} else {
			frameFrameContractDisconnect.setVisible(true);
			frameFrameContractDisconnect.setExtendedState(JFrame.NORMAL); 
		}
		
	}

	// btn view renter
	protected void btnViewActionPerformed(ActionEvent e) {
		if(frameViewRoomOfUser==null) {
			frameViewRoomOfUser = new ViewRoomOfUser();
			frameViewRoomOfUser.setVisible(true);
			frameViewRoomOfUser.setLocationRelativeTo(null);
			frameViewRoomOfUser.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			frameViewRoomOfUser.setApNum(String.valueOf(currentApartNum));
			frameViewRoomOfUser.setMaxPeop(String.valueOf(peopleMax));
			frameViewRoomOfUser.setType(typeRoom);
			frameViewRoomOfUser.loadRoomateInfor(inforRoomate);
			frameViewRoomOfUser.setOwnerNameCard(lblOwner.getText());
			
			var dao = new ApartmentDao();
			List<Apartment> apartments = dao.selectApartment();
			
			for(Apartment apart : apartments) {
				if(apart.getRoomNumber() == currentApartNum) {
					frameViewRoomOfUser.displayCon(apart.getConvenient());
					frameViewRoomOfUser.displayUti(apart.getUtilities());
				}
			}
		} else {
			frameViewRoomOfUser.setVisible(true);
			frameViewRoomOfUser.setExtendedState(JFrame.NORMAL); 
		}
		
	}
	
	// money
	public void setMoneyApart(String money) {
		lblPrice.setText(money);
	}
	
//	private void callStateCard() {
//		if(Integer.parseInt(lblPrice.getText()) == 0) {
//			CardLayout layout = (CardLayout) CardButton.getLayout();
//		    if ("rented".equals(layout.toString())) { // Kiểm tra nếu layout là "rented"
//
//				layout.show(CardButton, "rented");
////		        setBackgroundColor(Color.PINK);
//		    	int roomNumber = cardRoom.getCurrentApartNum();
//		    	System.out.println(cardRoom.getCurrentApartNum());
//		    	AppStateManager.saveAppState(roomNumber, new Color(46, 204, 113), "rented");
//		    }
//		} else if (Integer.parseInt(lblPrice.getText()) > 0){
//			CardLayout layout = (CardLayout) CardButton.getLayout();
//			if ("rented".equals(layout.toString())) { 
//				int roomNumber = cardRoom.getCurrentApartNum();
//		    	AppStateManager.saveAppState(roomNumber, new Color(39, 158, 255), "rented");
//			}
//		}
//		
//	}
	
	
	
	
	 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void setBackgroundColor(Color color) {
		setBackground(color);
		CardBtnAvailable.setBackground(color);
		CardBtnRented.setBackground(color);

	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		int borderWidth = 2;
		RoundRectangle2D roundedRect = new RoundRectangle2D.Double(0, 0, getWidth() - borderWidth,
				getHeight() - borderWidth, 0, 0);

		// Draw the border
		g2d.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke(borderWidth));
		g2d.draw(roundedRect);

		g2d.dispose();
	}

	
	
	
	
	
	
	private void initComponent() {
		roomNum = new JLabel("101");
		roomNum.setForeground(Color.BLACK);
		roomNum.setFont(new Font("Arial", Font.BOLD, 12));

		lblIconRoom = new JLabel("");
		lblIconRoom.setIcon(new ImageIcon(CardRoom.class.getResource("/icon/home(1).png")));

		lblRoomNumber = new JLabel("Room");
		lblRoomNumber.setForeground(Color.BLACK);
		lblRoomNumber.setFont(new Font("Arial", Font.BOLD, 12));

		lblIconOwner = new JLabel("");
		lblIconOwner.setIcon(new ImageIcon(CardRoom.class.getResource("/icon/check(1).png")));

		lblOwner.setForeground(Color.BLACK);
		lblOwner.setFont(new Font("Arial", Font.BOLD, 12));

		lblIconMoney = new JLabel("");
		lblIconMoney.setIcon(new ImageIcon(CardRoom.class.getResource("/icon/payment(1).png")));
		lblPrice.setForeground(new Color(244, 0, 0));
		lblPrice.setFont(new Font("Arial", Font.BOLD, 12));

		lblIconPeople = new JLabel("");
		lblIconPeople.setIcon(new ImageIcon(CardRoom.class.getResource("/icon/users(1).png")));

		btnViewRoom.setFocusable(false);
		btnViewRoom.setFont(new Font("Arial", Font.BOLD, 12));
		btnViewRoom.setForeground(Color.WHITE);
		btnViewRoom.setBorder(null);
		btnViewRoom.setBackground(new Color(117, 51, 219));
		btnViewRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnViewRoomActionPerformed(e);
			}
		});

		btnEditRoom.setFocusable(false);
		btnEditRoom.setForeground(Color.WHITE);
		btnEditRoom.setFont(new Font("Arial", Font.BOLD, 12));
		btnEditRoom.setBorder(null);
		btnEditRoom.setBackground(Color.GRAY);
		btnEditRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnEditRoomActionPerformed(e);
			}
		});

		btnAddRenter.setBorder(null);
		btnAddRenter.setBackground(new Color(82, 190, 234));
		btnAddRenter.setFocusable(false);
		btnAddRenter.setForeground(Color.WHITE);
		btnAddRenter.setFont(new Font("Arial", Font.BOLD, 12));
		btnAddRenter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAddRenterActionPerformed(e);
			}
		});

		btnPayment.setForeground(Color.WHITE);
		btnPayment.setFont(new Font("Arial", Font.BOLD, 12));
		btnPayment.setFocusable(false);
		btnPayment.setBorder(null);
		btnPayment.setBackground(new Color(0, 154, 34));
		btnPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnPaymentActionPerformed(e);
			}
		});

		btnView.setForeground(Color.WHITE);
		btnView.setFont(new Font("Arial", Font.BOLD, 12));
		btnView.setFocusable(false);
		btnView.setBorder(null);
		btnView.setBackground(Color.GRAY);
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnViewActionPerformed(e);
			}
		});

		btnCheckOut.setForeground(Color.WHITE);
		btnCheckOut.setFont(new Font("Arial", Font.BOLD, 12));
		btnCheckOut.setFocusable(false);
		btnCheckOut.setBorder(null);
		btnCheckOut.setBackground(new Color(234, 56, 0));
		btnCheckOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCheckOutActionPerformed(e);
			}
		});

		GroupLayout gl_CardBtnAvailable = new GroupLayout(CardBtnAvailable);
		gl_CardBtnAvailable.setHorizontalGroup(
			gl_CardBtnAvailable.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_CardBtnAvailable.createSequentialGroup()
					.addComponent(btnViewRoom, GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnEditRoom, GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAddRenter, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_CardBtnAvailable.setVerticalGroup(
			gl_CardBtnAvailable.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_CardBtnAvailable.createParallelGroup(Alignment.BASELINE)
					.addComponent(btnViewRoom, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
					.addComponent(btnEditRoom, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addComponent(btnAddRenter, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
		);
		CardBtnAvailable.setLayout(gl_CardBtnAvailable);

		GroupLayout gl_CardBtnRented = new GroupLayout(CardBtnRented);
		gl_CardBtnRented.setHorizontalGroup(
				gl_CardBtnRented.createParallelGroup(Alignment.LEADING).addGap(0, 190, Short.MAX_VALUE)
						.addGroup(gl_CardBtnRented.createSequentialGroup()
								.addComponent(btnCheckOut, GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnView, GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnPayment, GroupLayout.PREFERRED_SIZE, 69, Short.MAX_VALUE)));
		gl_CardBtnRented
				.setVerticalGroup(gl_CardBtnRented.createParallelGroup(Alignment.LEADING).addGap(0, 23, Short.MAX_VALUE)
						.addGroup(gl_CardBtnRented.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnCheckOut, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
								.addComponent(btnView, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnPayment, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)));
		CardBtnRented.setLayout(gl_CardBtnRented);
		
		lblPeopleInRoom = new JLabel("0");
		lblPeopleInRoom.setForeground(SystemColor.desktop);
		lblPeopleInRoom.setFont(new Font("Arial", Font.BOLD, 13));
		
		lblR = new JLabel("/");
		lblR.setForeground(SystemColor.desktop);
		lblR.setFont(new Font("Arial", Font.BOLD, 13));
		lblR.setBackground(Color.BLACK);
		
		lblMaxPeople = new JLabel("0");
		lblMaxPeople.setForeground(SystemColor.desktop);
		lblMaxPeople.setFont(new Font("Arial", Font.BOLD, 13));
		lblMaxPeople.setBackground(new Color(234, 56, 0));
		
		lblRenter = new JLabel("renters");
		lblRenter.setForeground(SystemColor.desktop);
		lblRenter.setFont(new Font("Arial", Font.BOLD, 13));
		lblRenter.setBackground(Color.BLACK);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(34)
					.addComponent(lblIconRoom)
					.addGap(18)
					.addComponent(lblRoomNumber)
					.addGap(6)
					.addComponent(roomNum, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(34)
					.addComponent(lblIconOwner)
					.addGap(18)
					.addComponent(lblOwner, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(34)
					.addComponent(lblIconMoney)
					.addGap(18)
					.addComponent(lblPrice, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(34)
					.addComponent(lblIconPeople)
					.addGap(18)
					.addComponent(lblPeopleInRoom, GroupLayout.PREFERRED_SIZE, 11, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(lblR, GroupLayout.PREFERRED_SIZE, 7, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(lblMaxPeople)
					.addGap(6)
					.addComponent(lblRenter, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(CardButton, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
					.addGap(10))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblIconRoom)
						.addComponent(lblRoomNumber, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(roomNum, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
					.addGap(21)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblIconOwner)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(2)
							.addComponent(lblOwner)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblIconMoney)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(2)
							.addComponent(lblPrice)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblIconPeople)
						.addComponent(lblPeopleInRoom)
						.addComponent(lblR)
						.addComponent(lblMaxPeople)
						.addComponent(lblRenter))
					.addGap(18)
					.addComponent(CardButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(7))
		);
		setLayout(groupLayout);
	}
}
