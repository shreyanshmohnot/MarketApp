/*
 * Honor Pledge:
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * 
 * smohnot
 */

package ModelController;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import Handler.Products;
import Handler.Session;
import Interface.BrowseInterface;
import Model.BrowseModel;

// This browseController is responsible for interacting with the browse model.
// It is primarily used to get and update the product lists whenever the database is changes.
public class BrowseController extends UnicastRemoteObject implements BrowseInterface {

	private static final long serialVersionUID = 1L;
	// Ryan: Should these have a scope here?
	// Fixed: defined the scope of variables in this class.
	private BrowseModel browseModel;
	// a static volatile arraylist helps in getting the current updated value by the
	// threads requesting it.
	// used as static to allow for requests from other classes and volatile because
	// it returns most updated value.
	public volatile static ArrayList<Products> productList;

	public BrowseController() throws RemoteException {
		this.browseModel = new BrowseModel();
	}

	// this function is for getting the list of products from the database. It calls
	// the Model backend and @returns converted, from the result rows into list.
	@Override
	public ArrayList<Products> BrowseProducts() {
		return productList = browseModel.getItems();
	}

	// this function is for getting the list of shopping cart products from the
	// database. It calls
	// the Model backend and @returns converted, from the result rows into list.
	@Override
	public ArrayList<Products> ShoppingList(Session session) throws RemoteException {
		return browseModel.getCart(session.getUsername());
	}
}