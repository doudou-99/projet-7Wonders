module projet7Wonders {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires org.mongodb.bson;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.driver.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    opens example to javafx.fxml;
    opens modele.modele to javafx.base;
    exports application;


}
