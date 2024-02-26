package component;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.awt.BasicStroke;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;

import dao.ApartmentDao;
import model.ModelCard;

import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import javax.swing.JLayeredPane;
import java.awt.GridLayout;
import java.awt.SystemColor;

public class CardSelect extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLayeredPane layeredPane;
	private JPanel panelRenter;
	private JPanel panelRooms;
	private JLabel lblRenter;
	private JLabel lblTotalRooms;
	private JLabel ReadRenter;
	private JLabel ReadRooms;

	
	public CardSelect() {
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));
		
		layeredPane = new JLayeredPane();
		layeredPane.setOpaque(true);
		layeredPane.setBackground(SystemColor.menu);
		add(layeredPane, BorderLayout.CENTER);
		layeredPane.setLayout(new GridLayout(1, 0, 20, 0));
		
		panelRenter = new JPanel();
		panelRenter.setBackground(Color.WHITE);
		layeredPane.add(panelRenter);
		
		lblRenter = new JLabel("Total number of current renters");
		lblRenter.setForeground(Color.GRAY);
		lblRenter.setHorizontalAlignment(SwingConstants.CENTER);
		lblRenter.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		ReadRenter = new JLabel();
		ReadRenter.setHorizontalAlignment(SwingConstants.CENTER);
		ReadRenter.setFont(new Font("Tahoma", Font.PLAIN, 50));
		GroupLayout gl_panelRenter = new GroupLayout(panelRenter);
		gl_panelRenter.setHorizontalGroup(
			gl_panelRenter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelRenter.createSequentialGroup()
					.addGroup(gl_panelRenter.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelRenter.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblRenter, GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE))
						.addGroup(gl_panelRenter.createSequentialGroup()
							.addGap(160)
							.addComponent(ReadRenter, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
							.addGap(152)))
					.addContainerGap())
		);
		gl_panelRenter.setVerticalGroup(
			gl_panelRenter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelRenter.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblRenter, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(ReadRenter, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(29, Short.MAX_VALUE))
		);
		panelRenter.setLayout(gl_panelRenter);
		
		panelRooms = new JPanel();
		panelRooms.setBackground(Color.WHITE);
		layeredPane.add(panelRooms);
		
		lblTotalRooms = new JLabel("Total number of rented rooms");
		lblTotalRooms.setForeground(Color.GRAY);
		lblTotalRooms.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalRooms.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		ReadRooms = new JLabel();
		ReadRooms.setForeground(new Color(0, 128, 0));
		ReadRooms.setHorizontalAlignment(SwingConstants.CENTER);
		ReadRooms.setFont(new Font("Tahoma", Font.PLAIN, 50));
		GroupLayout gl_panelRooms = new GroupLayout(panelRooms);
		gl_panelRooms.setHorizontalGroup(
			gl_panelRooms.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelRooms.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelRooms.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelRooms.createSequentialGroup()
							.addComponent(lblTotalRooms, GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_panelRooms.createSequentialGroup()
							.addGap(154)
							.addComponent(ReadRooms, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
							.addGap(158))))
		);
		gl_panelRooms.setVerticalGroup(
			gl_panelRooms.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelRooms.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTotalRooms, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(ReadRooms, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(33, Short.MAX_VALUE))
		);
		panelRooms.setLayout(gl_panelRooms);
		var dao = new ApartmentDao();
		var daoSelct = dao.slectCard();
		setData(daoSelct);
	}
	
	public void setData(List<Object> data) {
		 int totalRenter = 0;
		 int totalRooms = 0;
		 
		 Set<Integer> totalRenterSet = new HashSet<>();
		 Set<Integer> totalRoom = new HashSet<>();
		 
		 for (int i = 0; i < data.size(); i+=3) {
		        int userId = (int) data.get(i);
		        totalRenterSet.add(userId);
		        
		        String allUserIds = (String) data.get(i + 1);
		        String[] userIdsArray = allUserIds.split(";");
		        for (String id : userIdsArray) {
		            totalRenterSet.add(Integer.parseInt(id));
		        }
		       
		        int room = (int) data.get(i+2);
		        totalRoom.add(room);
		        
		    }
		    
		totalRenter = totalRenterSet.size();
		totalRooms = totalRoom.size();
	    ReadRenter.setText(String.valueOf(totalRenter));
	    
	    ReadRooms.setText(String.valueOf(totalRooms));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//		g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
		RoundRectangle2D roundedRect = new RoundRectangle2D.Double(0, 0, getWidth() - 2,
				getHeight() - 2, 0, 0);
//		g2d.setBackground(Color.WHITE);
		g2d.setColor(Color.LIGHT_GRAY);
		g2d.setStroke(new BasicStroke(1));
		g2d.draw(roundedRect);
		
	}
}
