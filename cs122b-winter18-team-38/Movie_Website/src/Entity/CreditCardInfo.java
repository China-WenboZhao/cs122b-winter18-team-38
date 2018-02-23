package Entity;

public class CreditCardInfo {

	private String firstName;
	private String lastName;
	private String cc_number;
	private String cc_expiration;
	
	public CreditCardInfo() {
		// TODO Auto-generated constructor stub
	}

	public CreditCardInfo(String firstName, String lastName, String cc_number, String cc_expiration) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.cc_number = cc_number;
		this.cc_expiration = cc_expiration;
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

	public String getCc_number() {
		return cc_number;
	}

	public void setCc_number(String cc_number) {
		this.cc_number = cc_number;
	}

	public String getCc_expiration() {
		return cc_expiration;
	}

	public void setCc_expiration(String cc_expiration) {
		this.cc_expiration = cc_expiration;
	}

	
}
