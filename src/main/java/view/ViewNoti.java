package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.Color;

public class ViewNoti extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTextArea txtMess;
	private JLabel lblEmail;
	private JLabel lblRoom;
	private JLabel lblTime;
	private JLabel lblNewLabel;
	private JLabel lblRoom_1;
	private JLabel lbltime;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewNoti frame = new ViewNoti();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param mess 
	 * @param time 
	 * @param room 
	 * @param email 
	 */
	public ViewNoti() {};
	public ViewNoti(String email, String room, String time, String mess) {
		setTitle("View Noti");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(600, 300, 652, 474);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		{
			scrollPane = new JScrollPane();
			scrollPane.setBounds(50, 126, 530, 278);
			contentPane.add(scrollPane);
			{
				txtMess = new JTextArea();
				txtMess.setWrapStyleWord(true);
				txtMess.setEditable(false);
				txtMess.setLineWrap(true);
				txtMess.setFont(new Font("Tahoma", Font.PLAIN, 13));
				scrollPane.setViewportView(txtMess);
			}
		}
		{
			lblEmail = new JLabel();
			lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblEmail.setBounds(109, 16, 263, 27);
			contentPane.add(lblEmail);
		}
		{
			lblRoom = new JLabel();
			lblRoom.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblRoom.setBounds(109, 47, 69, 22);
			contentPane.add(lblRoom);
		}
		{
			lblTime = new JLabel("Sending time:");
			lblTime.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblTime.setBounds(50, 80, 88, 20);
			contentPane.add(lblTime);
		}
		{
			lblNewLabel = new JLabel("From:");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNewLabel.setBounds(50, 23, 57, 14);
			contentPane.add(lblNewLabel);
		}
		{
			lblRoom_1 = new JLabel("Room:");
			lblRoom_1.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblRoom_1.setBounds(50, 52, 57, 14);
			contentPane.add(lblRoom_1);
		}
		{
			lbltime = new JLabel();
			lbltime.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lbltime.setBounds(144, 83, 217, 14);
			contentPane.add(lbltime);
			
			lblEmail.setText(email);
			lblRoom.setText(room);
			lbltime.setText(time);
			txtMess.setText(mess);
		}
		
		
	}
}
