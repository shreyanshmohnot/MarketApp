// Honor Pledge:
//
// I pledge that I have neither given nor received any help on this assignment.
//
// smohnot
package Server;

import java.lang.reflect.Proxy;
import java.rmi.Naming;

import Handler.AuthorizationInvocationHandler;
import Interface.AdminInterface;
import Interface.BrowseInterface;
import Interface.ClientInterface;
import Interface.LoginInterface;
import ModelController.AdminController;
import ModelController.BrowseController;
import ModelController.ClientController;
import ModelController.LoginController;

// This class provides the RMI functionality at the Server side interaction.
// Various Interface object are initialized at the run time.
public class ServerRMI {

	// private static Registry registry;
	private int port = 3232;

	// Ryan: Should these have a scope here?
	// Fixed : defined the scope of variables in this class as private.
	private String server_Addr = "//in-csci-rrpc01.cs.iupui.edu";
	private String loginpanel = server_Addr + ":" + this.port + "/Login";
	private String adminpanel = server_Addr + ":" + this.port + "/Admin";
	private String clientpanel = server_Addr + ":" + this.port + "/Client";
	private String browsepanel = server_Addr + ":" + this.port + "/Browse";

	private LoginInterface interfaceLogin;
	private ClientInterface interfaceClient;
	private AdminInterface interfaceAdmin;
	private BrowseInterface interfaceBrowse;

	public ServerRMI() throws Exception {
		// registry = LocateRegistry.createRegistry(this.port);
		this.loginNaming();
		this.clientNaming();
		this.adminNaming();
		this.browseNaming();
	}

	// This function invokes the admin binding with the Server controller to receive
	// requests from the View. It uses a proxy instance in binding.
	private void adminNaming() {
		try {
			// All calls to this proxy will be forwarded to the Handler implementation in
			// exception Handler interface.
			this.interfaceAdmin = (AdminInterface) Proxy.newProxyInstance(AdminInterface.class.getClassLoader(),
					new Class<?>[] { AdminInterface.class }, new AuthorizationInvocationHandler(new AdminController()));

			Naming.rebind(this.adminpanel, this.interfaceAdmin);
		} catch (Exception e) {
			System.out.println("Error in Admin binding" + e.getMessage());
		}
	}

	// This function invokes the Client binding with the Server controller to
	// receive requests from the View. It uses a proxy instance in binding.
	private void clientNaming() {
		try {
			// All calls to this proxy will be forwarded to the Handler implementation in
			// exception Handler interface.
			this.interfaceClient = (ClientInterface) Proxy.newProxyInstance(ClientInterface.class.getClassLoader(),
					new Class<?>[] { ClientInterface.class },
					new AuthorizationInvocationHandler(new ClientController()));

			Naming.rebind(this.clientpanel, this.interfaceClient);
		} catch (Exception e) {
			System.out.println("Error in Client binding" + e.getMessage());
		}
	}

	// This function invokes the login binding with the Server controller to receive
	// requests from the View. It uses a proxy instance in binding.
	private void loginNaming() {
		try {
			// All calls to this proxy will be forwarded to the Handler implementation in
			// exception Handler interface.
			this.interfaceLogin = (LoginInterface) Proxy.newProxyInstance(LoginInterface.class.getClassLoader(),
					new Class<?>[] { LoginInterface.class }, new AuthorizationInvocationHandler(new LoginController()));

			Naming.rebind(this.loginpanel, this.interfaceLogin);
		} catch (Exception e) {
			System.out.println("Error in login binding" + e.getMessage());
		}
	}

	// This function invokes the bowse panel binding with the browse controller to
	// receive
	// requests from the View. It uses a proxy instance in binding.
	private void browseNaming() {
		try {
			// All calls to this proxy will be forwarded to the Handler implementation in
			// exception Handler interface.
			this.interfaceBrowse = (BrowseInterface) Proxy.newProxyInstance(BrowseInterface.class.getClassLoader(),
					new Class<?>[] { BrowseInterface.class },
					new AuthorizationInvocationHandler(new BrowseController()));

			Naming.rebind(this.browsepanel, this.interfaceBrowse);
		} catch (Exception e) {
			System.out.println("Error in Browse binding" + e.getMessage());
		}
	}
}
