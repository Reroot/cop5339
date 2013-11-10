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
 * @author Seth
 *
 */
public class ProductTest {

	int ID = 1, quantity = 2;
	String name = "apple", description = "crisp";
	BigDecimal sellPrice = new BigDecimal("1.25"), invoicePrice = new BigDecimal("0.40");
	Product product;
	
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
		product = new Product(ID, name, description, sellPrice, invoicePrice, quantity);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link shoppingCart.Product#hashCode()}.
	 */
	@Test
	public void testHashCode() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link shoppingCart.Product#Product(int, java.lang.String, java.lang.String, java.math.BigDecimal, java.math.BigDecimal, int)}.
	 */
	@Test
	public void testProduct() {
		int ID = 1, quantity = 2;
		String name = "apple", description = "crisp";
		BigDecimal sellPrice = new BigDecimal("1.25"), invoicePrice = new BigDecimal("0.40");
		Product p = new Product(ID, name, description, sellPrice, invoicePrice, quantity);
		assertEquals(p.getID(), ID);
		assertEquals(p.getName(), name);
		assertEquals(p.getDescription(), description);
		assertEquals(p.getSellPrice(), sellPrice);
		assertEquals(p.getInvoicePrice(), invoicePrice);
		assertEquals(p.getQuantity(), quantity);
	}

	/**
	 * Test method for {@link shoppingCart.Product#update(int, java.lang.String, java.lang.String, java.math.BigDecimal, java.math.BigDecimal, int)}.
	 */
	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link shoppingCart.Product#increment()}.
	 */
	@Test
	public void testIncrement() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link shoppingCart.Product#decrement()}.
	 */
	@Test
	public void testDecrement() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link shoppingCart.Product#getID()}.
	 */
	@Test
	public void testGetID() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link shoppingCart.Product#getName()}.
	 */
	@Test
	public void testGetName() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link shoppingCart.Product#getDescription()}.
	 */
	@Test
	public void testGetDescription() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link shoppingCart.Product#getSellPrice()}.
	 */
	@Test
	public void testGetSellPrice() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link shoppingCart.Product#getInvoicePrice()}.
	 */
	@Test
	public void testGetInvoicePrice() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link shoppingCart.Product#getQuantity()}.
	 */
	@Test
	public void testGetQuantity() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link shoppingCart.Product#clone()}.
	 */
	@Test
	public void testClone() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link shoppingCart.Product#addListener(javax.swing.event.ChangeListener)}.
	 */
	@Test
	public void testAddListener() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link shoppingCart.Product#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject() {
		Product p = new Product(ID, name, description, sellPrice, invoicePrice, quantity);
		assertTrue(p.equals(product));
		p = new Product(ID, name, description, sellPrice, invoicePrice, 0);
		assertTrue(p.equals(product));
		p = new Product(ID, "not an apple", description, sellPrice, invoicePrice, 0);
		assertFalse(p.equals(product));
	}

}
