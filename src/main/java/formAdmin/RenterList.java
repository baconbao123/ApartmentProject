package formAdmin;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.StyledDocument;

import dao.UserDao;
import entity.Users;
import event.EventTableRenterAction;
import formAdView.ViewInfoRenter;
import formAdmin.ContractList.CenterRenderer;
import formEnterAd.FrameAddRenter;
import formUpdateAd.FrameUpRenter;
import scrollbar.ModernScrollBarUI;
import scrollbar.ScrollBarCustom;
import table.ImgContractRender;
import table.TableActionCellEditor;
import table.TableActionCellRender;
import table.TableHeader;

import java.awt.Component;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.SystemColor;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.EventObject;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.Font;
import java.awt.Graphics2D;

import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class RenterList extends JPanel {
	private JPanel TablePanel;
	private JButton btnPrevious;
	private JButton Next;
	private Integer pageNumber = 1;
	private Integer rowOfPage = 15;
	private Integer totalOfRow = 0;
	private Double totalPage = 0.0;
	private JScrollPane scrollPane;
	private JTable table;
	private JLabel lblNewLabel;
	private JTextField txtSearchName;
	private JTextField txtNIC;
	private JTextField txtByPhone;
	private JButton btnHistory;
	private JComboBox cbbData;
	private JLabel txtPageNumber;
	private JButton btnReset;
	private JButton btnAddRenter;
//	private RenterList renterList;
	
	
	
	
	public RenterList() {
		setBackground(SystemColor.menu);
		setBounds(0, 0, 1050, 800);
		
		TablePanel = new JPanel();
		TablePanel.setBackground(SystemColor.menu);
		TablePanel.setBorder(null);
		
		
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBar(new ScrollBarCustom());
		
		txtPageNumber = new JLabel("");
		txtPageNumber.setText(pageNumber.toString());
		txtSearchName = new JTextField();
		
		table = new JTable();
		table.setBorder(null);
		table.setRowSelectionAllowed(true);
		table.setColumnSelectionAllowed(false);
		table.setShowHorizontalLines(true);
		table.setGridColor(new Color(230, 230, 230));
		table.setRowHeight(40);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setVisible(true);
		table.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				TableHeader header = new TableHeader(value + "");
				
					header.	setHorizontalAlignment(JLabel.CENTER);
					return header;
				
			}
		});
		
		
		
		initComponent();
		loadTable();
	}
	
	public void loadTable() {
		var model = new DefaultTableModel() {
			public java.lang.Class<?> getColumnClass(int columnIndex) {
				switch(columnIndex) {
					default: return String.class;
				}
				
			};
			
			@Override
			public boolean isCellEditable(int row, int column) {
				switch (column) {
					case 10: return true;
					default: return false;
				}
			}
		};

		model.addColumn("Id");
		model.addColumn("Avatar");
		model.addColumn("Fullname");
		model.addColumn("Gender");
		model.addColumn("Phone");
		model.addColumn("Date Of Birth");
		model.addColumn("Address");
		model.addColumn("Citizen Identification Card");
		model.addColumn("Issue Authority");
		model.addColumn("Img Authority");
		model.addColumn("Action");	
		
		
		var dao = new UserDao();
		totalOfRow = dao.countRenter();
		totalPage = Math.ceil(totalOfRow.doubleValue()/rowOfPage.doubleValue());
		
		dao.selectRenders(pageNumber, rowOfPage).stream().forEach(
			renter -> {
				ImageIcon avatarIcon;
				if (renter.getAvatar() != null) {
				    avatarIcon = new ImageIcon(renter.getAvatar());
				} else {
				    avatarIcon = null;
				}
				
			model.addRow(new Object[] {
					renter.getId(),
					avatarIcon,
					renter.getName(),
					renter.getGender(),
					renter.getPhone(),
					renter.getDob(),
					renter.getAddress(),
					renter.getNic(),
					renter.getiAuthority(),
					renter.getImgIAuthority(),
			});
		});
		table.setModel(model);
		table.getColumnModel().getColumn(1).setCellRenderer(new ImgContractRender());
		table.getColumnModel().getColumn(1).setPreferredWidth(20);
		
		EventTableRenterAction event = new EventTableRenterAction() {
			
			@Override
			public void view(int row) {
				ImageIcon avatarOrigin = (ImageIcon) table.getValueAt(row, 1);
				Image avatarNew = avatarOrigin.getImage().getScaledInstance(140, 110, Image.SCALE_SMOOTH);
				ImageIcon avatarResize = new ImageIcon(avatarNew);
				
				
				String fullName = (String) table.getValueAt(row, 2);
				String gender = (String) table.getValueAt(row, 3);
				String phone = (String) table.getValueAt(row, 4);
				java.sql.Date dob = (java.sql.Date) table.getValueAt(row, 5);
				String address = (String) table.getValueAt(row, 6);
				String nic = (String) table.getValueAt(row, 7);
				String iAuthority = (String) table.getValueAt(row, 8);
				String imgCICs = (String) table.getValueAt(row, 9);
				String[] imgSplit = imgCICs.split(";");
				ImageIcon[] imageIcons = new ImageIcon[imgSplit.length];
				ImageIcon img1 = new ImageIcon();
				ImageIcon img2 = new ImageIcon();
				
				for(int i=0; i<imgSplit.length; i++) {
					try {
						BufferedImage image = ImageIO.read(new File(imgSplit[i].trim()));
						ImageIcon resizedImageIcon = new ImageIcon(image.getScaledInstance(400, 400, Image.SCALE_SMOOTH));
						imageIcons[i] = resizedImageIcon;
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				if(imageIcons.length>=2) {
					img1.setImage(imageIcons[0].getImage());
				    
				    img2.setImage(imageIcons[1].getImage());
				}
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String dobString = dateFormat.format(dob);
				
				var viewInfo = new ViewInfoRenter(avatarResize, fullName, gender, phone, dobString, address, nic, iAuthority, img1, img2);
				viewInfo.setVisible(true);
				viewInfo.setLocationRelativeTo(null);
				viewInfo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
			}
			
			@Override
			public void edit(int row) {
				int id = (int) table.getValueAt(row, 0);
				String idStr = String.valueOf(id);
				String fullname = (String) table.getValueAt(row, 2);
				java.sql.Date dob = (java.sql.Date) table.getValueAt(row, 5);
				RenterList renterList = null;
				
				var vewUp = new FrameUpRenter(idStr, fullname, dob);
				vewUp.setVisible(true);
				vewUp.setLocationRelativeTo(null);
				vewUp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
			}
		};
		
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		
		table.getColumnModel().getColumn(10).setCellRenderer(new TableActionCellRender());
		table.getColumnModel().getColumn(10).setCellEditor(new TableActionCellEditor(event));
		
		table.getColumnModel().getColumn(9).setCellRenderer(new ImgContractRender());
		
		filterData();
	}
	
	
	
	
	public void filterData() {
		DocumentListener documentListner = new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				filterData();
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				filterData();
				
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			private void filterData() {
				var dao = new UserDao();
				String name = txtSearchName.getText().trim();
	            String phone = txtByPhone.getText().trim();
	            String nic = txtNIC.getText().trim();
	            List<Users> filteredUsers = null;
	            if (!name.isEmpty() || !phone.isEmpty() || !nic.isEmpty()) {
	            	filteredUsers = dao.selectRendersFilteredData(name, phone, nic);
	            } else {
	                filteredUsers = dao.selectRenders(pageNumber, rowOfPage); // Clear the table when no input is provided
	            }
	            updateTable(filteredUsers);
			}
		};
		txtSearchName.getDocument().addDocumentListener(documentListner);
		txtByPhone.getDocument().addDocumentListener(documentListner);
		txtNIC.getDocument().addDocumentListener(documentListner);
	}
	
	
	protected void updateTable(List<Users> filterUsers) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		filterUsers.forEach(renter -> {
				ImageIcon avatarIcon;
				if (renter.getAvatar() != null) {
				    avatarIcon = new ImageIcon(renter.getAvatar());
				} else {
				    avatarIcon = null;
				}
				
			model.addRow(new Object[] {
					renter.getId(),
					avatarIcon,
					renter.getName(),
					renter.getGender(),
					renter.getPhone(),
					renter.getDob(),
					renter.getAddress(),
					renter.getNic(),
					renter.getiAuthority(),
					renter.getImgIAuthority(),
			});
		});
	}
	
	protected void cbbDataActionPerformed(ActionEvent e) {
		if(table!=null) {
			pageNumber = 1;
			txtPageNumber.setText(pageNumber.toString());
			rowOfPage = Integer.parseInt(cbbData.getSelectedItem().toString());
			totalPage = Math.ceil(totalOfRow.doubleValue() / rowOfPage.doubleValue());
			loadTable();
		}
	}
	
	protected void btnPreviousActionPerformed(ActionEvent e) {
		if(pageNumber>1) {
			pageNumber--;
			txtPageNumber.setText(pageNumber.toString());
			loadTable();
		}
	}
	
	protected void NextActionPerformed(ActionEvent e) {
		if(pageNumber<totalPage.intValue()) {
			pageNumber++;
			txtPageNumber.setText(pageNumber.toString());
			loadTable();
		}
	}
	
	protected void btnResetActionPerformed(ActionEvent e) {
		txtSearchName.setText("");
		txtByPhone.setText("");
		txtNIC.setText("");
		
		loadTable();
	}
	
	protected void btnAddRenterActionPerformed(ActionEvent e) {
		var add = new FrameAddRenter();
		add.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		add.setVisible(true);
		add.setLocationRelativeTo(null);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public void initComponent() {
		TablePanel.setLayout(new BorderLayout(0, 0));
	
		
		btnPrevious = new JButton("<");
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnPreviousActionPerformed(e);
			}
		});
		btnPrevious.setFocusable(false);
		btnPrevious.setBackground(SystemColor.activeCaption);
		btnPrevious.setBorder(null);
		
		Next = new JButton(">");
		Next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NextActionPerformed(e);
			}
		});
		Next.setFocusable(false);
		Next.setBorder(null);
		Next.setBackground(SystemColor.activeCaption);
		
		lblNewLabel = new JLabel("Renter List");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));
		
		txtSearchName = new JTextField();
		txtSearchName.setBackground(SystemColor.menu);
		txtSearchName.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(240, 240, 240), new Color(192, 192, 192)), "Search By Name", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(192, 192, 192)));
		txtSearchName.setFont(new Font("Arial", Font.PLAIN, 12));
		txtSearchName.setColumns(10);
		
		txtNIC = new JTextField();
		txtNIC.setFont(new Font("Arial", Font.PLAIN, 12));
		txtNIC.setColumns(10);
		txtNIC.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(240, 240, 240), new Color(192, 192, 192)), "Search By National ID Card", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(192, 192, 192)));
		txtNIC.setBackground(SystemColor.menu);
		
		txtByPhone = new JTextField();
		txtByPhone.setFont(new Font("Arial", Font.PLAIN, 12));
		txtByPhone.setColumns(10);
		txtByPhone.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(240, 240, 240), new Color(192, 192, 192)), "Search By Phone", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(192, 192, 192)));
		txtByPhone.setBackground(SystemColor.menu);
		
		btnHistory = new JButton("History");
		btnHistory.setForeground(SystemColor.text);
		btnHistory.setFont(new Font("Arial", Font.BOLD, 15));
		btnHistory.setBorder(null);
		btnHistory.setBackground(new Color(15, 160, 206));
		
		cbbData = new JComboBox();
		cbbData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbbDataActionPerformed(e);
			}
		});
		cbbData.setBorder(null);
		cbbData.setBackground(SystemColor.window);
		cbbData.setModel(new DefaultComboBoxModel(new String[] {"15", "30", "40", "60"}));
		
		
		txtPageNumber.setFont(new Font("Arial", Font.PLAIN, 14));
		txtPageNumber.setHorizontalAlignment(SwingConstants.CENTER);
		txtPageNumber.setOpaque(true);
		txtPageNumber.setBackground(SystemColor.window);
		
		btnReset = new JButton("Reset");
		btnReset.setFocusable(false);
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnResetActionPerformed(e);
			}
		});
		btnReset.setForeground(SystemColor.text);
		btnReset.setFont(new Font("Arial", Font.BOLD, 15));
		btnReset.setBorder(null);
		btnReset.setBackground(new Color(143, 188, 143));
		
		btnAddRenter = new JButton("Add Renter");
		btnAddRenter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAddRenterActionPerformed(e);
			}
		});
		btnAddRenter.setForeground(SystemColor.text);
		btnAddRenter.setFont(new Font("Arial", Font.BOLD, 15));
		btnAddRenter.setFocusable(false);
		btnAddRenter.setBorder(null);
		btnAddRenter.setBackground(new Color(100, 149, 237));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(TablePanel, GroupLayout.DEFAULT_SIZE, 1013, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(361)
							.addComponent(btnPrevious, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtPageNumber, GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(Next, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
							.addGap(315)
							.addComponent(cbbData, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(txtSearchName, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtByPhone, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtNIC, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 196, Short.MAX_VALUE)
							.addComponent(btnAddRenter, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnHistory, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)))
					.addGap(19))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(476)
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
					.addGap(476))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(txtSearchName, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(txtByPhone, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
							.addComponent(txtNIC, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnHistory, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnAddRenter, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(TablePanel, GroupLayout.PREFERRED_SIZE, 652, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(cbbData, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
						.addComponent(Next, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
						.addComponent(txtPageNumber, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
						.addComponent(btnPrevious, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(16))
		);
		groupLayout.linkSize(SwingConstants.VERTICAL, new Component[] {btnPrevious, Next, cbbData});
		groupLayout.linkSize(SwingConstants.VERTICAL, new Component[] {btnHistory, btnReset});
		groupLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {btnPrevious, Next});
		
		
		scrollPane.setBackground(SystemColor.menu);
		scrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED, SystemColor.menu, SystemColor.menu, SystemColor.menu, SystemColor.menu));
		TablePanel.add(scrollPane, BorderLayout.CENTER);
		setLayout(groupLayout);
		scrollPane.setViewportView(table);
	}
	
	private ImageIcon createRoundImageIcon(ImageIcon originalIcon) {
	    Image originalImage = originalIcon.getImage();
	    int width = originalImage.getWidth(null);
	    int height = originalImage.getHeight(null);

	    BufferedImage roundedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = roundedImage.createGraphics();
	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g2.setClip(new RoundRectangle2D.Float(0, 0, width, height, width, height));
	    g2.drawImage(originalImage, 0, 0, null);
	    g2.dispose();

	    return new ImageIcon(roundedImage);
	}
	
}
