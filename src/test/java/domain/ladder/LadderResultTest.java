package domain.ladder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.player.Player;
import domain.prize.Prize;
import java.util.Map;
import org.junit.jupiter.api.Test;

class LadderResultTest {
    @Test
    void 존재하지_않는_참가자의_결과를_확인할_경우_예외가_발생한다() {
        // given
        Map<Player, Prize> results = Map.of(
                new Player("프린"), new Prize("100"),
                new Player("땡이"), new Prize("꽝")
        );
        LadderResult ladderResult = new LadderResult(results);

        // when & then
        assertThatThrownBy(() -> ladderResult.findPrizeByPlayerName("토미"))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 존재하는_참가자의_결과를_확인하면_상품을_반환한다() {
        // given
        Map<Player, Prize> results = Map.of(
                new Player("프린"), new Prize("100"),
                new Player("땡이"), new Prize("꽝")
        );
        LadderResult ladderResult = new LadderResult(results);

        // when
        String prizeName = ladderResult.findPrizeByPlayerName("프린");

        // then
        assertThat(prizeName).isEqualTo("100");
    }
}
