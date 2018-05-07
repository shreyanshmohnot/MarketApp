// Honor Pledge:
//
// I pledge that I have neither given nor received any help on this assignment.
//
// smohnot
package View;

import java.io.IOException;
import java.io.Serializable;

// This class is used for sign up for the customer. It is also used for signup by the admin whenever new administrator is added.
public class SignUpClass implements Serializable {

	private static final long serialVersionUID = 1L;

	// Ryan: Should these have a scope here?
	// Fixed: fix with declaring variables as private
	private String email, first, last, pwd, pwd1;
	private int role;

	public SignUpClass(int role) {
		this.role = role;
	}

	// method to get sign up details from the user.
	public void SignUpInfo() throws IOException {
		if (this.role == 0) {
			System.out.println("Enter Admin Sign Up Information :");
		} else {
			System.out.println("Enter Customer Sign Up Information :");
		}
		System.out.print("Enter First Name :");
		this.first = Client.Client.reader.readLine();
		System.out.print("Enter Last Name :");
		this.last = Client.Client.reader.readLine();
		System.out.print("Enter Email :");
		this.email = Client.Client.reader.readLine();
		System.out.print("Enter Password:");
		this.pwd = Client.Client.reader.readLine();
		System.out.print("Verify Password:");
		this.pwd1 = Client.Client.reader.readLine();
	}

	// method to get Email address in the Signup object
	public String getEmail() {
		return this.email;
	}

	// method to get password one in the Signup object
	public String getPassword() {
		return this.pwd;
	}

	// method to get verify password in the Signup object
	public String getPassword1() {
		return this.pwd1;
	}

	// method to get user role in the Signup object
	public int getRole() {
		return this.role;
	}

	// method to get user first name in the Signup object
	public String getFirst() {
		return this.first;
	}

	// method to get user last name in the Signup object
	public String getLast() {
		return this.last;
	}
}
