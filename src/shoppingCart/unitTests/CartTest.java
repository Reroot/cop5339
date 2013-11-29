/**
 * 
 */
package shoppingCart.unitTests;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Iterator;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import shoppingCart.Cart;
import shoppingCart.Product;

/**
 *  @author Newman Souza
 *  @author Seth Moore
 */
public class CartTest {

	Cart cart;
	Product firstProduct;
	Product lastProduct;
	BigDecimal initialTotal;
	int initialQuantity;
	
	
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
		cart = Cart.getInstance();
		cart.add(new Product(0, "name0", "description0", new BigDecimal("1.00"), new BigDecimal("2.00"), 10));
		cart.add(new Product(1, "name1", "description1", new BigDecimal("1.00"), new BigDecimal("2.00"), 10));
		cart.add(new Product(2, "name2", "description2", new BigDecimal("1.00"), new BigDecimal("2.00"), 10));
		cart.add(new Product(3, "name3", "description3", new BigDecimal("1.00"), new BigDecimal("2.00"), 10));
		
		initialTotal = new BigDecimal("80.00");
		initialQuantity = 40;
		
		Iterator<Product> iter = cart.iterator();
		firstProduct = iter.next();
		while (iter.hasNext()) lastProduct = iter.next();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		cart.clear();
	}

	@Test
	public void testGetInstance(){
		Cart cart2 = Cart.getInstance();
		assertTrue(cart2 == cart);
	}
	
	/**
	 * Test method for {@link shoppingCart.Cart#clear()}.
	 */
	@Test
	public void testClear() {
		assertTrue(cart.iterator().hasNext());
		assertTrue(cart.getQuantity() > 0);
		cart.clear();
		assertFalse(cart.iterator().hasNext());
		assertTrue(cart.getQuantity() == 0);
		assertEquals(new BigDecimal("0.00"), cart.getTotal());
	}

	/**
	 * Test method for {@link shoppingCart.Cart#increment(shoppingCart.Product)}.
	 */
	@Test
	public void testIncrement() {
		int quantity = lastProduct.getQuantity();
		cart.increment(lastProduct);
		assertEquals(quantity + 1, lastProduct.getQuantity());
	}

	/**
	 * Test method for {@link shoppingCart.Cart#decrement(shoppingCart.Product)}.
	 */
	@Test
	public void testDecrement() {
		int quantity = lastProduct.getQuantity();
		cart.decrement(lastProduct);
		assertEquals(quantity - 1, lastProduct.getQuantity());
	}

	/**
	 * Test method for {@link shoppingCart.Cart#getMatchingProduct(shoppingCart.Product)}.
	 */
	@Test
	public void testGetMatchingProduct() {
		Product p = (Product)firstProduct.clone();
		assertFalse(p == firstProduct);
		assertTrue(cart.getMatchingProduct(p) == firstProduct);
		p = (Product)lastProduct.clone();
		p.update(p.getID(), "not", "a", new BigDecimal("99.33"), new BigDecimal("99.33"), 934);
		assertFalse(p == lastProduct);
		assertTrue(cart.getMatchingProduct(p) == lastProduct);
		p.update(222, "not", "a", new BigDecimal("99.33"), new BigDecimal("99.33"), 934);
		assertTrue(cart.getMatchingProduct(p) == null);
	}

	/**
	 * Test method for {@link shoppingCart.Cart#add(shoppingCart.Product)}.
	 */
	@Test
	public void testAdd() {
		Product p = new Product(99, "name", "description", new BigDecimal("10.00"), new BigDecimal("20.00"), 10);
		cart.add(p);
		assertFalse(p == cart.getMatchingProduct(p));
		assertEquals(p, cart.getMatchingProduct(p));
		assertEquals(p.getQuantity(), cart.getMatchingProduct(p).getQuantity());
	}

	/**
	 * Test method for {@link shoppingCart.Cart#remove(shoppingCart.Product)}.
	 */
	@Test
	public void testRemove() {
		cart.remove(firstProduct);
		assertTrue(cart.getMatchingProduct(firstProduct) == null);
	}

	/**
	 * Test method for {@link shoppingCart.Cart#iterator()}.
	 */
	@Test
	public void testIterator() {
		cart.clear();
		assertFalse(cart.iterator().hasNext());
		Product p1 = new Product(1, "name", "desc", new BigDecimal("2.00"), new BigDecimal("2.00"), 10);
		Product p2 = new Product(2, "name", "desc", new BigDecimal("2.00"), new BigDecimal("2.00"), 10);
		Product p3 = new Product(3, "name", "desc", new BigDecimal("2.00"), new BigDecimal("2.00"), 10);
		cart.add(p1);
		cart.add(p2);
		cart.add(p3);
		Iterator<Product> iter = cart.iterator();
		assertTrue(iter.hasNext());
		assertEquals(p1, iter.next());
		while (iter.hasNext()) p1 = iter.next();
		assertEquals(p3, p1);
	}
	
	/**
	 * Test method for {@link shoppingCart.Cart#getTotal()}.
	 */
	@Test
	public void testGetTotal() {
		assertEquals(initialTotal, cart.getTotal());
	}

	/**
	 * Test method for {@link shoppingCart.Cart#getQuantity()}.
	 */
	@Test
	public void testGetQuantity() {
		assertEquals(initialQuantity, cart.getQuantity());
	}
	
	/**
	 * Test method for {@link shoppingCart.Cart#addListener(javax.swing.event.ChangeListener)}.
	 */
	@Test
	public void testAddListener() {final StringBuffer sBuff = new StringBuffer();
	cart.addListener(new
			ChangeListener(){

				@Override
				public void stateChanged(ChangeEvent arg0) {
					sBuff.append('1');
				}
	});
	assertEquals("", sBuff.substring(0));
	cart.increment(firstProduct);
	assertEquals("1", sBuff.substring(0));
	cart.decrement(lastProduct);
	assertEquals("11", sBuff.substring(0));
	Product p = (Product)firstProduct.clone();
	p.update(firstProduct.getID(), "", "", new BigDecimal("0.00"), new BigDecimal("0.00"), 55);
	cart.add(new Product(66, "3", "3", new BigDecimal("0.00"), new BigDecimal("0.00"), 55));
	assertEquals("111", sBuff.substring(0));
	cart.remove(lastProduct);
	assertEquals("1111", sBuff.substring(0));
	cart.clear();
	assertEquals("11111", sBuff.substring(0));
	}

}
