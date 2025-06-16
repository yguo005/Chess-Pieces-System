package chess;

public class Queen extends AbstractChessPiece{
    public Queen(int row, int column, Color color){
        super(row, column, color);
    }

    /**
     * A queen can move horizontally, vertically and diagonally.
     * @param row the row where the piece might be moved to.
     * @param col the column where the piece might be moved to.
     * @return
     */
    @Override
    public boolean canMove(int row, int col) {
        return (getRow()==row || getColumn()==col || Math.abs(getRow()-row) == Math.abs(getColumn()-col))
                && !(getRow() == row && getColumn() == col);
    }//operator precedence: AND operator (&&) has higher precedence than the OR operator ||

    @Override
    public boolean canKill(ChessPiece piece) {
        return canMove(piece.getRow(), piece.getColumn()) && getColor() != piece.getColor();
    }
}
