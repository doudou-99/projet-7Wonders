package modeles;

import java.io.Serializable;

public class Banque implements Serializable {
    private final static long serialVersionUID=7L;
    private static int pieces=90;

    public static int getPieces() {
        return pieces;
    }

    public static void setPieces(int pieces) {
        modeles.Banque.pieces = pieces;
    }
}
