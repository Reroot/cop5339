package shoppingCart;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;

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
    	try {
			userList = dbManager.loadUserList();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		CartSystem cartSystem = new CartSystem();

		// TODO TO BE DELETED
//		Inventory inventory = Inventory.getInstance();
//		System.out.println("Creating inventory...");
//		inventory.add(new Product(101, "16Gb Corsair memory 1", "Corsair Vengeance 16Gb memory 1", new BigDecimal("101.00"), new BigDecimal("201.00"), 1));
//		inventory.add(new Product(102, "16Gb Corsair memory 2", "Corsair Vengeance 16Gb memory 2", new BigDecimal("102.00"), new BigDecimal("202.00"), 2));
//		inventory.add(new Product(103, "16Gb Corsair memory 3", "Corsair Vengeance 16Gb memory 3", new BigDecimal("103.00"), new BigDecimal("203.00"), 3));
//		inventory.add(new Product(104, "16Gb Corsair memory 4", "Corsair Vengeance 16Gb memory 4", new BigDecimal("104.00"), new BigDecimal("204.00"), 4));
//		inventory.add(new Product(105, "16Gb Corsair memory 5", "Corsair Vengeance 16Gb memory 5", new BigDecimal("105.00"), new BigDecimal("205.00"), 0));
//		inventory.add(new Product(106, "16Gb Corsair memory 6", "Corsair Vengeance 16Gb memory 6", new BigDecimal("106.00"), new BigDecimal("206.00"), 6));
//		inventory.add(new Product(107, "16Gb Corsair memory 7", "Corsair Vengeance 16Gb memory 7", new BigDecimal("107.00"), new BigDecimal("207.00"), 7));
//		inventory.add(new Product(108, "16Gb Corsair memory 8", "Corsair Vengeance 16Gb memory 8", new BigDecimal("108.00"), new BigDecimal("208.00"), 8));
//		inventory.add(new Product(109, "16Gb Corsair memory 9", "Corsair Vengeance 16Gb memory 9", new BigDecimal("109.00"), new BigDecimal("209.00"), 9));
//		inventory.add(new Product(110, "16Gb Corsair memory 10", "Corsair Vengeance 16Gb memory 10", new BigDecimal("110.00"), new BigDecimal("210.00"), 0));
//		inventory.add(new Product(111, "16Gb Corsair memory 11", "Corsair Vengeance 16Gb memory 11", new BigDecimal("111.00"), new BigDecimal("211.00"), 11));
//		inventory.add(new Product(112, "16Gb Corsair memory 12", "Corsair Vengeance 16Gb memory 12", new BigDecimal("112.00"), new BigDecimal("212.00"), 12));
//		inventory.add(new Product(113, "16Gb Corsair memory 13", "Corsair Vengeance 16Gb memory 13", new BigDecimal("113.00"), new BigDecimal("213.00"), 13));
//		inventory.add(new Product(114, "16Gb Corsair memory 14", "Corsair Vengeance 16Gb memory 14", new BigDecimal("114.00"), new BigDecimal("214.00"), 14));
//		inventory.add(new Product(115, "16Gb Corsair memory 15", "Corsair Vengeance 16Gb memory 15", new BigDecimal("115.00"), new BigDecimal("215.00"), 0));
//		inventory.add(new Product(116, "16Gb Corsair memory 16", "Corsair Vengeance 16Gb memory 16", new BigDecimal("116.00"), new BigDecimal("216.00"), 16));
//		inventory.add(new Product(117, "16Gb Corsair memory 17", "Corsair Vengeance 16Gb memory 17", new BigDecimal("117.00"), new BigDecimal("217.00"), 17));
//		inventory.add(new Product(118, "16Gb Corsair memory 18", "Corsair Vengeance 16Gb memory 18", new BigDecimal("118.00"), new BigDecimal("218.00"), 18));
//		inventory.add(new Product(119, "16Gb Corsair memory 19", "Corsair Vengeance 16Gb memory 19", new BigDecimal("119.00"), new BigDecimal("219.00"), 19));
//		inventory.add(new Product(120, "16Gb Corsair memory 20", "Corsair Vengeance 16Gb memory 20", new BigDecimal("120.00"), new BigDecimal("220.00"), 0));
//		inventory.add(new Product(121, "16Gb Corsair memory 21", "Corsair Vengeance 16Gb memory 21", new BigDecimal("121.00"), new BigDecimal("221.00"), 21));
//		inventory.add(new Product(122, "16Gb Corsair memory 22", "Corsair Vengeance 16Gb memory 22", new BigDecimal("122.00"), new BigDecimal("222.00"), 22));
//		inventory.add(new Product(123, "16Gb Corsair memory 23", "Corsair Vengeance 16Gb memory 23", new BigDecimal("123.00"), new BigDecimal("223.00"), 23));
//		inventory.add(new Product(124, "16Gb Corsair memory 24", "Corsair Vengeance 16Gb memory 24", new BigDecimal("124.00"), new BigDecimal("224.00"), 24));
//		inventory.add(new Product(125, "16Gb Corsair memory 25", "Corsair Vengeance 16Gb memory 25", new BigDecimal("125.00"), new BigDecimal("225.00"), 0));
//		System.out.println();
//		System.out.println("Saving Inventory...");
//	    cartSystem.dbManager.saveInventory(inventory);
//	    System.out.println("Loading Inventory...");
//	    Inventory invFromFile = cartSystem.dbManager.loadInventory();
//	    System.out.println();
//	    System.out.println("Displaying products from loaded Inventory:");
//		Iterator<Product> loadedInvIter = invFromFile.iterator();
//		while (loadedInvIter.hasNext()) {
//			Product product = (Product)loadedInvIter.next();
//			System.out.print("  " + product.getID() + ", ");
//			System.out.print("  " + product.getName() + ", ");
//			System.out.print("  " + product.getDescription() + ", ");
//			System.out.print("  " + product.getInvoicePrice() + ", ");
//			System.out.print("  " + product.getSellPrice() + ", ");
//			System.out.println("  " + product.getQuantity());
//		}
//	    System.out.println();

	    // TODO TO BE DELETED
	    System.out.println("Removing all users from UserList...");
	    cartSystem.userList.clear();
	    System.out.println("Adding users to UserList...");
		cartSystem.userList.addUser("Newman", "newman", "Seller");
		cartSystem.userList.addUser("Seth", "seth", "Customer");
		cartSystem.userList.addUser("John", "john", "Customer");
		cartSystem.userList.addUser("Mary", "mary", "Customer");
	    System.out.println();
        System.out.println("Saving UserList...");
        cartSystem.dbManager.saveUserList(cartSystem.userList);
	    System.out.println("Loading UserList...");
	    UserList userFromFile = cartSystem.dbManager.loadUserList();
        System.out.println();
	    System.out.println("Displaying users from loaded UserList:");
	    Iterator<User> loadedUserIter = userFromFile.iterator();
    	while (loadedUserIter.hasNext()) {
    		User user = (User)loadedUserIter.next();
    		System.out.print("  " + user.getUsername() + ", ");
    		System.out.print(user.getPassword() + ", ");
    		System.out.println(user.getType());
    	}

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
    	    try {
				Inventory inventory = dbManager.loadInventory(); // Because Inventory is a singleton, when UI calls getInstance()
																 // it will be this same instance.
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
    	boolean approved = paymentValidator.validate(cardNumber, total);
    	if (approved) {
    	    try {
    	    	Inventory inventory = Inventory.getInstance();
				dbManager.saveInventory(inventory);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	return approved;
    }
    
    private DBManager dbManager;
    private PaymentValidator paymentValidator;
    private UserList userList;
    
    
}
