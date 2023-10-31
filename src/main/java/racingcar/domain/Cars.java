package racingcar.domain;

import java.util.Collections;
import java.util.List;

import static racingcar.enums.RacingConfig.CAR_COUNT_MIN;

public class Cars {
    private final List<Car> cars;

    public Cars(List<Car> cars) {
        validateDuplicatedCar(cars);
        validateSize(cars);
        this.cars = cars;
    }

    private void validateDuplicatedCar(List<Car> cars) {
        long distinctCount = cars.stream()
                .distinct()
                .count();
        if (distinctCount != cars.size()) {
            throw new IllegalArgumentException("중복된 자동차가 존재합니다.");
        }
    }

    private void validateSize(List<Car> cars) {
        if (cars.size() < CAR_COUNT_MIN.getValue()) {
            throw new IllegalArgumentException(String.format("자동차는 %d대 이상이어야 합니다.", CAR_COUNT_MIN.getValue()));
        }
    }

    public List<Car> getCars() {
        return Collections.unmodifiableList(cars);
    }
}
