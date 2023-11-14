package christmas.util;

import static christmas.ui.ErrorMessage.INVALID_NUMBER;

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
}
