package formUser;

import javax.swing.JPanel;

import component.Login;
import dao.ApartUserDao;
import dao.ApartmentDao;
import dao.ContractDao;
import dao.FeesDao;
import entity.Apartment;
import entity.Contract;
import formUserCom.CardRoomUser;
import model.Fees;
import scrollbar.ScrollBarCustom;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import java.awt.SystemColor;

public class Home extends JPanel {

	private static final long serialVersionUID = 1L;
	private int userId = Login.getId();
	private JScrollPane scrollPane;
	private JPanel panelAparts;
	private JLabel lblNewLabel;
	private JLabel lblBillOrNoti;
	private JPanel panel;
	
	public Home() {
		setBounds(0, 0, 1100, 800);
		System.out.println("userId" + userId);
		panel = new JPanel();
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED, SystemColor.menu, SystemColor.menu, SystemColor.menu, SystemColor.menu));
		panelAparts = new JPanel();
		panelAparts.setBorder(null);
		scrollPane.setVerticalScrollBar(new ScrollBarCustom());
		
		var dao = new ApartUserDao();
		List<Apartment> apartments = dao.selApartOfUser(userId);
		
		var daoOwner = new ContractDao();
		List<Contract> contracts = daoOwner.selecOwnerApart();
		
		var daoIDRoomate = new ApartmentDao();
		List<Contract> conIDRoomate = daoIDRoomate.selInfoRoomate();
		
		var daoFee = new ApartmentDao();
		List<Fees> fees = daoFee.selectMoneyByAll();
		
		System.out.println(apartments);
		
		panelAparts.setLayout(new GridLayout(0, 2, 50, 50));

		for(int i = 1; i <= apartments.size(); i++) {
	        JPanel floorPanel = new JPanel();
	        floorPanel.setLayout(new BorderLayout());
	        var cardRoomUser = new CardRoomUser();
	        if(apartments.size() >= i) {
	            JLabel lblFloor = new JLabel("Floor " + apartments.get(i-1).getFloor());
	            lblFloor.setFont(new Font("Arial", Font.BOLD, 14));
	            floorPanel.add(lblFloor, BorderLayout.NORTH);

	            cardRoomUser.setRoomNumber(apartments.get(i-1).getRoomNumber());
	            cardRoomUser.setPeopleNumber(apartments.get(i-1).getPeople());
	            cardRoomUser.setPeopleMax(apartments.get(i-1).getPeopleMaximun());
	            cardRoomUser.setTypeRoom(apartments.get(i-1).getType());
	            
	            Contract matchContr = findContractFromRoom(contracts, apartments.get(i-1).getRoomNumber());
	            if(matchContr!=null) {
	            	cardRoomUser.setOwnerName(matchContr.getOwnerName());
	            }
	            
				Contract matchingConRoomate = findContractFromRoom(conIDRoomate, apartments.get(i-1).getRoomNumber());
				if(matchingConRoomate!=null) {
					cardRoomUser.setInforRoomate(matchingConRoomate.getRoomates());
					cardRoomUser.setImgContracts(matchingConRoomate.getImgContracs());
					cardRoomUser.setConStatus(matchingConRoomate.isStatus());
					cardRoomUser.setFromDateCon(matchingConRoomate.getFormDate());
					cardRoomUser.setToDateCon(matchingConRoomate.getToDate());
					cardRoomUser.setIdCon(matchingConRoomate.getId());
				}
	            
	            Fees matchFee = findAllFeeForRoom(fees, apartments.get(i-1).getRoomNumber());
	            if(matchFee!=null) {
	            	cardRoomUser.setAllMoney(matchFee.getTotal());
	            } 
	            
	            
	            
	            cardRoomUser.setPreferredSize(new Dimension(243, 249)); // Adjust width and height as needed
	        
	        }

	        floorPanel.add(cardRoomUser, BorderLayout.CENTER);
	        panelAparts.add(floorPanel);
	        
	    }
		
		JPanel containerPanel = new JPanel();
		containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
		containerPanel.add(panelAparts);

		// Đặt panelAparts vào JScrollPane
		scrollPane.setViewportView(containerPanel);

		
		
		initComponent();
	
	}
	
	
	private Contract findContractFromRoom(List<Contract> contracts, int room) {
		for(Contract contr: contracts) {
			if(contr.getApartNum() == room) {
				return contr;
			}
		}
		
		return null;
	}
	
	private Fees findAllFeeForRoom(List<Fees> fees, int room) {
		for(Fees fee: fees) {
			if(fee.getRoom() == room) {
				return fee;
			}
		}
		return null;
	}
	

	
	
	
	
	private void initComponent() {
		
		lblNewLabel = new JLabel("List of rented rooms.");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		
		lblBillOrNoti = new JLabel("Bill OR noti");
		lblBillOrNoti.setFont(new Font("Arial", Font.BOLD, 20));
		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED, 574, Short.MAX_VALUE)
							.addComponent(lblBillOrNoti, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
							.addGap(109))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 547, GroupLayout.PREFERRED_SIZE)
							.addGap(41)
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
							.addGap(20))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(66)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblBillOrNoti, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(18)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 273, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(86, Short.MAX_VALUE))
		);
		
		
		setLayout(groupLayout);
	}
}
