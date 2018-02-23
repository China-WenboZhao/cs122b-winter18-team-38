package Entity;

/*
 * This Employee class only has the Employeename field in this example.
 * 
 * However, in the real project, this Employee class can contain many more things,
 * for example, the Employee's shopping cart items.
 * 
 */
public class Employee {

	private String email;
	private String password;
	private String fullname;

	public Employee(String email) {
		super();
		this.email = email;
	}
	
	public Employee(String email, String password, String fullname) {
		super();
		this.email = email;
		this.password = password;
		this.fullname = fullname;
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

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

}
