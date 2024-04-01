import application.BoardService;
import application.RoomService;
import controller.GateController;
import controller.board.BoardController;
import controller.room.RoomController;
import database.DefaultConnectionPool;
import database.JdbcTemplate;
import persistence.BoardDao;
import persistence.RoomDao;
import ui.InputView;
import ui.output.BoardOutputView;
import ui.output.GateOutputView;
import ui.output.RoomOutputView;

public class Application {
    public static void main(String[] args) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(new DefaultConnectionPool());
        InputView inputView = new InputView();
        BoardController boardController = new BoardController(inputView, new BoardOutputView(),
                new BoardService(new BoardDao(jdbcTemplate)));
        RoomController roomController = new RoomController(inputView, new RoomOutputView(),
                new RoomService(new RoomDao(jdbcTemplate)), boardController);
        GateController gateController = new GateController(inputView, new GateOutputView(), roomController);
        gateController.run();
    }
}
