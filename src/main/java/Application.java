import application.BoardService;
import application.RoomService;
import controller.GateController;
import controller.board.BoardController;
import controller.room.RoomController;
import database.DefaultConnectionPool;
import persistence.BoardDao;
import persistence.RoomDao;
import ui.InputView;
import ui.output.BoardOutputView;
import ui.output.GateOutputView;
import ui.output.RoomOutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        BoardController boardController = new BoardController(inputView, new BoardOutputView(),
                new BoardService(new BoardDao(new DefaultConnectionPool())));
        RoomController roomController = new RoomController(inputView, new RoomOutputView(),
                new RoomService(new RoomDao(new DefaultConnectionPool())), boardController);
        GateController gateController = new GateController(inputView, new GateOutputView(), roomController);
        gateController.run();
    }
}
