module com.ua.kva {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires java.xml;


    opens com.ua.tpm to javafx.fxml;
    opens com.ua.tpm.application to javafx.fxml;
    opens com.ua.tpm.game to javafx.fxml;
    opens com.ua.tpm.history to javafx.fxml;
    opens com.ua.tpm.mode to javafx.fxml;
    opens com.ua.tpm.settings to javafx.fxml;
    exports com.ua.tpm;
    exports com.ua.tpm.application;
    exports com.ua.tpm.game;
    exports com.ua.tpm.history;
    exports com.ua.tpm.mode;
    exports com.ua.tpm.settings;
}