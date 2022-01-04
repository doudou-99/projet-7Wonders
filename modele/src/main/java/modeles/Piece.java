package modeles;

import modeles.enums.TypePiece;

public class Piece {
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
