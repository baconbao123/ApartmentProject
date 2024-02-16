package formUpdateAd;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;

import org.w3c.dom.Document;

import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

public class FrameUpApart extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblUpdateApartment;
	private JLabel lblFloor;
	private JLabel ReadNumFloor;
	private JLabel lblConvenient;
	private JPanel panelConvenient;
	private JLabel lblUtilities;
	private JPanel panelUtilities;
	private JLabel lblMaximumOccupancy;
	private JTextField txtMaxPeople;
	private JLabel lblType;
	private JTextField txtType;
	private JButton btnSave;
	private JLabel lblegM;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameUpApart frame = new FrameUpApart();
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
	public FrameUpApart() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 530);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		lblUpdateApartment = new JLabel("Update Apartment 101");
		lblUpdateApartment.setForeground(Color.GRAY);
		lblUpdateApartment.setFont(new Font("Arial", Font.BOLD, 15));
		
		lblFloor = new JLabel("Floor");
		lblFloor.setForeground(Color.GRAY);
		lblFloor.setFont(new Font("Arial", Font.BOLD, 14));
		
		ReadNumFloor = new JLabel("1");
		ReadNumFloor.setFont(new Font("Arial", Font.PLAIN, 14));
		
		lblConvenient = new JLabel("Convenient");
		lblConvenient.setForeground(Color.GRAY);
		lblConvenient.setFont(new Font("Arial", Font.BOLD, 14));
		
		panelConvenient = new JPanel();
		
		lblUtilities = new JLabel("Utilities");
		lblUtilities.setForeground(Color.GRAY);
		lblUtilities.setFont(new Font("Arial", Font.BOLD, 14));
		
		panelUtilities = new JPanel();
		
		lblMaximumOccupancy = new JLabel("Maximum occupancy");
		lblMaximumOccupancy.setForeground(Color.GRAY);
		lblMaximumOccupancy.setFont(new Font("Arial", Font.BOLD, 14));
		
		
		
		txtMaxPeople = new JTextField();
		txtMaxPeople.setColumns(10);
		txtMaxPeople.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY));
		
		
		lblType = new JLabel("Type");
		lblType.setForeground(Color.GRAY);
		lblType.setFont(new Font("Arial", Font.BOLD, 14));
		
		txtType = new JTextField();
		txtType.setColumns(10);
		txtType.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY));
		
		btnSave = new JButton("Save");
		btnSave.setForeground(Color.WHITE);
		btnSave.setFont(new Font("Arial", Font.BOLD, 12));
		btnSave.setFocusable(false);
		btnSave.setBorder(null);
		btnSave.setBackground(new Color(38, 185, 154));
		
		lblegM = new JLabel("(e.g: 15*30 m2)");
		lblegM.setForeground(Color.GRAY);
		lblegM.setFont(new Font("Arial", Font.PLAIN, 12));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblegM, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblFloor, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
							.addGap(122)
							.addComponent(ReadNumFloor, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblConvenient, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
							.addGap(63)
							.addComponent(panelConvenient, GroupLayout.PREFERRED_SIZE, 292, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblUtilities, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
							.addGap(63)
							.addComponent(panelUtilities, GroupLayout.PREFERRED_SIZE, 292, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(1)
							.addComponent(lblMaximumOccupancy, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
							.addGap(16)
							.addComponent(txtMaxPeople, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblType, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
							.addGap(17)
							.addComponent(txtType, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(193)
							.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGap(160)
					.addComponent(lblUpdateApartment, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(149))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblUpdateApartment, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblFloor, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(ReadNumFloor, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(33)
							.addComponent(lblConvenient, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
						.addComponent(panelConvenient, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(33)
							.addComponent(lblUtilities, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
						.addComponent(panelUtilities, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE))
					.addGap(32)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblMaximumOccupancy, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtMaxPeople, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblType, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtType, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblegM)
					.addGap(38)
					.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(25, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}

}
