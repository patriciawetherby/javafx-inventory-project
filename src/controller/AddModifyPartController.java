package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.OutSourced;
import model.Part;

/**
 * This is the FXML Controller class for the Add and Modify Part screens.
 * @author Patricia
 */

public class AddModifyPartController implements Initializable {

    Stage stage;
    Parent scene;
    private Part part;
    
    @FXML
    void onActionAddInHouse(ActionEvent event) throws IOException {
        
        // Check if InHouse or OutSourced was selected
        if(addPartIHBtn.isSelected())
        {
            partOriginLbl.setText("Machine ID");
        }
        else if(addPartOSBtn.isSelected())
        {
            partOriginLbl.setText("Company Name");
        }

    }
    
    @FXML
    void onActionAddOutSourced(ActionEvent event) throws IOException {
        
        // Check if InHouse or OutSourced was selected
        if(addPartIHBtn.isSelected())
        {
            partOriginLbl.setText("Machine ID");
        }
        else if(addPartOSBtn.isSelected())
        {
            partOriginLbl.setText("Company Name");
        }
       
    }
    
    @FXML
    void onActionModifyPartInHouse(ActionEvent event) throws IOException {
        
        //Check if InHouse or OutSourced was selected
         if(addPartIHBtn.isSelected())
        {
            partOriginLbl.setText("Machine ID");
        }
        else if(addPartOSBtn.isSelected())
        {
            partOriginLbl.setText("Company Name");
        }

    }

    @FXML
    void onActionModifyPartOutSourced(ActionEvent event) throws IOException {
        
        //Check if InHouse or OutSourced was selected
        if(addPartIHBtn.isSelected())
        {
            partOriginLbl.setText("Machine ID");
        }
        else if(addPartOSBtn.isSelected())
        {
            partOriginLbl.setText("Company Name");
        }

    }
    
    @FXML
    void onActionReturnToMainMenu(ActionEvent event) throws IOException {
        
        // Confirmation check for Back button
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will reset the text field values. Is that okay?");
        // If user clicks Okay button, they will go to main menu
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
    void onActionSavePart(ActionEvent event) throws IOException {
        
        //Using Wrapper classes to get the values from the text fields
        
        try
        {
            int id = Inventory.autoGenPartId();
            String name = partNameTxt.getText();
            int stock = Integer.parseInt(partInvTxt.getText());
            double price = Double.parseDouble(partPriceTxt.getText());
            int max = Integer.parseInt(partMaxTxt.getText());
            int min = Integer.parseInt(partMinTxt.getText());
            boolean isInHouse;
            
            /**
             * Logical Error: A logical error happened when I was checking the alerts where I was unsure why the part was
             * still saving despite the error message showing up. I had forgot to add the return command, and had been wrestling
             * with the code to see if I needed a while loop and a separate variable to check if the error persisted. Thankfully,
             * adding a simple return command eliminated the need for more code.
             */
            
            // Checking if min/max levels are appropriate
            
            if (min > max)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter appropriate values for min and max values");
                alert.showAndWait();
                return;
            }
            
            //Checking if stock matches min and max values
            
            if (stock > max || stock < min)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter appropriate inventory value");
                alert.showAndWait();
                return;
                
            }
            
            //Checking if InHouse or OutSourced

            if(addPartIHBtn.isSelected())
            {
                isInHouse = true;
                int machineId = Integer.parseInt(partOrigin.getText());
                InHouse i = new InHouse(id, name, price, stock, min, max, machineId);
                Inventory.addPart(i);
            }
            else
            {
                isInHouse = false;
                String companyName = partOrigin.getText();
                OutSourced o = new OutSourced(id, name, price, stock, min, max, companyName);
                Inventory.addPart(o);
            }
            
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
    void onActionUpdatePart(ActionEvent event) throws IOException
    {  
        
        try
        {
            int id = Integer.parseInt(partIdTxt.getText());
            String name = partNameTxt.getText();
            int stock = Integer.parseInt(partInvTxt.getText());
            double price = Double.parseDouble(partPriceTxt.getText());
            int max = Integer.parseInt(partMaxTxt.getText());
            int min = Integer.parseInt(partMinTxt.getText());
            boolean isInHouse;
            boolean error;
            
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
            
            if(addPartIHBtn.isSelected())
            {
                isInHouse = true;
                int machineId = Integer.parseInt(partOrigin.getText()); 
                int index = Inventory.getAllParts().indexOf(part);
                InHouse i = new InHouse(id, name, price, stock, min, max, machineId);
                Inventory.updatePart(index, i);
            }
            else
            {
                isInHouse = false;
                String companyName = partOrigin.getText();
                int index = Inventory.getAllParts().indexOf(part);
                OutSourced o = new OutSourced(id, name, price, stock, min, max, companyName);
                Inventory.updatePart(index, o);
             
            }
            
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
     *  The sendPart method sends the values from the Add Part menu so it can be modified
     *  @param part Temporary part for sendPart method
     */
     public void sendPart(Part part)
    {
        this.part = part;
        
        //Check if InHouse or OutSourced
        if(part instanceof InHouse)
        {
            partOriginLbl.setText("Machine ID");
            addPartIHBtn.setSelected(true);
            partIdTxt.setText(String.valueOf(part.getId()));
            partNameTxt.setText(part.getName());
            partInvTxt.setText(String.valueOf(part.getStock()));
            partPriceTxt.setText(String.valueOf(part.getPrice()));
            partMinTxt.setText(String.valueOf(part.getMin()));
            partMaxTxt.setText(String.valueOf(part.getMax()));
            partOrigin.setText(String.valueOf(((InHouse)part).getMachineId()));
            partOriginLbl.setText("Machine ID");
        }
        else if(part instanceof OutSourced)
        {
            partOriginLbl.setText("Company Name");
            addPartOSBtn.setSelected(true);
            partIdTxt.setText(String.valueOf(part.getId()));
            partNameTxt.setText(part.getName());
            partInvTxt.setText(String.valueOf(part.getStock()));
            partPriceTxt.setText(String.valueOf(part.getPrice()));
            partMinTxt.setText(String.valueOf(part.getMin()));
            partMaxTxt.setText(String.valueOf(part.getMax()));
            partOrigin.setText(((OutSourced)part).getCompanyName());
            
        }
        
    }
    
     //FXML labels
    @FXML
    private RadioButton addPartIHBtn;

    @FXML
    private ToggleGroup addPartTG;

    @FXML
    private RadioButton addPartOSBtn;

    @FXML
    private TextField partIdTxt;

    @FXML
    private TextField partNameTxt;

    @FXML
    private TextField partInvTxt;

    @FXML
    private TextField partPriceTxt;

    @FXML
    private TextField partMaxTxt;

    @FXML
    private TextField partMinTxt;

    @FXML
    private TextField partOrigin;
    
    @FXML
    private Label partOriginLbl;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        
    }    
    
}
