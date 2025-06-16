# Chess Pieces System

A comprehensive Java implementation of chess pieces with proper object-oriented design principles including interfaces, abstract classes, and inheritance hierarchies. The system models all six types of chess pieces with their unique movement patterns and capture rules.

## Overview

This project implements a complete chess piece system that accurately models the movement and capture rules of all chess pieces. The system uses interface-based design with abstract base classes to minimize code duplication while maintaining proper encapsulation and extensibility. Each piece follows official chess rules for movement and capture mechanics.

## Features

- Complete implementation of all six chess piece types
- Interface-based polymorphic design
- Abstract base class for shared functionality
- Accurate chess movement and capture rules
- Comprehensive input validation and error handling
- Full unit test coverage with JUnit 5
- Proper enum usage for piece colors
- Board coordinate validation (0-7 range)

## Project Structure

```
src/chess/
├── ChessPiece.java         # Main interface for all pieces
├── AbstractChessPiece.java # Abstract base class
├── Color.java              # Enum for piece colors
├── Bishop.java             # Bishop piece implementation
├── Knight.java             # Knight piece implementation
├── Rook.java              # Rook piece implementation
├── Queen.java             # Queen piece implementation
├── King.java              # King piece implementation
└── Pawn.java              # Pawn piece implementation

test/chess/
└── TestChessPiece.java    # Comprehensive unit tests
```

## Architecture

### Interface Design

The system uses the `ChessPiece` interface to establish a common contract:

```java
public interface ChessPiece {
    int getRow();
    int getColumn();
    Color getColor();
    boolean canMove(int row, int col);
    boolean canKill(ChessPiece piece);
}
```

### Class Hierarchy

```
ChessPiece (interface)
    ↑
AbstractChessPiece (abstract class)
    ↑
    ├── Bishop
    ├── Knight
    ├── Rook
    ├── Queen
    ├── King
    └── Pawn
```

### Color Enumeration

```java
public enum Color {
    BLACK, WHITE
}
```

## Chess Pieces Implementation

### Abstract Base Class

The `AbstractChessPiece` class provides common functionality:

**Shared Attributes:**
- Row position (0-7, where 0 is bottom of board)
- Column position (0-7, where 0 is leftmost)
- Piece color (BLACK or WHITE)

**Common Validation:**
- Position validation ensuring coordinates are within board bounds
- Constructor throws `IllegalArgumentException` for invalid positions

**Constructor:**
```java
public AbstractChessPiece(int row, int column, Color color) {
    if (row < 0 || row > 7 || column < 0 || column > 7) {
        throw new IllegalArgumentException("Row and column must be [0-7]");
    }
    this.row = row;
    this.column = column;
    this.color = color;
}
```

### Bishop

Moves diagonally in any direction.

**Movement Rules:**
- Can move any number of squares diagonally
- Cannot move horizontally or vertically
- Cannot move to the same square

**Implementation Logic:**
```java
public boolean canMove(int row, int col) {
    return Math.abs(getRow() - row) == Math.abs(getColumn() - col) 
           && Math.abs(getRow() - row) != 0;
}
```

**Usage Example:**
```java
Bishop bishop = new Bishop(0, 2, Color.WHITE);
bishop.canMove(2, 4);  // Returns true - diagonal move
bishop.canMove(2, 2);  // Returns false - vertical move
```

### Knight

Moves in an L-shaped pattern.

**Movement Rules:**
- Moves exactly 2 squares in one direction and 1 square perpendicular
- Can jump over other pieces
- Cannot move to the same square

**Implementation Logic:**
```java
public boolean canMove(int row, int col) {
    return Math.abs(getRow() - row) * Math.abs(getColumn() - col) == 2
           && !(Math.abs(getRow() - row) == 0 && Math.abs(getColumn() - col) == 0);
}
```

**Usage Example:**
```java
Knight knight = new Knight(0, 1, Color.WHITE);
knight.canMove(2, 2);  // Returns true - L-shaped move
knight.canMove(2, 1);  // Returns false - straight move
```

### Rook

Moves horizontally or vertically.

**Movement Rules:**
- Can move any number of squares horizontally or vertically
- Cannot move diagonally
- Cannot move to the same square

**Implementation Logic:**
```java
public boolean canMove(int row, int col) {
    return (getColumn() == col && getRow() != row) || 
           (getRow() == row && getColumn() != col);
}
```

