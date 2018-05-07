// Honor Pledge:
//
// I pledge that I have neither given nor received any help on this assignment.
//
// smohnot
package Model;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

// this class represents the database connectivity functions.
// It has two functions setData (for insert, update and delete) and getResultSet (for select).
// it is a singleton class.
public class ConnectionFactory {

	// private connection instance.
	private Connection connection = null;

	// initializer for the database connection factory.
	public ConnectionFactory() {
		this.connection = this.setConnection();
	}

	// this function establishes a connection with the database and returns the
	// connection instance.
	public Connection setConnection() {
		if (this.connection != null) {
			return this.connection;
		}
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/smohnot_db",
					"smohnot", "shreyanshm");
			return this.connection;
		} catch (Exception e) {
			this.connection = null;
			System.out.println("Error in establishing connection");
		}
		return null;
	}

	// this function is used to terminate the connection from the server side.
	public boolean closeConnection() {
		if (this.connection == null) {
			return true;
		}
		try {
			this.connection.close();
			return true;
		} catch (SQLException e) {
			return false;
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	// For INSERT, UPDATE data into DB.
	// it @returns the number of rows affected by the query execution.
	public int setData(String query) {
		int n = 0;
		try {
			Statement stmt = (Statement) this.connection.createStatement();
			n = stmt.executeUpdate(query);
		} catch (SQLException ex) {
			if (ex.getErrorCode() == 1062) {
				return -1;
			}
			System.out.println("Cannot set to Database\n" + ex.getMessage() + " Database Connection");
		} catch (Exception e) {
			System.out.println("Error in excuting query " + e.getMessage());
		}
		return n;
	}

	// For getting SELECT from DB.
	// it @returns the set of result rows after execution of the query.
	public ResultSet getDataSet(String query) {
		ResultSet rs = null;
		try {
			Statement stmt = (Statement) this.connection.createStatement();
			rs = stmt.executeQuery(query);
			return rs;
		} catch (Exception ex) {
			System.out.println("Cannot Extract from Database\n" + ex.getMessage() + " Database Connection");
		}
		return null;
	}
}