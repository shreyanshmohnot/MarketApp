// Honor Pledge:
//
// I pledge that I have neither given nor received any help on this assignment.
//
// smohnot
package Interface;

import Handler.Session;
import View.SignIn;
import View.SignUpClass;

// Interface for the Login View to interact with the controller from the Model Side.
public interface LoginInterface extends java.rmi.Remote {
	Session CheckLogin(SignIn signin) throws java.rmi.RemoteException;

	String RegisterLogin(SignUpClass signup) throws java.rmi.RemoteException;
}
