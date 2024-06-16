package roomescape.application.dto.request;

import java.time.LocalTime;
import roomescape.domain.time.ReservationTime;

public record ReservationTimeCreationRequest(LocalTime startAt) {
    public ReservationTime toReservationTime() {
        return new ReservationTime(startAt);
    }
}
