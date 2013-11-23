package shoppingCart;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.sound.sampled.Line;
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
		checkoutButton.addMouseListener(new
				MouseAdapter(){
					public void mouseClicked(MouseEvent e){
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
    	productForm.setLayout(new GridLayout(8,2));
    	
    	productForm.add(new JLabel("ID:"));
    	productForm.add(new JLabel(String.valueOf(product.getID())));
    	productForm.add(new JLabel("Name:"));
    	productForm.add(new JLabel(product.getName()));
    	productForm.add(new JLabel("Description:"));
    	productForm.add(new JLabel(product.getDescription()));
    	productForm.add(new JLabel("Invoice Price:"));
    	productForm.add(new JLabel(String.valueOf(product.getInvoicePrice())));
    	productForm.add(new JLabel("Sell Price:"));
    	productForm.add(new JLabel(String.valueOf(product.getSellPrice())));
    	productForm.add(new JLabel("Quantity:"));
    	productForm.add(new JLabel(String.valueOf(product.getQuantity())));
    	JOptionPane.showMessageDialog(ui, productForm);
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
    	line.setLayout(new BoxLayout(line, BoxLayout.LINE_AXIS));
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

    	label = new JLabel(product.getSellPrice().toString());
    	line.add(label);

    	label = new JLabel(String.valueOf(product.getQuantity()));
    	line.add(label);

    	JButton addButton = new JButton("Add to Cart");
    	addButton.addMouseListener(new
    			MouseAdapter(){
    				public void mouseClicked(MouseEvent e) {
						Inventory.getInstance().decrement(product);
						Cart.getInstance().increment(product);
    				};
    			}
    	);
    	line.add(addButton);
    	return line;
    }

}
