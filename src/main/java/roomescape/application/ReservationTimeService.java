package roomescape.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import roomescape.application.dto.ReservationTimeCreationRequest;
import roomescape.domain.reservation.Reservation;
import roomescape.domain.reservation.repository.ReservationRepository;
import roomescape.domain.time.ReservationTime;
import roomescape.domain.time.repository.ReservationTimeRepository;
import roomescape.dto.reservationtime.ReservationTimeResponse;

@Service
public class ReservationTimeService {
    private final ReservationTimeRepository reservationTimeRepository;
    private final ReservationRepository reservationRepository;

    public ReservationTimeService(ReservationTimeRepository reservationTimeRepository,
                                  ReservationRepository reservationRepository) {
        this.reservationTimeRepository = reservationTimeRepository;
        this.reservationRepository = reservationRepository;
    }

    public ReservationTime register(ReservationTimeCreationRequest request) {
        if (reservationTimeRepository.existsByStartAt(request.startAt())) {
            throw new IllegalArgumentException("이미 존재하는 예약 시간입니다.");
        }
        ReservationTime reservationTime = new ReservationTime(request.startAt());
        return reservationTimeRepository.save(reservationTime);
    }

    public ReservationTime getReservationTime(long id) {
        return reservationTimeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 예약 시간입니다."));
    }

    public List<ReservationTimeResponse> getReservationTimes() {
        List<ReservationTimeResponse> responses = new ArrayList<>();
        for (ReservationTime reservationTime : reservationTimeRepository.findAll()) {
            Optional<Reservation> optionalReservation = reservationRepository.findByTimeId(reservationTime.getId());
            boolean alreadyBooked = optionalReservation.isPresent();
            ReservationTimeResponse response = ReservationTimeResponse.from(reservationTime, alreadyBooked);
            responses.add(response);
        }
        return responses;
    }

    public void delete(long id) {
        ReservationTime reservationTime = getReservationTime(id);
        Optional<Reservation> optionalReservation = reservationRepository.findByTimeId(id);
        if (optionalReservation.isPresent()) {
            throw new IllegalArgumentException("해당 시간을 사용하는 예약이 존재합니다");
        }
        reservationTimeRepository.deleteById(reservationTime.getId());
    }
}
