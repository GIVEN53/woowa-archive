package oncall.ui;

import java.util.List;
import oncall.dto.ShiftOrderResult;

public class OutputView {
    public void printError(Exception e) {
        System.out.println(e.getMessage());
    }

    public void printResult(List<ShiftOrderResult> shiftOrderResults) {
        shiftOrderResults.forEach(
                r -> System.out.printf("%d월 %d일 %s %s%n", r.month(), r.day(), r.dayOfWeek(), r.employeeName())
        );
    }
}
