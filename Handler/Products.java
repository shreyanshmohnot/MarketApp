/*
 * Honor Pledge:
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * 
 * smohnot
 */
package Handler;

import java.io.Serializable;

// This class is for storing the products. It is a serializable class because it is used to store product in array lists.
public class Products implements Serializable {

	private static final long serialVersionUID = 1L;

	// Ryan: Should these have a scope here?
	// Fixed: Have defined the scope of variables in this class.
	private String Name, Desc;
	private int Quantity, ID;
	private double Price;

	public Products() {

	}

	// function to store the attributes of the products
	// it @args product id, name, its description, quantity and price.
	public void setAttributes(int id, String name, String desc, int quantity, double price) {
		this.ID = id;
		this.Name = name;
		this.Desc = desc;
		this.Quantity = quantity;
		this.Price = price;
	}

	// function to store the attributes of the products
	// it @args product id, name, its description, quantity and price.
	public void setAttributes(int id, String name, int quantity, double price) {
		this.ID = id;
		this.Name = name;
		this.Quantity = quantity;
		this.Price = price;
	}

	// function to get the product ID in @return
	public int getID() {
		return this.ID;
	}

	// function to get the product Name in @return
	public String getName() {
		return this.Name;
	}

	// function to get the product description in @return
	public String getDesc() {
		return this.Desc;
	}

	// function to get the product quantity in @return
	public int getQuantity() {
		return this.Quantity;
	}

	// function to get the product price in @return
	public double getPrice() {
		return this.Price;
	}
}
