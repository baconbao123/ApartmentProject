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
import java.awt.BasicStroke;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;

import model.ModelCard;

import javax.swing.SwingConstants;

public class CardSelect extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblText;
	private JLabel lblData;

	
	public CardSelect() {
		setBackground(Color.WHITE);
		
		lblText = new JLabel("<html>Total number of rooms currently rented</html>");
		lblText.setHorizontalAlignment(SwingConstants.CENTER);
		lblText.setForeground(new Color(192, 194, 199));
		lblText.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		lblData = new JLabel("60");
		lblData.setHorizontalAlignment(SwingConstants.CENTER);
		lblData.setForeground(Color.BLACK);
		lblData.setFont(new Font("Arial", Font.BOLD, 50));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(169)
					.addComponent(lblData, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
					.addGap(166))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblText, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblText, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblData, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(24, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		
	}
	
	public void seData(ModelCard card) {
		lblText.setText(card.getText());
		lblData.setText(card.getData());
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
