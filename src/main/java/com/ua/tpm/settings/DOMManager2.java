package com.ua.tpm.settings;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class DOMManager2 {

    /*Ім'я головного контейнеру*/
    private String root;
    /*Ім'я нащадків головного контейнера. Міститиме 3 нащадки*/
    private String style;
    /*Посилання на файл, з яким зараз працює менеджер*/
    private File file;

    /*Конструктор без параметрів*/
    public DOMManager2() {
        this.style = "Style";
    }

    public DOMManager2(String root, String style) {
        this.root = root;
        this.style = style;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    /*Метод створює Xml файл*/
    public void createXml(File file) {

        file.delete();
        try {

            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

            Document document = documentBuilder.newDocument();

            // root element
            Element rootElem = document.createElement("settings");
            document.appendChild(rootElem);

            rootElem.setAttribute(root, style);

            // create the xml file
            //transform the DOM Object to an XML File
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File("settings.xml"));

            transformer.transform(domSource, streamResult);

        } catch (ParserConfigurationException | TransformerException ex) {
            Logger.getLogger(DOMManager2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String readXml(File file) {

        String data = null;
        try {

            /*Перетворюємо Xml файл в об'єкт документу*/
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);

            /*Нормалізуємо вигляд документу*/
            document.getDocumentElement().normalize();

            /*Зберігаємо файл, з яким працюємо*/
            setFile(file);

            data = document.getDocumentElement().getAttribute("settings");
        }
        catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(DOMManager2.class.getName()).log(Level.SEVERE, null, ex);
        }

        catch (Exception ex) {
            Logger.getLogger(DOMManager2.class.getName()).log(Level.SEVERE, null, ex);
        }

        return data;
    }

    /*Метод видаляє файл*/
    public void deleteXml(File file) {
        file.delete();
    }
}
