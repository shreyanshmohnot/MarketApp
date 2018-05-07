// Honor Pledge:
//
// I pledge that I have neither given nor received any help on this assignment.
//
// smohnot
package View;

import java.io.IOException;
import java.io.Serializable;

// This class is used for sign in functionality.
public class SignIn implements Serializable {

	private static final long serialVersionUID = 1L;
	// Ryan: Should these have a scope here?
	// Fixed: fixed with declaring variables as private
	private String username, pwd;

	// method to return username back. it returns string username.
	public String getLogin() {
		return this.username;
	}

	// method to return password entered by the user. It returns the string pwd.
	public String getpLogin() {
		return this.pwd;
	}

	// method to get sign in parameters from the user.
	public void LoginInfo() throws IOException {
		System.out.println("Enter Login Information :");
		System.out.print("Username/Email : ");
		this.username = Client.Client.reader.readLine();
		System.out.print("Password: ");
		this.pwd = Client.Client.reader.readLine();
	}
}
