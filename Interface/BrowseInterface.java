/*
 * Honor Pledge:
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * 
 * smohnot
 */

package Interface;

import java.util.ArrayList;

import Handler.Products;
import Handler.RequiresRole;
import Handler.Session;

//Interface for the browsing the products in the View and browsecontroller from the Model Side.
public interface BrowseInterface extends java.rmi.Remote {
	// @returns a list of products in the database
	ArrayList<Products> BrowseProducts() throws java.rmi.RemoteException;

	// @returns a list of shopping cart products for specific user from database.
	@RequiresRole("customer")
	ArrayList<Products> ShoppingList(Session s) throws java.rmi.RemoteException;

}
