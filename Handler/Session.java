/*
 * Honor Pledge:
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * 
 * smohnot
 */
package Handler;

//Ryan: Missing Honor Pledge & Digital Signature.
import java.io.Serializable;
//Ryan: Please provide useful comments in each file.

// FIXED: This class creates session objects to store user information in that.
// It is serializable because object then could be sent to Server for verification 
public class Session implements Serializable {

	private static final long serialVersionUID = 1L;
	private String userName, roleType;

	// Ryan: Should these have a scope here?
	// Fixed: defined what scope it should carry.
	private boolean isAuthenticated;

	public Session(String username, String type) {
		this.userName = username;
		this.roleType = type;
		this.isAuthenticated = true;
	}

	// this function get the authentication set on a user.
	public boolean getAuthentication() {
		return this.isAuthenticated;
	}

	// this function get the role type either user is admin or customer.
	public String getRoleType() {
		return this.roleType;
	}

	// this function returns the username.
	public String getUsername() {
		return this.userName;
	}

	// this function sets the type of role user requests.
	public void setRoleType(String str) {
		this.roleType = str;
	}
}