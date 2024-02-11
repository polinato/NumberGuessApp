package com.ua.tpm.game;

import com.ua.tpm.history.DOMManager;
import com.ua.tpm.history.GameHistory;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

/*Клас перемикач. Має 3 гри та описує їх логіку.
Головний клас NumberGuessGame при створенні створює 3 допоміжні класи та має на них посилання, що дуже зручно для швидкої зміни режиму гри або початку нової.*/
public enum NumberGuessGame {

    /*Клас реалізує логіку гри вгадування числа від 0 до 10*/
    First("First") {

        /*Число яке ввів користувач*/
        private int number;

        /*Число яке загадав комп'ютер*/
        private int secretNumber;

        /*Лічильник спроб*/
        private int iterator = 1;

        /*Колекція моделі GameHistory. Розглянемо модель на етапі реалізації історії ігор*/
        private final ArrayList<GameHistory> ogh = new ArrayList<>();

        /*CRUD дії з xml файлом, тобто створення (create), читання (read), оновлення (update), видалення (delete).
        Більш детально на етапі реалізації історії ігор*/
        private DOMManager domm;

        /*Абстрактний метод класу батька, за допомогою поліморфізму змінюється разом з режимом. Користувач вводить число
        у текстове поле tf. Якщо це число, то воно порівнюється із загаданим числом та зараховується спроба. На 1 гру
        дається 7 спроб. Спроби записуються у мітку attempts. При вгадуванні числа або закінченні спроб - відповідь
        записується у мітку answer. Потім кнопка підтвердження стає неактивною,змушуючи користувача або вийти з гри або
        почати спочатку. Після того як кнопка стала неактивною результат записується до xml файлу*/
        @Override
        public void newGame(TextField tf, Button b, Label answer, Label attempts) {

            /*Попереднє очищення полів та лічильника*/
            iterator = clear(tf, b);

            /*Загадуємо випадкове число від 0 до 10*/
            secretNumber = randomNumber(10);

            /*попереднє повідомлення*/
            answer.setText("Загадано число от 0 до 10!");

            /*Виводимо спроби*/
            attempts.setText("Спроба: " + iterator + "/7");

            /*Починаємо гру*/
            game(b, tf, attempts, answer);
        }

        @Override
        protected void game(Button b, TextField tf, Label attempts, Label answer) {

            /*Встановлюємо на кнопку обробник подій ActionEvent. При натисканні на кнопку обробляється спроба вгадування*/
            b.setOnAction((ActionEvent event) -> {

                /*Перевіряємо чи ввели число і якщо так, то обробляємо запит*/
                if (isNumber(tf)) {

                    /*Перетворюємо текс у число*/
                    number = Integer.parseInt(tf.getText());

                    /*Обновляємо спробу*/
                    attempts.setText("Спроба: " + iterator + "/7");

                    /*Записуємо відповідь-підказку*/
                    answer.setText(answer(number, secretNumber));

                    /*Спроби закінчилися*/
                    if (iterator == 7) {

                        /*Вимикаємо кнопку*/
                        b.setDisable(true);

                        /*Говоримо користувачеві про невдачу*/
                        answer.setText("Спробуй ще");

                        /*Користувач вгадав*/
                    }
                    else if (answer.getText().equals("Вгадав!")) {
                        b.setDisable(true);
                    }

                    /*Збільшуємо спроби*/
                    iterator++;
                }
                else {
                    /*Якщо користувач ввів у поле не число*/
                    answer.setText("Це не число!");
                }

                /*Перевіряємо на завершення гри*/
                if (b.isDisable()) {

                    /*Записуємо кількість спроб*/
                    String tmp = attempts.getText();

                    /*Записуємо поточну дату*/
                    LocalDate date = LocalDate.now();

                    /*Додаємо у колекцію нову модель з даними: режим, спроби, дата*/
                    ogh.add(new GameHistory("від 0 до 10", tmp, date));

                    /*Дописуємо у файл або створюємо новий. Більш детальніше на етапі історії ігор*/
                    domm = new DOMManager(ogh, "games", "game");
                    File file = new File("history.xml");
                    domm.writeXml(file);
                }
            });
        }
    },

    Second("Second") {

        private int number;
        private int secretNumber;
        private int iterator = 1;
        private final ArrayList<GameHistory> ogh = new ArrayList<>();
        private DOMManager domm;

        @Override
        public void newGame(TextField tf, Button b, Label answer, Label attempts) {

            /*Попереднє очищення полів та лічильника*/
            iterator = clear(tf, b);

            /*Загадуємо випадкове число від 0 до 50*/
            secretNumber = randomNumber(50);

            /*попереднє повідомлення*/
            answer.setText("Загадано число от 0 до 50!");

            /*Виводимо спроби*/
            attempts.setText("Спроба: " + iterator + "/7");

            /*Починаємо гру*/
            game(b, tf, attempts, answer);
        }

        @Override
        protected void game(Button b, TextField tf, Label attempts, Label answer) {

            /*Встановлюємо на кнопку обробник подій ActionEvent. При натисканні на кнопку обробляється спроба вгадування*/
            b.setOnAction((ActionEvent event) -> {

                /*Перевіряємо чи ввели число і якщо так, то обробляємо запит*/
                if (isNumber(tf)) {

                    /*Перетворюємо текс у число*/
                    number = Integer.parseInt(tf.getText());

                    /*Обновляємо спробу*/
                    attempts.setText("Спроба: " + iterator + "/7");

                    /*Записуємо відповідь-підказку*/
                    answer.setText(answer(number, secretNumber));

                    /*Спроби закінчилися*/
                    if (iterator == 7) {

                        /*Вимикаємо кнопку*/
                        b.setDisable(true);

                        /*Говоримо користувачеві про невдачу*/
                        answer.setText("Спробуй ще");

                        /*Користувач вгадав*/
                    }
                    else if (answer.getText().equals("Вгадав!")) {
                        b.setDisable(true);
                    }

                    /*Збільшуємо спроби*/
                    iterator++;
                }

                else {
                    /*Якщо користувач ввів у поле не число*/
                    answer.setText("Це не число!");
                }

                /*Перевіряємо на завершення гри*/
                if (b.isDisable()) {

                    /*Записуємо кількість спроб*/
                    String tmp = attempts.getText();

                    /*Записуємо поточну дату*/
                    LocalDate date = LocalDate.now();

                    /*Додаємо у колекцію нову модель з даними: режим, спроби, дата*/
                    ogh.add(new GameHistory("від 0 до 50", tmp, date));

                    /*Дописуємо у файл або створюємо новий. Більш детальніше на етапі історії ігор*/
                    domm = new DOMManager(ogh, "games", "game");
                    File file = new File("history.xml");
                    domm.writeXml(file);
                }
            });
        }
    },