**Usage Example:**
```java
Rook rook = new Rook(0, 0, Color.WHITE);
rook.canMove(0, 2);  // Returns true - horizontal move
rook.canMove(2, 0);  // Returns true - vertical move
rook.canMove(2, 2);  // Returns false - diagonal move
```

### Queen

Combines rook and bishop movement patterns.

**Movement Rules:**
- Can move horizontally, vertically, or diagonally
- Can move any number of squares in chosen direction
- Cannot move to the same square

**Implementation Logic:**
```java
public boolean canMove(int row, int col) {
    return (getRow() == row || getColumn() == col || 
            Math.abs(getRow() - row) == Math.abs(getColumn() - col))
           && !(getRow() == row && getColumn() == col);
}
```

**Usage Example:**
```java
Queen queen = new Queen(0, 3, Color.WHITE);
queen.canMove(0, 5);  // Returns true - horizontal move
queen.canMove(2, 3);  // Returns true - vertical move
queen.canMove(2, 5);  // Returns true - diagonal move
```

### King

Moves one square in any direction.

**Movement Rules:**
- Can move exactly one square in any direction
- Cannot move to the same square
- Cannot move more than one square

**Implementation Logic:**
```java
public boolean canMove(int row, int col) {
    return Math.abs(getRow() - row) <= 1 && Math.abs(getColumn() - col) <= 1
           && !(Math.abs(getRow() - row) == 0 && Math.abs(getColumn() - col) == 0);
}
```

**Usage Example:**
```java
King king = new King(0, 4, Color.WHITE);
king.canMove(0, 5);  // Returns true - one square horizontal
king.canMove(1, 4);  // Returns true - one square vertical
king.canMove(2, 4);  // Returns false - two squares
```

### Pawn

Most complex piece with different rules for movement and capture.

**Movement Rules:**
- Moves forward only (direction depends on color)
- Can move 1 or 2 squares forward on first move
- Can move only 1 square forward on subsequent moves
- Cannot move to the same square

**Capture Rules:**
- Can only capture diagonally forward
- Cannot capture pieces of the same color

**Position Constraints:**
- White pawns: rows 1-7 (cannot start on row 0)
- Black pawns: rows 0-6 (cannot start on row 7)

**Implementation Logic:**
```java
public boolean canMove(int row, int col) {
    if (getColor() == Color.WHITE) {
        return (getRow() == 1 && (row - getRow() == 1 || row - getRow() == 2) && getColumn() == col)
               || (getRow() != 1 && row - getRow() == 1 && getColumn() == col);
    } else { // BLACK
        return (getRow() == 6 && (getRow() - row == 1 || getRow() - row == 2) && getColumn() == col)
               || (getRow() != 6 && getRow() - row == 1 && getColumn() == col);
    }
}
```

**Usage Example:**
```java
// White pawn
Pawn whitePawn = new Pawn(1, 1, Color.WHITE);
whitePawn.canMove(2, 1);  // Returns true - one square forward
whitePawn.canMove(3, 1);  // Returns true - two squares forward (first move)
whitePawn.canKill(new Pawn(2, 2, Color.BLACK));  // Returns true - diagonal capture

// Black pawn
Pawn blackPawn = new Pawn(6, 1, Color.BLACK);
blackPawn.canMove(5, 1);  // Returns true - one square forward
blackPawn.canMove(4, 1);  // Returns true - two squares forward (first move)
```

## Capture Mechanics

All pieces implement the `canKill` method with consistent logic:

```java
public boolean canKill(ChessPiece piece) {
    return canMove(piece.getRow(), piece.getColumn()) && 
           getColor() != piece.getColor();
}
```

**Capture Rules:**
- Piece must be able to move to target square
- Target piece must be of opposite color
- Cannot capture pieces of the same color

## Input Validation

### Constructor Validation

**Board Boundaries:**
```java
if (row < 0 || row > 7 || column < 0 || column > 7) {
    throw new IllegalArgumentException("Row and column must be [0-7]");
}
```

**Pawn-Specific Validation:**
```java
// White pawns cannot start on row 0
if (color == Color.WHITE && row < 1) {
    throw new IllegalArgumentException("row for white pawns must be >=1");
}

// Black pawns cannot start on row 7
if (color == Color.BLACK && row > 6) {
    throw new IllegalArgumentException("row for black pawns must be <=6");
}
```

### Error Handling Examples

