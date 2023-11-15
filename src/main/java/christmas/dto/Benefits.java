package christmas.dto;

import java.util.Map;

public record Benefits(Map<String, Integer> benefits) {
    public static Benefits empty() {
        return new Benefits(Map.of());
    }

    public int get(String eventName) {
        return benefits.get(eventName);
    }

    public boolean isEmpty() {
        return benefits.isEmpty();
    }

    public void put(Giveaway giveaway) {
        benefits.put(giveaway.name(), giveaway.price());
    }
}
