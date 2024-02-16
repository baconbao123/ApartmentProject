package formAdView;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.ApartmentDao;
import entity.Apartment;
import formEnterAd.FrameAddContract;
import formEnterAd.FrameAddRoom;
import view.CardRoom;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import javax.swing.ScrollPaneConstants;

public class ViewRoomApartNoBtn extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblLabel;
	private JLabel lblFloor;
	private JLabel ReadNumFloor;
	private JLabel lblConvenient;
	private JLabel lblUtilities;
	private JLabel lblOccupants;
	private JLabel ReadNumPeople;
	private JLabel lblStatus;
	private JRadioButton rdbRented;
	private JRadioButton rdbAvailable;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblType;
	private JLabel ReadApartType;

	private FrameAddRoom frameRoom;
	private JLabel lblReadApNum;
	private JLabel lblID;
	private JLabel ReadID;
	private JScrollPane scrollPane;
	private JPanel panelCon;
	private JLabel ReadNumOccupants_1;
	private JLabel ReadMaxPeople;
	private JScrollPane scrollUlti;
	private JPanel panelUtilities;

	public FrameAddRoom getFrameRoom() {
		return frameRoom;
	}

	public void setFrameRoom(FrameAddRoom frameRoom) {
		this.frameRoom = frameRoom;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewRoomApartNoBtn frame = new ViewRoomApartNoBtn();
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
	public ViewRoomApartNoBtn() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 565, 504);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		lblLabel = new JLabel("Information Of Apartment");

		lblFloor = new JLabel("Floor");
		ReadNumFloor = new JLabel("1");

		lblReadApNum = new JLabel("101");

		
		// number people in room
		ReadNumPeople = new JLabel("0");

		rdbRented = new JRadioButton("Rented");
		buttonGroup.add(rdbRented);

		rdbAvailable = new JRadioButton("Available");
		buttonGroup.add(rdbAvailable);
		
		scrollUlti = new JScrollPane();
		panelUtilities = new JPanel();
		panelUtilities.setLayout(new GridLayout(0,3));

		scrollPane = new JScrollPane();
		panelCon = new JPanel();
		panelCon.setLayout(new GridLayout(0,3));
		

		initComponent();

	}
	//floor
	public void setFloorApart(String floor) {
		ReadNumFloor.setText(floor);
	}

	// id
	public void setIDCurrent(String idCurrent) {
		ReadID.setText(idCurrent);
	}

	
	public void showMaxPeople(int peopleMaximun) {
		ReadMaxPeople.setText(String.valueOf(peopleMaximun));
	}
		
	
	// show convenient with checkbox
	public void displayConvenient(String convenient) {
		panelCon.removeAll(); 

		if(convenient!=null) {
			String[] conveniences = convenient.split(";");
		    
	        
		    for(String convenience : conveniences) {
	            JCheckBox checkBox = new JCheckBox(convenience);
	            checkBox.setSelected(true);
	            checkBox.setEnabled(false);
	            panelCon.add(checkBox); // Add JCheckBox to the panel
	        }
		}
		
       
        
        // Revalidate and repaint the panel to update the UI
        panelCon.revalidate();
        panelCon.repaint();
	}
	
	public void displayUlti(String ultilites) {
		panelUtilities.removeAll(); 

		if(ultilites!=null) {
			 String[] ultilite = ultilites.split(";");
			 
			 for(String ul : ultilite) {
		            JCheckBox checkBox = new JCheckBox(ul);
		            checkBox.setSelected(true);
		            checkBox.setEnabled(false);
		            panelUtilities.add(checkBox); // Add JCheckBox to the panel
		        }
		}
       
	    
	        
	   
        
        // Revalidate and repaint the panel to update the UI
	    panelUtilities.revalidate();
	    panelUtilities.repaint();
	}


	// room Number
	public void setRoomNum(int currentRoomNum) {
		lblReadApNum.setText(String.valueOf(currentRoomNum));
	}

	
	
	public void showTypeRoom(String type) {
		ReadApartType.setText(type);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private void initComponent() {
		lblFloor.setFont(new Font("Arial", Font.BOLD, 14));
		lblLabel.setFont(new Font("Arial", Font.BOLD, 15));
		ReadNumFloor.setFont(new Font("Arial", Font.PLAIN, 14));

		lblConvenient = new JLabel("Convenient");
		lblConvenient.setFont(new Font("Arial", Font.BOLD, 14));

		lblUtilities = new JLabel("Utilities");
		lblUtilities.setFont(new Font("Arial", Font.BOLD, 14));
		ReadNumPeople.setFont(new Font("Arial", Font.PLAIN, 14));
		rdbRented.setFocusable(false);
		rdbAvailable.setFocusable(false);

		lblOccupants = new JLabel("Occupants count");
		lblOccupants.setFont(new Font("Arial", Font.BOLD, 14));

		lblStatus = new JLabel("Status");
		lblStatus.setFont(new Font("Arial", Font.BOLD, 14));

		rdbRented.setBackground(Color.WHITE);
		rdbRented.setBorder(null);
		rdbRented.setFont(new Font("Arial", Font.PLAIN, 14));

		rdbAvailable.setFont(new Font("Arial", Font.PLAIN, 14));
		rdbAvailable.setBorder(null);
		rdbAvailable.setBackground(Color.WHITE);

		lblType = new JLabel("Type");
		lblType.setFont(new Font("Arial", Font.BOLD, 14));

		ReadApartType = new JLabel("30*15 m2");
		ReadApartType.setFont(new Font("Arial", Font.PLAIN, 14));

		lblReadApNum.setFont(new Font("Arial", Font.BOLD, 15));

		lblID = new JLabel("ID");
		lblID.setFont(new Font("Arial", Font.BOLD, 14));

		ReadID = new JLabel("1");
		ReadID.setFont(new Font("Arial", Font.PLAIN, 14));

		scrollPane.setBorder(null);

		scrollPane.setViewportView(panelCon);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		ReadNumOccupants_1 = new JLabel("/");
		ReadNumOccupants_1.setFont(new Font("Arial", Font.PLAIN, 14));
		
		ReadMaxPeople = new JLabel("0");
		ReadMaxPeople.setFont(new Font("Arial", Font.PLAIN, 14));
		
		
		scrollUlti.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollUlti.setBorder(null);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(165)
							.addComponent(lblLabel, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(lblReadApNum, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(5)
							.addComponent(lblID, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
							.addGap(104)
							.addComponent(ReadID, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(5)
							.addComponent(lblFloor, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
							.addGap(104)
							.addComponent(ReadNumFloor, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(5)
							.addComponent(lblOccupants, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(ReadNumPeople)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(ReadNumOccupants_1, GroupLayout.PREFERRED_SIZE, 8, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(ReadMaxPeople, GroupLayout.PREFERRED_SIZE, 8, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(5)
							.addComponent(lblStatus, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
							.addGap(27)
							.addComponent(rdbRented)
							.addGap(18)
							.addComponent(rdbAvailable, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(5)
							.addComponent(lblType, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
							.addGap(27)
							.addComponent(ReadApartType, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(5)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblConvenient, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblUtilities, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
							.addGap(44)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollUlti, GroupLayout.PREFERRED_SIZE, 367, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 367, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(24, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblLabel, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblReadApNum, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(20)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblID)
						.addComponent(ReadID))
					.addGap(16)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblFloor)
						.addComponent(ReadNumFloor))
					.addGap(33)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(24)
							.addComponent(lblConvenient))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(47)
							.addComponent(lblUtilities))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(scrollUlti, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblOccupants)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(ReadNumPeople)
							.addComponent(ReadNumOccupants_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addComponent(ReadMaxPeople, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblStatus, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(rdbRented, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(rdbAvailable, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblType, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(ReadApartType, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addGap(96))
		);
		
		
		scrollUlti.setViewportView(panelUtilities);
		contentPane.setLayout(gl_contentPane);
	}

	

	

	

	
}
