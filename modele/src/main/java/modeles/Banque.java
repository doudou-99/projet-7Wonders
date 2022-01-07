package modeles;

public class Banque {
    private static int pieces=90;

    public static int getPieces() {
        return pieces;
    }

    public static void setPieces(int pieces) {
        modeles.Banque.pieces = pieces;
    }
}
