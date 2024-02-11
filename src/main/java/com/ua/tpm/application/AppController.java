package com.ua.tpm.application;

import com.ua.tpm.settings.DOMManager2;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.IOException;

public class AppController {

    @FXML
    DOMManager2 domm = new DOMManager2();

    @FXML
    public void handleButtonActionExit(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    /*Модифікатор protected дозволяє використовувати метод всім нащадкам і більше нікому.
    Звичайно в рамках самого класу також можна використовувати. Метод відкриває головний інтерфейс*/
    protected void handleButtonActionMain(ActionEvent event) {
        createWindow("/com/ua/tpm/application/MainView.fxml");
    }

    @FXML
    /*Метод відкриває інтерфейс режимів гри*/
    protected void handleButtonActionMode(ActionEvent event) {
        createWindow("/com/ua/tpm/mode/ModeView.fxml");
    }

    @FXML
    /*Метод відкриває інтерфейс історії ігор*/
    protected void handleButtonActionHistory(ActionEvent event) {
        createWindow("/com/ua/tpm/history/HistoryView.fxml");
    }
    @FXML
    public void handleButtonActionSettings(ActionEvent event) {
        createWindow("/com/ua/tpm/settings/SettingsView.fxml");
    }

    @FXML
    /*Метод відкриває інтерфейс гри*/
    protected void handleButtonActionGame(ActionEvent event) {
        createWindow("/com/ua/tpm/game/GameView.fxml");
    }

    /*Метод створює нове вікно за шляхом до інтерфейсу*/
    protected void createWindow(String str) {

        try {

            /*Завантажуємо інтерфейс*/
            Parent root = FXMLLoader.load(getClass().getResource(str));
            root.setStyle(domm.readXml(new File("settings.xml")) + ';');

            /*Створюємо нове вікно*/
            Stage newStage = new Stage();

            /*Створюємо сцену з інтерфейсу*/
            newStage.setScene(new Scene(root, 240, 290));

            /*Вікно не може змінювати свій розмір*/
            newStage.setResizable(false);

            /*Показуємо створене вікно*/
            newStage.show();

        }

        catch (IOException ex) {

            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
