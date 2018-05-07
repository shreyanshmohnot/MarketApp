// Honor Pledge:
//
// I pledge that I have neither given nor received any help on this assignment.
//
// smohnot
package View;

import java.io.IOException;

import Handler.AuthorizationException;
import Handler.Session;
import ViewController.ApplicationControl;

// Ryan: Here you are violating the principle of separation of concerns
// in that you are mixing View data with the application framework. Instead
// you should isolate these two components.
// FIXED: Separated the RMI functionality. Created new RMI Class. The framework is isolated and resides in ClientRMI file.
// View class for the Admin Panel. It is initialized whenever the user is administrator.
public class AdminPanel extends Views implements Commands {

	private ApplicationControl appControl;
	private Session session;
	private String result = null;

	public AdminPanel() {
		System.out.print("Welcome Administrator :- ");
	}

	// this method send a request to the View controller for adding new products by
	// the user.
	private void AddProduct() throws IOException {
		try {
			System.out.println("Enter Product Name: ");
			String pname = Client.Client.reader.readLine();
			if (pname.length() == 0) {
				System.out.println("Invalid Input");
				return;
			}
			System.out.println("Enter Product Description: ");
			String pdesc = Client.Client.reader.readLine();
			if (pdesc.length() == 0) {
				System.out.println("Invalid Input");
				return;
			}
			System.out.println("Enter Product Quantity");
			int pquant = Integer.parseInt(Client.Client.reader.readLine());
			System.out.println("Enter Product Price");
			double pprice = Double.parseDouble(Client.Client.reader.readLine());
			this.CommandExecution("Add Product," + pname.replaceAll(",", "") + "," + pdesc.replaceAll(",", "") + ","
					+ pquant + "," + pprice);
			System.out.println("Progress : " + this.result);
		} catch (Exception e) {
			System.out.println("Invalid Input");
			return;
		}
	}

	// this method sends a commands to the command invoker.
	private void BrowseProducts() {
		this.CommandExecution("Browse");
		System.out.println("Progress : " + this.result);
	}

	// function to add new customer to the marketplace
	private void AddCustomer() {
		this.CommandExecution("Add Customer");
		System.out.println("Progress : " + this.result);
	}

	// function to remove customer from marketplace.
	private void RemoveCustomer() throws IOException {
		// takes input username/email address of customer to remove.
		System.out.println("Enter Username/Email of customer :");
		String input = Client.Client.reader.readLine();
		if (input.length() == 0) {
			System.out.println("Invalid Input");
			return;
		}
		this.CommandExecution("RemoveC," + input);
		System.out.println("Progress : " + this.result);
	}

	// function to add new administrator to the marketplace application.
	private void AddAdmin() {
		this.CommandExecution("Add Admin");
		System.out.println("Progress : " + this.result);
	}

	// this function send a logout request to the command invoker
	private void Logout() {
		this.CommandExecution("Logout");
		System.out.println("Progress : " + this.result);
	}

	// this method overrides the command execution for each command to execute same.
	@Override
	public void CommandExecution(String str) {
		this.result = this.appControl.CommandExecution(str, this.session);
	}

	// Dashboard for the Admin Panel. It shows all the functionality available to
	// the administrator as a part for this role.
	@Override
	public void displayView(ApplicationControl appControl, Session session) throws IOException {
		this.appControl = appControl;
		this.session = session;
		System.out.println(session.getUsername() + " !!");
		String id = null;
		do {
			try {
				System.out.println("1. Browse Products\n2. Add Product\n3. Add Admin");
				System.out.println("4. Add Customer\n5. Remove Customer\n6. Sign Out");
				id = Client.Client.reader.readLine();
				switch (id) {
				case "1":
					this.BrowseProducts();
					break;
				case "2":
					this.AddProduct();
					break;
				case "3":
					this.AddAdmin();
					break;
				case "4":
					this.AddCustomer();
					break;
				case "5":
					this.RemoveCustomer();
					break;
				case "6":
					this.Logout();
					return;
				default:
					System.out.println("Please select valid option.");
					break;
				}
			} catch (AuthorizationException ae) {
				ae.getMessage();
			}
		} while (!id.equals("6"));
	}
}