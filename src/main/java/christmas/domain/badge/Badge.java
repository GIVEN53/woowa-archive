package christmas.domain.badge;

public enum Badge {
    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000);

    private final String name;
    private final int requiredBenefitAmount;

    Badge(String name, int requiredBenefitAmount) {
        this.name = name;
        this.requiredBenefitAmount = requiredBenefitAmount;
    }
}
