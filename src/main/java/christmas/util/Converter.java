package christmas.util;

import static christmas.ui.ErrorMessage.INVALID_NUMBER;
import static christmas.ui.ErrorMessage.INVALID_ORDER;
import static christmas.util.Delimiter.COMMA;

import java.util.List;

public class Converter {
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
}
