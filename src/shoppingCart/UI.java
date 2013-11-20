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
public class UI extends JFrame{
	
	final static String LOGINPANEL = "LoginScreen";
	final static String CUSTOMERPANEL = "CustomerScreen";
	final static String SELLERPANEL = "SellerScreen";
	final static String CARTPANEL = "CartScreen";
	
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
//    	setPreferredSize(new Dimension(400, 400));
    	screenCards = new JPanel(new CardLayout());
    	
    	//setLayout(new BorderLayout());
    	
    	JPanel loginScreen = createLoginScreen();
    	screenCards.add(loginScreen, LOGINPANEL);
    	
    	JPanel customerScreen = createCustomerScreen();
    	screenCards.add(customerScreen, CUSTOMERPANEL);
    	
    	JPanel sellerScreen = createSellerScreen();
    	screenCards.add(sellerScreen, SELLERPANEL);
    	
    	JPanel cartScreen = createCartScreen();
    	screenCards.add(cartScreen, CARTPANEL);
    	
    	add(screenCards, BorderLayout.CENTER);
    	
    	
    	
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

    	((CardLayout)screenCards.getLayout()).show(screenCards, LOGINPANEL);
    	
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
    	Iterator<Product> iter = inventory.iterator();
//    	customerBrowsePanel.populate(iter);
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
//    	customerBrowsePanel.populate(pIter);
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
//    	cartBrowsePanel.populate(pIter);
    	((CardLayout)(screenCards.getLayout())).show(screenCards, CARTPANEL);
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
    			    	//String userType = cartSystem.login("Newman", "newman");
    					//String userType = "Customer";
    					if (userType == null){
    						JOptionPane.showMessageDialog(screenCards, "Invalid username/password pair.\nPlease try again.");
    						usernameField.setText("");
    						passwordField.setText("");
    					}
    					else if (userType.equals("Customer")){
    						cart.clear();
    						displayCustomerScreen();
    					}
    					else if (userType.equals("Seller")){
    						displaySellerScreen();
    					}
    					
    				};
    			}
    		);
    	centerPanel.add(usernameField);
    	centerPanel.add(passwordField);
    	centerPanel.add(loginButton);
		return loginScreen;
	}
    
	private JPanel createSellerScreen() {
		JPanel customerScreen = new JPanel();
    	customerScreen.setLayout(new BorderLayout());
    	
    	JPanel screenNamePanel = new JPanel();
    	screenNamePanel.setPreferredSize(new Dimension(400, 50));
    	screenNamePanel.setBorder(new EtchedBorder());
    	customerScreen.add(screenNamePanel, BorderLayout.PAGE_START);
    	screenNamePanel.add(new JLabel("Seller Screen"));
    	
    	JPanel browsePanel = new JPanel();
    	browsePanel.setPreferredSize(new Dimension(300, 350));
    	browsePanel.setBorder(new EtchedBorder());
    	customerScreen.add(browsePanel, BorderLayout.CENTER);
    	
    	JPanel sidePanel = new JPanel();
    	sidePanel.setPreferredSize(new Dimension(100, 350));
    	sidePanel.setBorder(new EtchedBorder());
    	customerScreen.add(sidePanel, BorderLayout.LINE_END);
		return customerScreen;
	}
	
	private JPanel createCustomerScreen() {
		JPanel customerScreen = new JPanel();
    	customerScreen.setLayout(new BorderLayout());
    	
    	JPanel screenNamePanel = new JPanel();
    	screenNamePanel.setPreferredSize(new Dimension(400, 50));
    	screenNamePanel.setBorder(new EtchedBorder());
    	customerScreen.add(screenNamePanel, BorderLayout.PAGE_START);
    	screenNamePanel.add(new JLabel("Customer Screen"));
    	
    	JPanel browsePanel = new JPanel();
    	browsePanel.setPreferredSize(new Dimension(300, 350));
    	browsePanel.setBorder(new EtchedBorder());
    	customerScreen.add(browsePanel, BorderLayout.CENTER);
    	
    	JPanel sidePanel = new JPanel();
    	sidePanel.setPreferredSize(new Dimension(100, 350));
    	sidePanel.setBorder(new EtchedBorder());
    	customerScreen.add(sidePanel, BorderLayout.LINE_END);
    	JButton checkoutButton = new JButton("Checkout");
    	checkoutButton.addMouseListener(new
    			MouseAdapter(){
    				public void mouseClicked(MouseEvent e){
    					displayCheckoutScreen();
    				}
    			}
    		);
    	sidePanel.add(checkoutButton);
		return customerScreen;
	}
	
	private JPanel createCartScreen() {
		JPanel customerScreen = new JPanel();
    	customerScreen.setLayout(new BorderLayout());
    	
    	JPanel screenNamePanel = new JPanel();
    	screenNamePanel.setPreferredSize(new Dimension(400, 50));
    	screenNamePanel.setBorder(new EtchedBorder());
    	customerScreen.add(screenNamePanel, BorderLayout.PAGE_START);
    	screenNamePanel.add(new JLabel("Checkout Screen"));
    	
    	JPanel browsePanel = new JPanel();
    	browsePanel.setPreferredSize(new Dimension(300, 350));
    	browsePanel.setBorder(new EtchedBorder());
    	customerScreen.add(browsePanel, BorderLayout.CENTER);
    	
    	JPanel sidePanel = new JPanel();
    	sidePanel.setPreferredSize(new Dimension(100, 350));
    	sidePanel.setBorder(new EtchedBorder());
    	customerScreen.add(sidePanel, BorderLayout.LINE_END);
		return customerScreen;
	}

	
    private CartSystem cartSystem;
    private Inventory inventory;
    private Cart cart;
//  private AbstractBrowsePanel customerBrowsePanel;
//  private AbstractBrowsePanel sellerBrowsePanel;
//  private AbstractBrowsePanel cartBrowsePanel;
    JPanel screenCards;

}
