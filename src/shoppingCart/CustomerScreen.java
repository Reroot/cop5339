package shoppingCart;


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

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/** A class that assembles the Customer JPanel for the UI.
 *  @author Newman Souza
 *  @author Seth Moore
 */ 
@SuppressWarnings("serial")
public class CustomerScreen extends AbstractScreen {
	
    /** Constructs a CustomerBrowsePanel object.
     *  @precondition 		none
     *  @postcondition 		object created
     */
    public CustomerScreen(UI ui) {
    	super(ui);
    }

	@Override
    public void createHeaderPanel() {
//    	headerPanel.setPreferredSize(new Dimension(600, 30));
    	headerPanel.setBorder(new EtchedBorder());
    	headerPanel.add(new JLabel("Customer Screen"));
		JButton logoutButton = new JButton("Logout");
    	logoutButton.addActionListener(new
    			ActionListener() {
    				public void actionPerformed(ActionEvent e) {
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
		JLabel titleLabel = new JLabel("Cart Summary");
		final JLabel itemsLabel = new JLabel("Items:" + Cart.getInstance().getQuantity());
		final JLabel totalLabel = new JLabel("Total:" + Cart.getInstance().getTotal());
		sidePanel = new
				JPanel() {
					public void repaint() {
						itemsLabel.setText("Items:" + Cart.getInstance().getQuantity());
						totalLabel.setText("Total:" + Cart.getInstance().getTotal());
						super.repaint();
					}
			
				};
		sidePanel.setPreferredSize(new Dimension(200, 500));
		sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
		sidePanel.setBorder(new EtchedBorder());
		sidePanel.add(titleLabel);
		JPanel cartSummary = new JPanel();
		cartSummary.setMaximumSize(new Dimension(250, 100));
		cartSummary.setBorder(new EtchedBorder());
		cartSummary.setLayout(new GridLayout(3,1));
		cartSummary.add(itemsLabel);
		cartSummary.add(totalLabel);
		sidePanel.add(cartSummary);
		JButton checkoutButton = new JButton("Checkout");
		checkoutButton.addActionListener(new
    			ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ui.displayCheckoutScreen();
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

    /** .
     *  @param 
     *  @return ?
     *  @precondition 
     *  @postcondition 
     */
    public void displayProductForm(Product product) {
    	JPanel productForm = new JPanel();
    	productForm.setLayout(new GridBagLayout());
    	GridBagConstraints c = new GridBagConstraints();
    	c.fill = GridBagConstraints.HORIZONTAL;

    	JLabel label;
    	label = new JLabel("ID:");
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.insets = new Insets(10,20,10,0);
    	c.weightx = 0.5;
    	c.gridx = 0;
    	c.gridy = 0;
    	productForm.add(label, c);
    	label = new JLabel("Name:");
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridy = 1;
    	productForm.add(label, c);
    	label = new JLabel("Description: ");
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridy = 2;
    	productForm.add(label, c);
    	label = new JLabel("Price:");
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridy = 3;
    	productForm.add(label, c);
    	label = new JLabel("Quantity:");
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridy = 4;
    	productForm.add(label, c);

    	label = new JLabel(String.valueOf(product.getID()));
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.insets = new Insets(10,20,10,20);
    	c.weightx = 0.5;
    	c.gridx = 2;
    	c.gridy = 0;
    	productForm.add(label, c);
    	label = new JLabel(product.getName());
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridy = 1;
    	productForm.add(label, c);
    	label = new JLabel(product.getDescription());
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridy = 2;
    	productForm.add(label, c);
    	label = new JLabel(String.valueOf(product.getSellPrice()));
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridy = 3;
    	productForm.add(label, c);
    	label = new JLabel(String.valueOf(product.getQuantity()));
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridy = 4;
    	productForm.add(label, c);
    	JOptionPane.showMessageDialog(ui, productForm, "Product Detail", JOptionPane.INFORMATION_MESSAGE);
    }

    /** Assembles a line for each Product in the Inventory.
	 *  @param product		The Product to be displayed in the line
	 *  @precondition 		product is a valid reference
	 *  @postcondition  	Line assembled
	 */
    @Override
    public JPanel addLine(final Product product) {
    	GridBagLayout grid = new GridBagLayout();
    	GridBagConstraints c = new GridBagConstraints();
    	if (browsePanel.getComponentCount() == 0) {
    		JPanel titleBar = new JPanel();
    		titleBar.add(new JLabel("test"));
    		titleBar.setLayout(grid);
    		browsePanel.add(titleBar);
    	}
    	final JButton addButton = new JButton("Add to Cart");
    	final JLabel quantityLabel = new JLabel(String.valueOf(product.getQuantity()));
    	final JPanel line = new
    			JPanel() {
    				public void repaint() {
    					quantityLabel.setText(String.valueOf(product.getQuantity()));
    					if (product.getQuantity() < 1) {
    						addButton.setEnabled(false);
    					}
    					super.repaint();
    				}
    			};
    	line.setLayout(grid);
//   	c.fill = GridBagConstraints.HORIZONTAL;
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
//    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.insets = new Insets(10,10,10,10);
    	c.weightx = 0.5;
    	c.gridx = 0;
//    	c.gridy = 0;
    	line.add(label, c);

    	label = new JLabel(product.getSellPrice().toString());
//    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.insets = new Insets(10,10,10,10);
    	c.weightx = 0.5;
    	c.gridx = 1;
    	line.add(label, c);

//    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.insets = new Insets(10,10,10,10);
    	c.weightx = 0.5;
    	c.gridx = 2;
    	line.add(quantityLabel, c);

    	
    	addButton.addActionListener(new
    			ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0) {
						Inventory.getInstance().decrement(product);
						Cart.getInstance().increment(product);
						
					};
    			}
    	);
//    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.insets = new Insets(10,10,10,10);
    	c.weightx = 0.5;
    	c.gridx = 3;
    	line.add(addButton, c);
    	product.addListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				line.repaint();
			}
		});
    	return line;
    }

}
