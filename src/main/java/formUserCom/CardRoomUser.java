package formUserCom;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.SystemColor;
import javax.swing.ImageIcon;

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

	/**
	 * Create the panel.
	 */
	public CardRoomUser() {
		setBackground(new Color(39, 158, 255));
		setBounds(0, 0, 240, 195);
		ReadRoomNum = new JLabel("101");
		ReadOwner = new JLabel("None");
		ReadPrice = new JLabel("0");
		ReadPeople = new JLabel("2");
		ReadMaxPeople = new JLabel("/ 4 renters");
		
		initComponent();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	private void initComponent() {
		lblRoomNumber = new JLabel("Room");
		lblRoomNumber.setBounds(74, 21, 40, 16);
		lblRoomNumber.setForeground(Color.BLACK);
		lblRoomNumber.setFont(new Font("Arial", Font.BOLD, 13));
		
		
		ReadRoomNum.setBounds(115, 21, 31, 16);
		ReadRoomNum.setForeground(Color.BLACK);
		ReadRoomNum.setFont(new Font("Arial", Font.BOLD, 13));
		
		
		ReadOwner.setBounds(74, 60, 132, 14);
		ReadOwner.setForeground(Color.BLACK);
		ReadOwner.setFont(new Font("Arial", Font.BOLD, 13));
		
		
		ReadPrice.setBounds(74, 94, 132, 14);
		ReadPrice.setForeground(Color.BLACK);
		ReadPrice.setFont(new Font("Arial", Font.BOLD, 13));
		
		
		ReadMaxPeople.setBounds(84, 126, 73, 16);
		ReadMaxPeople.setForeground(Color.BLACK);
		ReadMaxPeople.setFont(new Font("Arial", Font.BOLD, 13));
		ReadMaxPeople.setBackground(Color.BLACK);
		
		
		ReadPeople.setBounds(74, 126, 11, 16);
		ReadPeople.setFont(new Font("Arial", Font.BOLD, 13));
		
		btnViewRoom = new JButton("View Room");
		btnViewRoom.setBounds(10, 160, 106, 24);
		btnViewRoom.setForeground(Color.WHITE);
		btnViewRoom.setFont(new Font("Arial", Font.BOLD, 12));
		btnViewRoom.setFocusable(false);
		btnViewRoom.setBorder(null);
		btnViewRoom.setBackground(Color.LIGHT_GRAY);
		
		btnViewContract = new JButton("View Contract");
		btnViewContract.setBounds(124, 160, 106, 24);
		btnViewContract.setForeground(Color.WHITE);
		btnViewContract.setFont(new Font("Arial", Font.BOLD, 12));
		btnViewContract.setFocusable(false);
		btnViewContract.setBorder(null);
		btnViewContract.setBackground(new Color(210, 180, 140));
		
		lblIconRoom = new JLabel("");
		lblIconRoom.setBounds(40, 21, 16, 16);
		lblIconRoom.setIcon(new ImageIcon(CardRoomUser.class.getResource("/icon/home(1).png")));
		
		lblIconOwner = new JLabel("");
		lblIconOwner.setBounds(40, 58, 16, 16);
		lblIconOwner.setIcon(new ImageIcon(CardRoomUser.class.getResource("/icon/check(1).png")));
		
		lblIconMoney = new JLabel("");
		lblIconMoney.setBounds(40, 92, 16, 16);
		lblIconMoney.setIcon(new ImageIcon(CardRoomUser.class.getResource("/icon/payment(1).png")));
		
		lblIconPeople = new JLabel("");
		lblIconPeople.setBounds(40, 126, 16, 16);
		lblIconPeople.setIcon(new ImageIcon(CardRoomUser.class.getResource("/icon/users(1).png")));
		setLayout(null);
		add(lblRoomNumber);
		add(ReadRoomNum);
		add(ReadOwner);
		add(ReadPrice);
		add(ReadMaxPeople);
		add(ReadPeople);
		add(btnViewRoom);
		add(btnViewContract);
		add(lblIconRoom);
		add(lblIconOwner);
		add(lblIconMoney);
		add(lblIconPeople);
		
	}
	
	
}
