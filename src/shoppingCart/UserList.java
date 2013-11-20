package shoppingCart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/** A class that manages a list of users.
 *  @author Newman Souza
 *  @author Seth Moore
 */ 
public class UserList implements Iterable<User>, Serializable {
	
    /** Constructs a UserList object.
     *  @precondition 		none
     *  @postcondition 		object created
     */
    public UserList() {
    	users = new ArrayList<User>();
    }

    // TODO TO BE DELETED
    public void addUser(String username, String password, String type) {
		User user = new User(username, password, type);
		users.add(user);
    }

    // TODO TO BE DELETED
    /** Deletes all users from UserList.
     */
    public void clear() {
    	users.clear();
    }


    /** Validates user credentials.
	 *  @param username		User's username
	 *  @param password		User's password
	 *  @return 			The User's type if user is in the list and null otherwise
	 *  @precondition 		username and password are valid references
	 *  @postcondition  	User's type is determined
	 */
	public String validate(String username, String password) {
    	Iterator<User> userList = users.iterator();
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

	/** Provides an iterator for the User list.
	 *  @return 			An iterator for the User list
	 */
	public Iterator<User> iterator() {
		return Collections.unmodifiableList(users).iterator();
	}
	
    private ArrayList<User> users;
    
}
