package component;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingUtilities;

import java.awt.Cursor;
import view.Button;

public class Header extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel IconClose;
	private JLabel IconMinimus;
	private Button cmdMenu;

	/**
	 * Create the panel.
	 */
	public Header() {
		setOpaque(false);
		
		IconClose = new JLabel("");
		IconClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				IconCloseMouseClicked(e);
			}
		});
		IconClose.setBackground(new Color(255, 0, 0));
		IconClose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		IconClose.setIcon(new ImageIcon(Header.class.getResource("/icon/close.png")));

		IconMinimus = new JLabel("");
		IconMinimus.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				IconMinimusMouseClicked(e);
			}
		});
		IconMinimus.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		IconMinimus.setIcon(new ImageIcon(Header.class.getResource("/icon/minus.png")));
		
		cmdMenu = new Button();
		cmdMenu.setIcon(new ImageIcon(Header.class.getResource("/icon/menu.png")));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(cmdMenu, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 985, Short.MAX_VALUE)
					.addComponent(IconMinimus, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(IconClose, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(cmdMenu, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 25, Short.MAX_VALUE)
				.addComponent(IconMinimus, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
				.addComponent(IconClose, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
		);
		setLayout(groupLayout);
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		GradientPaint gp = new GradientPaint(0, 0, Color.decode("#D9D9D9"), getWidth(), 0, Color.decode("#D9D9D9"));
		g2.setPaint(gp);
		g2.fillRect(0, 0, getWidth(), getHeight());
		super.paintComponent(g);
	}
	
	protected void IconCloseMouseClicked(MouseEvent e) {
		System.exit(0);
	}
	
	protected void IconMinimusMouseClicked(MouseEvent e) {
		JFrame jf = (JFrame) SwingUtilities.getWindowAncestor(this);
		
		if(jf!=null) {
			jf.setState(JFrame.ICONIFIED);
		}
	}
	
	public void addMenuEvent(ActionListener event) {
		cmdMenu.addActionListener(event);
	}
}
