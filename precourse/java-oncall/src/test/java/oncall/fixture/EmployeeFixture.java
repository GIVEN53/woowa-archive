package oncall.fixture;

import static java.util.stream.Collectors.collectingAndThen;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import oncall.domain.Employee;
import oncall.domain.Employees;

public class EmployeeFixture {
    public static Employee createEmployee(String name) {
        return new Employee(name);
    }

    public static Employee createEmployee() {
        return createEmployee("홍길동");
    }

    public static Employees createEmployees(int count) {
        return IntStream.rangeClosed(1, count)
                .mapToObj(i -> createEmployee("홍길동" + i))
                .collect(collectingAndThen(Collectors.toList(), Employees::new));    }
}
