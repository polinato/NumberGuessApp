package com.ua.tpm.history;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

/*Клас - модель даних. Роль в зберіганні об'єктів з певними даними. Один такий клас описує лише 1 історію*/
public class GameHistory {

    /*Дані історії: гра, спроба, дата. StringProperty краще піддається змінам*/
    private final StringProperty game;
    private final StringProperty attempt;
    private final ObjectProperty<LocalDate> date;

    /*Конструктори*/
    public GameHistory(String game, String attempt, LocalDate date) {

        this.game = new SimpleStringProperty(game);
        this.attempt = new SimpleStringProperty(attempt);
        this.date = new SimpleObjectProperty<>(date);
    }

    public GameHistory() {
        this(null, null, null);
    }

    // Гра. гетери, сетери, властивість
    public String getGame() {
        return game.get();
    }

    public void setGame(String game) {
        this.game.set(game);
    }

    public StringProperty GameProperty() {
        return game;
    }

    //Спроба. гетери, сетери, властивість
    public String getAttempt() {
        return attempt.get();
    }

    public void setAttempt(String attempt) {
        this.attempt.set(attempt);
    }

    public StringProperty AttemptProperty() {
        return attempt;
    }

    //Дата. гетери, сетери, властивість
    public LocalDate getDate() {
        return date.get();
    }

    public void setDate(LocalDate date) {
        this.date.set(date);
    }

    public ObjectProperty<LocalDate> DateProperty() {
        return date;
    }
}
