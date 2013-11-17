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
 * @author Seth
 *
 */
public class CartTest {

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

	@Test
	public void testGetInstance(){
		Cart cart1 = Cart.getInstance();
		Cart cart2 = Cart.getInstance();
		assertTrue(cart1 == cart2);
	}
	
	/**
	 * Test method for {@link shoppingCart.Cart#ProductList()}.
	 */
	@Test
	public void testProductList() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link shoppingCart.Cart#clear()}.
	 */
	@Test
	public void testClear() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link shoppingCart.Cart#increment(shoppingCart.Product)}.
	 */
	@Test
	public void testIncrement() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link shoppingCart.Cart#decrement(shoppingCart.Product)}.
	 */
	@Test
	public void testDecrement() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link shoppingCart.Cart#getMatchingProduct(shoppingCart.Product)}.
	 */
	@Test
	public void testGetMatchingProduct() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link shoppingCart.Cart#add(shoppingCart.Product)}.
	 */
	@Test
	public void testAdd() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link shoppingCart.Cart#remove(shoppingCart.Product)}.
	 */
	@Test
	public void testRemove() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link shoppingCart.Cart#iterator()}.
	 */
	@Test
	public void testIterator() {
		fail("Not yet implemented");
	}
	
	/**
	 * Test method for {@link shoppingCart.Cart#getTotal()}.
	 */
	@Test
	public void testGetTotal() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link shoppingCart.Cart#getQuantity()}.
	 */
	@Test
	public void testGetQuantity() {
		fail("Not yet implemented");
	}

}
