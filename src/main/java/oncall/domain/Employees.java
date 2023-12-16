package oncall.domain;

import java.util.List;
import oncall.ui.ErrorMessage;

public class Employees {
    private final List<Employee> employees;

    public Employees(List<Employee> employees) {
        validateEmployeeMinSize(employees);
        this.employees = employees;
    }

    private void validateEmployeeMinSize(List<Employee> employees) {
        if (employees.size() < 5) {
            throw new IllegalArgumentException(ErrorMessage.LESS_THAN_MINIMUM_EMPLOYEE.getMessage());
        }
    }
}
