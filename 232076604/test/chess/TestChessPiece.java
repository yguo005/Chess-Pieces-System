package chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestChessPiece {
    private AbstractChessPiece bishop, knight, rook, queen, king, whitePawn, blackPawn;
    @BeforeEach
    public void setUp(){
        bishop=new Bishop(0,2,Color.WHITE);
        knight =new Knight(0,1,Color.WHITE);
        rook=new Rook(0,0,Color.WHITE);
        queen = new Queen(0,3,Color.WHITE);
        king = new King(0,4,Color.WHITE);
        whitePawn = new Pawn(1,1,Color.WHITE);
        blackPawn = new Pawn(6,1,Color.BLACK);
    }
    @Test
    public void testInvalidConstructor(){
        assertThrows(IllegalArgumentException.class,()->{
            new Knight(9,3, Color.WHITE);//row out of range
        });
        assertThrows(IllegalArgumentException.class,()->{
            new Knight(3,-1,Color.WHITE);//column out of range
        });
    }
    @Test
    public void testBishop(){
        Bishop sameBishop = new Bishop(4,4,Color.WHITE);
        assertFalse(sameBishop.canMove(4,4));//same cell

        assertTrue(bishop.canMove(2,4));//move diagonal
        assertFalse(bishop.canMove(2,2));//move straight
        assertTrue(bishop.canKill(new Pawn(2,4,Color.BLACK)));//White can kill black piece
        assertFalse(bishop.canKill(new Pawn(2,4,Color.WHITE)));
    }
    @Test
    public void testKnight(){
        Knight sameKnight = new Knight(4,4,Color.WHITE);
        assertFalse(sameKnight.canMove(4,4));

        assertTrue(knight.canMove(2,2));//move in L shape
        assertFalse(knight.canMove(2,1));//move straight
        assertTrue(knight.canKill(new Pawn(2,2,Color.BLACK)));
        assertFalse(knight.canKill(new Pawn(2,2,Color.WHITE)));
    }
    @Test
    public void testRook(){
        Rook sameRook = new Rook(4,4,Color.WHITE);
        assertFalse(sameRook.canMove(4,4));

        assertTrue(rook.canMove(0,2));//move horizontal
        assertTrue(rook.canMove(2,0));//move vertical
        assertFalse(rook.canMove(2,2));//diagonal
        assertTrue(rook.canKill(new Pawn(1,0,Color.BLACK)));
        assertFalse(rook.canKill(new Pawn(1,0,Color.WHITE)));
    }
    @Test
    public void testQueen(){
        Queen sameQueen = new Queen(4,4,Color.WHITE);
        assertFalse(sameQueen.canMove(4,4));

        assertTrue(queen.canMove(0,5));//move horizon
        assertTrue(queen.canMove(2,3));//move vertical
        assertTrue(queen.canMove(2,5));//diagonal
        assertFalse(queen.canMove(2,4));//L shape
        assertTrue(queen.canKill(new Pawn(1,3,Color.BLACK)));
        assertFalse(queen.canKill(new Pawn(1,3,Color.WHITE)));
    }
    @Test
    public void testKing(){
        King sameKing = new King(4,4,Color.WHITE);
        assertFalse(sameKing.canMove(4,4));

        assertTrue(king.canMove(0,5));//move horizontal by one square
        assertTrue(king.canMove(1,4));//move vertical by one square
        assertTrue(king.canMove(1,5));//move diagonal by one square
        assertFalse(king.canMove(2,4));//move horizontal by 2 squares
        assertTrue(king.canKill(new Pawn(1,4,Color.BLACK)));
        assertFalse(king.canKill(new Pawn(1,4,Color.WHITE)));
    }
    @Test
    public void testPawn(){
        try {
            new Pawn(0,0,Color.WHITE);
            fail("Expectecd an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e){
            assertEquals("row for white pawns must be >=1", e.getMessage());
        }
        try {
            new Pawn(7,0, Color.BLACK);
            fail("Expectecd an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e){
            assertEquals("row for black pawns must be <=6", e.getMessage());
        }

        Pawn samePawn = new Pawn(1,1,Color.WHITE);
        assertFalse(samePawn.canMove(1,1));

        //test white pawn first move
        assertTrue(whitePawn.canMove(2,1));//move 1 cell forward
        assertTrue(whitePawn.canMove(3,1));//move 2 cells forward
        assertFalse(whitePawn.canMove(4,1));//move more than 2 forward

        //test white pawn's subsequent move
        whitePawn = new Pawn(2,1,Color.WHITE);
        assertTrue(whitePawn.canMove(3,1));//move 1 cell forward
        assertFalse(whitePawn.canMove(4,1));//move more than 1 cell forward
        assertTrue(whitePawn.canKill(new Pawn(3,2,Color.BLACK)));//kill diagonally forward
        assertFalse(whitePawn.canKill(new Pawn(3,2,Color.WHITE)));

        //test black pawn initial move
        assertTrue(blackPawn.canMove(5,1));//move 1 cell forward
        assertTrue(blackPawn.canMove(4,1));//move 2 cells forward
        assertFalse(blackPawn.canMove(3,1));//move more than 2 cells

        //test black pawn subsequent move
        blackPawn=new Pawn(5,1,Color.BLACK);
        assertTrue(blackPawn.canMove(4,1));//move 1 cell forward
        assertFalse(blackPawn.canMove(3,1));//move more than 1 cell
        assertFalse(blackPawn.canKill(new Pawn(4,2,Color.BLACK)));
        assertTrue(blackPawn.canKill(new Pawn(4,2,Color.WHITE)));

    }
}


