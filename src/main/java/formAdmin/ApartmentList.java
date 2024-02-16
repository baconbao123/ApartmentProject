package formAdmin;

import java.awt.SystemColor;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Color;
import javax.swing.JTextPane;
import component.CardApartment;

public class ApartmentList extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblLableAvalabel;
	private JLabel lblRented;
	private JLabel lblNotYetPaid;
	private JLabel lblNewLabel;
	private JTextPane colorPaid;
	private JLabel lblPaid;
	private JTextPane colorRented;
	private JLabel lblRented_1;
	private JTextPane colorAvailable;
	private JLabel lblRented_2;
	private CardApartment cardApartment;

	/**
	 * Create the panel.
	 */
	public ApartmentList() {
		setBorder(null);
		setBounds(0, 0, 1050, 800);
		
		lblLableAvalabel = new JLabel("Available Room: " + 123);
		lblLableAvalabel.setFont(new Font("Arial", Font.PLAIN, 14));
		
		lblRented = new JLabel("Rented: " + 1);
		lblRented.setFont(new Font("Arial", Font.PLAIN, 14));
		
		lblNotYetPaid = new JLabel("Not yet paid: " + 0);
		lblNotYetPaid.setFont(new Font("Arial", Font.PLAIN, 14));
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(new Color(46, 204, 113));
		
		colorPaid = new JTextPane();
		colorPaid.setBackground(new Color(46, 204, 113));
		
		lblPaid = new JLabel("Paid");
		lblPaid.setFont(new Font("Arial", Font.PLAIN, 14));
		
		colorRented = new JTextPane();
		colorRented.setBackground(new Color(29, 158, 255));
		
		lblRented_1 = new JLabel("Rented");
		lblRented_1.setFont(new Font("Arial", Font.PLAIN, 14));
		
		colorAvailable = new JTextPane();
		colorAvailable.setBackground(new Color(255, 255, 255));
		
		lblRented_2 = new JLabel("Available Room");
		lblRented_2.setFont(new Font("Arial", Font.PLAIN, 14));
		
		cardApartment = new CardApartment();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(33)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 1017, Short.MAX_VALUE)
							.addComponent(lblNewLabel))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(8)
									.addComponent(lblLableAvalabel, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblRented, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblNotYetPaid, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 272, Short.MAX_VALUE)
									.addComponent(colorPaid, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblPaid, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(colorRented, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblRented_1, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(colorAvailable, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblRented_2, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
								.addComponent(cardApartment, GroupLayout.DEFAULT_SIZE, 986, Short.MAX_VALUE))
							.addGap(31))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(39)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(colorPaid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
							.addComponent(lblNewLabel)
							.addGap(28))
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addComponent(lblRented_2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addComponent(colorAvailable, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblRented_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lblPaid, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(colorRented, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNotYetPaid, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblRented, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblLableAvalabel)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cardApartment, GroupLayout.PREFERRED_SIZE, 671, GroupLayout.PREFERRED_SIZE)
					.addGap(25))
		);
		setLayout(groupLayout);
		initData();
	}

	private void initData() {
		initCardRoomRented();
		
	}

	private void initCardRoomRented() {
		
		
	}
}
