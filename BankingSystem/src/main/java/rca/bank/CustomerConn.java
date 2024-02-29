package rca.bank;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class CustomerConn {
	private Connection connection;

	public CustomerConn(Connection connection) {
		this.connection = connection;
	}


	 	private static final String  url = "jdbc:mysql://localhost:3306/banking_system";
	    private static final String username= "root";
	    private static final String password = "";

	    public static Connection getConnection() throws SQLException {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver"); // Adjust the driver class name as needed
	            return DriverManager.getConnection(url, username, password);
	        } catch (ClassNotFoundException e) {
	            throw new SQLException("MySQL JDBC Driver not found", e);
	        }
	    }





	public boolean addCustomer(Customer customer) throws SQLException {
		String query = "INSERT INTO customers(username,password) VALUES(?,?)";

		try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
			preparedStatement.setString(1, customer.getUsername());
			preparedStatement.setString(2, customer.getPassword());

			int rowsAffected = preparedStatement.executeUpdate();

			return rowsAffected > 0;
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
	}



}
