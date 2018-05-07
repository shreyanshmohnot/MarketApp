// Honor Pledge:
//
// I pledge that I have neither given nor received any help on this assignment.
//
// smohnot
package Server;

import Model.ConnectionFactory;

// It utilize the policy files and security manager. It initiates the RMI connections and gets ready for incoming connections.
public class Server {
	// singleton connection for the database. only one instance can be created in
	// the for this.
	public static final ConnectionFactory dbConn = new ConnectionFactory();

	public static void main(String[] args) {
		// Location of MarketPlace and Login Server
		System.setSecurityManager(new SecurityManager());
		System.out.println("Creating a MarketPlace Server!");
		try {
			if (dbConn.setConnection() == null) {
				throw new Exception("Database Connection");
			}
			new ServerRMI();
			System.out.println("MarketPlace Server Ready!");
		} catch (Exception e) {
			System.out.println("Error in Server @ " + e.getMessage());
		}
	}
}