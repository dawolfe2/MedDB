
package meddb;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

    //Daniel Wolfe
    //menu controller for navigating patient menu scenes

public class PatientMenuController implements Initializable {

        //private variables needed for changing scenes
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML private Button buttonEntry;
    @FXML private Button buttonRecords;
    @FXML private AnchorPane rootPane;
    
        //buttons for moving to the patient entry scene and the patient lookup scene
    @FXML
    private void handleButtonEntry(ActionEvent event) throws IOException {
        
            //code to change scenes to NewPatient fxml
        root = FXMLLoader.load(getClass().getResource("NewPatient.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }
    
    @FXML
    private void handleButtonRecords(ActionEvent event) throws IOException {
        
            //code to change scenes to PatientLookup fxml
        root = FXMLLoader.load(getClass().getResource("PatientLookup.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }
    
    @FXML
    private void handleButtonRecordsAdmin(ActionEvent event) throws IOException {
        
            //code to change scenes to PatientLookup fxml
        root = FXMLLoader.load(getClass().getResource("PatientLookupAdmin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
