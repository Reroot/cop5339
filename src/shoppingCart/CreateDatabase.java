package shoppingCart;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;

/** A class that creates the database for the ShoppingCart Application.
 *  The database has 2 files: the Seller's Inventory and the application's UserList.
 *  @author Newman Souza
 *  @author Seth Moore
 */ 
public class CreateDatabase {

	public static void main(String[] args) throws IOException, ClassNotFoundException {

    	DBManager dbManager = new DBManager();
		Inventory inventory = Inventory.getInstance();
		UserList userList = new UserList();

		// creates Inventory
	    System.out.println("Removing all products from Inventory...");
	    userList.clear();
		System.out.println("Creating new inventory...");
		inventory.setCosts(new BigDecimal("10000"));
		inventory.setRevenues(new BigDecimal("100000"));
		inventory.add(new Product(101, "16Gb Corsair memory 1", "Corsair Vengeance 16Gb memory 1", new BigDecimal("101.00"), new BigDecimal("201.00"), 1));
		inventory.add(new Product(102, "16Gb Corsair memory 2", "Corsair Vengeance 16Gb memory 2", new BigDecimal("102.00"), new BigDecimal("202.00"), 2));
		inventory.add(new Product(103, "16Gb Corsair memory 3", "Corsair Vengeance 16Gb memory 3", new BigDecimal("103.00"), new BigDecimal("203.00"), 3));
		inventory.add(new Product(104, "16Gb 4", "Corsair Vengeance 16Gb memory 4", new BigDecimal("104.00"), new BigDecimal("204.00"), 4));
		inventory.add(new Product(105, "16Gb Corsair memory 5", "Corsair Vengeance 16Gb memory 5", new BigDecimal("105.00"), new BigDecimal("205.00"), 0));
		inventory.add(new Product(106, "16Gb Corsair memory 6", "Corsair Vengeance 16Gb memory 6", new BigDecimal("106.00"), new BigDecimal("206.00"), 6));
		inventory.add(new Product(107, "16Gb Corsair memory 7", "Corsair Vengeance 16Gb memory 7", new BigDecimal("107.00"), new BigDecimal("207.00"), 7));
		inventory.add(new Product(108, "16Gb Corsair memory 8", "Corsair Vengeance 16Gb memory 8", new BigDecimal("108.00"), new BigDecimal("208.00"), 8));
		inventory.add(new Product(109, "16Gb Corsair memory Corsair memory 9", "Corsair Vengeance 16Gb memory 9", new BigDecimal("109.00"), new BigDecimal("209.00"), 9));
		inventory.add(new Product(110, "16Gb Corsair memory 10", "Corsair Vengeance 16Gb memory 10", new BigDecimal("110.00"), new BigDecimal("210.00"), 0));
		inventory.add(new Product(111, "16Gb Corsair memory 11", "Corsair Vengeance 16Gb memory 11", new BigDecimal("111.00"), new BigDecimal("211.00"), 11));
		inventory.add(new Product(112, "16Gb Corsair memory 12", "Corsair Vengeance 16Gb memory 12", new BigDecimal("112.00"), new BigDecimal("212.00"), 12));
		inventory.add(new Product(113, "16Gb Corsair memory 13", "Corsair Vengeance 16Gb memory 13", new BigDecimal("113.00"), new BigDecimal("213.00"), 13));
		inventory.add(new Product(114, "16Gb Corsair memory 14", "Corsair Vengeance 16Gb memory 14", new BigDecimal("114.00"), new BigDecimal("214.00"), 14));
		inventory.add(new Product(115, "16Gb Corsair memory 15", "Corsair Vengeance 16Gb memory 15", new BigDecimal("115.00"), new BigDecimal("215.00"), 0));
		inventory.add(new Product(116, "16Gb Corsair memory 16", "Corsair Vengeance 16Gb memory 16", new BigDecimal("116.00"), new BigDecimal("216.00"), 16));
		inventory.add(new Product(117, "16Gb Corsair memory 17", "Corsair Vengeance 16Gb memory 17", new BigDecimal("117.00"), new BigDecimal("217.00"), 17));
		inventory.add(new Product(118, "16Gb Corsair memory 18", "Corsair Vengeance 16Gb memory 18", new BigDecimal("118.00"), new BigDecimal("218.00"), 18));
		inventory.add(new Product(119, "16Gb Corsair memory 19", "Corsair Vengeance 16Gb memory 19", new BigDecimal("119.00"), new BigDecimal("219.00"), 19));
		inventory.add(new Product(120, "16Gb Corsair memory 20", "Corsair Vengeance 16Gb memory 20", new BigDecimal("120.00"), new BigDecimal("220.00"), 0));
		inventory.add(new Product(121, "16Gb Corsair memory 21", "Corsair Vengeance 16Gb memory 21", new BigDecimal("121.00"), new BigDecimal("221.00"), 21));
		inventory.add(new Product(122, "16Gb Corsair memory 22", "Corsair Vengeance 16Gb memory 22", new BigDecimal("122.00"), new BigDecimal("222.00"), 22));
		inventory.add(new Product(123, "16Gb Corsair memory 23", "Corsair Vengeance 16Gb memory 23", new BigDecimal("123.00"), new BigDecimal("223.00"), 23));
		inventory.add(new Product(124, "16Gb Corsair memory 24", "Corsair Vengeance 16Gb memory 24", new BigDecimal("124.00"), new BigDecimal("224.00"), 24));
		inventory.add(new Product(125, "16Gb Corsair memory 25", "Corsair Vengeance 16Gb memory 25", new BigDecimal("125.00"), new BigDecimal("225.00"), 0));
		System.out.println();
		System.out.println("Saving Inventory...");
		dbManager.saveInventory(inventory);
	    System.out.println("Loading Inventory...");
	    Inventory invFromFile = dbManager.loadInventory();
	    System.out.println();
	    System.out.println("Displaying products from loaded Inventory:");
		Iterator<Product> loadedInvIter = invFromFile.iterator();
		while (loadedInvIter.hasNext()) {
			Product product = (Product)loadedInvIter.next();
			System.out.print("  " + product.getID() + ", ");
			System.out.print("  " + product.getName() + ", ");
			System.out.print("  " + product.getDescription() + ", ");
			System.out.print("  " + product.getInvoicePrice() + ", ");
			System.out.print("  " + product.getSellPrice() + ", ");
			System.out.println("  " + product.getQuantity());
		}
	    System.out.println();

		// creates UserList
	    System.out.println("Removing all users from UserList...");
	    userList.clear();
	    System.out.println("Creating new UserList...");
		userList.addUser("Newman", "newman", "Seller");
		userList.addUser("Seth", "seth", "Customer");
		userList.addUser("John", "john", "Customer");
		userList.addUser("Mary", "mary", "Customer");
		System.out.println();
		System.out.println("Saving UserList...");
		dbManager.saveUserList(userList);
		System.out.println("Loading UserList...");
		UserList userFromFile = dbManager.loadUserList();
//		System.out.println();
//		System.out.println("Displaying users from loaded UserList:");
//		Iterator<User> loadedUserIter = userFromFile.iterator();
//		while (loadedUserIter.hasNext()) {
//    		User user = (User)loadedUserIter.next();
//    		System.out.print("  " + user.getUsername() + ", ");
//    		System.out.print(user.getPassword() + ", ");
//    		System.out.println(user.getType());
//		}
	}
}
