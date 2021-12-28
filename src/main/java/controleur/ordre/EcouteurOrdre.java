package controleur.ordre;

public interface EcouteurOrdre {
    void setAbonnements(LanceurOrdre controleur);
    void broadCast(Ordre ordre);
}
