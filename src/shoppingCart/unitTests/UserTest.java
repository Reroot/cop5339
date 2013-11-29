/**
 * 
 */
package shoppingCart.unitTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import shoppingCart.model.User;

/**
 *  @author Newman Souza
 *  @author Seth Moore
 */
public class UserTest {
	
	User user;

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
		user = new User("username", "password", "type");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link shoppingCart.model.User#User(java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testUser() {
		User u = new User("1", "2", "3");
		assertEquals("1", u.getUsername());
		assertEquals("3", u.getType());
		assertTrue(u.checkPassword("2"));
	}

	/**
	 * Test method for {@link shoppingCart.model.User#getUsername()}.
	 */
	@Test
	public void testGetUsername() {
		assertEquals("username", user.getUsername());
	}

	/**
	 * Test method for {@link shoppingCart.model.User#getType()}.
	 */
	@Test
	public void testGetType() {
		assertEquals("type", user.getType());
	}

	/**
	 * Test method for {@link shoppingCart.model.User#checkPassword(java.lang.String)}.
	 */
	@Test
	public void testCheckPassword() {
		assertFalse(user.checkPassword("not"));
		assertTrue(user.checkPassword("password"));
	}
}
