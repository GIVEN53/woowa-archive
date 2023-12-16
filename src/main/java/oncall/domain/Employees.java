package oncall.domain;

import static oncall.ui.ErrorMessage.DUPLICATED_EMPLOYEE;
import static oncall.ui.ErrorMessage.LESS_THAN_MINIMUM_EMPLOYEE;
import static oncall.ui.ErrorMessage.MORE_THAN_MAXIMUM_EMPLOYEE;

import java.util.List;

public class Employees {
    private final List<Employee> employees;

    public Employees(List<Employee> employees) {
        validateEmployeeMinSize(employees);
        validateEmployeeMaxSize(employees);
        validateDuplicateEmployee(employees);
        this.employees = employees;
    }

    private void validateEmployeeMinSize(List<Employee> employees) {
        if (employees.size() < 5) {
            throw new IllegalArgumentException(LESS_THAN_MINIMUM_EMPLOYEE.getMessage());
        }
    }

    private void validateEmployeeMaxSize(List<Employee> employees) {
        if (employees.size() > 35) {
            throw new IllegalArgumentException(MORE_THAN_MAXIMUM_EMPLOYEE.getMessage());
        }
    }

    private void validateDuplicateEmployee(List<Employee> employees) {
        long distinctCount = employees.stream()
                .distinct()
                .count();
        if (distinctCount != employees.size()) {
            throw new IllegalArgumentException(DUPLICATED_EMPLOYEE.getMessage());
        }
    }
}
