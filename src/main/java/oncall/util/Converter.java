package oncall.util;

import static oncall.ui.ErrorMessage.SEPARATE_BY_COMMA;
import static oncall.util.Delimiter.COMMA;

import java.util.List;

public class Converter {
    private Converter() {
    }

    public static int convertToInt(String value) {
        return Integer.parseInt(value);
    }

    public static List<String> convertToListByDelimiter(String value) {
        if (value.matches(COMMA.getRegex())) {
            return List.of(value.split(COMMA.getValue()));
        }
        throw new IllegalArgumentException(SEPARATE_BY_COMMA.getMessage());
    }
}
