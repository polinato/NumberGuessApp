package com.ua.tpm.history;

import com.ua.tpm.application.AppController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/*Клас показує історію при її відкритті. Розширяє AppController*/
public class HistoryViewController extends AppController implements Initializable {

    private final DOMManager domm = new DOMManager();
    private final File file = new File("history.xml");

    /*Зберігаємо посилання на елемент ListView, менеджер та файл*/
    @FXML
    private ListView listview;

    @FXML
    public void handleButtonActionBack(ActionEvent e) {

        listview.getScene().getWindow().hide();
        handleButtonActionMain(e);
    }

    @FXML
    public void handleButtonActionDeletehistory(ActionEvent e) {

        listview.getItems().clear();
        domm.deleteXml(file);
    }

    /*Коли створюється цей контролер або відкривається то перш за все він завантажує дані з файлу у елемент listview*/
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        /*listview не можна редагувати*/
        listview.setEditable(false);

        /*Перевіряємо файл на наявність. Якщо є, то читаємо файл та заповнюємо listview. Якщо немає, то нічого не робимо. listview буде пустим. Помилки не буде*/
        if (file.exists()) {

            ArrayList<GameHistory> arrGH = domm.readXml(file);
            ObservableList<String> ogh = FXCollections.observableArrayList();

            arrGH.stream().map((s) -> "Режим: " + s.getGame() + ", " + s.getAttempt() + ", " + "Дата: " + s.getDate().toString()).forEach((tmp) -> {
                ogh.add(tmp);
            });

            listview.setItems(ogh);
        }
    }
}
