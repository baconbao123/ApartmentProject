package view;

//import javax.swing.JPanel;
//import javax.swing.JButton;
//import javax.swing.JLabel;
//import java.awt.BorderLayout;
//import java.awt.Color;
//import javax.swing.GroupLayout;
//import javax.swing.GroupLayout.Alignment;
//import javax.swing.ImageIcon;
//
//public class MenuLoggout extends JPanel {
//    private static final long serialVersionUID = 1L;
//    private JButton btnLoggout;
//
//    public MenuLoggout() {
//        setOpaque(false);
//        setBounds(0, 0, 230, 50);
//        setLayout(new BorderLayout(0, 0));
//
//        btnLoggout = new JButton("Logout");
//        btnLoggout.setBackground(new Color(247, 248, 249));
//        btnLoggout.setBorder(null);
//        btnLoggout.setIcon(new ImageIcon(MenuLoggout.class.getResource("/icon/Left icon.png")));
//        btnLoggout.setFocusable(false);
//        add(btnLoggout);
//    }
//}
import javax.swing.*;

import event.EventMenu;
import event.EventMenuSelected;
import model.ModelMenu;
import net.miginfocom.swing.MigLayout;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuLogout extends JPanel {
    private static final long serialVersionUID = 1L;
    private JButton btnLogout;
    
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

    public MenuLogout(ModelMenu menu, EventMenu event, EventMenuSelected eventSelected, int index) {
    	this.menu = menu;
		this.eventSelected = eventSelected;
		this.index = index;
		
        setOpaque(false);
        setBounds(0, 0, 230, 50);
        
        setLayout(new MigLayout("wrap, fillx, insets 0", "[fill]", "[fill, 30!]0[fill, 35!]"));
		MenuButton firstItem = new MenuButton(new ImageIcon(
			new ImageIcon(getClass().getResource("icon/logout_1.png")).getImage()
				), "      " + "Logout"); 
		firstItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				if (menu.getSubMenu().length > 0) {
					if (event.menuPress(MenuLogout.this, !open)) {
						open = !open;
					}
				}
				eventSelected.menuSelected(index, -1);
			}

		});
		add(firstItem);
		int subMenuIndex = -1;
        
    }
}

