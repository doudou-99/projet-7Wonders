module modele {
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    requires org.mongodb.bson;
    requires org.mongodb.driver.core;
    requires org.mongodb.driver.sync.client;
    exports modeles;
    exports modeles.dataencryption;
    exports modeles.facade;
    exports modeles.interfaces;
    exports modeles.exceptions;
}
