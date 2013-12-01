package shoppingCart.system;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;

import shoppingCart.model.Inventory;
import shoppingCart.model.Product;
import shoppingCart.model.UserList;

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
		inventory.setCosts(new BigDecimal("0"));
		inventory.setRevenues(new BigDecimal("30000"));
		inventory.add(new Product(101, "COP4331 Book - PDF", "COP4331 Book (Cay Horstmann) - PDF", new BigDecimal("25.00"), new BigDecimal("50.00"), 5));
		inventory.add(new Product(102, "COP4331 Book - Paperback", "COP4331 Book (Cay Horstmann) - Paperback", new BigDecimal("35.00"), new BigDecimal("70.00"), 100));
		inventory.add(new Product(103, "COP4331 HW-1", "Solutions for Homework 1", new BigDecimal("20.00"), new BigDecimal("30.00"), 10));
		inventory.add(new Product(104, "COP4331 HW-2", "Solutions for Homework 2", new BigDecimal("20.00"), new BigDecimal("30.00"), 15));
		inventory.add(new Product(105, "COP4331 HW-3", "Solutions for Homework 3", new BigDecimal("30.00"), new BigDecimal("50.00"), 0));
		inventory.add(new Product(106, "COP4331 HW-4", "Solutions for Homework 4", new BigDecimal("30.00"), new BigDecimal("50.00"), 12));
		inventory.add(new Product(107, "COP4331 HW-5", "Solutions for Homework 5", new BigDecimal("20.00"), new BigDecimal("30.00"), 18));
		inventory.add(new Product(108, "COP4331 HW-6", "Solutions for Homework 6", new BigDecimal("40.00"), new BigDecimal("80.00"), 0));
		inventory.add(new Product(109, "COP4331 Homework (bundle)", "Solutions for all Homework (Bundle)", new BigDecimal("100.00"), new BigDecimal("200.00"), 4));
		inventory.add(new Product(110, "COP5339 HW-1 (graduate)", "Solutions for Homework 1 (graduate)", new BigDecimal("15.00"), new BigDecimal("45.00"), 10));
		inventory.add(new Product(111, "COP5339 HW-2 (graduate)", "Solutions for Homework 2 (graduate)", new BigDecimal("15.00"), new BigDecimal("45.00"), 0));
		inventory.add(new Product(112, "COP5339 HW-3 (graduate)", "Solutions for Homework 3 (graduate)", new BigDecimal("20.00"), new BigDecimal("60.00"), 8));
		inventory.add(new Product(113, "COP5339 HW-4 (graduate)", "Solutions for Homework 4 (graduate)", new BigDecimal("20.00"), new BigDecimal("60.00"), 20));
		inventory.add(new Product(114, "COP5339 HW-5 (graduate)", "Solutions for Homework 5 (graduate)", new BigDecimal("15.00"), new BigDecimal("45.00"), 0));
		inventory.add(new Product(115, "COP5339 HW-6 (graduate)", "Solutions for Homework 6 (graduate)", new BigDecimal("30.00"), new BigDecimal("80.00"), 16));
		inventory.add(new Product(116, "COP5339 Homework (bundle)", "Solutions for all Homework (Bundle)", new BigDecimal("150.00"), new BigDecimal("270.00"), 6));
		inventory.add(new Product(117, "Newman's picture", "Newman's autographed picture", new BigDecimal("5.00"), new BigDecimal("10.00"), 2));
		inventory.add(new Product(118, "Seth's picture", "Seth's autographed picture", new BigDecimal("5.00"), new BigDecimal("10.00"), 2));
		inventory.add(new Product(119, "Cardei's picture", "Cardei's autographed picture", new BigDecimal("5000.00"), new BigDecimal("10000.00"), 1));
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
