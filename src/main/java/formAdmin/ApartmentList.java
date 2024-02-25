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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ApartmentList extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblNewLabel;
	private JTextPane colorRented;
	private JLabel lblRented_1;
	private JTextPane colorAvailable;
	private JLabel lblRented_2;
	private CardApartment cardApartment;
	private ApartmentList apList;

	/**
	 * Create the panel.
	 */
	public ApartmentList() {
		setBorder(null);
		setBounds(0, 0, 1050, 800);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(new Color(46, 204, 113));
		
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
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(771, Short.MAX_VALUE)
					.addComponent(colorRented, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblRented_1, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(colorAvailable, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(lblRented_2, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
					.addGap(31))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(1050)
					.addComponent(lblNewLabel))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(33)
					.addComponent(cardApartment, GroupLayout.PREFERRED_SIZE, 986, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(39)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblRented_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addComponent(colorAvailable, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblRented_2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
							.addGap(6)
							.addComponent(lblNewLabel))
						.addComponent(colorRented, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(34)
					.addComponent(cardApartment, GroupLayout.PREFERRED_SIZE, 682, GroupLayout.PREFERRED_SIZE))
		);
		setLayout(groupLayout);
		
	}
}
