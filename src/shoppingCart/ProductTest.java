/**
 * 
 */
package shoppingCart;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Seth Moore
 *
 */
public class ProductTest {

	final int ID = 1, quantity = 2;
	final String name = "apple", description = "crisp";
	final BigDecimal sellPrice = new BigDecimal("1.25"), invoicePrice = new BigDecimal("0.40");
	Product product;
	String changeListenerString; 
	
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
		product = new Product(ID, name, description, invoicePrice, sellPrice, quantity);
		product.addListener(new
				ChangeListener(){

					@Override
					public void stateChanged(ChangeEvent arg0) {
						changeListenerString = ((Product)arg0.getSource()).getName();
					}
		});
		changeListenerString = "FAIL";
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
		Product p = new Product(ID, name, description, invoicePrice, sellPrice, quantity);
		assertTrue(p.hashCode() == product.hashCode());
		p.update(ID, name, description, invoicePrice, sellPrice, 20);
		assertTrue(p.hashCode() == product.hashCode());
		p.update(33, name, description, invoicePrice, sellPrice, 20);
		assertFalse(p.hashCode() == product.hashCode());
		p.update(ID, name, description, new BigDecimal("33.33"), sellPrice, 20);
		assertTrue(p.hashCode() == product.hashCode());
		p.update(999, name, description, invoicePrice, sellPrice, 20);
		assertFalse(p.hashCode() == product.hashCode());
	}

	/**
	 * Test method for {@link shoppingCart.Product#Product(int, java.lang.String, java.lang.String, java.math.BigDecimal, java.math.BigDecimal, int)}.
	 */
	@Test
	public void testProduct() {
		int ID = 1, quantity = 2;
		String name = "apple", description = "crisp";
		BigDecimal sellPrice = new BigDecimal("1.25"), invoicePrice = new BigDecimal("0.40");
		Product p = new Product(ID, name, description, invoicePrice, sellPrice, quantity);
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
		assertEquals(changeListenerString, "FAIL");
		int testInt = 5;
		String testString = "test";
		BigDecimal testDec = new BigDecimal("1.99");
		product.update(testInt, testString, testString, testDec, testDec, testInt);
		assertEquals(product.getID(), testInt);
		assertEquals(product.getName(), testString);
		assertEquals(product.getDescription(), testString);
		assertEquals(product.getSellPrice(), testDec);
		assertEquals(product.getInvoicePrice(), testDec);
		assertEquals(product.getQuantity(), testInt);
		assertEquals(changeListenerString, product.getName());
	}

	/**
	 * Test method for {@link shoppingCart.Product#increment()}.
	 */
	@Test
	public void testIncrement() {
		assertEquals(changeListenerString, "FAIL");
		product.increment();
		assertTrue(product.getQuantity() == quantity + 1);
		assertEquals(changeListenerString, product.getName());
	}

	/**
	 * Test method for {@link shoppingCart.Product#decrement()}.
	 */
	@Test
	public void testDecrement() {
		assertEquals(changeListenerString, "FAIL");
		product.decrement();
		assertTrue(product.getQuantity() == quantity -1);
		assertEquals(changeListenerString, product.getName());
	}

	/**
	 * Test method for {@link shoppingCart.Product#getID()}.
	 */
	@Test
	public void testGetID() {
		assertTrue(product.getID() == ID);
	}

	/**
	 * Test method for {@link shoppingCart.Product#getName()}.
	 */
	@Test
	public void testGetName() {
		assertEquals(product.getName(), name);
	}

	/**
	 * Test method for {@link shoppingCart.Product#getDescription()}.
	 */
	@Test
	public void testGetDescription() {
		assertEquals(product.getDescription(), description);
	}

	/**
	 * Test method for {@link shoppingCart.Product#getSellPrice()}.
	 */
	@Test
	public void testGetSellPrice() {
		assertEquals(product.getSellPrice(), sellPrice);
	}

	/**
	 * Test method for {@link shoppingCart.Product#getInvoicePrice()}.
	 */
	@Test
	public void testGetInvoicePrice() {
		assertEquals(product.getInvoicePrice(), invoicePrice);
	}

	/**
	 * Test method for {@link shoppingCart.Product#getQuantity()}.
	 */
	@Test
	public void testGetQuantity() {
		assertTrue(product.getQuantity() == quantity);
	}

	/**
	 * Test method for {@link shoppingCart.Product#clone()}.
	 */
	@Test
	public void testClone() {
		Product p = (Product)product.clone();
		assertFalse(p == product);
		assertEquals(p, product);
		assertEquals(p.getQuantity(), 0);
	}

	/**
	 * Test method for {@link shoppingCart.Product#addListener(javax.swing.event.ChangeListener)}.
	 */
	@Test
	public void testAddListener() {
		product.addListener(new
				ChangeListener(){

					@Override
					public void stateChanged(ChangeEvent arg0) {
						changeListenerString += "anotherListener";
					}
		});
		product.increment();
		assertEquals(changeListenerString, product.getName()+"anotherListener");
	}

	/**
	 * Test method for {@link shoppingCart.Product#removeListeners()}.
	 */
	@Test
	public void testRemoveListeners() {
		product.addListener(new
				ChangeListener(){

					@Override
					public void stateChanged(ChangeEvent arg0) {
						changeListenerString += "anotherListener";
					}
		});
		product.removeListeners();
		product.increment();
		assertEquals(changeListenerString, "FAIL");
		
	}	
	
	/**
	 * Test method for {@link shoppingCart.Product#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject() {
		Product p = new Product(ID, name, description, invoicePrice, sellPrice, quantity);
		assertTrue(p.equals(product));
		p = new Product(ID, name, description, invoicePrice, sellPrice, 0);
		assertTrue(p.equals(product));
		assertTrue(p.hashCode() == product.hashCode());
		p = new Product(ID, "not an apple", description, invoicePrice, sellPrice, 0);
		assertTrue(p.equals(product));
		p = new Product(999, name, description, invoicePrice, sellPrice, 0);
		assertFalse(p.equals(product));
	}

}
