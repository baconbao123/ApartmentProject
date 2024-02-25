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
import dao.ApartmentDao;


public class Dashboard extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblDashboard;
	private CardApartment cardApartment;
	private CardSelect cardSelect;

	/**
	 * Create the panel.
	 */
	public Dashboard() {
		setBounds(0, 0, 1100, 800);
		var contract = new ContractList();
		
		initComponent();
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private void initComponent() {
		
		lblDashboard = new JLabel("Dashboard");
		lblDashboard.setFont(new Font("Arial", Font.BOLD, 20));
		
		cardApartment = new CardApartment();
		
		cardSelect = new CardSelect();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblDashboard, GroupLayout.PREFERRED_SIZE, 569, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(cardSelect, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1062, Short.MAX_VALUE)
								.addComponent(cardApartment, GroupLayout.DEFAULT_SIZE, 1512, Short.MAX_VALUE))
							.addGap(19))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDashboard, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addComponent(cardSelect, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(cardApartment, GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
					.addGap(19))
		);
		setLayout(groupLayout);
		
	}
}
