/*
 * Honor Pledge:
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * 
 * smohnot
 */
package Handler;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

//Ryan: Missing Honor Pledge & Digital Signature.

//Ryan: Please provide useful comments in each file.
// FIXED:
// This class is for handling the proxy instance from the Server rmi. 
// It is an intermediates whenever any request of the interface associated with it is called.
public class AuthorizationInvocationHandler implements InvocationHandler, Serializable {

	private static final long serialVersionUID = 1L;
	private Object objectImpl;

	public AuthorizationInvocationHandler(Object impl) {
		this.objectImpl = impl;
	}

	// this function invokes the method called by user. It gets the method name, its
	// parameters in object class and calling class.
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// checking if method contains any annotations.
		if (method.isAnnotationPresent(RequiresRole.class)) {
			RequiresRole role = method.getAnnotation(RequiresRole.class);
			Session session = (Session) args[0];
			if (session.getRoleType().equals(role.value())) {
				return method.invoke(this.objectImpl, args);
			} else {
				// if exception occurs or unauthorized access is detected, a exception is thrown
				throw new AuthorizationException(method.getName());
			}
		} else {
			return method.invoke(this.objectImpl, args);
		}

	}
}