package oncall.controller;

import static java.util.stream.Collectors.collectingAndThen;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import oncall.application.ShiftAssignmentService;
import oncall.domain.DayOfWeek;
import oncall.domain.Employee;
import oncall.domain.Employees;
import oncall.domain.Month;
import oncall.domain.ShiftDate;
import oncall.dto.ShiftOrderResult;
import oncall.ui.InputView;
import oncall.ui.OutputView;
import oncall.util.Converter;

public class ShiftController {
    private final InputView inputView;
    private final OutputView outputView;
    private final ShiftAssignmentService shiftAssignmentService;

    public ShiftController(InputView inputView, OutputView outputView, ShiftAssignmentService shiftAssignmentService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.shiftAssignmentService = shiftAssignmentService;
    }

    public void run() {
        List<ShiftDate> shiftDates = getShiftDates();
        List<ShiftOrderResult> shiftOrderResults = getShiftOrderResults(shiftDates);
        outputView.printResult(shiftOrderResults);
    }

    private List<ShiftOrderResult> getShiftOrderResults(List<ShiftDate> shiftDates) {
        return inputWithRetry(() -> {
            Employees weekdayEmployees = getEmployees(inputView.scanEmployeeNickNameForWeekdayShift());
            Employees weekendEmployees = getEmployees(inputView.scanEmployeeNickNameForWeekendShift());
            return shiftAssignmentService.assign(shiftDates, weekdayEmployees,
                    weekendEmployees);
        });
    }

    private Employees getEmployees(String input) {
        List<String> employeeNicknames = Converter.convertToListByDelimiter(input);
        return employeeNicknames.stream()
                .map(Employee::new)
                .collect(collectingAndThen(Collectors.toList(), Employees::new));
    }

    private List<ShiftDate> getShiftDates() {
        return inputWithRetry(() -> {
            List<String> monthAndDayOfWeek = Converter.convertToListByDelimiter(inputView.scanMonthAndDayOfWeek());
            Month month = Month.from(Converter.convertToInt(monthAndDayOfWeek.get(0)));
            List<DayOfWeek> startDayOfWeeks = getDayOfWeeks(monthAndDayOfWeek);
            List<ShiftDate> shiftDates = new ArrayList<>();
            for (int day = 0; day < month.getDays(); day++) {
                shiftDates.add(new ShiftDate(month, day + 1, startDayOfWeeks.get(day % startDayOfWeeks.size())));
            }
            return shiftDates;
        });
    }

    private static List<DayOfWeek> getDayOfWeeks(List<String> monthAndDayOfWeek) {
        DayOfWeek dayOfWeek = DayOfWeek.from(monthAndDayOfWeek.get(1));
        return DayOfWeek.getDayOfWeeksInOrderFrom(dayOfWeek);
    }

    private <T> T inputWithRetry(final Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException exception) {
                outputView.printError(exception);
            }
        }
    }
}
