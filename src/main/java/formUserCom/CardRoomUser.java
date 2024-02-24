package formUserCom;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.BasicStroke;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.LayoutStyle.ComponentPlacement;


import dao.ApartmentDao;
import entity.Apartment;
import formAdView.ViewRoomContract;
import formUserView.ViewRoomOfUser;
import net.sf.jasperreports.components.spiderchart.SpiderChartFillFactory;

import java.awt.SystemColor;
import java.awt.geom.RoundRectangle2D;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CardRoomUser extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblRoomNumber;
	private JLabel ReadRoomNum;
	private JLabel ReadOwner;
	private JLabel ReadPrice;
	private JLabel ReadMaxPeople;
	private JLabel ReadPeople;
	private JButton btnViewRoom;
	private JButton btnViewContract;
	private JLabel lblIconRoom;
	private JLabel lblIconOwner;
	private JLabel lblIconMoney;
	private JLabel lblIconPeople;
	private JLabel lblRenter;
	private JLabel lblR;
	
	
	private String typeRoom;
	private String inforRoomate;
	private String imgContracts;
	private Boolean conStatus;
	private Date fromDateCon;
	private Date toDateCon;
	private int idCon;
	
	public String getImgContracts() {
		return imgContracts;
	}

	public void setImgContracts(String imgContracts) {
		this.imgContracts = imgContracts;
	}

	public Boolean getConStatus() {
		return conStatus;
	}

	public void setConStatus(Boolean conStatus) {
		this.conStatus = conStatus;
	}

	public Date getFromDateCon() {
		return fromDateCon;
	}

	public void setFromDateCon(Date fromDateCon) {
		this.fromDateCon = fromDateCon;
	}

	public Date getToDateCon() {
		return toDateCon;
	}

	public void setToDateCon(Date toDateCon) {
		this.toDateCon = toDateCon;
	}

	public int getIdCon() {
		return idCon;
	}

	public void setIdCon(int idCon) {
		this.idCon = idCon;
	}


	

	public String getTypeRoom() {
		return typeRoom;
	}

	public void setTypeRoom(String typeRoom) {
		this.typeRoom = typeRoom;
	}
	
	

	public String getInforRoomate() {
		return inforRoomate;
	}

	public void setInforRoomate(String inforRoomate) {
		this.inforRoomate = inforRoomate;
	}

	/**
	 * Create the panel.
	 */
	public CardRoomUser() {
		setBackground(new Color(39, 158, 255));
		setBounds(0, 0, 240, 195);
		ReadRoomNum = new JLabel("101");
		ReadOwner = new JLabel("None");
		ReadPrice = new JLabel("0");
		ReadPrice.setBackground(new Color(244, 0, 0));
		ReadPeople = new JLabel("0");
		ReadMaxPeople = new JLabel("0");
		
		initComponent();
	}
	
	public void setRoomNumber(Integer roomNumber) {
		ReadRoomNum.setText(String.valueOf(roomNumber));
	}
	
	public void setPeopleNumber(Integer peopleRoom) {
		ReadPeople.setText(String.valueOf(peopleRoom));
	}
	
	public void setPeopleMax(Integer peopleMax) {
		ReadMaxPeople.setText(String.valueOf(peopleMax));
	}
	
	public void setOwnerName(String name) {
		ReadOwner.setText(name);
	}
	
	public void setAllMoney(Float money) {
		ReadPrice.setText(String.valueOf(money));
	}
	
	protected void btnViewRoomActionPerformed(ActionEvent e) {
		var view = new ViewRoomOfUser();
		view.setVisible(true);
		view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		view.setLocationRelativeTo(null);
		
		view.setApNum(ReadRoomNum.getText());
		view.setMaxPeop(ReadMaxPeople.getText());
		view.setType(typeRoom);
		view.loadRoomateInfor(inforRoomate);
		
		var dao = new ApartmentDao();
		List<Apartment> apartments = dao.selectApartment();
		
		for(Apartment apart : apartments) {
			if(apart.getRoomNumber() == Integer.parseInt(ReadRoomNum.getText())) {
				view.displayCon(apart.getConvenient());
				view.displayUti(apart.getUtilities());
			}
		}
	}
	
	protected void btnViewContractActionPerformed(ActionEvent e) {
		String apNumStr = String.valueOf(ReadRoomNum.getText());
		String ownerApStr = ReadOwner.getText();
		String conStatusStr; 
		if (conStatus) {
			conStatusStr = "On";
		} else {
			conStatusStr = "Off";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateFromCon = sdf.format(fromDateCon);
		String toDateConStr = sdf.format(toDateCon);
		String imgCon = imgContracts;
		String idRoomate = inforRoomate;
//		
		
		var view = new ViewRoomContract(apNumStr, ownerApStr, conStatusStr, dateFromCon, toDateConStr, imgCon, idRoomate);
		view.setVisible(true);
		view.setLocationRelativeTo(null);
		view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	
	
	
	
	
	
	
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		RoundRectangle2D roundRect = new RoundRectangle2D.Double(0, 0, getWidth()-2, getHeight()-2, 0, 0);
		
		g2d.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke(2));
		g2d.draw(roundRect);
		g2d.dispose();
	}
	

	private void initComponent() {
		lblRoomNumber = new JLabel("Room");
		lblRoomNumber.setForeground(Color.BLACK);
		lblRoomNumber.setFont(new Font("Arial", Font.BOLD, 13));
		ReadRoomNum.setForeground(Color.BLACK);
		ReadRoomNum.setFont(new Font("Arial", Font.BOLD, 13));
		ReadOwner.setForeground(Color.BLACK);
		ReadOwner.setFont(new Font("Arial", Font.BOLD, 13));
		ReadPrice.setForeground(new Color(244, 0, 0));
		ReadPrice.setFont(new Font("Arial", Font.BOLD, 13));
		ReadMaxPeople.setForeground(Color.BLACK);
		ReadMaxPeople.setFont(new Font("Arial", Font.BOLD, 13));
		ReadMaxPeople.setBackground(Color.BLACK);
		ReadPeople.setFont(new Font("Arial", Font.BOLD, 13));
		
		btnViewRoom = new JButton("View Room");
		btnViewRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnViewRoomActionPerformed(e);
			}
		});
		btnViewRoom.setForeground(Color.WHITE);
		btnViewRoom.setFont(new Font("Arial", Font.BOLD, 12));
		btnViewRoom.setFocusable(false);
		btnViewRoom.setBorder(null);
		btnViewRoom.setBackground(Color.LIGHT_GRAY);
		
		btnViewContract = new JButton("View Contract");
		btnViewContract.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnViewContractActionPerformed(e);
			}
		});
		btnViewContract.setForeground(Color.WHITE);
		btnViewContract.setFont(new Font("Arial", Font.BOLD, 12));
		btnViewContract.setFocusable(false);
		btnViewContract.setBorder(null);
		btnViewContract.setBackground(new Color(210, 180, 140));
		
		lblIconRoom = new JLabel("");
		lblIconRoom.setIcon(new ImageIcon(CardRoomUser.class.getResource("/icon/home(1).png")));
		
		lblIconOwner = new JLabel("");
		lblIconOwner.setIcon(new ImageIcon(CardRoomUser.class.getResource("/icon/check(1).png")));
		
		lblIconMoney = new JLabel("");
		lblIconMoney.setIcon(new ImageIcon(CardRoomUser.class.getResource("/icon/payment(1).png")));
		
		lblIconPeople = new JLabel("");
		lblIconPeople.setIcon(new ImageIcon(CardRoomUser.class.getResource("/icon/users(1).png")));
		
		lblRenter = new JLabel("renters");
		lblRenter.setForeground(Color.BLACK);
		lblRenter.setFont(new Font("Arial", Font.BOLD, 13));
		lblRenter.setBackground(Color.BLACK);
		
		lblR = new JLabel("/");
		lblR.setForeground(Color.BLACK);
		lblR.setFont(new Font("Arial", Font.BOLD, 13));
		lblR.setBackground(Color.BLACK);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(40)
					.addComponent(lblIconRoom)
					.addGap(18)
					.addComponent(lblRoomNumber, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(ReadRoomNum, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(40)
					.addComponent(lblIconOwner)
					.addGap(18)
					.addComponent(ReadOwner, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(40)
					.addComponent(lblIconMoney)
					.addGap(18)
					.addComponent(ReadPrice, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnViewRoom, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnViewContract, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(40)
					.addComponent(lblIconPeople)
					.addGap(18)
					.addComponent(ReadPeople, GroupLayout.PREFERRED_SIZE, 11, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblR, GroupLayout.PREFERRED_SIZE, 7, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(ReadMaxPeople)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblRenter, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(21)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblIconRoom)
								.addComponent(lblRoomNumber)
								.addComponent(ReadRoomNum))
							.addGap(21)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblIconOwner)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(2)
									.addComponent(ReadOwner, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblIconMoney)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(2)
									.addComponent(ReadPrice, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblIconPeople)
								.addComponent(ReadPeople)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(ReadMaxPeople)
									.addComponent(lblRenter, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(126)
							.addComponent(lblR, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnViewContract, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
						.addComponent(btnViewRoom, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
					.addGap(11))
		);
		setLayout(groupLayout);
		
	}
	
	
	
	
}
