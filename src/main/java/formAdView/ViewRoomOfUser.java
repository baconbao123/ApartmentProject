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

		setContentPane(contentPane);

		scrollTable = new JScrollPane();
		
		scrollUti = new JScrollPane();
		panelUti = new JPanel();
		panelUti.setBorder(null);
		panelUti.setLayout(new GridLayout(0, 2));
		scrollUti.setViewportView(panelUti);
		scrollUti.setVerticalScrollBar(new ScrollBarCustom());
		
		scrollCon = new JScrollPane();
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private void initComponent() {
		
		lblApNum = new JLabel("Apartment Number");
		lblApNum.setFont(new Font("Arial", Font.PLAIN, 12));
		
		lblApType = new JLabel("Apartment Type ");
		lblApType.setFont(new Font("Arial", Font.PLAIN, 12));
		
		lblMax = new JLabel("Maximum Number Of Renters");
		lblMax.setFont(new Font("Arial", Font.PLAIN, 12));
		
		lblAp = new JLabel("APARTMENT INFORMATION");
		lblAp.setForeground(Color.BLACK);
		lblAp.setFont(new Font("Arial", Font.BOLD, 16));
		
		lblRentersInfo = new JLabel("Renters Information");
		lblRentersInfo.setForeground(Color.BLACK);
		lblRentersInfo.setFont(new Font("Arial", Font.BOLD, 16));
		
		lblConvenient = new JLabel("Convenient");
		lblConvenient.setFont(new Font("Arial", Font.PLAIN, 12));
		
		
		scrollCon.setBorder(new BevelBorder(BevelBorder.LOWERED, SystemColor.menu, SystemColor.menu, SystemColor.menu, SystemColor.menu));
		
		lblUtilities = new JLabel("Utilities");
		lblUtilities.setFont(new Font("Arial", Font.PLAIN, 12));
		
		
		scrollUti.setBorder(new BevelBorder(BevelBorder.LOWERED, SystemColor.menu, new Color(240, 240, 240), new Color(240, 240, 240), new Color(240, 240, 240)));
		
		
		scrollTable.setBorder(new BevelBorder(BevelBorder.LOWERED, SystemColor.menu, SystemColor.menu, SystemColor.menu, SystemColor.menu));
		
		ReadApNum = new JLabel("1");
		ReadApNum.setFont(new Font("Arial", Font.PLAIN, 12));
		
		ReadApType = new JLabel("1");
		ReadApType.setFont(new Font("Arial", Font.PLAIN, 12));
		
		ReadApMaxPeo = new JLabel("1");
		ReadApMaxPeo.setFont(new Font("Arial", Font.PLAIN, 12));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblAp, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblApNum, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(ReadApNum, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
							.addGap(42)
							.addComponent(lblApType, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(ReadApType, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
							.addGap(31)
							.addComponent(lblMax, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(ReadApMaxPeo, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblConvenient, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollCon, GroupLayout.PREFERRED_SIZE, 315, GroupLayout.PREFERRED_SIZE)
							.addGap(67)
							.addComponent(lblUtilities, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollUti))
						.addComponent(lblRentersInfo, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollTable, GroupLayout.PREFERRED_SIZE, 856, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(12, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAp, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblApNum, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
									.addComponent(ReadApNum))
								.addComponent(lblApType, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMax, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
								.addComponent(ReadApType, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(59)
									.addComponent(lblUtilities, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(26)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(scrollUti, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_contentPane.createSequentialGroup()
													.addGap(35)
													.addComponent(lblConvenient, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
												.addComponent(scrollCon, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
											.addGap(26)
											.addComponent(lblRentersInfo, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))))))
						.addComponent(ReadApMaxPeo, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(scrollTable, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(28, Short.MAX_VALUE))
		);
		
		
		
		gl_contentPane.linkSize(SwingConstants.VERTICAL, new Component[] {lblApNum, ReadApNum});
		contentPane.setLayout(gl_contentPane);
		
	}
	
	
	
	
}
