package chess;

public abstract class AbstractChessPiece implements ChessPiece{
    protected int row;//protected: these variables can be directly accessed in any class that extends AbstractChessPiece
    protected int column;
    protected Color color;

    /**
     * each chess piece should have a constructor that takes in an initial position as a row and column,
     * and a color as an enum Color with values BLACK and WHITE
     */
    public AbstractChessPiece(int row, int column, Color color){
        if (row < 0 || row >7 || column <0 || column >7){
            throw new IllegalArgumentException("Row and column must be [0-7]");
        }
        this.row=row;
        this.column=column;
        this.color=color;
    }

    @Override
    public int getRow() {
        return this.row;
    }

    @Override
    public int getColumn() {
        return this.column;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public abstract boolean canMove(int row, int col);//abstract method, as their implementations should be provided by subclasses

    @Override
    public abstract boolean canKill(ChessPiece piece);


}
