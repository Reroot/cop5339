package shoppingCart;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/** A class that assembles the Checkout JPanel for the UI.
 *  @author Newman Souza
 *  @author Seth Moore
 */ 
@SuppressWarnings("serial")
public class CheckoutScreen extends AbstractScreen {
	
    /** Constructs a CheckoutBrowsePanel object.
     *  @precondition 		none
     *  @postcondition 		object created
     */
    public CheckoutScreen(UI ui) {
    	super(ui);
    }

	public void createHeaderPanel() {
//    	headerPanel.setPreferredSize(new Dimension(600, 30));
    	headerPanel.setBorder(new EtchedBorder());
    	headerPanel.add(new JLabel("Checkout Screen"));
		JButton logoutButton = new JButton("Logout");
    	logoutButton.addMouseListener(new
    			MouseAdapter(){
    				public void mouseClicked(MouseEvent e){
    					Cart.getInstance().clear();
    					ui.displayLoginScreen();
    				}
    			}
    		);
    	headerPanel.add(logoutButton);
    	this.add(headerPanel, BorderLayout.NORTH);
    }
    
	@Override
	public void createSidePanel() {
		sidePanel.setPreferredSize(new Dimension(200, 500));
		sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
		sidePanel.setBorder(new EtchedBorder());
		JLabel titleLabel = new JLabel("Cart Summary");
		sidePanel.add(titleLabel);
		JLabel itemsLabel = new JLabel("Items:" + Cart.getInstance().getQuantity());
		JLabel totalLabel = new JLabel("Total:" + Cart.getInstance().getTotal());
		JPanel cartSummary = new JPanel();
		cartSummary.setMaximumSize(new Dimension(200, 100));
		cartSummary.setBorder(new EtchedBorder());
		cartSummary.setLayout(new GridLayout(3,1));
		cartSummary.add(itemsLabel);
		cartSummary.add(totalLabel);
		sidePanel.add(cartSummary);
		JLabel formLabel = new JLabel("Payment Information");
		sidePanel.add(formLabel);
		JPanel paymentForm = new JPanel();
		paymentForm.setMaximumSize(new Dimension(200, 100));
		paymentForm.setBorder(new EtchedBorder());
		paymentForm.setLayout(new GridLayout(5,1));
		JLabel cardTypeLabel = new JLabel("Type:");
		JLabel cardholderLabel = new JLabel("Cardholder Name:");
		final JLabel cardNumberLabel = new JLabel("Card Number:");
		JLabel expirationLabel = new JLabel("Expiration Date:");
		JLabel codeLabel = new JLabel("Security Code:");
		paymentForm.add(cardTypeLabel);
		paymentForm.add(cardholderLabel);
		paymentForm.add(cardNumberLabel);
		paymentForm.add(expirationLabel);
		paymentForm.add(codeLabel);
		sidePanel.add(paymentForm);
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addMouseListener(new
				MouseAdapter(){
					public void mouseClicked(MouseEvent e){
						ui.displayCustomerScreen();
					}
				}
			);
		sidePanel.add(cancelButton);
		JButton checkoutButton = new JButton("Pay");
		checkoutButton.addMouseListener(new
				MouseAdapter(){
					public void mouseClicked(MouseEvent e){
						boolean result = ui.getCartSystem().pay(cardNumberLabel.toString(), Cart.getInstance().getTotal());
						if (result) {
    						JOptionPane.showMessageDialog(null,
    								"Payment successful.");
        					Cart.getInstance().clear();
        					ui.displayCustomerScreen();
						} else {
							JOptionPane.showMessageDialog(null,
									"Payment failed.");
						}
					}
				}
			);
		sidePanel.add(checkoutButton);
		this.add(sidePanel, BorderLayout.EAST);
	}

    /** Assembles a line for each Product in the Inventory.
	 *  @param product		The Product to be displayed in the line
	 *  @precondition 		product is a valid reference
	 *  @postcondition  	Line assembled
	 */
    @Override
    public JPanel addLine(final Product product) {
    	final JPanel line = new JPanel();
    	product.addListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				line.repaint();
			}
		});
    	JLabel label;
    	
    	label = new JLabel(product.getName());
    	line.add(label);

    	label = new JLabel(product.getSellPrice().toString());
    	line.add(label);

    	label = new JLabel(String.valueOf(product.getQuantity()));
    	line.add(label);

    	JButton incrementButton = new JButton("Increment");
    	incrementButton.addMouseListener(new
    			MouseAdapter(){
    				public void mouseClicked(MouseEvent e) {
   						Inventory.getInstance().decrement(product);
   						Cart.getInstance().increment(product);
    				};
    			}
    	);

    	line.add(incrementButton);
    	
    	JButton decrementButton = new JButton("Decrement");
    	decrementButton.addMouseListener(new
    			MouseAdapter(){
    				public void mouseClicked(MouseEvent e) {
						Inventory.getInstance().increment(product);
						Cart.getInstance().decrement(product);
    				};
    			}
    	);
    	line.add(decrementButton);
    	return line;
    }

}
