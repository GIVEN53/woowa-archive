package domain.card.state;

import static fixture.CardFixture.카드;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.Cards;
import org.junit.jupiter.api.Test;

class StayTest {
    @Test
    void 카드를_추가하면_예외가_발생한다() {
        CardState stay = new Stay(new Cards());

        assertThatThrownBy(() -> stay.receive(카드()))
                .isExactlyInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void 끝내면_현재_상태를_리턴한다() {
        CardState stay = new Stay(new Cards());

        CardState finish = stay.finish();

        assertThat(finish).isExactlyInstanceOf(Stay.class);
    }

    @Test
    void 종료된_상태이다() {
        CardState stay = new Stay(new Cards());

        boolean finished = stay.isFinished();

        assertThat(finished).isTrue();
    }

    @Test
    void 스테이의_수익은_배팅_금액의_100퍼센트이다() {
        CardState stay = new Stay(new Cards());

        int profit = stay.calculateProfit(1000);

        assertThat(profit).isEqualTo(1000);
    }
}
