package view;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JPanel;

import event.EventMenu;
import event.EventMenuSelected;
import model.ModelMenu;
import net.miginfocom.swing.MigLayout;

public class MenuItem extends JPanel {

	private static final long serialVersionUID = 1L;

	private float alpha;
	private ModelMenu menu;
	private boolean open;
	private EventMenuSelected eventSelected;
	private int index;
	private boolean firstTime = true;
	private boolean reopened = false;

	public float getAlpha() {
		return alpha;
	}

	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}

	public ModelMenu getMenu() {
		return menu;
	}

	public void setMenu(ModelMenu menu) {
		this.menu = menu;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public EventMenuSelected getEventSelected() {
		return eventSelected;
	}

	public void setEventSelected(EventMenuSelected eventSelected) {
		this.eventSelected = eventSelected;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public MenuItem(ModelMenu menu, EventMenu event, EventMenuSelected eventSelected, int index) {
		this.menu = menu;
		this.eventSelected = eventSelected;
		this.index = index;
		setOpaque(false);
		setLayout(new MigLayout("wrap, fillx, insets 0", "[fill]", "[fill, 30!]0[fill, 35!]"));
		MenuButton firstItem = new MenuButton(menu.getIcon(), "      " + menu.getMenuName()); // spacing between icon
																								// and text
		firstItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				if (menu.getSubMenu().length > 0) {
					if (event.menuPress(MenuItem.this, !open)) {
						open = !open;
					}
				}
				eventSelected.menuSelected(index, -1);
			}

		});
		
		// lưu lại trạng thái open, để khi ấn 1 lần vào firstItem thì show ra toàn subMenu
		addComponentListener((ComponentListener) new ComponentAdapter() {
	        @Override
	        public void componentResized(ComponentEvent e) {
	            if (!firstTime) {
	                if (getWidth() > 0 && getHeight() > 0) {
	                    // Update the temporary 'open' state based on the new size
	                    boolean tempOpen = (getHeight() > 40);
	                    if (open != tempOpen) {
	                        open = tempOpen;
	                        // Repaint the MenuItem
	                        revalidate();
	                        repaint();
	                    }
	                }
	            }
	            firstTime = false;
	        }
	    });
		add(firstItem);
		int subMenuIndex = -1;

		// show subMenu
		for (String st : menu.getSubMenu()) {
			MenuButton item = new MenuButton(st);
			item.setIndex(++subMenuIndex);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent ae) {
					eventSelected.menuSelected(index, item.getIndex());
				}
			});
			add(item);
		}
		
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		int width = getWidth();
		int height = getPreferredSize().height;
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(new Color(236, 236, 236)); // color when dropdown show submenu
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		g2.fillRect(0, 2, width, 38); // draw color firstItem
		g2.setComposite(AlphaComposite.SrcOver);
		g2.fillRect(0, 40, width, height - 40); // draw color subMenu
		g2.setColor(new Color(100, 100, 100)); // draw color line vertical
		g2.drawLine(30, 40, 30, height - 17); // draw color line vertical
		for (int i = 0; i < menu.getSubMenu().length; i++) {
			int y = ((i + 1) * 35 + 31) - 17;
			g2.drawLine(30, y, 38, y);
		}
		if (menu.getSubMenu().length > 0) {
			createArrowButton(g2);
		}

		super.paintComponent(g);
	}

	private void createArrowButton(Graphics2D g2) {
		int size = 4;
		int y = 15;
		int x = 215;
		g2.setColor(new Color(0, 0, 0));
		float ay = alpha * size;
		float ay1 = (1f - alpha) * size;
		g2.drawLine(x, (int) (y + ay), x + 4, (int) (y + ay1));
		g2.drawLine(x + 4, (int) (y + ay1), x + 8, (int) (y + ay));
	}

}
