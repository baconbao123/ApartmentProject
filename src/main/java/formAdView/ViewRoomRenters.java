package formAdView;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import java.awt.BorderLayout;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class ViewRoomRenters extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField txtApID;
	private JLabel lblNewLabel_2;
	private JTextField txtStatus;
	private JLabel lblNewLabel_3;
	private JTextField txtFee;
	private JLabel lblNewLabel_4;
	private JTextField txtApType;
	private JLabel lblNewLabel_5;
	private JTextField txtNumRenters;
	private JLabel lblRentersInfo;
	private JTable table;
	private JScrollPane scrollPanRenter;
	private JPanel panel;
	private JTable tableRenter;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewRoomRenters frame = new ViewRoomRenters();
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
	public ViewRoomRenters() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1056, 703);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		lblNewLabel = new JLabel("APARTMENT INFORMATION");
		lblNewLabel.setBounds(10, 13, 217, 19);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel.setForeground(Color.BLACK);
		
		lblNewLabel_1 = new JLabel("Apartment ID");
		lblNewLabel_1.setBounds(11, 48, 71, 18);
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 12));
		
		txtApID = new JTextField();
		txtApID.setBounds(92, 48, 71, 18);
		txtApID.setEnabled(false);
		txtApID.setEditable(false);
		txtApID.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY));
		txtApID.setFont(new Font("Arial", Font.PLAIN, 12));
		txtApID.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Status");
		lblNewLabel_2.setBounds(180, 48, 35, 18);
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 12));
		
		txtStatus = new JTextField();
		txtStatus.setBounds(219, 48, 114, 18);
		txtStatus.setFont(new Font("Arial", Font.PLAIN, 12));
		txtStatus.setEnabled(false);
		txtStatus.setEditable(false);
		txtStatus.setColumns(10);
		txtStatus.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY));
		
		lblNewLabel_3 = new JLabel("Rental Fee");
		lblNewLabel_3.setBounds(347, 50, 60, 14);
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 12));
		
		txtFee = new JTextField();
		txtFee.setBounds(412, 48, 114, 18);
		txtFee.setFont(new Font("Arial", Font.PLAIN, 12));
		txtFee.setEnabled(false);
		txtFee.setEditable(false);
		txtFee.setColumns(10);
		txtFee.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY));
		
		lblNewLabel_4 = new JLabel("Apartment Type ");
		lblNewLabel_4.setBounds(545, 48, 88, 18);
		lblNewLabel_4.setFont(new Font("Arial", Font.PLAIN, 12));
		
		txtApType = new JTextField();
		txtApType.setBounds(638, 46, 114, 20);
		txtApType.setFont(new Font("Arial", Font.PLAIN, 12));
		txtApType.setEnabled(false);
		txtApType.setEditable(false);
		txtApType.setColumns(10);
		txtApType.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY));
		
		lblNewLabel_5 = new JLabel("Maximum Number Of Renters");
		lblNewLabel_5.setBounds(774, 49, 163, 14);
		lblNewLabel_5.setFont(new Font("Arial", Font.PLAIN, 12));
		
		txtNumRenters = new JTextField();
		txtNumRenters.setBounds(952, 46, 71, 20);
		txtNumRenters.setFont(new Font("Arial", Font.PLAIN, 12));
		txtNumRenters.setEnabled(false);
		txtNumRenters.setEditable(false);
		txtNumRenters.setColumns(10);
		txtNumRenters.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY));
		
		lblRentersInfo = new JLabel("Renters Information");
		lblRentersInfo.setBounds(11, 92, 151, 19);
		lblRentersInfo.setForeground(Color.BLACK);
		lblRentersInfo.setFont(new Font("Arial", Font.BOLD, 16));
		contentPane.setLayout(null);
		contentPane.add(lblNewLabel_1);
		contentPane.add(txtApID);
		contentPane.add(lblNewLabel_2);
		contentPane.add(txtStatus);
		contentPane.add(lblNewLabel_3);
		contentPane.add(txtFee);
		contentPane.add(lblNewLabel_4);
		contentPane.add(txtApType);
		contentPane.add(lblNewLabel_5);
		contentPane.add(txtNumRenters);
		contentPane.add(lblNewLabel);
		contentPane.add(lblRentersInfo);
		
		scrollPanRenter = new JScrollPane();
		scrollPanRenter.setBackground(Color.WHITE);
		scrollPanRenter.setBorder(null);
		scrollPanRenter.setBounds(11, 122, 1012, 517);
		contentPane.add(scrollPanRenter);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		scrollPanRenter.setViewportView(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		tableRenter = new JTable();
		panel.add(tableRenter, BorderLayout.CENTER);
		
		
		
		
	}
}
