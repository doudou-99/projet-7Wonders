package modeles;


import java.io.Serializable;

public class Piece{
    private String type;
    private int valeur;
    private int nombrePieces;

    public Piece(){}

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

    public void setType(String type) {
        this.type = type;
    }

    public void setNombrePieces(int nombrePieces) {
        this.nombrePieces = nombrePieces;
    }
}
