package shoppingCart.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import shoppingCart.model.Cart;
import shoppingCart.model.Inventory;
import shoppingCart.model.Product;

/**
 * A JPanel containing all the panels, buttons, listeners, etc. for
 * the customer's Checkout Screen.
 * 
 * ConcreteClass in Template Method Pattern
 * 
 *  @author Newman Souza
 *  @author Seth Moore
 */ 
@SuppressWarnings("serial")
public class CheckoutScreen extends AbstractScreen {
	
 	/**
	 * Constructs a CheckoutScreen object.
	 * 
	 * @param ui a reference to the UI that created this screen.
	 */
    public CheckoutScreen(UI ui) {
    	super(ui);
    }

    /**
     * Creates the header panel for the CheckoutScreen.
     */
	public void createHeaderPanel() {
    	headerPanel.setBorder(new EtchedBorder());
		headerPanel.setLayout(new GridBagLayout());
    	GridBagConstraints headerC = new GridBagConstraints();
		headerC.anchor = GridBagConstraints.LINE_END;
		headerC.weightx = 0.5;
		headerC.gridx = 0;
    	JLabel label = new JLabel("Checkout Screen");
		label.setFont(label.getFont().deriveFont(16.0f));
    	headerPanel.add(label, headerC);
		JButton logoutButton = new JButton("Logout");
    	logoutButton.addActionListener(new
    			ActionListener() {
    				public void actionPerformed(ActionEvent e) {
    					Cart.getInstance().clear();
    					ui.displayLoginScreen();
    				}
    			}
    		);
		headerC.gridx = 1;
		headerC.insets = new Insets(3,0,4,4);
		headerC.anchor = GridBagConstraints.LINE_END;
    	headerPanel.add(logoutButton, headerC);
    	this.add(headerPanel, BorderLayout.NORTH);
    }
    
