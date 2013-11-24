package shoppingCart;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;

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
    public Inventory loadInventory() {
    	Inventory inventory = null;
    	try {
			ObjectInputStream in = new ObjectInputStream (new FileInputStream("database\\Inventory.dat"));
			inventory = (Inventory)in.readObject(); 
			in.close();        
		} catch (IOException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Inventory database not found.");
			System.exit(0);
		}
		return inventory;
    }

    /** Saves Inventory to database.
     *  @param inventory	The Seller's inventory
     *  @precondition 		inventory is a valid reference
     *  @postcondition  	The Seller's inventory is saved to file
     *  @throws 			IOException 
     */
    public void saveInventory(Inventory inventory) {
    	try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("database\\Inventory.dat")); 
            out.writeObject(inventory); 
            out.close(); 
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Inventory database not found.");
			System.exit(0);
		}
    }

    /** Loads the list of users from database.
     *  @return 			The list of users
     *  @precondition 		Database file is valid and available in file system
     *  @postcondition  	The list of users is loaded
     *  @throws 			ClassNotFoundException 
     */
    public UserList loadUserList() {
    	UserList fromFile = null;
    	try {
			ObjectInputStream in = new ObjectInputStream (new FileInputStream("database\\UserList.dat"));
			fromFile = (UserList)in.readObject(); 
			in.close();        
		} catch (IOException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "UserList database not found.");
			System.exit(0);
		}
		return fromFile;
    }

    /** Saves the list of users to database.
     *  @param userList		The list of users
     *  @precondition 		userList is a valid reference
     *  @postcondition  	The list of users is saved to file
     */
    public void saveUserList(UserList userList) {
    	try {
	        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("database\\UserList.dat")); 
	        out.writeObject(userList); 
	        out.close(); 
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "UserList database not found.");
			System.exit(0);
		}
    }

}
