module clientjfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires org.mongodb.bson;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.driver.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    requires modele;
    opens vues to javafx.fxml;
    exports vues;
    exports application;
    exports controleur;
    exports controleur.ordre;
}
