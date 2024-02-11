package com.ua.tpm;

import com.ua.tpm.application.AppController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;

public class MainViewController extends AppController {

    /*Метод відкриває інтерфейс режиму гри*/
    @FXML
    public void handleButtonActionLoadMode(ActionEvent event) {

        /*Знаходимо джерело натискання, тобто кнопку.
        Знайдена кнопка "знає" на якій сцені вона знаходиться.
        В свою чергу сцена "знає" в якому вікні вона знаходиться.
        Коли ми все це знаходимо, то можемо закрити вікно, на якому знаходиться кнопка, методом hide()*/
        ((Node) (event.getSource())).getScene().getWindow().hide();
        handleButtonActionMode(event);//метод з класу батька - AppController
    }

    /*Метод відкриває інтерфейс історії ігор*/
    @FXML
    public void handleButtonActionLoadHistory(ActionEvent event) {

        ((Node) (event.getSource())).getScene().getWindow().hide();
        handleButtonActionHistory(event);
    }

    @FXML
    public void handleButtonActionLoadSettings(ActionEvent event) {

        ((Node) (event.getSource())).getScene().getWindow().hide();
        handleButtonActionSettings(event);
    }
}