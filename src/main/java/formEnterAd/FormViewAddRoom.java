package formEnterAd;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class FormViewAddRoom extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblFloor;
	private JLabel lblConvenient;
	private JLabel lblUtilities;
	private JLabel lblMaximumOccupancy;
	private JLabel lblType;
	private JLabel ReadNumFloor;
	private JButton btnSave;
	private JLabel lblApartment;
	private JLabel ReadNumApart;
	private JScrollPane scrollPane;
	private JPanel panelConvenient;
	private JScrollPane scrollUlti;
	private JLabel ReadMaxPeople;
	private JLabel ReadType;
	private JPanel panelUltilities;
	private JLabel lblAdditional;
	private JTextField txtPeople;
	private JLabel lblStatus;
	private JRadioButton rdbRented;
	private JRadioButton rdbAvailable;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Create the panel.
	 */
	public FormViewAddRoom() {
		setBackground(Color.WHITE);
		
		ReadNumFloor = new JLabel("1");
		ReadNumFloor.setFont(new Font("Arial", Font.PLAIN, 14));
		
		
		
		ReadNumApart = new JLabel("101");
		
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		panelConvenient = new JPanel();
		
		
		scrollUlti = new JScrollPane();
		scrollUlti.setBorder(null);
		panelUltilities = new JPanel();
		
		ReadMaxPeople = new JLabel("1");
		
		ReadType = new JLabel("15*24 m2");
		
		txtPeople = new JTextField();
		
		
		rdbRented = new JRadioButton("Rented");
		buttonGroup.add(rdbRented);
		
		rdbAvailable = new JRadioButton("Available");
		buttonGroup.add(rdbAvailable);
		
		btnSave = new JButton("Save");

		initComponent();
	}

	public void setFloorApart(String floor) {
		ReadNumFloor.setText(floor);
	}
	
	public void setNumApart(String roomNumber) {
		ReadNumApart.setText(roomNumber);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private void initComponent() {
		
		
		
		
		
		
		
		
		
		
		
		lblType = new JLabel("Type");
		lblType.setForeground(Color.GRAY);
		lblType.setFont(new Font("Arial", Font.BOLD, 14));
		
		lblFloor = new JLabel("Floor");
		lblFloor.setForeground(Color.GRAY);
		lblFloor.setFont(new Font("Arial", Font.BOLD, 14));
		
		lblConvenient = new JLabel("Convenient");
		lblConvenient.setForeground(Color.GRAY);
		lblConvenient.setFont(new Font("Arial", Font.BOLD, 14));
		
		lblUtilities = new JLabel("Utilities");
		lblUtilities.setForeground(Color.GRAY);
		lblUtilities.setFont(new Font("Arial", Font.BOLD, 14));
		
		lblMaximumOccupancy = new JLabel("Maximum occupancy");
		lblMaximumOccupancy.setForeground(Color.GRAY);
		lblMaximumOccupancy.setFont(new Font("Arial", Font.BOLD, 14));
		
		btnSave.setForeground(Color.WHITE);
		btnSave.setFont(new Font("Arial", Font.BOLD, 12));
		btnSave.setFocusable(false);
		btnSave.setBorder(null);
		btnSave.setBackground(new Color(38, 185, 154));
		
		lblApartment = new JLabel("Apartment");
		lblApartment.setForeground(Color.GRAY);
		lblApartment.setFont(new Font("Arial", Font.BOLD, 15));
		
		ReadNumApart.setForeground(Color.GRAY);
		ReadNumApart.setFont(new Font("Arial", Font.BOLD, 15));
		
		ReadType.setFont(new Font("Arial", Font.PLAIN, 14));
		
		ReadMaxPeople.setFont(new Font("Arial", Font.PLAIN, 14));
		
		lblAdditional = new JLabel("Additional Renters");
		lblAdditional.setForeground(Color.GRAY);
		lblAdditional.setFont(new Font("Arial", Font.BOLD, 14));
		
		txtPeople.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY));
		txtPeople.setColumns(10);
		
		lblStatus = new JLabel("Status");
		lblStatus.setForeground(Color.GRAY);
		lblStatus.setFont(new Font("Arial", Font.BOLD, 14));
		
		rdbRented.setFont(new Font("Arial", Font.PLAIN, 12));
		rdbRented.setFocusable(false);
		rdbRented.setBackground(Color.WHITE);
		
		rdbAvailable.setFont(new Font("Arial", Font.PLAIN, 12));
		rdbAvailable.setFocusable(false);
		rdbAvailable.setBackground(Color.WHITE);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblMaximumOccupancy, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(ReadMaxPeople, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(168)
							.addComponent(lblApartment)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(ReadNumApart, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblFloor, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblConvenient, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblUtilities, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
							.addGap(63)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(ReadNumFloor, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
								.addComponent(scrollUlti)))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblAdditional, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(txtPeople, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblType, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(ReadType, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(21, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(204)
					.addComponent(btnSave, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
					.addGap(200))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblStatus, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(rdbRented)
					.addGap(18)
					.addComponent(rdbAvailable)
					.addContainerGap(148, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblApartment, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblFloor, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(ReadNumFloor, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)))
						.addComponent(ReadNumApart, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(39)
							.addComponent(lblConvenient, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(46)
							.addComponent(lblUtilities, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(scrollUlti, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMaximumOccupancy, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(ReadMaxPeople, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblType, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(ReadType, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAdditional, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtPeople, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(16)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStatus, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(rdbRented)
						.addComponent(rdbAvailable))
					.addGap(18)
					.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(28, Short.MAX_VALUE))
		);
		groupLayout.linkSize(SwingConstants.VERTICAL, new Component[] {scrollPane, scrollUlti});
		
		
		scrollUlti.setViewportView(panelUltilities);
		scrollPane.setViewportView(panelConvenient);
		setLayout(groupLayout);
		
	}
}
