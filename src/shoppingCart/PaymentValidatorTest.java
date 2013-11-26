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
public class PaymentValidatorTest {

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
	 * Test method for {@link shoppingCart.PaymentValidator#validate(java.lang.String, java.math.BigDecimal)}.
	 */
	@Test
	public void testValidate() {
		PaymentValidator pv = new PaymentValidator();
		assertTrue(pv.validate("anything", new BigDecimal("500.00")));
	}

}
