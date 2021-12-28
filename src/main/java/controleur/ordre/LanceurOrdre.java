package controleur.ordre;

public interface LanceurOrdre {
    void abonnement(EcouteurOrdre o, Ordre.OrdreType... types);

    void fireOrdre(Ordre ordre);
}
