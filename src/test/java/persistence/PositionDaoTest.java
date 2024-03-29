package persistence;

import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import support.TestConnectionManager;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class PositionDaoTest {
    private final ConnectionManager connectionManager = new TestConnectionManager();
    private final PositionDao positionDao = new PositionDao(connectionManager);

    @AfterEach
    void tearDown() {
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection.prepareStatement("DELETE FROM POSITION")) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void 포지션을_저장한다() {
        Position position = new Position(File.F, Rank.ONE);
        positionDao.save(position, 1, 1);

        Position savedPosition = positionDao.findByGameIdAndPieceId(1, 1);

        assertThat(savedPosition).isEqualTo(position);
    }

    @Test
    void 게임_ID와_기물_ID로_포지션을_찾는다() {
        Position targetPosition = new Position(File.F, Rank.ONE);
        positionDao.save(targetPosition, 1, 1);
        positionDao.save(new Position(File.B, Rank.TWO), 1, 2);

        Position savedPosition = positionDao.findByGameIdAndPieceId(1, 1);

        assertThat(savedPosition).isEqualTo(targetPosition);
    }

    @Test
    void 포지션을_삭제한다() {
        Position position = new Position(File.F, Rank.ONE);
        positionDao.save(position, 1, 1);

        positionDao.delete(1, 1);

        Position savedPosition = positionDao.findByGameIdAndPieceId(1, 1);
        assertThat(savedPosition).isNull();
    }
}
