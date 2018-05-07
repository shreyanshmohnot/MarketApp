// Honor Pledge:
//
// I pledge that I have neither given nor received any help on this assignment.
//
// smohnot
package Model;

import java.sql.ResultSet;

// Model for the Admin with all business logic.
public class AdminModel {
	public AdminModel() {
	}

	// This function would add new product in the online shopping.
	// it takes @args as product name, its description, quantity and price. inserts
	// them into database table.
	// it @returns the database rows affected.
	public int AddProducts(String pname, String pdesc, int pquant, double pprice) {
		String query = "insert into productmaster set ProductName='" + pname + "', ProductDesc='" + pdesc
				+ "', ProductPrice=" + pprice + ", ProductQuantity=" + pquant + "";
		return Server.Server.dbConn.setData(query);
	}

	// this function get the list of customer currently within the system.
	// it @returns the checked value matching as supplied by the administrator.
	public synchronized int GetUsers(String username) {
		String query = "select UserId, Email from loginmaster where RoleType = 1";
		try {
			ResultSet rs = Server.Server.dbConn.getDataSet(query);
			while (rs.next()) {
				if (rs.getString(2).equals(username)) {
					return rs.getInt(1);
				}
			}
		} catch (Exception e) {
		}
		return -1;
	}

	// this function removes a user customer
	// it @returns the updated rows affected.
	public synchronized int RemoveUser(int userid) {
		String query = "delete from loginmaster where UserId='" + userid + "'";
		return Server.Server.dbConn.setData(query);
	}

	// this function updates the price of an item within the system.
	// it @returns the updated rows affected.
	public synchronized int UpdatePrice(int prodid, double price) {
		String query = "update productmaster set ProductPrice=" + price + " where ProductID=" + prodid + "";
		return Server.Server.dbConn.setData(query);
	}

	// this function updates the quantity of supplied product id.
	// it @returns the updated rows affected.
	public synchronized int UpdateQuantity(int prodid, int pquant) {
		String query = "update productmaster set ProductQuantity=" + pquant + " where ProductID=" + prodid + "";
		return Server.Server.dbConn.setData(query);
	}

	// this function updates the description of an item within the system.
	// it @returns the updated rows affected.
	public synchronized int UpdateDescription(int prodid, String desc) {
		String query = "update productmaster set ProductDesc='" + desc + "' where ProductID=" + prodid + "";
		return Server.Server.dbConn.setData(query);
	}

	// this function removes a product from the catalog given by the productid
	// it @returns the updated rows affected.
	public synchronized int RemoveProduct(int prodid) {
		String query = "delete from productmaster where ProductID=" + prodid + "";
		return Server.Server.dbConn.setData(query);
	}
}
