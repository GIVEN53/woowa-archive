package oncall.domain;

import static oncall.ui.ErrorMessage.INVALID_NICKNAME_SIZE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class EmployeeTest {
    @ParameterizedTest
    @ValueSource(strings = {"홍길동홍길동", "1234567", "홍길동홍길동홍길동"})
    void 닉네임_길이가_5글자를_초과할_경우_예외가_발생한다(String nickname) {
        // when & then
        assertThatThrownBy(() -> new Employee(nickname))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_NICKNAME_SIZE.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"홍길동", "12345", "준팍"})
    void 닉네임_길이가_5글자를_이내일_경우_예외가_발생하지_않는다(String nickname) {
        // when
        Employee employee = new Employee(nickname);

        // then
        assertThat(employee.getNickname()).isEqualTo(nickname);
    }
}