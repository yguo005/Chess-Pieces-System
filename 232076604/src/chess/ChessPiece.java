package chess;


/**
 * Interface for every piece of a chess game: King, queen, bishop, knight, rook and pawn.
 */
public interface ChessPiece {

    /**
     * Return the current row of the chess piece.
     *
     * @return the row of the current piece.
     */
    int getRow();

    /**
     * Return the current column of the chess piece.
     *
     * @return the column of the current chess piece.
     */
    int getColumn();

    /**
     * Get the color of the chess piece.
     *
     * @return the color of the chess piece (black or white).
     */
    Color getColor();

    /**
     * Determine if the chess piece can move to a given cell.
     *
     * @param row the row where the piece might be moved to.
     * @param col the column where the piece might be moved to.
     * @return True if the piece can move to that location. False otherwise.
     */
    boolean canMove(int row, int col);

    /**
     * Determine of the piece can kill another piece starting from its current location.
     * @param piece the piece that might be killed.
     * @return True if the new piece can be killed.
     */
    boolean canKill(ChessPiece piece);

}
