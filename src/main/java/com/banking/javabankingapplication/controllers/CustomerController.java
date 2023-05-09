package com.banking.javabankingapplication.controllers;

import com.banking.javabankingapplication.customer.CustomerValidator;
import com.banking.javabankingapplication.dbconnection.DatabaseHandler;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import com.banking.javabankingapplication.customer.Customer;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class CustomerController extends Application {
    private DatabaseHandler handler;

    @FXML
    private Text headerText;

    @FXML
    private TextArea firstNameField;

    @FXML
    private TextArea lastNameField;

    @FXML
    private TextArea addressField;

    @FXML
    private TextArea dobField;

    @FXML
    private TextArea phoneField;

    @FXML
    private Button createAccountButton;

    @FXML
    private void initialize() {
        // initialize the controller
    }

    @FXML
    private void switchToBankingScene(ActionEvent event) throws IOException {
        Parent bankingViewParent = FXMLLoader.load(getClass().getResource("/com/banking/javabankingapplication/banking-view.fxml"));
        Scene bankingViewScene = new Scene(bankingViewParent);

        // Get the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(bankingViewScene);
        window.show();
    }


    @FXML
    private void createAccount(ActionEvent event) throws IOException {
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String address = addressField.getText().trim();
        String dob = dobField.getText().trim();
        String phone = phoneField.getText().trim();
        handler = new DatabaseHandler();

        // Perform data validation here
        CustomerValidator validator = new CustomerValidator();

        if(!validator.checkFirstName(firstName)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error in input!");
            alert.setContentText("First name cant have numbers!");
            alert.showAndWait();
            firstNameField.setText("");
            return;
        }

        if(!validator.checkLastName(lastName)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error in input!");
            alert.setContentText("Last name cant have numbers!");
            alert.showAndWait();
            lastNameField.setText("");
            return;
        }

        if(!validator.checkPhoneNumber(phone)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error in input!");
            alert.setContentText("Phone needs to be in format +385012020!");
            alert.showAndWait();
            phoneField.setText("");
            return;
        }

        if(!validator.checkDateOfBirth(dob)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error in input!");
            alert.setContentText("Date of birth needs to be YYYY-DD-MM!");
            alert.showAndWait();
            dobField.setText("");
            return;
        }


        // Create a new Customer object with the user's input
        Customer customer = new Customer(firstName, lastName, phone, dob, address);



        // Call the createUser() method to save the user to the database
        handler.insertCustomer(customer.getCustomerFirstName(), customer.getCustomerLastName(), customer.getPhoneNumber(),
                customer.getDateOfBirth(), customer.getCustomerAddress());

        // Display a success message to the user
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success!");
        alert.setHeaderText(null);
        alert.setContentText("Your account has been created successfully.");
        alert.showAndWait();

        // Clear the input fields
        firstNameField.setText("");
        lastNameField.setText("");
        addressField.setText("");
        dobField.setText("");
        phoneField.setText("");
        switchToBankingScene(event);

    }


    @Override
    public void start(Stage stage) throws Exception {

    }
}