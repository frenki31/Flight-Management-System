package inheritance;

import java.util.ArrayList;
import java.util.Random;
/**
 * Class Flight saves all the information of the flight as a database
 * @author user
 *
 */
public class Flight {
	private String to,from,multi_city_from,first_flight, second_flight, type_of_flight,card_number,card_holder,booking_number;
	private int nr_of_adults, nr_of_children, nr_of_infants;
	private ArrayList<Passenger> passengers = new ArrayList<>();
	private double total;
	private String status = "active";

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

        this.booking_number = random.ints(leftLimit, rightLimit + 1)
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}