package shoppingCart;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

/** A class that manages interaction with user, receives input and display screens.
 *  @author Seth Moore and Newman Souza
 */ 
@SuppressWarnings("serial")
public class UI extends JFrame{
	
	final static String LOGINPANEL = "LoginScreen";
	final static String CUSTOMERPANEL = "CustomerScreen";
	final static String SELLERPANEL = "SellerScreen";
	final static String CHECKOUTPANEL = "CheckoutScreen";
	
    /** Constructs a UI object.
     *  @precondition none
     *  @postcondition object created
     */
    public UI(CartSystem cartSystem) {
    	super("Shopping Cart");
    	inventory = Inventory.getInstance();
    	this.cartSystem = cartSystem;
    	cart = Cart.getInstance();
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setPreferredSize(new Dimension(800, 600));
    	screenCards = new JPanel(new CardLayout());
    	
    	//setLayout(new BorderLayout());
    	
    	JPanel loginScreen = createLoginScreen();
    	screenCards.add(loginScreen, LOGINPANEL);

    	customerScreen = new CustomerScreen(this);
    	customerScreen.createScreen();
    	screenCards.add(customerScreen, CUSTOMERPANEL);
    	
    	sellerScreen = new SellerScreen(this);
    	sellerScreen.createScreen();
    	screenCards.add(sellerScreen, SELLERPANEL);
    	
    	checkoutScreen = new CheckoutScreen(this);
    	checkoutScreen.createScreen();
    	screenCards.add(checkoutScreen, CHECKOUTPANEL);
    	
    	add(screenCards);
    	   	
    	pack();
    	setLocationRelativeTo(null);
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
    	((CardLayout)screenCards.getLayout()).show(screenCards, LOGINPANEL);
    }

    private JPanel createLoginScreen() {
		JPanel loginScreen = new JPanel();
		loginScreen.setLayout(new BorderLayout());
		JPanel topPanel = new JPanel();
		topPanel.add(new JLabel("Login Screen"));
		topPanel.setBorder(new EtchedBorder());
		loginScreen.add(topPanel, BorderLayout.PAGE_START);
		
		JPanel centerPanel = new JPanel();
		loginScreen.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		
		final JTextField usernameField = new JTextField(20);
		usernameField.setMaximumSize( usernameField.getPreferredSize() );
		final JTextField passwordField = new JTextField(20);
		passwordField.setMaximumSize( passwordField.getPreferredSize() );
    	JButton loginButton = new JButton("Login");
    	loginButton.addMouseListener(new
    			MouseAdapter(){
    				public void mouseClicked(MouseEvent e) {
    					String userType = cartSystem.login(usernameField.getText(), passwordField.getText());
    					if (userType == null){
    						JOptionPane.showMessageDialog(screenCards, "Invalid username/password pair.\nPlease try again.");
    						usernameField.setText("");
    						passwordField.setText("");
    					}
    					else if (userType.equals("Customer")){
    				    	cartSystem.saveInventory();
    				    	
    						cart.clear();
    						displayCustomerScreen();
    					}
    					else if (userType.equals("Seller")){
    						displaySellerScreen();
    					}
    					
    				};
    			}
   		);
    	JButton newmanButton = new JButton("Newman");
    	newmanButton.addMouseListener(new
    			MouseAdapter() {
    				public void mouseClicked(MouseEvent e) {
    					String userType = cartSystem.login("Newman", "newman");
    					displaySellerScreen();
    				};
    			}
    	);
    	JButton sethButton = new JButton("Seth");
       	sethButton.addMouseListener(new
    			MouseAdapter() {
    				public void mouseClicked(MouseEvent e) {
    					String userType = cartSystem.login("Seth", "seth");
    					displayCustomerScreen();
    				};
    			}
    	);
    	centerPanel.add(usernameField);
    	centerPanel.add(passwordField);
    	centerPanel.add(loginButton);
    	centerPanel.add(newmanButton);
    	centerPanel.add(sethButton);
		return loginScreen;
	}

    /** .
     *  @param 
     *  @return ?
     *  @precondition 
     *  @postcondition 
     */
    public void displaySellerScreen() {
    	Iterator<Product> iter = inventory.iterator();
    	sellerScreen.populate(iter);
    	((CardLayout)(screenCards.getLayout())).show(screenCards, SELLERPANEL);
    }

    /** .
     *  @param 
     *  @return ?
     *  @precondition 
     *  @postcondition 
     */
    public void displayCustomerScreen() {
    	PrunningIterator pIter = new PrunningIterator(inventory.iterator());
    	customerScreen.populate(pIter);
    	((CardLayout)(screenCards.getLayout())).show(screenCards, CUSTOMERPANEL);
    }

    /** .
     *  @param 
     *  @return ?
     *  @precondition 
     *  @postcondition 
     */
    public void displayCheckoutScreen() {
    	PrunningIterator pIter = new PrunningIterator(cart.iterator());
    	if (!pIter.hasNext()) {
			JOptionPane.showMessageDialog(screenCards, "Cart is empty.");
    	} else {
        	checkoutScreen.populate(pIter);
        	((CardLayout)(screenCards.getLayout())).show(screenCards, CHECKOUTPANEL);
    	}
    }
    
	public CartSystem getCartSystem() {
		return cartSystem;
	}

    private CartSystem cartSystem;
    private Inventory inventory;
    private Cart cart;
    private AbstractScreen customerScreen;
    private AbstractScreen sellerScreen;
    private AbstractScreen checkoutScreen;
    JPanel screenCards;

}
