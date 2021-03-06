package shoppingCart.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/** A class that manages a list of users.
 *  @author Newman Souza
 *  @author Seth Moore
 */ 
@SuppressWarnings("serial")
public class UserList implements Serializable {
	
    /** Constructs a UserList object.
     *  @precondition 		none
     *  @postcondition 		object created
     */
    public UserList() {
    	users = new ArrayList<User>();
    }

    /**
     * Creates and adds a new user to the UserList.
     * 
     * @param username the User's username
     * @param password the User's password
     * @param type the User's type
     */
    public void addUser(String username, String password, String type) {
		User user = new User(username, password, type);
		users.add(user);
    }

    /** Validates user credentials.
	 *  @param username		User's username
	 *  @param password		User's password
	 *  @return 			The User's type if user is in the list and null otherwise
	 *  @precondition 		username and password are valid references
	 *  @postcondition  	User's type is determined
	 */
	public String validate(String username, String password) {
		Iterator<User> userList = Collections.unmodifiableList(users).iterator();
    	while (userList.hasNext()) {
    		User user = (User)userList.next();
    		if (user.getUsername().equals(username)) {
    			if (user.checkPassword(password)) {
    				return user.getType();
    			}
    		}
    	}
   		return null;
	}
	
    private ArrayList<User> users;
    
}
