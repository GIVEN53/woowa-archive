package controller.board;

import application.BoardService;
import dto.MovementDto;
import ui.InputView;
import ui.output.BoardOutputView;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class BoardController {
    private final InputView inputView;
    private final BoardOutputView outputView;
    private final BoardService boardService;
    private final Map<BoardCommand, Consumer<List<String>>> commands;

    public BoardController(InputView inputView, BoardOutputView outputView, BoardService boardService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.boardService = boardService;
        this.commands = new EnumMap<>(BoardCommand.class);
    }

    private void putCommands() {
        commands.put(BoardCommand.MOVE, this::move);
        commands.put(BoardCommand.STATUS, (ignore) -> status());
        commands.put(BoardCommand.END, (ignore) -> {
        });
    }

    public void run(int roomId) {
        putCommands();
        // 저장된 체스 확인
        outputView.printStartMessage(true); // todo
        // 체스 게임 생성
        // 체스 출력
        BoardCommand command = BoardCommand.NONE;
        while (command != BoardCommand.END) {
            command = playGame();
            command = verifyKingCaptured(command);
        }
        // 현재 게임 저장
    }

    private BoardCommand playGame() {
        try {
            outputView.printCommandMessage();
            List<String> rawCommands = inputView.readCommandNameAndArgs();
            BoardCommand command = BoardCommand.findCommand(rawCommands);
            commands.get(command).accept(rawCommands);
            return command;
        } catch (Exception e) {
            outputView.printErrorMessage(e);
            return BoardCommand.NONE;
        }
    }

    private void move(List<String> rawCommands) {
        MovementDto movementDto = MovementDto.from(rawCommands);
        // MoveDto 생성
        // 체스 게임 서비스에서 move 메서드 호출
        // 체스 출력
//        outputView.printBoard();
    }

    private void status() {
        // 체스 게임 서비스에서 status 메서드 호출
        // 체스 출력
    }

    private BoardCommand verifyKingCaptured(BoardCommand command) {
        // 체스 게임 서비스에서 isCapturedKing 메서드 호출
        // 잡혔으면 승자 출력하고 END 리턴
//        return BoardCommand.END;
        // 아니면 그대로
        return command;
    }
}
