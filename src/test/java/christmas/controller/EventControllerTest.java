package christmas.controller;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static christmas.ui.ErrorMessage.INVALID_ORDER;
import static christmas.ui.ErrorMessage.ORDERED_ONLY_BEVERAGE_MENU;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import christmas.Application;
import org.junit.jupiter.api.Test;

class EventControllerTest extends NsTest {
    @Test
    void 음료만_주문할_경우_예외가_발생한다() {
        assertSimpleTest(() -> {
            runException("3", "제로콜라-1");
            assertThat(output()).contains(
                    ORDERED_ONLY_BEVERAGE_MENU.getMessage()
            );
        });
    }

    @Test
    void 중복된_메뉴를_주문할_경우_예외가_발생한다() {
        assertSimpleTest(() -> {
            runException("3", "티본스테이크-1,티본스테이크-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains(
                    INVALID_ORDER.getMessage()
            );
        });
    }

    @Test
    void 메뉴_형식이_다른_경우_예외가_발생한다() {
        assertSimpleTest(() -> {
            runException("3", "티본스테이크*1,바비큐립-1,초코케이크-2");
            assertThat(output()).contains(
                    INVALID_ORDER.getMessage()
            );
        });
    }

    @Test
    void 메뉴_개수가_1이상이_아닐_경우_예외가_발생한다() {
        assertSimpleTest(() -> {
            runException("3", "티본스테이크-0,바비큐립-1,초코케이크-2");
            assertThat(output()).contains(
                    INVALID_ORDER.getMessage()
            );
        });
    }

    @Test
    void 혜택을_올바르게_출력한다() {
        assertSimpleTest(() -> {
            run("25", "티본스테이크-2,아이스크림-6,제로콜라-1,레드와인-1");
            assertThat(output()).contains(
                    "<주문 메뉴>",
                    "티본스테이크 2개",
                    "아이스크림 6개",
                    "제로콜라 1개",
                    "레드와인 1개",
                    "<할인 전 총주문 금액>",
                    "203,000원",
                    "<증정 메뉴>",
                    "샴페인 1개",
                    "<총혜택 금액>",
                    "크리스마스 디데이 할인: -3,400원",
                    "증정 이벤트: -25,000원",
                    "평일 할인: -12,138원",
                    "특별 할인: -1,000원",
                    "<총혜택 금액>",
                    "-41,538원",
                    "<할인 후 예상 결제 금액>",
                    "186,462원",
                    "<12월 이벤트 배지>",
                    "산타"
            );
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}