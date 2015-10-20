import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.*;
import java.awt.*;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.border.EtchedBorder;
import java.awt.CardLayout;
import java.sql.*;
import javax.swing.JToolBar;
import javax.swing.MutableComboBoxModel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;


import javax.swing.border.MatteBorder;
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.Rectangle;
import javax.swing.JTable;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JCalendar;
import javax.swing.JRadioButton;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
public class Login_Frame {

	private JFrame frame;
	private JPasswordField passwordField;
	private final JButton Login_button = new JButton("");
	private Login_Panel login;
	private Connection conn=null;
	private PreparedStatement pst=null;
	private ResultSet rs=null;
	JLabel lblincorrect = new JLabel("Password Incorrect Try Again!!!!!");
	private JDateChooser dateChooser;
	private JTextField txt_airlines;
	private JTextField txt_icao;
	private JTextField txt_iata;
	private JTextField txt_adult;
	private JTextField txt_child;
	private JTextField txt_infant;
	private JTextField txt_total_seat;
	private JTextField txt_left_seat;
	private JTextField txt_others;
	private JTable table;
	private AirlinePane_Panel airlinepane;
	private SchedulePane_Panel schedulepane;
	private JPanel user;
	private JTabbedPane admin_tabbed;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private JTextField txt_s_airlines;
	private JTextField txt_depart;
	private JTextField txt_s_iata;
	private JTextField txt_arrival;
	private JTextField txt_time_depart;
	private JTextField txt_time_arrival;
	private JTable table_1;
	private IndiaPanel indiapanel;
	private String selected="";
	private int flagMap=0;
	private JTable oneway_table;
	private JTable roundtrip_table;
	private JTextField txt_o_to;
	private JTextField txt_r_to;
	private JTable table_2;
	private String SelectedAdult;
	private String SelectedChild;
	private String SelectedInfant;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				
				/*try {
					UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
					} catch (Exception e) {
					e.printStackTrace();
					}
				*/
				try {
					UIManager.setLookAndFeel("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
					
					} catch (Exception e) {
					e.printStackTrace();
					}
				
								try {
					Login_Frame window = new Login_Frame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login_Frame() {
		
		initialize();
		conn=javaconnect.Db();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		frame = new JFrame();
		frame.setTitle("Testter");
		frame.setSize(971, 771);
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		//frame.setResizable(false);
	
		login = new Login_Panel();
		frame.getContentPane().add(login, "name_8443021439300");
		login.setLayout(null);
		
		JLabel lblUserLevel = new JLabel("User Level :");
		lblUserLevel.setForeground(Color.BLACK);
		lblUserLevel.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblUserLevel.setBounds(89, 298, 100, 37);
		login.add(lblUserLevel);
		
		JComboBox <String>combo_login = new JComboBox<String>();
		combo_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(combo_login.getSelectedItem()=="Guest User")
				{
					user.setVisible(true);
					login.setVisible(false);
					
				}
			}
		});
		combo_login.setBackground(SystemColor.textHighlight);
		combo_login.setModel(new DefaultComboBoxModel<String>(new String[] {"Administrator", "Guest User"}));
		combo_login.setBounds(205, 308, 214, 20);
		login.add(combo_login);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setForeground(Color.BLACK);
		lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblPassword.setBounds(89, 372, 100, 37);
		login.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(205, 382, 219, 20);
		login.add(passwordField);
		Login_button.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				
				try{
					String sql="select * from admin where password=?";
					pst=conn.prepareStatement(sql);
					pst.setString(1,passwordField.getText());
					rs=pst.executeQuery();
					
					if(rs.next())
					{
						admin_tabbed.setVisible(true);
						airlinepane.setVisible(true);
						login.setVisible(false);
						updateTable();
					}
					else
					{
						lblincorrect.setVisible(true);
						passwordField.setText("");
					}
					
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, e);
				}
				
				
			}
		});
		Login_button.setBackground(Color.WHITE);
		Login_button.setBorderPainted(false);
		Login_button.setBorder(null);
		Login_button.setIcon(new ImageIcon("D:\\test Project\\Airline\\button.png"));
		Login_button.setBounds(297, 430, 169, 34);
		login.add(Login_button);
		lblincorrect.setForeground(Color.RED);
		
		
		lblincorrect.setVisible(false);
		lblincorrect.setBackground(Color.WHITE);
		lblincorrect.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblincorrect.setBounds(205, 357, 234, 14);
		login.add(lblincorrect);
		
		user = new JPanel();
		frame.getContentPane().add(user, "name_8483663589150");
		user.setLayout(null);
		
		JScrollPane oneway_scrollPane = new JScrollPane();
		oneway_scrollPane.setBounds(0, 246, 683, 171);
		user.add(oneway_scrollPane);
		
		oneway_table = new JTable();
		oneway_scrollPane.setViewportView(oneway_table);
		
		JScrollPane roundtrip_scrollPane = new JScrollPane();
		roundtrip_scrollPane.setBounds(686, 246, 683, 171);
		user.add(roundtrip_scrollPane);
		
		roundtrip_table = new JTable();
		roundtrip_scrollPane.setViewportView(roundtrip_table);
		
		JPanel onewayresult_panel = new JPanel();
		onewayresult_panel.setBounds(10, 428, 426, 263);
		user.add(onewayresult_panel);
		onewayresult_panel.setLayout(null);
		
		JLabel lblAdult_1 = new JLabel("Adult :");
		lblAdult_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAdult_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAdult_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblAdult_1.setBounds(10, 38, 121, 14);
		onewayresult_panel.add(lblAdult_1);
		
		JLabel lblChild_1 = new JLabel("Child :");
		lblChild_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblChild_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblChild_1.setBounds(10, 63, 121, 14);
		onewayresult_panel.add(lblChild_1);
		JLabel lblInfant = new JLabel("Infant :");
		lblInfant.setHorizontalAlignment(SwingConstants.RIGHT);
		lblInfant.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblInfant.setBounds(10, 88, 121, 14);
		onewayresult_panel.add(lblInfant);
		
		JLabel lblOtherFare = new JLabel("Other Fare :");
		lblOtherFare.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOtherFare.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblOtherFare.setBounds(10, 141, 121, 14);
		onewayresult_panel.add(lblOtherFare);
		
		JLabel lblTotalBusFare = new JLabel("Total Bus Fare :");
		lblTotalBusFare.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalBusFare.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTotalBusFare.setBounds(10, 113, 121, 14);
		onewayresult_panel.add(lblTotalBusFare);
		
		JLabel lblTotal = new JLabel("TOTAL :");
		lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotal.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblTotal.setForeground(Color.RED);
		lblTotal.setBounds(10, 197, 121, 14);
		onewayresult_panel.add(lblTotal);
		
		JLabel lblnoAdult = new JLabel("NO:A");
		lblnoAdult.setBounds(141, 40, 34, 14);
		onewayresult_panel.add(lblnoAdult);
		
		JLabel lblnoChild = new JLabel("NO:A");
		lblnoChild.setBounds(141, 65, 34, 14);
		onewayresult_panel.add(lblnoChild);
		
		JLabel lblnoInfant = new JLabel("NO:A");
		lblnoInfant.setBounds(141, 90, 34, 14);
		onewayresult_panel.add(lblnoInfant);
		
		JLabel lblX = new JLabel("X");
		lblX.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblX.setBounds(185, 40, 18, 14);
		onewayresult_panel.add(lblX);
		
		JLabel lblX_1 = new JLabel("X");
		lblX_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblX_1.setBounds(185, 65, 18, 14);
		onewayresult_panel.add(lblX_1);
		
		JLabel lblX_2 = new JLabel("X");
		lblX_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblX_2.setBounds(185, 90, 18, 14);
		onewayresult_panel.add(lblX_2);
		
		JLabel lbladultfare = new JLabel("NO:A");
		lbladultfare.setBounds(206, 40, 83, 14);
		onewayresult_panel.add(lbladultfare);
		
		JLabel lblchildfare = new JLabel("NO:A");
		lblchildfare.setBounds(206, 65, 83, 14);
		onewayresult_panel.add(lblchildfare);
		
		JLabel lblinfantfare = new JLabel("123456");
		lblinfantfare.setBounds(206, 90, 83, 14);
		onewayresult_panel.add(lblinfantfare);
		
		JLabel label_15 = new JLabel("=");
		label_15.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_15.setBounds(299, 40, 18, 14);
		onewayresult_panel.add(label_15);
		
		JLabel label_22 = new JLabel("=");
		label_22.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_22.setBounds(299, 65, 18, 14);
		onewayresult_panel.add(label_22);
		
		JLabel label_23 = new JLabel("=");
		label_23.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_23.setBounds(299, 90, 18, 14);
		onewayresult_panel.add(label_23);
		
		JLabel lbladulttotal = new JLabel("New label");
		lbladulttotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lbladulttotal.setBounds(328, 40, 83, 14);
		onewayresult_panel.add(lbladulttotal);
		
		JLabel lblchildtotal = new JLabel("New label");
		lblchildtotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblchildtotal.setBounds(327, 65, 83, 14);
		onewayresult_panel.add(lblchildtotal);
		
		JLabel lblinfanttotal = new JLabel("New label");
		lblinfanttotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblinfanttotal.setBounds(328, 90, 83, 14);
		onewayresult_panel.add(lblinfanttotal);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(328, 115, 83, 14);
		onewayresult_panel.add(lblNewLabel_1);
		
		JLabel lblotherfare = new JLabel("New label");
		lblotherfare.setHorizontalAlignment(SwingConstants.RIGHT);
		lblotherfare.setBounds(328, 143, 83, 14);
		onewayresult_panel.add(lblotherfare);
		
		JLabel lblNewLabel_3 = new JLabel("___________________");
		lblNewLabel_3.setForeground(Color.RED);
		lblNewLabel_3.setBounds(299, 168, 117, 14);
		onewayresult_panel.add(lblNewLabel_3);
		
		JLabel lbltotal = new JLabel("New label");
		lbltotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lbltotal.setBounds(277, 199, 139, 14);
		onewayresult_panel.add(lbltotal);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.setBounds(159, 213, 89, 23);
		onewayresult_panel.add(btnConfirm);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(screenSize.width/4, 11, screenSize.width/2, 191);
		user.add(tabbedPane);
		
		JPanel oneway_panel = new JPanel();
		tabbedPane.addTab("One-Way", null, oneway_panel, null);
		oneway_panel.setLayout(null);
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblFrom.setBounds(10, 11, 38, 14);
		oneway_panel.add(lblFrom);
		
		JTextArea txt_o_from = new JTextArea();
		txt_o_from.setBounds(10, 36, 156, 22);
		oneway_panel.add(txt_o_from);
		
		JButton from_map_button = new JButton("");
		from_map_button.setBounds(179, 37, 21, 23);
		oneway_panel.add(from_map_button);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblTo.setBounds(20, 78, 21, 14);
		oneway_panel.add(lblTo);
		
		txt_o_to = new JTextField();
		txt_o_to.setBounds(10, 111, 156, 20);
		oneway_panel.add(txt_o_to);
		txt_o_to.setColumns(10);
		
		JButton to_map_button = new JButton("");
		to_map_button.setBounds(179, 110, 21, 23);
		oneway_panel.add(to_map_button);
		
		JLabel lblDepartureDate = new JLabel("Departure Date");
		lblDepartureDate.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblDepartureDate.setBounds(248, 41, 100, 14);
		oneway_panel.add(lblDepartureDate);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(346, 38, 134, 20);
		oneway_panel.add(dateChooser_1);
		
		JLabel lblAdult = new JLabel("Adult (12+)");
		lblAdult.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblAdult.setBounds(493, 11, 71, 14);
		oneway_panel.add(lblAdult);
		
		JLabel lblChild = new JLabel("Child (2-11)");
		lblChild.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblChild.setBounds(493, 63, 71, 14);
		oneway_panel.add(lblChild);
		
		JLabel lblNewLabel = new JLabel("Infant (0-2)");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblNewLabel.setBounds(493, 114, 71, 14);
		oneway_panel.add(lblNewLabel);
	/*Oneway adult numbers of Passengers ComboBox operations and Variables*/
		JComboBox<String> adult_o_combo = new JComboBox<String>();
		DefaultComboBoxModel<String> adult_o_model=new DefaultComboBoxModel<String>();
		adult_o_combo.setModel(adult_o_model);
		adult_o_combo.setBounds(574, 8, 38, 20);
		oneway_panel.add(adult_o_combo);
		
		JComboBox <String>child_o_combo = new JComboBox<String>();
		DefaultComboBoxModel<String> child_o_model=new DefaultComboBoxModel<String>();
		child_o_combo.setModel(child_o_model);
		child_o_combo.setBounds(574, 60, 38, 20);
		oneway_panel.add(child_o_combo);
		
		JComboBox <String>infant_o_combo = new JComboBox<String>();
		DefaultComboBoxModel<String> infant_o_model=new DefaultComboBoxModel<String>();
		infant_o_combo.setModel(infant_o_model);
		infant_o_combo.setBounds(574, 111, 38, 20);
		oneway_panel.add(infant_o_combo);
		
		for(int i=0;i<10;i++)
		{
			String value=Integer.toString(i);
			adult_o_model.addElement(value);
			
		}
		adult_o_combo.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED)
				{
					SelectedAdult=adult_o_model.getSelectedItem().toString();
					child_o_model.removeAllElements();
					for(int i=0;i<=(9-Integer.parseInt(SelectedAdult));i++)
					{
						String value=Integer.toString(i);
						child_o_model.addElement(value);
						
					}
					infant_o_model.removeAllElements();
					for(int i=0;i<=Integer.parseInt(SelectedAdult);i++)
					{
						String value=Integer.toString(i);
						infant_o_model.addElement(value);
						
					}
				}
				
			}
		
			});
		
		
		
		
		
		JPanel roundtrip_panel = new JPanel();
		tabbedPane.addTab("Round Trip", null, roundtrip_panel, null);
		roundtrip_panel.setLayout(null);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBounds(0, 0, 783, 163);
		roundtrip_panel.add(panel_4);
		
		JLabel label_1 = new JLabel("From");
		label_1.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		label_1.setBounds(10, 11, 38, 14);
		panel_4.add(label_1);
		
		JTextArea txt_r_from = new JTextArea();
		txt_r_from.setBounds(10, 36, 156, 22);
		panel_4.add(txt_r_from);
		
		JButton button_1 = new JButton("");
		button_1.setBounds(179, 37, 21, 23);
		panel_4.add(button_1);
		
		JLabel label_4 = new JLabel("To");
		label_4.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		label_4.setBounds(20, 78, 21, 14);
		panel_4.add(label_4);
		
		txt_r_to = new JTextField();
		txt_r_to.setColumns(10);
		txt_r_to.setBounds(10, 111, 156, 20);
		panel_4.add(txt_r_to);
		
		JButton button_2 = new JButton("");
		button_2.setBounds(179, 110, 21, 23);
		panel_4.add(button_2);
		
		JLabel label_5 = new JLabel("Departure Date");
		label_5.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		label_5.setBounds(248, 41, 100, 14);
		panel_4.add(label_5);
		
		JDateChooser dateChooser_2 = new JDateChooser();
		dateChooser_2.setBounds(346, 38, 134, 20);
		panel_4.add(dateChooser_2);
		
		JLabel label_10 = new JLabel("Adult (12+)");
		label_10.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		label_10.setBounds(493, 11, 71, 14);
		panel_4.add(label_10);
		
		JLabel label_11 = new JLabel("Child (2-11)");
		label_11.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		label_11.setBounds(493, 63, 71, 14);
		panel_4.add(label_11);
		
		JLabel label_12 = new JLabel("Infant (0-2)");
		label_12.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		label_12.setBounds(493, 114, 71, 14);
		panel_4.add(label_12);
		
		JComboBox <String>adult_r_combo = new JComboBox<String>();
		DefaultComboBoxModel<String> adult_r_model=new DefaultComboBoxModel<String>();
		adult_r_combo.setModel(adult_r_model);
		adult_r_combo.setBounds(574, 8, 38, 20);
		panel_4.add(adult_r_combo);
		
		JComboBox<String> child_r_combo = new JComboBox<String>();
		DefaultComboBoxModel<String> child_r_model=new DefaultComboBoxModel<String>();
		child_r_combo.setModel(child_r_model);
		child_r_combo.setBounds(574, 60, 38, 20);
		panel_4.add(child_r_combo);
		
		JComboBox <String>infant_r_combo = new JComboBox<String>();
		DefaultComboBoxModel<String> infant_r_model=new DefaultComboBoxModel<String>();
		infant_r_combo.setModel(infant_r_model);
		infant_r_combo.setBounds(574, 111, 38, 20);
		panel_4.add(infant_r_combo);
		
		
		for(int i=0;i<10;i++)
		{
			String value=Integer.toString(i);
			adult_r_model.addElement(value);
			
		}
		adult_r_combo.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED)
				{
					SelectedAdult=adult_r_model.getSelectedItem().toString();
					child_r_model.removeAllElements();
					for(int i=0;i<=(9-Integer.parseInt(SelectedAdult));i++)
					{
						String value=Integer.toString(i);
						child_r_model.addElement(value);
						
					}
					infant_r_model.removeAllElements();
					for(int i=0;i<=Integer.parseInt(SelectedAdult);i++)
					{
						String value=Integer.toString(i);
						infant_r_model.addElement(value);
						
					}
				}
				
			}
		
			});
		
		
		
		
		
		
		
		JLabel lblReturnDate = new JLabel("Return Date");
		lblReturnDate.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblReturnDate.setBounds(248, 114, 71, 14);
		panel_4.add(lblReturnDate);
		
		JDateChooser dateChooser_3 = new JDateChooser();
		dateChooser_3.setBounds(346, 111, 134, 20);
		panel_4.add(dateChooser_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(476, 457, 365, 207);
		user.add(scrollPane);
		
		table_2 = new JTable();
		scrollPane.setViewportView(table_2);
		
		JButton btnS = new JButton("");
		btnS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				
			}
		});
		btnS.setIcon(new ImageIcon("searchflight.jpg"));
		btnS.setBackground(Color.WHITE);
		btnS.setBounds(615, 205, 146, 30);
		user.add(btnS);
		
		
		
		
		admin_tabbed = new JTabbedPane(JTabbedPane.TOP);
		admin_tabbed.setVisible(false);
		frame.getContentPane().add(admin_tabbed, "name_1107387913463");
		
		airlinepane = new AirlinePane_Panel();
		airlinepane.setFont(new Font("Tahoma", Font.BOLD, 14));
		airlinepane.setToolTipText("");
		airlinepane.setBounds(new Rectangle(0, 0, screenSize.width, screenSize.height));
		airlinepane.setBackground(new Color(255, 255, 204));
		admin_tabbed.addTab("Flight Info", null, airlinepane, null);
		airlinepane.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setOpaque(false);
		panel_2.setBounds(88, 56, 278, 500);
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 153)));
		panel_2.setBackground(Color.WHITE);
		airlinepane.add(panel_2);
		
		JLabel label = new JLabel("AIRLINE INFO");
		label.setForeground(new Color(255, 255, 255));
		label.setFont(new Font("Times New Roman", Font.BOLD, 18));
		label.setBounds(51, 26, 133, 14);
		panel_2.add(label);
		
		JLabel lblAirlines = new JLabel("Airlines:");
		lblAirlines.setForeground(new Color(102, 102, 255));
		lblAirlines.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblAirlines.setBounds(10, 51, 67, 14);
		panel_2.add(lblAirlines);
		
		txt_airlines = new JTextField();
		txt_airlines.setForeground(new Color(51, 51, 204));
		txt_airlines.setFont(new Font("Tahoma", Font.BOLD, 11));
		txt_airlines.setColumns(10);
		txt_airlines.setBounds(112, 49, 133, 20);
		panel_2.add(txt_airlines);
		
		JLabel label_2 = new JLabel("ICAO:");
		label_2.setForeground(new Color(255, 0, 0));
		label_2.setFont(new Font("Times New Roman", Font.BOLD, 15));
		label_2.setBounds(10, 152, 67, 14);
		panel_2.add(label_2);
		
		txt_icao = new JTextField();
		txt_icao.setForeground(new Color(204, 0, 0));
		txt_icao.setFont(new Font("Tahoma", Font.BOLD, 11));
		txt_icao.setColumns(10);
		txt_icao.setBounds(112, 150, 133, 20);
		panel_2.add(txt_icao);
		
		JLabel label_3 = new JLabel("IATA:");
		label_3.setForeground(new Color(153, 255, 51));
		label_3.setFont(new Font("Times New Roman", Font.BOLD, 15));
		label_3.setBounds(10, 102, 67, 14);
		panel_2.add(label_3);
		
		JLabel lblAdultFare = new JLabel("Adult Fare:");
		lblAdultFare.setForeground(new Color(255, 255, 0));
		lblAdultFare.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblAdultFare.setBounds(10, 202, 82, 14);
		panel_2.add(lblAdultFare);
		
		JLabel lblChildFare = new JLabel("Child Fare:");
		lblChildFare.setForeground(new Color(255, 51, 153));
		lblChildFare.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblChildFare.setBounds(10, 252, 82, 14);
		panel_2.add(lblChildFare);
		
		JLabel lblInfantFare = new JLabel("Infant Fare:");
		lblInfantFare.setForeground(new Color(153, 255, 204));
		lblInfantFare.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblInfantFare.setBounds(10, 302, 82, 14);
		panel_2.add(lblInfantFare);
		
		JLabel label_7 = new JLabel("Total Seat:");
		label_7.setForeground(new Color(255, 255, 255));
		label_7.setFont(new Font("Times New Roman", Font.BOLD, 15));
		label_7.setBounds(10, 352, 82, 14);
		panel_2.add(label_7);
		
		JLabel label_8 = new JLabel("Lefted Seat:");
		label_8.setForeground(new Color(255, 51, 102));
		label_8.setFont(new Font("Times New Roman", Font.BOLD, 15));
		label_8.setBounds(10, 402, 82, 14);
		panel_2.add(label_8);
		
		txt_iata = new JTextField();
		txt_iata.setForeground(new Color(0, 204, 0));
		txt_iata.setFont(new Font("Tahoma", Font.BOLD, 11));
		txt_iata.setColumns(10);
		txt_iata.setBounds(112, 100, 133, 20);
		panel_2.add(txt_iata);
		
		txt_adult = new JTextField();
		txt_adult.setForeground(new Color(0, 0, 102));
		txt_adult.setFont(new Font("Tahoma", Font.BOLD, 11));
		txt_adult.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char c=evt.getKeyChar();
				if(!(Character.isDigit(c)||c==KeyEvent.VK_BACK_SPACE||c==KeyEvent.VK_DELETE))
				{
					evt.consume();
				}
			}
		});
		txt_adult.setColumns(10);
		txt_adult.setBounds(112, 200, 133, 20);
		panel_2.add(txt_adult);
		
		txt_child = new JTextField();
		txt_child.setForeground(new Color(204, 51, 102));
		txt_child.setFont(new Font("Tahoma", Font.BOLD, 11));
		txt_child.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char c=evt.getKeyChar();
				if(!(Character.isDigit(c)||c==KeyEvent.VK_BACK_SPACE||c==KeyEvent.VK_DELETE))
				{
					evt.consume();
				}
			}
		});
		txt_child.setColumns(10);
		txt_child.setBounds(112, 250, 133, 20);
		panel_2.add(txt_child);
		
		txt_infant = new JTextField();
		txt_infant.setForeground(new Color(51, 204, 255));
		txt_infant.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		txt_infant.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char c=evt.getKeyChar();
				if(!(Character.isDigit(c)||c==KeyEvent.VK_BACK_SPACE||c==KeyEvent.VK_DELETE))
				{
					evt.consume();
				}
			}
		});
		txt_infant.setColumns(10);
		txt_infant.setBounds(112, 300, 133, 20);
		panel_2.add(txt_infant);
		
		txt_total_seat = new JTextField();
		txt_total_seat.setForeground(new Color(153, 51, 102));
		txt_total_seat.setFont(new Font("Tahoma", Font.BOLD, 11));
		txt_total_seat.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char c=evt.getKeyChar();
				if(!(Character.isDigit(c)||c==KeyEvent.VK_BACK_SPACE||c==KeyEvent.VK_DELETE))
				{
					evt.consume();
				}
			}
		});
		txt_total_seat.setColumns(10);
		txt_total_seat.setBounds(112, 350, 133, 20);
		panel_2.add(txt_total_seat);
		
		txt_left_seat = new JTextField();
		txt_left_seat.setForeground(new Color(255, 0, 204));
		txt_left_seat.setFont(new Font("Tahoma", Font.BOLD, 11));
		txt_left_seat.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char c=evt.getKeyChar();
				if(!(Character.isDigit(c)||c==KeyEvent.VK_BACK_SPACE||c==KeyEvent.VK_DELETE))
				{
					evt.consume();
				}
			}
		});
		txt_left_seat.setColumns(10);
		txt_left_seat.setBounds(112, 400, 133, 20);
		panel_2.add(txt_left_seat);
		
		JLabel label_9 = new JLabel("Others:");
		label_9.setForeground(new Color(255, 255, 102));
		label_9.setFont(new Font("Times New Roman", Font.BOLD, 15));
		label_9.setBounds(10, 452, 67, 14);
		panel_2.add(label_9);
		
		txt_others = new JTextField();
		txt_others.setForeground(new Color(0, 153, 0));
		txt_others.setFont(new Font("Tahoma", Font.BOLD, 11));
		txt_others.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				
				char c=evt.getKeyChar();
				if(!(Character.isDigit(c)||c==KeyEvent.VK_BACK_SPACE||c==KeyEvent.VK_DELETE))
				{
					evt.consume();
				}
			}
			
		});
		txt_others.setColumns(10);
		txt_others.setBounds(112, 450, 133, 20);
		panel_2.add(txt_others);
		
		JScrollPane scrollPane_info = new JScrollPane();
		scrollPane_info.setOpaque(false);
		scrollPane_info.setFont(new Font("Times New Roman", Font.BOLD, 14));
		scrollPane_info.setBorder(new TitledBorder(null, "FLIGHTINFO", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		scrollPane_info.setBounds(397, 56, 950, 500);
		airlinepane.add(scrollPane_info);
		
		table = new JTable();
		table.setForeground(new Color(51, 0, 102));
		table.setOpaque(false);
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent evt){
				if(evt.getKeyCode()==KeyEvent.VK_DOWN||evt.getKeyCode()==KeyEvent.VK_UP)
				{
					int row=table.getSelectedRow();
					String iataClicked=(table.getModel().getValueAt(row,1).toString());
					String icaoClicked=(table.getModel().getValueAt(row,2).toString());
					String sql="select * from flightinfo where iata='"+iataClicked+"' and icao ='"+icaoClicked+"'";
					try{
						
						
						
						pst=conn.prepareStatement(sql);
						rs=pst.executeQuery();
						if(rs.next())
						{
							String add1=rs.getString("airlines");
							txt_airlines.setText(add1);
							String add2=rs.getString("iata");
							txt_iata.setText(add2);
							String add3=rs.getString("icao");
							txt_icao.setText(add3);
							String add4=rs.getString("adult_fare");
							txt_adult.setText(add4);
							String add5=rs.getString("child_fare");
							txt_child.setText(add5);
							String add6=rs.getString("infant_fare");
							txt_infant.setText(add6);
							String add7=rs.getString("total_seat");
							txt_total_seat.setText(add7);
							String add8=rs.getString("left_seat");
							txt_left_seat.setText(add8);
							String add9=rs.getString("others_fare");
							txt_others.setText(add9);
							
						}
					}
					catch(Exception e)
					{
						JOptionPane.showMessageDialog(null, e);
					}

				}
			}
		});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row=table.getSelectedRow();
				String iataClicked=(table.getModel().getValueAt(row,1).toString());
				String icaoClicked=(table.getModel().getValueAt(row,2).toString());
				String sql="select * from flightinfo where iata='"+iataClicked+"' and icao ='"+icaoClicked+"'";

				try{
					
					pst=conn.prepareStatement(sql);
					rs=pst.executeQuery();
					if(rs.next())
					{
						String add1=rs.getString("airlines");
						txt_airlines.setText(add1);
						String add2=rs.getString("iata");
						txt_iata.setText(add2);
						String add3=rs.getString("icao");
						txt_icao.setText(add3);
						String add4=rs.getString("adult_fare");
						txt_adult.setText(add4);
						String add5=rs.getString("child_fare");
						txt_child.setText(add5);
						String add6=rs.getString("infant_fare");
						txt_infant.setText(add6);
						String add7=rs.getString("total_seat");
						txt_total_seat.setText(add7);
						String add8=rs.getString("left_seat");
						txt_left_seat.setText(add8);
						String add9=rs.getString("others_fare");
						txt_others.setText(add9);
						
					}
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, e);
				}
			
			}
		});
		table.setBorder(null);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		scrollPane_info.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel_1.setBounds(397, 554, 507, 78);
		airlinepane.add(panel_1);
		panel_1.setLayout(null);
		panel_1.setForeground(Color.WHITE);
		panel_1.setFont(new Font("Times New Roman", Font.BOLD, 17));
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 204)));
		panel_1.setBackground(Color.WHITE);
		
		JButton button_save = new JButton("Save");
		button_save.setFont(new Font("Times New Roman", Font.BOLD, 14));
		button_save.setIcon(new ImageIcon("D:\\test Project\\Airline\\FloppySmall.png"));
		button_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try{
					String sql="insert into flightinfo(airlines,iata,icao,adult_fare,child_fare,infant_fare,total_seat,left_seat,others_fare) values(?,?,?,?,?,?,?,?,?)";
					pst=conn.prepareStatement(sql);
					pst.setString(1, txt_airlines.getText());
					pst.setString(2, txt_iata.getText());
					pst.setString(3, txt_icao.getText());
					pst.setString(4, txt_adult.getText());
					pst.setString(5, txt_child.getText());
					pst.setString(6, txt_infant.getText());
					pst.setString(7, txt_total_seat.getText());
					pst.setString(8, txt_left_seat.getText());
					pst.setString(9, txt_others.getText());
					pst.execute();
					JOptionPane.showMessageDialog(null,"Saved");
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,e);
				}
			}
		});
		button_save.setBounds(22, 40, 89, 23);
		panel_1.add(button_save);
		
		JButton button_delete = new JButton("Delete");
		button_delete.setFont(new Font("Times New Roman", Font.BOLD, 14));
		button_delete.setIcon(new ImageIcon("D:\\test Project\\Airline\\delete.png"));
		button_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int p=JOptionPane.showConfirmDialog(null, "Do you Really Want To Delete !!","Delete",JOptionPane.YES_NO_OPTION);
				if(p==0)
				{
					try{
					String sql="delete from flightinfo where iata=? and icao =?";
					pst=conn.prepareStatement(sql);
					pst.setString(1, txt_iata.getText());
					pst.setString(2, txt_icao.getText());
					pst.execute();
					}
					catch(Exception e)
					{
						JOptionPane.showMessageDialog(null, e);
						
					}
					updateTable();
				}
				
			}
		});
		button_delete.setBounds(133, 40, 100, 23);
		panel_1.add(button_delete);
		
		JButton button_update = new JButton("Update");
		button_update.setFont(new Font("Times New Roman", Font.BOLD, 14));
		button_update.setIcon(new ImageIcon("D:\\test Project\\Airline\\update1small.png"));
		button_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String value1=txt_airlines.getText();
				//String value2=txt_iata.getText();
				//String value3=txt_icao.getText();
				String value4=txt_adult.getText();
				String value5=txt_child.getText();
				String value6=txt_infant.getText();
				String value7=txt_total_seat.getText();
				String value8=txt_left_seat.getText();
				String value9=txt_others.getText();
				String sql="update flightinfo set airlines='"+value1+"',adult_fare='"+value4+"',child_fare='"+value5+"',infant_fare='"+value6+"',total_seat='"+value7+"',left_seat='"+value8+"',others_fare='"+value9+"' where iata=? and icao=?";
				try{
					
					pst=conn.prepareStatement(sql);
					pst.setString(1, txt_iata.getText());
					pst.setString(2,txt_icao.getText());
					pst.execute();
					JOptionPane.showMessageDialog(null,"Update Successfull");
					updateTable();
					
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,e);
				}
			}
		});
		button_update.setBounds(261, 40, 111, 23);
		panel_1.add(button_update);
		
		JButton button_refresh = new JButton("Refresh");
		button_refresh.setFont(new Font("Times New Roman", Font.BOLD, 14));
		button_refresh.setIcon(new ImageIcon("D:\\test Project\\Airline\\refresh.png"));
		button_refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				txt_airlines.setText("");
				txt_iata.setText("");
				txt_icao.setText("");
				txt_adult.setText("");
				txt_child.setText("");
				txt_infant.setText("");
				txt_total_seat.setText("");
				txt_left_seat.setText("");
				txt_others.setText("");
				updateTable();
			}
		});
		button_refresh.setBounds(382, 40, 110, 23);
		panel_1.add(button_refresh);
		
		schedulepane = new SchedulePane_Panel();
		schedulepane.setBackground(Color.WHITE);
		schedulepane.setBounds(new Rectangle(0, 0, screenSize.width, screenSize.height));
		schedulepane.setName("Schedule Flight");
		admin_tabbed.addTab("Schedule Flight", null, schedulepane, null);
		schedulepane.setLayout(null);
		
		JScrollPane scrollPane_schedule = new JScrollPane();
		scrollPane_schedule.setFont(new Font("Tahoma", Font.BOLD, 13));
		scrollPane_schedule.setOpaque(false);
		scrollPane_schedule.setForeground(Color.BLACK);
		scrollPane_schedule.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "FLIGHTSCHEDULE", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 204)));
		scrollPane_schedule.setBounds(397, 56, 950, 500);
		schedulepane.add(scrollPane_schedule);
		
		table_1 = new JTable();
		table_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent evt) {
				
				if(evt.getKeyCode()==KeyEvent.VK_DOWN||evt.getKeyCode()==KeyEvent.VK_UP)
				{
					int row=table_1.getSelectedRow();
					String iataClicked=(table.getModel().getValueAt(row,1).toString());
					String sql="select airlines,iata,departure,arrival,date_depart,time_depart,time_arrival from flightinfo where iata='"+iataClicked+"'";

					try{
						
						pst=conn.prepareStatement(sql);
						rs=pst.executeQuery();
						if(rs.next())
						{
							String add1=rs.getString("airlines");
							txt_s_airlines.setText(add1);
							String add2=rs.getString("iata");
							txt_s_iata.setText(add2);
							String add3=rs.getString("departure");
							txt_depart.setText(add3);
							String add5=rs.getString("arrival");
							txt_arrival.setText(add5);
							
							   
							Date add6=rs.getDate("date_depart"); 
							dateChooser.setDate(add6);
							
							//	String add6=rs.getString("date_depart");
				        	// ((JTextField)dateChooser.getDateEditor().getUiComponent()).setText(add6);
							
							String add7=rs.getString("time_depart");
							txt_time_depart.setText(add7);
							String add8=rs.getString("time_arrival");
							txt_time_arrival.setText(add8);
							
						}
					}
					catch(Exception e)
					{
						JOptionPane.showMessageDialog(null, e);
					}
				}
				
				
			}
		});
		table_1.setForeground(new Color(51, 0, 51));
		table_1.setBorder(null);
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				int row=table_1.getSelectedRow();
				String iataClicked=(table.getModel().getValueAt(row,1).toString());
				String sql="select airlines,iata,departure,arrival,date_depart,time_depart,time_arrival from flightinfo where iata='"+iataClicked+"'";

				try{
					
					pst=conn.prepareStatement(sql);
					rs=pst.executeQuery();
					if(rs.next())
					{
						String add1=rs.getString("airlines");
						txt_s_airlines.setText(add1);
						String add2=rs.getString("iata");
						txt_s_iata.setText(add2);
						String add3=rs.getString("departure");
						txt_depart.setText(add3);
						
						String add5=rs.getString("arrival");
						txt_arrival.setText(add5);
						
						   
						Date add6=rs.getDate("date_depart"); 
						dateChooser.setDate(add6);
						
						//	String add6=rs.getString("date_depart");
			        	// ((JTextField)dateChooser.getDateEditor().getUiComponent()).setText(add6);
						
						String add7=rs.getString("time_depart");
						txt_time_depart.setText(add7);
						String add8=rs.getString("time_arrival");
						txt_time_arrival.setText(add8);
						
					}
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		scrollPane_schedule.setViewportView(table_1);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setOpaque(false);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 204)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(88, 56, 278, 500);
		schedulepane.add(panel);
		
		JLabel lblAirlineSchedule = new JLabel("AIRLINE SCHEDULE");
		lblAirlineSchedule.setForeground(Color.WHITE);
		lblAirlineSchedule.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblAirlineSchedule.setBounds(49, 11, 196, 14);
		panel.add(lblAirlineSchedule);
		
		JLabel lblAirlines_1 = new JLabel("AIRLINES:");
		lblAirlines_1.setForeground(new Color(51, 0, 255));
		lblAirlines_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblAirlines_1.setBounds(10, 51, 82, 14);
		panel.add(lblAirlines_1);
		
		txt_s_airlines = new JTextField();
		txt_s_airlines.setForeground(new Color(0, 0, 153));
		txt_s_airlines.setEditable(false);
		txt_s_airlines.setFont(new Font("Tahoma", Font.BOLD, 11));
		txt_s_airlines.setColumns(10);
		txt_s_airlines.setBounds(95, 49, 133, 20);
		panel.add(txt_s_airlines);
		
		JLabel lblDepart = new JLabel("DEPART:");
		lblDepart.setForeground(new Color(0, 255, 51));
		lblDepart.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblDepart.setBounds(10, 152, 67, 14);
		panel.add(lblDepart);
		
		txt_depart = new JTextField();
		txt_depart.setForeground(new Color(0, 102, 51));
		txt_depart.setFont(new Font("Tahoma", Font.BOLD, 11));
		txt_depart.setColumns(10);
		txt_depart.setBounds(95, 150, 133, 20);
		panel.add(txt_depart);
		
		JLabel label_6 = new JLabel("IATA:");
		label_6.setForeground(new Color(255, 255, 0));
		label_6.setFont(new Font("Times New Roman", Font.BOLD, 15));
		label_6.setBounds(10, 102, 67, 14);
		panel.add(label_6);
		
		JLabel lblArrival = new JLabel("ARRIVAL:");
		lblArrival.setForeground(new Color(255, 0, 153));
		lblArrival.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblArrival.setBounds(10, 252, 82, 14);
		panel.add(lblArrival);
		
		JLabel lblDate = new JLabel("DATE:");
		lblDate.setForeground(new Color(51, 204, 153));
		lblDate.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblDate.setBounds(10, 302, 67, 14);
		panel.add(lblDate);
		
		JLabel lblTimeDepart = new JLabel("TIME DEP:");
		lblTimeDepart.setForeground(new Color(0, 255, 0));
		lblTimeDepart.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblTimeDepart.setBounds(10, 352, 82, 14);
		panel.add(lblTimeDepart);
		
		JLabel lblTimeArrival = new JLabel("TIME ARR:");
		lblTimeArrival.setForeground(new Color(204, 0, 102));
		lblTimeArrival.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblTimeArrival.setBounds(10, 402, 88, 14);
		panel.add(lblTimeArrival);
		
		txt_s_iata = new JTextField();
		txt_s_iata.setForeground(new Color(255, 153, 0));
		txt_s_iata.setEditable(false);
		txt_s_iata.setFont(new Font("Tahoma", Font.BOLD, 11));
		txt_s_iata.setColumns(10);
		txt_s_iata.setBounds(95, 100, 133, 20);
		panel.add(txt_s_iata);
		
		txt_arrival = new JTextField();
		txt_arrival.setForeground(new Color(204, 0, 102));
		txt_arrival.setFont(new Font("Tahoma", Font.BOLD, 11));
		txt_arrival.setColumns(10);
		txt_arrival.setBounds(95, 250, 133, 20);
		panel.add(txt_arrival);
		
		txt_time_depart = new JTextField();
		txt_time_depart.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char c=evt.getKeyChar();
				if(!(Character.isDigit(c)||c==KeyEvent.VK_BACK_SPACE||c==' '||c==KeyEvent.VK_DELETE||c==':'||c==KeyEvent.VK_A||c==KeyEvent.VK_M||c==KeyEvent.VK_P))
				{
					evt.consume();
				}
				
			}
		});
		txt_time_depart.setFont(new Font("Tahoma", Font.BOLD, 11));
		txt_time_depart.setColumns(10);
		txt_time_depart.setBounds(95, 350, 133, 20);
		panel.add(txt_time_depart);
		
		txt_time_arrival = new JTextField();
		txt_time_arrival.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				
				char c=evt.getKeyChar();
				if(!(Character.isDigit(c)||c==KeyEvent.VK_BACK_SPACE||c==' '||c==KeyEvent.VK_DELETE||c==':'||c==KeyEvent.VK_A||c==KeyEvent.VK_M||c==KeyEvent.VK_P))
				{
					evt.consume();
				}
			}
		});
		txt_time_arrival.setForeground(new Color(51, 0, 102));
		txt_time_arrival.setFont(new Font("Tahoma", Font.BOLD, 11));
		txt_time_arrival.setColumns(10);
		txt_time_arrival.setBounds(95, 400, 133, 20);
		panel.add(txt_time_arrival);
		
		JButton depart_map = new JButton("");
		depart_map.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				admin_tabbed.setVisible(false);
				frame.setSize(867, 761);
				frame.setLocationRelativeTo(null);
				indiapanel.setVisible(true);
				flagMap=1;
				
				
				
			}
		});
		depart_map.setIcon(new ImageIcon("D:\\test Project\\Airline\\MAP DEPART.png"));
		depart_map.setBounds(238, 146, 21, 20);
		panel.add(depart_map);
		
		JButton arrival_map = new JButton("");
		arrival_map.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				admin_tabbed.setVisible(false);
				frame.setSize(867, 761);
				frame.setLocationRelativeTo(null);
				indiapanel.setVisible(true);
				flagMap=2;
			}
		});
		arrival_map.setIcon(new ImageIcon("D:\\test Project\\Airline\\MAP ARRIVAL.png"));
		arrival_map.setBounds(238, 246, 21, 20);
		panel.add(arrival_map);
		
		dateChooser = new JDateChooser();
		dateChooser.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 9));
		dateChooser.setFont(new Font("Tahoma", Font.BOLD, 11));
		dateChooser.setDateFormatString("dd-MMM-yyyy");
		dateChooser.setBounds(95, 302, 133, 20);
		panel.add(dateChooser);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setOpaque(false);
		panel_3.setForeground(Color.WHITE);
		panel_3.setFont(new Font("Times New Roman", Font.BOLD, 17));
		panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.BOTTOM, null, new Color(255, 255, 204)));
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(397, 556, 507, 81);
		schedulepane.add(panel_3);
		
		JButton button_5 = new JButton("");
		button_5.setOpaque(false);
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//String value3=txt_icao.getText();
				String value3=txt_depart.getText();
				
				String value5=txt_arrival.getText();
				
				
				//Date value6=dateChooser.getCalendar();
				String value6=((JTextField)dateChooser.getDateEditor().getUiComponent()).getText();
				
				String value7=txt_time_depart.getText();
				String value8=txt_time_arrival.getText();
			
				String sql="update flightinfo set departure='"+value3+"',arrival='"+value5+"',date_depart='"+value6+"',time_depart='"+value7+"',time_arrival='"+value8+"' where iata=?";
				try{
					
					pst=conn.prepareStatement(sql);
					pst.setString(1, txt_s_iata.getText());
					pst.execute();
					JOptionPane.showMessageDialog(null,"Update Successfull");
					updateTableSchedule();
					
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,e);
				}
				
			}
		});
		button_5.setIcon(new ImageIcon("update1.png"));
		button_5.setAlignmentX(Component.CENTER_ALIGNMENT);
		button_5.setFont(new Font("Times New Roman", Font.BOLD, 14));
		button_5.setBounds(213, 11, 72, 52);
		panel_3.add(button_5);
		
		JButton refresh_schedule = new JButton("");
		refresh_schedule.setIcon(new ImageIcon("refresh1.png"));
		refresh_schedule.setAlignmentX(Component.CENTER_ALIGNMENT);
		refresh_schedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateTableSchedule();
				txt_s_airlines.setText("");
				txt_s_iata.setText("");
				txt_depart.setText("");
				txt_arrival.setText("");
				dateChooser.setCalendar(null);
				txt_time_depart.setText("");
				txt_time_arrival.setText("");
				
			}
		});
		refresh_schedule.setFont(new Font("Times New Roman", Font.BOLD, 14));
		refresh_schedule.setBounds(356, 11, 72, 52);
		panel_3.add(refresh_schedule);
		
		JButton button = new JButton("");
		button.setIcon(new ImageIcon("add.png"));
		button.setBounds(65, 11, 72, 52);
		panel_3.add(button);
		
		indiapanel = new IndiaPanel();
		indiapanel.setToolTipText("");
		indiapanel.setForeground(Color.MAGENTA);
		indiapanel.setBounds(new Rectangle(0, 0,screenSize.width/2, screenSize.height));
		frame.getContentPane().add(indiapanel, "name_7897375872101");
		indiapanel.setLayout(null);
		ButtonGroup group = new ButtonGroup();
		JRadioButton tvm_rd = new JRadioButton("");
		tvm_rd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selected="Trivandrum";
			}
		});
		tvm_rd.setToolTipText("Thiruvananthapuram");
		tvm_rd.setOpaque(false);
		tvm_rd.setBounds(383, 618, 25, 25);
		indiapanel.add(tvm_rd);
		
		JRadioButton kochi_rd = new JRadioButton("");
		kochi_rd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selected="Kochi";
			}
		});
		kochi_rd.setToolTipText("Kochi");
		kochi_rd.setOpaque(false);
		kochi_rd.setBounds(374, 583, 25, 25);
		indiapanel.add(kochi_rd);
		
		JRadioButton chennai_rd = new JRadioButton("");
		chennai_rd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selected="Chennai";
			}
		});
		chennai_rd.setToolTipText("Chennai");
		chennai_rd.setOpaque(false);
		chennai_rd.setBounds(447, 515, 25, 25);
		indiapanel.add(chennai_rd);
		
		JRadioButton bang_rd = new JRadioButton("");
		bang_rd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selected="Bangalore";
			}
		});
		bang_rd.setToolTipText("Bangalore");
		bang_rd.setOpaque(false);
		bang_rd.setBounds(387, 515, 25, 25);
		indiapanel.add(bang_rd);
		
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				indiapanel.setVisible(false);
				admin_tabbed.setVisible(true);
				
				//frame.setSize(867, 750);
				frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				
			}
		});
		btnBack.setBounds(113, 478, 89, 23);
		indiapanel.add(btnBack);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				indiapanel.setVisible(false);
				admin_tabbed.setVisible(true);
				
				//frame.setSize(867, 750);
				frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				if(flagMap==1)
				{
					txt_depart.setText(selected);
				}
				else if(flagMap==2)
				{
					txt_arrival.setText(selected);
					
				}
				else{}
				
			}
		});
		btnSubmit.setBounds(604, 478, 89, 23);
		indiapanel.add(btnSubmit);
		
		JRadioButton vishakhapatanam_rd = new JRadioButton("");
		vishakhapatanam_rd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selected="Vishakhapatanam";
			}
		});
		vishakhapatanam_rd.setToolTipText("Vishakhapatanam");
		vishakhapatanam_rd.setOpaque(false);
		vishakhapatanam_rd.setBounds(518, 396, 25, 25);
		indiapanel.add(vishakhapatanam_rd);
		
		JRadioButton mumbai_rd = new JRadioButton("");
		mumbai_rd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selected="Mumbai";
			}
		});
		mumbai_rd.setToolTipText("Mumbai");
		mumbai_rd.setOpaque(false);
		mumbai_rd.setBounds(309, 372, 25, 25);
		indiapanel.add(mumbai_rd);
		
		JRadioButton delhi_rd = new JRadioButton("");
		delhi_rd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selected="Delhi";
			}
			
		});
		delhi_rd.setToolTipText("Delhi");
		delhi_rd.setOpaque(false);
		delhi_rd.setBounds(402, 172, 25, 25);
		indiapanel.add(delhi_rd);
		
		JRadioButton kolkatta_rd = new JRadioButton("");
		kolkatta_rd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selected="Kolkatta";
			}
		});
		kolkatta_rd.setOpaque(false);
		kolkatta_rd.setToolTipText("Kolkatta");
		kolkatta_rd.setBounds(616, 306, 25, 25);
		indiapanel.add(kolkatta_rd);
		
		JRadioButton jodhpur_rd = new JRadioButton("");
		jodhpur_rd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selected="Jodhpur";
			}
		});
		jodhpur_rd.setOpaque(false);
		jodhpur_rd.setToolTipText("Jodhpur");
		jodhpur_rd.setBounds(286, 221, 25, 25);
		indiapanel.add(jodhpur_rd);
		
		JRadioButton amritsar_rd = new JRadioButton("");
		amritsar_rd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selected="Amritsar";
			}
		});
		amritsar_rd.setToolTipText("Amritsar");
		amritsar_rd.setOpaque(false);
		amritsar_rd.setBounds(359, 88, 25, 25);
		indiapanel.add(amritsar_rd);
		
		JRadioButton guwahati_rd = new JRadioButton("");
		guwahati_rd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selected="Guwahati";
			}
		});
		guwahati_rd.setOpaque(false);
		guwahati_rd.setToolTipText("Guwahati");
		guwahati_rd.setBounds(655, 201, 25, 25);
		indiapanel.add(guwahati_rd);
		
		JRadioButton muscut_rd = new JRadioButton("");
		muscut_rd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selected="Muscut";
			}
		});
		muscut_rd.setOpaque(false);
		muscut_rd.setToolTipText("Muscut");
		muscut_rd.setBounds(50, 267, 25, 25);
		indiapanel.add(muscut_rd);
		
		JRadioButton colombo_rd = new JRadioButton("");
		colombo_rd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selected="Colombo";
			}
		});
		colombo_rd.setOpaque(false);
		colombo_rd.setToolTipText("Colombo");
		colombo_rd.setBounds(447, 648, 25, 25);
		indiapanel.add(colombo_rd);
		group.add(tvm_rd);
		group.add(kochi_rd);
		group.add(chennai_rd);
		group.add(bang_rd);
		group.add(vishakhapatanam_rd);
		group.add(mumbai_rd);
		group.add(delhi_rd);
		group.add(kolkatta_rd);
		group.add(jodhpur_rd);
		group.add(amritsar_rd);
		group.add(guwahati_rd);
		group.add(muscut_rd);
		group.add(colombo_rd);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmSignout = new JMenuItem("SignOut");
		mntmSignout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login.setVisible(true);
				admin_tabbed.setVisible(false);
				user.setVisible(false);
				indiapanel.setVisible(false);
				passwordField.setText("");
			}
		});
		mnFile.add(mntmSignout);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setIcon(new ImageIcon("D:\\test Project\\Airline\\close.png"));
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		mnFile.add(mntmExit);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenuItem mntmRefresh = new JMenuItem("Refresh");
		mntmRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				txt_airlines.setText("");
				txt_iata.setText("");
				txt_icao.setText("");
				txt_adult.setText("");
				txt_child.setText("");
				txt_infant.setText("");
				txt_total_seat.setText("");
				txt_left_seat.setText("");
				txt_others.setText("");
				updateTable();
			}
			
			
		});
		mnEdit.add(mntmRefresh);
	}
	

	private void updateTable()
	{
		try{
			String sql="select airlines,iata,icao,adult_fare,child_fare,infant_fare,total_seat,left_seat,others_fare from flightinfo";
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,e);
		}
	}
	private void updateTableSchedule()
	{
		try{
			String sql="select airlines,iata,departure,arrival,date_depart,time_depart,time_arrival from flightinfo";
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
			
			
			
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,e);
		}
	}
}
