package formAdView;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.ApartmentDao;
import entity.Apartment;
import formEnterAd.FrameAddContract;
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
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblType;
	private JLabel ReadApartType;

 
	private JLabel lblReadApNum;
	private JLabel lblID;
	private JLabel ReadID;
	private JScrollPane scrollPane;
	private JPanel panelCon;
	private JLabel ReadNumOccupants_1;
	private JLabel ReadMaxPeople;
	private JScrollPane scrollUlti;
	private JPanel panelUtilities;
	private JLabel lblM;


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
		setTitle("View Apartment Information");
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 565, 455);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		lblLabel = new JLabel("Information Of Apartment");
		lblLabel.setBounds(170, 11, 182, 24);

		lblFloor = new JLabel("Floor");
		lblFloor.setBounds(10, 88, 40, 17);
		ReadNumFloor = new JLabel("1");
		ReadNumFloor.setBounds(154, 88, 26, 17);

		lblReadApNum = new JLabel("101");
		lblReadApNum.setBounds(356, 11, 26, 24);

		
		// number people in room
		ReadNumPeople = new JLabel("0");
		ReadNumPeople.setBounds(154, 338, 8, 17);
		
		scrollUlti = new JScrollPane();
		scrollUlti.setBounds(153, 238, 367, 82);
		panelUtilities = new JPanel();
		panelUtilities.setLayout(new GridLayout(0,3));

		scrollPane = new JScrollPane();
		scrollPane.setBounds(153, 138, 367, 82);
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
		lblConvenient.setBounds(10, 162, 99, 17);
		lblConvenient.setFont(new Font("Arial", Font.BOLD, 14));

		lblUtilities = new JLabel("Utilities");
		lblUtilities.setBounds(10, 267, 99, 17);
		lblUtilities.setFont(new Font("Arial", Font.BOLD, 14));
		ReadNumPeople.setFont(new Font("Arial", Font.PLAIN, 14));

		lblOccupants = new JLabel("Occupants count");
		lblOccupants.setBounds(10, 338, 126, 17);
		lblOccupants.setFont(new Font("Arial", Font.BOLD, 14));

		lblType = new JLabel("Type");
		lblType.setBounds(10, 366, 117, 19);
		lblType.setFont(new Font("Arial", Font.BOLD, 14));

		ReadApartType = new JLabel("30*15");
		ReadApartType.setBounds(154, 366, 46, 19);
		ReadApartType.setFont(new Font("Arial", Font.PLAIN, 14));

		lblReadApNum.setFont(new Font("Arial", Font.BOLD, 15));

		lblID = new JLabel("ID");
		lblID.setBounds(10, 55, 40, 17);
		lblID.setFont(new Font("Arial", Font.BOLD, 14));

		ReadID = new JLabel("1");
		ReadID.setBounds(154, 55, 26, 17);
		ReadID.setFont(new Font("Arial", Font.PLAIN, 14));

		scrollPane.setBorder(null);

		scrollPane.setViewportView(panelCon);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		ReadNumOccupants_1 = new JLabel("/");
		ReadNumOccupants_1.setBounds(168, 338, 8, 17);
		ReadNumOccupants_1.setFont(new Font("Arial", Font.PLAIN, 14));
		
		ReadMaxPeople = new JLabel("0");
		ReadMaxPeople.setBounds(182, 338, 18, 17);
		ReadMaxPeople.setFont(new Font("Arial", Font.PLAIN, 14));
		
		
		scrollUlti.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollUlti.setBorder(null);
		
		lblM = new JLabel("/  m2");
		lblM.setBounds(200, 366, 41, 19);
		lblM.setFont(new Font("Arial", Font.PLAIN, 14));
		
		
		scrollUlti.setViewportView(panelUtilities);
		contentPane.setLayout(null);
		contentPane.add(lblM);
		contentPane.add(lblLabel);
		contentPane.add(lblReadApNum);
		contentPane.add(lblID);
		contentPane.add(ReadID);
		contentPane.add(lblFloor);
		contentPane.add(ReadNumFloor);
		contentPane.add(lblConvenient);
		contentPane.add(lblUtilities);
		contentPane.add(scrollUlti);
		contentPane.add(scrollPane);
		contentPane.add(lblType);
		contentPane.add(ReadApartType);
		contentPane.add(lblOccupants);
		contentPane.add(ReadNumPeople);
		contentPane.add(ReadNumOccupants_1);
		contentPane.add(ReadMaxPeople);
	}

	

	

	

	
}
