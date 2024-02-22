package formUser;

import javax.swing.JPanel;

import component.Login;
import dao.ApartUserDao;
import entity.Apartment;
import entity.Contract;
import formUserCom.CardRoomUser;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.GridLayout;
import java.util.List;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JScrollPane;

public class Home extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblHome;
	private int userId = Login.getId();
	private JScrollPane scrollPane;
	private JPanel panelAparts;
	
	public Home() {
		setBounds(0, 0, 1100, 800);
		System.out.println("userId" + userId);
		
		scrollPane = new JScrollPane();
		panelAparts = new JPanel();
		scrollPane.setViewportView(panelAparts);
		
		
		var dao = new ApartUserDao();
		List<Apartment> apartments = dao.selApartOfUser(userId);
		panelAparts.setLayout(new GridLayout(4, 3, 20, 25));
//		panelAparts.setLayout(new GridLayout(1, 2));
		
		System.out.println(apartments);
		
		for(int i = 1; i <= apartments.size(); i++) {
			panelAparts.setLayout(new GridLayout(apartments.size(), 1));
			var cardRoomUser = new CardRoomUser();
//			cardRoomUser.setLayout(new );

			 cardRoomUser.setPreferredSize(new Dimension(240, 195));
			 
			if(apartments.size()>=i) {
				panelAparts.add(cardRoomUser);
			}
			
			
		}
		
		panelAparts.setLayout(new GridLayout(1, 2));
//		
		
		initComponent();
		
	}
	
	
	
//	JPanel panelToShowCardRoomUsers = new JPanel();
//	panelToShowCardRoomUsers.setLayout(new GridLayout(userContract.size(), 1));
//	for(Contract con:  userContract) {
//	for (Contract contract : userContract) {
//	    CardRoomUser cardRoomUser = new CardRoomUser();
//	    
//	    // Assuming you have methods in CardRoomUser class to set contract details
////	    cardRoomUser.setRoomDetails(contract.getApartNum(), contract.getOwnerName(), contract.getPrice());
//	    
//	    panelToShowCardRoomUsers.add(cardRoomUser);
//	}		
//	panelAparts.setLayout(new GridLayout(2, 2));

	
	
	
	
	
	private void initComponent() {
		lblHome = new JLabel("Home");
		lblHome.setFont(new Font("Arial", Font.BOLD, 18));
		
		
		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(524)
					.addComponent(lblHome, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(524))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1062, Short.MAX_VALUE)
					.addGap(19))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addComponent(lblHome)
					.addGap(11)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 651, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(94, Short.MAX_VALUE))
		);
		
		
		
		
//		cardRoomUser = new CardRoomUser();
//		GroupLayout gl_panelAparts = new GroupLayout(panelAparts);
//		gl_panelAparts.setHorizontalGroup(
//			gl_panelAparts.createParallelGroup(Alignment.LEADING)
//				.addGroup(gl_panelAparts.createSequentialGroup()
//					.addComponent(cardRoomUser, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)
//					.addContainerGap(816, Short.MAX_VALUE))
//		);
//		gl_panelAparts.setVerticalGroup(
//			gl_panelAparts.createParallelGroup(Alignment.LEADING)
//				.addGroup(gl_panelAparts.createSequentialGroup()
//					.addComponent(cardRoomUser, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
//					.addContainerGap(522, Short.MAX_VALUE))
//		);
//		panelAparts.setLayout(gl_panelAparts);
		setLayout(groupLayout);
	}
}
