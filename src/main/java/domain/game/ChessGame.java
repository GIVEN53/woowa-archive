package domain.game;

import domain.board.ChessBoard;
import domain.board.ChessBoardFactory;
import domain.game.state.GameState;
import domain.game.state.Prepare;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;

public class ChessGame {
    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;

    private int gameId; // todo final
    private GameState state;

    public ChessGame() { // todo 삭제
        ChessBoard chessBoard = ChessBoardFactory.createInitialChessBoard();
        this.state = new Prepare(chessBoard);
    }

    public ChessGame(int gameId, GameState state) {
        this.gameId = gameId;
        this.state = state;
    }

    public void start() {
        this.state = state.start();
    }

    public void move(MovePosition movePosition) {
        Position sourcePosition = createPosition(movePosition.source());
        Position targetPosition = createPosition(movePosition.target());
        this.state = state.move(sourcePosition, targetPosition);
    }

    private Position createPosition(String source) {
        File file = File.fromName(source.charAt(FILE_INDEX));
        Rank rank = Rank.fromNumber(source.charAt(RANK_INDEX));
        return new Position(file, rank);
    }

    public GameResult status() {
        return new GameResult(state.chessBoard().getPositionAndPieces());
    }

    public void end() {
        this.state = state.end();
    }

    public boolean isRunning() {
        return state.isRunning();
    }

    public ChessBoard getBoard() {
        return state.chessBoard();
    }
}
