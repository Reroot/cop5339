/**
 * 
 */
package shoppingCart;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *  @author Newman Souza
 *  @author Seth Moore
 */
public class UserListTest {

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
	}

	/**
	 * Test method for {@link shoppingCart.UserList#UserList()}.
	 */
	@Test
	public void testUserList() {
		UserList uList = new UserList();
		assertTrue(uList.validate("username", "password") == null);
	}

	/**
	 * Test method for {@link shoppingCart.UserList#addUser(java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testAddUser() {
		UserList uList = new UserList();
		uList.addUser("username", "password", "type");
		assertTrue(uList.validate("name", "word") == null);
		assertFalse(uList.validate("username", "password") == null);
	}

	/**
	 * Test method for {@link shoppingCart.UserList#validate(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testValidate() {UserList uList = new UserList();
	uList.addUser("username", "password", "type");
	uList.addUser("user", "pass", "pe");
	uList.addUser("name", "word", "ty");
	assertTrue(uList.validate("name", "pass") == null);
	assertEquals("ty", uList.validate("name", "word"));
	assertEquals("type", uList.validate("username", "password"));
	assertEquals("pe", uList.validate("user", "pass"));
	}

}
