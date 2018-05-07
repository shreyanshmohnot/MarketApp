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
import Interface.AdminInterface;
import Model.AdminModel;

// This class is used as Model Controller for the Admin and sends its requests to the Admin Model for processing.
// Returns all requests back to the RMI functionality and back to the View Controller.
public class AdminController extends UnicastRemoteObject implements AdminInterface {

	private static final long serialVersionUID = 1L;
	private AdminModel AdminModel;

	public AdminController() throws RemoteException {
		this.AdminModel = new AdminModel();
	}

	// method to update new add products.
	private String AddProducts(String str) {
		String prodT[] = str.split(",");
		String pname = prodT[1];
		String pdesc = prodT[2];
		int pquant = Integer.parseInt(prodT[3]);
		double pprice = Double.parseDouble(prodT[4]);
		return (this.AdminModel.AddProducts(pname, pdesc, pquant, pprice) > 0) ? "Product Added" : "Added";
	}

	// removes customer from the list of logins.
	private String RemoveCustomer(String user) {
		String tp[] = user.split(",");
		int n = this.AdminModel.GetUsers(tp[1]);
		if (n != -1) {
			return (this.AdminModel.RemoveUser(n) > 0) ? "RemoveC" : "Error";
		}
		return "InvalidU";
	}

	// Its the implementation for the requests send to admin Model.
	// used to filter requests according to the string requests.
	@Override
	public String adminRequest(Session session, String str) throws RemoteException {
		if (str.startsWith("Add Product")) {
			return this.AddProducts(str);
		} else {
			if (str.startsWith("RemoveC")) {
				return this.RemoveCustomer(str);
			} else {
				if (str.startsWith("UpdateProd")) {
					return this.UpdateProduct(str);
				}
			}
		}
		return null;
	}

	// update the products with the input given by the administrator.
	// specific to the description, quantity and price.
	private String UpdateProduct(String str) {
		String nstr[] = str.split(",");
		int prodID = Integer.parseInt(nstr[2]);
		for (Products prod : BrowseController.productList) {
			if (prod.getID() == prodID) {
				switch (nstr[1]) {
				case "Remove":
					return this.AdminModel.RemoveProduct(prodID) > 0 ? "Removed" : "Error";
				case "Desc":
					return this.AdminModel.UpdateDescription(prodID, nstr[3]) > 0 ? "UpdateD" : "Error";
				case "Price":
					double price = Double.parseDouble(nstr[3]);
					return this.AdminModel.UpdatePrice(prodID, price) > 0 ? "UpdateP" : "Error";
				case "Quant":
					int pquant = Integer.parseInt(nstr[3]);
					return this.AdminModel.UpdateQuantity(prodID, pquant) > 0 ? "UpdateQ" : "Error";
				}
			}
		}
		return "Invalid";
	}
}