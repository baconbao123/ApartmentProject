package formAdmin;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import dao.ContractDao;
import entity.Contract;
import event.EventLoadTable;
import event.EventTableRenterAction;
import formAdView.ViewRoomContract;
import formEnterAd.FrameAddContract;
import formUpdateAd.FrameUpContract;
import model.tableBillEditor;
import scrollbar.ScrollBarCustom;
import table.ImgContractRender;
import table.TableActionCellEditor;
import table.TableActionCellRender;
import table.TableHeader;
import view.AppStateManager;
import view.CardRoom;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.EventObject;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Dimension;

public class ContractList extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblContract;
	private JTextField txtByOwner;
	private JTextField txtByRoom;
	private JScrollPane scrollTable;
	private JTable tableConstract;
	private JButton btnPrevious;
	private JButton btnNext;
	private JButton btnAddContract;
	private Integer pageNumber = 1;
	private Integer rowOfPage = 15;
	private Integer totalOfRow = 0;
	private Double totalPage = 0.0;
	private JLabel lblPageNumber;
	private JComboBox cbbStatus;
	private JComboBox cbbData;
	private JButton btnReset;
	private CardRoom cardRoom = new CardRoom();
//	private EventLoadTable eventLoad;

	/** 
	 * Create the panel.
	 */
	public ContractList() {
		setBounds(0, 0, 1050, 800);
		scrollTable = new JScrollPane();
		scrollTable.setVerticalScrollBar(new ScrollBarCustom());
		txtByOwner = new JTextField();
		txtByRoom = new JTextField();
		
		lblPageNumber = new JLabel("");
		lblPageNumber.setText(pageNumber.toString());
		
		
		cbbStatus = new JComboBox();
		

		tableConstract = new JTable();
		tableConstract.setBackground(SystemColor.window);
		tableConstract.setBorder(null);
		tableConstract.setRowSelectionAllowed(true);
		tableConstract.setColumnSelectionAllowed(false);
		tableConstract.setShowHorizontalLines(true);
		tableConstract.setGridColor(new Color(230, 230, 230));
		tableConstract.setRowHeight(40);
		tableConstract.getTableHeader().setReorderingAllowed(false);
		tableConstract.getTableHeader().setVisible(true);
		tableConstract.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				TableHeader header = new TableHeader(value + "");
				header.setHorizontalAlignment(JLabel.CENTER);
				return header;
			}
		});
		scrollTable.setViewportView(tableConstract);

		initComponent();
		loadTableContract();
	}

	public void loadTableContract() {
		
		var model = new DefaultTableModel() {

			public java.lang.Class<?> getColumnClass(int columnIndex) {
				switch (columnIndex) {
				case 1:	return Integer.class;
				case 8: return Integer.class;
				default:
					return String.class;
				}
			};

			public boolean isCellEditable(int row, int column) {
				switch (column) {
				case 10:
					return true;
				default:
					return false;
				}
			};
		};

		model.addColumn("Id");
		model.addColumn("Apartment Number");
		model.addColumn("Owner Name");
		model.addColumn("Picture Images");
		model.addColumn("Status");
		model.addColumn("From Date");
		model.addColumn("To Date");
		model.addColumn("Roomates");
		model.addColumn("roomateID");
		model.addColumn("Owner Id");
		model.addColumn("Action");
		
		
 
		var dao = new ContractDao();
		Set<String> statusSet = new HashSet<>(); //cbb
		totalOfRow = dao.countContract();
		totalPage = Math.ceil(totalOfRow.doubleValue() / rowOfPage.doubleValue());
		
		dao.selContract(pageNumber, rowOfPage).stream().forEach(contract -> {
			String statusContract = contract.isStatus() ? "On" : "Off";
			statusSet.add(statusContract);//add to cbb
			String roomateNamesString = "";
		    if (contract.getRoomates() != null) {
		    	System.out.println(contract.getRoomates());
		        String[] roomateID = contract.getRoomates().split(";");
		        
		        List<String> roomateName = dao.getRoommateNamesByIds(roomateID);
		        roomateNamesString = String.join(", ", roomateName);
		    }

			model.addRow(new Object[] { contract.getId(), contract.getApartNum(), contract.getOwnerName(),
					contract.getImgContracs(), statusContract, contract.getFormDate(), contract.getToDate(),
					roomateNamesString, contract.getRoomates(), contract.getOwnerID()});

		});
		List<String> uniqueStatusList = new ArrayList<>(statusSet);//cbb
		DefaultComboBoxModel<String> statusComboBoxModel = new DefaultComboBoxModel<>(uniqueStatusList.toArray(new String[0]));//cbb
		statusComboBoxModel.addElement("Search by status");
		cbbStatus.setModel(statusComboBoxModel);//cbb
		cbbStatus.setSelectedItem("Search by status");
		tableConstract.setModel(model);

		tableConstract.getColumnModel().getColumn(0).setCellRenderer(new CenterRenderer());
		tableConstract.getColumnModel().getColumn(1).setCellRenderer(new CenterRenderer());
		tableConstract.getColumnModel().getColumn(3).setCellRenderer(new CenterRenderer());
		tableConstract.getColumnModel().getColumn(4).setCellRenderer(new CenterRenderer());
		tableConstract.getColumnModel().getColumn(5).setCellRenderer(new CenterRenderer());

		tableConstract.getColumnModel().getColumn(0).setPreferredWidth(5);
		tableConstract.getColumnModel().getColumn(1).setPreferredWidth(90);
		tableConstract.getColumnModel().getColumn(2).setPreferredWidth(50);
		tableConstract.getColumnModel().getColumn(3).setPreferredWidth(150);
		tableConstract.getColumnModel().getColumn(4).setPreferredWidth(25);
		tableConstract.getColumnModel().getColumn(5).setPreferredWidth(45);
		tableConstract.getColumnModel().getColumn(8).setPreferredWidth(0);
		tableConstract.getColumnModel().getColumn(8).setMinWidth(0);
		tableConstract.getColumnModel().getColumn(8).setMaxWidth(0);
		tableConstract.getColumnModel().getColumn(9).setPreferredWidth(0);
		tableConstract.getColumnModel().getColumn(9).setMinWidth(0);
		tableConstract.getColumnModel().getColumn(9).setMaxWidth(0);
		

		tableConstract.getColumnModel().getColumn(3).setCellRenderer(new ImgContractRender());

		EventTableRenterAction event = new EventTableRenterAction() {

			@Override
			public void view(int row) {
				Integer apartInt = (Integer) tableConstract.getValueAt(row, 1);
				String apartStr = String.valueOf(apartInt);
				String ownerName = (String) tableConstract.getValueAt(row, 2);
				String status = (String) tableConstract.getValueAt(row, 4);
				java.sql.Date dateFrom = (java.sql.Date) tableConstract.getValueAt(row, 5);
				java.sql.Date dateTo = (java.sql.Date) tableConstract.getValueAt(row, 6);
				String imgPaths = (String) tableConstract.getValueAt(row, 3);
				System.out.println(imgPaths);
				String rommateStrID = (String) tableConstract.getValueAt(row, 8);
				
				SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd");
				String dateFromStr = spf.format(dateFrom);
				String dateToStr = spf.format(dateTo);
				
				
				var viewContract = new ViewRoomContract(apartStr, ownerName, status, dateFromStr, dateToStr, imgPaths, rommateStrID);
				viewContract.setVisible(true);
				viewContract.setLocationRelativeTo(null);
				viewContract.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
				
			}

			@Override
			public void edit(int row) {
				Integer apartInt = (Integer) tableConstract.getValueAt(row, 1);
				String apartStr = String.valueOf(apartInt);
				
				Integer id = (Integer) tableConstract.getValueAt(row, 0);
				
				java.sql.Date fromDate = (java.sql.Date) tableConstract.getValueAt(row, 5);
				java.sql.Date toDate = (java.sql.Date) tableConstract.getValueAt(row, 6);
				
				String statusStr = (String) tableConstract.getValueAt(row, 4);
			    boolean status = statusStr.equals("On");
			    Integer ownerId= (Integer) tableConstract.getValueAt(row, 9);
				
				var upCon = new FrameUpContract(id, apartStr, fromDate, toDate, status, ownerId, new EventLoadTable() {
					
					@Override
					public void loadDataTable() {
						loadTableContract();
						
					}
				});
				
				upCon.setVisible(true);
				upCon.setLocationRelativeTo(null);
				upCon.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		};

		tableConstract.getColumnModel().getColumn(10).setCellRenderer(new TableActionCellRender());
		tableConstract.getColumnModel().getColumn(10).setCellEditor(new TableActionCellEditor(event));
		
		
		var daoUp = new ContractDao();
		
		for(int i=0; i<model.getRowCount();i++) {
			Date toDate = (Date) model.getValueAt(i, 6);
			String statusString = (String) model.getValueAt(i, 4);
			
			
			Boolean status = "On".equals(statusString);
			
					
			if(toDate!=null && toDate.before(new Date())&&status) {
//				model.setValueAt("Off", i, 4);
				String numRoomStr =  String.valueOf(model.getValueAt(i, 1));
				
				int conId = (int) tableConstract.getValueAt(i, 0);
				Integer roomInt = Integer.parseInt(numRoomStr);
				
				var contract = new Contract();
				contract.setId(conId);
				contract.setStatus(false);
				
				daoUp.updateConStatus(contract);
				
				if(cardRoom!=null) {
					CardLayout layout = (CardLayout) cardRoom.CardButton.getLayout();
					layout.show(cardRoom.CardButton, "available");
					cardRoom.setBackground(Color.WHITE);
					cardRoom.repaint();
					
					AppStateManager.saveAppState(roomInt, Color.WHITE, "available");
					
				} 
			}
			
		
		}

		
		
		
		
		
		filterData();
		
		

	}
	
	public void endContract() {
		var daoContract = new ContractDao();
//		daoEndContract.endContract();
		
	}

	protected void btnAddContractActionPerformed(ActionEvent e) {
		CardRoom cardRoom = new CardRoom();
		var form = new FrameAddContract(cardRoom, new EventLoadTable() {
			
			@Override
			public void loadDataTable() {
				loadTableContract();
				
			}
		});
		form.setVisible(true);
		form.setLocationRelativeTo(null);
		form.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		form.getContractList();
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

	
	
	protected void btnNextActionPerformed(ActionEvent e) {
		if(pageNumber<totalPage.intValue()) {
			pageNumber++;
			lblPageNumber.setText(pageNumber.toString());
			loadTableContract();
		}
	}
	
	protected void btnPreviousActionPerformed(ActionEvent e) {
		if(pageNumber>1) {
			pageNumber--;
			lblPageNumber.setText(pageNumber.toString());
			loadTableContract();
		}
	}
	
	protected void cbbDataActionPerformed(ActionEvent e) {
		if(tableConstract!=null) {
			pageNumber=1;
			lblPageNumber.setText(pageNumber.toString());
			rowOfPage = Integer.parseInt(cbbData.getSelectedItem().toString());
			totalPage = Math.ceil(totalOfRow.doubleValue()/rowOfPage.doubleValue());
			loadTableContract();
		}
	}
	
	public void filterData() {
		var documentListener = new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				filterDataContract();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				filterDataContract();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}
		};
		
		txtByOwner.getDocument().addDocumentListener(documentListener);
		txtByRoom.getDocument().addDocumentListener(documentListener);
		cbbStatus.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				filterDataContract();
			}
		});
	}
	
	private void filterDataContract() {
		Integer roomInt = null;
		var dao = new ContractDao();
		String onwer = txtByOwner.getText().trim();	
		String room = txtByRoom.getText().trim();
		if (!room.isEmpty()) {
		    roomInt = Integer.parseInt(room);
		}
		
		String statusSelected = (String) cbbStatus.getSelectedItem();
		Boolean status = null;
		if ("On".equals(statusSelected)) {
	        status = true;
	    } else if ("Off".equals(statusSelected)) {
	        status = false;
	    }
		
		
		List<Contract> filterContract = null;
		
		
		
		
		if(!onwer.isEmpty() || roomInt != null || status != null) {
			filterContract = dao.selContractFilter(onwer, roomInt, status);
		} else {
			filterContract = dao.selContract(pageNumber, rowOfPage);
		}
		updateTable(filterContract);
	}
	
	
	
	private void updateTable(List<Contract> filterContract) {
		var dao = new ContractDao();
		DefaultTableModel model = (DefaultTableModel) tableConstract.getModel();
		model.setRowCount(0);
		Set<String> statusSet = new HashSet<>(); //cbb
		filterContract.forEach(contract -> {
			String statusContract = contract.isStatus() ? "On" : "Off";
			statusSet.add(statusContract);//add to cbb
			String roomateNamesString = "";
		    if (contract.getRoomates() != null) {
		    	System.out.println(contract.getRoomates());
		        String[] roomateID = contract.getRoomates().split(";");
		        
		        List<String> roomateName = dao.getRoommateNamesByIds(roomateID);
		        roomateNamesString = String.join(", ", roomateName);
		    }

			model.addRow(new Object[] { contract.getId(), contract.getApartNum(), contract.getOwnerName(),
					contract.getImgContracs(), statusContract, contract.getFormDate(), contract.getToDate(),
					roomateNamesString, contract.getRoomates(), contract.getOwnerID()});
		});
	}
	
	protected void btnResetActionPerformed(ActionEvent e) {
		txtByOwner.setText("");
		txtByRoom.setText("");
		cbbStatus.setSelectedItem("Search by status");
		
		
	}
	
	public void reloadTable() {
		loadTableContract();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	

	private void initComponent() {
		lblContract = new JLabel("Contract");
		lblContract.setFont(new Font("Arial", Font.BOLD, 20));

		btnAddContract = new JButton("Add Contract");
		btnAddContract.setFocusable(false);
		btnAddContract.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAddContractActionPerformed(e);
			}
		});
		btnAddContract.setForeground(SystemColor.window);
		btnAddContract.setFont(new Font("Arial", Font.BOLD, 15));
		btnAddContract.setBackground(SystemColor.activeCaption);
		btnAddContract.setBorder(null);

		
		lblPageNumber.setFont(new Font("Arial", Font.BOLD, 12));
		lblPageNumber.setOpaque(true);
		lblPageNumber.setEnabled(false);
		lblPageNumber.setBackground(Color.WHITE);
		lblPageNumber.setHorizontalAlignment(SwingConstants.CENTER);

		
		cbbStatus.setMinimumSize(new Dimension(30, 21));
		cbbStatus.setBackground(SystemColor.window);
		cbbStatus.setFont(new Font("Arial", Font.BOLD, 12));
		cbbStatus.setBorder(null);

		cbbData = new JComboBox();
		cbbData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbbDataActionPerformed(e);
			}
		});
		cbbData.setModel(new DefaultComboBoxModel(new String[] { "15", "30", "40", "50" }));
		cbbData.setFont(new Font("Arial", Font.BOLD, 12));
		cbbData.setBorder(null);
		cbbData.setBackground(SystemColor.window);

		txtByOwner.setBackground(SystemColor.menu);
		txtByOwner.setBorder(new TitledBorder(
				new BevelBorder(BevelBorder.LOWERED, new Color(192, 192, 192), new Color(192, 192, 192),
						new Color(192, 192, 192), new Color(192, 192, 192)),
				"Search By Owner", TitledBorder.LEADING, TitledBorder.TOP, null, Color.LIGHT_GRAY));
		txtByOwner.setColumns(10);

		txtByRoom.setBackground(SystemColor.menu);
		txtByRoom.setBorder(new TitledBorder(
				new BevelBorder(BevelBorder.LOWERED, new Color(192, 192, 192), new Color(192, 192, 192),
						new Color(192, 192, 192), new Color(192, 192, 192)),
				"Search By Room", TitledBorder.LEADING, TitledBorder.TOP, null, Color.LIGHT_GRAY));
		txtByRoom.setColumns(10);

		scrollTable.setBackground(SystemColor.window);
		scrollTable.setBorder(new BevelBorder(BevelBorder.LOWERED, SystemColor.menu, SystemColor.menu, SystemColor.menu,
				SystemColor.menu));

		btnPrevious = new JButton("<");
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnPreviousActionPerformed(e);
			}
		});
		btnPrevious.setBackground(SystemColor.activeCaption);
		btnPrevious.setBorder(null);

		btnNext = new JButton(">");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNextActionPerformed(e);
			}
		});
		btnNext.setBorder(null);
		btnNext.setBackground(SystemColor.activeCaption);
		
		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnResetActionPerformed(e);
			}
		});
		btnReset.setForeground(SystemColor.window);
		btnReset.setFont(new Font("Arial", Font.BOLD, 15));
		btnReset.setFocusable(false);
		btnReset.setBorder(null);
		btnReset.setBackground(new Color(143, 188, 143));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(371)
							.addComponent(btnPrevious, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(lblPageNumber, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnNext, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 241, Short.MAX_VALUE)
							.addComponent(cbbData, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(20)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollTable, GroupLayout.DEFAULT_SIZE, 1015, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(txtByOwner, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtByRoom, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(cbbStatus, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 265, Short.MAX_VALUE)
									.addComponent(btnAddContract, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblContract, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))))
					.addGap(15))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblContract, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(txtByOwner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtByRoom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnAddContract, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(cbbStatus, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addComponent(scrollTable, GroupLayout.PREFERRED_SIZE, 628, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNext, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
						.addComponent(lblPageNumber, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
						.addComponent(btnPrevious, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
						.addComponent(cbbData, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
					.addGap(31))
		);
		groupLayout.linkSize(SwingConstants.VERTICAL, new Component[] {txtByOwner, txtByRoom, btnAddContract, btnReset});
		groupLayout.linkSize(SwingConstants.VERTICAL, new Component[] {lblPageNumber, btnPrevious});
		setLayout(groupLayout);
	}

}
