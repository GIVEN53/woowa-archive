package roomescape.application.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalTime;
import roomescape.domain.time.ReservationTime;

public record ReservationTimeResponse(long id, @JsonFormat(pattern = "HH:mm") LocalTime startAt) {
    public static ReservationTimeResponse from(ReservationTime time) {
        return new ReservationTimeResponse(time.getId(), time.getStartAt());
    }
}
