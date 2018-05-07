// Honor Pledge:
//
// I pledge that I have neither given nor received any help on this assignment.
//
// smohnot
package Client;

import java.io.Serializable;
import java.rmi.Naming;

import Interface.AdminInterface;
import Interface.BrowseInterface;
import Interface.ClientInterface;
import Interface.LoginInterface;

// This class provides the RMI functionality at the Client side interaction.
// Various Interface object are initialized at the run time.
public class ClientRMI implements Serializable {

	private static final long serialVersionUID = 1L;
	// private static Registry registry;
	private int port = 3232;

	// Ryan: Should these have a scope here?
	// Fixed: defined the scope of variables here.
	private AdminInterface adminInterface;
	private ClientInterface clientInterface;
	private LoginInterface loginInterface;
	private BrowseInterface browseInterface;

	private String server_Addr = "//in-csci-rrpc01.cs.iupui.edu";
	private String loginpanel = server_Addr + ":" + port + "/Login";
	private String clientpanel = server_Addr + ":" + port + "/Client";
	private String adminpanel = server_Addr + ":" + port + "/Admin";
	private String browsepanel = server_Addr + ":" + port + "/Browse";

	public ClientRMI() {
		try {
			// registry = LocateRegistry.getRegistry(server_Addr+this.port);
			this.rmiInvoker_login();
			this.rmiInvoker_client();
			this.rmiInvoker_admin();
			this.rmiInvoker_browse();
		} catch (Exception ex) {
			System.out.println("Error in Client RMI " + ex.getMessage());
			// ex.printStackTrace();
		}
	}

	// This function returns the admin Interface for the RMI interaction.
	public AdminInterface getAdmin() {
		return this.adminInterface;
	}

	// This function returns the Client interface object for the RMI interaction.
	public ClientInterface getClient() {
		return this.clientInterface;
	}

	// This function return login interface object for RMI interaction.
	public LoginInterface getLogin() {
		return this.loginInterface;
	}

	// This funciton returns browse stream of products to browse.
	public BrowseInterface getBrowse() {
		return this.browseInterface;
	}

	// This function Invokes the admin interface for the lookup functionality.
	private void rmiInvoker_admin() {
		try {
			this.adminInterface = (AdminInterface) Naming.lookup(adminpanel);
		} catch (Exception e) {
			System.out.println("Admin Panel RMI Error: " + e.getMessage());
		}
	}

	// This function Invokes the Client interface for the lookup functionality.
	private void rmiInvoker_client() {
		try {
			this.clientInterface = (ClientInterface) Naming.lookup(clientpanel);
		} catch (Exception e) {
			System.out.println("Customer Panel RMI Error: " + e.getMessage());
		}
	}

	// This function Invokes the login interface for the lookup functionality.
	private void rmiInvoker_login() {
		try {
			this.loginInterface = (LoginInterface) Naming.lookup(loginpanel);
		} catch (Exception e) {
			System.out.println("Login Panel RMI Error: " + e.getMessage());
			// e.printStackTrace();
		}
	}

	// This function Invokes the browse interface for the browsing functionality.
	private void rmiInvoker_browse() {
		try {
			this.browseInterface = (BrowseInterface) Naming.lookup(browsepanel);
		} catch (Exception e) {
			System.out.println("Browse Panel Error " + e.getMessage());
			// e.printStackTrace();
		}
	}
}
