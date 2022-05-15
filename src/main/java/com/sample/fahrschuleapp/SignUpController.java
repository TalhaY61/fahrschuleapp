package com.sample.fahrschuleapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {


    @FXML
    private Button registerbtn;
    @FXML
    private Button cancelsigninbtn;
    @FXML
    private Button deleteconfirmbtn;
    @FXML
    private Label registertxtlabel, deletetxtlabel;
    @FXML
    private TextField nametxtfield;
    @FXML
    private TextField surnametxtfield;
    @FXML
    private TextField agetxtfield, pricetxtfield;
    @FXML
    private TextField emailtxtfield, drivinglicensetxtfield;
    @FXML
    private TextField phonenumbertxtfield, usernametxtfield, gendertxtfield, salarytxtfield;
    @FXML
    private PasswordField passwordtxtfield;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void cancelSignInButtonPressed(ActionEvent event) throws IOException {
        //switch zur AdminView und dort kann man weiter machen.
        root = FXMLLoader.load(getClass().getResource("AdminView.fxml"));
        stage = (Stage) cancelsigninbtn.getScene().getWindow();
        stage.setScene(new Scene(root, 520, 400));
        stage.show();
    }

    public void registerButtonInstructorPressed(ActionEvent event) throws IOException {
        registerInstructor();
    }
    public void deleteButtonOnAction(ActionEvent event) throws IOException {
        deleteUser();
    }
    public void registerButtonStudentPressed(ActionEvent event) throws IOException {
        registerStudent();
    }

    public void registerInstructor(){
        //Verknüpft die MySQL und hier wird dann der USER REGISTRIERT.
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String firstname = nametxtfield.getText();
        String surname = surnametxtfield.getText();
        String age = agetxtfield.getText();
        String email = emailtxtfield.getText();
        String phonenumber = phonenumbertxtfield.getText();
        String username = usernametxtfield.getText();
        String password = passwordtxtfield.getText();
        String gender = gendertxtfield.getText();
        String salary = salarytxtfield.getText();

        String insertFields = "INSERT INTO instructor (FirstName, SurName, Age, Email, Phonenumber, Gender, Salary, Username, Password) VALUES ('";
        String insertValues = firstname + "','" + surname  + "','" + age  + "','" + email  + "','" + phonenumber  + "','" + gender  + "','" + salary  + "','" + username  + "','" + password + "');";
        String insertToRegister = insertFields + insertValues;

        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertToRegister);
            registertxtlabel.setText("Instructor has been registered!");

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    public void registerStudent(){
        //Verknüpft die MySQL und hier wird dann der USER REGISTRIERT.
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String firstname = nametxtfield.getText();
        String surname = surnametxtfield.getText();
        String age = agetxtfield.getText();
        String email = emailtxtfield.getText();
        String phonenumber = phonenumbertxtfield.getText();
        String username = usernametxtfield.getText();
        String password = passwordtxtfield.getText();
        String gender = gendertxtfield.getText();
        String price = pricetxtfield.getText();
        String drivingLicense = drivinglicensetxtfield.getText();

        String insertFields = "INSERT INTO student (FirstName, SurName, Age, Email, Phonenumber, Gender, Price, DrivingLicenseType, Username, Password) VALUES ('";
        String insertValues = firstname + "','" + surname  + "','" + age  + "','" + email  + "','" + phonenumber  + "','" + gender  + "','" + price  + "','" + drivingLicense  + "','" + username  + "','" + password + "');";
        String insertToRegister = insertFields + insertValues;

        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertToRegister);
            registertxtlabel.setText("Student has been registered!");

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    //Problem beim löschen des schülers oder lehrers (15.05, 22:07)
    //bozduk bunu kankss
    public void deleteUser() {

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String username = usernametxtfield.getText();
        String password = passwordtxtfield.getText();

        String deleteUser = "DELETE FROM instructor, student WHERE Username=" + "'" + username + "';";
        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(deleteUser);
            deletetxtlabel.setText("User has been removed!");

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }




}
