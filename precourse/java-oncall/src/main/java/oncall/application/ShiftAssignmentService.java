package oncall.application;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import oncall.domain.Employees;
import oncall.domain.ShiftDate;
import oncall.dto.ShiftOrderResult;

public class ShiftAssignmentService {

    public List<ShiftOrderResult> assign(List<ShiftDate> shiftDates, Employees weekdayEmployees,
                                         Employees weekendEmployees) {
        Queue<String> consecutiveEmployees = new LinkedList<>();
        int weekdayEmployeeIndex = 0;
        int weekendEmployeeIndex = 0;
        List<ShiftOrderResult> shiftOrderResults = new ArrayList<>();
        for (ShiftDate shiftDate : shiftDates) {
            String name = null;
            if (shiftDate.isWeekday()) {
                name = getNextName(weekdayEmployees, weekdayEmployeeIndex, consecutiveEmployees, shiftOrderResults);
                weekdayEmployeeIndex++;
            } else if (shiftDate.isWeekend() || shiftDate.isHoliday()) {
                name = getNextName(weekendEmployees, weekendEmployeeIndex, consecutiveEmployees, shiftOrderResults);
                weekendEmployeeIndex++;
            }
            shiftOrderResults.add(ShiftOrderResult.of(name, shiftDate));
        }
        return shiftOrderResults;
    }

    private String getNextName(Employees employees, int employeeIndex,
                               Queue<String> consecutiveEmployees, List<ShiftOrderResult> shiftOrderResults) {
        if (checkQueue(consecutiveEmployees)) {
            return consecutiveEmployees.poll();
        }
        String name = employees.getNicknameByIndex(employeeIndex);
        if (isConsecutiveWorker(name, shiftOrderResults)) {
            consecutiveEmployees.offer(name);
            name = employees.getNicknameByIndex(employeeIndex + 1);
        }
        return name;
    }

    private boolean checkQueue(Queue<String> consecutiveEmployees) {
        return !consecutiveEmployees.isEmpty();
    }

    private boolean isConsecutiveWorker(String name, List<ShiftOrderResult> shiftOrderResults) {
        int size = shiftOrderResults.size();
        if (size == 0) {
            return false;
        }
        String lastEmployeeName = shiftOrderResults.get(size - 1).employeeName();
        return lastEmployeeName.equals(name);
    }
}
