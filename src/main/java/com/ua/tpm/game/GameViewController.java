package com.ua.tpm.game;

import com.ua.tpm.application.AppController;
import com.ua.tpm.mode.ModeViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/*Клас який контролює інтерфейс гри. Розширює клас AppController*/
public class GameViewController extends AppController implements Initializable {

    /*Витягуємо вибраний режим, який вибрав користувач*/
    private final NumberGuessGame game = ModeViewController.getMode();

    /*Посилання на елементи інтерфейсу*/
    @FXML
    Label answer, attempts;
    @FXML
    Button accept;
    @FXML
    TextField tf;

    /*Метод Запускає нову гру*/
    @FXML
    public void handleButtonActionRestart(ActionEvent e) {

        game.newGame(tf, accept, answer, attempts);
    }

    @FXML
    public void handleButtonActionBack(ActionEvent e) {

        attempts.getScene().getWindow().hide();
        handleButtonActionMode(e);
    }

    @FXML
    public void handleButtonActionMenu(ActionEvent e) {

        attempts.getScene().getWindow().hide();
        handleButtonActionMain(e);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        /*Запускаємо гру при відкритті інтерфейсу*/
        game.newGame(tf, accept, answer, attempts);
    }
}

