/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * This is the FXML Controller class for the Add and Modify Product screens.
 * @author Patricia
 */
public class AddModifyProductController implements Initializable {
    
    //FXML labels
    
    @FXML
    private TableView<Part> productPartTable;
    
    @FXML
    private TableView<Part> assocPartTable;
    
    @FXML
    private TextField productIdTxt;

    @FXML
    private TextField productNameTxt;

    @FXML
    private TextField productInvTxt;

    @FXML
    private TextField productPriceTxt;

    @FXML
    private TextField productMaxTxt;

    @FXML
    private TextField productMinTxt;

    @FXML
    private TableColumn<Product, Integer> partIdCol2;

    @FXML
    private TableColumn<Product, String> partNameCol2;

    @FXML
    private TableColumn<Product, Integer> partInvLevelCol2;

    @FXML
    private TableColumn<Product, Double> partPriceCol2;
    
    @FXML
    private TableColumn<Product, Integer> partIdCol3;

    @FXML
    private TableColumn<Product, String> partNameCol3;

    @FXML
    private TableColumn<Product, Integer> partInvLevelCol3;

    @FXML
    private TableColumn<Product, Double> partPriceCol3;
 
    @FXML
    private Button searchParts2;
    
    @FXML
    private TextField partsQuery2;
    
    Stage stage;
    Parent scene;
    
    private Product product;
    private ObservableList<Part> bottomList = FXCollections.observableArrayList();
    
    @FXML
    void onActionAddPartToProduct(ActionEvent event) {
        
        /**
         * Runtime Error: I had a lot of difficulty with this section since I was unsure how to use the 
         * methods from the Product class in a non-static method. Until this point, I was only using static 
         * calls from the Part class. I had contacted the Course Instructor and realized I was declaring an instance
         * of the class incorrectly. Afterwards, I was able to access the methods properly after using Wrapper classes.
         * I also realized I needed to create a temporary Observable List to hold the values of the bottom Associated Parts table.
         * Without this, I was unable to figure out how to properly use the two tables in the Product screens.
         */
        
        //Check if item is selected
        if((Part)productPartTable.getSelectionModel().getSelectedItem() == null)
        {
           return; 
        }
        
        Part selectedPart = (Part)productPartTable.getSelectionModel().getSelectedItem(); 
        
        if (selectedPart == null)
            return;
        
        bottomList.add(selectedPart);    
    }

    @FXML
    void onActionRemoveAssocPart(ActionEvent event) {
        
        //Remove part from the Associated Parts Table
        if((Part)assocPartTable.getSelectionModel().getSelectedItem() == null)
        {
           return; 
        }
     
        Part selectedPart = (Part)assocPartTable.getSelectionModel().getSelectedItem();
       
        bottomList.remove(selectedPart);
       
    }

    @FXML
    void onActionReturnToMainMenu(ActionEvent event) throws IOException {
        
        //Confirm button for the Back Button
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will reset the text field values. Is that okay?");
        //If user clicks Okay button, they will go to main menu
        Optional<ButtonType> result = alert.showAndWait();
        
        if(result.isPresent() && result.get() == ButtonType.OK)
        {
            stage =(Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }

    }

    /**
     * @exception NumberFormatException Incorrect value was entered
     */
    @FXML
    void onActionSaveProduct(ActionEvent event) throws IOException {
        
        try
        {
            int id = Inventory.autoGenProductId();
            String name = productNameTxt.getText();
            int stock = Integer.parseInt(productInvTxt.getText());
            double price = Double.parseDouble(productPriceTxt.getText());
            int max = Integer.parseInt(productMaxTxt.getText());
            int min = Integer.parseInt(productMinTxt.getText());
        
            if (min > max)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter appropriate values for min and max values");
                    alert.showAndWait();
                    return;
                }

            if (stock > max || stock < min)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter appropriate inventory value");
                    alert.showAndWait();
                    return;
                }
        
            Product p = new Product(id, name, price, stock, min, max);
            Inventory.addProduct(p);
            p.getAllAssociatedParts().addAll(bottomList);
        
        
            stage =(Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
        catch(NumberFormatException e)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING); //Alert dialog box
            alert.setTitle("Warning Dialog");
            alert.setContentText("Please enter a valid value for each Text Field");
            alert.showAndWait();
        }
        
        

    }
    
    /**
     * @exception NumberFormatException Incorrect value was entered
     */
    @FXML
    void onActionUpdateProduct(ActionEvent event) throws IOException {
        
        try
        {
            int id = Integer.parseInt(productIdTxt.getText());
            String name = productNameTxt.getText();
            int stock = Integer.parseInt(productInvTxt.getText());
            double price = Double.parseDouble(productPriceTxt.getText());
            int max = Integer.parseInt(productMaxTxt.getText());
            int min = Integer.parseInt(productMinTxt.getText());
            
            if (min > max)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter appropriate values for min and max values");
                    alert.showAndWait();
                    return;
                }

            if (stock > max || stock < min)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter appropriate inventory value");
                    alert.showAndWait();
                    return;
                }
        
            int index = Inventory.getAllProducts().indexOf(product);
            Product p = new Product(id, name, price, stock, min, max);
            p.getAllAssociatedParts().addAll(bottomList);
            Inventory.updateProduct(index, p);
        
            stage =(Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
            
        }
        catch(NumberFormatException e)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING); //Alert dialog box
            alert.setTitle("Warning Dialog");
            alert.setContentText("Please enter a valid value for each Text Field");
            alert.showAndWait();
        }
        
        
        

    }
    
    //Searches parts from upper table
    @FXML
    private void onActionSearchProductParts(ActionEvent event)
    {
        String q = partsQuery2.getText();
        
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
        
        productPartTable.setItems(part);
        partsQuery2.setText("");
    }
    
    /**
     *  The sendProduct method sends the values from the Add Product menu so it can be modified
     *  @param product Temporary product for sendProduct method
     */
    public void sendProduct(Product product)
    {
        this.product = product;
        
        productIdTxt.setText(String.valueOf(product.getId()));
        productNameTxt.setText(product.getName());
        productInvTxt.setText(String.valueOf(product.getStock()));
        productPriceTxt.setText(String.valueOf(product.getPrice()));
        productMinTxt.setText(String.valueOf(product.getMin()));
        productMaxTxt.setText(String.valueOf(product.getMax()));
        
        bottomList.addAll(product.getAllAssociatedParts());
            
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        
        productPartTable.setItems(Inventory.getAllParts());
                
        partIdCol2.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol2.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvLevelCol2.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol2.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        assocPartTable.setItems(bottomList);
        
        partIdCol3.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol3.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvLevelCol3.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol3.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        
        
    }    
    
}
