package menu;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;

import net.miginfocom.swing.*;
import scrollbar.ScrollBarCustom;
import view.MenuAnimation;
import view.MenuItem;

import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;

import org.jdesktop.animation.timing.Animator;

import event.EventMenu;
import event.EventMenuSelected;
import event.EventShowPopupMenu;
import model.ModelMenu;
import view.MenuTop;
import javax.swing.LayoutStyle.ComponentPlacement;
import view.MenuBottom;
import view.MenuLogout;

public class Menu extends JPanel {
	private JScrollPane menuScroll;
	private JPanel panel;

	private final MigLayout layout;
	private EventMenuSelected event;
	private boolean enableMenu = true;
	private boolean showMenu = true;
	private EventShowPopupMenu eventShowPopup;
	private MenuTop menuTop;
	private MenuBottom menuBottom;

	public EventMenuSelected getEvent() {
		return event;
	}

	public void addEvent(EventMenuSelected event) {
		this.event = event;
	}

	public boolean isEnableMenu() {
		return enableMenu;
	}

	public void setEnableMenu(boolean enableMenu) {
		this.enableMenu = enableMenu;
	}

	public boolean isShowMenu() {
		return showMenu;
	}

	public void setShowMenu(boolean showMenu) {
		this.showMenu = showMenu;
	}

	public MigLayout getLayout() {
		return layout;
	}

	public EventShowPopupMenu getEventShowPopup() {
		return eventShowPopup;
	}

	public void addEventShowPopup(EventShowPopupMenu eventShowPopup) {
		this.eventShowPopup = eventShowPopup;
	}

	public Menu() {
		setOpaque(false);
		setBorder(null);

		menuScroll = new JScrollPane();
		menuScroll.setBorder(null);
		menuScroll.setOpaque(false);
		menuScroll.getViewport().setOpaque(false);
		menuScroll.setVerticalScrollBar(new ScrollBarCustom());

		menuTop = new MenuTop();
		
		menuBottom = new MenuBottom();

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(menuScroll, GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
						.addComponent(menuTop, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(menuBottom, GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(menuTop, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(menuScroll, GroupLayout.PREFERRED_SIZE, 502, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addComponent(menuBottom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(90, Short.MAX_VALUE))
		);

		layout = new MigLayout("wrap, fillx, insets 0", "[fill]", "[]0[]");

		panel = new JPanel();
		menuScroll.setViewportView(panel);
		panel.setLayout(layout);
		panel.setOpaque(false);
		setLayout(groupLayout);
	}

	public void initAdminMenu() {
		addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/icon/dashboards.png")), "Dashboard"));
		addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/icon/group.png")), "Resident Management",
				"Resident List", "Contract"));
		addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/icon/love.png")), "Apartment Management"));
		addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/icon/online-payment.png")), "Payment Management"));
		addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/icon/notification.png")), "Notifications",
				"All Notifications", "Sent", "Compose"));
		addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/icon/cogwheel.png")), "Settings"));
		
	}
	
	public void initUserMenu() {
		addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/icon/dashboards.png")), "Home"));
		addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/icon/group.png")), "Payment Management"));
		addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/icon/notification.png")), "Notifications"));
		addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/icon/cogwheel.png")), "Settings"));
	}

	// 1
	private void addMenu(ModelMenu menu) {
		panel.add(new MenuItem(menu, getEventMenu(), event, panel.getComponentCount()), "h 40!");
	}

	// 2
	private EventMenu getEventMenu() {
		return new EventMenu() {
			@Override
			public boolean menuPress(Component com, boolean open) {
				if (enableMenu) {
					if (isShowMenu()) {
						if (open) {
							new MenuAnimation(layout, com).openMenu();
						} else {
							new MenuAnimation(layout, com).closeMenu();
						}
						return true;
					} else {
						eventShowPopup.showPopup(com);
					}
				}
				return false;
			}
		};
	}

	public void hideAllMenu() {
		for (Component com : panel.getComponents()) {
			MenuItem item = (MenuItem) com;
			if (item.isOpen()) {
				new MenuAnimation(layout, com, 300).closeMenu();
				item.setOpaque(false);
			}
		}
	}
	

	
	

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		GradientPaint gp = new GradientPaint(0, 0, Color.decode("#FFFFFF"), 0, getHeight(), Color.decode("#FFFFFF"));
		g2.setPaint(gp);
		g2.fillRect(0, 0, getWidth(), getHeight());
		super.paintComponent(g);
	}
}
