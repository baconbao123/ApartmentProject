package component;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import dao.ApartmentDao;
import dao.ContractDao;
import scrollbar.ScrollBarCustom;
import view.AppStateManager;
import view.AppStateManager.RoomState;
import view.CardRoom;
import entity.*;
import formEnterAd.FrameAddRoom;
import model.Fees;

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
		panel.setLayout(new GridLayout(NUM_FLOORS, ROOMS_PER_FLOOR, 15, 20)); 

		var daoAp = new ApartmentDao();
		List<Apartment> apartments = daoAp.selectApartment();
		

		Map<Integer, AppStateManager.RoomState> roomColors = AppStateManager.loadAppState();

		var daoCon = new ContractDao();
		List<Contract> contracts = daoCon.selecOwnerApart();

		var daoPeople = new ApartmentDao();
		List<Apartment> apartPeop = daoPeople.selectApartRoomate();
		
		var daoIDRoomate = new ApartmentDao();
		List<Contract> conIDRoomate = daoIDRoomate.selInfoRoomate();
		
		var feeDao = new ApartmentDao();
		List<Fees> fees = feeDao.selectMoneyByAll();
		
		var feeMothDao = new ApartmentDao();
		List<Fees> feeMonth = feeMothDao.selectMonthByMoth();

		int id = 1;

		for (int i = 1; i <= apartments.size(); i++) {
//		for (int i = 1; i <= TOTAL_ROOMS; i++) {
			JPanel floorPanel = new JPanel();
			floorPanel.setLayout(new BorderLayout());

			var cardRoom = new CardRoom();

			if (apartments.size() >= i) {
				JLabel lbFloor = new JLabel("Floor " + apartments.get(i - 1).getFloor());
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
				cardRoom.setApartmenNumber(apartments.get(i - 1).getRoomNumber());
				cardRoom.setPeopleNumber(apartments.get(i - 1).getPeopleMaximun());
				cardRoom.setCurrentApartNum(apartments.get(i - 1).getRoomNumber());
				cardRoom.setCurrentFloor(apartments.get(i - 1).getFloor());
				cardRoom.setTypeRoom(apartments.get(i-1).getType());
				cardRoom.setPeopleMax(apartments.get(i-1).getPeopleMaximun());
				cardRoom.setIdApartRoom(id);

				id++;

				// get owner name
				Contract matchingContract = findContractForApartment(contracts, apartments.get(i - 1).getRoomNumber());
				if (matchingContract != null) {
					cardRoom.setOwnerTex(matchingContract.getOwnerName());
					cardRoom.setPeopleInRoom(String.valueOf(matchingContract.getRoomates()));
				} else {
					cardRoom.setOwnerTex(null);
					cardRoom.setPeopleInRoom("0");
				}
				
				// get ID Roomate
				Contract matchingConRoomate = findContractForApartment(conIDRoomate, apartments.get(i-1).getRoomNumber());
				if(matchingConRoomate!=null) {
					cardRoom.setInforRoomate(matchingConRoomate.getRoomates());
					cardRoom.setImgContracts(matchingConRoomate.getImgContracs());
					cardRoom.setConStatus(matchingConRoomate.isStatus());
					cardRoom.setFromDateCon(matchingConRoomate.getFormDate());
					cardRoom.setToDateCon(matchingConRoomate.getToDate());
					cardRoom.setIdCon(matchingConRoomate.getId());
				} else {
					cardRoom.setInforRoomate("Cannot find a id");
				}
 
				// fee
				Fees matchFee = findFeeForApartment(fees, apartments.get(i - 1).getRoomNumber());
				if (matchFee != null) {
					cardRoom.setMoneyApart(String.valueOf(matchFee.getTotal()));
					cardRoom.setIdFeeAll(matchFee.getIdFeeAll());
				} else {
					cardRoom.setMoneyApart("0");
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

	private Fees findFeeForApartment(List<Fees> fees, int roomNumber) {
		for (Fees fee : fees) {
			if (fee.getRoom() == roomNumber) {
				return fee;
			}
		}
		return null;
	}
	

//	public void refreshApartments() {
//	    panel.removeAll(); // Remove all components from the panel
//	    panel.revalidate(); // Revalidate the panel to reflect the changes
//	    panel.repaint(); // Repaint the panel to update the display
//	    // Add the code inside your `CardApartment` constructor that populates the panel with updated data
//	}
	
	

}
