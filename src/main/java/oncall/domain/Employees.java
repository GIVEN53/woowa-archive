package oncall.domain;

import java.util.List;
import oncall.ui.ErrorMessage;

public class Employees {
    private final List<Employee> employees;

    public Employees(List<Employee> employees) {
        validateEmployeeMinSize(employees);
        validateEmployeeMaxSize(employees);
        this.employees = employees;
    }

    private void validateEmployeeMinSize(List<Employee> employees) {
        if (employees.size() < 5) {
            throw new IllegalArgumentException(ErrorMessage.LESS_THAN_MINIMUM_EMPLOYEE.getMessage());
        }
    }

    private void validateEmployeeMaxSize(List<Employee> employees) {
        if (employees.size() > 35) {
            throw new IllegalArgumentException(ErrorMessage.MORE_THAN_MAXIMUM_EMPLOYEE.getMessage());
        }
    }
}
