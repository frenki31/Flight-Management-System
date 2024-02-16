package flight;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import inheritance.Flight;
import inheritance.Passenger;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import static flight.NewReservation.tempFlight;

/**
 * Class Admin is the database where administrators can see and make actions on the bookings
 * @author user
 */
public class Admin extends JFrame {

    private final JTable table;
	private ArrayList<Flight> data;
	/**
	 * Create the frame.
	 */
	protected boolean write_to_file(ArrayList<Flight> flights) {

		String yourFile = "flights.json";
		File tmpDir = new File(yourFile);
		boolean exists = tmpDir.exists();

		try {
			FileWriter fileWriter;
			if (!exists) {
                new FileWriter("flights.json");
            }
            new FileReader("flights.json");
            Gson gson = new Gson();
            new TypeToken<ArrayList<Flight>>() {};
            fileWriter = new FileWriter("flights.json");
			gson.toJson(flights, fileWriter);
			fileWriter.close();
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	protected void write_to_temp_file(Flight flight) {
		tempFlight(flight);
	}

	public void getData() {
		String yourFile = "flights.json";
		File tmpDir = new File(yourFile);
		boolean exists = tmpDir.exists();

		try {
			java.lang.reflect.Type listType = new TypeToken<ArrayList<Flight>>() {}.getType();
			ArrayList<Flight> p;
			p = getFlights(exists, listType);
			data = p;
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	static ArrayList<Flight> getFlights(boolean exists, java.lang.reflect.Type listType) throws IOException {
		ArrayList<Flight> p;
		FileWriter fileWriter;
		if (!exists) {
			fileWriter = new FileWriter("flights.json");
			fileWriter.close();
		}

		Reader reader = new FileReader("flights.json");
		Gson gson = new Gson();

        p = gson.fromJson(reader, listType);
		return p;
	}

	public Admin() {
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

		JLabel lblNewLabel_1 = new JLabel(
				"\u00A9 2022 FlyHigh Airways    Terms of use    Privacy policy    ADM policy    Contact    customercare@flyhigh.al");
		lblNewLabel_1.setBounds(300, 670, 680, 35);
		contentPane.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel panel = new JPanel();
		panel.setBounds(0, 90, 1280, 560);
		contentPane.add(panel);
		panel.setLayout(null);

		table = new JTable();
		table.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setModel(new DefaultTableModel(
				new Object[][] { { "Booking Number", "Passenger", "First Flight", "Departure Date", "Second Flight",
						"Arrival Date", "Status" }, },
				new String[] { "Booking Number", "Passenger", "Departure Airport", "Departure Date", "Arrival Airport",
						"Arrival Date", "Status" }));
		table.getColumnModel().getColumn(0).setPreferredWidth(45);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(140);
		table.getColumnModel().getColumn(3).setPreferredWidth(80);
		table.getColumnModel().getColumn(4).setPreferredWidth(140);
		table.getColumnModel().getColumn(5).setPreferredWidth(35);
		table.getColumnModel().getColumn(6).setPreferredWidth(35);
		table.setRowHeight(25);
		table.setBounds(50, 100, 800, 400);
		panel.add(table);

		JLabel label = new JLabel("Welcome Admin");
		label.setFont(new Font("Century Gothic", Font.BOLD, 30));
		label.setBounds(50, 40, 600, 40);
		panel.add(label);

		JButton btnNewButton = new JButton("DISPLAY TICKETS");
		btnNewButton.addActionListener(new ActionListener() {
			/**
			 * From the file read the information and display it in the table
			 */
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if (model.getRowCount() > 0) {
					for (int i = model.getRowCount() - 1; i > -1; i--) {
						model.removeRow(i);
					}
				}
				model.addRow(new Object[] { "Booking Number", "Passenger", "First Flight", "Departure Date",
						"Second Flight", "Arrival Date", "Status" });
				String b_number;
				String passenger;
				String to;
				String first_flight;
				String from = null;
				String second_flight;
				for (Flight fl : data) {
					b_number = fl.getBooking_number();
					to = fl.getFrom() + " - " + fl.getTo();
					if (fl.getType_of_flight().equals("Multi City Trip")) {
						from = fl.getMulti_city_from() + " - " + fl.getFrom();
					} else if (fl.getType_of_flight().equals("Round Trip")) {
						from = fl.getTo() + " - " + fl.getFrom();
					}
					first_flight = fl.getFirst_flight();
					second_flight = fl.getSecond_flight();
					for (Passenger pass : fl.getPassengers()) {
						passenger = pass.getFirst_name() + " " + pass.getLast_name();
						model.addRow(new Object[] { b_number, passenger, to, first_flight, from, second_flight,
								fl.getStatus() });
					}
				}
			}
		});
		btnNewButton.setFont(new Font("Century Gothic", Font.BOLD, 13));
		btnNewButton.setBorder(new MatteBorder(1, 1, 1, 1, new Color(255, 255, 0)));
		btnNewButton.setBackground(new Color(255, 204, 51));
		btnNewButton.setBounds(1041, 100, 120, 30);
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("BACK");
		btnNewButton_1.addActionListener(e -> {
            dispose();
            Display ds = new Display();
            ds.setVisible(true);
        });
		btnNewButton_1.setFont(new Font("Century Gothic", Font.BOLD, 13));
		btnNewButton_1.setBorder(new MatteBorder(1, 1, 1, 1, new Color(255, 255, 0)));
		btnNewButton_1.setBackground(new Color(255, 204, 51));
		btnNewButton_1.setBounds(1041, 470, 120, 30);
		panel.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("CANCEL TICKET");
		btnNewButton_2.addActionListener(new ActionListener() {
			/**
			 * Cancel the ticket
			 */
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
                table.getValueAt(row, 0).toString();
                data = (ArrayList) data.stream().map(x -> {
					x.setStatus("canceled");
					return x;
				}).collect(Collectors.toList());
				write_to_file(data);
			}
		});
		btnNewButton_2.setFont(new Font("Century Gothic", Font.BOLD, 13));
		btnNewButton_2.setBorder(new MatteBorder(1, 1, 1, 1, new Color(255, 255, 0)));
		btnNewButton_2.setBackground(new Color(255, 204, 51));
		btnNewButton_2.setBounds(1041, 224, 120, 30);
		panel.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("SHOW TICKET");
		btnNewButton_3.addActionListener(new ActionListener() {
			/**
			 * Display the booking
			 */
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				String b_nr = table.getValueAt(row, 0).toString();
				for (Flight fl : data) {
					if (fl.getBooking_number().equalsIgnoreCase(b_nr)) {
						write_to_temp_file(fl);
						MyBooking mb = new MyBooking();
						mb.setVisible(true);
					}
				}

			}
		});
		btnNewButton_3.setFont(new Font("Century Gothic", Font.BOLD, 13));
		btnNewButton_3.setBorder(new MatteBorder(1, 1, 1, 1, new Color(255, 255, 0)));
		btnNewButton_3.setBackground(new Color(255, 204, 51));
		btnNewButton_3.setBounds(1041, 340, 120, 30);
		panel.add(btnNewButton_3);
	}
}
