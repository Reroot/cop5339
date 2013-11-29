package shoppingCart.system;

import java.math.BigDecimal;

/** A class that validates payments for the Shopping Cart application.
 * @author Newman Souza
 * @author Seth Moore
 */ 
public class PaymentValidator {

    /** Validates a payment.
     *  @param cardNumber 	the customer's credit card number.
     *  @param total 		the monetary amount being validated
     *  @return true 		if the payment is approved and false otherwise
     *  @precondition 		none
     *  @postcondition 		payment validated
     */
    public boolean validate(String cardNumber, BigDecimal total) {
    	return true;
    }

}
