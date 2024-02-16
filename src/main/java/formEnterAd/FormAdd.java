package formEnterAd;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import view.AppStateManager;
import view.CardRoom;

import java.awt.Color;
import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;

public class FormAdd extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panelForm;
	private JPanel panelAddRenter;
	private JPanel panelAddRoom;
	private JPanel panelAddContract;
	private JButton btnSaveForm;
	private JButton btnSaveForm2;
	private JButton btnSaveForm3;
	private FormAddContract formAddContract;
	private FormAddRenter formAddRenter;
	private FormViewAddRoom formViewAddRoom;
	
	private String cardName="addRenter";	
	
	private CardRoom cardRoomNum;
	
	
	
	public void setNumApart(CardRoom cardRoom) {
		cardRoomNum = cardRoom;
	}
	
	
	public FormAddContract getFormAddContract() {
        return formAddContract;
    }
	
	public FormViewAddRoom getFormViewAddRoom() {
		return formViewAddRoom;
	}



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormAdd frame = new FormAdd();
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
	public FormAdd() {
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 626);
		
		panelForm = new JPanel();
		setContentPane(panelForm);
		panelForm.setLayout(new CardLayout(0, 0));
		
		//panel renter
		panelAddRenter = new JPanel();
		panelForm.add(panelAddRenter, "addRenter");
		btnSaveForm = new JButton("Compelete Form 1 and Next");
		

		
		//panel room
		panelAddRoom = new JPanel();
		panelForm.add(panelAddRoom, "addRoom");
		btnSaveForm2 = new JButton("Compelete Form 2 and Next");
		

		
		// panel contract
		panelAddContract = new JPanel();
		formAddContract = new FormAddContract();
		
		panelForm.add(panelAddContract, "addContract");
		
		
		
		btnSaveForm3 = new JButton("Compelete Form 3 and Close The Form");
		
		
		
		changeForm(cardName);
		initComponent();
	}
	
	
	
	
	
	// change to card
	private void changeForm(String cardName) {
		CardLayout cardLayout = (CardLayout) panelForm.getLayout();
		cardLayout.show(panelForm, cardName);
		
		btnSaveForm.addActionListener(e -> {  // btn change form to card room
			changeForm("addRoom");
		});
		btnSaveForm2.addActionListener(e -> changeForm("addContract")); // btn change form to card contract
		btnSaveForm3.addActionListener(e -> {   // btn close form
			setVisible(false);
			
			if(cardRoomNum!=null) {
				int apartNum = cardRoomNum.getCurrentApartNum();
				System.out.println(apartNum);
				cardRoomNum.setBackgroundColor(new Color(39, 158, 255));
				System.out.println("apartNum " + apartNum);
				
				CardLayout cardLayoutApNum = (CardLayout) cardRoomNum.CardButton.getLayout();
				cardLayoutApNum.show(cardRoomNum.CardButton, "rented");
				
				AppStateManager.saveAppState(apartNum, new Color(39, 158, 255), "rented");
			}
		});
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


	private void initComponent() {
		panelForm.setBackground(new Color(255, 255, 255));
		panelForm.setBorder(null);

		
		//panel renter
		panelAddRenter.setBackground(Color.WHITE);
		btnSaveForm.setForeground(Color.WHITE);
		btnSaveForm.setFont(new Font("Arial", Font.BOLD, 12));
		btnSaveForm.setFocusable(false);
		btnSaveForm.setBorder(null);
		btnSaveForm.setBackground(new Color(38, 185, 154));
		
		formAddRenter = new FormAddRenter();
		GroupLayout gl_panelAddRenter = new GroupLayout(panelAddRenter);
		gl_panelAddRenter.setHorizontalGroup(
			gl_panelAddRenter.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelAddRenter.createSequentialGroup()
					.addGap(148)
					.addComponent(btnSaveForm, GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
					.addGap(150))
				.addGroup(gl_panelAddRenter.createSequentialGroup()
					.addComponent(formAddRenter, GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panelAddRenter.setVerticalGroup(
			gl_panelAddRenter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelAddRenter.createSequentialGroup()
					.addComponent(formAddRenter, GroupLayout.PREFERRED_SIZE, 505, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnSaveForm, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(17, Short.MAX_VALUE))
		);
		panelAddRenter.setLayout(gl_panelAddRenter);
		
		// panel room
		panelAddRoom.setBackground(Color.WHITE);
		btnSaveForm2.setForeground(Color.WHITE);
		btnSaveForm2.setFont(new Font("Arial", Font.BOLD, 12));
		btnSaveForm2.setFocusable(false);
		btnSaveForm2.setBorder(null);
		btnSaveForm2.setBackground(new Color(38, 185, 154));
		
		formViewAddRoom = new FormViewAddRoom();
		GroupLayout gl_panelAddRoom = new GroupLayout(panelAddRoom);
		gl_panelAddRoom.setHorizontalGroup(
			gl_panelAddRoom.createParallelGroup(Alignment.LEADING)
				.addComponent(formViewAddRoom, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
				.addGroup(gl_panelAddRoom.createSequentialGroup()
					.addGap(142)
					.addComponent(btnSaveForm2, GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
					.addGap(144))
		);
		gl_panelAddRoom.setVerticalGroup(
			gl_panelAddRoom.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panelAddRoom.createSequentialGroup()
					.addComponent(formViewAddRoom, GroupLayout.PREFERRED_SIZE, 493, GroupLayout.PREFERRED_SIZE)
					.addGap(31)
					.addComponent(btnSaveForm2, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(32, Short.MAX_VALUE))
		);
		panelAddRoom.setLayout(gl_panelAddRoom);
		
		// panel contract
		
		panelAddContract.setBackground(Color.WHITE);
		btnSaveForm3.setForeground(Color.WHITE);
		btnSaveForm3.setFont(new Font("Arial", Font.BOLD, 12));
		btnSaveForm3.setFocusable(false);
		btnSaveForm3.setBorder(null);
		btnSaveForm3.setBackground(new Color(38, 185, 154)); 
		
		
		GroupLayout gl_panelAddContract = new GroupLayout(panelAddContract);
		gl_panelAddContract.setHorizontalGroup(
			gl_panelAddContract.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelAddContract.createSequentialGroup()
					.addComponent(formAddContract, GroupLayout.PREFERRED_SIZE, 507, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_panelAddContract.createSequentialGroup()
					.addGap(137)
					.addComponent(btnSaveForm3, GroupLayout.PREFERRED_SIZE, 240, Short.MAX_VALUE)
					.addGap(140))
		);
		gl_panelAddContract.setVerticalGroup(
			gl_panelAddContract.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelAddContract.createSequentialGroup()
					.addComponent(formAddContract, GroupLayout.PREFERRED_SIZE, 525, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnSaveForm3, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(20, Short.MAX_VALUE))
		);
		panelAddContract.setLayout(gl_panelAddContract);
	}
}
