/*
 * Honor Pledge:
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * 
 * smohnot
 */
package AbstractClass;

import java.util.ArrayList;

import Handler.Products;
import View.Views;

// This class is for making objects for specific Admin and customer views. 
// It returns the objects for respective views for Client and Admin.
public abstract class AbstractFactory {

	// abstract method to generate views for the Admin or Client.
	public abstract Views getView();

	// abstract method to generate browse views for the Admin or Client.
	public abstract Views getBrowse(ArrayList<Products> list);
}
