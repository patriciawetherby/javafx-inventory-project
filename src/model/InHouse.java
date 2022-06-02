package model;

/**
 * The InHouse class represents a part made in house. 
 * @author Patricia
 */
public class InHouse extends Part
{

    private int machineId;
    
    /**
     * The constructor initializes the InHouse fields and inherits from the Part class
     * @param id InHouse ID
     * @param name Part name
     * @param price Part price
     * @param stock Part inventory level
     * @param min Part minimum inventory value
     * @param max Part maximum inventory value
     * @param machineId Part Machine ID
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId)
    {
        super(id, name, price, stock, min, max); //Inherited from Part abstract class
        this.machineId = machineId;
    }
    
    /**
     * The getMachineId method returns the Machine ID
     * @return machineId The ID of the Machine
     */
    public int getMachineId()
    {
        return machineId;
    }
    /**
     * The setMachineId method updates the Machine ID field
     * @param machineId The ID of the Machine
     */
    public void setMachineId(int machineId)
    {
        this.machineId = machineId;
    }
}
