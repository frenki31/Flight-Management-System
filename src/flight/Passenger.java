package flight;
/**
 * Class Passenger saves the passenger details
 * @author user
 *
 */
public class Passenger {
	private String suffix;
	private String first_name;
	private String last_name;
	private String date_of_birth;
	private String email;
	private String city;
	private String street;
	private String country;
	private String phone_no;

	public Passenger(String suffix, String first_name, String last_name, String email,
			String city, String street, String country, String phone_no) {
		setCity(city);
		setCountry(country);
		setEmail(email);
		setFirst_name(first_name);
		setLast_name(last_name);
		setPhone_no(phone_no);
		setStreet(street);
		setSuffix(suffix);
		// TODO Auto-generated constructor stub
	}

	public Passenger() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * Getters and setters
	 * @return
	 */
	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(String date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone_no() {
		return phone_no;
	}

	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}

}
