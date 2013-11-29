/**
 * 
 */
package shoppingCart.unitTests;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import shoppingCart.system.CartSystem;

/**
 *  @author Newman Souza
 *  @author Seth Moore
 */
public class CartSystemTest {

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
	 * Test method for {@link shoppingCart.system.CartSystem#CartSystem()}.
	 */
	@Test
	public void testCartSystem() {
		CartSystem cs = new CartSystem();
		assertFalse(cs == null);
	}

	/**
	 * Test method for {@link shoppingCart.system.CartSystem#login(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testLogin() {
		CartSystem cs = new CartSystem();
		assertEquals("Seller", cs.login("Newman", "newman"));
	}

	/**
	 * Test method for {@link shoppingCart.system.CartSystem#pay(java.lang.String, java.math.BigDecimal)}.
	 */
	@Test
	public void testPay() {
		CartSystem cs = new CartSystem();
		assertTrue(cs.pay("anything", new BigDecimal("99.99")));
	}

}
