package shoppingCart;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/** A class that manages the Shopping Cart database.
 *  @author Newman Souza
 *  @author Seth Moore
 */ 
public class DBManager {
	
    /** Loads Inventory from database.
     *  @return 			The Seller's inventory
     *  @precondition 		Database file is valid and available in file system
     *  @postcondition  	The Seller's inventory is loaded
     *  @throws 			IOException 
     *  @throws 			FileNotFoundException 
     *  @throws 			ClassNotFoundException 
     */
    public Inventory loadInventory() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream (new FileInputStream("database\\Inventory.dat"));
		Inventory inventory = (Inventory)in.readObject(); 
		in.close();        
		return inventory;
    }

    /** Saves Inventory to database.
     *  @param inventory	The Seller's inventory
     *  @precondition 		inventory is a valid reference
     *  @postcondition  	The Seller's inventory is saved to file
     *  @throws 			IOException 
     */
    public void saveInventory(Inventory inventory) throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("database\\Inventory.dat")); 
        out.writeObject(inventory); 
        out.close(); 
    }

    /** Loads the list of users from database.
     *  @return 			The list of users
     *  @precondition 		Database file is valid and available in file system
     *  @postcondition  	The list of users is loaded
     *  @throws 			ClassNotFoundException 
     */
    public UserList loadUserList() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream (new FileInputStream("database\\UserList.dat"));
		UserList fromFile = (UserList)in.readObject(); 
		in.close();        
		return fromFile;
    }

    /** Saves the list of users to database.
     *  @param userList		The list of users
     *  @precondition 		userList is a valid reference
     *  @postcondition  	The list of users is saved to file
     */
    public void saveUserList(UserList userList) throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("database\\UserList.dat")); 
        out.writeObject(userList); 
        out.close(); 
    }

}
