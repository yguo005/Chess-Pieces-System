package chess;

public class Pawn extends AbstractChessPiece {
    public Pawn(int row, int column, Color color) {
        super(row, column, color);
        if (color==Color.WHITE && row < 1){
            throw new IllegalArgumentException("row for white pawns must be >=1");
        }
        else if (color==Color.BLACK && row > 6){
            throw new IllegalArgumentException("row for black pawns must be <=6");
        }
    }

    /**
     * it can move only “ahead,”  where its color started.
     * It can move only one place forward in its own column
     * (except the first time it moves: it can move one or two places forward).
     *
     * @param row the row where the piece might be moved to.
     * @param col the column where the piece might be moved to.
     * @return
     */
    @Override
    public boolean canMove(int row, int col) {
        if (getColor() == Color.WHITE) {
            return (getRow() == 1 && (row - getRow() == 1 || row - getRow() == 2) && getColumn() == col)//if it's pawn's first move, it can move one or 2 cell forward
                    || (getRow() != 1 && row - getRow() == 1 && getColumn() == col)
                    && !(getColumn() == col && getRow() == row);//not the same cell
        } else {//BLACK
            return (getRow() == 6 && (getRow() - row == 1 || getRow() - row == 2) && getColumn() == col)
                    || (getRow() != 6 && getRow() - row == 1 && getColumn() == col)
                    && !(getRow()==row && getColumn()==col);
        }
    }

    /**
     * to kill it must move one place forward diagonally
     *
     * @param piece the piece that might be killed.
     * @return
     */
    @Override
    public boolean canKill(ChessPiece piece) {
        if (getColor() == piece.getColor()) {
            return false;
        }
        if (getColor() == Color.WHITE) {
            return piece.getRow() - getRow() == 1 && Math.abs(getColumn() - piece.getColumn()) == 1;//piece.getRow() is the target position, getRow() is current position
        } else {
            return getRow() - piece.getRow() == 1 && Math.abs(getColumn() - piece.getColumn()) == 1;//BLACK
        }
    }
}
