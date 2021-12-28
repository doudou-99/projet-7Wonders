package modele;

import modele.enums.TypePiece;

public class Piece {
    private final static int nombreMax=70;
    private final static int nombreMin=3;
    private TypePiece type;
    private int piece;

    public Piece(int piece, String type) {
        this.piece = piece;
        this.type=TypePiece.valueOf(type);
    }

    public int getPiece() {
        return piece;
    }

    public void setPiece(int piece) {
        this.piece = piece;
    }
}
