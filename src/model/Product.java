package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The Product class represents a product
 * @author Patricia
 */
public class Product 
{
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    
    /**
     *  The Product constructor initializes the Product fields.
     *  @param id Product ID
     *  @param name Product name
     *  @param price Product price
     *  @param stock Product inventory level
     *  @param min Product minimum inventory level
     *  @param max Product maximum inventory level
     */
    public Product(int id, String name, double price, int stock, int min, int max)
    {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     *  The addAssociatedpart method adds a part to an Associated Parts list
     *  @param part Parts associated with the product
     */
    public void addAssociatedPart(Part part)
    {
        associatedParts.add(part);
    }
    
    /**
     *  The deleteAssociatedParts method deletes a part from an Associated Parts list
     *  @param selectedAssociatedPart Highlighted part to add to the Associated Part list
     *  @return false Checking if no part was selected for deletion
     */
    public boolean deleteAssociatedParts(Part selectedAssociatedPart)
    {
        
        if(selectedAssociatedPart != null)
        {
            return getAllAssociatedParts().remove(selectedAssociatedPart);
        }
        return false;
        
    }
    
    /**
     *  The getAllAssociatedParts method gets the list of associatedParts.
     *  @return associatedParts The associatedPart list contents
     */
    public ObservableList<Part> getAllAssociatedParts()
    {
        return associatedParts;
        
    }
    
    /**
     *  The getId method gets the Product ID
     * @return id The ID to get
     */
    public int getId()
    {
        return id;
    }

    /**
     * The setId method updates the Product ID
     * @param id The ID to set
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * The getName method gets the Product Name
     * @return name The Product name to get
     */
    public String getName()
    {
        return name;
    }

    /**
     *  The setName method updates the Product Name
     * @param name The Product name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     *  The getPrice method gets the Product price
     *  @return price The Product price to get
     */
    public double getPrice()
    {
        return price;
    }

    /**
     *  The setPrice method updates the Product price
     * @param price The Product price to set
     */
    public void setPrice(double price)
    {
        this.price = price;
    }

    /**
     *  The getStock method gets the Product stock
     *  @return stock The inventory level of the Product
     */
    public int getStock()
    {
        return stock;
    }

    /**
     *  The setStock method updates the Product stock
     *  @param stock The inventory level of the Product
     */
    public void setStock(int stock)
    {
        this.stock = stock;
    }

    /**
     *  The getMin method gets the Product minimum value
     *  @return min The minimum inventory level of the Product
     */
    public int getMin()
    {
        return min;
    }

    /**
     *  The setMin method updates the Product minimum value
     *  @param min The minimum inventory level of the Product
     */
    public void setMin(int min)
    {
        this.min = min;
    }

    /**
     *  The getMax method gets the Product maximum value
     *  @return max The maximum inventory level of the Product
     */
    public int getMax()
    {
        return max;
    }

    /**
     *  The setMax method updates the Product maximum value
     *  @param max The maximum inventory level of the Product
     */
    public void setMax(int max)
    {
        this.max = max;
    }
    
    
}
