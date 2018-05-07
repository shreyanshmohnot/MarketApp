// Honor Pledge:
//
// I pledge that I have neither given nor received any help on this assignment.
//
// smohnot
package Client;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import ViewController.FrontController;

// Ryan: Much of this behavior seems much better suited for your Controller -
// move it there.
// This class provides Client method for the application to run initially.
// It sets the security manager policy files for permissions required for the Java RMI functionality.
public class Client {

	public static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) {
		try {
			// RMI Security Manager
			System.setSecurityManager(new SecurityManager());
			// FrontController initialized here as common port for the views to interact
			// with the Model.
			FrontController clientControl = new FrontController();
			System.out.println("Welcome to Online Market Place!!!");
			int id;
			do {
				System.out.println("Select your Choice :\n1. Login\n2. Sign Up\n3. Quit");
				System.out.print("Enter Choice : ");
				id = Integer.parseInt(reader.readLine());
				// Switches between the different classes of Sign In and Sign Up.
				switch (id) {
				case 1:
					clientControl.clientRequests("Sign In");
					break;
				case 2:
					clientControl.clientRequests("Sign Up");
					break;
				case 3:
					System.out.println("Thank You!!!");
					System.exit(0);
					break;
				default:
					System.out.println("Invalid Request. Please Re-enter.");
					break;
				}

			} while (id != 3);
		} catch (Exception e) {
			System.out.println("IO Error");
		}
	}
}
