/*
 * Honor Pledge:
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * 
 * smohnot
 */
package Handler;
//Ryan: Missing Honor Pledge & Digital Signature.

//Ryan: Please provide useful comments in each file.
// FIXED:
// This class handles the exceptions occurring during runtime whenever unauthorized access occurs.
// It returns a error message generated in super class.
public class AuthorizationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public AuthorizationException(String methodName) {
		super("Access Denined for " + methodName + "() function!");
	}
}
