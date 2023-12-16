package oncall.util;

public enum Delimiter {
    COMMA(",");

    private final String value;

    Delimiter(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
