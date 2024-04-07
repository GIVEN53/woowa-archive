package oncall.util;

public enum Delimiter {
    COMMA(",", "^[가-힣a-zA-Z\\d]+(,[가-힣a-zA-Z\\d]+)*$");

    private final String value;
    private final String regex;

    Delimiter(String value, String regex) {
        this.value = value;
        this.regex = regex;
    }

    public String getValue() {
        return value;
    }

    public String getRegex() {
        return regex;
    }
}
