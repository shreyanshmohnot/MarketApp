//Honor Pledge:
//
//I pledge that I have neither given nor received any help on this assignment.
//
//smohnot

package View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Handler.Products;
import Handler.Session;
import ViewController.ApplicationControl;

// this class is for adding browse functionality to the customers and implement the command pattern
// it inhertis views to abstract creation of the class.
public class CustomerBrowse extends Views implements Commands {

	private List<Products> productList;
	private ApplicationControl appControl;
	private Session session;
	private String result = null;

	public CustomerBrowse(ArrayList<Products> list) {
		this.productList = list;
	}

	// this override method to execute each as same command.
	@Override
	public void CommandExecution(String str) {
		this.result = this.appControl.CommandExecution(str, this.session);
	}

	// function to display the products in the product list send by the browse
	// interface for customer.
	@Override
	public void displayView(ApplicationControl app, Session s) throws IOException {
		this.appControl = app;
		this.session = s;
		int i;
		String input;
		do {
			i = 1;
			System.out.printf("%-15s%-18s%-25s%-15s\n", "Product ID", "Product Name", "Product Description",
					"Product Price");
			for (Products prod : productList) {
				System.out.printf(" %-15d%-18s%-25s%-15.2f\n", prod.getID(), prod.getName(), prod.getDesc(),
						prod.getPrice());
			}
			System.out.println("Enter Product ID to Add to Cart OR Press 0 to return..");
			input = Client.Client.reader.readLine();
			if (input.equals("0")) {
				return;
			}
			if (input.length() == 0) {
				System.out.println("Invalid Input. Please Re-enter");
				i = 0;
			} else {
				this.CommandExecution("Add Cart," + input);
				System.out.println(this.result);
			}
		} while (i == 0);
	}
}