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

// this class is for Admin Browse functionality.
// it extends the views pattern from the abstract factory and implements command execution
public class AdminBrowse extends Views implements Commands {

	private List<Products> productList;
	private ApplicationControl appControl;
	private Session session;
	private String result = null;

	public AdminBrowse(ArrayList<Products> list) {
		this.productList = list;
	}

	// this override method to execute each as same command.
	@Override
	public void CommandExecution(String str) {
		this.result = this.appControl.CommandExecution(str, this.session);
	}

	// function to display the products in the product list send by the browse
	// interface for the Admin.
	@Override
	public void displayView(ApplicationControl app, Session s) throws IOException {
		this.appControl = app;
		this.session = s;
		int i;
		String input, choice;
		do {
			i = 1;
			System.out.printf("%-15s%-18s%-25s%-15s%-15s\n", "Product ID", "Product Name", "Product Description",
					"Product Price", "Product Quantity");
			for (Products prod : productList) {
				System.out.printf(" %-15d%-18s%-25s%-15.2f%-15d\n", prod.getID(), prod.getName(), prod.getDesc(),
						prod.getPrice(), prod.getQuantity());
			}
			System.out.println("Select Product ID to Edit or Press 0 to exit");
			input = Client.Client.reader.readLine();
			if (input.length() == 0) {
				System.out.println("Invalid Input. Please Re-enter");
				return;
			}
			if (input.equals("0")) {
				return;
			}
			System.out.println("Edit Options for Product " + input);
			System.out.println("1. Update Description");
			System.out.println("2. Update Quantity");
			System.out.println("3. Update Price");
			System.out.println("4. Remove Product");
			System.out.println("Select an option to Edit or Press 0 to exit");
			choice = Client.Client.reader.readLine();
			if (choice.length() == 0) {
				System.out.println("Invalid Input. Please Re-enter");
				i = 0;
			} else {
				switch (choice) {
				case "0":
					return;
				case "1":
					System.out.println("Enter Product New Description: ");
					choice = Client.Client.reader.readLine();
					input = "Desc," + input;
					break;
				case "2":
					System.out.println("Enter Product New Quantity: ");
					choice = Client.Client.reader.readLine();
					input = "Quant," + input;
					break;
				case "3":
					System.out.println("Enter Product New Price: ");
					choice = Client.Client.reader.readLine();
					input = "Price," + input;
					break;
				case "4":
					choice = input;
					input = "Remove," + input;
					break;
				default:
					System.out.println("Invalid Input. Please Re-enter");
					i = 0;
					break;
				}
				if (choice.length() == 0) {
					System.out.println("Invalid Input. Please Re-enter");
					i = 0;
				}
			}
		} while (i == 0);
		this.CommandExecution("UpdateProd," + input + "," + choice);
		System.out.println(this.result);
	}
}