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
		setBounds(0, 0, 246, 257);
		ReadRoomNum = new JLabel("101");
		ReadRoomNum.setBounds(115, 21, 31, 16);
		ReadOwner = new JLabel("None");
		ReadOwner.setBounds(74, 60, 132, 14);
		ReadPrice = new JLabel("0");
		ReadPrice.setBounds(74, 94, 132, 14);
		ReadPrice.setBackground(new Color(217, 181, 67));
		ReadPeople = new JLabel("0");
		ReadPeople.setForeground(Color.WHITE);
		ReadPeople.setBounds(74, 126, 11, 16);
		ReadMaxPeople = new JLabel("0");
		ReadMaxPeople.setBounds(104, 126, 7, 16);
		
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
		lblRoomNumber.setBounds(74, 21, 40, 16);
		lblRoomNumber.setForeground(Color.WHITE);
		lblRoomNumber.setFont(new Font("Arial", Font.BOLD, 13));
		ReadRoomNum.setForeground(Color.WHITE);
		ReadRoomNum.setFont(new Font("Arial", Font.BOLD, 13));
		ReadOwner.setForeground(Color.WHITE);
		ReadOwner.setFont(new Font("Arial", Font.BOLD, 13));
		ReadPrice.setForeground(Color.WHITE);
		ReadPrice.setFont(new Font("Arial", Font.BOLD, 13));
		ReadMaxPeople.setForeground(Color.WHITE);
		ReadMaxPeople.setFont(new Font("Arial", Font.BOLD, 13));
		ReadMaxPeople.setBackground(new Color(234, 56, 0));
		ReadPeople.setFont(new Font("Arial", Font.BOLD, 13));
		
		btnViewRoom = new JButton("View Room");
		btnViewRoom.setBounds(15, 160, 221, 28);
		btnViewRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnViewRoomActionPerformed(e);
			}
		});
		btnViewRoom.setForeground(Color.WHITE);
		btnViewRoom.setFont(new Font("Arial", Font.BOLD, 12));
		btnViewRoom.setFocusable(false);
		btnViewRoom.setBorder(null);
		btnViewRoom.setBackground(new Color(0, 154, 33));
		
		btnViewContract = new JButton("View Contract");
		btnViewContract.setBounds(15, 198, 221, 28);
		btnViewContract.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnViewContractActionPerformed(e);
			}
		});
		btnViewContract.setForeground(Color.WHITE);
		btnViewContract.setFont(new Font("Arial", Font.BOLD, 12));
		btnViewContract.setFocusable(false);
		btnViewContract.setBorder(null);
		btnViewContract.setBackground(new Color(215, 125, 0));
		
		lblIconRoom = new JLabel("");
		lblIconRoom.setBounds(40, 21, 16, 16);
		lblIconRoom.setIcon(new ImageIcon(CardRoomUser.class.getResource("/icon/home.png")));
		
		lblIconOwner = new JLabel("");
		lblIconOwner.setBounds(40, 58, 16, 16);
		lblIconOwner.setIcon(new ImageIcon(CardRoomUser.class.getResource("/icon/check.png")));
		
		lblIconMoney = new JLabel("");
		lblIconMoney.setBounds(40, 92, 16, 16);
		lblIconMoney.setIcon(new ImageIcon(CardRoomUser.class.getResource("/icon/payment.png")));
		
		lblIconPeople = new JLabel("");
		lblIconPeople.setBounds(40, 126, 16, 16);
		lblIconPeople.setIcon(new ImageIcon(CardRoomUser.class.getResource("/icon/users.png")));
		
		lblRenter = new JLabel("renters");
		lblRenter.setBounds(117, 126, 73, 16);
		lblRenter.setForeground(Color.WHITE);
		lblRenter.setFont(new Font("Arial", Font.BOLD, 13));
		lblRenter.setBackground(Color.BLACK);
		
		lblR = new JLabel("/");
		lblR.setBounds(91, 126, 7, 16);
		lblR.setForeground(Color.WHITE);
		lblR.setFont(new Font("Arial", Font.BOLD, 13));
		lblR.setBackground(Color.BLACK);
		setLayout(null);
		add(lblIconRoom);
		add(lblRoomNumber);
		add(ReadRoomNum);
		add(lblIconOwner);
		add(ReadOwner);
		add(lblIconMoney);
		add(ReadPrice);
		add(btnViewRoom);
		add(btnViewContract);
		add(lblIconPeople);
		add(ReadPeople);
		add(lblR);
		add(ReadMaxPeople);
		add(lblRenter);
		
	}
	
	
	
	
}
