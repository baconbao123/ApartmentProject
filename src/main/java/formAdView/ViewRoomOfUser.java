package formAdView;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dao.UserDao;
import entity.Users;
import scrollbar.ScrollBarCustom;
import table.TableHeader;

import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.JTable;

public class ViewRoomOfUser extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblApNum;
	private JLabel lblApType;
	private JLabel lblMax;
	private JLabel lblAp;
	private JLabel lblRentersInfo;
	private JLabel lblConvenient;
	private JScrollPane scrollCon;
	private JPanel panelCon;
	private JLabel lblUtilities;
	private JScrollPane scrollUti;
	private JPanel panelUti;
	private JScrollPane scrollTable;
	private JLabel ReadApNum;
	private JLabel ReadApType;
	private JLabel ReadApMaxPeo;
	private JTable tableInfo;
	private JLabel lblOwnerName;
	private JLabel ReadOwnerName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewRoomOfUser frame = new ViewRoomOfUser();
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
	public ViewRoomOfUser() {
		setBackground(SystemColor.menu);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 904, 673);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		ReadOwnerName = new JLabel("ReadOwnerName");
		setContentPane(contentPane);

		scrollTable = new JScrollPane();
		scrollTable.setBounds(15, 286, 856, 315);
		
		scrollUti = new JScrollPane();
		scrollUti.setBounds(547, 90, 4, 88);
		panelUti = new JPanel();
		panelUti.setBorder(null);
		panelUti.setLayout(new GridLayout(0, 2));
		scrollUti.setViewportView(panelUti);
		scrollUti.setVerticalScrollBar(new ScrollBarCustom());
		
		scrollCon = new JScrollPane();
		scrollCon.setBounds(97, 90, 315, 88);
		panelCon = new JPanel();
		panelCon.setBorder(null);
		panelCon.setLayout(new GridLayout(0, 2));
		scrollCon.setViewportView(panelCon);
		scrollCon.setVerticalScrollBar(new ScrollBarCustom());
		
		tableInfo = new JTable();
		tableInfo.setBorder(null);
		tableInfo.setBackground(Color.WHITE);
		tableInfo.setRowSelectionAllowed(false);
		tableInfo.setColumnSelectionAllowed(false);
		tableInfo.setShowHorizontalLines(true);
		tableInfo.setGridColor(new Color(230, 230, 230));
		tableInfo.setRowHeight(40);
		tableInfo.getTableHeader().setReorderingAllowed(false);
		tableInfo.getTableHeader().setVisible(true);
		tableInfo.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				TableHeader header = new TableHeader(value + "");
				header.setHorizontalAlignment(JLabel.CENTER);
				return header;
			}
		});
		scrollTable.setViewportView(tableInfo);
		
		initComponent();
	}

	public void setApNum(String apNum) {
		ReadApNum.setText(apNum);
	}
	
	public void setType(String type) {
		ReadApType.setText(type);
	}
	
	public void setMaxPeop(String num) {
		ReadApMaxPeo.setText(num);
	}
	
	public void displayCon(String cons) {
		panelCon.removeAll();
		
		if(cons!=null) {
			String[] convenStr = cons.split(";");
			
			for(String con : convenStr) {
				var chkb =  new JCheckBox(con);
				chkb.setSelected(true);
				chkb.setEnabled(false);
				panelCon.add(chkb);
			}
		}
		
		panelCon.revalidate();
		panelCon.repaint();
	}
	
	public void displayUti(String uti) {
		panelUti.removeAll();
		
		if(uti!=null) {
			String[] utiStr = uti.split(";");
			
			for(String utili: utiStr) {
				var ckb = new JCheckBox(utili);
				ckb.setSelected(true);
				ckb.setEnabled(false);
				panelUti.add(ckb);
			}
		}
		
		panelUti.revalidate();
		panelUti.repaint();
	}
	
	public void loadRoomateInfor(String roomate) {
		String[] roomateIDSplit = roomate.split(";"); 
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
		tableInfo.setModel(model);
		
		tableInfo.getColumnModel().getColumn(0).setCellRenderer(new CenterRenderer());
		tableInfo.getColumnModel().getColumn(1).setCellRenderer(new CenterRenderer());
		tableInfo.getColumnModel().getColumn(2).setCellRenderer(new CenterRenderer());
		tableInfo.getColumnModel().getColumn(3).setCellRenderer(new CenterRenderer());
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
	
	public void setOwnerNameCard(String ownerName) {
		ReadOwnerName.setText(ownerName);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private void initComponent() {
		
		lblApNum = new JLabel("Apartment Number");
		lblApNum.setBounds(15, 46, 113, 18);
		lblApNum.setFont(new Font("Arial", Font.PLAIN, 12));
		
		lblApType = new JLabel("Apartment Type ");
		lblApType.setBounds(231, 46, 88, 18);
		lblApType.setFont(new Font("Arial", Font.PLAIN, 12));
		
		lblMax = new JLabel("Maximum Number Of Renters");
		lblMax.setBounds(459, 46, 163, 18);
		lblMax.setFont(new Font("Arial", Font.PLAIN, 12));
		
		lblAp = new JLabel("APARTMENT INFORMATION");
		lblAp.setBounds(15, 16, 217, 19);
		lblAp.setForeground(Color.BLACK);
		lblAp.setFont(new Font("Arial", Font.BOLD, 16));
		
		lblRentersInfo = new JLabel("Renters Information");
		lblRentersInfo.setBounds(15, 256, 151, 19);
		lblRentersInfo.setForeground(Color.BLACK);
		lblRentersInfo.setFont(new Font("Arial", Font.BOLD, 16));
		
		lblConvenient = new JLabel("Convenient");
		lblConvenient.setBounds(15, 125, 78, 18);
		lblConvenient.setFont(new Font("Arial", Font.PLAIN, 12));
		
		
		scrollCon.setBorder(new BevelBorder(BevelBorder.LOWERED, SystemColor.menu, SystemColor.menu, SystemColor.menu, SystemColor.menu));
		
		lblUtilities = new JLabel("Utilities");
		lblUtilities.setBounds(479, 123, 64, 18);
		lblUtilities.setFont(new Font("Arial", Font.PLAIN, 12));
		
		
		scrollUti.setBorder(new BevelBorder(BevelBorder.LOWERED, SystemColor.menu, new Color(240, 240, 240), new Color(240, 240, 240), new Color(240, 240, 240)));
		
		
		scrollTable.setBorder(new BevelBorder(BevelBorder.LOWERED, SystemColor.menu, SystemColor.menu, SystemColor.menu, SystemColor.menu));
		
		ReadApNum = new JLabel("1");
		ReadApNum.setBounds(134, 46, 55, 18);
		ReadApNum.setFont(new Font("Arial", Font.PLAIN, 12));
		
		ReadApType = new JLabel("1");
		ReadApType.setBounds(325, 46, 103, 18);
		ReadApType.setFont(new Font("Arial", Font.PLAIN, 12));
		
		ReadApMaxPeo = new JLabel("1");
		ReadApMaxPeo.setBounds(632, 46, 55, 18);
		ReadApMaxPeo.setFont(new Font("Arial", Font.PLAIN, 12));
		contentPane.setLayout(null);
		contentPane.add(scrollTable);
		contentPane.add(lblAp);
		contentPane.add(lblApNum);
		contentPane.add(ReadApNum);
		contentPane.add(lblApType);
		contentPane.add(ReadApType);
		contentPane.add(lblMax);
		contentPane.add(ReadApMaxPeo);
		contentPane.add(lblConvenient);
		contentPane.add(scrollCon);
		contentPane.add(lblUtilities);
		contentPane.add(scrollUti);
		contentPane.add(lblRentersInfo);
		
		lblOwnerName = new JLabel("Owner Name");
		lblOwnerName.setFont(new Font("Arial", Font.BOLD, 14));
		lblOwnerName.setBounds(15, 227, 113, 18);
		contentPane.add(lblOwnerName);
		
		
		ReadOwnerName.setFont(new Font("Arial", Font.PLAIN, 14));
		ReadOwnerName.setBounds(117, 227, 224, 18);
		contentPane.add(ReadOwnerName);
		
	}
	
	
	
	
}
