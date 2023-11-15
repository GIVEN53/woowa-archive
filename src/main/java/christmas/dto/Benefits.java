package christmas.dto;

import static christmas.application.discount.EventName.GIVEAWAY_EVENT;

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
        benefits.put(GIVEAWAY_EVENT.getName(), giveaway.price());
    }
}
