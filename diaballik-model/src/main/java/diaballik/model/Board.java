package diaballik.model;

import java.util.ArrayList;

public class Board {

    private Piece currentWhiteHolder;
    private Piece currentBlackHolder;
    private ArrayList<Piece> pieces;

    public Board() {

    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public Piece getPiece(final int x, final int y) {
        return null;
    }

    public Piece getBall(final Color color) {
        final Piece p;
        if (color == Color.WHITE) {
            p = currentWhiteHolder;
        } else {
            p = currentBlackHolder;
        }
        return p;
    }

    public Piece movePiece(final Piece p, final int x, final int y) {
        //mettre à jour la pièce et le tableau pieces
        return null;
    }

    public Piece moveBall(final Piece p) {
        // mettre à jour le holder ?
        return null;
    }

    public Color win() {
        // regarder si une piece d'une couleur est dans le camp adverse, return la couleur , null sinon
        return null;
    }

}