```java
// Invalid board position
try {
    Knight knight = new Knight(9, 3, Color.WHITE);
} catch (IllegalArgumentException e) {
    System.out.println("Row out of range: " + e.getMessage());
}

// Invalid pawn position
try {
    Pawn pawn = new Pawn(0, 0, Color.WHITE);
} catch (IllegalArgumentException e) {
    System.out.println("Invalid pawn position: " + e.getMessage());
}
```

## Testing

### Test Coverage

The project includes comprehensive unit tests covering:

**Constructor Validation:**
- Valid piece creation
- Invalid position handling
- Pawn-specific position constraints

**Movement Testing:**
- Valid moves for each piece type
- Invalid moves rejection
- Edge cases and boundary conditions

**Capture Testing:**
- Valid captures of opposite color pieces
- Invalid same-color capture attempts
- Capture rule verification for each piece

**Sample Test Methods:**
```java
@Test
public void testBishop() {
    Bishop bishop = new Bishop(0, 2, Color.WHITE);
    assertTrue(bishop.canMove(2, 4));   // Diagonal move
    assertFalse(bishop.canMove(2, 2));  // Straight move
    assertTrue(bishop.canKill(new Pawn(2, 4, Color.BLACK)));  // Capture
    assertFalse(bishop.canKill(new Pawn(2, 4, Color.WHITE))); // Same color
}
```

### Running Tests

```bash
# Compile all files
javac -cp ".:junit5.jar" src/chess/*.java test/chess/*.java

# Run tests
java -cp ".:junit5.jar" org.junit.platform.console.ConsoleLauncher --scan-classpath
```

## Usage Examples

### Basic Piece Operations

```java
// Create pieces
Bishop bishop = new Bishop(2, 2, Color.WHITE);
Knight knight = new Knight(1, 1, Color.BLACK);

// Check positions
System.out.println("Bishop at: (" + bishop.getRow() + ", " + bishop.getColumn() + ")");
System.out.println("Bishop color: " + bishop.getColor());

// Test movements
boolean canMove = bishop.canMove(4, 4);  // Diagonal move
boolean canCapture = bishop.canKill(knight);  // Different colors
```

### Polymorphic Usage

```java
ChessPiece[] pieces = {
    new Bishop(0, 2, Color.WHITE),
    new Knight(0, 1, Color.WHITE),
    new Rook(0, 0, Color.WHITE),
    new Queen(0, 3, Color.WHITE),
    new King(0, 4, Color.WHITE),
    new Pawn(1, 0, Color.WHITE)
};

// Test all pieces polymorphically
for (ChessPiece piece : pieces) {
    System.out.println(piece.getClass().getSimpleName() + 
                      " at (" + piece.getRow() + ", " + piece.getColumn() + ")");
    System.out.println("Can move to (2,2): " + piece.canMove(2, 2));
}
```

### Game Scenario Example

```java
// Set up a simple chess scenario
ChessPiece whiteKing = new King(0, 4, Color.WHITE);
ChessPiece blackQueen = new Queen(7, 3, Color.BLACK);
ChessPiece whitePawn = new Pawn(1, 3, Color.WHITE);

// Check if queen can attack king
boolean queenThreatensKing = blackQueen.canKill(whiteKing);

// Check if pawn can block
boolean pawnCanBlock = whitePawn.canMove(2, 3);

System.out.println("Queen threatens king: " + queenThreatensKing);
System.out.println("Pawn can block: " + pawnCanBlock);
```

## Design Decisions

### Interface-Based Design
- Provides common contract for all pieces
- Enables polymorphic usage
- Supports future extensibility

### Abstract Base Class
- Eliminates code duplication
- Provides shared validation logic
- Implements common interface methods

### Enum for Colors
- Type-safe color representation
- Prevents invalid color values
- Improves code readability

### Protected Access Modifiers
- Allows subclass access to position data
- Maintains encapsulation from external classes
- Supports inheritance-based design

## Performance Considerations

- Constant-time position access through direct field access
- Efficient movement validation using mathematical calculations
- Minimal object creation overhead
- Direct coordinate comparison for capture validation

## Extension Points

The system can be easily extended with:

1. **Additional Piece Types:** Implement ChessPiece interface
2. **Special Moves:** Extend movement logic for castling, en passant
3. **Board Representation:** Add full board state management
4. **Game Logic:** Implement complete chess game rules
5. **Move History:** Add move tracking and undo functionality

