/* Honor Pledge:
 *
 * I pledge that I have neither given nor received any help on this assignment.
 *
 * smohnot
 */
package ViewController;

import java.io.IOException;

import Handler.Session;
import View.SignIn;

// This class implements front controller.
// It also uses command pattern in place.
// Here it initializes the RMI Client and dispatcher class for sending command to dispatcher.
public class FrontController {
	// Ryan: Should these have a scope here?
	// Fixed: Have defined the scope of variables in this class.
	private ApplicationControl appControl;

	public FrontController() {
		this.appControl = new ApplicationControl();
	}

	// This method invokes the Client requests and sends them to dispatcher for
	// further processing. This method has string as parameters.
	public void clientRequests(String str) throws IOException {
		switch (str) {
		case "Sign In":
			// SignIn
			SignIn signin = new SignIn();
			while (!this.LoginCheck(signin)) {
				break;
			}
			break;
		case "Sign Up":
			this.appControl.CommandExecution("Add Customer", null);
			break;
		}
	}

	// This method invokes the Client requests and sends them to dispatcher for
	// further processing. This method uses signin class as parameters.
	private boolean LoginCheck(SignIn signin) throws IOException {
		Session checkLogin = null;
		int error = 0;
		do {
			signin.LoginInfo();
			error = 0;
			if (signin.getpLogin().isEmpty()) {
				System.out.println("Password is empty. Please re-enter.");
				error = 1;
			}
			if (signin.getLogin().isEmpty()) {
				System.out.println("Email is empty. Please re-enter.");
				error = 1;
			}
		} while (error == 1);

		// sends requests to the application controller.
		checkLogin = this.appControl.CommandExecution(signin);

		if (checkLogin == null) {
			System.out.println("Wrong Username/Password.");
			return false;
		} else {
			this.appControl.Dispatcher(checkLogin, "Panel");
			return true;
		}
	}
}