package patriciawetherbyc482pa;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.OutSourced;
import model.Product;

/**
 * This class loads the Inventory Program.
 * @author Patricia
 */
public class PatriciaWetherbyC482PA extends Application {
    
    @Override
    public void start(Stage stage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This is the main method.
     * This method initializes parts and products for the program.
     * @param args The command line arguments
     */
    public static void main(String[] args)
    {
        //Pre-populated values!
        
        InHouse part1 = new InHouse(1, "Motherboard", 129.99, 3, 1, 10, 452);
        InHouse part2 = new InHouse(2, "DDR4 8GB RAM", 64.99, 6, 1, 10, 281);
        OutSourced part3 = new OutSourced(3, "Power Supply 600W", 49.99, 4, 1, 10, "Corsair");
        OutSourced part4 = new OutSourced(4, "GPU 4GB", 59.99, 2, 1, 10, "AMD");
        
        Inventory.addPart(part1);
        Inventory.addPart(part2);
        Inventory.addPart(part3);
        Inventory.addPart(part4);
        
        Product product1 = new Product(1, "Desktop", 999.99, 4, 1, 10);
        Product product2 = new Product(2, "Laptop", 759.99, 8, 1, 12);
        
        Inventory.addProduct(product1);
        Inventory.addProduct(product2);
        
        
        launch(args);
    }
       
    
}
