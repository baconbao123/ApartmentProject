package formEnterAd;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.ApartmentDao;
import entity.Apartment;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingUtilities;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import javax.swing.JDialog;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.SwingConstants;

public class FrameAddRoom extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblApartment;
	private JLabel lblFloor;
	private JLabel ReadNumFloor;
	private JLabel lblConvenient;
	private JLabel lblUtilities;
	private JLabel lblMaximumOccupancy;
	private JTextField txtMaxOccupacy;
	private JLabel lblType;
	private JTextField txtType;
	private JButton btnSave;
	private JLabel lblegM;
	private JLabel ReadApartNum;
	private JScrollPane scrollConvenient;
	private JPanel panelConvenient;
	private JCheckBox ckboxConvenient;
	private JButton btnAddConvenient;
	private JButton btnDeleteCon;
	private JScrollPane scrollUtilities;
	private JPanel panelUtilities;
	private JButton btnDeleteUti;
	private JButton btnAddUti;
	private JCheckBox chckbxGym;
	private JLabel lblID;
	private JLabel ReadNumId;

	/**
	 * Launch the application.
	 */
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 530);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		lblFloor = new JLabel("Floor");
		lblFloor.setBounds(10, 85, 40, 17);
		lblFloor.setForeground(Color.GRAY);
		lblFloor.setFont(new Font("Arial", Font.BOLD, 14));
		
		ReadNumFloor = new JLabel("1");
		ReadNumFloor.setBounds(172, 85, 26, 17);
		ReadNumFloor.setFont(new Font("Arial", Font.PLAIN, 14));
		
		// convenient
		scrollConvenient = new JScrollPane();
		scrollConvenient.setBounds(172, 120, 293, 80);
		panelConvenient = new JPanel();
		
		panelConvenient.setLayout(new GridLayout(0, 2));
		btnAddConvenient = new JButton("   +   ");
		btnAddConvenient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAddConvenientActionPerformed(e);
			}
		});
		panelConvenient.add(btnAddConvenient);
		btnDeleteCon = new JButton("Detele convenient");
		btnDeleteCon.setBounds(10, 161, 119, 23);
		btnDeleteCon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDeleteConActionPerformed(e);
			}
		});
		
		
		ckboxConvenient = new JCheckBox("air-conditioner");
		ckboxConvenient.setHorizontalAlignment(SwingConstants.CENTER);
		panelConvenient.add(ckboxConvenient);
		
		panelUtilities = new JPanel();
		panelUtilities.setLayout(new GridLayout(0, 2));
		
		txtMaxOccupacy = new JTextField();
		txtMaxOccupacy.setBounds(172, 319, 127, 18);
		txtType = new JTextField();
		txtType.setBounds(172, 352, 128, 18);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSaveActionPerformed(e);
			}
		});
		btnSave.setBounds(175, 432, 104, 33);
		
		
		initComponent();
	}
	
	//id
	public void setIDApart(String id) {
		ReadNumId.setText(id);
		System.out.println("Id " + id);
	}
	
	// floor
	public void setFloorApartNum(String apartFloor) {
		ReadNumFloor.setText(apartFloor);
	}
	
	// apart num
	public void setApartNum(String apartNum) {
		ReadApartNum.setText(apartNum);
	}
	
	
	// convenient
	protected void btnAddConvenientActionPerformed(ActionEvent e) {
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
				
				panelConvenient.add(newCheckbox);
				panelConvenient.revalidate();
				panelConvenient.repaint();
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
	    // Method to update the panelDelete contents
	    updatePanelDelete(panelDelete);
	    
	    JScrollPane scrollPane = new JScrollPane(panelDelete);
	    dialog.getContentPane().add(scrollPane);
	    
	    dialog.setSize(300, 200);
	    dialog.setVisible(true);
	}

	private void updatePanelDelete(JPanel panelDelete) {
	    panelDelete.removeAll(); 
	    
	    for (Component component : panelConvenient.getComponents()) {
	        if (component instanceof JCheckBox) {
	            JCheckBox checkBox = (JCheckBox) component;
	            JCheckBox checkBoxToDelete = new JCheckBox(checkBox.getText());
	            checkBoxToDelete.setSelected(false);
	            checkBoxToDelete.addItemListener(new ItemListener() {
	                @Override
	                public void itemStateChanged(ItemEvent e) {
	                    if (e.getStateChange() == ItemEvent.SELECTED) {
	                        int option = JOptionPane.showConfirmDialog(panelDelete, "Confirm delete");
	                        
	                        if (option == JOptionPane.YES_OPTION) {
	                            panelDelete.revalidate();
	                            panelDelete.repaint();
	                            panelConvenient.remove(checkBox);
	                            panelConvenient.revalidate();
	                            panelConvenient.repaint();
	                            
	                            // Update the panelDelete contents
	                            updatePanelDelete(panelDelete);
	                        }
	                    }
	                }
	            });
	            panelDelete.add(checkBoxToDelete);
	        }
	    }
	    
	    panelDelete.revalidate();
	    panelDelete.repaint();
	}
	
	// utilities
	protected void btnAddUtiActionPerformed(ActionEvent e) {
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
				
				panelUtilities.add(newCheckbox);
				panelUtilities.revalidate();
				panelUtilities.repaint();
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
	    // Method to update the panelDelete contents
	    updatePanelUlti(panelDelete);
	    
	    JScrollPane scrollPane = new JScrollPane(panelDelete);
	    dialog.getContentPane().add(scrollPane);
	    
	    dialog.setSize(300, 200);
	    dialog.setVisible(true);
	}
	
	private void updatePanelUlti(JPanel panelDelete) {
	    panelDelete.removeAll(); 
	    
	    for (Component component : panelUtilities.getComponents()) {
	        if (component instanceof JCheckBox) {
	            JCheckBox checkBox = (JCheckBox) component;
	            JCheckBox checkBoxToDelete = new JCheckBox(checkBox.getText());
	            checkBoxToDelete.setSelected(false);
	            checkBoxToDelete.addItemListener(new ItemListener() {
	                @Override
	                public void itemStateChanged(ItemEvent e) {
	                    if (e.getStateChange() == ItemEvent.SELECTED) {
	                        int option = JOptionPane.showConfirmDialog(panelDelete, "Confirm delete");
	                        
	                        if (option == JOptionPane.YES_OPTION) {
	                            panelDelete.revalidate();
	                            panelDelete.repaint();
	                            panelUtilities.remove(checkBox);
	                            panelUtilities.revalidate();
	                            panelUtilities.repaint();
	                            
	                            // Update the panelDelete contents
	                            updatePanelDelete(panelDelete);
	                        }
	                    }
	                }
	            });
	            panelDelete.add(checkBoxToDelete);
	        }
	    }
	    
	    panelDelete.revalidate();
	    panelDelete.repaint();
	}
	
	private String getSelectedConveniences() {
	    StringBuilder selectedConveniences = new StringBuilder();
	    Component[] components = panelConvenient.getComponents();
	    for (Component component : components) {
	        if (component instanceof JCheckBox) {
	            JCheckBox checkBox = (JCheckBox) component;
	            if (checkBox.isSelected()) {
	                if (selectedConveniences.length() > 0) {
	                    selectedConveniences.append("; "); 
	                }
	                selectedConveniences.append(checkBox.getText());
	            }
	        }
	    }
	    return selectedConveniences.toString();
	}
	
	private String getSelectedUlti() {
		StringBuilder selectedUlti = new StringBuilder();
		Component[] components = panelUtilities.getComponents();
		for(Component comp : components) {
			if(comp instanceof JCheckBox) {
				JCheckBox checkbox = (JCheckBox) comp;
				if(checkbox.isSelected()) {
					if(selectedUlti.length()>0) {
						selectedUlti.append(";");
					}
					selectedUlti.append(checkbox.getText());
				}
			}
		}
		
		return selectedUlti.toString();
		
	}
	
	protected void btnSaveActionPerformed(ActionEvent e) {
		var apart = new Apartment();
		var dao = new ApartmentDao();
		
		
		
		apart.setId(Integer.parseInt(ReadNumId.getText()));
		apart.setRoomNumber(Integer.parseInt(ReadApartNum.getText()));
		apart.setFloor(Integer.parseInt(ReadNumFloor.getText()));
		apart.setType(txtType.getText());
		apart.setPeopleMaximun(Integer.parseInt(txtMaxOccupacy.getText()));
		
		apart.setConvenient(getSelectedConveniences());
		apart.setUtilities(getSelectedUlti());
		
		dao.insertApart(apart);
	}
	
	
	
	
	
	
	
	
	
	

	private void initComponent() {	
		
		
		btnAddConvenient.setFont(new Font("Arial", Font.PLAIN, 14));
		btnAddConvenient.setBackground(Color.LIGHT_GRAY);
		btnAddConvenient.setBorder(null);
		
		ckboxConvenient.setBorder(null);
		ckboxConvenient.setFont(new Font("Arial", Font.PLAIN, 14));
		
		lblApartment = new JLabel("Apartment");
		lblApartment.setBounds(183, 16, 73, 24);
		lblApartment.setForeground(Color.GRAY);
		lblApartment.setFont(new Font("Arial", Font.BOLD, 15));
		
		lblConvenient = new JLabel("Convenient");
		lblConvenient.setBounds(10, 143, 99, 17);
		lblConvenient.setForeground(Color.GRAY);
		lblConvenient.setFont(new Font("Arial", Font.BOLD, 14));
		
		lblUtilities = new JLabel("Utilities");
		lblUtilities.setBounds(10, 244, 99, 17);
		lblUtilities.setForeground(Color.GRAY);
		lblUtilities.setFont(new Font("Arial", Font.BOLD, 14));

		lblMaximumOccupancy = new JLabel("Maximum occupancy");
		lblMaximumOccupancy.setBounds(11, 319, 145, 17);
		lblMaximumOccupancy.setForeground(Color.GRAY);
		lblMaximumOccupancy.setFont(new Font("Arial", Font.BOLD, 14));
		
		txtMaxOccupacy.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY));
		txtMaxOccupacy.setColumns(10);
		
		lblType = new JLabel("Type");
		lblType.setBounds(10, 352, 145, 17);
		lblType.setForeground(Color.GRAY);
		lblType.setFont(new Font("Arial", Font.BOLD, 14));
		
		txtType.setColumns(10);
		txtType.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY));
		
		btnSave.setForeground(Color.WHITE);
		btnSave.setFont(new Font("Arial", Font.BOLD, 12));
		btnSave.setFocusable(false);
		btnSave.setBorder(null);
		btnSave.setBackground(new Color(38, 185, 154));
		
		lblegM = new JLabel("(e.g: 15*30 m2)");
		lblegM.setBounds(10, 372, 145, 14);
		lblegM.setForeground(Color.GRAY);
		lblegM.setFont(new Font("Arial", Font.PLAIN, 12));
		
		ReadApartNum = new JLabel("101");
		ReadApartNum.setBounds(262, 16, 24, 24);
		ReadApartNum.setForeground(Color.GRAY);
		ReadApartNum.setFont(new Font("Arial", Font.BOLD, 15));
		
		
		scrollConvenient.setBorder(null);
		
		scrollUtilities = new JScrollPane();
		scrollUtilities.setBorder(null);
		scrollUtilities.setBounds(172, 220, 293, 78);
		
		
		panelConvenient.setBorder(null);
		scrollConvenient.setViewportView(panelConvenient);
		scrollConvenient.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.setLayout(null);
		contentPane.add(scrollUtilities);
		
		
		scrollUtilities.setViewportView(panelUtilities);
		
		
		btnAddUti = new JButton("   +   ");
		btnAddUti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAddUtiActionPerformed(e);
			}
		});
		btnAddUti.setFont(new Font("Arial", Font.PLAIN, 14));
		btnAddUti.setBorder(null);
		btnAddUti.setBackground(Color.LIGHT_GRAY);
		panelUtilities.add(btnAddUti);
		
		chckbxGym = new JCheckBox("gym");
		chckbxGym.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxGym.setFont(new Font("Arial", Font.PLAIN, 14));
		chckbxGym.setBorder(null);
		panelUtilities.add(chckbxGym);
		contentPane.add(lblFloor);
		contentPane.add(lblConvenient);
		contentPane.add(lblUtilities);
		contentPane.add(lblMaximumOccupancy);
		contentPane.add(lblType);
		contentPane.add(btnDeleteCon);
		contentPane.add(scrollConvenient);
		contentPane.add(ReadNumFloor);
		contentPane.add(txtMaxOccupacy);
		contentPane.add(txtType);
		contentPane.add(btnSave);
		contentPane.add(lblegM);
		contentPane.add(lblApartment);
		contentPane.add(ReadApartNum);
		
		btnDeleteUti = new JButton("Detele Utilities");
		btnDeleteUti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDeleteUtiActionPerformed(e);
			}
		});
		btnDeleteUti.setBounds(10, 260, 119, 23);
		contentPane.add(btnDeleteUti);
		
		lblID = new JLabel("ID");
		lblID.setForeground(Color.GRAY);
		lblID.setFont(new Font("Arial", Font.BOLD, 14));
		lblID.setBounds(10, 57, 40, 17);
		contentPane.add(lblID);
		
		ReadNumId = new JLabel("1");
		ReadNumId.setFont(new Font("Arial", Font.PLAIN, 14));
		ReadNumId.setBounds(172, 57, 26, 17);
		contentPane.add(ReadNumId);
		
	}
	
	
	
	
	
}
