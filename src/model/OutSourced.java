package model;

/**
 * The OutSourced class represents a part that was outsourced for manufacturing.
 * @author Patricia
 */
public class OutSourced extends Part
{   
    private String companyName;
    
    /**
     * The constructor initializes the OutSourced fields and inherits from the Parts class
     * @param id OutSourced Part ID
     * @param name Part name
     * @param price Part price
     * @param stock Part inventory level
     * @param min Part minimum inventory level
     * @param max Part maximum inventory level
     * @param companyName Name of the 3rd party company
     */
    public OutSourced(int id, String name, double price, int stock, int min, int max, String companyName)
    {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }
    
    /**
     *  The getCompanyName method returns the Company Name.
     * @return companyName The name of the 3rd party company
     */
    public String getCompanyName()
    {
        return companyName;
    }
    
    /**
     *  The setCompanyName method updates the Company Name field.
     *  @param companyName The name of the 3rd party company
     */
    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }
}
