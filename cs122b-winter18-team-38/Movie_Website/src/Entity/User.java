package Entity;

/*
 * This User class only has the username field in this example.
 * 
 * However, in the real project, this User class can contain many more things,
 * for example, the user's shopping cart items.
 * 
 */
public class User {

	private String email;
	private String password;
	private String id;
	private String firstName;
	private String lastName;
	private String ccId;
	private String address;

	public User(String email) {
		super();
		this.email = email;
	}
	
	public User(String email, String password, String id, String firstName, String lastName, String ccId,
			String address) {
		super();
		this.email = email;
		this.password = password;
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.ccId = ccId;
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCcId() {
		return ccId;
	}

	public void setCcId(String ccId) {
		this.ccId = ccId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
