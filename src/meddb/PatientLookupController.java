
package meddb;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
    
    //Daniel Wolfe
    //patient information search controller
    //this page allows the user to search for patient information by entering name or room number

public class PatientLookupController implements Initializable {
    
     //variables for changing scenes
    private Stage stage;
    private Scene scene;
    private Parent root;
    private static Patient selectedPatient;
    
    @FXML private Label textReleased;
    @FXML private TextField textName;
    @FXML private TextField textRoom;
    @FXML private TableView<Patient> tableView;
    @FXML private TableColumn<Patient, String> lastnameColumn;
    @FXML private TableColumn<Patient, String> firstnameColumn;
    @FXML private TableColumn<Patient, Integer> ageColumn;
    @FXML private TableColumn<Patient, String> sexColumn;
    @FXML private TableColumn<Patient, String> illnessColumn;
    @FXML private TableColumn<Patient, String> allergiesColumn;
    @FXML private TableColumn<Patient, String> dateColumn;
    @FXML private TableColumn<Patient, String> wardColumn;
    @FXML private TableColumn<Patient, Integer> roomColumn;
    
        //button for searching patient information by name
    @FXML
    private void handleButtonName(ActionEvent event) {
        
            //erases old text on button click
        tableView.getItems().clear();
        String databaseURL = "jdbc:derby://localhost:1527/contact";
        String last = textName.getText();
        
            //try catch for connecting to database
        try {
                //tries to connect to database using database URL, username and password parameter
            Connection connection = DriverManager.getConnection(databaseURL, "nbuser", "nbuser");
            System.out.println("Connected to Database");
            
                //searches for patient in database using lastname and returns result set of information
            String sql = "SELECT * FROM admittedpatients where lastname=?";
            PreparedStatement p = connection.prepareStatement(sql);
            p.setString(1, last);
            ResultSet rs = p.executeQuery();
            
                //creating list for patient information to be stored and then set to the table
            ObservableList<Patient> patient; 
            patient = FXCollections.observableArrayList();
            
                //if query found a matching table from the execute query rs
                //stores all obtained information from the query in new variables
            while (rs.next()) {
                
                    //retrieving 
                String lastname = rs.getString("lastname");
                String firstname = rs.getString("firstname");
                int age = rs.getInt("age");
                String sex = rs.getString("sex");
                String illness = rs.getString("illness");
                String allergies = rs.getString("allergies");
                String dateAdmitted = rs.getString("dateadmitted");
                String ward = rs.getString("ward");
                int room = rs.getInt("roomnumber");
                
                    //patient constructor for retrieved patient information
                selectedPatient = new Patient(firstname, lastname, age, sex, illness, allergies, dateAdmitted, ward, room);
                    //puts patient object information to patient list
                patient.add(selectedPatient);
            }  
            
                //sets all information from patient list to the table
            tableView.setItems(patient);
                  
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    
        //button for entering patient room
        //code is the same as above method but uses room number to search
    @FXML
    private void handleButtonRoom(ActionEvent event) {
        
        tableView.getItems().clear();
        String databaseURL = "jdbc:derby://localhost:1527/contact";
        int roomnum = Integer.valueOf(textRoom.getText());
        
        try {
            
            Connection connection = DriverManager.getConnection(databaseURL, "nbuser", "nbuser");
            String sql = "SELECT * FROM admittedpatients where roomnumber=?";
            PreparedStatement p = connection.prepareStatement(sql);
            p.setInt(1, roomnum);
            ResultSet rs = p.executeQuery();
            
            ObservableList<Patient> patient; 
            patient = FXCollections.observableArrayList();
            
            while (rs.next()) {
                
                String lastname = rs.getString("lastname");
                String firstname = rs.getString("firstname");
                int age = rs.getInt("age");
                String sex = rs.getString("sex");
                String illness = rs.getString("illness");
                String allergies = rs.getString("allergies");
                String dateAdmitted = rs.getString("dateadmitted");
                String ward = rs.getString("ward");
                int room = rs.getInt("roomnumber");
                
                selectedPatient = new Patient(firstname, lastname, age, sex, illness, allergies, dateAdmitted, ward, room);
                
                patient.add(selectedPatient);
            }  
            
            tableView.setItems(patient);
                  
        } 
        
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    
        //button to remove selected patient entry in database
    @FXML
    @SuppressWarnings("empty-statement")
    private void handleButtonRemove(ActionEvent event) throws IOException {
          
        String databaseURL = "jdbc:derby://localhost:1527/contact";
        
            //try to connect to the database
        try {
            Connection connection = DriverManager.getConnection(databaseURL, "nbuser", "nbuser");
            
                //delete table database command string
            String sql = "DELETE FROM admittedpatients WHERE lastname=?";;
                //prepared statement to put selected last name into ? from patient class
            PreparedStatement p = connection.prepareStatement(sql);
            p.setString(1, selectedPatient.getLast());
                //execute DELETE FROM query
            p.executeUpdate();
                //put new text on textbox confirming deletion
            textReleased.setText(selectedPatient.toString() + "Patient released and removed from Database");
        
        }
        
        catch (SQLException ex) {
            //ex.printStackTrace();
        }
        
    }    
    
        //button to head back to the menu
    @FXML
    private void handleButtonBack(ActionEvent event) throws IOException {
        
        root = FXMLLoader.load(getClass().getResource("PatientMenu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        lastnameColumn.setCellValueFactory(new PropertyValueFactory<>("last"));
        firstnameColumn.setCellValueFactory(new PropertyValueFactory<>("first"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        sexColumn.setCellValueFactory(new PropertyValueFactory<>("sex"));
        illnessColumn.setCellValueFactory(new PropertyValueFactory<>("illness"));
        allergiesColumn.setCellValueFactory(new PropertyValueFactory<>("allergies"));
        wardColumn.setCellValueFactory(new PropertyValueFactory<>("ward"));
        roomColumn.setCellValueFactory(new PropertyValueFactory<>("room"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateAdmitted"));
    }       
}
