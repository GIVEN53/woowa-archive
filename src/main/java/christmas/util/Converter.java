package christmas.util;

import static christmas.ui.ErrorMessage.INVALID_NUMBER;
import static christmas.ui.ErrorMessage.INVALID_ORDER;
import static christmas.util.Delimiter.COMMA;
import static christmas.util.Delimiter.DASH;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import org.assertj.core.data.MapEntry;

public class Converter {
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("###,###");

    private Converter() {
    }

    public static int convertToInt(final String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_NUMBER.getMessage());
        }
    }

    public static List<String> convertToListByComma(final String input) {
        if (input.matches(COMMA.getRegex())) {
            return List.of(input.split(COMMA.getValue()));
        }
        throw new IllegalArgumentException(INVALID_ORDER.getMessage());
    }

    public static Map.Entry<String, Integer> convertToMapEntryByDash(final String input) {
        if (input.matches(DASH.getRegex())) {
            String[] split = input.split(DASH.getValue());
            return MapEntry.entry(split[0], convertToInt(split[1]));
        }
        throw new IllegalArgumentException(INVALID_ORDER.getMessage());
    }

    public static String convertNumberWithComma(final int number) {
        return DECIMAL_FORMAT.format(number);
    }
}
