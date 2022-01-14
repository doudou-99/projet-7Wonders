package serveur;

import client.ServiceWonders;
import client.ServiceWondersImpl;

import javax.security.auth.callback.Callback;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import static client.ServiceWonders.SERVEUR;


public class Serveur {
    public static void main(String[] args){
        try {
            // Définition du port pour la communication RMI
            LocateRegistry.createRegistry(1099);

            // Création de l'instance du service qui va être embarqué dans le serveur RMI
            ServiceWonders serviceWonders = ServiceWondersImpl.creer();
            // Association de l'adresse à au service
            Naming.rebind(SERVEUR, serviceWonders);

            System.out.println("Serveur lancé");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
