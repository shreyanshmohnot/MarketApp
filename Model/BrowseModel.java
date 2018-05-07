/*
 * Honor Pledge:
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * 
 * smohnot
 */
package Model;

import java.sql.ResultSet;
import java.util.ArrayList;

import Handler.Products;

// this class is for browsing the products in the online marketplace.
public class BrowseModel {
	private ArrayList<Products> pList;
	private ArrayList<Products> shopList;

	public BrowseModel() {

	}

	// function to @return the array of product in the online marketplace
	// application.
	public synchronized ArrayList<Products> getItems() {
		pList = new ArrayList<>();
		String query = "select * from productmaster";
		try {
			ResultSet rs = Server.Server.dbConn.getDataSet(query);
			while (rs.next()) {
				Products product = new Products();
				product.setAttributes(rs.getInt(1), rs.getString(2), rs.getString(4), rs.getInt(5), rs.getDouble(3));
				pList.add(product);
			}
		} catch (Exception e) {
		}
		return pList;
	}

	// function to get the cart shopping list values and pass it to the user
	// requested.
	public ArrayList<Products> getCart(String username) {
		this.shopList = new ArrayList<Products>();
		String query = "select shoppingcart.ProductID, productmaster.ProductName, productmaster.ProductPrice, "
				+ "shoppingcart.ProductQuantity from shoppingcart INNER JOIN productmaster on "
				+ "productmaster.ProductID=shoppingcart.ProductID  where shoppingcart.Email='" + username + "'";
		try {
			ResultSet rs = Server.Server.dbConn.getDataSet(query);
			while (rs.next()) {
				Products product = new Products();
				product.setAttributes(rs.getInt(1), rs.getString(2), rs.getInt(4), rs.getDouble(3));
				this.shopList.add(product);
			}
		} catch (Exception e) {
			System.out.println("Error in shoping cart reterival");
		}
		return this.shopList;
	}
}
