package shoppingCart;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

/** A class that manages interaction with user, receives input and display screens.
 *  @author Seth Moore and Newman Souza
 */ 
public class UI extends JFrame{
	
    /** Constructs a UI object.
     *  @precondition none
     *  @postcondition object created
     */
    public UI() {
    	super("Shopping Cart");
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    	setPreferredSize(new Dimension(400, 400));
    	setLayout(new BorderLayout());
    	
    	JPanel screenNamePanel = new JPanel();
    	screenNamePanel.setPreferredSize(new Dimension(400, 50));
    	screenNamePanel.setBorder(new EtchedBorder());
    	add(screenNamePanel, BorderLayout.PAGE_START);
    	
    	JPanel browsePanel = new JPanel();
    	browsePanel.setPreferredSize(new Dimension(300, 350));
    	browsePanel.setBorder(new EtchedBorder());
    	add(browsePanel, BorderLayout.CENTER);
    	
    	JPanel sidePanel = new JPanel();
    	sidePanel.setPreferredSize(new Dimension(100, 350));
    	sidePanel.setBorder(new EtchedBorder());
    	add(sidePanel, BorderLayout.LINE_END);
    	
    	pack();
    	setVisible(true);
    	
    	displayLoginScreen();
    }

    /** .
     *  @param 
     *  @return ?
     *  @precondition 
     *  @postcondition 
     */
    public void displayLoginScreen() {

    	// code
    	
    }

    /** .
     *  @param 
     *  @return ?
     *  @precondition 
     *  @postcondition 
     */
    public void displayProductForm() {

    	// code
    	
    }

    /** .
     *  @param 
     *  @return ?
     *  @precondition 
     *  @postcondition 
     */
    public void displayProductForm(Product product) {

    	// code
    	
    }

    /** .
     *  @param 
     *  @return ?
     *  @precondition 
     *  @postcondition 
     */
    public void displaySellerScreen() {

    	// code
    	
    }

    /** .
     *  @param 
     *  @return ?
     *  @precondition 
     *  @postcondition 
     */
    public void displaySellerScreen(Inventory inventory) {

    	this.inventory = inventory;
    	
    }

    /** .
     *  @param 
     *  @return ?
     *  @precondition 
     *  @postcondition 
     */
    public void displayCustomerScreen() {

    	// code
    	
    }

    /** .
     *  @param 
     *  @return ?
     *  @precondition 
     *  @postcondition 
     */
    public void displayCustomerScreen(Inventory inventory) {

    	this.inventory = inventory;
    	
    }

    /** .
     *  @param 
     *  @return ?
     *  @precondition 
     *  @postcondition 
     */
    public void displayCheckoutScreen() {

    	// code
    	
    }

    private Inventory inventory;
    private Cart cart;

}
