// Honor Pledge:
//
// I pledge that I have neither given nor received any help on this assignment.
//
// smohnot
package Interface;

import Handler.RequiresRole;
import Handler.Session;

// Interface for the Admin View to interact with the controller from the Model Side.
public interface AdminInterface extends java.rmi.Remote {
	@RequiresRole("admin")
	String adminRequest(Session s, String str) throws java.rmi.RemoteException;
}
