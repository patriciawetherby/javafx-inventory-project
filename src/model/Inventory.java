package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The Inventory class represents a list of all parts/products.
 * This class contains several methods to access the private Observable List.
 * @author Patricia
 */
public class Inventory 
{
    /**
     * @param allParts An Observable List of all the parts in Inventory
     * @param allProducts An Observable List of all the parts in Inventory
     * @param partIdCount The starting number for auto-generated IDs for parts
     * @param productIdCount The starting number for auto-generated IDs for products
     */
    
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    
    private static int partIdCount = 1000;
    private static int productIdCount = 2000;
    
    /**
     *  The addPart method adds a new part to the allParts list.
     *  @param newPart New part to add to the allParts list
     */
    public static void addPart(Part newPart)
    {
        allParts.add(newPart); 
    }
    
    /**
     *  The addProduct method adds a new product to the allProducts list.
     *  @param newProduct New product to add to the allProducts list
     */
    public static void addProduct (Product newProduct)
    {
        allProducts.add(newProduct);
    }
    
    /**
     *  The lookupPart method searches through the allParts list by number.
     *  @param partId Part ID for search function
     *  @return null
     */
    public static Part lookupPart (int partId) //by number
    {
        ObservableList<Part> allParts = Inventory.getAllParts();
        
        for(Part p : allParts)
        {
            if(p.getId() == partId)
            {
                return p;
            }
        }
        return null;
    }
    
    /**
     *  This lookupProduct method searches through the allProducts list by number.
     *  @param productId Product ID for search function
     *  @return null
     */
    public static Product lookupProduct (int productId) //by number
    {
        ObservableList<Product> allProducts = Inventory.getAllProducts();
        
        for(Product pr : allProducts)
        {
            if(pr.getId() == productId)
            {
                return pr;
            }
        }
        return null;
    }
    
    /**
     *  This overloaded lookupPart method searches through the allParts list by name.
     *  @param partName Part name for search function
     *  @return searchedPart The entered name if found
     */
    public static ObservableList<Part> lookupPart (String partName) //by name
    {
        ObservableList<Part> searchedPart = FXCollections.observableArrayList();
        for(Part p : allParts)
        {
            if(p.getName().contains(partName))
            {
                searchedPart.add(p);
            }
        }
        return searchedPart;
    }
    
    /**
     *  This overloaded lookupProduct method searches through the allProducts list by name.
     *  @param productName Product name for search function
     *  @return searchedProduct The entered name if found
     */
    public static ObservableList<Product> lookupProduct (String productName) //by name
    {
        ObservableList<Product> searchedProduct = FXCollections.observableArrayList();
        for(Product pr : allProducts)
        {
            if(pr.getName().contains(productName))
            {
                searchedProduct.add(pr);
            }
        }
        return searchedProduct;
    }
    
    /**
     * The updatePart method updates the part with new values from the Modify screen.
     * @param selectedPart Highlighted part from Parts Table to modify
     * @param index The index of the selected part
     */
    public static void updatePart (int index, Part selectedPart)
    {
        allParts.set(index, selectedPart);
    }
    
    /**
     *  The updateProduct method updates the product with new values from the Modify screen.
     *  @param newProduct Product with new associated parts
     *  @param index The index of the selected program
     */
    public static void updateProduct (int index, Product newProduct)
    {
        allProducts.set(index, newProduct);
    }
    
    /**
     *  The deletePart method deletes a part when selected in a table.
     *  @param selectedPart Highlighted part from Parts Table to delete
     *  @return false Allows the delete command to be ignored if nothing is selected
     */
    public static boolean deletePart (Part selectedPart)
    {
        if(selectedPart != null)
        {
            return Inventory.getAllParts().remove(selectedPart);
        }
        return false;
    }
    
    /**
     *  The deleteProduct method deletes a product when selected in a table.
     *  @param selectedProduct Highlighted part from Products Table to delete
     *  @return false Allows the delete command to be ignored if nothing is selected
     */
    public static boolean deleteProducts (Product selectedProduct)
    {
        if(selectedProduct != null)
        {
            return Inventory.getAllProducts().remove(selectedProduct);
        }
        return false;
    }
    
    /**
     *  The getAllParts method returns the allParts list.
     *  @return allParts The Observable List of all the parts
     */
    public static ObservableList<Part> getAllParts()
    {
        return allParts;
    }
    
    /**
     *  The getAllProducts method returns the allProducts list.
     *  @return allProducts The Observable List of all the products
     */
    public static ObservableList<Product> getAllProducts()
    {
        return allProducts;
    } 
    
    /**
     *  The autoGenPartId generates a new part ID starting at 1000
     *  @return partIdCount The part ID number from 1000
     */
    public static int autoGenPartId()
    {
        partIdCount ++;
        return partIdCount;
    }
    
    /**
     *  The autoGenProductId generates a new part ID starting at 2000
     *  @return productIdCount The product ID number from 2000
     */
    public static int autoGenProductId()
    {
        productIdCount ++;
        return productIdCount;
    }
}

