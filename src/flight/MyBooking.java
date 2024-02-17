package flight;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.google.gson.Gson;
import inheritance.Flight;
import inheritance.Passenger;

import javax.swing.JSeparator;
/**
 * Class MyBooking is the interface of all tickets with the same booking number
 * @author user
 *
 */
public class MyBooking extends JFrame {
	private Flight flight;
    Display menu = new Display();
	/**
	 * Create the frame.
	 */
	public void getData() {
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
			flight = gson.fromJson(reader, Flight.class);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public MyBooking() {
		getData();
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
		
		JLabel lblNewLabel_1 = new JLabel("\u00A9 2022 FlyHigh Airways    Terms of use    Privacy policy    ADM policy    Contact    customercare@flyhigh.al");
		lblNewLabel_1.setBounds(300, 670, 680, 35);
		contentPane.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 90, 1280, 560);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblYourBooking = new JLabel("My Booking");
		lblYourBooking.setFont(new Font("Century Gothic", Font.BOLD, 30));
		lblYourBooking.setBounds(40, 40, 600, 40);
		panel.add(lblYourBooking);
		
		JLabel lblNewLabel_2 = new JLabel("Welcome");
		lblNewLabel_2.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(879, 40, 77, 24);
		panel.add(lblNewLabel_2);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(1105, 40, 1, 25);
		panel.add(separator);
		
		JLabel lblLogout = new JLabel("Logout");
		lblLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setVisible(false);
				menu.setVisible(true);
			}
		});
		lblLogout.setFont(new Font("Century Gothic", Font.BOLD, 14));
		lblLogout.setBounds(1116, 40, 77, 24);
		panel.add(lblLogout);
		
		JLabel lblYourCurrentBooking = new JLabel("Your current booking");
	
		lblYourCurrentBooking.setBackground(Color.WHITE);
		lblYourCurrentBooking.setFont(new Font("Century Gothic", Font.BOLD, 30));
		lblYourCurrentBooking.setBounds(40, 130, 730, 40);
		panel.add(lblYourCurrentBooking);
		
		JLabel lblBookingNumber = new JLabel("Booking Number: ");
		lblBookingNumber.setFont(new Font("Century Gothic", Font.BOLD, 25));
		lblBookingNumber.setBounds(780, 132, 219, 40);
		panel.add(lblBookingNumber);
		
		JLabel labelBookingNumber = new JLabel(flight.getBooking_number());
		labelBookingNumber.setFont(new Font("Century Gothic", Font.PLAIN, 25));
		labelBookingNumber.setBounds(1015, 130, 225, 40);
		panel.add(labelBookingNumber);
		
		JLabel labelSuffix = new JLabel(flight.getPassengers().get(0).getSirName());
		labelSuffix.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		labelSuffix.setBounds(966, 40, 130, 24);
		panel.add(labelSuffix);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(40, 180, 600, 190);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("Flight Itinerary");
		label.setOpaque(true);
		label.setFont(new Font("Century Gothic", Font.BOLD, 15));
		label.setBackground(new Color(153, 153, 255));
		label.setBounds(0, 0, 600, 25);
		panel_1.add(label);
		
		JLabel lblFirstFlight = new JLabel(flight.getFrom() + " - "+ flight.getTo());
		
		
		lblFirstFlight.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		lblFirstFlight.setBounds(30, 50, 250, 25);
		panel_1.add(lblFirstFlight);
		
		JLabel labelSecondFlight = new JLabel();
		if(flight.getType_of_flight().equals("One Way Trip")) {
			labelSecondFlight.setText(null);
		}
		else if(flight.getType_of_flight().equals("Multi City Trip")) {
			labelSecondFlight.setText(flight.getMulti_city_from()+" - "+ flight.getFrom());
		}
		else {
			labelSecondFlight.setText(flight.getTo() + " - "+ flight.getFrom());
		}
		
		labelSecondFlight.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		labelSecondFlight.setBounds(320, 50, 250, 25);
		panel_1.add(labelSecondFlight);
		
		JLabel labelTime1 = new JLabel(flight.getFirst_flight());
		labelTime1.setForeground(new Color(255, 0, 0));
		labelTime1.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		labelTime1.setBounds(30, 90, 250, 25);
		panel_1.add(labelTime1);
		
		JLabel labelTime2 = new JLabel(flight.getSecond_flight());
		labelTime2.setForeground(new Color(255, 0, 0));
		labelTime2.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		labelTime2.setBounds(320, 90, 250, 25);
		panel_1.add(labelTime2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setLayout(null);
		panel_2.setBounds(640, 180, 600, 190);
		panel.add(panel_2);
		
		JLabel lblPassengerDetails = new JLabel("Passenger Details");
		lblPassengerDetails.setOpaque(true);
		lblPassengerDetails.setFont(new Font("Century Gothic", Font.BOLD, 15));
		lblPassengerDetails.setBackground(new Color(153, 153, 255));
		lblPassengerDetails.setBounds(0, 0, 600, 25);
		panel_2.add(lblPassengerDetails);
		
		JLabel lblPassenger1 = new JLabel("");
		lblPassenger1.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		lblPassenger1.setBounds(30, 50, 250, 25);
		panel_2.add(lblPassenger1);
		
		JLabel labelPassenger2 = new JLabel("");
		labelPassenger2.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		labelPassenger2.setBounds(320, 50, 250, 25);
		panel_2.add(labelPassenger2);
		
		JLabel labelPassenger3 = new JLabel("");
		labelPassenger3.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		labelPassenger3.setBounds(30, 95, 250, 25);
		panel_2.add(labelPassenger3);
		
		JLabel labelPassenger4 = new JLabel("");
		labelPassenger4.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		labelPassenger4.setBounds(320, 95, 250, 25);
		panel_2.add(labelPassenger4);
		
		JLabel labelPassenger5 = new JLabel("");
		labelPassenger5.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		labelPassenger5.setBounds(30, 140, 250, 25);
		panel_2.add(labelPassenger5);
		
		JLabel labelPassenger6 = new JLabel("");
		labelPassenger6.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		labelPassenger6.setBounds(320, 140, 250, 25);
		panel_2.add(labelPassenger6);
		
		JLabel[] labels = {lblPassenger1,labelPassenger2,labelPassenger3,labelPassenger4,labelPassenger5,labelPassenger6};
		int index = 0;
		/**
		 * If there is more than one passenger it iterates through the arrayList to display all
		 */
		for(Passenger pass:flight.getPassengers()) {
			labels[index].setText(pass.getName());
			index++;
		}
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBounds(40, 370, 600, 150);
		panel.add(panel_3);
		
		JLabel lblPaymentDetails = new JLabel("Payment Details");
		lblPaymentDetails.setOpaque(true);
		lblPaymentDetails.setFont(new Font("Century Gothic", Font.BOLD, 15));
		lblPaymentDetails.setBackground(new Color(153, 153, 255));
		lblPaymentDetails.setBounds(0, 0, 600, 25);
		panel_3.add(lblPaymentDetails);
		
		JLabel lblCardHolder = new JLabel(flight.getCard_holder());
		lblCardHolder.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		lblCardHolder.setBounds(30, 80, 170, 25);
		panel_3.add(lblCardHolder);
		
		JLabel label_1 = new JLabel(flight.getCard_number());
		label_1.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		label_1.setBounds(215, 80, 170, 25);
		panel_3.add(label_1);

        JLabel label_2 = new JLabel(flight.getTotal() + "$");
		label_2.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		label_2.setBounds(400, 80, 170, 25);
		panel_3.add(label_2);
		
		JLabel lblCardHolder_1 = new JLabel("Card Holder:");
		lblCardHolder_1.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		lblCardHolder_1.setBounds(30, 45, 170, 25);
		panel_3.add(lblCardHolder_1);
		
		JLabel lblCardNumber = new JLabel("Card Number:");
		lblCardNumber.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		lblCardNumber.setBounds(215, 44, 170, 25);
		panel_3.add(lblCardNumber);
		
		JLabel lblAmount = new JLabel("Amount:");
		lblAmount.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		lblAmount.setBounds(400, 44, 170, 25);
		panel_3.add(lblAmount);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.setBounds(640, 370, 600, 150);
		panel.add(panel_4);
		
		JLabel lblExtras = new JLabel("Extras");
		lblExtras.setOpaque(true);
		lblExtras.setFont(new Font("Century Gothic", Font.BOLD, 15));
		lblExtras.setBackground(new Color(153, 153, 255));
		lblExtras.setBounds(0, 0, 600, 25);
		panel_4.add(lblExtras);
		
		JLabel lblLuggageKg = new JLabel("Luggage:                                                                         20 kg per passenger");
		lblLuggageKg.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		lblLuggageKg.setBounds(40, 70, 520, 25);
		panel_4.add(lblLuggageKg);
		if(flight.getStatus().equalsIgnoreCase("canceled")) {
			lblYourCurrentBooking.setText("Canceled ticket");
			label_2.setText("-"+flight.getTotal()+"$");
		}
	}
}