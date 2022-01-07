package modeles;


import java.io.Serializable;

public class Piece implements Serializable {
    private static final long serialVersionUID=1L;
    private String type;
    private int valeur;
    private int nombrePieces;

    public Piece(int piece, String type) {
        this.valeur = piece;
        this.type=type;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public int getNombrePieces() {
        return nombrePieces;
    }

    public String getType() {
        return type;
    }
}
