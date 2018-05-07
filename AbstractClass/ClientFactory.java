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
import View.CustomerBrowse;
import View.CustomerPanel;
import View.Views;

// This class created a factory for creating Client View for the user. It uses abstract factory inherited in it.
// This makes it useful in casting returns using abstract factory objects.
public class ClientFactory extends AbstractFactory {

	// Ryan: Should this really be null here? If so why
	// is it needed?
	// FIXED:
	// Abstract Factory implemented partially before. It was not creating views as
	// an abstract rather generating specific views.

	// this function used to get the View requested by the factory.
	@Override
	public Views getView() {
		return new CustomerPanel();
	}

	// this function is used to get the views requested by the factory
	@Override
	public Views getBrowse(ArrayList<Products> list) {
		return new CustomerBrowse(list);
	}
}
