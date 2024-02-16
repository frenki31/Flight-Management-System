package flight;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.google.gson.Gson;
import com.toedter.calendar.JDateChooser;
import com.google.gson.reflect.TypeToken;
import inheritance.Flight;
import inheritance.Passenger;

/**
 * Class NewReservation allows the user to choose the flight,price and enter all personal and contact details
 * @author user
 *
 */
public class NewReservation extends JFrame {
	Display main = new Display();
	private JTextField txtCvv = null;
    private final JTextField textFieldFName, textFieldLName, textFieldResidence, textFieldPhone,
			textFieldCountry,textFieldEmail, textFieldCity, textFieldStreet, textFieldCard, textField;
	private final JLabel message, message1, label_2, labelPricePromo, labelPriceEco, labelBusiness,
			label_3, label_5, label_7, label_12, label_14, label_16, labeLTotal,label_23,label_25;
	private final JRadioButton radioButton, radioButton_2, radioButton_5, rdbtnSelectFlight;
	private JRadioButton radioButton_1 = null;
	private JRadioButton radioButton_3,radioButton_4, radioButton_6, radioButton_7;
	private final JComboBox comboBox, comboBox_1;
	private JCheckBox chckbxIHaveRead;
	private JLabel Message;
    Flight flight;
	double flight_price = 0;
	Passenger passenger;
	ArrayList<Passenger> passengers = new ArrayList<>();


	/**
	 * Launch the application.
	 * Function to read from the file
	 */
	public ArrayList<Flight> getData() {
		String yourFile = "flights.json";
		File tmpDir = new File(yourFile);
		boolean exists = tmpDir.exists();
		try {
			java.lang.reflect.Type listType = new TypeToken<ArrayList<Flight>>() {}.getType();
			return getFlights(exists, listType);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ArrayList<>();
		}
	}

	static ArrayList<Flight> getFlights(boolean exists, java.lang.reflect.Type listType) throws IOException {
		return Admin.getFlights(exists, listType);
	}

