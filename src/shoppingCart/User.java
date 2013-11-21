package shoppingCart;

import java.io.Serializable;

/** A class that manages User state.
 *  @author Newman Souza
 *  @author Seth Moore
 */ 
@SuppressWarnings("serial")
public class User implements Serializable {
	
	/** Constructs a User object.
     *  @param username		User's username
     *  @param password		User's password
     *  @param type			User's type
     *  @precondition 		none
     *  @postcondition 		object created
     */
    public User(String username, String password, String type) {
    	this.username = username;
    	this.password = password;
    	this.type = type;
    }
    
    /** Retrieves the User's username.
     *  @return 			The User's username 
     *  @precondition 		none
     */
    public String getUsername() {
    	return this.username;
    }

    /** Accessor - Retrieves the User's type.
     *  @return 			The User's type
     *  @precondition 		none
     */
    public String getType() {
    	return this.type;
    }

    /** Validates the User's password.
     *  @param password		The User's password
     *  @return 			True if password is correct and False otherwise
     *  @precondition 		password is a valid reference
     */
    public boolean checkPassword(String password) {
    	return (this.password.equals(password));
    }

    // TODO TO BE DELETED
    public String getPassword() {
    	return this.password;
    }

    /** The User's username. 
     */
    private String username;
    /** The User's password. 
     */
    private String password;
    /** The User's type. 
     */
    private String type;

}
