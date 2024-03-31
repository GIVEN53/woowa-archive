package ui.output;

import domain.board.ChessBoard;
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

public class BoardOutputView {
    private static final Map<Type, String> PIECE_DISPLAY = Map.of(
            Type.PAWN, "p",
            Type.KNIGHT, "n",
            Type.BISHOP, "b",
            Type.ROOK, "r",
            Type.QUEEN, "q",
            Type.KING, "k",
            Type.EMPTY, "."
    );

    private static final Map<Color, String> COLOR_DISPLAY = Map.of(
            Color.WHITE, "흰색",
            Color.BLACK, "검은색"
    );

    public void printStartMessage(boolean existsPlayingGame) {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 저장된 체스 게임을 확인 중입니다.");
        if (!existsPlayingGame) {
            System.out.println("> 플레이 중인 게임이 존재하지 않아 새로운 체스 게임을 생성합니다.");
        }
    }

    public void printCommandMessage() {
        System.out.println("> 기물 이동 : move source위치 target위치 - ex. move b2 b3");
        System.out.println("> 게임 상태 : status");
        System.out.println("> 게임 종료 : end - 종료 시 게임이 저장됩니다.");
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

    public void printKingCapturedMessage(Color winner) {
        System.out.printf("> %s이 승리했습니다.%n", COLOR_DISPLAY.get(winner));
    }

    public void printErrorMessage(Exception e) {
        System.out.println(e.getMessage());
    }
}
