
package meddb;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
    
    //Daniel Wolfe
    //patient information search controller
    //this page allows the user to search for patient information by entering name or room number

public class PatientLookupController implements Initializable {
    
     //variables for changing scenes
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    private TextField textName;
    @FXML
    private TextField textRoom;
    @FXML
    private TextArea textOut;
    
        //button for searching patient information by name
    @FXML
    private void handleButtonName(ActionEvent event) {
        
            //erases old text on button click
        textOut.setText("");
        String databaseURL = "jdbc:derby://localhost:1527/contact";
        String last = textName.getText();
        
            //try catch for connecting to database
        try {
                //tries to connect to database using database URL, username and password parameter
            Connection connection = DriverManager.getConnection(databaseURL, "nbuser", "nbuser");
            System.out.println("Connected to Database");
            
                //searches for patient in database using lastname and returns result set of information
            String sql = "SELECT * FROM Patients where lastname=?";
            PreparedStatement p = connection.prepareStatement(sql);
            p.setString(1, last);
            ResultSet rs = p.executeQuery();
            
                //stores all obtained information in variables, creates new patient object with information and displays toString information to textbox
            if (rs.next()) {
                
                String lastname = rs.getString("lastname");
                String firstname = rs.getString("firstname");
                int age = rs.getInt("age");
                String sex = rs.getString("sex");
                String illness = rs.getString("illness");
                String allergies = rs.getString("allergies");
                String dateAdmitted = rs.getString("dateadmitted");
                String ward = rs.getString("ward");
                int room = rs.getInt("roomnumber");
                
                Patient patient = new Patient(firstname, lastname, age, sex, illness, allergies, dateAdmitted, ward, room);
                textOut.setText(patient.toString());
            
            }    
                  
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    
        //button for entering patient room
        //code is the same as above method but uses room number to search
    @FXML
    private void handleButtonRoom(ActionEvent event) {
        
        textOut.setText("");
        String databaseURL = "jdbc:derby://localhost:1527/contact";
        int roomnum = Integer.valueOf(textRoom.getText());
        
        try {
            Connection connection = DriverManager.getConnection(databaseURL, "nbuser", "nbuser");
            System.out.println("Connected to Database");
            
            String sql = "SELECT * FROM Patients where lastname=?";
            PreparedStatement p = connection.prepareStatement(sql);
            p.setInt(1, roomnum);
            ResultSet rs = p.executeQuery();
            
            if (rs.next()) {
                
                String lastname = rs.getString("lastname");
                String firstname = rs.getString("firstname");
                int age = rs.getInt("age");
                String sex = rs.getString("sex");
                String illness = rs.getString("illness");
                String allergies = rs.getString("allergies");
                String dateAdmitted = rs.getString("dateadmitted");
                String ward = rs.getString("ward");
                int room = rs.getInt("roomnumber");
                
                Patient patient = new Patient(firstname, lastname, age, sex, illness, allergies, dateAdmitted, ward, room);
                textOut.setText(patient.toString());
                
            }    
                  
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    
        //button to head back to the menu
    @FXML
    private void handleButtonBack(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("PatientMenu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }    
    
}
