package formEnterAd;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.ApartmentDao;
import entity.Apartment;
import regex.Regex;
import regex.Valid;
import view.CardRoom;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.BevelBorder;
import javax.swing.ImageIcon;
import java.awt.Cursor;

public class FrameAddRoom extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblLabel;
	private JLabel lblFloor;
	private JLabel ReadNumFloor;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnCreate;

	
	private JLabel lblReadApNum;
	private JLabel lblID;
	private JLabel ReadID;
	private JScrollPane scrollUtilities;
	private JLabel lblConvenient;
	private JButton btnDeleteCon;
	private JScrollPane scrollConvenient;
	private JButton btnDeleteUti;
	private JLabel lblUtilities;
	private JLabel lblMaximumOccupancy;
	private JLabel lblType;
	private JLabel lblegM;
	private JTextField txtType;
	private JPanel panelCon;
	private JPanel panelUlti;
	private JButton btnAddCon;
	private JButton btnAddUlti;
	private JLabel ReadMaxRoom;
	private JLabel lblcurrentlyAMaximum;
	private JLabel lblM;

	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameAddRoom frame = new FrameAddRoom();
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
	public FrameAddRoom() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 501, 551);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		lblLabel = new JLabel("Add Information Of Apartment");
		lblLabel.setBounds(104, 13, 208, 24);

		lblFloor = new JLabel("Floor");
		lblFloor.setBounds(15, 88, 40, 17);
		ReadNumFloor = new JLabel("1");
		ReadNumFloor.setBounds(177, 88, 26, 17);

		lblReadApNum = new JLabel("101");
		lblReadApNum.setBounds(318, 13, 26, 24);
		
		scrollConvenient = new JScrollPane();
		scrollConvenient.setBounds(177, 116, 293, 80);
		scrollUtilities = new JScrollPane();
		scrollUtilities.setBounds(177, 216, 293, 78);
		
		panelUlti = new JPanel();
		scrollUtilities.setViewportView(panelUlti);
		panelUlti.setLayout(new GridLayout(0, 2));
		
		btnAddUlti = new JButton("+");
		btnAddUlti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAddUltiActionPerformed(e);
			}
		});
		btnAddUlti.setForeground(Color.BLACK);
		btnAddUlti.setFont(new Font("Arial", Font.PLAIN, 14));
		btnAddUlti.setBorder(null);
		btnAddUlti.setBackground(Color.LIGHT_GRAY);
		panelUlti.add(btnAddUlti);
		
		panelCon = new JPanel();
		scrollConvenient.setViewportView(panelCon);
		panelCon.setLayout(new GridLayout(0, 2));
		
		btnAddCon = new JButton("+");
		btnAddCon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAddConActionPerformed(e);
			}
		});
		btnAddCon.setBackground(Color.LIGHT_GRAY);
		btnAddCon.setBorder(null);
		btnAddCon.setForeground(Color.BLACK);
		btnAddCon.setFont(new Font("Arial", Font.PLAIN, 14));
		panelCon.add(btnAddCon);
		

		initComponent();

	}
	//floor
	public void setFloorApart(String floor) {
		ReadNumFloor.setText(floor);
	}

	// id
	public void setIDCurrent(String idCurrent) {
		ReadID.setText(idCurrent);
	}

		
	
	// room Number
	public void setRoomNum(int currentRoomNum) {
		lblReadApNum.setText(String.valueOf(currentRoomNum));
	}
	
	public void displayConvenient(String convenient) {
		if(convenient!=null) {
			panelCon.removeAll(); 
		    panelCon.setLayout(new GridLayout(0, 2)); // Set the layout before adding components
		    
		    // Add btnAddCon first
		    panelCon.add(btnAddCon);
	
		    if(convenient != null) {
		        String[] conveniences = convenient.split(";");
		        
		        for(String convenience : conveniences) {
		            JCheckBox checkBox = new JCheckBox(convenience);
		            checkBox.setSelected(true);
		            panelCon.add(checkBox); // Add JCheckBox to the panel
		        }
		    }
	
		    panelCon.revalidate();
		    panelCon.repaint();
		}
		
	}
	
	public void displayUlti(String ulti) {
		if(ulti!=null) {
			panelUlti.removeAll(); 
			panelUlti.setLayout(new GridLayout(0, 2)); // Set the layout before adding components
		    
		    // Add btnAddCon first
			panelUlti.add(btnAddUlti);
	
		    if(ulti != null) {
		        String[] ultis = ulti.split(";");
		        
		        for(String u : ultis) {
		            JCheckBox checkBox = new JCheckBox(u);
		            checkBox.setSelected(true);
		            panelUlti.add(checkBox); // Add JCheckBox to the panel
		        }
		    }
	
		    panelUlti.revalidate();
		    panelUlti.repaint();
		}
		
	}

	

	// convenient
	protected void btnAddConActionPerformed(ActionEvent e) {
		var dialog = new JDialog();
		var dialogPanel = new JPanel();
		
		dialog.setLocationRelativeTo(null);
		
		var txtEnterCon = new JTextField(20);
		dialogPanel.add(txtEnterCon);
		
		var btnSave = new JButton("Save");
		btnSave.setBorder(null);
		btnSave.setPreferredSize(new Dimension(70, 25));
		btnSave.setBackground(new Color(38, 185, 154));
		btnSave.setForeground(Color.WHITE);
		btnSave.setFocusable(false);
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String infor = txtEnterCon.getText();
				var newCheckbox = new JCheckBox(infor);
				newCheckbox.setFocusable(false);
				newCheckbox.setSelected(true);
				
				panelCon.add(newCheckbox);
				panelCon.revalidate();
				panelCon.repaint();
				dialog.dispose();
			}
		});
		
		dialogPanel.add(btnSave);
		
		dialog.getContentPane().add(dialogPanel);
		dialog.setSize(300, 100);
		dialog.setVisible(true);
	}
	
	protected void btnDeleteConActionPerformed(ActionEvent e) {
		var dialog = new JDialog();
	    var panelDelete = new JPanel();
	    
	    dialog.setLocationRelativeTo(null);
	    
	    updatePanelDelete(panelDelete);
	    
	    var scrollPane = new JScrollPane(panelDelete);
	    dialog.getContentPane().add(scrollPane);
	    
	    dialog.setSize(300, 200);
	    dialog.setVisible(true);
	}
	
	private void updatePanelDelete(JPanel panelDelete) {
		panelDelete.removeAll();
		
		for(Component com: panelCon.getComponents()) {
			if(com instanceof JCheckBox) {
				var ckb = (JCheckBox) com;
				var ckbToDelete = new JCheckBox(ckb.getText());
				ckbToDelete.setSelected(false);
				ckbToDelete.addItemListener(new ItemListener() {
					
					@Override
					public void itemStateChanged(ItemEvent e) {
						if(e.getStateChange() == ItemEvent.SELECTED) {
	                        int option = JOptionPane.showConfirmDialog(panelDelete, "Confirm delete");

							if(option == JOptionPane.YES_OPTION) {
								panelDelete.revalidate();
	                            panelDelete.repaint();
	                            panelCon.remove(ckb);
	                            panelCon.revalidate();
	                            panelCon.repaint();
	                            
	                            updatePanelDelete(panelDelete);
							}
						}
						
						
					}
				});
			  panelDelete.add(ckbToDelete);
			}
		}
		panelDelete.revalidate();
	    panelDelete.repaint();
	}
	
	//ulti
	protected void btnAddUltiActionPerformed(ActionEvent e) {
		var dialog = new JDialog();
		var dialogPanel = new JPanel();
		
		dialog.setLocationRelativeTo(null);
		
		var txtEnterCon = new JTextField(20);
		dialogPanel.add(txtEnterCon);
		
		var btnSave = new JButton("Save");
		btnSave.setBorder(null);
		btnSave.setPreferredSize(new Dimension(70, 25));
		btnSave.setBackground(new Color(38, 185, 154));
		btnSave.setForeground(Color.WHITE);
		btnSave.setFocusable(false);
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String infor = txtEnterCon.getText();
				var newCheckbox = new JCheckBox(infor);
				newCheckbox.setFocusable(false);
				newCheckbox.setSelected(true);
				
				panelUlti.add(newCheckbox);
				panelUlti.revalidate();
				panelUlti.repaint();
				dialog.dispose();
			}
		});
		
		dialogPanel.add(btnSave);
		
		dialog.getContentPane().add(dialogPanel);
		dialog.setSize(300, 100);
		dialog.setVisible(true);
	}
	
	
	protected void btnDeleteUtiActionPerformed(ActionEvent e) {
		var dialog = new JDialog();
	    var panelDelete = new JPanel();
	    
	    dialog.setLocationRelativeTo(null);
	    
	    updatePanelUlti(panelDelete);
	    
	    var scrollPane = new JScrollPane(panelDelete);
	    dialog.getContentPane().add(scrollPane);
	    
	    dialog.setSize(300, 200);
	    dialog.setVisible(true);
	}
	
	private void updatePanelUlti(JPanel panelDelete) {
		panelDelete.removeAll();
		
		for(Component com: panelUlti.getComponents()) {
			if(com instanceof JCheckBox) {
				var ckb = (JCheckBox) com;
				var ckbToDelete = new JCheckBox(ckb.getText());
				ckbToDelete.setSelected(false);
				ckbToDelete.addItemListener(new ItemListener() {
					
					@Override
					public void itemStateChanged(ItemEvent e) {
						if(e.getStateChange() == ItemEvent.SELECTED) {
	                        int option = JOptionPane.showConfirmDialog(panelDelete, "Confirm delete");

							if(option == JOptionPane.YES_OPTION) {
								panelDelete.revalidate();
	                            panelDelete.repaint();
	                            panelUlti.remove(ckb);
	                            panelUlti.revalidate();
	                            panelUlti.repaint();
	                            
	                            updatePanelDelete(panelDelete);
							}
						}
						
						
					}
				});
			  panelDelete.add(ckbToDelete);
			}
		}
		panelDelete.revalidate();
	    panelDelete.repaint();
	}
	
	private String getSelectedCon() {
		StringBuilder selectedCon = new StringBuilder();
		Component[] components = panelCon.getComponents();
		for(Component com: components) {
			if(com instanceof JCheckBox) {
				var chkb = (JCheckBox) com;
				if(chkb.isSelected()) {
					if(selectedCon.length()>0) {
						selectedCon.append("; ");
					}
					selectedCon.append(chkb.getText());
				}
			}
		}
		return selectedCon.toString();
	}
	
	private String getSelectedUlti() {
		StringBuilder selectedUlti = new StringBuilder();
		Component[] components = panelUlti.getComponents();
		for(Component com: components) {
			if(com instanceof JCheckBox) {
				var chkb = (JCheckBox) com;
				if(chkb.isSelected()) {
					if(selectedUlti.length()>0) {
						selectedUlti.append("; ");
					}
					selectedUlti.append(chkb.getText());
				}
			}
		}
		return selectedUlti.toString();
	}
	
