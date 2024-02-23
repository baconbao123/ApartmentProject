package formAdView;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.ContractDao;
import entity.Users;

import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.Cursor;

import javax.swing.JScrollPane;

public class ViewRoomContract extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblContractInformation;
	private JLabel lblApartNum;
	private JLabel lblOwnerNum;
	private JLabel lblContractImgs;
	private JLabel lblUpTo;
	private JLabel lblStatus;
	private JLabel lblFromDate;
	private JLabel lblToDate;
	private JLabel lblRoomates;
	private JButton btnSave;
	private JLabel lblReadApartNum;
	private JLabel lblReadName;
	private JLabel lblReadStatus;
	private JLabel lblReadFromDate;
	private JLabel lblReadToDate;
	private JScrollPane scrollPane;
	private JPanel panelReadPicCons;
	private JPanel panelReadRoomate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewRoomContract frame = new ViewRoomContract();
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
	public ViewRoomContract(String apartNum, String fullname, String status, String dateFrom, String dateTo,
			String imgPaths, String rommateStrID) {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 520);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		lblContractInformation = new JLabel("Contract Information");
		lblApartNum = new JLabel("Apartment Number");
		lblOwnerNum = new JLabel("Owner Name");
		lblContractImgs = new JLabel("Contract Images");
		lblUpTo = new JLabel("Up to 5 images(png or jpg)");
		lblStatus = new JLabel("Status");
		lblFromDate = new JLabel("From Date");
		lblToDate = new JLabel("To Date");
		lblRoomates = new JLabel("Roomates");
		

		lblReadApartNum = new JLabel("101");
		lblReadName = new JLabel("Phuong Anh");
		lblReadStatus = new JLabel("Rented");
		lblReadFromDate = new JLabel("12/12/1200");
		lblReadToDate = new JLabel("12/12/1200");
		panelReadPicCons = new JPanel();
		scrollPane = new JScrollPane();
		panelReadRoomate = new JPanel();
		panelReadRoomate.setLocation(0, 240);

		lblReadApartNum.setText(apartNum);
		lblReadName.setText(fullname);
		lblReadStatus.setText(status);
		lblReadFromDate.setText(dateTo);
		lblReadToDate.setText(dateFrom);

		// img Contract
		Map<JLabel, Image> imageMap = new HashMap<>();
		String[] imgPathArr = imgPaths.split(";");
		for (String imgPath : imgPathArr) {
			var imgJLabel = new JLabel();
			imgJLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			ImageIcon icon = new ImageIcon(imgPath);
			Image image = icon.getImage();
			Image imageResize = image.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
			ImageIcon iconNew = new ImageIcon(imageResize);
			imgJLabel.setIcon(iconNew);
			panelReadPicCons.add(imgJLabel);

			imageMap.put(imgJLabel, image); 

			imgJLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					JLabel clickedLabel = (JLabel) e.getSource();
					Image clickedImage = imageMap.get(clickedLabel); // Lấy hình ảnh tương ứng với JLabel được click
					if (clickedImage != null) {
						JDialog dialog = new JDialog();
						ImageIcon iconNewShow = new ImageIcon(
								clickedImage.getScaledInstance(600, 600, Image.SCALE_SMOOTH));
						JLabel imgLabelShow = new JLabel(iconNewShow);
						dialog.add(imgLabelShow);
						dialog.pack();
						dialog.setVisible(true);
						dialog.setLocationRelativeTo(null);
					}
					super.mouseClicked(e);
				}
			});

		}
		
		// info roomate
		var dao = new ContractDao();
		StringBuilder allRoommateInfo = new StringBuilder();
		dao.selectIDRoomate().stream().forEach(roomate -> {
			String[] roomateID = rommateStrID.split(";");
			List<String> roomateInfo = dao.getRoommateInforsByIds(roomateID);
			
			StringBuilder formattedInfo = new StringBuilder();
			roomateInfo.forEach(info -> {
		        formattedInfo.append(info.replace(",", ",<br>")).append(",<br>");
		    });
			String formInforRep = formattedInfo.toString();
			if (formInforRep.endsWith(",<br>")) {
				formInforRep = formInforRep.substring(0, formInforRep.length() - 6); 
			}
			allRoommateInfo.append("<html>").append(formInforRep).append("</html>");
		});
		JLabel allRoommateLabel = new JLabel(allRoommateInfo.toString());
		allRoommateLabel.setAlignmentX(Component.LEFT_ALIGNMENT); // Để căn trái trong FlowLayout
		panelReadRoomate.add(allRoommateLabel);
		panelReadRoomate.setLayout(new FlowLayout(FlowLayout.LEFT));

		initComponent();
	}

	public ViewRoomContract() {};

	private void initComponent() {
		lblContractInformation.setBounds(129, 16, 187, 24);
		lblContractInformation.setFont(new Font("Arial", Font.BOLD, 18));

		lblApartNum.setBounds(15, 58, 138, 19);
		lblApartNum.setForeground(Color.GRAY);
		lblApartNum.setFont(new Font("Arial", Font.BOLD, 14));

		lblOwnerNum.setBounds(15, 98, 138, 19);
		lblOwnerNum.setForeground(Color.GRAY);
		lblOwnerNum.setFont(new Font("Arial", Font.BOLD, 14));

		lblContractImgs.setBounds(15, 136, 138, 19);
		lblContractImgs.setForeground(Color.GRAY);
		lblContractImgs.setFont(new Font("Arial", Font.BOLD, 14));

		lblUpTo.setBounds(15, 161, 138, 13);
		lblUpTo.setForeground(Color.GRAY);
		lblUpTo.setFont(new Font("Arial", Font.PLAIN, 11));

		lblStatus.setBounds(15, 192, 138, 19);
		lblStatus.setForeground(Color.GRAY);
		lblStatus.setFont(new Font("Arial", Font.BOLD, 14));

		lblFromDate.setBounds(15, 229, 138, 19);
		lblFromDate.setForeground(Color.GRAY);
		lblFromDate.setFont(new Font("Arial", Font.BOLD, 14));

		lblToDate.setBounds(15, 268, 138, 19);
		lblToDate.setForeground(Color.GRAY);
		lblToDate.setFont(new Font("Arial", Font.BOLD, 14));

		lblRoomates.setBounds(15, 323, 138, 19);
		lblRoomates.setForeground(Color.GRAY);
		lblRoomates.setFont(new Font("Arial", Font.BOLD, 14));

		lblReadApartNum.setBounds(190, 58, 195, 20);
		lblReadApartNum.setForeground(Color.BLACK);
		lblReadApartNum.setFont(new Font("Arial", Font.PLAIN, 12));

		lblReadName.setBounds(190, 97, 214, 20);
		lblReadName.setForeground(Color.BLACK);
		lblReadName.setFont(new Font("Arial", Font.PLAIN, 12));
		contentPane.setLayout(null);
		contentPane.add(lblContractInformation);
		contentPane.add(lblApartNum);
		contentPane.add(lblReadApartNum);
		contentPane.add(lblContractImgs);
		contentPane.add(lblUpTo);
		contentPane.add(lblStatus);
		contentPane.add(lblFromDate);
		contentPane.add(lblToDate);
		contentPane.add(lblRoomates);
		contentPane.add(lblOwnerNum);
		contentPane.add(lblReadName);

		lblReadStatus.setForeground(Color.BLACK);
		lblReadStatus.setFont(new Font("Arial", Font.PLAIN, 12));
		lblReadStatus.setBounds(190, 191, 214, 20);
		contentPane.add(lblReadStatus);

		lblReadFromDate.setForeground(Color.BLACK);
		lblReadFromDate.setFont(new Font("Arial", Font.PLAIN, 12));
		lblReadFromDate.setBounds(190, 228, 214, 20);
		contentPane.add(lblReadFromDate);

		lblReadToDate.setForeground(Color.BLACK);
		lblReadToDate.setFont(new Font("Arial", Font.PLAIN, 12));
		lblReadToDate.setBounds(190, 268, 214, 20);
		contentPane.add(lblReadToDate);

		panelReadPicCons.setBounds(190, 136, 234, 50);
		panelReadPicCons.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelReadPicCons.setBackground(Color.WHITE);
		contentPane.add(panelReadPicCons);

		scrollPane.setBounds(190, 302, 234, 142);
		contentPane.add(scrollPane);

		panelReadRoomate.setBackground(Color.WHITE);
//		panelReadRoomate.setLayout(new FlowLayout(FlowLayout.LEFT));
		scrollPane.setViewportView(panelReadRoomate);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(null);
		panelReadRoomate.setBorder(null);
		
	}

}
