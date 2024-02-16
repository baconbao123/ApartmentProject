package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.time.LocalDate;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import dao.HistoryDao;

import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class History extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField textPage;
	private String module;
	private JButton filterBtn;
	private JButton Reset;
	private JDateChooser to;
	private JDateChooser from;
	private JComboBox comboPage;
	private JLabel lblDisplay;
	private static Integer pageNumber = 1;
	private static Integer rowOfPage = 20;
	private static Double totalOfPage = 0.0;
	private static Integer totalOfRow=0;
	private static Boolean fitler = false;
	private JTextField inputName;
	private HistoryDao dao = new HistoryDao();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					History frame = new History("");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public History(String module) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 918, 515);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel lblTitle = new JLabel("History");
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 255, 255));
		
		JButton btnPrev = new JButton("");
		btnPrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(pageNumber > 1) {
					pageNumber--;
				}
				loadData();
			}
		});
		btnPrev.setIcon(new ImageIcon(History.class.getResource("/icon/icons8-double-arrow-left.png")));
		
		JButton btnNext = new JButton("");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(pageNumber>=1 && pageNumber<totalOfPage.intValue()) {
					pageNumber++;
				}
				loadData();
			}
		});
		btnNext.setIcon(new ImageIcon(History.class.getResource("/icon/icons8-double-arrow-right .png")));
		
		textPage = new JTextField();
		textPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textPage.getText().matches("-?\\d+")  ) {
					if (Integer.parseInt(textPage.getText())<totalOfPage.intValue()&&Integer.parseInt(textPage.getText())>0) {
						pageNumber = Integer.parseInt(textPage.getText());
						loadData();
					}
					else {
						JOptionPane.showMessageDialog(null, "The page isn't exist");
						textPage.setText(pageNumber.toString());
					}
					
				}
				else  {
					JOptionPane.showMessageDialog(null, "The page isn't exist");
					textPage.setText(pageNumber.toString());
				}
			}
		});
		textPage.setColumns(10);
		
		lblDisplay = new JLabel("Display");
		lblDisplay.setFont(new Font("Dialog", Font.BOLD, 15));
		
		comboPage = new JComboBox();
		comboPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table!= null) {
					rowOfPage = Integer.parseInt(comboPage.getSelectedItem().toString());
					loadData();
					}

			}
		});
		comboPage.setBorder(new TitledBorder(null, "Show", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		comboPage.setModel(new DefaultComboBoxModel(new String[] {"20", "50", "100", "500"}));
		
		from = new JDateChooser();
		from.setDateFormatString("yyyy-MM-dd");
		from.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "From", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		
		to = new JDateChooser();
		to.setDateFormatString("yyyy-MM-dd");
		to.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "To", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		
		Reset = new JButton("Reset");
		Reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fitler = false;
				rowOfPage = 20;
				pageNumber = 1;
				to.setDate(null);
				inputName.setText(null);
				from.setDate(null);
				comboPage.setSelectedIndex(0);
				loadData();
				
			}
		});
		Reset.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		filterBtn = new JButton("Filter");
		filterBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fitler = true;
				loadData();
			}
		});
		filterBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		inputName = new JTextField();
		inputName.setBorder(new TitledBorder(null, "Name", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		inputName.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(403)
							.addComponent(lblTitle))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(44)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblDisplay)
									.addGap(92)
									.addComponent(btnPrev)
									.addGap(37)
									.addComponent(textPage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(32)
									.addComponent(btnNext, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(comboPage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 807, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(from, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
									.addGap(46)
									.addComponent(to, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
									.addGap(28)
									.addComponent(inputName, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
									.addGap(51)
									.addComponent(Reset)
									.addGap(18)
									.addComponent(filterBtn)))))
					.addContainerGap(41, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTitle)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(16)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(filterBtn)
								.addComponent(Reset)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(from, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
								.addComponent(to, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
								.addComponent(inputName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 291, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(14)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnPrev)
								.addComponent(lblDisplay)
								.addComponent(textPage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNext, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboPage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		this.module = module;
		table = new JTable();
		table.setBackground(new Color(255, 255, 255));
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		lblTitle.setText(module);
		init();
	}
	
	public void init() {
		var model = new DefaultTableModel(){
			@Override
			 public Class<?> getColumnClass(int column){
				 switch (column) {
				default: return String.class;
			 }
		}
		};
		model.addColumn("Module");
		model.addColumn("Operation");
		model.addColumn("Updated by");
		model.addColumn("Time");
		
		
		totalOfRow = dao.total(module,fitler,null, null, null);
		totalOfPage = Math.ceil(totalOfRow.doubleValue() / rowOfPage.doubleValue());
		lblDisplay.setText("Display  "+(pageNumber*rowOfPage-rowOfPage)+" to "+((pageNumber*rowOfPage)>totalOfRow? totalOfRow :pageNumber*rowOfPage)+ " in "+ totalOfRow + " rows");
		textPage.setText(pageNumber.toString());
		dao.selHistory(pageNumber, rowOfPage, module, null, null,inputName.getText(),fitler).stream().forEach(item -> {
			model.addRow(new Object[] {item.getModule(), item.getOperation(), item.getUpdated_by(), item.getTime()});
		});
		
		table.setModel(model);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.setRowHeight(40);
	}
	
	public void loadData() {
		var model =(DefaultTableModel) table.getModel();
		model.setRowCount(0);
		totalOfRow = dao.total(module,fitler,inputName.getText(), from.getDate(), to.getDate());
		totalOfPage = Math.ceil(totalOfRow.doubleValue() / rowOfPage.doubleValue());
		lblDisplay.setText("Display  "+(pageNumber*rowOfPage-rowOfPage)+" to "+((pageNumber*rowOfPage)>totalOfRow? totalOfRow :pageNumber*rowOfPage)+ " in "+ totalOfRow + " rows");
		textPage.setText(pageNumber.toString());
		dao.selHistory(pageNumber, rowOfPage, module, from.getDate(), to.getDate(),inputName.getText(),fitler).stream().forEach(item -> {
			model.addRow(new Object[] {item.getModule(), item.getOperation(), item.getUpdated_by(), item.getTime()});
		});
	
		table.setModel(model);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.setRowHeight(40);
	}
	
	
}
