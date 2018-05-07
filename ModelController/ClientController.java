// Honor Pledge:
//
// I pledge that I have neither given nor received any help on this assignment.
//
// smohnot
package ModelController;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import Handler.Products;
import Handler.Session;
import Interface.ClientInterface;
import Model.ClientModel;

// This class is for Shopping interface available to customer after login.
// Returns all requests back to the RMI functionality and back to the View controller.
public class ClientController extends UnicastRemoteObject implements ClientInterface {

	private static final long serialVersionUID = 1L;
	// Ryan: Should these have a scope here?
	// Fixed: defined the scope of variables of this class as private.
	private ClientModel clientModel;

	public ClientController() throws RemoteException {
		this.clientModel = new ClientModel();
	}

	// It is the implementation for Client requests and sends to Client Model.
	// It filter requests received and then sends to Model.
	@Override
	public String clientRequest(Session session, String str) throws RemoteException {
		if (str.startsWith("Add Cart")) {
			return this.AddToCart(session.getUsername(), str);
		} else {
			if (str.startsWith("Purchase")) {
				return this.PurchaseProducts(session.getUsername(), str);
			}
		}
		switch (str) {
		case "Empty Cart":
			return this.RemoveProducts(session.getUsername());
		}
		return null;
	}

	// method to search products
	private String PurchaseProducts(String user, String pname) {
		String[] result = pname.split(",");
		int prodID = Integer.parseInt(result[1]);
		int prodQuant = Integer.parseInt(result[2]);
		int n = this.clientModel.checkShopCart(prodID, user);
		if (n > 0) {
			n = this.clientModel.UpdateProduct(prodID, prodQuant);
			if (n <= 0) {
				return "OutS";
			} else {
				return this.clientModel.UpdateShopping(user, prodID);
			}
		} else {
			return "Invalid Product";
		}
	}

	// method to add to shopping cart for specific user
	private String AddToCart(String user, String str) {
		String[] nstr = str.split(",");
		int prodID = Integer.parseInt(nstr[1]);
		int prodQuant = 1;
		for (Products prod : BrowseController.productList) {
			if (prod.getID() == prodID) {
				int n = this.clientModel.AddToCart(user, prodID, prodQuant);
				if (n == -1) {
					return "Exists";
				}
				if (n > 0) {
					System.out.println(user + " added " + prodID + " to cart");
					return "Added";
				} else {
					return "Out";
				}
			}
		}
		return "Invalid";
	}

	// method to remove from shopping cart for specific user
	private String RemoveProducts(String user) {
		int n = this.clientModel.RemoveCart(user);
		return (n >= 0) ? "Done" : "Error";
	}
}