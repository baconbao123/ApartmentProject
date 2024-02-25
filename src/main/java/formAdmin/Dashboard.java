package formAdmin;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import component.CardApartment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLayeredPane;
import java.awt.GridLayout;
import component.CardSelect;


public class Dashboard extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblDashboard;
	private CardApartment cardApartment;
	private JLayeredPane layeredPane;
	private CardSelect cardRenters;
	private CardSelect cardApart;

	/**
	 * Create the panel.
	 */
	public Dashboard() {
		setBounds(0, 0, 1100, 800);
		
		
		
		
		 
		
		
		initComponent();
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private void initComponent() {
		
		lblDashboard = new JLabel("Dashboard");
		lblDashboard.setFont(new Font("Arial", Font.BOLD, 20));
		
		cardApartment = new CardApartment();
		
		layeredPane = new JLayeredPane();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblDashboard, GroupLayout.PREFERRED_SIZE, 569, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(cardApartment, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1062, Short.MAX_VALUE)
								.addComponent(layeredPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1062, Short.MAX_VALUE))
							.addGap(19))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addComponent(lblDashboard, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(layeredPane, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(cardApartment, GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
					.addGap(19))
		);
		layeredPane.setLayout(new GridLayout(1, 0, 70, 0));
		
		cardRenters = new CardSelect();
		layeredPane.add(cardRenters);
		
		cardApart = new CardSelect();
		layeredPane.add(cardApart);
		setLayout(groupLayout);
		
	}
}
