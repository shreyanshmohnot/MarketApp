/*
 * Honor Pledge:
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * 
 * smohnot
 */
package ViewController;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import Client.ClientRMI;
import Handler.AuthorizationException;
import Handler.Products;
import Handler.Session;
import View.SignIn;
import View.SignUpClass;

// This is application controller for the View side. It is the main implementation of the front controller.
// This has the application logic for the controller to communicate with the dispatcher and the command invoker.
public class ApplicationControl implements Serializable {

	private static final long serialVersionUID = 1L;

	// Ryan: Should these have a scope here?
	// Fixed: Have defined the scope of variables in this class.
	private FrontDispatcher dispatcher;
	private ClientRMI rmiClient;
	private ArrayList<Products> productList;

	public ApplicationControl() {
		this.dispatcher = new FrontDispatcher(this);
		this.rmiClient = new ClientRMI();
	}

	// this is the command execution involving command pattern.
	// it takes session id and string as parameters. this controls the main
	// execution of the application.
	// @returns the string back to the calling views.
	public String CommandExecution(String str, Session session) {
		try {
			if (str.startsWith("Add Product")) {
				String prodT[] = str.split(",");
				String pname = prodT[1];
				String pdesc = prodT[2];
				int pquant = Integer.parseInt(prodT[3]);
				double pprice = Double.parseDouble(prodT[4]);
				if (pname.length() == 0) {
					return "Invalid Product Name";
				}
				if (pquant <= 0) {
					return "Invalid Product Quantity";
				}
				if (pprice <= 0) {
					return "Invalid Product Price";
				}
				if (pdesc.length() == 0) {
					return "Invalid Product Description";
				}
				String res = this.rmiClient.getAdmin().adminRequest(session, str);
				switch (res) {
				case "Product Added":
					return "Product Added in the database";
				case "Failed":
					return "Failed to add the product";
				default:
					return "Invalid Product";
				}
			} else {
				if (str.startsWith("Add Cart") || str.startsWith("Purchase")) {
					switch (this.rmiClient.getClient().clientRequest(session, str)) {
					case "Added":
						return "Product Added";
					case "Out":
						return "Cannot be added to cart. Out of Stock";
					case "Invalid":
						return "Invalid Product";
					case "Done":
						return "Purchase Complete";
					case "Error":
						return "Error in Shopping Cart";
					case "OutS":
						return "Purchase cannot be made. Out of Stock";
					case "Exists":
						return "Product Exists in cart.";
					default:
						return "Invalid Product";
					}
				} else {
					if (str.startsWith("RemoveC") || str.startsWith("UpdateProd")) {
						switch (this.rmiClient.getAdmin().adminRequest(session, str)) {
						case "RemoveC":
							return "Removed Customer";
						case "Error":
							return "Error in Removing";
						case "InvalidU":
							return "Invalid Username/Email";
						case "UpdateD":
							return "Updated Description";
						case "UpdateP":
							return "Updated Price";
						case "UpdateQ":
							return "Updated Quantity";
						case "Removed":
							return "Removed Product";
						case "Invalid":
							return "Invalid Product";
						default:
							return "Invalid";
						}
					}
				}
			}
			switch (str) {
			case "Add Admin":
				while (!this.RegisterCheck(new SignUpClass(0))) {
					break;
				}
				break;
			case "Add Customer":
				while (!this.RegisterCheck(new SignUpClass(1))) {
					break;
				}
				break;
			case "Browse":
				this.productList = this.rmiClient.getBrowse().BrowseProducts();
				this.dispatcher.ShowView(session, str, this.productList);
				return "Done";
			case "ShopCart":
				this.productList = this.rmiClient.getBrowse().ShoppingList(session);
				this.dispatcher.ShowView(session, str, this.productList);
				return "Done";
			case "Empty Cart":
				String temp = this.rmiClient.getClient().clientRequest(session, str);
				return temp;
			case "Logout":
				return "Exit";
			}
		} catch (AuthorizationException ae) {
			ae.getMessage();
		} catch (Exception ae) {
			System.out.println("Error in Controller" + ae.getMessage());
			ae.printStackTrace();
		}
		return null;
	}

	// this is the command execution involving command pattern.
	// it takes signin object as parameter.
	public Session CommandExecution(SignIn signin) {
		try {
			return this.rmiClient.getLogin().CheckLogin(signin);
		} catch (Exception e) {
			System.out.println("Error in login");
		}
		return null;
	}

	// this is the command execution involving command pattern.
	// it takes signup class as parameters.
	public String CommandExecution(SignUpClass signup) {
		try {
			return this.rmiClient.getLogin().RegisterLogin(signup);
		} catch (Exception ex) {
			System.out.println("Error during signup");
		}
		return "Failed";
	}

	// this method dispatches objects associated with respective dispatchers.
	public void Dispatcher(Session session, String str) throws IOException {
		this.dispatcher.ShowView(session, str);
	}

	// This method invokes the Client requests and sends them to dispatcher for
	// further processing. This method uses signup class as parameters.
	public boolean RegisterCheck(SignUpClass signup) throws IOException {
		int error = 0;
		do {
			signup.SignUpInfo();
			error = 0;
			if (signup.getEmail().isEmpty()) {
				System.out.println("Please provide an email");
				error = 1;
			}
			if (signup.getPassword().isEmpty()) {
				System.out.println("Please provide an password");
				error = 1;
			} else {
				if (!signup.getPassword().equals(signup.getPassword1())) {
					System.out.println("Passwords do not match");
					error = 1;
				}
			}
			if (signup.getFirst().isEmpty()) {
				System.out.println("Please provide an First Name");
				error = 1;
			}
			if (signup.getLast().isEmpty()) {
				System.out.println("Please provide an Last Name");
				error = 1;
			}
		} while (error == 1);
		switch (this.CommandExecution(signup)) {
		case "Failed":
			System.out.println("Sign Up Failed. Retry");
			return false;
		case "Exists":
			System.out.println("Email already exists. Re-submit");
			return false;
		case "Success":
			System.out.println("Registration Successful.");
			return true;
		default:
			System.out.println("Error in registration.");
			return false;
		}
	}
}