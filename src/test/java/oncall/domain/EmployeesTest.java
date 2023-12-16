package oncall.domain;

import static oncall.ui.ErrorMessage.DUPLICATED_EMPLOYEE;
import static oncall.ui.ErrorMessage.LESS_THAN_MINIMUM_EMPLOYEE;
import static oncall.ui.ErrorMessage.MORE_THAN_MAXIMUM_EMPLOYEE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

class EmployeesTest {

    @Test
    void 최소_인원수보다_작을_경우_예외가_발생한다() {
        // given
        int employeeCount = 4;
        List<Employee> employees = IntStream.rangeClosed(1, employeeCount)
                .mapToObj(i -> new Employee("홍길동" + i))
                .toList();
        // when & then
        assertThatThrownBy(() -> new Employees(employees))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(LESS_THAN_MINIMUM_EMPLOYEE.getMessage());
    }

    @Test
    void 최대_인원수보다_클_경우_예외가_발생한다() {
        // given
        int employeeCount = 36;
        List<Employee> employees = IntStream.rangeClosed(1, employeeCount)
                .mapToObj(i -> new Employee("홍길동" + i))
                .toList();
        // when & then
        assertThatThrownBy(() -> new Employees(employees))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(MORE_THAN_MAXIMUM_EMPLOYEE.getMessage());
    }

    @Test
    void 중복된_직원이_있을_경우_예외가_발생한다() {
        // given
        List<Employee> employees = List.of(
                new Employee("홍길동"),
                new Employee("홍길동"),
                new Employee("김길동"),
                new Employee("김길동"),
                new Employee("박길동")
        );

        // when & then
        assertThatThrownBy(() -> new Employees(employees))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(DUPLICATED_EMPLOYEE.getMessage());
    }

}