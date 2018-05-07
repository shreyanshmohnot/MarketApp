// Honor Pledge:
//
// I pledge that I have neither given nor received any help on this assignment.
//
// smohnot

package View;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import Handler.Products;
import Handler.Session;
import ViewController.ApplicationControl;

// this class holds the shopping cart for the customer and implements the commands and views for the same.
public class ShoppingCart extends Views implements Commands, Serializable {

	private static final long serialVersionUID = 1L;
	private List<Products> productList;
	private ApplicationControl appControl;
	private Session session;
	private String result = null;

	public ShoppingCart(List<Products> pList) {
		this.productList = pList;
	}

	// it displays the view requested by the customer for shopping cart and then
	// used
	// for purchasing and deleting from the cart.
	@Override
	public void displayView(ApplicationControl app, Session s) throws IOException {
		this.appControl = app;
		this.session = s;
		int i = 0;
		String input = null;
		if (this.productList.isEmpty()) {
			System.out.println("Shopping Cart Empty");
			return;
		}
		do {
			i = 1;
			System.out.printf("%-15s%-18s%-15s%-10s\n", "Product ID", "Product Name", "Product Price",
					"Product Quantity");
			for (Products prod : productList) {
				System.out.printf(" %-15d%-18s%-15.2f%-10d\n", prod.getID(), prod.getName(), prod.getPrice(),
						prod.getQuantity());
			}
			System.out.println("Shopping Cart Options: ");
			System.out.println("1. Purchase Product\n2. Empty Cart\n3. Exit");
			input = Client.Client.reader.readLine();
			switch (input) {
			case "1":
				this.PurchaseCart();
				return;
			case "2":
				this.RemoveCart();
				return;
			case "3":
				return;
			default:
				i = 0;
				System.out.println("Invalid Option");
				break;
			}
		} while (i != 2);
	}

	@Override
	public void CommandExecution(String str) {
		this.result = this.appControl.CommandExecution(str, this.session);
	}

	// this method is to provide purchasing capabilities to the customer and
	// purchase an item requested.
	private void PurchaseCart() throws IOException {
		System.out.println("Enter Product ID to purchase: ");
		String input = Client.Client.reader.readLine();
		System.out.println("Enter the Quantity");
		String quant = Client.Client.reader.readLine();
		if (input.length() == 0 && quant.length() == 0) {
			System.out.println("Invalid Option. Please resubmit..");
			return;
		}
		this.CommandExecution("Purchase," + input + "," + quant);
		System.out.println("Progresss : " + this.result);
	}

	// this method is to empty the whole cart and remove them from the database too.
	private void RemoveCart() {
		this.CommandExecution("Empty Cart");
		System.out.println("Progresss : " + this.result);
	}
}