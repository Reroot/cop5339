package shoppingCart;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
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
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/** A class that assembles the Seller JPanel for the UI.
 *  @author Newman Souza
 *  @author Seth Moore
 */ 
@SuppressWarnings("serial")
public class SellerScreen extends AbstractScreen {
	
    /** Constructs a SellerBrowsePanel object.
     *  @precondition 		none
     *  @postcondition 		object created
     */
    public SellerScreen(UI ui) {
    	super(ui);
    }

	@Override
    public void createHeaderPanel() {
//    	headerPanel.setPreferredSize(new Dimension(600, 30));
    	headerPanel.setBorder(new EtchedBorder());
    	headerPanel.add(new JLabel("Seller Screen"));
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
		final JLabel costsLabel = new JLabel("Costs:" + Inventory.getInstance().getCosts());
		final JLabel revenuesLabel = new JLabel("Revenues:" + Inventory.getInstance().getRevenues());
		final JLabel profitsLabel = new JLabel("Profits:" + Inventory.getInstance().getRevenues().subtract(Inventory.getInstance().getCosts()));
		sidePanel = new
				JPanel() {
					public void repaint() {
						costsLabel.setText("Costs:" + Inventory.getInstance().getCosts());
						revenuesLabel.setText("Revenues:" + Inventory.getInstance().getRevenues());
						profitsLabel.setText("Profits:" + Inventory.getInstance().getRevenues().subtract(Inventory.getInstance().getCosts()));
						super.repaint();
					}
			
				};
		sidePanel.setPreferredSize(new Dimension(200, 500));
		sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
		sidePanel.setBorder(new EtchedBorder());
		JLabel titleLabel = new JLabel("Financial Information");
		sidePanel.add(titleLabel);
		JPanel sellerFinancials = new JPanel();
		sellerFinancials.setMaximumSize(new Dimension(200, 100));
		sellerFinancials.setBorder(new EtchedBorder());
		sellerFinancials.setLayout(new GridLayout(3,1));
		sellerFinancials.add(costsLabel);
		sellerFinancials.add(revenuesLabel);
		sellerFinancials.add(profitsLabel);
		sidePanel.add(sellerFinancials);
		JButton checkoutButton = new JButton("Add Product");
		checkoutButton.addMouseListener(new
				MouseAdapter(){
					public void mouseClicked(MouseEvent e) {
			    		Product p = null;
						JOptionPane.showMessageDialog(null,
//		    					displayProductForm(p));
								"test");
					}
				}
			);
		sidePanel.add(checkoutButton);
		Inventory.getInstance().addListener(new
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
    	// TODO
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
    	label = new JLabel("Invoice Price:");
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridy = 3;
    	productForm.add(label, c);
    	label = new JLabel("Sell Price:");
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridy = 4;
    	productForm.add(label, c);
    	label = new JLabel("Quantity:");
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridy = 5;
    	productForm.add(label, c);

    	label = new JLabel(String.valueOf(product.getID()));
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.insets = new Insets(10,20,10,20);
    	c.weightx = 0.5;
    	c.gridx = 2;
    	c.gridy = 0;
    	productForm.add(label, c);
    	JTextField nameTextField = new JTextField(product.getName());
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridy = 1;
    	productForm.add(nameTextField, c);
    	JTextField descriptionTextField = new JTextField(product.getDescription());
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridy = 2;
    	productForm.add(descriptionTextField, c);
    	JTextField invoicePriceTextField = new JTextField(String.valueOf(product.getInvoicePrice()));
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridy = 3;
    	productForm.add(invoicePriceTextField, c);
    	JTextField sellPriceTextField = new JTextField(String.valueOf(product.getSellPrice()));
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridy = 4;
    	productForm.add(sellPriceTextField, c);
    	JTextField quantityTextField = new JTextField(String.valueOf(product.getQuantity()));
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridy = 5;
    	productForm.add(quantityTextField, c);
    	
    	Object[] options = {"Update", "Delete", "Cancel"};
    	int button = JOptionPane.showOptionDialog(ui, productForm, "Product Update",
    			JOptionPane.YES_NO_CANCEL_OPTION,
    			JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
    	if (button == 0) {
    		Product p = new Product(product.getID(), nameTextField.getText(), descriptionTextField.getText(), 
    				new BigDecimal(invoicePriceTextField.getText()), new BigDecimal(sellPriceTextField.getText()), Integer.parseInt(quantityTextField.getText()));
    		Inventory.getInstance().update(p);
    		ui.getCartSystem().saveInventory();
    	} else if (button == 1) {
    		Inventory.getInstance().remove(product);
    		ui.getCartSystem().saveInventory();
    		ui.displaySellerScreen();
    	}
    }

    /** .
     *  @param 
     *  @return ?
     *  @precondition 
     *  @postcondition 
     */
    public void displayProductForm() {
    	// TODO
    }

    /** Assembles a line for each Product in the Inventory.
	 *  @param product		The Product to be displayed in the line
	 *  @precondition 		product is a valid reference
	 *  @postcondition  	Line assembled
	 */
    @Override
    public JPanel addLine(final Product product) {

    	final JLabel nameLabel = new JLabel(product.getName());
        final JLabel invoicePriceLabel = new JLabel(product.getInvoicePrice().toString());
    	final JLabel sellPriceLabel = new JLabel(product.getSellPrice().toString());
        final JLabel quantityLabel = new JLabel(String.valueOf(product.getQuantity()));
    	final JPanel line = new
    			JPanel() {
    				public void repaint() {
    					nameLabel.setText(product.getName());
    					invoicePriceLabel.setText(String.valueOf(product.getInvoicePrice()));
    					sellPriceLabel.setText(String.valueOf(product.getSellPrice()));
    					quantityLabel.setText(String.valueOf(product.getQuantity()));
    					super.repaint();
    				}
    			};
    	product.addListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				line.repaint();
			}
		});
    	

//    	GridBagLayout grid = new GridBagLayout();
//		GridBagConstraints c = new GridBagConstraints();    	
//    	line.setLayout(grid);
        JLabel idLabel = new JLabel(String.valueOf(product.getID()));
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.gridwidth = 1;
//        c.gridx = 0;
//        c.gridy = 0;
//        line.add(label, c);
        line.add(idLabel);

        nameLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED)); 
        nameLabel.addMouseListener(new
    			MouseAdapter(){
    				public void mouseClicked(MouseEvent e) {
    					displayProductForm(product);
    				};
    			}
    	);
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.gridwidth = 2;
//        c.gridx = 1;
//        c.gridy = 0;
//    	line.add(label, c);
        line.add(nameLabel);

//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.gridwidth = 1;
//        c.gridx = 1;
//        c.gridy = 0;
//    	line.add(label, c);
        line.add(invoicePriceLabel);

//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.gridwidth = 1;
//        c.gridx = 4;
//        c.gridy = 0;
//    	line.add(label, c);
        line.add(sellPriceLabel);

//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.gridwidth = 1;
//        c.gridx = 5;
//        c.gridy = 0;
//    	line.add(label, c);
        line.add(quantityLabel);

    	return line;
    }

}
