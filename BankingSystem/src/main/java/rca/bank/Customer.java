package rca.bank;

public class Customer {

	private String username;
	private String password;
	private String account;
	private double  balance;

	public Customer() {}

	public Customer(String username, String password) {
		super();
		this.username = username;
		this.password = password;

	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isValidUsername() {
		return username != null && username.length() >= 5 ;
	}

	public boolean isValidPassword() {
		return password != null && password.length() >=5;
	}

	public boolean isValidCustomer() {
		return isValidUsername() && isValidPassword();
	}


}
