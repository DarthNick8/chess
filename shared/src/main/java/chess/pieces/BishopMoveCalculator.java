package chess.pieces;

import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessPiece;
import chess.ChessPosition;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BishopMoveCalculator {
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position) {
        return calculateBishopMoves(board, position);
    }

    private Collection<ChessMove> calculateBishopMoves(ChessBoard board, ChessPosition position) {
        List<ChessMove> validMoves = new ArrayList<>();

        // Define the possible directions a bishop can move (diagonal)
        int[][] directions = { {-1, -1}, {-1, 1}, {1, -1}, {1, 1} };

        // Iterate over each direction
        for (int[] direction : directions) {
            int row = position.getRow() + direction[0];
            int col = position.getColumn() + direction[1];

            // Continue moving in the current direction until the edge of the board is reached
            while (isValidPosition(row, col)) {
                ChessPiece pieceAtPosition = board.getPiece(new ChessPosition(row, col));

                if (pieceAtPosition == null) {
                    // Empty square, add a valid move
                    validMoves.add(new ChessMove(position, new ChessPosition(row, col), null));
                } else {
                    // Square occupied by a piece
                    if (pieceAtPosition.isOpponent(board.getPiece(position))) {
                        // Opponent's piece, add a valid capture move
                        validMoves.add(new ChessMove(position, new ChessPosition(row, col), null));
                    }
                    break;  // Stop further moves in this direction
                }

                // Move to the next square in the current direction
                row += direction[0];
                col += direction[1];
            }
        }
        return validMoves;
    }

    private boolean isValidPosition(int row, int col) {
        // Adjusted to match 0-based indexing used in the ChessPosition class
        return row >= 0 && row <= 7 && col >= 0 && col <= 7;
    }
}
