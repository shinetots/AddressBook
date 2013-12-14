import java.util.StringTokenizer;

public class Contacts {
	/**
	 * Contacts fields
	 */
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String phone;
	
	/**
	 * default constructor
	 */
	public Contacts() {
	}

	/**
	 * constructor for Contacts class which contains all fields
	 */
	public Contacts(String firstName, String lastName, String address, String city, String state, String zip, String phone) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phone = phone;
	}
	/** 
     *  @return the person's first name
     */
	public String getFirstName() {
		return firstName;
	}
	/** 
     *  @return the person's last name
     */
	public String getLastName() {
		return lastName;
	}
	/** 
     *  @return the person's address
     */
	public String getAddress() {
		return address;
	}
	/** 
     *  @return the person's city address
     */
	public String getCity(){
		return city;
	}
	/** 
     *  @return the person's state address
     */
	public String getState(){
		return state;
	}
	/** 
     *  @return the person's zip code
     */
	public String getZip(){
		return zip;
	}
	/** 
     *  @return the person's phone number
     */
	public String getPhone() {
		return phone;
	}
	/** 
     * Set person's first name
     */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/** 
     * Set person's last name
     */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/** 
     * Set person's address
     */
	public void setAddress(String address) {
		this.address = address;
	}
	/** 
     * Set person's city addresss
     */
	public void setCity(String city){
		this.city = city;
	}
	/** 
     * Set person's state address
     */
	public void setState(String state){
		this.state = state;
	}
	/** 
     * Set person's zip code
     */
	public void setZip(String zip){
		this.zip = zip;
	}
	/** 
     * Set person's phone number
     */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/** 
     * @return person's first name
     * @return person's last name
     * @return person's address
     * @return person's city address
     * @return person's state address
     * @return person's zip code
     * @return person's phone number
     */
	public String serialize() {
		return String.format("%s|%s|%s|%s|%s|%s|%s", getFirstName(), getLastName(), getAddress(), getCity(), getState(), getZip(), getPhone());
	}
	/**
	 * Separates person's first name, last name, address, city, state, zip code and phone number using "|" token.
	 * */
	public void deserialize(String serial) {
		StringTokenizer token = new StringTokenizer(serial, "|");

		setFirstName(token.nextToken());
		setLastName(token.nextToken());
		setAddress(token.nextToken());
		setCity(token.nextToken());
		setState(token.nextToken());
		setZip(token.nextToken());
		setPhone(token.nextToken());
	}
}