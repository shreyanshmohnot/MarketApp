// Honor Pledge:
//
// I pledge that I have neither given nor received any help on this assignment.
//
// smohnot
package ViewController;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import AbstractClass.AbstractFactory;
import AbstractClass.FactoryCreator;
import Handler.Products;
import Handler.Session;
import View.ShoppingCart;
import View.Views;

// dispatcher to dispatch the views to View areas.
public class FrontDispatcher implements Serializable {

	private static final long serialVersionUID = 1L;
	// Ryan: Should these have a scope here?
	// Fixed: Have defined the scope of variables in this class.
	private ApplicationControl appControl;

	public FrontDispatcher(ApplicationControl applicationControl) {
		this.appControl = applicationControl;
	}

	// this function is used for redirect the views as per the session.
	public void ShowView(Session session, String str) throws IOException {
		AbstractFactory viewFactory = FactoryCreator.getFactory(session.getRoleType());
		Views views = viewFactory.getView();
		views.displayView(this.appControl, session);
	}

	// function to redirect the views to appropriate View panels.
	public void ShowView(Session session, String str, ArrayList<Products> pList) throws IOException {
		switch (str) {
		case "Browse":
			AbstractFactory viewFactory = FactoryCreator.getFactory(session.getRoleType());
			Views views = viewFactory.getBrowse(pList);
			views.displayView(this.appControl, session);
			break;
		case "ShopCart":
			ShoppingCart shopcart = new ShoppingCart(pList);
			shopcart.displayView(this.appControl, session);
			break;
		}
	}
}