package formEnterAd;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import common.ConnectDB;
import dao.ApartmentDao;
import model.Fees;
import view.CardRoom;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;

public class FrameAddMoney extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblConfirmPayment;
	private JButton btnPayAll;
	private JButton btnPayMonth;
	private CardRoom cardRoom;
	private JDialog paymentDialog;
	private int currentCardRoom;

	public int getCurrentCardRoom() {
		return currentCardRoom;
	}

	public void setCurrentCardRoom(int currentCardRoom) {
		this.currentCardRoom = currentCardRoom;
	}
	
	
	
	// pay all
	private float moneyAll;

	private String idFeeAll;

	public float getMoneyAll() {
		return moneyAll;
	}

	public void setMoneyAll(float moneyAll) {
		this.moneyAll = moneyAll;
	}
//
	public String getIdFeeAll() {
		return idFeeAll;
	}

	public void setIdFeeAll(String idFeeAll) {
		this.idFeeAll = idFeeAll;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameAddMoney frame = new FrameAddMoney();
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
	public FrameAddMoney() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 276, 157);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(null);

		setContentPane(contentPane);

		btnPayAll = new JButton("Pay All");
		btnPayAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnPayAllActionPerformed(e);
			}
		});
		btnPayAll.setFocusable(false);

		btnPayMonth = new JButton("Monthly payment");
