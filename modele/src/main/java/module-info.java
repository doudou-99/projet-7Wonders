module modele {
    requires org.mongodb.driver.core;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.bson;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    exports modeles.exceptions;
    exports modeles.interfaces;
    exports modeles.facade;
    exports modeles.dao;
    exports modeles.dataencryption;
    exports modeles;

}
