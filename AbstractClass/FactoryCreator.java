/*
 * Honor Pledge:
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * 
 * smohnot
 */
package AbstractClass;

import java.io.Serializable;

// this class created factories of objects. It parses requests and returns the factory associated with the object required.
public class FactoryCreator implements Serializable {

	private static final long serialVersionUID = 1L;

	// getFactory function returns the factory of related object as requested using
	// conditional statement.
	public static AbstractFactory getFactory(String str) {
		switch (str) {
		case "customer":
			return new ClientFactory();
		case "admin":
			return new AdminFactory();
		}
		// if requests not matching
		return null;
	}
}
