package oncall.application;

import static oncall.fixture.EmployeeFixture.createEmployees;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import oncall.domain.DayOfWeek;
import oncall.domain.Employees;
import oncall.domain.Month;
import oncall.domain.ShiftDate;
import oncall.dto.ShiftOrderResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShiftAssignmentServiceTest {
    private final ShiftAssignmentService shiftAssignmentService = new ShiftAssignmentService();
    List<ShiftDate> shiftDates = new ArrayList<>();

    @BeforeEach
    void setUp() {
        Month month = Month.from(1);
        List<DayOfWeek> startDayOfWeeks = DayOfWeek.getDayOfWeeksInOrderFrom(DayOfWeek.MONDAY);
        for (int day = 0; day < month.getDays(); day++) {
            shiftDates.add(new ShiftDate(month, day + 1, startDayOfWeeks.get(day % startDayOfWeeks.size())));
        }
    }

    @Test
    void 근무를_배정한다() {
        //  given
        Employees weekdayEmployees = createEmployees(5);
        Employees weekendEmployees = createEmployees(5);

        // when
        List<ShiftOrderResult> shiftOrderResults = shiftAssignmentService.assign(shiftDates, weekdayEmployees,
                weekendEmployees);

        // then
        assertThat(shiftOrderResults).hasSize(31);


    }


}