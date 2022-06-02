
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

/**
 * This is the main menu controller
 * @author Patricia
 */
public class MainMenuController implements Initializable {
    
    //FXML labels
    @FXML
    private TableView<Part> PartsTable;

    @FXML
    private TableColumn<Part, Integer> partIdCol;

    @FXML
    private TableColumn<Part, String> partNameCol;

    @FXML
    private TableColumn<Part, Integer> partInvLevelCol;

    @FXML
    private TableColumn<Part, Double> partPriceCol;

    @FXML
    private TableView<Product> ProductsTable;

    @FXML
    private TableColumn<Product, Integer> productIdCol;

    @FXML
    private TableColumn<Product, Integer> productNameCol;

    @FXML
    private TableColumn<Product, Integer> productInvLevelCol;

    @FXML
    private TableColumn<Product, Integer> productPriceCol;
    
    @FXML
    private Button partSearchBtn;
    
    @FXML
    private Button productSearchBtn;
    
    @FXML
    private TextField partQuery;
    
    @FXML
    private TextField productQuery;
    
    
    Stage stage;
    Parent scene;   
    
    /**
     * New Feature Proposal:
     * 
     * If I were to make an updated version of this program, I would combine the add/modify/delete buttons and
     * simply change the event handlers to respond appropriately. This would be implemented very similarly to how I 
     * merged the update/add controllers for my parts and products. I would do the same with the search features and 
     * have if statements figure out what type of item was selected. If possible, I would also implement an create product 
     * feature directly from the main menu that would quick add a product by multiple selecting several parts.
     */
    @FXML
    void onActionAddPartMenu(ActionEvent event) throws IOException {
        
        stage =(Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddPart.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionAddProductMenu(ActionEvent event) throws IOException {
        
        stage =(Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionDeletePart(ActionEvent event) {
        
        if((Part)PartsTable.getSelectionModel().getSelectedItem() == null)
        {
           return; 
        }
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will delete this part. Is that okay?");
        //If user clicks Okay button, they will delete
        Optional<ButtonType> result = alert.showAndWait();
        
        if(result.isPresent() && result.get() == ButtonType.OK)
        {
            Part selectedPart = (Part)PartsTable.getSelectionModel().getSelectedItem();
            Inventory.deletePart(selectedPart);
        }

    }

    @FXML
    void onActionDeleteProduct(ActionEvent event) {
        
        if((Product)ProductsTable.getSelectionModel().getSelectedItem() == null)
        {
           return; 
        }
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will delete this product. Is that okay?");
        //If user clicks Okay button, they will delete
        Optional<ButtonType> result = alert.showAndWait();
        
        if(result.isPresent() && result.get() == ButtonType.OK)
        {
            Product selectedProduct = (Product)ProductsTable.getSelectionModel().getSelectedItem();
            if(selectedProduct.getAllAssociatedParts().isEmpty())
            {
                Inventory.deleteProducts(selectedProduct);
            }
            else
            {
                Alert alert2 = new Alert(Alert.AlertType.ERROR, "This product contains existing parts and therefore cannot be deleted");
                alert2.showAndWait();
                return;
            }
        }
    }

    @FXML
    void onActionModifyPartMenu(ActionEvent event) throws IOException {
        
        //Allowing ModifyPartsController to access information from this controller
        if(PartsTable.getSelectionModel().getSelectedItem() == null)
        {
           return; 
        }
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ModifyPart.fxml"));
        loader.load();
        
        AddModifyPartController AMPartController = loader.getController();
        AMPartController.sendPart(PartsTable.getSelectionModel().getSelectedItem());
        
        stage =(Stage)((Button)event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot(); //Switches to ModifyPartsMenu
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionModifyProductMenu(ActionEvent event) throws IOException {
        
        //Allowing ModifyProductController to access information from this controller
        if(ProductsTable.getSelectionModel().getSelectedItem() == null)
        {
           return; 
        }
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ModifyProduct.fxml"));
        loader.load();
        
        AddModifyProductController AMProductController = loader.getController();
        AMProductController.sendProduct(ProductsTable.getSelectionModel().getSelectedItem());
        
        stage =(Stage)((Button)event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot(); //Switches to ModifyPartsMenu
        stage.setScene(new Scene(scene));
        stage.show();

    }
    

    @FXML
    private void onActionSearchParts(ActionEvent event)
    {
        String q = partQuery.getText();
        
        ObservableList<Part> part = Inventory.lookupPart(q);
        
        if(part.isEmpty())
        {
            try
            {
                int partId = Integer.parseInt(q);
                Part p = Inventory.lookupPart(partId);
            
                if(p != null)
                {
                    part.add(p);
                }
            }
            catch (NumberFormatException e)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR); //Alert dialog box
                alert.setTitle("Error");
                alert.setContentText("Part Not Found");
                alert.showAndWait();
                return;
            }
            
        }
        
        PartsTable.setItems(part);
        partQuery.setText("");
    }
    
    @FXML
    private void onActionSearchProducts(ActionEvent event)
    {
        String q = productQuery.getText();
        
        ObservableList<Product> product = Inventory.lookupProduct(q);
        
        if(product.isEmpty())
        {
            try
            {
                int productId = Integer.parseInt(q);
                Product pr = Inventory.lookupProduct(productId);
            
                if(pr != null)
                {
                    product.add(pr);
                }
                
            }
            catch (NumberFormatException e)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR); //Alert dialog box
                alert.setTitle("Error");
                alert.setContentText("Product Not Found");
                alert.showAndWait();
                return;
            }
            
        }
        
        ProductsTable.setItems(product);
        productQuery.setText("");
    }
    
    @FXML
    void onActionExitProgram(ActionEvent event) {
        
        System.exit(0);

    } 
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        PartsTable.setItems(Inventory.getAllParts());
        ProductsTable.setItems(Inventory.getAllProducts());
        
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        productIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInvLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        //Clearing text field
        partQuery.setText("");
        
    }    
    
}
