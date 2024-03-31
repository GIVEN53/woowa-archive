package view;

import static view.Command.END;
import static view.Command.MOVE;
import static view.Command.START;

import domain.board.ChessBoard;
import domain.game.GameResult;
import domain.piece.Color;
import domain.piece.Empty;
import domain.piece.Piece;
import domain.piece.Type;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final Map<Type, String> PIECE_DISPLAY = Map.of(
            Type.PAWN, "p",
            Type.KNIGHT, "n",
            Type.BISHOP, "b",
            Type.ROOK, "r",
            Type.QUEEN, "q",
            Type.KING, "k",
            Type.EMPTY, "."
    );

    public void printStartingMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.printf("> 게임 시작 : %s%n", START.getName());
        System.out.printf("> 게임 종료 : %s%n", END.getName());
        System.out.printf("> 게임 이동 : %s source위치 target위치 - 예. %s b2 b3%n", MOVE.getName(), MOVE.getName());
    }

    public void printBoard(ChessBoard chessBoard) {
        Map<Position, Piece> board = chessBoard.getPositionAndPieces();
        Arrays.stream(Rank.values())
                .map(rank -> generatePieceDisplay(board, rank))
                .forEach(System.out::println);
        System.out.println();
    }

    private String generatePieceDisplay(Map<Position, Piece> board, Rank rank) {
        return Arrays.stream(File.values())
                .map(file -> new Position(file, rank))
                .map(position -> board.getOrDefault(position, Empty.create()))
                .map(this::pieceDisplay)
                .collect(Collectors.joining());
    }

    private String pieceDisplay(Piece piece) {
        String pieceName = PIECE_DISPLAY.get(piece.type());
        if (piece.isSameColor(Color.BLACK)) {
            return pieceName.toUpperCase();
        }
        return pieceName;
    }

    public void printStatus(GameResult gameResult) {
        System.out.println("> 게임 결과");
        System.out.printf("> 흰색 점수 : %.1f%n", gameResult.calculateScore(Color.WHITE));
        System.out.printf("> 검은색 점수 : %.1f%n", gameResult.calculateScore(Color.BLACK));
    }

    public void printKingCapturedMessage(Color winner) {
        System.out.printf("> %s이 승리했습니다.%n", generateWinnerDisplay(winner));
    }

    private String generateWinnerDisplay(Color winner) {
        if (winner.isWhite()) {
            return "흰색";
        }
        return "검은색";
    }

    public void printErrorMessage(Exception e) {
        System.out.println(e.getMessage());
    }
}
