package christmas.domain.badge;

import java.util.Arrays;

public enum Badge {

    SANTA("산타", 20_000, 1),
    TREE("트리", 10_000, 2),
    STAR("별", 5_000, 3),
    NONE("없음", 0, 4);

    private final String name;
    private final int requiredBenefitAmount;
    private final int priority;
    private static final Badge[] BADGES = Badge.values();

    Badge(String name, int requiredBenefitAmount, int priority) {
        this.name = name;
        this.requiredBenefitAmount = requiredBenefitAmount;
        this.priority = priority;
    }

    public static Badge findByBenefitAmount(int benefitAmount) {
        return Arrays.stream(BADGES)
                .sorted((o1, o2) -> o2.priority - o1.priority)
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
}
