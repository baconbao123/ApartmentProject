package component;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import dao.ApartmentDao;
import dao.ContractDao;
import scrollbar.ScrollBarCustom;
import view.AppStateManager;
import view.CardRoom;
import entity.*;
import formAdView.ViewRoomApart;

public class CardApartment extends JPanel {

	
	
	private static final long serialVersionUID = 1L;
	private JScrollPane cardScroll;
	private JPanel panel;
	
	private static final int NUM_FLOORS = 4;
	private static final int ROOMS_PER_FLOOR = 4;
	private static final int TOTAL_ROOMS = NUM_FLOORS * ROOMS_PER_FLOOR;

	/**
	 * Create the panel.
	 */
	public CardApartment() {
		setBorder(null);
		setBounds(0, 0, 1062, 650);
		setLayout(new BorderLayout(0, 0));
		
		cardScroll = new JScrollPane();
		cardScroll.setBorder(null);
		add(cardScroll, BorderLayout.CENTER);
		cardScroll.setVerticalScrollBar(new ScrollBarCustom());
		
		panel = new JPanel();
		cardScroll.setViewportView(panel);
		panel.setLayout(new GridLayout(NUM_FLOORS, ROOMS_PER_FLOOR, 15, 20)); // Sử dụng GridLayout với cỡ khoảng cách 10 pixel
		
		var daoAp = new ApartmentDao();
		List<Apartment> apartments = daoAp.selectApartment();
		Map<Integer, AppStateManager.RoomState> roomColors = AppStateManager.loadAppState();

		var daoCon = new ContractDao();
		List<Contract> contracts = daoCon.selecOwnerApart();
		
		int id = 1;
		
		for (int i = 1; i <= apartments.size(); i++) {
//		for (int i = 1; i <= TOTAL_ROOMS; i++) {
			JPanel floorPanel = new JPanel();
			floorPanel.setLayout(new BorderLayout());			
			
			// Kiểm tra xem nếu trong danh sách Apartment có phòng thứ i thì mới truyền dữ liệu vào CardRoomAvailable
			var cardRoom = new CardRoom();
			
			
			
			if(apartments.size() >= i) {
				JLabel lbFloor = new JLabel("Floor " + apartments.get(i-1).getFloor());
				lbFloor.setFont(new Font("Arial", Font.BOLD, 14));
				floorPanel.add(lbFloor, BorderLayout.NORTH);
				

				if (roomColors != null && roomColors.containsKey(apartments.get(i - 1).getRoomNumber())) {
		            AppStateManager.RoomState roomState = roomColors.get(apartments.get(i - 1).getRoomNumber());
		            Color savedColor = roomState.getColor();
		            String cardLayoutState = roomState.getCardLayoutState();

		            cardRoom.setBackgroundColor(savedColor);

		            CardLayout cardLayout = (CardLayout) cardRoom.CardButton.getLayout();
		            cardLayout.show(cardRoom.CardButton, cardLayoutState);
		        }
	            
				cardRoom.setApartmenNumber(apartments.get(i-1).getRoomNumber()); 
				cardRoom.setPeopleNumber(apartments.get(i-1).getPeople(), apartments.get(i-1).getPeopleMaximun());
				cardRoom.setCurrentApartNum(apartments.get(i-1).getRoomNumber());
				cardRoom.setCurrentFloor(apartments.get(i-1).getFloor());
				cardRoom.setIdApartRoom(id);
				
				id++;
			
				// get owner name
				 Contract matchingContract = findContractForApartment(contracts, apartments.get(i - 1).getRoomNumber());
			        if (matchingContract != null) {
			            cardRoom.setOwnerTex(matchingContract.getOwnerName());
			        } else {
			            cardRoom.setOwnerTex(null); 
		        }
				
			}
			
			
			
			floorPanel.add(cardRoom, BorderLayout.CENTER);
			
			panel.add(floorPanel);
		}
	}

	private Contract findContractForApartment(List<Contract> contracts, int roomNumber) {
		for (Contract contract : contracts) {
	        if (contract.getApartNum() == roomNumber) {
	            return contract;
	        }
	    }
	    return null;
	}
	

	
}
