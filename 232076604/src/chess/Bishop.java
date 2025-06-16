package chess;


public class Bishop extends AbstractChessPiece{
    public Bishop(int row, int column, Color color){
        super(row,column,color);
    }

    /**
     * A bishop can only move diagonally
     * @param row the row where the piece might be moved to.
     * @param col the column where the piece might be moved to.
     * @return
     */
    @Override
    public boolean canMove(int row, int col) {
        return Math.abs(getRow()-row) == Math.abs(getColumn()-col) // diagonal: the absolute difference between the current row and the target row should be equal to the absolute difference between the current column and the target column
                && Math.abs(getRow()-row)!=0;//not the same cell
    }

    /**
     * A bishop can kill if the target piece is on its diagonal path
     * and the target piece is of a different color
     * @param piece the piece that might be killed.
     * @return
     */
    @Override
    public boolean canKill(ChessPiece piece) {
        return canMove(piece.getRow(), piece.getColumn()) && getColor()!=piece.getColor();
    }
}
