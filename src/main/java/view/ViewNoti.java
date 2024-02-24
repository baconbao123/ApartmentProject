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
	private JLabel lblTime;
	private JLabel lblNewLabel;
	private JLabel lbltime;
	private JLabel lblNewLabel_1;

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
	public ViewNoti(String email, String time, String mess) {
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
			lblEmail.setBounds(109, 42, 263, 27);
			contentPane.add(lblEmail);
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
			lblNewLabel.setBounds(50, 49, 57, 14);
			contentPane.add(lblNewLabel);
		}
		{
			lbltime = new JLabel();
			lbltime.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lbltime.setBounds(144, 83, 217, 14);
			contentPane.add(lbltime);
			
			lblEmail.setText("< "+email+" >");
			lbltime.setText(time);
			txtMess.setText(mess);
		}
		{
			lblNewLabel_1 = new JLabel("Notification information");
			lblNewLabel_1.setForeground(new Color(0, 128, 255));
			lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNewLabel_1.setBounds(49, 11, 172, 20);
			contentPane.add(lblNewLabel_1);
		}
		
		
	}
}
