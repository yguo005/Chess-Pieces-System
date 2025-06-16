package chess;

public class King extends AbstractChessPiece{
    public King(int row, int column, Color color){
        super(row, column, color);
    }

    /**
     * A king can move one square in any direction (horizontally, vertically, or diagonally).
     * @param row the row where the piece might be moved to.
     * @param col the column where the piece might be moved to.
     * @return
     */
    @Override
    public boolean canMove(int row, int col) {
        return Math.abs(getRow()-row)<=1 && Math.abs(getColumn()-col)<=1
                && !(Math.abs(getRow()-row )== 0 && Math.abs(getColumn()-col)==0 );
    }

    @Override
    public boolean canKill(ChessPiece piece) {
        return canMove(piece.getRow(), piece.getColumn()) && getColor()!=piece.getColor();
    }
}