    Third("Third") {

        private int number;
        private int secretNumber;
        private int iterator = 1;
        private final ArrayList<GameHistory> ogh = new ArrayList<>();
        private DOMManager domm;

        @Override
        public void newGame(TextField tf, Button b, Label answer, Label attempts) {

            /*Попереднє очищення полів та лічильника*/
            iterator = clear(tf, b);

            /*Загадуємо випадкове число від 0 до 10*/
            secretNumber = randomNumber(100);

            /*попереднє повідомлення*/
            answer.setText("Загадано число от 0 до 100!");

            /*Виводимо спроби*/
            attempts.setText("Спроба: " + iterator + "/7");

            /*Починаємо гру*/
            game(b, tf, attempts, answer);
        }

        @Override
        protected void game(Button b, TextField tf, Label attempts, Label answer) {

            /*Встановлюємо на кнопку обробник подій ActionEvent. При натисканні на кнопку обробляється спроба вгадування*/
            b.setOnAction((ActionEvent event) -> {

                /*Перевіряємо чи ввели число і якщо так, то обробляємо запит*/
                if (isNumber(tf)) {

                    /*Перетворюємо текс у число*/
                    number = Integer.parseInt(tf.getText());

                    /*Обновляємо спробу*/
                    attempts.setText("Спроба: " + iterator + "/7");

                    /*Записуємо відповідь-підказку*/
                    answer.setText(answer(number, secretNumber));

                    /*Спроби закінчилися*/
                    if (iterator == 7) {

                        /*Вимикаємо кнопку*/
                        b.setDisable(true);

                        /*Говоримо користувачеві про невдачу*/
                        answer.setText("Спробуй ще");

                        /*Користувач вгадав*/
                    }

                    else if (answer.getText().equals("Вгадав!")) {

                        b.setDisable(true);
                    }

                    /*Збільшуємо спроби*/
                    iterator++;
                }

                else {
                    /*Якщо користувач ввів у поле не число*/
                    answer.setText("Це не число!");
                }

                /*Перевіряємо на завершення гри*/
                if (b.isDisable()) {

                    /*Записуємо кількість спроб*/
                    String tmp = attempts.getText();

                    /*Записуємо поточну дату*/
                    LocalDate date = LocalDate.now();

                    /*Додаємо у колекцію нову модель з даними: режим, спроби, дата*/
                    ogh.add(new GameHistory("від 0 до 100", tmp, date));

                    /*Дописуємо у файл або створюємо новий. Більш детальніше на етапі історії ігор*/
                    domm = new DOMManager(ogh, "games", "game");
                    File file = new File("history.xml");
                    domm.writeXml(file);
                }
            });
        }
    };

    /*Зберігаємо ім'я гри*/
    private final String game;

    /*Конструктор*/
    NumberGuessGame(String game) {
        this.game = game;
    }

    /*Щоб не писати у кожному класі одні і ті ж методи, реалізуємо їх у суперкласі, тобто у класі NumberGuessGame.
     Метод перевіряє текстове поле на правильність вводу*/
    private static boolean isNumber(TextField tf) {

        /*Якщо у полі є якісь символи*/
        if (tf.getText() != null || tf.getText().length() != 0) {

            try {

                /*Спробуємо перевести символи у число. Якщо не вдасться, то повернеться false*/
                int num = Integer.parseInt(tf.getText());

                /*Перевіряємо число, якщо все вірно, то повер-таємо true*/
                if (num <= Integer.MAX_VALUE && num >= 0) {
                    return true;
                }
            }

            catch (NumberFormatException nfe) {}
        }
        return false;
    }

    /*Метод який порівнює загадане число з введеним та дає ві-дповідь*/
    private static String answer(int number, int secretNumber) {

        if (number < secretNumber) {
            return "Число більше";
        }

        else if (number > secretNumber) {
            return "Число менше";
        }

        else if (number == secretNumber) {
            return "Вгадав!";
        }
        return "";
    }

    /*Метод очищає текстове поле та вмикає кнопку*/
    private static int clear(TextField tf, Button b) {

        tf.clear();
        b.setDisable(false);

        /*повертаємо лічильник у початкову позицію*/
        return 1;
    }

    /*метод повертає випадкове число в діапазоні від 0 до max*/
    private static int randomNumber(int max) {

        return new Random().nextInt(max);
    }

    public String isGame() {

        return game;
    }

    /*Абстрактні методи реалізуються в кожному класі та перевизначаються*/
    public abstract void newGame(TextField tf, Button b, Label answer, Label attempts);

    protected abstract void game(Button b, TextField tf, Label attempts, Label answer);
}

