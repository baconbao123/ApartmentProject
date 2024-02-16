package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

import event.EventMenuSelected;
import net.miginfocom.swing.MigLayout;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowEvent;

public class MenuPopup extends JDialog {
	private Animator animator;
	private boolean show = true;

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private MenuPopupPanel panel;

	public MenuPopup(JFrame parent, int index, EventMenuSelected eventSelected, String... subMenu) {
		super(parent, false);
		
		addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent e) {
			}
			public void windowLostFocus(WindowEvent e) {
				thisWindowLostFocus(e);
			}
		});
		setUndecorated(true);
		panel = new MenuPopupPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		
		setOpacity(0f);
		setBackground(new Color(0, 0, 0, 0));
		panel.setLayout(new MigLayout("fill, wrap", "8[fill, 120]0", "0[35, fill]0[35, fill]0"));
		int subMenuIndex = -1;
		for(String st: subMenu) {
			MenuButton item = new MenuButton(st, true);
			item.setIndex(++subMenuIndex);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					eventSelected.menuSelected(index, item.getIndex());
				}
			});
			panel.add(item);
			setSize(new Dimension(200, 35*subMenu.length));
		}
		TimingTarget target = new TimingTargetAdapter() {
			@Override
			public void timingEvent(float fraction) {
				if(show) {
					setOpacity(fraction);
				} else {
					setOpacity(1f - fraction);
				}
				super.timingEvent(fraction);
			}
			
			@Override
			public void end() {
				if(show == false) {
					setVisible(false);
				}
			}
		};
		animator = new Animator(200, target);
		animator.setResolution(0);
		animator.setAcceleration(0.5f);
	}
	
	@Override
	public void setVisible(boolean bin) {
		super.setVisible(bin);
		if(show) {
			animator.start();
		}
	}
	
	private void closeMenu() {
		if(animator.isRunning()) {
			animator.stop();
		}
		show = false;
		animator.start();
	}

	protected void thisWindowLostFocus(WindowEvent e) {
		closeMenu();
	}
}
