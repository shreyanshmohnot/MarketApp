/*
 * Honor Pledge:
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * 
 * smohnot
 */

package Handler;

//Ryan: Missing Honor Pledge & Digital Signature.
// FIXED:
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// This interface is for checking role types from user requests.
// It compares value of role with user session.
@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.LOCAL_VARIABLE })
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresRole {
	String value();
}