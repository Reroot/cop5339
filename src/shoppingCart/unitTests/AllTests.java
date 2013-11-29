package shoppingCart.unitTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CartSystemTest.class, CartTest.class, DBManagerTest.class,
		InventoryTest.class, PaymentValidatorTest.class, ProductTest.class,
		PrunningIteratorTest.class, UserListTest.class, UserTest.class })
public class AllTests {

}
