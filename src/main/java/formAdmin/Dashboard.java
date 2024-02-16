package formAdmin;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import component.CardApartment;
import javax.swing.LayoutStyle.ComponentPlacement;


public class Dashboard extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblDashboard;
	private CardApartment cardApartment;

	/**
	 * Create the panel.
	 */
	public Dashboard() {
		setBounds(0, 0, 1100, 800);
		
		
		
		
		
		
		
		initComponent();
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private void initComponent() {
		
		lblDashboard = new JLabel("Dashboard");
		lblDashboard.setFont(new Font("Tahoma", Font.BOLD, 17));
		
		cardApartment = new CardApartment();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(484)
					.addComponent(lblDashboard, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addComponent(cardApartment, GroupLayout.DEFAULT_SIZE, 1062, Short.MAX_VALUE)
					.addGap(19))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addComponent(lblDashboard, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 163, Short.MAX_VALUE)
					.addComponent(cardApartment, GroupLayout.PREFERRED_SIZE, 579, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		setLayout(groupLayout);
		
	}
}
