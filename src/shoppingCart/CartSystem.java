package shoppingCart;

import java.math.BigDecimal;

/** A class representing a Shopping Cart application.
 *  The application performs different functions depending on who logs in.
 *  It allows a seller to maintain an inventory of items available for sale
 *  and customers to browse and add items to their cart, and purchase the contents of their cart.
 *  @author Newman Souza
 *  @author Seth Moore
 */ 
public class CartSystem {

    /** Constructs a CartSystem object.
     *  @precondition 		none
     *  @postcondition 		object created
     */
    public CartSystem() {
    	dbManager = new DBManager();
    	paymentValidator = new PaymentValidator();
    	UI ui = new UI(this);
    	userList = new UserList();
		userList = dbManager.loadUserList();
    }

	public static void main(String[] args) {
		CartSystem cartSystem = new CartSystem();
	}
    
    /** Logs in a user as a customer or a seller.
     *  @param username		the User's username
     *  @param password		the User's password
     *  @return 			the User's type
     *  @precondition 		username and password are valid references
     *  @postcondition 		user is logged in
     */
    public String login(String username, String password) {
    	String type = userList.validate(username, password);
    	if (type != null) {
    		Inventory inventory = Inventory.getInstance();	// Inventory is a singleton, getInstance() guarantees that
			inventory = dbManager.loadInventory(); 			//   this same instance will always be used it gets called.
    	}
    	return type;
    }

    /** Processes payment for for items in Customer's cart.
     *  @param  			the customer's payment information
     *  @return 			true if successful and false otherwise
     *  @precondition 		none
     *  @postcondition 		payment processed
     */
    public boolean pay(String cardNumber, BigDecimal total) {
    	boolean result = paymentValidator.validate(cardNumber, total);
    	if (result) {
    		saveInventory();
    	}
    	return result;
    }
    
    public void saveInventory() {
    	Inventory inventory = Inventory.getInstance();
		dbManager.saveInventory(inventory);
    }
    
    private DBManager dbManager;
    private PaymentValidator paymentValidator;
    private UserList userList;
    
}
