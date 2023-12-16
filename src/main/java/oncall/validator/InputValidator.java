package oncall.validator;

import static oncall.ui.ErrorMessage.IS_BLANK;

public class InputValidator {
    public void validate(final String input) {
        validateBlank(input);
    }

    private void validateBlank(final String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException(IS_BLANK.getMessage());
        }
    }
}
