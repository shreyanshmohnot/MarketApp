// Honor Pledge:
//
// I pledge that I have neither given nor received any help on this assignment.
//
// smohnot
package Model;

import java.sql.ResultSet;

// Model for Client with its business logic.
public class ClientModel {

	public ClientModel() {

	}

	// This function is used for add products to the shopping cart.
	// it @returns the number of rows affected by the update query.
	public int AddToCart(String username, int prodID, int prodQuant) {
		String query = "insert into shoppingcart select '" + username + "', " + prodID + ", " + prodQuant
				+ " from dual where EXISTS (SELECT ProductID from productmaster where ProductID = " + prodID
				+ " and ProductQuantity>=" + prodQuant + ")";
		return Server.Server.dbConn.setData(query);
	}

	// This function is used to delete the cart for specific user.
	// it @returns the number of rows affected by the update query.
	public int RemoveCart(String username) {
		String query = "delete from shoppingcart where Email='" + username + "'";
		return Server.Server.dbConn.setData(query);
	}

	// this function checks it the supplied product id matches what that user has in
	// its cart or not.
	// it checks and return 1 for true and 0 for false.
	public int checkShopCart(int prodid, String user) {
		String query = "select ProductID from shoppingcart where ProductID=" + prodid + " and Email='" + user + "'";
		try {
			ResultSet rs = Server.Server.dbConn.getDataSet(query);
			while (rs.next()) {
				if (rs.getInt(1) == prodid) {
					return 1;
				}
			}
		} catch (Exception e) {
		}
		return 0;
	}

	// Update products catalog in the database with current availability and check
	// if user has requested correct quantity. it @returns the number of rows
	// affected.
	public synchronized int UpdateProduct(int prodid, int prodq) {
		String query = "UPDATE productmaster set ProductQuantity = ProductQuantity - " + prodq + " WHERE ProductID="
				+ prodid + " and ProductQuantity>=" + prodq + "";
		return Server.Server.dbConn.setData(query);
	}

	// Remove products purchased from the shopping cart for the user.
	// it @returns string with the progress of the query.
	public String UpdateShopping(String user, int prodId) {
		String query = "delete from shoppingcart where Email='" + user + "' and ProductID=" + prodId + "";
		return (Server.Server.dbConn.setData(query) > 0) ? "Done" : "Error";
	}
}