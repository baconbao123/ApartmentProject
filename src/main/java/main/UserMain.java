package main;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

import component.Header;
import component.Login;
import event.EventMenuSelected;
import event.EventShowPopupMenu;
import formUser.Home;
import formUser.MainForm;
import formUser.NotiUser;
import formUser.PaymentUser;
import formUser.Report;
import formUser.SettingUser;
import menu.Menu;
import net.miginfocom.swing.MigLayout;
import view.MenuItem;
import view.MenuPopup;

public class UserMain extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private MigLayout migLayout;
	private JLayeredPane layeredBg;
	private MainForm mainForm;
	private Menu menu;
	private Header header;
	private Animator animator;
	private AdminMain adMain = new AdminMain();
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

	public AdminMain getAdMain() {
		return adMain;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserMain frame = new UserMain();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.adMain.initMoving(frame);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UserMain() {
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

		layeredBg.add(menu, "w 230!, spany 2");
		layeredBg.add(header, "h 25!, wrap");
		layeredBg.add(mainForm, "w 100%, h 100%");
		
		menu.addEvent(new EventMenuSelected() {
			
			@Override
			public void menuSelected(int menuIndex, int subMenuIndex) {
				System.out.println("Menu Index: " + menuIndex);
				if (menuIndex == 0) {
					mainForm.showForm(new Home());
				} else if (menuIndex == 1) {
					mainForm.showForm(new PaymentUser());
				} else if (menuIndex == 2) {
					mainForm.showForm(new Report());
				} else if (menuIndex == 3) {
					mainForm.showForm(new NotiUser());
				} else if (menuIndex == 4) {
					mainForm.showForm(new SettingUser());
				}

			}
		});
		menu.addEventShowPopup(new EventShowPopupMenu() {

			@Override
			public void showPopup(Component com) {
				MenuItem item = (MenuItem) com;
				MenuPopup popup = new MenuPopup(UserMain.this, item.getIndex(), item.getEventSelected(),
						item.getMenu().getSubMenu());
				int x = UserMain.this.getX() + 52;
				int y = UserMain.this.getY() + com.getY() + 86;
				popup.setLocation(x, y);
				popup.setVisible(true);
			}
		});
		
		menu.initUserMenu();
		
		TimingTarget target = new TimingTargetAdapter() {
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
	}

}
