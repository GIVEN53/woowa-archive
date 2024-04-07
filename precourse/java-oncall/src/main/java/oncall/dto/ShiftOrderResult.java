package oncall.dto;

import oncall.domain.ShiftDate;

public record ShiftOrderResult(String employeeName, int month, int day, String dayOfWeek) {
    public static ShiftOrderResult of(String employeeName, ShiftDate shiftDate) {
        if (shiftDate.isWeekday() && shiftDate.isHoliday()) {
            String dayOfWeek = shiftDate.dayOfWeek().getName() + "(휴일)";
            return new ShiftOrderResult(employeeName, shiftDate.month().getMonth(), shiftDate.day(), dayOfWeek);
        }
        return new ShiftOrderResult(employeeName, shiftDate.month().getMonth(), shiftDate.day(),
                shiftDate.dayOfWeek().getName());
    }
}
