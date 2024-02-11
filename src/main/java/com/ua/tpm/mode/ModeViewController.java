package com.ua.tpm.mode;

import com.ua.tpm.application.AppController;
import com.ua.tpm.game.NumberGuessGame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/*Контролер розширяє AppController. Відповідає за контроль над інтерфейсом режимів гри*/
public class ModeViewController extends AppController {

    /*Статична змінна буде доступна у іншому класі. Це дасть змогу додатку «зрозуміти», який саме режим потрібно вибрати*/
    private static NumberGuessGame mode;

    /*Команду ((Node)(event.getSource())).getScene().getWindow().hide();
    не можемо використовувати для закривання вікна, тому що кноп-ки, на які натискає користувач,
     знаходяться у меню, яке не має доступу до вікна, тому створюємо посилання на елемент контейнеру.*/
    @FXML
    private VBox vbox;

    /*Метод повертає вибраний режим*/
    public static NumberGuessGame getMode() {
        return mode;
    }

    /*Обробник подій, який знаходить кнопку, на яку натиснув користувач за її ідентифікатором стилю.
     Цей обробник зроблений так, щоб не створювати не потрібні обробники подій, які виконують майже одну й ту саму дію.*/
    @FXML
    private void handleButtonActionGameMode(ActionEvent event) {

        /*Визначаємо кнопку, на яку натиснули*/
        Button source = (Button) event.getSource();

        /*далі оператор switch переходить на потрібний case, якщо ідентифікатор співпадає*/
        switch (source.getId()) {

            case "button1":
                /*Встановлюємо перший режим гри*/
                mode = NumberGuessGame.First;
                break;

            case "button2":
                /*Встановлюємо другий режим гри*/
                mode = NumberGuessGame.Second;
                break;

            case "button3":
                /*Встановлюємо третій режим гри*/
                mode = NumberGuessGame.Third;
                break;
        }

        /*Так як кнопка, на яку натиснули, знаходиться не у меню, то вона має доступ до вікна*/
        ((Node) (event.getSource())).getScene().getWindow().hide();

        /*відкриваємо вікно гри*/
        handleButtonActionGame(event);
    }

    /*Цей обробник відкриває головне меню*/
    @FXML
    public void handleButtonActionBack(ActionEvent event) {

        /*Так як кнопка знаходиться у меню, то вона не має до-ступу до вікна. Використовуємо посилання на контейнер для зна-ходження вікна*/
        vbox.getScene().getWindow().hide();

        /*Відкриваємо головний вид*/
        handleButtonActionMain(event);
    }
}