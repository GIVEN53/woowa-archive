package oncall.controller;

import static java.util.stream.Collectors.collectingAndThen;

import java.util.ArrayList;
import java.util.List;
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
        List<String> monthAndDayOfWeek = Converter.convertToListByDelimiter(inputView.scanMonthAndDayOfWeek());
        Month month = Month.from(Converter.convertToInt(monthAndDayOfWeek.get(0)));
        DayOfWeek dayOfWeek = DayOfWeek.from(monthAndDayOfWeek.get(1));

        List<String> weekdayEmployeeNicknames = Converter.convertToListByDelimiter(
                inputView.scanEmployeeNickNameForWeekdayShift());
        Employees weekdayEmployees = weekdayEmployeeNicknames.stream()
                .map(Employee::new)
                .collect(collectingAndThen(Collectors.toList(), Employees::new));

        List<String> weekendEmployeeNicknames = Converter.convertToListByDelimiter(
                inputView.scanEmployeeNickNameForWeekendShift());
        Employees weekendEmployees = weekendEmployeeNicknames.stream()
                .map(Employee::new)
                .collect(collectingAndThen(Collectors.toList(), Employees::new));

        // 루프 돌 요일 화, 수, 목, 금 ,,,
        List<DayOfWeek> startDayOfWeeks = DayOfWeek.getDayOfWeeksInOrderFrom(dayOfWeek);
        List<ShiftDate> shiftDates = new ArrayList<>();
        for (int day = 1; day <= month.getDays(); day++) {
            shiftDates.add(new ShiftDate(month, day, startDayOfWeeks.get(day % startDayOfWeeks.size())));
        }
        List<ShiftOrderResult> shiftOrderResults = shiftAssignmentService.assign(shiftDates, weekdayEmployees,
                weekendEmployees);

        outputView.printResult(shiftOrderResults);
    }
}
