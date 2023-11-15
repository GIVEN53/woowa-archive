package christmas.domain.badge;

import static java.util.Comparator.comparingInt;

import java.util.Arrays;

public enum Badge {

    SANTA("산타", 20_000, 1),
    TREE("트리", 10_000, 2),
    STAR("별", 5_000, 3),
    NONE("없음", 0, 4);

    private static final Badge[] BADGES = Badge.values();
    private final String name;
    private final int requiredBenefitAmount;
    private final int priority;

    Badge(String name, int requiredBenefitAmount, int priority) {
        this.name = name;
        this.requiredBenefitAmount = requiredBenefitAmount;
        this.priority = priority;
    }

    public static Badge findByBenefitAmount(int benefitAmount) {
        return Arrays.stream(BADGES)
                .sorted(comparingInt(o -> o.priority))
                .filter(badge -> !badge.isNone(badge))
                .filter(badge -> badge.isSatisfied(benefitAmount))
                .findFirst()
                .orElse(NONE);
    }

    private boolean isSatisfied(int benefitAmount) {
        return this.requiredBenefitAmount <= benefitAmount;
    }

    private boolean isNone(Badge badge) {
        return badge == NONE;
    }

    public String getName() {
        return this.name;
    }
}
