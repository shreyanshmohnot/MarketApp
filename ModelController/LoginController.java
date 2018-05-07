// Honor Pledge:
//
// I pledge that I have neither given nor received any help on this assignment.
//
// smohnot
package ModelController;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import Handler.Session;
import Interface.LoginInterface;
import Model.LoginModel;
import View.SignIn;
import View.SignUpClass;

// This login controller used for implementing login functionality.
// It sends all its requests to the login Model for getting the logic.
public class LoginController extends UnicastRemoteObject implements LoginInterface {

	private static final long serialVersionUID = 1L;
	private LoginModel loginModel;

	public LoginController() throws RemoteException {
		this.loginModel = new LoginModel();
	}

	// forwards Model to check login parameters and returns the appropriate login
	// role either admin or customer.
	@Override
	public Session CheckLogin(SignIn signin) throws RemoteException {
		Session session_t = null;
		String temp = this.loginModel.LoginAuthentication(signin.getLogin(), signin.getpLogin());
		if (temp.equals("Wrong")) {
			return session_t;
		} else {
			String role = null;
			if (temp.equals("1")) {
				role = "customer";
			} else {
				role = "admin";
			}
			session_t = new Session(signin.getLogin(), role);
			System.out.println(signin.getLogin() + " Logged In");
			return session_t;
		}
	}

	// forwards requests to Model to register user in the online marketplace.
	@Override
	public String RegisterLogin(SignUpClass signup) throws RemoteException {
		int n = this.loginModel.RegisterCustomer(signup.getRole(), signup.getEmail(), signup.getPassword(),
				signup.getFirst(), signup.getLast());
		if (n > 0) {
			return "Success";
		} else {
			if (n == -1) {
				return "Exists";
			}
		}
		return "Failed";
	}
}