	/**
	 * Creates the side panel for the CheckoutScreen,
	 * which includes a cart summary and payment form.
	 */
	@Override
	public void createSidePanel() {
		final JLabel itemsLabel = new JLabel("Items: " + Cart.getInstance().getQuantity());
		final JLabel totalLabel = new JLabel("Total: $" + Cart.getInstance().getTotal());
		sidePanel = new
				JPanel() {
					public void repaint() {
						itemsLabel.setText("Items: " + Cart.getInstance().getQuantity());
						totalLabel.setText("Total: $" + Cart.getInstance().getTotal());
						super.repaint();
					}
			
				};
		sidePanel.setPreferredSize(new Dimension(200, 500));
		sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
		sidePanel.setBorder(new EtchedBorder());
		JLabel titleLabel = new JLabel("Cart Summary");
		sidePanel.add(titleLabel);
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
		paymentForm.setLayout(new GridLayout(5,2));
		JLabel cardTypeLabel = new JLabel("Type:");
		JTextField cardTypeTextField = new JTextField("");
		JLabel cardholderLabel = new JLabel("Cardholder Name:");
		JTextField cardHolderTextField = new JTextField("");
		JLabel cardNumberLabel = new JLabel("Card Number:");
		final JTextField cardNumberTextField = new JTextField("");
		JLabel expirationLabel = new JLabel("Expiration Date:");
		JTextField expirationTextField = new JTextField("");
		JLabel codeLabel = new JLabel("Security Code:");
		JTextField codeTextField = new JTextField("");
		paymentForm.add(cardTypeLabel);
		paymentForm.add(cardTypeTextField);
		paymentForm.add(cardholderLabel);
		paymentForm.add(cardHolderTextField);
		paymentForm.add(cardNumberLabel);
		paymentForm.add(cardNumberTextField);
		paymentForm.add(expirationLabel);
		paymentForm.add(expirationTextField);
		paymentForm.add(codeLabel);
		paymentForm.add(codeTextField);
		sidePanel.add(paymentForm);
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new
    			ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ui.displayCustomerScreen();
					}
				}
			);
		sidePanel.add(cancelButton);
		JButton checkoutButton = new JButton("Pay");
		checkoutButton.addActionListener(new
    			ActionListener() {
					public void actionPerformed(ActionEvent e) {
						boolean result = ui.getCartSystem().pay(cardNumberTextField.getText(), Cart.getInstance().getTotal());
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
		Cart.getInstance().addListener(new
				ChangeListener() {
					@Override
					public void stateChanged(ChangeEvent arg0) {
						sidePanel.repaint();
					}
					
		});
		this.add(sidePanel, BorderLayout.EAST);
	}

   /**
    * Displays a Product's details.
    * 
    * @param product the Product whose details are to be displayed.
    */
    public void displayProductForm(Product product) {
    	JPanel productForm = new JPanel();
    	productForm.setLayout(new GridBagLayout());
    	GridBagConstraints c = new GridBagConstraints();
    	c.fill = GridBagConstraints.HORIZONTAL;

    	JLabel label;
    	label = new JLabel("ID:");
    	c.insets = new Insets(10,20,10,0);
    	c.weightx = 0.5;
    	c.gridx = 0;
    	c.gridy = 0;
    	productForm.add(label, c);
    	label = new JLabel("Name:");
    	c.gridy = 1;
    	productForm.add(label, c);
    	label = new JLabel("Description: ");
    	c.gridy = 2;
    	productForm.add(label, c);
    	label = new JLabel("Price:");
    	c.gridy = 3;
    	productForm.add(label, c);
    	label = new JLabel("Quantity:");
    	c.gridy = 4;
    	productForm.add(label, c);

    	label = new JLabel(String.valueOf(product.getID()));
    	c.insets = new Insets(10,20,10,20);
    	c.weightx = 0.5;
    	c.gridx = 2;
    	c.gridy = 0;
    	productForm.add(label, c);
    	label = new JLabel(product.getName());
    	c.gridy = 1;
    	productForm.add(label, c);
    	label = new JLabel(product.getDescription());
    	c.gridy = 2;
    	productForm.add(label, c);
    	label = new JLabel(String.valueOf(NumberFormat.getCurrencyInstance().format(product.getSellPrice())));
    	c.gridy = 3;
    	productForm.add(label, c);
    	label = new JLabel(String.valueOf(product.getQuantity()));
    	c.gridy = 4;
    	productForm.add(label, c);
    	JOptionPane.showMessageDialog(ui, productForm, "Product Detail", JOptionPane.INFORMATION_MESSAGE);
    }
	
    /** 
     * Assembles and returns a JPanel for the supplied Product,
     * with product summary and increment/decrement buttons.
     *  
	 *  @param product		The Product represented by the JPanel.
	 *  @return 			a JPanel filled with appropriate labels, buttons, etc.
	 *  @precondition 		product is a valid reference
	 */
    @Override
    public JPanel addLine(final Product product) {
    	GridBagLayout grid = new GridBagLayout();
    	GridBagConstraints c = new GridBagConstraints();
    	if (browsePanel.getComponentCount() == 0) {
    		JPanel titleBar = new JPanel();
    		titleBar.add(new JLabel("Name"));
    		titleBar.add(new JLabel("Price"));
    		titleBar.add(new JLabel("Quantity in cart"));
    		titleBar.setLayout(grid);
    		browsePanel.add(titleBar);
    	}
    	final JButton incrementButton = new JButton("Increment");
    	final JButton decrementButton = new JButton("Decrement");
    	final JLabel quantityLabel = new JLabel(String.valueOf(product.getQuantity()));
    	final JPanel line = new
    			JPanel() {
    				public void repaint() {
    					quantityLabel.setText(String.valueOf(product.getQuantity()));
    					if (product.getQuantity() < 1) {
    						decrementButton.setEnabled(false);
    					}
    					else {
    						decrementButton.setEnabled(true);
    					}
    					if (Inventory.getInstance().getMatchingProduct(product).getQuantity() < 1) {
    						incrementButton.setEnabled(false);
    					}
    					else {
    						incrementButton.setEnabled(true);
    					}
    					super.repaint();
    				}
    			};
    	product.addListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				line.repaint();
			}
		});
    	JLabel label;
    	
    	label = new JLabel(product.getName());
    	label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED)); 
    	label.addMouseListener(new
    			MouseAdapter(){
    				public void mouseClicked(MouseEvent e) {
    					displayProductForm(product);
    				};
    			}
    	);
    	line.add(label);

    	label = new JLabel("$" + product.getSellPrice().toString());
    	line.add(label);

    	
    	line.add(quantityLabel);

    	incrementButton.addActionListener(new
    			ActionListener(){
    				public void actionPerformed(ActionEvent arg0) {
   						Inventory.getInstance().decrement(product);
   						Cart.getInstance().increment(product);
    				};
    			}
    	);

    	line.add(incrementButton);
    	
    	decrementButton.addActionListener(new
    			ActionListener(){
    				public void actionPerformed(ActionEvent arg0) {
						Inventory.getInstance().increment(product);
						Cart.getInstance().decrement(product);
    				};
    			}
    	);
    	line.add(decrementButton);
    	return line;
    }

}