package com.ua.tpm.settings;

import com.ua.tpm.application.AppController;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;

public class SettingsViewController extends AppController implements Initializable {

    /*Посилання на елементи інтерфейсу*/
    @FXML
    private ColorPicker colorPicker;
    /*CRUD дії з xml файлом, тобто створення (create), читання (read), оновлення (update), видалення (delete).
    Більш детально на етапі реалізації історії ігор*/
    private DOMManager2 domm;

    @FXML
    public void handleButtonActionSetColor(ActionEvent event) {

        String str = colorPicker.getValue().toString();

        /*Дописуємо у файл або створюємо новий. Більш детальніше на етапі історії ігор*/
        domm = new DOMManager2("settings", "-fx-background-color: #" + str.charAt(2) + str.charAt(3) +
                str.charAt(4) + str.charAt(5) + str.charAt(6) + str.charAt(7));

        File file = new File("settings.xml");
        domm.createXml(file);
    }

    /*Цей обробник відкриває головне меню*/
    @FXML
    public void handleButtonActionBack(ActionEvent event) {

        /*Так як кнопка знаходиться у меню, то вона не має доступу до вікна. Використовуємо посилання на контейнер для
        знаходження вікна*/
        colorPicker.getScene().getWindow().hide();

        /*Відкриваємо головний вид*/
        handleButtonActionMain(event);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) { }
}