package Sales;

import javax.swing.JOptionPane;

/**
 *
 * @author sebs
 */
public class ProductNotFoundException extends Exception{
/*
 * This class is extends an exception, but it is not.
 * It is thrown so that it could be used for positive reasons
 */
    public ProductNotFoundException(){
        super("Product Not  found exception");           
    }
    
    public ProductNotFoundException(String s){ 
        
        super(s);
        JOptionPane.showMessageDialog(null,s);
    }
}
