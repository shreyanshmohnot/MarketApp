/*
 * Honor Pledge:
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * 
 * smohnot
 */
package View;

import java.io.IOException;

import Handler.Session;
import ViewController.ApplicationControl;

// it is an abstract class for the views. It is used for the abstract factory pattern.
public abstract class Views {
	// function to display the View area of different views accordingly.
	public abstract void displayView(ApplicationControl app, Session s) throws IOException;
}