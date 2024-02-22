package main;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

import component.Header;
import component.Login;
import event.EventMenuSelected;
import event.EventShowPopupMenu;

import formAdmin.Bill;
import formAdmin.ContractList;
import formAdmin.Dashboard;
import formAdmin.Notification;
import formAdmin.RenterList;
import formUser.*;

import formAdmin.Setting;
import formAdmin.ApartmentList;
import formAdmin.Dashboard;
import formUser.MainForm;
import menu.Menu;
import net.miginfocom.swing.MigLayout;
import view.MenuItem;
import view.MenuPopup;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JLayeredPane;

public class AdminMain extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private MigLayout migLayout;
	private JLayeredPane layeredBg;
	private MainForm mainForm;
	private Menu menu;
	private Header header;
	private Animator animator;
	private Login login;
	
	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminMain frame = new AdminMain();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.initMoving(frame);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminMain() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1330, 825);
		contentPane = new JPanel();
		contentPane.setOpaque(false);
		contentPane.setBorder(null);

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		layeredBg = new JLayeredPane();
		contentPane.add(layeredBg, BorderLayout.CENTER);

		init();
	}

	private void init() {
		migLayout = new MigLayout("fill", "0[]0[100%, fill]0", "0[fill, top]0");
		layeredBg.setLayout(migLayout);
		menu = new Menu();
		header = new Header();
		mainForm = new MainForm();

		// add menu index
		menu.addEvent(new EventMenuSelected() {

			@Override
			public void menuSelected(int menuIndex, int subMenuIndex) {
				System.out.println("Menu Index: " + menuIndex + " subMenu Index: " + subMenuIndex);
				
				switch (menuIndex) {
				case 0: {
					 mainForm.showForm(new Dashboard());
					 break;
				}
				case 1: {
					switch (subMenuIndex) {
					case 0: {
						mainForm.showForm(new RenterList());
						break;
					}
					case 1: {
						mainForm.showForm(new ContractList());
					}
					default:
						break;
					}
					break;
				}
				case 2: 
					mainForm.showForm(new ApartmentList());
					break;
				case 3: {
					mainForm.showForm(new Bill());
					break;
				}
				case 4: {
					switch (subMenuIndex) {
					case 0:
					{
						mainForm.showForm(new Notification());
						break;
					}
					default:
						break;
					}
					break;
				}
				case 5: {
					switch (subMenuIndex) {
					case 0:
					{
						
						break;
					}
					default:
						mainForm.showForm(new Setting());
						break;
					}
					break;
				}
				default:
					mainForm.showForm(new Dashboard());
					break;
				}

			
			}
		});
		menu.addEventShowPopup(new EventShowPopupMenu() {
			@Override
			public void showPopup(Component com) {
				MenuItem item = (MenuItem) com;
				MenuPopup popup = new MenuPopup(AdminMain.this, item.getIndex(), item.getEventSelected(),
						item.getMenu().getSubMenu());
				int x = AdminMain.this.getX() + 52;
				int y = AdminMain.this.getY() + com.getY() + 86;
				popup.setLocation(x, y);
				popup.setVisible(true);
			}
		});

		menu.initAdminMenu();

		layeredBg.add(menu, "w 230!, spany 2"); // span y 2 cell
		layeredBg.add(header, "h 25!, wrap");
		layeredBg.add(mainForm, "w 100%, h 100%");

		// show or hide sidebar
		TimingTarget target = new TimingTargetAdapter() {
			@Override
			public void timingEvent(float fraction) {
				double width;
				if (menu.isShowMenu()) {
					width = 60 + (170 * (1f - fraction));
				} else {
					width = 60 + (170 * fraction);
				}
				migLayout.setComponentConstraints(menu, "w " + width + "!, spany 2");
				menu.revalidate();
			}

			@Override
			public void end() {
				menu.setShowMenu(!menu.isShowMenu());
				menu.setEnableMenu(true); // hide submenu if close menu
			}
		};
		animator = new Animator(800, target);
		animator.setResolution(0);
		animator.setDeceleration(0.5f);
		animator.setAcceleration(0.5f);
		header.addMenuEvent(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!animator.isRunning()) {
					animator.start();
				}
				// hide all submenu
				menu.setEnableMenu(false); // hide submenu if close menu
				if (menu.isShowMenu()) {
					menu.hideAllMenu();
				}
			}
		});
		//start with this form
		mainForm.showForm(new Dashboard());
	}
	
	private int x;
	private int y;
	
	public void initMoving(JFrame frame) {
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				x = me.getXOnScreen() - frame.getX();
			}
		});
		
		addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {};
			
			@Override
			public void mouseDragged(MouseEvent e) {
				frame.setLocation(e.getXOnScreen() - x, e.getYOnScreen() - y);
			}
		});
	}

}
