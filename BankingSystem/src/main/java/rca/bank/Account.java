package rca.bank;

public class Account {
	private int accountId;
	private String username;
	private double amount;

	public Account() {

	}

	public Account(int accountId, String username, double amount) {
		super();
		this.accountId = accountId;
		this.username = username;
		this.amount = amount;
	}



	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
