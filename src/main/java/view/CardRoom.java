package view;

import javax.swing.JPanel;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import dao.ApartmentDao;
import entity.Apartment;
import formAdView.ViewRoomApart;
import formAdView.ViewRoomApartNoBtn;
import formAdView.ViewRoomRenters;
import formEnterAd.FrameAddRenter;
import formEnterAd.FrameAddRoom;
import formUpdateAd.FrameUpApart;
import formEnterAd.FormAdd;
import formEnterAd.FormAddContract;
import formEnterAd.FormViewAddRoom;

import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
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

public class CardRoom extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblIconRoom;
	private JLabel lblRoomNumber;
	private JLabel lblIconOwner;
	private JLabel lblOwner;
	private JLabel lblIconMoney;
	private JLabel lblPrice;
	private JLabel lblIconPeople;
	private JLabel lblRenterInRoom;
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
		btnAddRenter = new JButton("Add renter");

		CardBtnRented = new JPanel();
		CardBtnRented.setBackground(Color.WHITE);
		CardButton.add(CardBtnRented, "rented");

		btnCheckOut = new JButton("Check Out");
		btnView = new JButton("View");
		btnPayment = new JButton("Payment");

		initComponent();
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
	public void setPeopleNumber(int peopleNumber, int peopleMaximum) {
		lblRenterInRoom.setText(peopleNumber + "/" + peopleMaximum + " renters");
	}

	// -----------------------------Available-------------------------------------------------
	// btn add renter
	protected void btnAddRenterActionPerformed(ActionEvent e) {
		var addForm = new FormAdd();
		addForm.setVisible(true);
		addForm.setLocationRelativeTo(null);
		addForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addForm.setNumApart(this);

		FormAddContract formAddContract = addForm.getFormAddContract();
		if (formAddContract != null) {
			formAddContract.setApartNumber(String.valueOf(roomNum.getText()));
			formAddContract.setAparId(String.valueOf(idCurrent));
		}
		
		FormViewAddRoom formViewAdd = addForm.getFormViewAddRoom();
		if(formViewAdd!=null) {
			formViewAdd.setFloorApart(String.valueOf(currentapfloor));
			formViewAdd.setNumApart(String.valueOf(currentApartNum));
		}
	}

	// btn edit room
	protected void btnEditRoomActionPerformed(ActionEvent e) {
		var view = new ViewRoomApart();
		view.setVisible(true);
		view.setLocationRelativeTo(null);
		view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		view.setRoomNum(currentApartNum);
		view.setFloorApart(String.valueOf(currentapfloor));
		view.setIDCurrent(String.valueOf(idCurrent));

		FrameAddRoom frameRoom = view.getFrameRoom();
		if (frameRoom != null) {
			frameRoom.setFloorApartNum(String.valueOf(currentapfloor));
			System.out.println("Id " + idCurrent);
		}

		var dao = new ApartmentDao();
		List<Apartment> apartments = dao.selectApartment();
		for (Apartment apartment : apartments) {
			if (apartment.getRoomNumber() == currentApartNum) {
				view.displayConvenient(apartment.getConvenient());
				view.showMaxPeople(apartment.getPeopleMaximun());
				view.showTypeRoom(apartment.getType());
				view.displayUlti(apartment.getUtilities());
				break;
			}
		}

	}

	// btn view room
	protected void btnViewRoomActionPerformed(ActionEvent e) {
		var view = new ViewRoomApartNoBtn();
		view.setVisible(true);
		view.setLocationRelativeTo(null);
		view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		view.setRoomNum(currentApartNum);
		view.setFloorApart(String.valueOf(currentapfloor));
		view.setIDCurrent(String.valueOf(idCurrent));

		var dao = new ApartmentDao();
		List<Apartment> apartments = dao.selectApartment();
		for (Apartment apartment : apartments) {
			if (apartment.getRoomNumber() == currentApartNum) {
				view.displayConvenient(apartment.getConvenient());
				view.showMaxPeople(apartment.getPeopleMaximun());
				view.showTypeRoom(apartment.getType());
				view.displayUlti(apartment.getUtilities());
				break; 
			}
		}

	}
	
	
	
	// -----------------------------Rented-------------------------------------------------
	// btn payment
	protected void btnPaymentActionPerformed(ActionEvent e) {
		int roomNum = getCurrentApartNum();
		int result = JOptionPane.showConfirmDialog(null, "Confirm payment for room number " + roomNum);
		if (result == JOptionPane.YES_OPTION) {
			AppStateManager.saveAppState(roomNum, new Color(46, 204, 113), "rented");
			setBackgroundColor(new Color(46, 204, 113));
			System.out.println(roomNum);
			btnPayment.setEnabled(false);
		}

	}

	// btn checkout
	protected void btnCheckOutActionPerformed(ActionEvent e) {
		int roomNum = getCurrentApartNum();
		int result = JOptionPane.showConfirmDialog(null, "Check-out confirmation for Room " + roomNum);
		if (result == JOptionPane.YES_OPTION) {

			String cardName = "available";
			AppStateManager.saveAppState(roomNum, new Color(255, 255, 255), cardName);
			setBackgroundColor(new Color(255, 255, 255));

			CardLayout cardLayout = (CardLayout) CardButton.getLayout();
			cardLayout.show(CardButton, "available");

		}

	}

	// btn view renter
	protected void btnViewActionPerformed(ActionEvent e) {
		var view = new ViewRoomRenters();
		view.setVisible(true);
		view.setLocationRelativeTo(null);
		view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
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
		lblPrice.setForeground(Color.BLACK);
		lblPrice.setFont(new Font("Arial", Font.BOLD, 12));

		lblIconPeople = new JLabel("");
		lblIconPeople.setIcon(new ImageIcon(CardRoom.class.getResource("/icon/users(1).png")));

		lblRenterInRoom = new JLabel("2/4 renter");
		lblRenterInRoom.setForeground(Color.BLACK);
		lblRenterInRoom.setFont(new Font("Arial", Font.BOLD, 12));
		lblRenterInRoom.setBackground(Color.BLACK);

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

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(24)
								.addComponent(lblIconRoom, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
								.addGap(18).addComponent(lblRoomNumber).addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(roomNum, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup().addGap(24)
								.addComponent(lblIconOwner, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(lblOwner, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup().addGap(24)
								.addComponent(lblIconMoney, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(lblPrice, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup().addGap(24)
								.addComponent(lblIconPeople, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
								.addGap(19).addComponent(lblRenterInRoom, GroupLayout.PREFERRED_SIZE, 131,
										GroupLayout.PREFERRED_SIZE))
						.addComponent(CardButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))
				.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblIconRoom, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblRoomNumber, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
								.addComponent(roomNum, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)))
				.addGap(21)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblIconOwner, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup().addGap(2).addComponent(lblOwner)))
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblIconMoney, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup().addGap(2).addComponent(lblPrice)))
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblIconPeople, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRenterInRoom, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
				.addGap(18).addComponent(CardButton, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
				.addContainerGap()));

		GroupLayout gl_CardBtnAvailable = new GroupLayout(CardBtnAvailable);
		gl_CardBtnAvailable.setHorizontalGroup(gl_CardBtnAvailable.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_CardBtnAvailable.createSequentialGroup()
						.addComponent(btnViewRoom, GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnEditRoom, GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnAddRenter, GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)));
		gl_CardBtnAvailable.setVerticalGroup(gl_CardBtnAvailable.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_CardBtnAvailable.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnViewRoom, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
						.addComponent(btnEditRoom, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAddRenter, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)));
		CardBtnAvailable.setLayout(gl_CardBtnAvailable);
		setLayout(groupLayout);

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
	}

}