	/**
	 * Function to write to the temporary file
	 *
	 * @param flight
	 */
	protected boolean write_to_temp_file(Flight flight) {
		return tempFlight(flight);
	}
	static boolean tempFlight(Flight flight){
		String yourFile = "temp_flight.json";
		File tmpDir = new File(yourFile);
		boolean exists = tmpDir.exists();

		try {
			FileWriter fileWriter;
			if (!exists) {
				fileWriter = new FileWriter("temp_flight.json");
				fileWriter.close();
			}
			new FileReader("temp_flight.json");
			Gson gson = new Gson();

			fileWriter = new FileWriter("temp_flight.json");

			gson.toJson(flight, fileWriter);
			fileWriter.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return exists;
	}
	/**
	 * Function to write to the main file
	 *
	 * @param flight
	 */
	protected boolean write_to_file(Flight flight) {
		String yourFile = "flights.json";
		File tmpDir = new File(yourFile);
		boolean exists = tmpDir.exists();
		try {
			System.out.println(flight.getPassengers());
			FileWriter fileWriter;
			if (!exists) {
                fileWriter = new FileWriter("flights.json");
            }
			Reader reader = new FileReader("flights.json");
			Gson gson = new Gson();
			java.lang.reflect.Type listType = new TypeToken<ArrayList<Flight>>() {}.getType();
			ArrayList<Flight> p;

            p = gson.fromJson(reader, listType);
            if (p == null) {
                p = new ArrayList<>();
            }
            p.add(flight);
			fileWriter = new FileWriter("flights.json");

			gson.toJson(p, fileWriter);
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return exists;
	}
	/**
	 * Create the frame.
	 */
	public NewReservation() {
		String yourFile = "temp_flight.json";

		File tmpDir = new File(yourFile);
		boolean exists = tmpDir.exists();
		try {
			FileWriter fileWriter;
			if (!exists) {
				fileWriter = new FileWriter("temp_flight.json");
				fileWriter.close();
			}
			Reader reader = new FileReader("temp_flight.json");
			Gson gson = new Gson();

			this.flight = gson.fromJson(reader, Flight.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String mcdep = "";
		if(flight.getType_of_flight().equals("Multi City Trip")) {
			mcdep = flight.getMulti_city_from();
		}
		else {
			mcdep = flight.getTo();
		}
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1280, 760);
        JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 153, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblX.setBackground(Color.RED);
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				lblX.setBackground(new Color(204, 0, 0));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblX.setOpaque(true);
		lblX.setBorder(new LineBorder(Color.GRAY, 1, true));
		lblX.setBackground(new Color(204, 0, 0));
		lblX.setForeground(Color.WHITE);
		lblX.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setBounds(1240, 0, 40, 20);
		contentPane.add(lblX);

		JLabel lblNewLabel = new JLabel("   FMS 1.02");
		lblNewLabel.setBackground(new Color(204, 204, 255));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 13));
		lblNewLabel.setBounds(0, 0, 1240, 20);
		contentPane.add(lblNewLabel);

		JLabel lblAirways = new JLabel("AIRWAYS");
		lblAirways.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblAirways.setBounds(145, 51, 161, 40);
		contentPane.add(lblAirways);

		JLabel lblFlyhighAirways = new JLabel("FLYHIGH");
		lblFlyhighAirways.setFont(new Font("Baskerville Old Face", Font.BOLD | Font.ITALIC, 33));
		lblFlyhighAirways.setBackground(new Color(153, 153, 255));
		lblFlyhighAirways.setBounds(26, 19, 182, 51);
		contentPane.add(lblFlyhighAirways);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setOpaque(true);
		tabbedPane.setBounds(0, 100, 1280, 560);
		contentPane.add(tabbedPane);

		JPanel RoundAndMulti = new JPanel();
		RoundAndMulti.setPreferredSize(new Dimension(5, 5));
		tabbedPane.addTab("Flights", null, RoundAndMulti, null);
		tabbedPane.setForegroundAt(0, new Color(0, 0, 0));
		tabbedPane.setEnabledAt(0, true);
		RoundAndMulti.setLayout(null);

		JLabel lblFirstFlight = new JLabel("First flight");
		lblFirstFlight.setFont(new Font("Century Gothic", Font.BOLD, 30));
		lblFirstFlight.setBounds(40, 38, 600, 40);
		RoundAndMulti.add(lblFirstFlight);

		JLabel labelSecondFlight = new JLabel("Second Flight");
		labelSecondFlight.setFont(new Font("Century Gothic", Font.BOLD, 30));
		labelSecondFlight.setBounds(40, 275, 600, 40);
		RoundAndMulti.add(labelSecondFlight);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.LIGHT_GRAY);
		panel_3.setBounds(40, 90, 605, 150);
		RoundAndMulti.add(panel_3);
		panel_3.setLayout(null);

		JPanel panelPromo = new JPanel();
		panelPromo.setBackground(new Color(204, 102, 0));
		panelPromo.setBounds(155, 7, 140, 136);
		panel_3.add(panelPromo);
		panelPromo.setLayout(null);

		rdbtnSelectFlight = new JRadioButton("Select flight");
		rdbtnSelectFlight.addActionListener(new ActionListener() {
			/**
			 * Check which radio button is selected to display the price
			 */
			public void actionPerformed(ActionEvent e) {
				rdbtnSelectFlight.setSelected(true);
				radioButton.setSelected(false);
				radioButton_1.setSelected(false);
				getFlightPrice();
			}
		});
		rdbtnSelectFlight.setBackground(new Color(204, 102, 0));
		rdbtnSelectFlight.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		rdbtnSelectFlight.setHorizontalAlignment(SwingConstants.RIGHT);
		rdbtnSelectFlight.setHorizontalTextPosition(SwingConstants.LEADING);
		rdbtnSelectFlight.setBounds(31, 7, 109, 23);
		panelPromo.add(rdbtnSelectFlight);

        Random random = new Random();
        labelPricePromo = new JLabel(String.format("%.2f$", (random.nextDouble() * 80 + 20)));
		labelPricePromo.setFont(new Font("Century Gothic", Font.BOLD, 13));
		labelPricePromo.setBounds(70, 40, 60, 23);
		panelPromo.add(labelPricePromo);

		JLabel lblBestPrice = new JLabel("Economy Class *");
		lblBestPrice.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		lblBestPrice.setBounds(10, 102, 120, 23);
		panelPromo.add(lblBestPrice);

		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBackground(new Color(255, 51, 0));
		panel_5.setBounds(305, 7, 140, 136);
		panel_3.add(panel_5);

		radioButton = new JRadioButton("Select flight");
		radioButton.addActionListener(e -> {
            rdbtnSelectFlight.setSelected(false);
            radioButton.setSelected(true);
            radioButton_1.setSelected(false);
            getFlightPrice();
        });
		radioButton.setHorizontalTextPosition(SwingConstants.LEADING);
		radioButton.setHorizontalAlignment(SwingConstants.RIGHT);
		radioButton.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		radioButton.setBackground(new Color(255, 51, 0));
		radioButton.setBounds(31, 7, 109, 23);
		panel_5.add(radioButton);

		labelPriceEco = new JLabel(String.format("%.2f$", (random.nextDouble() * 900 + 100)));
		labelPriceEco.setFont(new Font("Century Gothic", Font.BOLD, 13));
		labelPriceEco.setBounds(70, 40, 60, 23);
		panel_5.add(labelPriceEco);

		JLabel lblB = new JLabel("Business Class **");
		lblB.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		lblB.setBounds(10, 102, 120, 23);
		panel_5.add(lblB);

		JPanel panel_6 = new JPanel();
		panel_6.setLayout(null);
		panel_6.setBackground(Color.GRAY);
		panel_6.setBounds(455, 7, 140, 136);
		panel_3.add(panel_6);

		radioButton_1 = new JRadioButton("Select flight");
		radioButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnSelectFlight.setSelected(false);
				radioButton.setSelected(false);
				radioButton_1.setSelected(true);
				getFlightPrice();
			}
		});
		radioButton_1.setHorizontalTextPosition(SwingConstants.LEADING);
		radioButton_1.setHorizontalAlignment(SwingConstants.RIGHT);
		radioButton_1.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		radioButton_1.setBackground(Color.GRAY);
		radioButton_1.setBounds(31, 7, 109, 23);
		panel_6.add(radioButton_1);

		labelBusiness = new JLabel(String.format("%.2f$", (random.nextDouble() * 9000 + 1000)));
		labelBusiness.setFont(new Font("Century Gothic", Font.BOLD, 13));
		labelBusiness.setBounds(70, 40, 60, 23);
		panel_6.add(labelBusiness);

		JLabel lblLuxury = new JLabel("First Class ***");
		lblLuxury.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		lblLuxury.setBounds(10, 102, 120, 23);
		panel_6.add(lblLuxury);

		JLabel lblNewLabel_2 = new JLabel("From: "+ flight.getFrom());
		lblNewLabel_2.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(10, 39, 135, 14);
		panel_3.add(lblNewLabel_2);

		JLabel label= new JLabel("To: "+flight.getTo());
		label.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		label.setBounds(10, 64, 135, 14);
		panel_3.add(label);

		JLabel label_1 = new JLabel(flight.getFirst_flight());
		label_1.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		label_1.setBounds(10, 89, 135, 14);
		panel_3.add(label_1);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.LIGHT_GRAY);
		panel_4.setLayout(null);
		panel_4.setBounds(40, 326, 605, 150);
		RoundAndMulti.add(panel_4);

		JPanel panel_7 = new JPanel();
		panel_7.setLayout(null);
		panel_7.setBackground(new Color(204, 102, 0));
		panel_7.setBounds(155, 7, 140, 136);
		panel_4.add(panel_7);

		radioButton_2 = new JRadioButton("Select flight");
		radioButton_2.addActionListener(e -> {
            radioButton_2.setSelected(true);
            radioButton_3.setSelected(false);
            radioButton_4.setSelected(false);
            getFlightPrice();
        });
		radioButton_2.setHorizontalTextPosition(SwingConstants.LEADING);
		radioButton_2.setHorizontalAlignment(SwingConstants.RIGHT);
		radioButton_2.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		radioButton_2.setBackground(new Color(204, 102, 0));
		radioButton_2.setBounds(31, 7, 109, 23);
		panel_7.add(radioButton_2);

		label_3 = new JLabel(String.format("%.2f$", (random.nextDouble() * 80 + 20)));
		label_3.setFont(new Font("Century Gothic", Font.BOLD, 13));
		label_3.setBounds(70, 40, 60, 23);
		panel_7.add(label_3);

		JLabel lblEconomyClass = new JLabel("Economy Class *");
		lblEconomyClass.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		lblEconomyClass.setBounds(10, 102, 120, 23);
		panel_7.add(lblEconomyClass);

		JPanel panel_8 = new JPanel();
		panel_8.setLayout(null);
		panel_8.setBackground(new Color(255, 51, 0));
		panel_8.setBounds(305, 7, 140, 136);
		panel_4.add(panel_8);

		radioButton_3 = new JRadioButton("Select flight");
		radioButton_3.addActionListener(e -> {
            radioButton_2.setSelected(false);
            radioButton_3.setSelected(true);
            radioButton_4.setSelected(false);
            getFlightPrice();
        });
		radioButton_3.setHorizontalTextPosition(SwingConstants.LEADING);
		radioButton_3.setHorizontalAlignment(SwingConstants.RIGHT);
		radioButton_3.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		radioButton_3.setBackground(new Color(255, 51, 0));
		radioButton_3.setBounds(31, 7, 109, 23);
		panel_8.add(radioButton_3);

		label_5 = new JLabel(String.format("%.2f$", (random.nextDouble() * 900 + 100)));
		label_5.setFont(new Font("Century Gothic", Font.BOLD, 13));
		label_5.setBounds(70, 40, 60, 23);
		panel_8.add(label_5);

		JLabel lblBusinessClass = new JLabel("Business Class **");
		lblBusinessClass.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		lblBusinessClass.setBounds(10, 102, 120, 23);
		panel_8.add(lblBusinessClass);

		JPanel panel_9 = new JPanel();
		panel_9.setLayout(null);
		panel_9.setBackground(Color.GRAY);
		panel_9.setBounds(455, 7, 140, 136);
		panel_4.add(panel_9);

		radioButton_4 = new JRadioButton("Select flight");
		radioButton_4.addActionListener(e -> {
            radioButton_2.setSelected(false);
            radioButton_3.setSelected(false);
            radioButton_4.setSelected(true);
            getFlightPrice();
        });
		radioButton_4.setHorizontalTextPosition(SwingConstants.LEADING);
		radioButton_4.setHorizontalAlignment(SwingConstants.RIGHT);
		radioButton_4.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		radioButton_4.setBackground(Color.GRAY);
		radioButton_4.setBounds(31, 7, 109, 23);
		panel_9.add(radioButton_4);

		label_7 = new JLabel(String.format("%.2f$", (random.nextDouble() * 9000 + 1000)));
		label_7.setFont(new Font("Century Gothic", Font.BOLD, 13));
		label_7.setBounds(70, 40, 60, 23);
		panel_9.add(label_7);

		JLabel lblFirstClass = new JLabel("First Class ***");
		lblFirstClass.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		lblFirstClass.setBounds(10, 102, 120, 23);
		panel_9.add(lblFirstClass);

		JLabel label_9 = new JLabel("From: "+mcdep);
		label_9.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		label_9.setBounds(10, 39, 135, 14);
		panel_4.add(label_9);

		JLabel label_10 = new JLabel("To: "+flight.getFrom());
		label_10.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		label_10.setBounds(10, 64, 135, 14);
		panel_4.add(label_10);

		JLabel label_11 = new JLabel(flight.getSecond_flight());
		label_11.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		label_11.setBounds(10, 89, 135, 14);
		panel_4.add(label_11);

		JPanel panel_17 = new JPanel();
		panel_17.setLayout(null);
		panel_17.setBackground(new Color(153, 153, 255));
		panel_17.setBounds(700, 390, 520, 90);
		RoundAndMulti.add(panel_17);

		JButton button = new JButton("Back");
		button.addActionListener(e -> {
			setVisible(false);
			main.setVisible(true);
		});
		button.setFont(new Font("Century Gothic", Font.BOLD, 13));
		button.setBounds(15, 30, 100, 35);
		panel_17.add(button);

		JButton button_1 = new JButton("PROCEED");
		button_1.setFont(new Font("Century Gothic", Font.BOLD, 13));
		button_1.setBounds(405, 30, 100, 35);
		panel_17.add(button_1);

		JLabel label_28 = new JLabel("TOTAL: ");
		label_28.setFont(new Font("Century Gothic", Font.BOLD, 20));
		label_28.setBounds(160, 30, 85, 35);
		panel_17.add(label_28);

		label_2 = new JLabel("");
		label_2.setFont(new Font("Century Gothic", Font.BOLD, 20));
		label_2.setBounds(255, 30, 100, 35);
		panel_17.add(label_2);

		JPanel panel_16 = new JPanel();
		panel_16.setLayout(null);
		panel_16.setBackground(new Color(153, 153, 255));
		panel_16.setBounds(700, 50, 520, 280);
		RoundAndMulti.add(panel_16);

		JLabel label_6 = new JLabel("");
		label_6.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\image\\hqdefault.jpg"));
		label_6.setBounds(0, 0, 260, 300);
		panel_16.add(label_6);

		JLabel label_8 = new JLabel("Flight");
		label_8.setOpaque(true);
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setFont(new Font("Century Gothic", Font.BOLD, 15));
		label_8.setBackground(new Color(102, 153, 255));
		label_8.setBounds(285, 30, 200, 30);
		panel_16.add(label_8);

		JLabel label_15 = new JLabel("Luggage included      20 kg");
		label_15.setOpaque(true);
		label_15.setHorizontalAlignment(SwingConstants.CENTER);
		label_15.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		label_15.setBackground(new Color(102, 153, 255));
		label_15.setBounds(285, 216, 200, 30);
		panel_16.add(label_15);

		JLabel label_17 = new JLabel();
		label_17.setOpaque(true);
		label_17.setFont(new Font("Century Gothic", Font.BOLD, 10));
		label_17.setBackground(new Color(102, 153, 255));
		label_17.setBounds(285, 90, 200, 45);
		if(flight.getType_of_flight().equals("Round Trip"))
			label_17.setText(flight.getFrom() + " - " + flight.getTo()+" - "+flight.getFrom());
		else if(flight.getType_of_flight().equals("Multi City Trip"))
			label_17.setText(flight.getFrom() + " - " + flight.getTo()+" & "+flight.getMulti_city_from()+" - "+flight.getFrom());
		panel_16.add(label_17);

		JLabel label_19 = new JLabel(flight.getType_of_flight());
		label_19.setOpaque(true);
		label_19.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		label_19.setBackground(new Color(102, 153, 255));
		label_19.setBounds(285, 134, 100, 45);
		panel_16.add(label_19);

        JLabel label_18 = new JLabel();
		label_18.setOpaque(true);
		label_18.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		label_18.setBackground(new Color(102, 153, 255));
		label_18.setBounds(385, 134, 100, 45);
		if(flight.getNrOfPassengers()==1)
			label_18.setText(flight.getNrOfPassengers()+" passenger");
		else
			label_18.setText(flight.getNrOfPassengers()+" passengers");
		panel_16.add(label_18);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(380, 115, 1, 45);
		panel_16.add(separator_1);

		JPanel OneWay = new JPanel();
		tabbedPane.addTab("______________________________________________________________________", null, OneWay, "");
		tabbedPane.setEnabledAt(1, false);
		OneWay.setLayout(null);

		JLabel label_13 = new JLabel("One Way Trip");
		label_13.setFont(new Font("Century Gothic", Font.BOLD, 30));
		label_13.setBounds(40, 40, 600, 40);
		OneWay.add(label_13);

		JPanel panel_10 = new JPanel();
		panel_10.setBackground(Color.LIGHT_GRAY);
		panel_10.setLayout(null);
		panel_10.setBounds(40, 92, 605, 150);
		OneWay.add(panel_10);

		JPanel panel_11 = new JPanel();
		panel_11.setLayout(null);
		panel_11.setBackground(new Color(204, 102, 0));
		panel_11.setBounds(155, 7, 140, 136);
		panel_10.add(panel_11);

		 radioButton_5 = new JRadioButton("Select flight");
			radioButton_5.addActionListener(e -> {
				radioButton_5.setSelected(true);
				radioButton_6.setSelected(false);
				radioButton_7.setSelected(false);
				FlightPriceOneWay();
			});
		radioButton_5.setHorizontalTextPosition(SwingConstants.LEADING);
		radioButton_5.setHorizontalAlignment(SwingConstants.RIGHT);
		radioButton_5.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		radioButton_5.setBackground(new Color(204, 102, 0));
		radioButton_5.setBounds(31, 7, 109, 23);
		panel_11.add(radioButton_5);

		label_12 = new JLabel(String.format("%.2f$", (random.nextDouble() * 80 + 20)));
		label_12.setFont(new Font("Century Gothic", Font.BOLD, 13));
		label_12.setBounds(70, 40, 60, 23);
		panel_11.add(label_12);

		JLabel lblEconomyClass_1 = new JLabel("Economy Class *");
		lblEconomyClass_1.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		lblEconomyClass_1.setBounds(10, 102, 120, 23);
		panel_11.add(lblEconomyClass_1);

		JPanel panel_12 = new JPanel();
		panel_12.setLayout(null);
		panel_12.setBackground(new Color(255, 51, 0));
		panel_12.setBounds(305, 7, 140, 136);
		panel_10.add(panel_12);

		radioButton_6 = new JRadioButton("Select flight");
		radioButton_6.addActionListener(e -> {
            radioButton_5.setSelected(false);
            radioButton_6.setSelected(true);
            radioButton_7.setSelected(false);
            FlightPriceOneWay();
        });
		radioButton_6.setHorizontalTextPosition(SwingConstants.LEADING);
		radioButton_6.setHorizontalAlignment(SwingConstants.RIGHT);
		radioButton_6.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		radioButton_6.setBackground(new Color(255, 51, 0));
		radioButton_6.setBounds(31, 7, 109, 23);
		panel_12.add(radioButton_6);

		label_14 = new JLabel(String.format("%.2f$", (random.nextDouble() * 900 + 100)));
		label_14.setFont(new Font("Century Gothic", Font.BOLD, 13));
		label_14.setBounds(70, 40, 60, 23);
		panel_12.add(label_14);

		JLabel lblMostPopula = new JLabel("Business Class **");
		lblMostPopula.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		lblMostPopula.setBounds(10, 102, 120, 23);
		panel_12.add(lblMostPopula);

		JPanel panel_13 = new JPanel();
		panel_13.setLayout(null);
		panel_13.setBackground(Color.GRAY);
		panel_13.setBounds(455, 7, 140, 136);
		panel_10.add(panel_13);

		radioButton_7 = new JRadioButton("Select flight");
		radioButton_7.addActionListener(e -> {
            radioButton_5.setSelected(false);
            radioButton_6.setSelected(false);
            radioButton_7.setSelected(true);
            FlightPriceOneWay();
        });
		radioButton_7.setHorizontalTextPosition(SwingConstants.LEADING);
		radioButton_7.setHorizontalAlignment(SwingConstants.RIGHT);
		radioButton_7.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		radioButton_7.setBackground(Color.GRAY);
		radioButton_7.setBounds(31, 7, 109, 23);
		panel_13.add(radioButton_7);

		label_16 = new JLabel(String.format("%.2f$", (random.nextDouble() * 9000 + 1000)));
		label_16.setFont(new Font("Century Gothic", Font.BOLD, 13));
		label_16.setBounds(70, 40, 60, 23);
		panel_13.add(label_16);

		JLabel lblFirstClass_1 = new JLabel("First Class ***");
		lblFirstClass_1.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		lblFirstClass_1.setBounds(10, 102, 120, 23);
		panel_13.add(lblFirstClass_1);

		JLabel label_20 = new JLabel("From: "+flight.getFrom());
		label_20.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		label_20.setBounds(10, 39, 135, 14);
		panel_10.add(label_20);

		JLabel label_21 = new JLabel("To: "+flight.getTo());
		label_21.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		label_21.setBounds(10, 64, 135, 14);
		panel_10.add(label_21);

		JLabel label_22 = new JLabel(flight.getFirst_flight());
		label_22.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		label_22.setBounds(10, 89, 135, 14);
		panel_10.add(label_22);


		JPanel panel_14 = new JPanel();
		panel_14.setBackground(new Color(153, 153, 255));
		panel_14.setBounds(700, 50, 520, 280);
		OneWay.add(panel_14);
		panel_14.setLayout(null);

		JLabel label_24 = new JLabel("");
		label_24.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\image\\hqdefault.jpg"));
		label_24.setBounds(0, 0, 260, 300);
		panel_14.add(label_24);

		JLabel lblFlight = new JLabel("Flight");
		lblFlight.setBackground(new Color(102, 153, 255));
		lblFlight.setOpaque(true);
		lblFlight.setHorizontalAlignment(SwingConstants.CENTER);
		lblFlight.setFont(new Font("Century Gothic", Font.BOLD, 15));
		lblFlight.setBounds(285, 30, 200, 30);
		panel_14.add(lblFlight);

		JLabel lblLuggageIncluded = new JLabel("Luggage included      20 kg");
		lblLuggageIncluded.setOpaque(true);
		lblLuggageIncluded.setBackground(new Color(102, 153, 255));
		lblLuggageIncluded.setHorizontalAlignment(SwingConstants.CENTER);
		lblLuggageIncluded.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		lblLuggageIncluded.setBounds(285, 216, 200, 30);
		panel_14.add(lblLuggageIncluded);

		JLabel lblNewLabel_4 = new JLabel("  "+flight.getFrom() + " - "+flight.getTo());
		lblNewLabel_4.setFont(new Font("Century Gothic", Font.BOLD, 14));
		lblNewLabel_4.setOpaque(true);
		lblNewLabel_4.setBackground(new Color(102, 153, 255));
		lblNewLabel_4.setBounds(285, 90, 200, 45);
		panel_14.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("  "+flight.getType_of_flight());
		lblNewLabel_5.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblNewLabel_5.setOpaque(true);
		lblNewLabel_5.setBackground(new Color(102, 153, 255));
		lblNewLabel_5.setBounds(285, 134, 100, 45);
		panel_14.add(lblNewLabel_5);

        JLabel label_4 = new JLabel();
		label_4.setOpaque(true);
		label_4.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		label_4.setBackground(new Color(102, 153, 255));
		label_4.setBounds(385, 134, 100, 45);
		if(flight.getNrOfPassengers()==1) {
			label_4.setText(flight.getNrOfPassengers()+" passenger");
		}else {
			label_4.setText(flight.getNrOfPassengers()+" passengers");
		}
		panel_14.add(label_4);

		JSeparator separator = new JSeparator();
		separator.setBounds(380, 115, 1, 45);
		panel_14.add(separator);

		JPanel panel_15 = new JPanel();
		panel_15.setBackground(new Color(153, 153, 255));
		panel_15.setBounds(700, 390, 520, 90);
		OneWay.add(panel_15);
		panel_15.setLayout(null);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(e -> {
            setVisible(false);
            main.setVisible(true);
        });
		btnBack.setFont(new Font("Century Gothic", Font.BOLD, 13));
		btnBack.setBounds(15, 30, 100, 35);
		panel_15.add(btnBack);

		JButton btnProceed = new JButton("PROCEED");
		btnProceed.addActionListener(e -> {
            FlightPriceOneWay();
            tabbedPane.setSelectedIndex(2);
            tabbedPane.setEnabledAt(0, false);
            tabbedPane.setEnabledAt(1, false);
            tabbedPane.setEnabledAt(2, true);
            tabbedPane.setEnabledAt(3, false);
            tabbedPane.setEnabledAt(4, false);
        });
		btnProceed.setFont(new Font("Century Gothic", Font.BOLD, 13));
		btnProceed.setBounds(405, 30, 100, 35);

		panel_15.add(btnProceed);

		JLabel lblNewLabel_3 = new JLabel("TOTAL: ");
		lblNewLabel_3.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lblNewLabel_3.setBounds(160, 30, 85, 35);
		panel_15.add(lblNewLabel_3);

		labeLTotal = new JLabel("");
		labeLTotal.setFont(new Font("Century Gothic", Font.BOLD, 20));
		labeLTotal.setBounds(255, 30, 100, 35);
		panel_15.add(labeLTotal);

		JPanel PassengerDetails = new JPanel();
		tabbedPane.addTab("Passenger Details", null, PassengerDetails, null);
		tabbedPane.setEnabledAt(2, false);
		PassengerDetails.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(153, 153, 255));
		panel_1.setBounds(700, 50, 520, 280);
		PassengerDetails.add(panel_1);

		JLabel label_26 = new JLabel("");
		label_26.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\image\\hqdefault.jpg"));
		label_26.setBounds(0, 0, 260, 300);
		panel_1.add(label_26);

		JLabel lblFlight_1 = new JLabel("Flight");
		lblFlight_1.setOpaque(true);
		lblFlight_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblFlight_1.setFont(new Font("Century Gothic", Font.BOLD, 13));
		lblFlight_1.setBackground(new Color(102, 153, 255));
		lblFlight_1.setBounds(285, 30, 200, 30);
		panel_1.add(lblFlight_1);

		JLabel label_30 = new JLabel("Luggage included      20 kg");
		label_30.setOpaque(true);
		label_30.setHorizontalAlignment(SwingConstants.CENTER);
		label_30.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		label_30.setBackground(new Color(102, 153, 255));
		label_30.setBounds(285, 216, 200, 30);
		panel_1.add(label_30);

		JLabel label_31 = new JLabel();
		label_31.setOpaque(true);
		label_31.setFont(new Font("Century Gothic", Font.BOLD, 14));
		label_31.setBackground(new Color(102, 153, 255));
		label_31.setBounds(285, 90, 200, 45);
		if(flight.getType_of_flight().equals("One Way Trip")) {
			label_31.setText(flight.getFrom() + " - "+flight.getTo());
			label_31.setFont(new Font("Century Gothic", Font.BOLD, 14));
		}else if(flight.getType_of_flight().equals("Round Trip")) {
			label_31.setText(flight.getFrom() + " - "+flight.getTo() + " - " + flight.getFrom());
			label_31.setFont(new Font("Century Gothic", Font.BOLD, 10));
		}else {
			label_31.setText(flight.getFrom() + " - " + flight.getTo()+" & "+flight.getMulti_city_from()+" - "+flight.getFrom());
			label_31.setFont(new Font("Century Gothic", Font.BOLD, 10));
		}
		panel_1.add(label_31);

		JLabel label_32 = new JLabel(flight.getType_of_flight());
		label_32.setOpaque(true);
		label_32.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		label_32.setBackground(new Color(102, 153, 255));
		label_32.setBounds(285, 134, 100, 45);
		panel_1.add(label_32);

		JLabel label_33 = new JLabel("");
		label_33.setOpaque(true);
		label_33.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		label_33.setBackground(new Color(102, 153, 255));
		label_33.setBounds(385, 134, 100, 45);
		if(flight.getNrOfPassengers()==1) {
			label_33.setText(flight.getNrOfPassengers()+" passenger");
		}else {
			label_33.setText(flight.getNrOfPassengers()+" passengers");
		}
		panel_1.add(label_33);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(380, 115, 1, 45);
		panel_1.add(separator_2);

		JPanel panel_18 = new JPanel();
		panel_18.setLayout(null);
		panel_18.setBackground(new Color(153, 153, 255));
		panel_18.setBounds(700, 390, 520, 90);
		PassengerDetails.add(panel_18);

		JButton button_2 = new JButton("Back");
		button_2.addActionListener(arg0 -> {
            if(flight.getType_of_flight().equals("One Way Trip")) {
            	tabbedPane.setSelectedIndex(1);
            	tabbedPane.setEnabledAt(0, false);
            	tabbedPane.setEnabledAt(1, true);
            }else {
                tabbedPane.setSelectedIndex(0);
                tabbedPane.setEnabledAt(0, true);
                tabbedPane.setEnabledAt(1, false);
            }
            tabbedPane.setEnabledAt(2, false);
            tabbedPane.setEnabledAt(3, false);
            tabbedPane.setEnabledAt(4, false);
        });
		button_2.setFont(new Font("Century Gothic", Font.BOLD, 13));
		button_2.setBounds(15, 30, 100, 35);
		panel_18.add(button_2);

		JButton button_3 = new JButton("PROCEED");
		button_3.addActionListener(e -> {
            if(passengers.size() < flight.getNrOfPassengers()) {
                JOptionPane.showMessageDialog(null, "Please enter all the passengers and then continue...");
            }else {
                tabbedPane.setSelectedIndex(4);
                tabbedPane.setEnabledAt(0, false);
                tabbedPane.setEnabledAt(1, false);
                tabbedPane.setEnabledAt(2, false);
                tabbedPane.setEnabledAt(3, false);
                tabbedPane.setEnabledAt(4, true);
            }
        });
		button_3.setFont(new Font("Century Gothic", Font.BOLD, 13));
		button_3.setBounds(405, 30, 100, 35);

		panel_18.add(button_3);

		JLabel label_34 = new JLabel("TOTAL: ");
		label_34.setFont(new Font("Century Gothic", Font.BOLD, 20));
		label_34.setBounds(160, 30, 85, 35);
		panel_18.add(label_34);

		label_25 = new JLabel();
		label_25.setFont(new Font("Century Gothic", Font.BOLD, 20));
		label_25.setBounds(255, 30, 100, 35);
		panel_18.add(label_25);

		JLabel lblPassengerDetails = new JLabel("Passenger Details");
		lblPassengerDetails.setFont(new Font("Century Gothic", Font.BOLD, 30));
		lblPassengerDetails.setBounds(40, 40, 600, 40);
		PassengerDetails.add(lblPassengerDetails);

		JLabel lblSuffix = new JLabel("Suffix*");
		lblSuffix.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		lblSuffix.setBounds(40, 90, 46, 14);
		PassengerDetails.add(lblSuffix);

		JComboBox comboBoxSuffix = new JComboBox();
		comboBoxSuffix.setModel(new DefaultComboBoxModel(new String[] { "", "MR", "MRS", "MSTR", "MISS" }));
		comboBoxSuffix.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		comboBoxSuffix.setBounds(40, 115, 150, 30);
		PassengerDetails.add(comboBoxSuffix);

		textFieldFName = new JTextField();
		textFieldFName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				String string = "^(?=.*[A-Z])(?=.*[a-z]).{2,30}$"; //the pattern which should be followed inside textField
				Pattern pt = Pattern.compile(string);
				Matcher match = pt.matcher(textFieldFName.getText());
				if (!match.matches()) {
					message.setText("Enter a valid first name");
				} else {
					message.setText(null);
				}
			}
		});
		textFieldFName.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		textFieldFName.setBounds(225, 115, 150, 30);
		PassengerDetails.add(textFieldFName);
		textFieldFName.setColumns(10);

		textFieldLName = new JTextField();
		textFieldLName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String string = "^(?=.*[A-Z])(?=.*[a-z]).{2,30}$";
				Pattern pt = Pattern.compile(string);
				Matcher match = pt.matcher(textFieldLName.getText());
				if (!match.matches()) {
					message.setText("Enter a valid last name");
				} else {
					message.setText(null);
				}
			}
		});
		textFieldLName.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		textFieldLName.setColumns(10);
		textFieldLName.setBounds(414, 115, 150, 30);
		PassengerDetails.add(textFieldLName);

		JLabel lblFirstName = new JLabel("First Name*");
		lblFirstName.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		lblFirstName.setBounds(226, 91, 73, 14);
		PassengerDetails.add(lblFirstName);

		JLabel lblLastName = new JLabel("Last Name*");
		lblLastName.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		lblLastName.setBounds(413, 91, 73, 14);
		PassengerDetails.add(lblLastName);

		JDateChooser dateChooserBirthday = new JDateChooser();
		dateChooserBirthday.setBounds(40, 190, 150, 25);
		PassengerDetails.add(dateChooserBirthday);

		textFieldResidence = new JTextField();
		textFieldResidence.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String string = "^(?=.*[A-Z])(?=.*[a-z]).{2,30}$";
				Pattern pt = Pattern.compile(string);
				Matcher match = pt.matcher(textFieldResidence.getText());
				if (!match.matches()) {
					message.setText("Enter a valid residence country");
				} else {
					message.setText(null);
				}
			}
		});
		textFieldResidence.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		textFieldResidence.setColumns(10);
		textFieldResidence.setBounds(225, 190, 150, 25);
		PassengerDetails.add(textFieldResidence);

		JLabel lblDateOfBirth = new JLabel("Date of Birth*");
		lblDateOfBirth.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		lblDateOfBirth.setBounds(40, 165, 88, 14);
		PassengerDetails.add(lblDateOfBirth);

		JLabel lblResidenceCountry = new JLabel("Residence Country*");
		lblResidenceCountry.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		lblResidenceCountry.setBounds(226, 166, 122, 14);
		PassengerDetails.add(lblResidenceCountry);

		JLabel lblContactDetails = new JLabel("Contact Details");
		lblContactDetails.setFont(new Font("Century Gothic", Font.BOLD, 30));
		lblContactDetails.setBounds(40, 261, 600, 40);
		PassengerDetails.add(lblContactDetails);

		JLabel lblEmail = new JLabel("Email*");
		lblEmail.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		lblEmail.setBounds(40, 312, 46, 14);
		PassengerDetails.add(lblEmail);

		JLabel lblPhoneNumber = new JLabel("Phone number*");
		lblPhoneNumber.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		lblPhoneNumber.setBounds(226, 312, 96, 14);
		PassengerDetails.add(lblPhoneNumber);

		textFieldPhone = new JTextField();
		textFieldPhone.setText("+(prefix)(phone number)");
		textFieldPhone.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if (textFieldPhone.getText().isEmpty()) {
					textFieldPhone.setText("+(prefix)(phone number)");
				}
			}
			public void focusGained(FocusEvent arg0) {
				textFieldPhone.setText("");
			}
		});
		textFieldPhone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String string = "(?=.*[0-9]).{8,20}$";
				Pattern pattern = Pattern.compile(string);
				Matcher matcher = pattern.matcher(textFieldPhone.getText());
				if (!matcher.matches()) {
					message1.setText("Enter a valid phone number");
				} else {
					message1.setText(null);
				}
			}
		});
		textFieldPhone.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		textFieldPhone.setColumns(10);
		textFieldPhone.setBounds(225, 336, 150, 30);
		PassengerDetails.add(textFieldPhone);

		JLabel lblCountry = new JLabel("Country*");
		lblCountry.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		lblCountry.setBounds(413, 312, 73, 14);
		PassengerDetails.add(lblCountry);

		textFieldCountry = new JTextField();
		textFieldCountry.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String string = "^(?=.*[A-Z])(?=.*[a-z]).{2,20}$";
				Pattern pt = Pattern.compile(string);
				Matcher match = pt.matcher(textFieldCountry.getText());
				if (!match.matches()) {
					message1.setText("Enter your home contry");
				} else {
					message1.setText(null);
				}
			}
		});
		textFieldCountry.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		textFieldCountry.setColumns(10);
		textFieldCountry.setBounds(414, 336, 150, 30);
		PassengerDetails.add(textFieldCountry);

		JLabel lblCity = new JLabel("City");
		lblCity.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		lblCity.setBounds(40, 386, 88, 14);
		PassengerDetails.add(lblCity);

		JLabel lblStreet = new JLabel("Street");
		lblStreet.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		lblStreet.setBounds(226, 387, 122, 14);
		PassengerDetails.add(lblStreet);

		textFieldEmail = new JTextField();
		textFieldEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String string = "^[a-zA-Z0-9]{5,20}@(?=.*[a-zA-Z]).{4,10}[.](?=.*[a-zA-Z]).{2,5}$";
				Pattern pt = Pattern.compile(string);
				Matcher match = pt.matcher(textFieldEmail.getText());
				if (!match.matches()) {
					message1.setText("Enter a valid email");
				} else {
					message1.setText(null);
				}
			}
		});
		textFieldEmail.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(40, 336, 150, 30);
		PassengerDetails.add(textFieldEmail);

		textFieldCity = new JTextField();
		textFieldCity.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String string = "^(?=.*[A-Z])(?=.*[a-z]).{2,30}$";
				Pattern pt = Pattern.compile(string);
				Matcher match = pt.matcher(textFieldCity.getText());
				if (!match.matches()) {
					message1.setText("Enter your home city");
				} else {
					message1.setText(null);
				}
			}
		});
		textFieldCity.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		textFieldCity.setColumns(10);
		textFieldCity.setBounds(40, 411, 150, 30);
		PassengerDetails.add(textFieldCity);

		textFieldStreet = new JTextField();
		textFieldStreet.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String string = "^(?=.*[A-Z])(?=.*[a-z]).{2,30}$";
				Pattern pt = Pattern.compile(string);
				Matcher match = pt.matcher(textFieldStreet.getText());
				if (!match.matches()) {
					message1.setText("Enter a valid street");
				} else {
					message1.setText(null);
				}
			}
		});
		textFieldStreet.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		textFieldStreet.setColumns(10);
		textFieldStreet.setBounds(225, 412, 150, 30);
		PassengerDetails.add(textFieldStreet);

		JButton buttonAdd = new JButton("ADD A PASSENGER");
		buttonAdd.setFont(new Font("Century Gothic", Font.BOLD, 12));
		buttonAdd.setBounds(414, 412, 150, 30);
		PassengerDetails.add(buttonAdd);

		message = new JLabel("");
		message.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		message.setBounds(40, 236, 524, 25);
		message.setForeground(Color.RED);
		PassengerDetails.add(message);

		message1 = new JLabel("");
		message1.setForeground(Color.RED);
		message1.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		message1.setBounds(40, 466, 524, 25);
		PassengerDetails.add(message1);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		tabbedPane.addTab("_____________________________________________________________________", null, panel, null);
		tabbedPane.setEnabledAt(3, false);

		JPanel Payment = new JPanel();
		tabbedPane.addTab("Payment", null, Payment, null);
		Payment.setLayout(null);

		JPanel panel_19 = new JPanel();
		panel_19.setLayout(null);
		panel_19.setBackground(new Color(153, 153, 255));
		panel_19.setBounds(700, 390, 520, 90);
		Payment.add(panel_19);

		JButton button_4 = new JButton("Back");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabbedPane.setSelectedIndex(2);
				tabbedPane.setEnabledAt(0, false);
				tabbedPane.setEnabledAt(1, false);
				tabbedPane.setEnabledAt(2, true);
				tabbedPane.setEnabledAt(3, false);
				tabbedPane.setEnabledAt(4, false);
			}
		});
		button_4.setFont(new Font("Century Gothic", Font.BOLD, 13));
		button_4.setBounds(15, 30, 100, 35);
		panel_19.add(button_4);

		JPanel panel_20 = new JPanel();
		panel_20.setLayout(null);
		panel_20.setBackground(new Color(153, 153, 255));
		panel_20.setBounds(700, 50, 520, 280);
		Payment.add(panel_20);

		JLabel label_38 = new JLabel("");
		label_38.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\image\\hqdefault.jpg"));
		label_38.setBounds(0, 0, 260, 300);
		panel_20.add(label_38);

		JLabel lblFlight_2 = new JLabel("Flight");
		lblFlight_2.setOpaque(true);
		lblFlight_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblFlight_2.setFont(new Font("Century Gothic", Font.BOLD, 13));
		lblFlight_2.setBackground(new Color(102, 153, 255));
		lblFlight_2.setBounds(285, 30, 200, 30);
		panel_20.add(lblFlight_2);

		JLabel label_40 = new JLabel("Luggage included      20 kg");
		label_40.setOpaque(true);
		label_40.setHorizontalAlignment(SwingConstants.CENTER);
		label_40.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		label_40.setBackground(new Color(102, 153, 255));
		label_40.setBounds(285, 216, 200, 30);
		panel_20.add(label_40);

		JLabel label_41 = new JLabel("");
		label_41.setOpaque(true);
		label_41.setFont(new Font("Century Gothic", Font.BOLD, 14));
		label_41.setBackground(new Color(102, 153, 255));
		label_41.setBounds(285, 90, 200, 45);
		if(flight.getType_of_flight().equals("One Way Trip")) {
			label_41.setText(flight.getFrom() + " - "+flight.getTo());
		}else if(flight.getType_of_flight().equals("Round Trip")) {
			label_41.setText(flight.getFrom() + " - "+flight.getTo() + " - " + flight.getFrom());
		}else {
			label_41.setText(flight.getFrom() + " - " + flight.getTo()+" & "+flight.getMulti_city_from()+" - "+flight.getFrom());
		}
		panel_20.add(label_41);

		JLabel label_42 = new JLabel(flight.getType_of_flight());
		label_42.setOpaque(true);
		label_42.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		label_42.setBackground(new Color(102, 153, 255));
		label_42.setBounds(285, 134, 100, 45);
		panel_20.add(label_42);

		JLabel label_43 = new JLabel();
		label_43.setOpaque(true);
		label_43.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		label_43.setBackground(new Color(102, 153, 255));
		label_43.setBounds(385, 134, 100, 45);
		if(flight.getNrOfPassengers()==1) {
			label_43.setText(flight.getNrOfPassengers()+" passenger");
		}else {
			label_43.setText(flight.getNrOfPassengers()+" passengers");
		}
		panel_20.add(label_43);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(380, 115, 1, 45);
		panel_20.add(separator_3);

		JLabel lblPaymentDetails = new JLabel("Payment Details");
		lblPaymentDetails.setFont(new Font("Century Gothic", Font.BOLD, 30));
		lblPaymentDetails.setBounds(40, 50, 600, 40);
		Payment.add(lblPaymentDetails);

		JLabel lblTotalAmountPending = new JLabel("TOTAL AMOUNT PENDING: ");
		lblTotalAmountPending.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lblTotalAmountPending.setBounds(40, 101, 260, 35);
		Payment.add(lblTotalAmountPending);

		label_23 = new JLabel();
		label_23.setFont(new Font("Century Gothic", Font.BOLD, 20));
		label_23.setBounds(298, 101, 100, 35);
		Payment.add(label_23);

		textFieldCard = new JTextField("xxxx-xxxx-xxxx-xxxx");
		textFieldCard.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				String string = "^(?=.*[1-9]).{16}$";
				Pattern pt = Pattern.compile(string);
				Matcher match = pt.matcher(textFieldCard.getText());
				if (!match.matches()) {
					message.setText("Enter a valid card number");
				} else {
					message.setText(null);
				}
			}
		});
		textFieldCard.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(textFieldCard.getText().equals("xxxx-xxxx-xxxx-xxxx"))
					textFieldCard.setText("");
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(textFieldCard.getText().equals(""))
					textFieldCard.setText("xxxx-xxxx-xxxx-xxxx");
			}
		});
		textFieldCard.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		textFieldCard.setColumns(10);
		textFieldCard.setBounds(40, 174, 151, 30);
		Payment.add(textFieldCard);

		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				String string = "^(?=.*[A-Z])(?=.*[a-z]).{2,30}$";
				Pattern pt = Pattern.compile(string);
				Matcher match = pt.matcher(textField.getText());
				if (!match.matches()) {
					message.setText("Enter a valid card holder name");
				} else {
					message.setText(null);
				}
			}
		});
		textField.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		textField.setColumns(10);
		textField.setBounds(248, 174, 150, 30);
		Payment.add(textField);

		comboBox = new JComboBox();
		comboBox.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		comboBox.setModel(new DefaultComboBoxModel(
				new String[] { "", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
		comboBox.setToolTipText("");
		comboBox.setBounds(40, 240, 151, 30);
		Payment.add(comboBox);

		comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		comboBox_1.setModel(new DefaultComboBoxModel(
				new String[] { "", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031",
						"2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042" }));
		comboBox_1.setToolTipText("");
		comboBox_1.setBounds(248, 240, 151, 30);
		Payment.add(comboBox_1);

		JLabel lblNewLabel_6 = new JLabel("Last 3 digits on the back of card");
		lblNewLabel_6.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		lblNewLabel_6.setBounds(40, 304, 185, 30);
		Payment.add(lblNewLabel_6);

		JButton btnConfirmPay = new JButton("CONFIRM & PAY");
		btnConfirmPay.addActionListener(e -> {
            if(textFieldCard.getText().isEmpty() || textField.getText().isEmpty()
                    || textFieldCard.getText().equals("xxxx-xxxx-xxxx-xxxx") || txtCvv.getText().equals("CVV")
                    || Objects.equals(comboBox.getSelectedItem(),"") || Objects.equals(comboBox_1.getSelectedItem(), "")
                    || txtCvv.getText().isEmpty() || !chckbxIHaveRead.isSelected()) {
                Message.setText("Please fill all information");
            }else {
                String card_holder = textField.getText();
                String card_nr = textFieldCard.getText();
                flight.setCard_holder(card_holder);
                flight.setCard_number(card_nr);
                flight.setPassengers(passengers);
                write_to_file(flight);
                JOptionPane.showMessageDialog(null, "Your ticket with booking number "+flight.getBooking_number()+" is issued!");
				Flight result = null;
                ArrayList<Flight> data = getData();
                for(Flight temp_flight:data) {
					if(temp_flight.getBooking_number().equalsIgnoreCase(flight.getBooking_number())) {
						result = temp_flight;
						break;
                    }
                }
                write_to_temp_file(result);
                setVisible(false);
                MyBooking mb = new MyBooking();
                mb.setVisible(true);
            }
        });
		btnConfirmPay.setBounds(136, 445, 164, 35);
		Payment.add(btnConfirmPay);
		btnConfirmPay.setFont(new Font("Century Gothic", Font.BOLD, 13));

		txtCvv = new JTextField();
		txtCvv.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(txtCvv.getText().equals("CVV"))
					txtCvv.setText("");
			}
			public void focusLost(FocusEvent arg0) {
				if(txtCvv.getText().isEmpty())
					txtCvv.setText("CVV");
			}
		});
		txtCvv.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				String string = "^(?=.*[1-9]).{3}$";
				Pattern pt = Pattern.compile(string);
				Matcher match = pt.matcher(txtCvv.getText());
				if (!match.matches()) {
					message.setText("Enter the correct CVV");
				} else {
					message.setText(null);
				}
			}
		});
		txtCvv.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		txtCvv.setText("CVV");
		txtCvv.setColumns(10);
		txtCvv.setBounds(248, 304, 150, 30);
		Payment.add(txtCvv);

		chckbxIHaveRead = new JCheckBox("I have read terms and conditions");
		chckbxIHaveRead.setBounds(40, 370, 358, 23);
		Payment.add(chckbxIHaveRead);

		JLabel lblCardNumber = new JLabel("Card Number");
		lblCardNumber.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		lblCardNumber.setBounds(40, 147, 92, 14);
		Payment.add(lblCardNumber);

		JLabel lblCardHolder = new JLabel("Card Holder");
		lblCardHolder.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		lblCardHolder.setBounds(248, 147, 107, 14);
		Payment.add(lblCardHolder);

		JLabel lblExpiringMonth = new JLabel("Expiring Month");
		lblExpiringMonth.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		lblExpiringMonth.setBounds(40, 215, 122, 14);
		Payment.add(lblExpiringMonth);

		JLabel lblExpiringYear = new JLabel("Expiring Year");
		lblExpiringYear.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		lblExpiringYear.setBounds(248, 215, 133, 14);
		Payment.add(lblExpiringYear);

		JLabel lblSecurityCode = new JLabel("Security Code");
		lblSecurityCode.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		lblSecurityCode.setBounds(248, 281, 107, 14);
		Payment.add(lblSecurityCode);
		
		Message = new JLabel("");
		Message.setForeground(Color.RED);
		Message.setFont(new Font("Century Gothic", Font.BOLD, 15));
		Message.setBounds(40, 406, 358, 30);
		Payment.add(Message);
		tabbedPane.setEnabledAt(4, false);

		Panel panel_2 = new Panel();
		panel_2.setBounds(0, 660, 1280, 100);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel(
				"\u00A9 2022 FlyHigh Airways    Terms of us    Privacy policy    ADM policy    Contact    customercare@flyhigh.al");
		lblNewLabel_1.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(300, 10, 680, 35);
		panel_2.add(lblNewLabel_1);

		button_1.addActionListener(arg0 -> {
            getFlightPrice();
            tabbedPane.setSelectedIndex(2);
            tabbedPane.setEnabledAt(0, false);
            tabbedPane.setEnabledAt(1, false);
            tabbedPane.setEnabledAt(2, true);
            tabbedPane.setEnabledAt(3, false);
            tabbedPane.setEnabledAt(4, false);
        });

		if (flight.getType_of_flight().equals("One Way Trip")) {
			tabbedPane.setSelectedIndex(1);
			tabbedPane.setEnabledAt(0, false);
			tabbedPane.setEnabledAt(1, true);
        }
		else {
			tabbedPane.setSelectedIndex(0);
			tabbedPane.setEnabledAt(0, true);
			tabbedPane.setEnabledAt(1, false);
        }
        tabbedPane.setEnabledAt(2, false);
        tabbedPane.setEnabledAt(3, false);
        tabbedPane.setEnabledAt(4, false);

        buttonAdd.addActionListener(arg0 -> {
            if (textFieldFName.getText().isEmpty() || textFieldLName.getText().isEmpty()
                    || textFieldResidence.getText().isEmpty() || textFieldEmail.getText().isEmpty()
                    || textFieldPhone.getText().isEmpty() || textFieldCountry.getText().isEmpty()
                    || Objects.equals(comboBoxSuffix.getSelectedItem(), "") || Objects.isNull(dateChooserBirthday.getDate())
                    || textFieldPhone.getText().equals("+(prefix)(phone number)")) {
                message.setText("Please fill all mandatory data");
                lblSuffix.setForeground(Color.RED);
                lblFirstName.setForeground(Color.RED);
                lblLastName.setForeground(Color.RED);
                lblDateOfBirth.setForeground(Color.RED);
                lblResidenceCountry.setForeground(Color.RED);
                lblEmail.setForeground(Color.RED);
                lblPhoneNumber.setForeground(Color.RED);
                lblCountry.setForeground(Color.RED);
            } else {
				//If the arrayList size is less than the number entered by the user you should enter more passengers
                if(passengers.size() < flight.getNrOfPassengers()) {
                    String first_name = textFieldFName.getText();
                    String last_name = textFieldLName.getText();
                    String email = textFieldEmail.getText();
                    String suffix = comboBoxSuffix.getSelectedItem().toString();
                    String phone_no = textFieldPhone.getText();
                    String country = textFieldCountry.getText();
                    String city = textFieldCity.getText();
                    String street = textFieldStreet.getText();
                    passenger = new Passenger(suffix, first_name, last_name, email, city, street, country, phone_no);
                    passengers.add(passenger);
                    flight.setPassengers(passengers);
                    textFieldFName.setText("");
                    textFieldLName.setText("");
                    comboBoxSuffix.setSelectedIndex(0);
                    textFieldPhone.setText("+(prefix)(phone number)");
                    textFieldCountry.setText("");
                    textFieldCity.setText("");
                    textFieldStreet.setText("");
                    textFieldResidence.setText("");
                    dateChooserBirthday.setDate(null);
                    textFieldEmail.setText("");
                }
                else {
                    message.setText("You have entered all the passengers. Please proceed with the payment!");
                    buttonAdd.setEnabled(false);
                }
            }
        });
	}
	/**
	 * Function to get the total price
	 */
	public void getFlightPrice() {
		flight_price = 0;
		if (rdbtnSelectFlight.isSelected()) {
			flight_price += Double.parseDouble(
					labelPricePromo.getText().substring(0, labelPricePromo.getText().length() - 1));
		}
		if (radioButton.isSelected()) {
			flight_price += Double
					.parseDouble(labelPriceEco.getText().substring(0, labelPricePromo.getText().length() - 1));
		}
		if (radioButton_1.isSelected()) {
			flight_price += Double
					.parseDouble(labelBusiness.getText().substring(0, labelPricePromo.getText().length() - 1));
		}
		if (radioButton_2.isSelected()) {
			flight_price += Double.parseDouble(
					label_3.getText().substring(0, label_3.getText().length() - 1));
		}
		if (radioButton_3.isSelected()) {
			flight_price += Double.parseDouble(
					label_5.getText().substring(0, label_5.getText().length() - 1));

		}
		if (radioButton_4.isSelected()) {
			flight_price += Double.parseDouble(
					label_7.getText().substring(0, label_7.getText().length() - 1));
		}
		label_2.setText(String.format("%.2f$", flight.calc_total(flight_price)));
		label_25.setText(String.format("%.2f$", flight.calc_total(flight_price)));
		label_23.setText(String.format("%.2f$", flight.calc_total(flight_price)));
	}
	public void FlightPriceOneWay() {
		flight_price = 0;
		if (radioButton_5.isSelected()) {
			flight_price += Double
					.parseDouble(label_12.getText().substring(0, label_12.getText().length() - 1));
		}
		if (radioButton_6.isSelected()) {
			flight_price += Double.parseDouble(
					label_14.getText().substring(0, label_14.getText().length() - 1));
		}
		if (radioButton_7.isSelected()) {
			flight_price += Double.parseDouble(
					label_16.getText().substring(0, label_16.getText().length() - 1));
		}
		labeLTotal.setText(String.format("%.2f$", flight.calc_total(flight_price)));
		label_25.setText(String.format("%.2f$", flight.calc_total(flight_price)));
		label_23.setText(String.format("%.2f$", flight.calc_total(flight_price)));
	}
}
