// Honor Pledge:
//
// I pledge that I have neither given nor received any help on this assignment.
//
// smohnot
package Interface;

import Handler.RequiresRole;
import Handler.Session;

// Interface for the Client View to interact with the controller from the Model Side.
public interface ClientInterface extends java.rmi.Remote {
	@RequiresRole("customer")
	String clientRequest(Session s, String str) throws java.rmi.RemoteException;
}
