package flight;

import java.util.ArrayList;
import java.util.Random;
/**
 * Class Flight saves all the information of the flight as a database
 * @author user
 *
 */
public class Flight {

	private String to; //final destination
	private String from; //starting point
	private String multi_city_from; //starting point in a multi city booking
	private String first_flight; //first flight date
	private String second_flight; //second flight date
	private int nr_of_adults;
	private int nr_of_children;
	private int nr_of_infants;
	private String type_of_flight;
	private ArrayList<Passenger> passengers = new ArrayList<Passenger>();
	private double total; //total price
	private String card_number;
	private String card_holder;
	private String booking_number;
	private String status = "active";
	
	public Flight() {
	}
	/**
	 * Constructor with type of data we want to save
	 * @param to
	 * @param from
	 * @param first_flight
	 * @param second_flight
	 * @param nr_of_adults
	 * @param nr_of_children
	 * @param nr_of_infants
	 * @param type_of_flight
	 */
	public Flight(String to, String from, String first_flight, String second_flight, int nr_of_adults,
			int nr_of_children, int nr_of_infants,String type_of_flight) {
	setTo(to);
	setFrom(from);
	setFirst_flight(first_flight);
	setSecond_flight(second_flight);
	setNr_of_adults(nr_of_adults);
	setNr_of_children(nr_of_children);
	setNr_of_infants(nr_of_infants);
	setType_of_flight(type_of_flight);
	generateRandomString();
	}
	
	public Flight(String to, String from, String first_flight, String second_flight, int nr_of_adults,
			int nr_of_children, int nr_of_infants,String type_of_flight,ArrayList<Passenger> passengers) {
	setTo(to);
	setFrom(from);
	setFirst_flight(first_flight);
	setSecond_flight(second_flight);
	setNr_of_adults(nr_of_adults);
	setNr_of_children(nr_of_children);
	setNr_of_infants(nr_of_infants);
	setType_of_flight(type_of_flight);
	setPassengers(passengers);
	generateRandomString();
	}
	
	public int getNrOfPassengers() {
		return nr_of_adults+nr_of_children+nr_of_infants;
	}
	/**
	 * Method to calculate the total price
	 * @param ticket_price
	 * @return
	 */
	public double calc_total(double ticket_price) {
		double total = 0;
		total += ticket_price * nr_of_adults;
		total += ticket_price * nr_of_children * 0.75;
		total += ticket_price * nr_of_infants * 0.1;
		this.setTotal(total);
		
		return total;
	}
	/**
	 * Method that generates a random string
	 */
	public void generateRandomString() {
	    int leftLimit = 97; // letter 'a'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 6;
	    Random random = new Random();

	    String generatedString = random.ints(leftLimit, rightLimit + 1)
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString(); 
	    this.booking_number = generatedString;
	}
	/**
	 * getters and setters
	 * @return
	 */
	public String getMulti_city_from() {
		return multi_city_from;
	}
	public void setMulti_city_from(String multi_city_from) {
		this.multi_city_from = multi_city_from;
	}

	public ArrayList<Passenger> getPassengers() {
		return passengers;
	}
	public void setPassengers(ArrayList<Passenger> passengers) {
		this.passengers = passengers;
	}

	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}

	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}

	public String getFirst_flight() {
		return first_flight;
	}
	public void setFirst_flight(String first_flight) {
		this.first_flight = first_flight;
	}

	public String getSecond_flight() {
		return second_flight;
	}
	public void setSecond_flight(String second_flight) {
		this.second_flight = second_flight;
	}

	public int getNr_of_adults() {
		return nr_of_adults;
	}
	public void setNr_of_adults(int nr_of_adults) {
		this.nr_of_adults = nr_of_adults;
	}

	public int getNr_of_children() {
		return nr_of_children;
	}
	public void setNr_of_children(int nr_of_children) {
		this.nr_of_children = nr_of_children;
	}

	public int getNr_of_infants() {
		return nr_of_infants;
	}
	public void setNr_of_infants(int nr_of_infants) {
		this.nr_of_infants = nr_of_infants;
	}

	public String getType_of_flight() {
		return type_of_flight;
	}
	public void setType_of_flight(String type_of_flight) {
		this.type_of_flight = type_of_flight;
	}

	public String getCard_number() {
		return card_number;
	}
	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}
	
	public String getCard_holder() {
		return card_holder;
	}
	public void setCard_holder(String card_holder) {
		this.card_holder = card_holder;
	}
	
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	public String getBooking_number() {
		return booking_number;
	}
	public void setBooking_number(String booking_number) {
		this.booking_number = booking_number;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}