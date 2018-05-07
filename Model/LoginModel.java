// Honor Pledge:
//
// I pledge that I have neither given nor received any help on this assignment.
//
// smohnot
package Model;

import java.sql.ResultSet;

// Model for Login Authentications.
public class LoginModel {

	public LoginModel() {
	}

	// method to check login authentication for customer and admin.
	public String LoginAuthentication(String username, String password) {
		String query = "select RoleType from loginmaster where Email='" + username + "' and Password='" + password
				+ "'";
		try {
			ResultSet rs = Server.Server.dbConn.getDataSet(query);
			if (rs.next()) {
				return rs.getString(1);
			} else {
				return "Wrong";
			}
		} catch (Exception ex) {
			System.out.println("Error in SQL Login Model query " + ex.getMessage());
			// ex.printStackTrace();
		}
		return null;
	}

	// method to register users as well as administrators.
	public int RegisterCustomer(int role, String email, String password, String first, String last) {

		String query = "insert into loginmaster set " + "RoleType=" + role + "," + "Email='" + email + "',"
				+ "Password='" + password + "'," + "FirstName='" + first + "'," + "LastName='" + last + "',"
				+ "CreatedOn='" + java.util.Calendar.getInstance().get(java.util.Calendar.YEAR) + "-"
				+ (java.util.Calendar.getInstance().get(java.util.Calendar.MONTH) + 1) + "-"
				+ java.util.Calendar.getInstance().get(java.util.Calendar.DAY_OF_MONTH) + "'";

		return Server.Server.dbConn.setData(query);
	}
}
