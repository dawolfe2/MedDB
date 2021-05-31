
package meddb;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
    
    //Daniel Wolfe
    //Patient information entry controller
    //this page allows a user to enter patient information to store it into the database
    //requires most field to be entered in order for buttion method to activate
    //displays entered information into a text area on the screen

public class NewPatientController implements Initializable {
    
        //variables for changing scenes
    private Stage stage;
    private Scene scene;
    private Parent root;
    
        //text variables
    @FXML private Button buttonAdd;
    @FXML private TextField firstIn;
    @FXML private TextField lastIn;
    @FXML private TextField ageIn;
    @FXML private TextField sexIn;
    @FXML private TextField illnessIn;
    @FXML private TextField roomIn;
    @FXML private TextField allergiesIn;
    @FXML private TextField DateIn;
    @FXML private TextField wardIn;
    @FXML private TextArea textOut;
    
        //button for adding patient information to the database
    @FXML
    private void handleButtonAdd(ActionEvent event) {
        
            //text field input variables for patient information
        String first = firstIn.getText();
        String last = lastIn.getText();
        int age = Integer.valueOf(ageIn.getText());
        String sex = sexIn.getText();
        String illness = illnessIn.getText();
        int room = Integer.valueOf(roomIn.getText());
        String allergies = allergiesIn.getText();
        String ward = wardIn.getText();
        String date = DateIn.getText();
        
            //patient class constructor for holding patient information
        Patient newpatient = new Patient(first, last, age, sex, illness, allergies, date, ward, room);
        
            //if statement checking that the required fields are filled
        if(first == null || first.isEmpty() || first.trim().isEmpty() && last == null || last.isEmpty() || last.trim().isEmpty() 
                && illness == null || illness.isEmpty() || illness.trim().isEmpty() && ward == null || ward.isEmpty() || ward.trim().isEmpty() 
                && room < 0 && age < 1)
        {
            textOut.setText("Invalid Input: Not all required fields are filled in");
        }
        else{
            
                //displays information in text area from the patient class to string method
            textOut.setText(newpatient.toString());

                //resets input text fields to blank
            firstIn.setText("");
            lastIn.setText("");
            ageIn.setText("");
            sexIn.setText("");
            illnessIn.setText("");
            roomIn.setText("");
            allergiesIn.setText("");
            wardIn.setText("");
            DateIn.setText("");

                //try catch to connect to database using URL, username and pass
            String databaseURL = "jdbc:derby://localhost:1527/contact";
       
            try {
                Connection connection = DriverManager.getConnection(databaseURL, "nbuser", "nbuser");
                System.out.println("Connected to Database");

                    //code to insert data into database table
                    //uses string command with ? symbols to prepare statement using variables
                String sql = "INSERT INTO admittedpatients (lastname, firstname, age, sex, illness, allergies, dateadmitted, ward, roomnumber) Values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement p = connection.prepareStatement(sql);
                    //sets ? symbols to variable values from user input
                p.setString(1, last);
                p.setString(2, first);
                p.setInt(3, age);
                p.setString(4, sex);
                p.setString(5, illness);
                p.setString(6, allergies);
                p.setString(7, date);
                p.setString(8, ward);
                p.setInt(9, room);
                    //executes update table command from sql string
                p.executeUpdate();
                p.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("Failed");
            }
        
            
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
        
    }    
    
}
