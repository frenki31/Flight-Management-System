package flight;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.Cursor;

import com.google.gson.reflect.TypeToken;
import com.toedter.calendar.JDateChooser;
import inheritance.Flight;
import inheritance.Passenger;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;
import java.awt.Panel;
import java.util.Objects;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import static flight.NewReservation.getFlights;
import static flight.NewReservation.tempFlight;

/**
 * Class Display is the user interface and the main one because from here the user can choose the flight 
 * information and then go on with booking the ticket
 * @author user
 */
public class Display extends JFrame {

    private final JTextField textField_1, textField;
	private final JDateChooser dateChooserReturn, dateChooser, dateChooser_1;
	private final JRadioButton rdbtnOneWayTrip, rdbtnMulticityTrip, radioButtonMulti, radioButtonOneWay, radioButtonRound;
	private final JSpinner AdultSpin, ChildSpin, InfantSpin, spinner, spinner_1, spinner_2;
	protected Flight flight;
	private final JLabel lblmessage, message;
	private final JComboBox comboBox_2, comboBox_3, ToBox, FromBox;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
            try {
                Display frame = new Display();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
	}
	protected boolean write_to_temp_file(Flight flight) {
		return tempFlight(flight);
	}
	public ArrayList<Flight> getData() {
		String yourFile = "flights.json";
		File tmpDir = new File(yourFile);
		boolean exists = tmpDir.exists();
		try {
			java.lang.reflect.Type listType = new TypeToken<ArrayList<Flight>>() {}.getType();
            return getFlights(exists, listType);
		} catch (Exception ex) {
			System.out.println(ex);
			return new ArrayList<>();
		}
	}
	/**
	 * Constructor of the class inside which is every object put
	 */
	public Display() {
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

		JLabel lblInsta = new JLabel("");
		lblInsta.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\image\\Instagram_logo_2016.svg.png"));
		lblInsta.setBounds(1220, 40, 30, 30);
		contentPane.add(lblInsta);

		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\image\\Facebook_icon_2013.svg.png"));
		label_1.setBounds(1165, 40, 30, 30);
		contentPane.add(label_1);

		JTabbedPane TabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		TabbedPane.setBorder(null);
		TabbedPane.setBounds(40, 130, 1200, 200);
		contentPane.add(TabbedPane);

		JPanel NewResPane = new JPanel();
		TabbedPane.addTab("", null, NewResPane, null);
		TabbedPane.setEnabledAt(0, true);
		NewResPane.setLayout(null);

		FromBox = new JComboBox();
		FromBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(FromBox.getSelectedItem().equals(ToBox.getSelectedItem())){
					FromBox.setSelectedIndex(0);
				}
			}
		});
		FromBox.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		FromBox.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		FromBox.setName("From\r\n");
		FromBox.setModel(new DefaultComboBoxModel(new String[] { "From", "Tirana (TIA)", "Milan (MXP)", "Bergamo (BGY)",
				"Verona (VRN)", "Bologna (BLQ)", "Pisa (PSA)", "Rome (FCO)", "Istanbul (IST)" }));
		FromBox.setToolTipText("");
		FromBox.setBounds(30, 45, 240, 27);
		NewResPane.add(FromBox);

		ToBox = new JComboBox();
		ToBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(FromBox.getSelectedItem().equals(ToBox.getSelectedItem())){
					ToBox.setSelectedIndex(0);
				}
			}
		});
		ToBox.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		ToBox.setName("To");
		ToBox.setModel(new DefaultComboBoxModel(new String[] { "To", "Tirana (TIA)", "Milan (MXP)", "Bergamo (BGY)",
				"Verona (VRN)", "Bologna (BLQ)", "Pisa (PSA)", "Rome (FCO)", "Istanbul (IST)" }));
		ToBox.setBounds(310, 45, 240, 27);
		NewResPane.add(ToBox);

		JRadioButton rdbtnRoundTrip = new JRadioButton("Round Trip");
		rdbtnRoundTrip.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				rdbtnOneWayTrip.setSelected(false);
				rdbtnRoundTrip.setSelected(true);
				rdbtnMulticityTrip.setSelected(false);
				dateChooserReturn.setEnabled(true);
			}
		});
		rdbtnRoundTrip.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		rdbtnRoundTrip.setSelected(true);
		rdbtnRoundTrip.setBounds(650, 46, 100, 23);
		NewResPane.add(rdbtnRoundTrip);

		rdbtnOneWayTrip = new JRadioButton("One Way Trip");
		rdbtnOneWayTrip.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				rdbtnOneWayTrip.setSelected(true);
				rdbtnRoundTrip.setSelected(false);
				rdbtnMulticityTrip.setSelected(false);
				dateChooserReturn.setEnabled(false);
			}
		});
		rdbtnOneWayTrip.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		rdbtnOneWayTrip.setBounds(820, 46, 110, 23);
		NewResPane.add(rdbtnOneWayTrip);

		rdbtnMulticityTrip = new JRadioButton("Multi-City Trip");
		rdbtnMulticityTrip.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				rdbtnOneWayTrip.setSelected(false);
				rdbtnRoundTrip.setSelected(false);
				rdbtnMulticityTrip.setSelected(true);
				radioButtonMulti.setSelected(true);
				radioButtonOneWay.setSelected(false);
				radioButtonRound.setSelected(false);
				TabbedPane.setEnabledAt(2, true);
				TabbedPane.setEnabledAt(0, false);
				TabbedPane.setEnabledAt(1, false);
				TabbedPane.setSelectedIndex(2);
			}
		});
		rdbtnMulticityTrip.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		rdbtnMulticityTrip.setBounds(1000, 46, 109, 23);
		NewResPane.add(rdbtnMulticityTrip);

		JDateChooser dateChooserGoing = new JDateChooser();
		dateChooserGoing.setBounds(30, 110, 240, 25);
		NewResPane.add(dateChooserGoing);

		dateChooserReturn = new JDateChooser();
		dateChooserReturn.setBounds(310, 110, 240, 25);
		NewResPane.add(dateChooserReturn);

		JLabel lblAdult = new JLabel("Adult:");
		lblAdult.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		lblAdult.setBounds(650, 84, 46, 20);
		NewResPane.add(lblAdult);

		JLabel lblChild = new JLabel("Child:");
		lblChild.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		lblChild.setBounds(829, 84, 46, 20);
		NewResPane.add(lblChild);

		JLabel lblChil = new JLabel("Infant:");
		lblChil.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		lblChil.setBounds(1000, 84, 46, 20);
		NewResPane.add(lblChil);

		JLabel label_8 = new JLabel("Choose your flight");
		label_8.setFont(new Font("Century Gothic", Font.BOLD, 18));
		label_8.setBounds(30, 10, 520, 23);
		NewResPane.add(label_8);

		JButton btnSearch = new JButton("SEARCH");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Objects.equals(FromBox.getSelectedItem(), "From") || Objects.equals(ToBox.getSelectedItem(), "To")
						|| Objects.isNull(dateChooserGoing.getDate()) || AdultSpin.getValue().equals("0")) {
					lblmessage.setText("Please fill in every field");
				} else {
					String to = ToBox.getSelectedItem().toString();
					String from = FromBox.getSelectedItem().toString();
					Date second_flight_date;
					String second_flight ="";
					if(dateChooserReturn.getDate() != null) {
						if(rdbtnRoundTrip.isSelected() || rdbtnMulticityTrip.isSelected()) {
							second_flight_date = dateChooserReturn.getDate();
							second_flight =  DateFormat.getDateInstance().format(second_flight_date);
						}
					}
					Date first_flight_date = dateChooserGoing.getDate();
					String first_flight = DateFormat.getDateInstance().format(first_flight_date);
					int nr_of_adults = (Integer) AdultSpin.getValue();
					int nr_of_children = (Integer) ChildSpin.getValue();
					int nr_of_infants = (Integer) InfantSpin.getValue();
					System.out.println(rdbtnRoundTrip.isSelected());
					String type_of_flight;
					if(rdbtnRoundTrip.isSelected())
						type_of_flight="Round Trip";
					else if(rdbtnOneWayTrip.isSelected())
						type_of_flight = "One Way Trip";
					else
						type_of_flight = "Multi City Trip";
			        flight = new Flight(to,from,first_flight,second_flight,nr_of_adults,nr_of_children,nr_of_infants,type_of_flight);
			        if(write_to_temp_file(flight)) {
					NewReservation newReservation = new NewReservation();
					dispose();
					newReservation.setVisible(true);
					}
				}
			}
		});
		btnSearch.setFont(new Font("Century Gothic", Font.BOLD, 13));
		btnSearch.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 255, 0)));
		btnSearch.setBackground(new Color(255, 204, 51));
		btnSearch.setBounds(1000, 126, 109, 35);
		NewResPane.add(btnSearch);

		AdultSpin = new JSpinner();
		AdultSpin.setModel(new SpinnerNumberModel(1, 1, 9, 1));
		AdultSpin.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		AdultSpin.setBounds(706, 83, 45, 23);
		NewResPane.add(AdultSpin);

		ChildSpin = new JSpinner();
		ChildSpin.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		ChildSpin.setModel(new SpinnerNumberModel(0, 0, 9, 1));
		ChildSpin.setBounds(885, 83, 45, 23);
		NewResPane.add(ChildSpin);

		InfantSpin = new JSpinner();
		InfantSpin.setModel(new SpinnerNumberModel(0, 0, 9, 1));
		InfantSpin.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		InfantSpin.setBounds(1064, 83, 45, 23);
		NewResPane.add(InfantSpin);
		
		lblmessage = new JLabel("");
		lblmessage.setForeground(Color.RED);
		lblmessage.setFont(new Font("Century Gothic", Font.BOLD, 15));
		lblmessage.setBounds(30, 155, 520, 25);
		NewResPane.add(lblmessage);

		JPanel MyBookingsPane = new JPanel();
		TabbedPane.addTab("", null, MyBookingsPane, null);
		TabbedPane.setEnabledAt(1, false);
		MyBookingsPane.setLayout(null);

		JLabel lblLastName = new JLabel("Last Name: ");
		lblLastName.setFont(new Font("Century Gothic", Font.BOLD, 15));
		lblLastName.setBounds(30, 60, 140, 25);
		MyBookingsPane.add(lblLastName);

		JLabel lblBookingNumber = new JLabel("Booking Number:");
		lblBookingNumber.setFont(new Font("Century Gothic", Font.BOLD, 15));
		lblBookingNumber.setBounds(30, 120, 140, 25);
		MyBookingsPane.add(lblBookingNumber);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		textField_1.setColumns(10);
		textField_1.setBounds(180, 113, 230, 35);
		MyBookingsPane.add(textField_1);

		JLabel label_5 = new JLabel("");
		label_5.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\image\\istockphoto-962445754-170667a.jpg"));
		label_5.setBounds(600, 0, 580, 195);
		MyBookingsPane.add(label_5);

		textField = new JTextField();
		textField.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		textField.setColumns(10);
		textField.setBounds(180, 53, 230, 35);
		MyBookingsPane.add(textField);

		JLabel lblMyBookingg = new JLabel("My Booking");
		lblMyBookingg.setFont(new Font("Century Gothic", Font.BOLD, 18));
		lblMyBookingg.setBounds(30, 10, 400, 30);
		MyBookingsPane.add(lblMyBookingg);

		JButton btnNewButton = new JButton("RETRIEVE");
		btnNewButton.addActionListener(e -> {
            /**
             * Button Retrieve checks if the last name and booking number entered by the user match those
             * inside the file and then displys the booking
             */
            String last_name = textField.getText();
            String booking_nr = textField_1.getText();
            Flight result = null;
            ArrayList<Flight> data = getData();
            for(Flight temp_flight:data) {
                if(result!=null) {
                    break;
                }
                if(temp_flight.getPassengers()!= null) {
                    for(Passenger pass:temp_flight.getPassengers()) {
                        if(pass.getLast_name().equalsIgnoreCase(last_name) && temp_flight.getBooking_number().equalsIgnoreCase(booking_nr)) {
                            result = temp_flight;
                            break;
                        }
                    }

                }
            }
            write_to_temp_file(result);
            setVisible(false);
            MyBooking mb = new MyBooking();
            mb.setVisible(true);
        });
		btnNewButton.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 255, 0)));
		btnNewButton.setBackground(new Color(255, 204, 51));
		btnNewButton.setFont(new Font("Century Gothic", Font.BOLD, 13));
		btnNewButton.setBounds(440, 53, 120, 35);
		MyBookingsPane.add(btnNewButton);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		TabbedPane.addTab("", null, panel_1, null);
		TabbedPane.setEnabledAt(2, false);

		JComboBox comboBox = new JComboBox();

		comboBox.setModel(new DefaultComboBoxModel(new String[] {"From", "Tirana (TIA)", "Milan (MXP)", "Bergamo (BGY)", "Verona (VRN)", "Bologna (BLQ)", "Pisa (PSA)", "Rome (FCO)", "Istanbul (IST)"}));
		comboBox.setToolTipText("");
		comboBox.setName("From\r\n");
		comboBox.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		comboBox.setBounds(30, 40, 240, 27);
		panel_1.add(comboBox);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"To", "Tirana (TIA)", "Milan (MXP)", "Bergamo (BGY)", "Verona (VRN)", "Bologna (BLQ)", "Pisa (PSA)", "Rome (FCO)", "Istanbul (IST)"}));
		comboBox_1.setName("To");
		comboBox_1.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		comboBox_1.setBounds(310, 40, 240, 27);
		panel_1.add(comboBox_1);

		radioButtonRound = new JRadioButton("Round Trip");
		radioButtonRound.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				/**
				 * Check if one of the radio buttons is selected
				 */
				radioButtonOneWay.setSelected(false);
				radioButtonRound.setSelected(true);
				radioButtonMulti.setSelected(false);
				rdbtnRoundTrip.setSelected(true);
				rdbtnOneWayTrip.setSelected(false);
				rdbtnMulticityTrip.setSelected(false);
				TabbedPane.setEnabledAt(0, true);
				TabbedPane.setEnabledAt(1, false);
				TabbedPane.setEnabledAt(2, false);
				TabbedPane.setSelectedIndex(0);
			}
		});
		radioButtonRound.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		radioButtonRound.setBounds(650, 40, 100, 23);
		panel_1.add(radioButtonRound);

		radioButtonOneWay = new JRadioButton("One Way Trip");
		radioButtonOneWay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				radioButtonOneWay.setSelected(true);
				radioButtonRound.setSelected(false);
				radioButtonMulti.setSelected(false);
				rdbtnRoundTrip.setSelected(false);
				rdbtnOneWayTrip.setSelected(true);
				rdbtnMulticityTrip.setSelected(false);
				TabbedPane.setEnabledAt(0, true);
				TabbedPane.setEnabledAt(1, false);
				TabbedPane.setEnabledAt(2, false);
				TabbedPane.setSelectedIndex(0);
			}
		});
		radioButtonOneWay.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		radioButtonOneWay.setBounds(820, 40, 110, 23);
		panel_1.add(radioButtonOneWay);

		radioButtonMulti = new JRadioButton("Multi-City Trip");
		radioButtonMulti.addActionListener(arg0 -> {
            radioButtonOneWay.setSelected(false);
            radioButtonRound.setSelected(false);
            radioButtonMulti.setSelected(true);
            rdbtnRoundTrip.setSelected(false);
            rdbtnOneWayTrip.setSelected(false);
            rdbtnMulticityTrip.setSelected(true);
            TabbedPane.setEnabledAt(0, false);
            TabbedPane.setEnabledAt(1, false);
            TabbedPane.setEnabledAt(2, true);
            TabbedPane.setSelectedIndex(2);
        });
		radioButtonMulti.setSelected(true);
		radioButtonMulti.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		radioButtonMulti.setBounds(1000, 40, 109, 23);
		panel_1.add(radioButtonMulti);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(30, 135, 240, 25);
		panel_1.add(dateChooser);

		dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(310, 135, 240, 25);
		panel_1.add(dateChooser_1);

		JLabel label_9 = new JLabel("Adult:");
		label_9.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		label_9.setBounds(650, 87, 46, 20);
		panel_1.add(label_9);

		JLabel label_10 = new JLabel("Child:");
		label_10.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		label_10.setBounds(829, 87, 46, 20);
		panel_1.add(label_10);

		JLabel label_11 = new JLabel("Infant:");
		label_11.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		label_11.setBounds(1000, 87, 46, 20);
		panel_1.add(label_11);

		JButton button = new JButton("SEARCH\r\n");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem().equals("From") || comboBox_1.getSelectedItem().equals("To")
						|| comboBox_2.getSelectedItem().equals("From") || comboBox_3.getSelectedItem().equals("To")
						|| dateChooser.getDate().equals("") || dateChooser_1.getDate().equals("")
						|| spinner.getValue().equals("0")) {
					message.setText("Please fill in every field");
				} else {
					/**
					 * The function saves the information entered by the user and displays it in the new
					 * reservation class
					 */
					String to = comboBox.getSelectedItem().toString();
					String from = comboBox_1.getSelectedItem().toString();
					String multi_city_from = comboBox_2.getSelectedItem().toString();
					Date second_flight_date = dateChooser.getDate();
					String second_flight = DateFormat.getDateInstance().format(second_flight_date);
					Date first_flight_date = dateChooser_1.getDate();
					String first_flight = DateFormat.getDateInstance().format(first_flight_date);
					int nr_of_adults = (Integer) spinner.getValue();
					int nr_of_children = (Integer) spinner_1.getValue();
					int nr_of_infants = (Integer) spinner_2.getValue();
					String type_of_flight = "Multi City Trip";
			        flight = new Flight(to,from,first_flight,second_flight,nr_of_adults,nr_of_children,nr_of_infants,type_of_flight);
			        flight.setMulti_city_from(multi_city_from);
			        if(write_to_temp_file(flight)) {
			        	NewReservation newReservation = new NewReservation();
						dispose();
						newReservation.setVisible(true);
					}
				}
			}
		});
		button.setFont(new Font("Century Gothic", Font.BOLD, 13));
		button.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 255, 0)));
		button.setBackground(new Color(255, 204, 51));
		button.setBounds(1000, 126, 109, 35);
		panel_1.add(button);

		JLabel lblChooseYourFlight = new JLabel("Choose your flight");
		lblChooseYourFlight.setFont(new Font("Century Gothic", Font.BOLD, 18));
		lblChooseYourFlight.setBounds(30, 10, 520, 23);
		panel_1.add(lblChooseYourFlight);

		comboBox_2 = new JComboBox();

		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"From", "Tirana (TIA)", "Milan (MXP)", "Bergamo (BGY)", "Verona (VRN)", "Bologna (BLQ)", "Pisa (PSA)", "Rome (FCO)", "Istanbul (IST)"}));
		comboBox_2.setToolTipText("");
		comboBox_2.setName("From\r\n");
		comboBox_2.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		comboBox_2.setBounds(30, 87, 240, 27);
		panel_1.add(comboBox_2);

		comboBox_3 = new JComboBox();
		comboBox_3.setEnabled(false);
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"To", "Tirana (TIA)", "Milan (MXP)", "Bergamo (BGY)", "Verona (VRN)", "Bologna (BLQ)", "Pisa (PSA)", "Rome (FCO)", "Istanbul (IST)"}));
		comboBox_3.setToolTipText("");
		comboBox_3.setName("From\r\n");
		comboBox_3.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		comboBox_3.setBounds(310, 87, 240, 27);
		panel_1.add(comboBox_3);

		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 1, 9, 1));
		spinner.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		spinner.setBounds(705, 85, 45, 23);
		panel_1.add(spinner);

		spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerNumberModel(0, 0, 9, 1));
		spinner_1.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		spinner_1.setBounds(885, 86, 45, 23);
		panel_1.add(spinner_1);

		spinner_2 = new JSpinner();
		spinner_2.setModel(new SpinnerNumberModel(0, 0, 9, 1));
		spinner_2.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		spinner_2.setBounds(1064, 86, 45, 23);
		panel_1.add(spinner_2);
		
		message = new JLabel("");
		message.setForeground(Color.RED);
		message.setFont(new Font("Century Gothic", Font.BOLD, 15));
		message.setBounds(650, 135, 340, 30);
		panel_1.add(message);

		JPanel panel = new JPanel();
		panel.setBounds(0, 360, 1280, 40);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewRes = new JLabel("New Reservation");
		lblNewRes.setBackground(new Color(204, 204, 255));
		lblNewRes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblNewRes.setBackground(new Color(153, 153, 204));
				lblNewRes.setForeground(Color.WHITE);
			}

			public void mouseExited(MouseEvent arg0) {
				lblNewRes.setBackground(new Color(204, 204, 255));
				lblNewRes.setForeground(Color.BLACK);
			}

			public void mouseClicked(MouseEvent arg0) {
				lblNewRes.setBackground(new Color(51, 51, 153));
				lblNewRes.setForeground(Color.WHITE);
				TabbedPane.setEnabledAt(0, true);
				TabbedPane.setEnabledAt(2, false);
				TabbedPane.setEnabledAt(1, false);
				TabbedPane.setSelectedIndex(0);
			}
		});
		lblNewRes.setFont(new Font("Century Gothic", Font.BOLD, 17));
		lblNewRes.setOpaque(true);
		lblNewRes.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewRes.setBounds(1, 0, 639, 40);
		panel.add(lblNewRes);

		JLabel lblMyBooking = new JLabel("My Bookings");
		lblMyBooking.setBackground(new Color(204, 204, 255));
		lblMyBooking.setOpaque(true);
		lblMyBooking.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblMyBooking.setBackground(new Color(153, 153, 204));
				lblMyBooking.setForeground(Color.WHITE);
			}

			public void mouseExited(MouseEvent e) {
				lblMyBooking.setBackground(new Color(204, 204, 255));
				lblMyBooking.setForeground(Color.BLACK);
			}

			public void mouseClicked(MouseEvent e) {
				lblMyBooking.setBackground(new Color(51, 51, 153));
				lblMyBooking.setForeground(Color.WHITE);
				TabbedPane.setEnabledAt(1, true);
				TabbedPane.setEnabledAt(0, false);
				TabbedPane.setEnabledAt(2, false);
				TabbedPane.setSelectedIndex(1);
			}
		});
		lblMyBooking.setFont(new Font("Century Gothic", Font.BOLD, 17));
		lblMyBooking.setHorizontalAlignment(SwingConstants.CENTER);
		lblMyBooking.setBounds(641, 0, 638, 40);
		panel.add(lblMyBooking);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setOpaque(true);
		separator.setBounds(640, 0, 1, 38);
		panel.add(separator);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 401, 1280, 1);
		contentPane.add(separator_2);

		JLabel lblFlyhighAirways = new JLabel("FLYHIGH");
		lblFlyhighAirways.setFont(new Font("Baskerville Old Face", Font.BOLD | Font.ITALIC, 33));
		lblFlyhighAirways.setBackground(new Color(153, 153, 255));
		lblFlyhighAirways.setBounds(26, 19, 182, 51);
		contentPane.add(lblFlyhighAirways);

		JLabel label_3 = new JLabel("");
		label_3.setOpaque(true);
		label_3.setFocusCycleRoot(true);
		label_3.setFocusTraversalPolicyProvider(true);
		label_3.setIcon(
				new ImageIcon("C:\\Users\\user\\Desktop\\image\\WhatsApp Image 2022-01-25 at 11.03.09 PM.jpeg"));
		label_3.setBounds(0, 91, 1280, 270);
		contentPane.add(label_3);

		JLabel lblAirways = new JLabel("AIRWAYS");
		lblAirways.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblAirways.setBounds(145, 51, 161, 40);
		contentPane.add(lblAirways);

		JLabel label = new JLabel("+355 68 999 0000");
		label.setFont(new Font("Century Gothic", Font.BOLD, 15));
		label.setBounds(1000, 40, 122, 30);
		contentPane.add(label);

		JSeparator separator_3 = new JSeparator();
		separator_3.setOpaque(true);
		separator_3.setBounds(1140, 30, 1, 50);
		contentPane.add(separator_3);

		JSeparator separator_4 = new JSeparator();
		separator_4.setOpaque(true);
		separator_4.setBounds(964, 31, 1, 50);
		contentPane.add(separator_4);

		JLabel lblLoginAsAdmin = new JLabel("Login as admin");
		lblLoginAsAdmin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setVisible(false);
				AdminLog login = new AdminLog();
				login.setVisible(true);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				lblLoginAsAdmin.setForeground(Color.DARK_GRAY);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				lblLoginAsAdmin.setForeground(new Color(0, 0, 0));
			}
		});
		lblLoginAsAdmin.setFont(new Font("Century Gothic", Font.BOLD, 15));
		lblLoginAsAdmin.setBounds(818, 40, 122, 30);
		contentPane.add(lblLoginAsAdmin);

		Panel panel_2 = new Panel();
		panel_2.setBounds(0, 645, 1280, 115);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel(
				"\u00A9 2022 FlyHigh Airways    Terms of use    Privacy policy    ADM policy    Contact    customercare@flyhigh.al");
		lblNewLabel_1.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(300, 10, 680, 35);
		panel_2.add(lblNewLabel_1);

		JLabel label_2 = new JLabel("FLYHIGH");
		label_2.setFont(new Font("Baskerville Old Face", Font.BOLD | Font.ITALIC, 28));
		label_2.setBackground(new Color(153, 153, 255));
		label_2.setBounds(540, 43, 153, 51);
		panel_2.add(label_2);

		JLabel label_12 = new JLabel("AIRWAYS");
		label_12.setFont(new Font("Times New Roman", Font.BOLD, 16));
		label_12.setBounds(652, 64, 88, 40);
		panel_2.add(label_12);

		JLabel label_13 = new JLabel("");
		label_13.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Milan", TitledBorder.TRAILING,
				TitledBorder.BOTTOM, new Font("Century Gothic", Font.PLAIN, 14), new Color(255, 255, 255)));
		label_13.setIcon(new ImageIcon("src\\image\\milan.jpg"));
		label_13.setBounds(54, 423, 150, 200);
		contentPane.add(label_13);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 411, 2, 2);
		contentPane.add(scrollPane);

		JLabel label_14 = new JLabel("");
		label_14.setIcon(new ImageIcon("src\\image\\rome.jpg"));
		label_14.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Rome", TitledBorder.TRAILING,
				TitledBorder.BOTTOM, new Font("Century Gothic", Font.PLAIN, 14), new Color(255, 255, 255)));
		label_14.setBounds(258, 423, 150, 200);
		contentPane.add(label_14);

		JLabel label_15 = new JLabel("");
		label_15.setIcon(new ImageIcon("src\\image\\istanbul.jpg"));
		label_15.setBorder(
				new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Istanbul", TitledBorder.TRAILING,
						TitledBorder.BOTTOM, new Font("Century Gothic", Font.PLAIN, 14), new Color(255, 255, 255)));
		label_15.setBounds(462, 423, 150, 200);
		contentPane.add(label_15);

		JLabel label_16 = new JLabel("");
		label_16.setIcon(new ImageIcon("src\\image\\pisa.jpg"));
		label_16.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Pisa", TitledBorder.TRAILING,
				TitledBorder.BOTTOM, new Font("Century Gothic", Font.PLAIN, 14), new Color(255, 255, 255)));
		label_16.setBounds(666, 423, 150, 200);
		contentPane.add(label_16);

		JLabel label_17 = new JLabel("");
		label_17.setIcon(new ImageIcon("src\\image\\verona.jpg"));
		label_17.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Verona", TitledBorder.TRAILING,
				TitledBorder.BOTTOM, new Font("Century Gothic", Font.PLAIN, 14), new Color(255, 255, 255)));
		label_17.setBounds(870, 423, 150, 200);
		contentPane.add(label_17);

		JLabel label_18 = new JLabel("");
		label_18.setIcon(new ImageIcon("src\\image\\bergamo.jpg"));
		label_18.setBorder(
				new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Bergamo", TitledBorder.TRAILING,
						TitledBorder.BOTTOM, new Font("Century Gothic", Font.PLAIN, 14), new Color(255, 255, 255)));
		label_18.setBounds(1074, 423, 150, 200);
		contentPane.add(label_18);
		comboBox.addActionListener(e -> {
            /**
             * Checks if the same place is chosen for the departure and arrival
             */
            if(comboBox.getSelectedItem().equals(comboBox_1.getSelectedItem())){
                comboBox.setSelectedIndex(0);
            }
            if(comboBox.getSelectedItem().equals(comboBox_2.getSelectedItem())){
                comboBox.setSelectedIndex(0);
            }
            comboBox_3.setSelectedIndex(comboBox.getSelectedIndex());
        });
		comboBox_1.addActionListener(e -> {
            if(comboBox.getSelectedItem().equals(comboBox_1.getSelectedItem())){
                comboBox_1.setSelectedIndex(0);
            }
        });
		comboBox_2.addActionListener(e -> {
            if(comboBox.getSelectedItem().equals(comboBox_2.getSelectedItem())){
                comboBox_2.setSelectedIndex(0);
            }
        });
	}
}