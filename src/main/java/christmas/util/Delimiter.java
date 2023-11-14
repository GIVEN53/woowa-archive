package christmas.util;

public enum Delimiter {
    DASH("-", "^[가-힣]+-\\d+$"),
    COMMA(",", "^[가-힣\\-\\d]+(,[가-힣\\-\\d]+)*$");

    private final String value;
    private final String regex;

    Delimiter(String value, String regex) {
        this.value = value;
        this.regex = regex;
    }

    public String getValue() {
        return this.value;
    }

    public String getRegex() {
        return this.regex;
    }
}
