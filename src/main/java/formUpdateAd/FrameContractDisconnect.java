package formUpdateAd;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.ContractDao;
import entity.Contract;
import view.AppStateManager;
import view.CardRoom;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.Cursor;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrameContractDisconnect extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblRoom;
	private JLabel lblNewLabel_1;
	private JLabel lblApNum;
	private JLabel ReadApNum;
	private JLabel lblOwner;
	private JLabel ReadOwner;
	private JLabel lblConImgs;
	private JPanel panelImgCon;
	private JLabel lblNewLabel_7;
	private JRadioButton rdOn;
	private JRadioButton rdOff;
	private JLabel lblFromDate;
	private JLabel ReadFormDate;
	private JLabel lblToDate;
	private JLabel ReadToDate;
	private JLabel lblRoomates;
	private JScrollPane scrollPane;
	private JPanel panelRoomate;
	private JButton btnSave;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblContractId;
	private JLabel ReadIdCon;
	private CardRoom cardRoom;

	public void setCardRoom(CardRoom cardRoom) {
		this.cardRoom = cardRoom;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameContractDisconnect frame = new FrameContractDisconnect();
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
//	public FrameContractDisconnect() {}
	public FrameContractDisconnect() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 511, 505);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		lblRoom = new JLabel("");
		lblRoom.setFont(new Font("Arial", Font.BOLD, 14));
		
		ReadApNum = new JLabel("1");
		ReadApNum.setFont(new Font("Arial", Font.PLAIN, 14));
		
		ReadOwner = new JLabel("1");
		ReadOwner.setFont(new Font("Arial", Font.PLAIN, 14));
		
		panelImgCon = new JPanel();
		panelImgCon.setBackground(Color.WHITE);
		panelImgCon.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		rdOn = new JRadioButton("On");
		buttonGroup.add(rdOn);
		
		rdOff = new JRadioButton("Off");
		buttonGroup.add(rdOff);
		
		ReadFormDate = new JLabel("2012/12/12");
		ReadFormDate.setFont(new Font("Arial", Font.PLAIN, 14));
		
		ReadToDate = new JLabel("2012/12/12");
		ReadToDate.setFont(new Font("Arial", Font.PLAIN, 14));
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSaveActionPerformed(e);
			}
		});
		btnSave.setFocusable(false);

		
		initComponent();
	}
	
	public void setApartNum(String apartNum) {
		lblRoom.setText("Contract for room "+ apartNum);
		ReadApNum.setText(apartNum);
	}
	
	public void setOwneraName(String ownerName) {
		ReadOwner.setText(ownerName);
	}
	
	//img
	public void setImgCon(String imgs) {
//		System.out.println("img " + img);
		Map<JLabel, Image> imgMap = new HashMap<>();
		if(imgs!=null) {
			String[] imgPathArr = imgs.split(";");
			for(String imgPath: imgPathArr) {
				var imgLable = new JLabel();
				imgLable.setCursor(new Cursor(Cursor.HAND_CURSOR));
				var icon = new ImageIcon(imgPath);
				Image image = icon.getImage();
				Image imgResize = image.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
				var iconNew = new ImageIcon(imgResize);
				imgLable.setIcon(iconNew);
				panelImgCon.add(imgLable);
				
				imgMap.put(imgLable, image);
				
				imgLable.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						JLabel clickLabel = (JLabel) e.getSource();
						Image clickImg = imgMap.get(clickLabel);
						if(clickImg!=null) {
							var dialog = new JDialog();
							ImageIcon iconNewShow = new ImageIcon(clickImg.getScaledInstance(600, 600, Image.SCALE_SMOOTH));
							JLabel imgLabelShow = new JLabel(iconNewShow);
							dialog.getContentPane().add(imgLabelShow);
							dialog.pack();
							dialog.setVisible(true);
							dialog.setLocationRelativeTo(null);
						}
						super.mouseClicked(e);
					}
				});
				
			}
		}
		
	}
	
	public void setFromDateCon(Date fromDate) {
		SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd");
		String fromDateStr = spf.format(fromDate);
		ReadFormDate.setText(fromDateStr);
	}
	
	public void setToDateCon(Date toDate) {
		SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd");
		String toDateStr = spf.format(toDate);
		ReadToDate.setText(toDateStr);
	}
	
	public void setRoomateRoom(String roomates) {
//		System.out.println("roomates" + roomates);
		var dao = new ContractDao();
		StringBuilder allRoommateInfo = new StringBuilder();
		dao.selectIDRoomate().stream().forEach(roomate -> {
			String[] roomateIds = roomates.split(";");
			List<String> roomateInfor = dao.getRoommateInforsByIds(roomateIds);
			
			StringBuilder formatInfo = new StringBuilder();
			roomateInfor.forEach(info -> {
				formatInfo.append(info.replace(",", ",<br>")).append(",<br>");
			});
			String formattedInfo = formatInfo.toString();
			if (formattedInfo.endsWith(",<br>")) {
				formattedInfo = formattedInfo.substring(0, formattedInfo.length() - 6); // Loại bỏ 6 ký tự ",<br>"
			}
			allRoommateInfo.append("<html>").append(formattedInfo).append("</html>");
		});
		JLabel allRoomateLabel = new JLabel(allRoommateInfo.toString());
		allRoomateLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelRoomate.add(allRoomateLabel);
		panelRoomate.setLayout(new FlowLayout(FlowLayout.LEFT));
	}
	
	public void setIDCon(String id) {
		ReadIdCon.setText(id);
	}
	
	protected void btnSaveActionPerformed(ActionEvent e) {
		int result = JOptionPane.showConfirmDialog(null, "Check-out confirmation for Room " + ReadApNum.getText());
		if (result == JOptionPane.YES_OPTION) {
			var contract = new Contract();
			var dao = new ContractDao();
			
			contract.setId(Integer.parseInt(ReadIdCon.getText()));
			if(rdOn.isSelected()) {
				contract.setStatus(true);
			} else if(rdOff.isSelected()) {
				contract.setStatus(false);
			}
			
			dao.updateConStatus(contract);
			
			dispose();
			
			if(cardRoom!=null) {
				int roomNumber = cardRoom.getCurrentApartNum();
				cardRoom.setBackgroundColor(Color.WHITE);
				
				
				CardLayout layout = (CardLayout) cardRoom.CardButton.getLayout();
				layout.show(cardRoom.CardButton, "available");
				
				AppStateManager.saveAppState(roomNumber, Color.WHITE, "available");
			}
		}
		
		
	}
	
	
	
	
	
	
	
	

	private void initComponent() {
		lblNewLabel_1 = new JLabel("<html>If you wish to terminate the contract, please change the contract status to 'Off'.</html>");
		lblNewLabel_1.setForeground(new Color(220, 20, 60));
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 13));
		
		lblApNum = new JLabel("Apartment Number");
		lblApNum.setForeground(Color.GRAY);
		lblApNum.setFont(new Font("Arial", Font.BOLD, 14));
		
		lblOwner = new JLabel("Owner Name");
		lblOwner.setForeground(Color.GRAY);
		lblOwner.setFont(new Font("Arial", Font.BOLD, 14));
		
		lblConImgs = new JLabel("Contract Images");
		lblConImgs.setForeground(Color.GRAY);
		lblConImgs.setFont(new Font("Arial", Font.BOLD, 14));
		
		lblNewLabel_7 = new JLabel("Status");
		lblNewLabel_7.setForeground(Color.GRAY);
		lblNewLabel_7.setFont(new Font("Arial", Font.BOLD, 14));
		
		rdOn.setSelected(true);
		rdOn.setFocusable(false);
		rdOn.setFont(new Font("Arial", Font.PLAIN, 14));
		rdOn.setBackground(Color.WHITE);
		rdOn.setBorder(null);
		
		rdOff.setFocusable(false);
		rdOff.setFont(new Font("Arial", Font.PLAIN, 14));
		rdOff.setBackground(Color.WHITE);
		
		lblFromDate = new JLabel("From Date");
		lblFromDate.setForeground(Color.GRAY);
		lblFromDate.setFont(new Font("Arial", Font.BOLD, 14));
		
		lblToDate = new JLabel("To Date");
		lblToDate.setForeground(Color.GRAY);
		lblToDate.setFont(new Font("Arial", Font.BOLD, 14));
		
		lblRoomates = new JLabel("Roomates");
		lblRoomates.setForeground(Color.GRAY);
		lblRoomates.setFont(new Font("Arial", Font.BOLD, 14));
		
		btnSave.setForeground(Color.WHITE);
		btnSave.setBackground(new Color(38, 185, 154));
		btnSave.setFont(new Font("Arial", Font.BOLD, 13));
		btnSave.setBorder(null);
		
		lblContractId = new JLabel("Contract ID");
		lblContractId.setForeground(Color.GRAY);
		lblContractId.setFont(new Font("Arial", Font.BOLD, 14));
		
		ReadIdCon = new JLabel("1");
		ReadIdCon.setForeground(Color.BLACK);
		ReadIdCon.setFont(new Font("Arial", Font.PLAIN, 14));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(168)
					.addComponent(lblRoom)
					.addContainerGap(317, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblToDate, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblRoomates, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE))
							.addGap(46)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(ReadToDate, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 167, Short.MAX_VALUE))
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_7, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblFromDate, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE))
							.addGap(46)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(ReadFormDate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(rdOn)
									.addGap(18)
									.addComponent(rdOff, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)))))
					.addGap(26))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblApNum)
									.addGap(46)
									.addComponent(ReadApNum, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
									.addGap(13)
									.addComponent(lblContractId, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(ReadIdCon, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblOwner, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblConImgs, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE))
									.addGap(46)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(ReadOwner, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(panelImgCon, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE))))
							.addGap(26))))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(202)
					.addComponent(btnSave, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
					.addGap(194))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblRoom, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblApNum)
						.addComponent(ReadApNum)
						.addComponent(lblContractId, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(ReadIdCon, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblOwner, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(ReadOwner, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblConImgs, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(panelImgCon, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_7, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(rdOn)
						.addComponent(rdOff))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFromDate, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(ReadFormDate, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblToDate, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(ReadToDate, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblRoomates, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnSave, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.linkSize(SwingConstants.VERTICAL, new Component[] {rdOn, rdOff});
		
		panelRoomate = new JPanel();
		panelRoomate.setBackground(Color.WHITE);
		scrollPane.setViewportView(panelRoomate);
		contentPane.setLayout(gl_contentPane);
		
		
	}
}