//		btnPayMonth.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				btnPayMonthActionPerformed(e);
//			}
//		});

		initComponent();
	}

	// pay all
	// call btn pay all
	public void setBtnPayAllListener(ActionListener listener) {
		btnPayAll.addActionListener(listener);
	}

	public void btnPayAllActionPerformed(ActionEvent e) {
		if (idFeeAll != null) {
			Set<Integer> uniqueValues = new HashSet<>();
			
			String[] idFeeAllSplit = idFeeAll.split(";");
			
			for (String path : idFeeAllSplit) {
				try {
					int value = Integer.parseInt(path.trim());
					uniqueValues.add(value);
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			
			int result = JOptionPane.showConfirmDialog(null, "Pay the total amount of " + moneyAll);
			if (result == JOptionPane.YES_OPTION) {
				var dao = new ApartmentDao();

				for (Integer value : uniqueValues) {
					var fee = new Fees();
					fee.setIdFeeAll(value.toString());
					fee.setStatus(true);
					dao.updateFeeAll(fee);
					
					
				}
				JOptionPane.showMessageDialog(null, "Payment for the full amount successful");
				dispose();
			}
		}

	}

	// pay month
	public void setBtnPayMonthListener(ActionListener listener) {
		btnPayMonth.addActionListener(listener);
	}

	
	public void btnPayMonthActionPerformed(ActionEvent e) {		
		var dao = new ApartmentDao();
		List<Fees> daoFees = dao.selectMonthByMoth();
//		System.out.println("daoFees" + daoFees);
		
		 if (paymentDialog == null) {
		        paymentDialog = new JDialog();
//		        paymentDialog.setLayout(new GridLayout(timePayments.size(), 1));

		        for (Fees fee : daoFees) {
		            if (fee.getRoom() == currentCardRoom) {
		                JButton btnTime = new JButton(fee.getTime().toString());	
		                int id = fee.getId();
		                float money = fee.getTotal();

		                btnTime.setName(String.valueOf(id)); // Set id as the name of the button
		                btnTime.setFocusable(false);
		                btnTime.setBorder(null);
		                btnTime.setPreferredSize(new Dimension(100, 30));
		                btnTime.setBackground(new Color(185, 211, 238));

		                btnTime.addActionListener(new ActionListener() {
		                    @Override
		                    public void actionPerformed(ActionEvent e) {
		                        JButton source = (JButton) e.getSource();
		                        int buttonId = Integer.parseInt(source.getName()); // Get id of the button

		                        int result = JOptionPane.showConfirmDialog(null, "Payment confirm " + money);

		                        if (result == JOptionPane.YES_OPTION) {
		                            var dao = new ApartmentDao();
		                            var updatedFee = new Fees();

		                            updatedFee.setId(id);
		                            updatedFee.setStatus(true);
		                            dao.updateFeeByMoth(updatedFee);

		                            JOptionPane.showMessageDialog(null, "Payment successful");
		                            refreshPaymentDialog();
		                            dispose();
		                        }
		                    }
		                });

		                paymentDialog.add(btnTime);
		                
		            }
		        }

		        paymentDialog.setLayout(new FlowLayout());
		        paymentDialog.setLocationRelativeTo(null);
		        paymentDialog.setSize(300, 500);
		        paymentDialog.setVisible(true);
		    } else {
		        paymentDialog.setVisible(true);
		    }
		
		
		
	}

	private void refreshPaymentDialog() {
	    paymentDialog.getContentPane().removeAll(); // Xóa tất cả các thành phần hiện có trong dialog

	    var dao = new ApartmentDao();
	    List<Fees> daoFees = dao.selectMonthByMoth();

	    for (Fees fee : daoFees) {
	        if (fee.getRoom() == currentCardRoom) {
	            JButton btnTime = new JButton(fee.getTime().toString());
	            int id = fee.getId();
	            float money = fee.getTotal();

	            btnTime.setName(String.valueOf(id)); // Set id as the name of the button
	            btnTime.setFocusable(false);
	            btnTime.setBorder(null);
	            btnTime.setPreferredSize(new Dimension(100, 30));
	            btnTime.setBackground(new Color(185, 211, 238));

	            btnTime.addActionListener(new ActionListener() {
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                    JButton source = (JButton) e.getSource();
	                    int buttonId = Integer.parseInt(source.getName()); // Get id of the button

	                    int result = JOptionPane.showConfirmDialog(null, "Payment confirm " + money);

	                    if (result == JOptionPane.YES_OPTION) {
	                        var dao = new ApartmentDao();
	                        var updatedFee = new Fees();

	                        updatedFee.setId(id);
	                        updatedFee.setStatus(true);
	                        dao.updateFeeByMoth(updatedFee);

	                        JOptionPane.showMessageDialog(null, "Payment successful");
	                        refreshPaymentDialog();
	                        paymentDialog.revalidate(); 
	                    }
	                }
	            });

	            paymentDialog.add(btnTime);
	        }
	    }

	    paymentDialog.revalidate();
	    paymentDialog.repaint(); 
	}
	

	private void initComponent() {
		lblConfirmPayment = new JLabel("Confirm Payment?");
		lblConfirmPayment.setFont(new Font("Arial", Font.BOLD, 12));
		lblConfirmPayment.setForeground(Color.BLACK);

		btnPayAll.setForeground(Color.WHITE);
		btnPayAll.setFont(new Font("Arial", Font.BOLD, 12));
		btnPayAll.setBackground(new Color(0, 154, 34));
		btnPayAll.setBorder(null);

		btnPayMonth.setFocusable(false);
		btnPayMonth.setForeground(Color.WHITE);
		btnPayMonth.setBackground(new Color(0, 154, 34));
		btnPayMonth.setFont(new Font("Arial", Font.BOLD, 12));
		btnPayMonth.setBorder(null);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(33)
						.addComponent(btnPayAll, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, Short.MAX_VALUE)
						.addComponent(btnPayMonth, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
						.addGap(33))
				.addGroup(
						gl_contentPane
								.createSequentialGroup().addGap(78).addComponent(lblConfirmPayment,
										GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGap(77)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup().addContainerGap()
						.addComponent(lblConfirmPayment, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnPayAll, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnPayMonth))
						.addGap(28)));
		gl_contentPane.linkSize(SwingConstants.VERTICAL, new Component[] { btnPayAll, btnPayMonth });
		contentPane.setLayout(gl_contentPane);
	}

}
