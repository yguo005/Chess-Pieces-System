package chess;

public class Knight extends AbstractChessPiece{
    public Knight(int row, int column, Color color){
        super(row, column, color);
    }

    /**
     *  A knight can move only in an L pattern: two cells horizontally and one vertically or vice versa.
     * @param row the row where the piece might be moved to.
     * @param col the column where the piece might be moved to.
     * @return
     */
    @Override
    public boolean canMove(int row, int col) {
        return Math.abs(getRow()-row)*Math.abs(getColumn()-col) == 2
                && !(Math.abs(getRow()-row)==0 && Math.abs(getColumn()-col)==0);
    }

    @Override
    public boolean canKill(ChessPiece piece) {
        return canMove(piece.getRow(), piece.getColumn()) && getColor()!=piece.getColor();
    }
}
