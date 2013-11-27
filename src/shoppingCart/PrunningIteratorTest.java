/**
 * 
 */
package shoppingCart;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *  @author Newman Souza
 *  @author Seth Moore
 */
public class PrunningIteratorTest {
	
	Inventory inventory;
	PrunningIterator iterator;
	Product p2;

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
		inventory = Inventory.getInstance();
		p2 = new Product(23, "sam2", "ssld", new BigDecimal("1.00"), new BigDecimal("1.00"), 4);
		inventory.add(new Product(22, "sam1", "ssld", new BigDecimal("1.00"), new BigDecimal("1.00"), 0));
		inventory.add(p2);
		inventory.add(new Product(24, "sam3", "ssld", new BigDecimal("1.00"), new BigDecimal("1.00"), 2));
		inventory.add(new Product(25, "sam4", "ssld", new BigDecimal("1.00"), new BigDecimal("1.00"), 0));
		inventory.add(new Product(26, "sam5", "ssld", new BigDecimal("1.00"), new BigDecimal("1.00"), 8));
		inventory.add(new Product(27, "sam6", "ssld", new BigDecimal("1.00"), new BigDecimal("1.00"), 0));
		iterator = new PrunningIterator(inventory.iterator());
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		inventory.clear();
	}

	/**
	 * Test method for {@link shoppingCart.PrunningIterator#PrunningIterator(java.util.Iterator)}.
	 */
	@Test
	public void testPrunningIterator() {
		PrunningIterator iter = new PrunningIterator(inventory.iterator());
	}

	/**
	 * Test method for {@link shoppingCart.PrunningIterator#hasNext()}.
	 */
	@Test
	public void testHasNext() {
		int count = 0;
		while (iterator.hasNext()){
			Product p = iterator.next();
			System.out.println(p.getName());
			count++;
		}
		assertTrue(count == 3);
	}

	/**
	 * Test method for {@link shoppingCart.PrunningIterator#next()}.
	 */
	@Test
	public void testNext() {
		Product p = iterator.next();
		assertTrue(p.getQuantity() == p2.getQuantity());
		assertTrue(p.getID() == p2.getID());
		assertEquals(p2.getName(), p.getName());
	}

	/**
	 * Test method for {@link shoppingCart.PrunningIterator#remove()}.
	 */
	@Test(expected=UnsupportedOperationException.class)
	public void testRemove() {
		iterator.remove();
	}

}
