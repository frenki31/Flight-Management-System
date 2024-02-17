package inheritance;

public class Passenger extends Person{
	private String email;
	private String city;
	private String street;
	private String country;
	private String phone_no;

	public Passenger(String suffix, String first_name, String last_name, String email,
			String city, String street, String country, String phone_no) {
		super(suffix,first_name,last_name);
		setCity(city);
		setCountry(country);
		setEmail(email);
		setPhone_no(phone_no);
		setStreet(street);
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
	@Override
	public String getSirName(){
		return super.getSuffix()+" "+super.getSirName();
	}
	@Override
	public String getName(){
		return super.getSuffix()+" "+super.getName();
	}
}