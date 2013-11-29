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

import shoppingCart.Inventory;
import shoppingCart.Product;

/**
 *  @author Newman Souza
 *  @author Seth Moore
 */
public class InventoryTest {
	
	private Inventory inventory;
	private BigDecimal initialRevenues;
	private BigDecimal initialCosts;
	private Product firstProduct;
	private Product lastProduct;

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
		inventory.add(new Product(0, "name0", "description0", new BigDecimal("1.00"), new BigDecimal("2.00"), 10));
		inventory.add(new Product(1, "name1", "description1", new BigDecimal("1.00"), new BigDecimal("2.00"), 10));
		inventory.add(new Product(2, "name2", "description2", new BigDecimal("1.00"), new BigDecimal("2.00"), 10));
		inventory.add(new Product(3, "name3", "description3", new BigDecimal("1.00"), new BigDecimal("2.00"), 10));
		initialCosts = inventory.getCosts();
		initialRevenues = inventory.getRevenues();
		Iterator<Product> iter = inventory.iterator();
		firstProduct = iter.next();
		while (iter.hasNext()) lastProduct = iter.next();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		inventory.clear();
	}

	@Test
	public void testGetInstance(){
		Inventory inv = Inventory.getInstance();
		assertTrue(inv == inventory);
	}

	/**
	 * Test method for {@link shoppingCart.Inventory#clear()}.
	 */
	@Test
	public void testClear() {
		inventory.add(new Product(99, "name", "des", new BigDecimal("0.00"), new BigDecimal("0.00"), 5));
		inventory.clear();
		assertTrue(inventory.iterator().hasNext() == false);
		assertEquals(new BigDecimal("0.00"), inventory.getProfits());
	}

	/**
	 * Test method for {@link shoppingCart.Inventory#increment(shoppingCart.Product)}.
	 */
	@Test
	public void testIncrement() {
		BigDecimal prevRevenues = inventory.getRevenues();
		BigDecimal sellPrice = lastProduct.getSellPrice();
		int quantity = lastProduct.getQuantity();
		inventory.increment(lastProduct);
		assertEquals(prevRevenues.subtract(sellPrice), inventory.getRevenues());
		assertEquals(quantity + 1, lastProduct.getQuantity());
	}

	/**
	 * Test method for {@link shoppingCart.Inventory#decrement(shoppingCart.Product)}.
	 */
	@Test
	public void testDecrement() {
		BigDecimal prevRevenues = inventory.getRevenues();
		BigDecimal sellPrice = firstProduct.getSellPrice();
		int quantity = firstProduct.getQuantity();
		inventory.decrement(firstProduct);
		assertEquals(prevRevenues.add(sellPrice), inventory.getRevenues());
		assertEquals(quantity - 1, firstProduct.getQuantity());
	}

	/**
	 * Test method for {@link shoppingCart.Inventory#getMatchingProduct(shoppingCart.Product)}.
	 */
	@Test
	public void testGetMatchingProduct() {
		Product p = (Product)firstProduct.clone();
		assertFalse(p == firstProduct);
		assertTrue(inventory.getMatchingProduct(p) == firstProduct);
		p = (Product)lastProduct.clone();
		p.update(p.getID(), "not", "a", new BigDecimal("99.33"), new BigDecimal("99.33"), 934);
		assertFalse(p == lastProduct);
		assertTrue(inventory.getMatchingProduct(p) == lastProduct);
		p.update(222, "not", "a", new BigDecimal("99.33"), new BigDecimal("99.33"), 934);
		assertTrue(inventory.getMatchingProduct(p) == null);
	}

	/**
	 * Test method for {@link shoppingCart.Inventory#add(shoppingCart.Product)}.
	 */
	@Test
	public void testAdd() {
		BigDecimal previousCosts = inventory.getCosts();
		Product p = new Product(99, "name", "description", new BigDecimal("10.00"), new BigDecimal("20.00"), 10);
		BigDecimal additionCost = new BigDecimal("100.00");
		inventory.add(p);
		assertEquals(previousCosts.add(additionCost), inventory.getCosts());
		assertFalse(p == inventory.getMatchingProduct(p));
		assertEquals(p, inventory.getMatchingProduct(p));
		assertEquals(p.getQuantity(), inventory.getMatchingProduct(p).getQuantity());
	}

	/**
	 * Test method for {@link shoppingCart.Inventory#remove(shoppingCart.Product)}.
	 */
	@Test
	public void testRemove() {
		inventory.remove(firstProduct);
		assertTrue(inventory.getMatchingProduct(firstProduct) == null);
	}

	/**
	 * Test method for {@link shoppingCart.Inventory#iterator()}.
	 */
	@Test
	public void testIterator() {
		inventory.clear();
		assertFalse(inventory.iterator().hasNext());
		Product p1 = new Product(1, "name", "desc", new BigDecimal("2.00"), new BigDecimal("2.00"), 10);
		Product p2 = new Product(2, "name", "desc", new BigDecimal("2.00"), new BigDecimal("2.00"), 10);
		Product p3 = new Product(3, "name", "desc", new BigDecimal("2.00"), new BigDecimal("2.00"), 10);
		inventory.add(p1);
		inventory.add(p2);
		inventory.add(p3);
		Iterator<Product> iter = inventory.iterator();
		assertTrue(iter.hasNext());
		assertEquals(p1, iter.next());
		while (iter.hasNext()) p1 = iter.next();
		assertEquals(p3, p1);
	}
	

	/**
	 * Test method for {@link shoppingCart.Inventory#getCosts()}.
	 */
	@Test
	public void testGetCosts() {
		assertEquals(new BigDecimal("40.00") ,inventory.getCosts());
	}

	/**
	 * Test method for {@link shoppingCart.Inventory#getRevenues()}.
	 */
	@Test
	public void testGetRevenues() {
		assertEquals(new BigDecimal("0.00") ,inventory.getRevenues());
	}

	/**
	 * Test method for {@link shoppingCart.Inventory#getProfits()}.
	 */
	@Test
	public void testGetProfits() {
		assertEquals(initialRevenues.subtract(initialCosts), inventory.getProfits());
	}
	
	/**
	 * Test method for {@link shoppingCart.Inventory#getNewID()}.
	 */
	@Test
	public void testGetNewID() {
		assertTrue(inventory.getNewID() == lastProduct.getID() + 1);
		inventory.clear();
		assertTrue(inventory.getNewID() == 1);
	}
	
	/**
	 * Test method for {@link shoppingCart.Inventory#update(shoppingCart.Product)}.
	 */
	@Test
	public void testUpdate() {
		Product p1 = (Product)firstProduct.clone();
		BigDecimal newInvoicePrice = new BigDecimal("100");
		p1.update(p1.getID(), "dk", "ff", newInvoicePrice, p1.getSellPrice(), firstProduct.getQuantity() + 1);
		inventory.update(p1);
		BigDecimal expectedCosts = initialCosts.add(newInvoicePrice);
		assertEquals(expectedCosts, inventory.getCosts());
		
		Product p2 = (Product)firstProduct.clone();
		newInvoicePrice = new BigDecimal("100");
		p2.update(p2.getID(), "dk", "ff", newInvoicePrice, p2.getSellPrice(), firstProduct.getQuantity() - 1);
		inventory.update(p2);
		assertEquals(expectedCosts, inventory.getCosts());
	}
	
	/**
	 * Test method for {@link shoppingCart.Cart#addListener(javax.swing.event.ChangeListener)}.
	 */
	@Test
	public void testAddListener() { 
		final StringBuffer sBuff = new StringBuffer();
		inventory.addListener(new
				ChangeListener(){

					@Override
					public void stateChanged(ChangeEvent arg0) {
						sBuff.append('1');
					}
		});
		assertEquals("", sBuff.substring(0));
		inventory.increment(firstProduct);
		assertEquals("1", sBuff.substring(0));
		inventory.decrement(lastProduct);
		assertEquals("11", sBuff.substring(0));
		Product p = (Product)firstProduct.clone();
		p.update(firstProduct.getID(), "", "", new BigDecimal("0.00"), new BigDecimal("0.00"), 55);
		inventory.update(p);
		assertEquals("111", sBuff.substring(0));
		inventory.add(new Product(66, "3", "3", new BigDecimal("0.00"), new BigDecimal("0.00"), 55));
		assertEquals("1111", sBuff.substring(0));
		inventory.remove(lastProduct);
		assertEquals("11111", sBuff.substring(0));
		inventory.clear();
		assertEquals("111111", sBuff.substring(0));
	}
}