//  save
	protected void btnCreateActionPerformed(ActionEvent e) {
		var apart = new Apartment();
		var dao = new ApartmentDao();
		
		String errorMessage = "";
		
		if(getSelectedUlti().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please enter at least 1 amenity");
			return;
		}
		
		if(getSelectedCon().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please enter at least 1 utilities");
			return;
		}
		
		errorMessage = Valid.validateInputWithNOEmpty(txtType.getText(), Regex.TYPE, "Type Room ", " Invalid. Please enter an apartment with dimensions of 15*20 or 20*20");
		if(errorMessage!=null) {
			JOptionPane.showMessageDialog(null, errorMessage, "Invalid Input", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		apart.setId(Integer.parseInt(ReadID.getText()));
		apart.setFloor(Integer.parseInt(ReadNumFloor.getText()));
		apart.setRoomNumber(Integer.parseInt(lblReadApNum.getText()));
		apart.setPeopleMaximun(Integer.parseInt(ReadMaxRoom.getText()));
		apart.setType(txtType.getText().trim());
		apart.setConvenient(getSelectedCon());
		apart.setUtilities(getSelectedUlti());
		
		dao.insertApart(apart);
		
		dispose();
	}
	
	
	
	
	
	
	
	 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	private void initComponent() {
		lblFloor.setFont(new Font("Arial", Font.BOLD, 14));
		lblLabel.setFont(new Font("Arial", Font.BOLD, 15));
		ReadNumFloor.setFont(new Font("Arial", Font.PLAIN, 14));

		btnCreate = new JButton("Save");
		btnCreate.setBounds(191, 446, 92, 34);
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCreateActionPerformed(e);
			}
		});
		btnCreate.setForeground(Color.WHITE);
		btnCreate.setFont(new Font("Arial", Font.BOLD, 12));
		btnCreate.setFocusable(false);
		btnCreate.setBorder(null);
		btnCreate.setBackground(new Color(143, 188, 143));

		lblReadApNum.setFont(new Font("Arial", Font.BOLD, 15));

		lblID = new JLabel("ID");
		lblID.setBounds(15, 55, 40, 17);
		lblID.setFont(new Font("Arial", Font.BOLD, 14));

		ReadID = new JLabel("1");
		ReadID.setBounds(177, 55, 26, 17);
		ReadID.setFont(new Font("Arial", Font.PLAIN, 14));
		
		
		scrollUtilities.setBorder(null);
		
		lblConvenient = new JLabel("Utilities");
		lblConvenient.setBounds(15, 139, 99, 17);
		lblConvenient.setForeground(Color.GRAY);
		lblConvenient.setFont(new Font("Arial", Font.BOLD, 14));
		
		btnDeleteCon = new JButton("");
		btnDeleteCon.setBounds(15, 157, 25, 25);
		btnDeleteCon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDeleteCon.setFocusable(false);
		btnDeleteCon.setBackground(Color.WHITE);
		btnDeleteCon.setBorder(null);
		btnDeleteCon.setIcon(new ImageIcon(FrameAddRoom.class.getResource("/icon/delete.png")));
		btnDeleteCon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDeleteConActionPerformed(e);
			}
		});
		
		
		scrollConvenient.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollConvenient.setBorder(null);
		
		btnDeleteUti = new JButton("");
		btnDeleteUti.setBounds(15, 256, 25, 25);
		btnDeleteUti.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDeleteUti.setFocusable(false);
		btnDeleteUti.setBackground(Color.WHITE);
		btnDeleteUti.setBorder(null);
		btnDeleteUti.setBorderPainted(false);
		btnDeleteUti.setIcon(new ImageIcon(FrameAddRoom.class.getResource("/icon/delete.png")));
		btnDeleteUti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDeleteUtiActionPerformed(e);
			}
		});
		
		lblUtilities = new JLabel("Amenities");
		lblUtilities.setBounds(15, 233, 99, 17);
		lblUtilities.setForeground(Color.GRAY);
		lblUtilities.setFont(new Font("Arial", Font.BOLD, 14));
		
		lblMaximumOccupancy = new JLabel("Maximum occupancy");
		lblMaximumOccupancy.setBounds(16, 323, 145, 17);
		lblMaximumOccupancy.setForeground(Color.GRAY);
		lblMaximumOccupancy.setFont(new Font("Arial", Font.BOLD, 14));
		
		lblType = new JLabel("Type");
		lblType.setBounds(15, 379, 145, 17);
		lblType.setForeground(Color.GRAY);
		lblType.setFont(new Font("Arial", Font.BOLD, 14));
		
		lblegM = new JLabel("<html>(15*20 m2 or 20*20 m2 apartments available now.)</html>");
		lblegM.setBounds(15, 399, 145, 42);
		lblegM.setForeground(Color.GRAY);
		lblegM.setFont(new Font("Arial", Font.PLAIN, 12));
		
		txtType = new JTextField();
		txtType.setBounds(177, 380, 84, 18);
		txtType.setColumns(10);
		txtType.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY));
		
		ReadMaxRoom = new JLabel("4");
		ReadMaxRoom.setBounds(177, 323, 13, 17);
		ReadMaxRoom.setFont(new Font("Arial", Font.PLAIN, 14));
		contentPane.setLayout(null);
		contentPane.add(lblFloor);
		contentPane.add(lblID);
		contentPane.add(lblConvenient);
		contentPane.add(lblUtilities);
		contentPane.add(btnDeleteCon);
		contentPane.add(btnDeleteUti);
		contentPane.add(ReadNumFloor);
		contentPane.add(scrollConvenient);
		contentPane.add(ReadID);
		contentPane.add(scrollUtilities);
		contentPane.add(lblLabel);
		contentPane.add(lblReadApNum);
		contentPane.add(lblMaximumOccupancy);
		contentPane.add(ReadMaxRoom);
		contentPane.add(lblType);
		contentPane.add(lblegM);
		contentPane.add(txtType);
		contentPane.add(btnCreate);
		
		lblcurrentlyAMaximum = new JLabel("<html>(Max 4 people per apartment currently)</html>");
		lblcurrentlyAMaximum.setForeground(Color.GRAY);
		lblcurrentlyAMaximum.setFont(new Font("Arial", Font.PLAIN, 12));
		lblcurrentlyAMaximum.setBounds(15, 342, 170, 34);
		contentPane.add(lblcurrentlyAMaximum);
		
		lblM = new JLabel("/ m2");
		lblM.setFont(new Font("Arial", Font.PLAIN, 14));
		lblM.setBounds(271, 381, 41, 17);
		contentPane.add(lblM);
	}
	
	
	
	
}
