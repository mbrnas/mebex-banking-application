package com.banking.javabankingapplication.controllers;

import com.banking.javabankingapplication.bankaccount.BankAccount;
import com.banking.javabankingapplication.iban.IBAN;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;

public class BankingController {
    private BankAccount bankAccount;
    private IBAN iban;

    @FXML
    private Button depositButton;
    @FXML
    private Button withdrawButton;
    @FXML
    private Label balanceLabel;
    @FXML
    private TextField depositAmountTextField;
    @FXML
    private TextField withdrawAmountTextField;

    public BankingController() {
        bankAccount = new BankAccount(BigDecimal.TEN);
    }

    @FXML
    private void initialize() {
        depositButton.setOnAction(event -> handleDeposit());
        withdrawButton.setOnAction(event -> handleWithdraw());
        balanceLabel.setText(bankAccount.getAccountBalance().toString());
    }

    private void handleDeposit() {
        BigDecimal amount = new BigDecimal(depositAmountTextField.getText());
        bankAccount.deposit(amount);
        balanceLabel.setText(bankAccount.getAccountBalance().toString());
        depositAmountTextField.clear();
        System.out.println("hello");
    }

    private void handleWithdraw() {
        BigDecimal amount = new BigDecimal(withdrawAmountTextField.getText());
        bankAccount.withdraw(amount);
        balanceLabel.setText(bankAccount.getAccountBalance().toString());
        withdrawAmountTextField.clear();
    }
    @FXML
    private void changeHomeScene(MouseEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/banking/javabankingapplication/login.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}