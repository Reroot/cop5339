/**
 * 
 */
package shoppingCart.unitTests;

import static org.junit.Assert.*;

import java.io.File;
import java.math.BigDecimal;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import shoppingCart.model.Inventory;
import shoppingCart.model.Product;
import shoppingCart.model.UserList;
import shoppingCart.system.DBManager;

/**
 *  @author Newman Souza
 *  @author Seth Moore
 */
public class DBManagerTest {

	private String invSaveFile = ".\\inv.dat";
	private String userSaveFile = ".\\user.dat";
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		Inventory.getInstance().clear();
	}

	/**
	 * Test method for {@link shoppingCart.system.DBManager#loadInventory()}.
	 */
	@Test
	public void testLoadInventory() {
		BigDecimal bd = new BigDecimal("20.00");
		Inventory inventory = Inventory.getInstance();
		Product p = new Product(0, "name1", "description", bd, bd, 10);
		inventory.add(p);
		inventory.add(new Product(1, "name", "description", bd, bd, 10));
		inventory.add(new Product(2, "name", "description", bd, bd, 10));
		inventory.add(new Product(3, "name", "description", bd, bd, 10));
		DBManager db = new DBManager();
		db.saveInventory(inventory, invSaveFile);
		inventory.clear();
		inventory = db.loadInventory(invSaveFile);
		assertFalse(inventory.getMatchingProduct(p) == null);
		assertEquals(inventory.getMatchingProduct(p).getName(), p.getName());
		assertFalse(inventory.getMatchingProduct(p) == p);
		assertEquals(inventory.getCosts(), bd.multiply(BigDecimal.valueOf(40)));
		File file = new File(invSaveFile);
		file.delete();
	}

	/**
	 * Test method for {@link shoppingCart.system.DBManager#saveInventory(shoppingCart.model.Inventory)}.
	 */
	@Test
	public void testSaveInventory() {
		BigDecimal bd = new BigDecimal("20.00");
		Inventory inventory = Inventory.getInstance();
		Product p = new Product(0, "name1", "description", bd, bd, 10);
		inventory.add(p);
		inventory.add(new Product(1, "name", "description", bd, bd, 10));
		inventory.add(new Product(2, "name", "description", bd, bd, 10));
		inventory.add(new Product(3, "name", "description", bd, bd, 10));
		DBManager db = new DBManager();
		db.saveInventory(inventory, invSaveFile);
		inventory.clear();
		inventory = db.loadInventory(invSaveFile);
		assertFalse(inventory.getMatchingProduct(p) == null);
		assertEquals(inventory.getMatchingProduct(p).getName(), p.getName());
		assertFalse(inventory.getMatchingProduct(p) == p);
		assertEquals(inventory.getCosts(), bd.multiply(BigDecimal.valueOf(40)));
		File file = new File(invSaveFile);
		file.delete();
	}

	/**
	 * Test method for {@link shoppingCart.system.DBManager#loadUserList()}.
	 */
	@Test
	public void testLoadUserList() {
		UserList userList = new UserList();
		userList.addUser("username", "password", "type");
		userList.addUser("user", "pass", "ty");
		userList.addUser("name", "word", "pe");
		DBManager db = new DBManager();
		db.saveUserList(userList, userSaveFile);
		userList = new UserList();
		userList = db.loadUserList(userSaveFile);
		assertEquals(userList.validate("user", "pass"), "ty");
		
		File file = new File(userSaveFile);
		file.delete();
	}

	/**
	 * Test method for {@link shoppingCart.system.DBManager#saveUserList(shoppingCart.model.UserList)}.
	 */
	@Test
	public void testSaveUserList() {
		UserList userList = new UserList();
		userList.addUser("username", "password", "type");
		userList.addUser("user", "pass", "ty");
		userList.addUser("name", "word", "pe");
		DBManager db = new DBManager();
		db.saveUserList(userList, userSaveFile);
		userList = new UserList();
		userList = db.loadUserList(userSaveFile);
		assertEquals(userList.validate("user", "pass"), "ty");
		
		File file = new File(userSaveFile);
		file.delete();
	}

}
