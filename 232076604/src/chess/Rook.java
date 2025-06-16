package chess;

public class Rook extends AbstractChessPiece{
    public Rook(int row, int column, Color color){
        super(row, column,color);
    }

    /**
     *  rook can move horizontally or vertically
     * @param row the row where the piece might be moved to.
     * @param col the column where the piece might be moved to.
     * @return
     */
    @Override
    public boolean canMove(int row, int col) {
        return (getColumn() == col && getRow() !=row)||(getRow() == row && getColumn()!= col);
    }

    @Override
    public boolean canKill(ChessPiece piece) {
        return canMove(piece.getRow(), piece.getColumn()) && getColor()!=piece.getColor();
    }
}
