package com.ua.tpm.history;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory; //add dependency and in module-info requires
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/*Клас контролює файл xml*/
public class DOMManager {

    /*Зберігаємо список історії ігор*/
    private ArrayList<GameHistory> gameData;

    /*Ім'я головного контейнеру*/
    private String root;

    /*Ім'я нащадків головного контейнера. Міститиме 3 нащад-ки*/
    private String wrapper;

    /*Посилання на файл, з яким зараз працює менеджер*/
    private File file;

    /*Конструктор без параметрів*/
    public DOMManager() {

        this.gameData = new ArrayList<>();
        this.root = "Root";
        this.wrapper = "Wrapper";
    }

    public DOMManager(ArrayList<GameHistory> gameData, String root, String wrapper) {

        this.gameData = gameData;
        this.root = root;
        this.wrapper = wrapper;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public ArrayList<GameHistory> getGameData() {
        return gameData;
    }

    public void setGameData(ArrayList<GameHistory> gameData) {
        this.gameData = gameData;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public String getWrapper() {
        return wrapper;
    }

    public void setWrapper(String wrapper) {
        this.wrapper = wrapper;
    }

    /*Метод створює Xml файл*/
    public void createXml() {

        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            /*Створюємо конструктор Xml документу*/
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();

            /*Створюємо "уявлення" документу*/
            Document document = documentBuilder.newDocument();

            /*Створюємо головний та дочірній контейнери*/
            Element rootEl = document.createElement(root);
            Element wrapperEl = document.createElement(wrapper);

            /*Додаємо до документу головний контейнер*/
            /*Вигляд: */
            /*<root>*/
            /*</root>*/
            document.appendChild(rootEl);

            /*Заповнюємо даними документ. Після заповнення ма-тиме вигляд: де, root та wrapper - змінні*/
            /*<root>*/
            /*<wrapper>*/
            /*<game>text</game>*/
            /*<attempt>text</attempt>*/
            /*<date>text</date>*/
            /*wrapper*/
            /*..........................*/
            /*<wrapper>*/
            /*<game>text</game>*/
            /*<attempt>text</attempt>*/
            /*<date>text</date>*/
            /*wrapper*/
            /*</root>*/

            for (GameHistory gameData1 : gameData) {

                /*Створюємо контейнери*/
                Element game = document.createElement("game");
                Element attempt = document.createElement("attempt");
                Element date = document.createElement("date");

                /*заповнюємо контейнери текстом*/
                game.appendChild(document.createTextNode(gameData1.getGame()));
                attempt.appendChild(document.createTextNode(gameData1.getAttempt()));
                date.appendChild(document.createTextNode(gameData1.getDate().toString()));

                /*Створюємо ієрархію документу*/
                rootEl.appendChild(wrapperEl);
                wrapperEl.appendChild(game);
                wrapperEl.appendChild(attempt);
                wrapperEl.appendChild(date);
            }

            /*Класи які підготовляють "уявлення" до докумен-ту*/
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource doms = new DOMSource(document);

            /*Створюємо файл, якщо такого немає*/
            file = new File("history.xml");

            /*Перетворюємо документ у xml файл*/
            StreamResult streamResult = new StreamResult(file);
            transformer.transform(doms, streamResult);
        }

        catch (ParserConfigurationException | TransformerException ex) {

            Logger.getLogger(DOMManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*Метод працює як і метод createXml(), з різницею лише в тому, що спочатку зчитується старий файл та створюється список ArrayList<GameHistory>.
     Потім записуються у файл поточні ігри (ігри за сесію), а потім те, що було у файлі. Тобто файл перезаписується*/
    public void writeXml(File file) {

        if (file.exists()) {

            try {

                this.file = file;
                ArrayList<GameHistory> arrGH = readXml(file);

                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = factory.newDocumentBuilder();
                Document document = documentBuilder.newDocument();

                Element rootEl = document.createElement(root);
                Element wrapperEl = document.createElement(wrapper);

                document.appendChild(rootEl);

                for (GameHistory gameData1 : gameData) {
                    Element game = document.createElement("game");
                    Element attempt = document.createElement("attempt");
                    Element date = document.createElement("date");

                    game.appendChild(document.createTextNode(gameData1.getGame()));
                    attempt.appendChild(document.createTextNode(gameData1.getAttempt()));
                    date.appendChild(document.createTextNode(gameData1.getDate().toString()));
                    rootEl.appendChild(wrapperEl);

                    wrapperEl.appendChild(game);
                    wrapperEl.appendChild(attempt);
                    wrapperEl.appendChild(date);
                }

                for (GameHistory gameData1 : arrGH) {

                    Element game = document.createElement("game");
                    Element attempt = document.createElement("attempt");
                    Element date = document.createElement("date");

                    game.appendChild(document.createTextNode(gameData1.getGame()));
                    attempt.appendChild(document.createTextNode(gameData1.getAttempt()));
                    date.appendChild(document.createTextNode(gameData1.getDate().toString()));

                    rootEl.appendChild(wrapperEl);
                    wrapperEl.appendChild(game);
                    wrapperEl.appendChild(attempt);
                    wrapperEl.appendChild(date);
                }

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource doms = new DOMSource(document);

                StreamResult streamResult = new StreamResult(file);
                transformer.transform(doms, streamResult);
            }

            catch (ParserConfigurationException | TransformerException ex) {

                Logger.getLogger(DOMManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        else {
            createXml();
        }
    }

    /*Метод читає з Xml історію, перетворює її у вигляд об'єк-ту GameHistory та повертає список таких об'єктів*/
    public ArrayList<GameHistory> readXml(File file) {

        /*Створюємо пустий список*/
        ArrayList<GameHistory> data = new ArrayList<>();

        try {

            /*Перетворюємо Xml файл в об'єкт документу*/
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);

            /*Нормалізуємо вигляд документу*/
            document.getDocumentElement().normalize();

            /*Зберігаємо файл, з яким працюємо*/
            setFile(file);

            /*Зберігаємо головний контейнер*/
            setRoot(document.getDocumentElement().getNodeName());

            /*Перший дочірній елемент і буде обгорткою історії гри*/
            setWrapper(document.getDocumentElement().getFirstChild().getNodeName());

            /*Вибираємо тільки обгортки історії ігор*/
            NodeList list = document.getElementsByTagName(wrapper);

            /*Вибираємо дані з обгорток*/
            NodeList allChild;

            /*Форматує за патерном дату з рядка у об'єкт*/
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            /*Тимчасовий список даних*/
            ArrayList<String> arr_tmp_data = new ArrayList<>();
            allChild = list.item(0).getChildNodes();

            /*Заповнюємо тимчасовий список. необхідний, тому що неможливо використовувати NodeList у вигляді списку*/

            for (int i = 0; i < allChild.getLength(); i++) {

                String temp2 = allChild.item(i).getTextContent();
                arr_tmp_data.add(temp2);
            }

            /*Заповнюємо об'єкт історії*/
            for (int i = 0; i < arr_tmp_data.size(); i += 3) {

                /*Вибираємо назву режиму*/
                String game = arr_tmp_data.get(i);

                /*Вибираємо спроби*/
                String att = arr_tmp_data.get(i + 1);

                /*Вибираємо дату*/
                String date = arr_tmp_data.get(i + 2);

                /*Перетворюємо дату з рядка у об'єкт LocalDate*/
                LocalDate ld = LocalDate.parse(date, formatter);

                /*Додаємо об'єкт історії у список*/
                data.add(new GameHistory(game, att, ld));
            }
        }

        catch (ParserConfigurationException | SAXException | IOException ex) {

            Logger.getLogger(DOMManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        catch (Exception ex) {

            Logger.getLogger(DOMManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return data;
    }

    /*Метод видаляє файл*/
    public void deleteXml(File file) {
        file.delete();
    }
}
