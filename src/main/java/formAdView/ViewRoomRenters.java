package formAdView;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Component;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import java.awt.BorderLayout;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dao.UserDao;
import entity.Users;
import table.TableHeader;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.CardLayout;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;

public class ViewRoomRenters extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField txtApNum;
	private JLabel lblNewLabel_4;
	private JTextField txtApType;
	private JLabel lblNewLabel_5;
	private JTextField txtNumRenters;
	private JLabel lblRentersInfo;
	private JScrollPane scrollPane;
	private JTable tableRoomate;
	


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
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, SystemColor.menu, SystemColor.menu, SystemColor.menu, SystemColor.menu));
		setContentPane(contentPane);
		
		
		
		txtApNum = new JTextField();
		txtApType = new JTextField();
		txtNumRenters = new JTextField();
		scrollPane = new JScrollPane();
		scrollPane.setBackground(SystemColor.menu);
		
		tableRoomate = new JTable();
		tableRoomate.setBorder(null);
		tableRoomate.setBackground(Color.WHITE);
		tableRoomate.setRowSelectionAllowed(false);
		tableRoomate.setColumnSelectionAllowed(false);
		tableRoomate.setShowHorizontalLines(true);
		tableRoomate.setGridColor(new Color(230, 230, 230));
		tableRoomate.setRowHeight(40);
		tableRoomate.getTableHeader().setReorderingAllowed(false);
		tableRoomate.getTableHeader().setVisible(true);
		tableRoomate.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				TableHeader header = new TableHeader(value + "");
				header.setHorizontalAlignment(JLabel.CENTER);
				return header;
			}
		});
		
		
		scrollPane.setViewportView(tableRoomate);
		
		initComponent();
		
	}
	
	
	
	public void setApartNum(String aprtNum) {
		txtApNum.setText(aprtNum);
	}
	
	public void setApartType(String type) {
		txtApType.setText(type);
	}
	
	public void setMaxPeople(String peopleNum) {
		txtNumRenters.setText(peopleNum);
	}
	
	public void setRoomateInfor(String roomateID) {
//		System.out.println(roomateID);
		String[] roomateIDSplit = roomateID.split(";"); 
		var dao = new UserDao();
		List<Integer> idList = new ArrayList<>();
		
		for (String roommateID : roomateIDSplit) {
			try {
	            int id = Integer.parseInt(roommateID.trim());
	            idList.add(id);
	        } catch (NumberFormatException e) {
	            System.err.println("Invalid ID: " + roommateID);
	        }
		    
		}
		
		List<Users> rommates = dao.selRoomateInFor(idList);
		
		var model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				switch(column) {
					default: return false;
				}
			}
		};
		model.addColumn("Full name");
		model.addColumn("Phone");
		model.addColumn("Date Of Birth");
		model.addColumn("National ID Card");
		

		
		for (Users roommate : rommates) {
			model.addRow(new Object[] {
					roommate.getName(),
					roommate.getPhone(),
					roommate.getDob(),
					roommate.getNic()
			});
	    }
		tableRoomate.setModel(model);
		
		tableRoomate.getColumnModel().getColumn(0).setCellRenderer(new CenterRenderer());
		tableRoomate.getColumnModel().getColumn(1).setCellRenderer(new CenterRenderer());
		tableRoomate.getColumnModel().getColumn(2).setCellRenderer(new CenterRenderer());
		tableRoomate.getColumnModel().getColumn(3).setCellRenderer(new CenterRenderer());
		
	}
	
	class CenterRenderer extends DefaultTableCellRenderer {
		public CenterRenderer() {
			setHorizontalAlignment(SwingConstants.CENTER);
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			return this;
		};
	}
	
	
	
	
	
	
	
	

	private void initComponent() {
		
		lblNewLabel = new JLabel("APARTMENT INFORMATION");
		lblNewLabel.setBounds(10, 13, 217, 19);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel.setForeground(Color.BLACK);
		
		lblNewLabel_1 = new JLabel("Apartment Number");
		lblNewLabel_1.setBounds(11, 48, 113, 18);
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 12));
		
		txtApNum.setBounds(134, 48, 71, 18);
		txtApNum.setEnabled(false);
		txtApNum.setEditable(false);
		txtApNum.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY));
		txtApNum.setFont(new Font("Arial", Font.BOLD, 12));
		txtApNum.setColumns(10);
		
		txtNumRenters.setBounds(633, 48, 71, 18);
		txtNumRenters.setFont(new Font("Arial", Font.BOLD, 12));
		txtNumRenters.setEnabled(false);
		txtNumRenters.setEditable(false);
		txtNumRenters.setColumns(10);
		txtNumRenters.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY));
		
		txtApType.setBounds(320, 48, 114, 18);
		txtApType.setFont(new Font("Arial", Font.BOLD, 12));
		txtApType.setEnabled(false);
		txtApType.setEditable(false);
		txtApType.setColumns(10);
		txtApType.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY));
		
		lblRentersInfo = new JLabel("Renters Information");
		lblRentersInfo.setBounds(11, 92, 151, 19);
		lblRentersInfo.setForeground(Color.BLACK);
		lblRentersInfo.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_5 = new JLabel("Maximum Number Of Renters");
		lblNewLabel_5.setBounds(455, 48, 163, 18);
		lblNewLabel_5.setFont(new Font("Arial", Font.PLAIN, 12));
		
		lblNewLabel_4 = new JLabel("Apartment Type ");
		lblNewLabel_4.setBounds(227, 48, 88, 18);
		lblNewLabel_4.setFont(new Font("Arial", Font.PLAIN, 12));
		
		contentPane.setLayout(null);
		contentPane.add(lblNewLabel_1);
		contentPane.add(txtApNum);
		contentPane.add(lblNewLabel_4);
		contentPane.add(txtApType);
		contentPane.add(lblNewLabel_5);
		contentPane.add(txtNumRenters);
		contentPane.add(lblNewLabel);
		contentPane.add(lblRentersInfo);
		
		
		scrollPane.setBorder(new LineBorder(SystemColor.menu));
		scrollPane.setBounds(11, 122, 1019, 518);
		contentPane.add(scrollPane);
		
		

		
	}
}
