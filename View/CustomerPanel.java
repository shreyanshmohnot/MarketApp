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
// FIXED: Separated RMI from View. The framework resides in Client RMI now.

// This class is for customer to have their clientpanel View. It shows a couple options for the customer in the online marketplace.
// All requests originated from this class are send to the front controller.
public class CustomerPanel extends Views implements Commands {

	// FrontController Client;
	private ApplicationControl appControl;
	private Session session;
	private String result = null;

	public CustomerPanel() {
		System.out.print("Welcome to Online Martket Place :- ");
	}

	// method to browse products requested by the customer.
	private void BrowseProducts() {
		this.CommandExecution("Browse");
		System.out.println("Progress : " + this.result);
	}

	// method to view shopping cart by the customer.
	private void ShoppingCart() {
		this.CommandExecution("ShopCart");
		// System.out.println("Progress : " + this.result);
	}

	// this function sends a request to the command invoker for logout.
	private void LogOut() {
		this.CommandExecution("Logout");
		System.out.println("Progress : " + this.result);
	}

	// this override method to execute each as same command.
	@Override
	public void CommandExecution(String str) {
		this.result = this.appControl.CommandExecution(str, this.session);
	}

	// This function is used for the display the menu options available to customer
	// after login or signup.
	// It receives the parameter for front controller because each request then have
	// to send back to the front controller for decision.
	@Override
	public void displayView(ApplicationControl appControl, Session session) throws IOException {
		this.appControl = appControl;
		this.session = session;
		System.out.println(session.getUsername() + " !!!");
		int id = 0;
		do {
			try {
				System.out.println("1. Browse\n2. Shopping Cart\n3. Sign Out");
				id = Integer.parseInt(Client.Client.reader.readLine());
				switch (id) {
				case 1:
					this.BrowseProducts();
					break;
				case 2:
					this.ShoppingCart();
					break;
				case 3:
					this.LogOut();
					break;
				default:
					System.out.println("Please select valid option.");
					break;
				}
			} catch (AuthorizationException ae) {
				ae.getMessage();
			}
		} while (id != 3);
	}
}
