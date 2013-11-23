package shoppingCart;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
						JOptionPane.showMessageDialog(null,
								"Testing listener for\nAdd Product.");
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

    /** Assembles a line for each Product in the Inventory.
	 *  @param product		The Product to be displayed in the line
	 *  @precondition 		product is a valid reference
	 *  @postcondition  	Line assembled
	 */
    @Override
    public JPanel addLine(Product product) {
    	final JPanel line = new JPanel();
    	product.addListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				line.repaint();
			}
		});
//    	GridBagLayout grid = new GridBagLayout();
//		GridBagConstraints c = new GridBagConstraints();    	
//    	line.setLayout(grid);
        JLabel label;

        label = new JLabel(String.valueOf(product.getID()));
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.gridwidth = 1;
//        c.gridx = 0;
//        c.gridy = 0;
//        line.add(label, c);
        line.add(label);

    	label = new JLabel(product.getName());
    	label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED)); 
    	label.addMouseListener(new
    			MouseAdapter(){
    				public void mouseClicked(MouseEvent e) {
    						JOptionPane.showMessageDialog(null,
    								"Testing listener for\nProduct detail.");
    				};
    			}
    	);
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.gridwidth = 2;
//        c.gridx = 1;
//        c.gridy = 0;
//    	line.add(label, c);
        line.add(label);

    	label = new JLabel(product.getInvoicePrice().toString());
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.gridwidth = 1;
//        c.gridx = 1;
//        c.gridy = 0;
//    	line.add(label, c);
        line.add(label);

    	label = new JLabel(product.getSellPrice().toString());
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.gridwidth = 1;
//        c.gridx = 4;
//        c.gridy = 0;
//    	line.add(label, c);
        line.add(label);

    	label = new JLabel(String.valueOf(product.getQuantity()));
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.gridwidth = 1;
//        c.gridx = 5;
//        c.gridy = 0;
//    	line.add(label, c);
        line.add(label);

    	return line;
    }

}
