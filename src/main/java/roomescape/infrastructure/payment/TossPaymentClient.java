package roomescape.infrastructure.payment;

import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;
import roomescape.service.PaymentClient;
import roomescape.service.dto.request.PaymentCancelRequest;
import roomescape.service.dto.request.PaymentRequest;

public class TossPaymentClient implements PaymentClient {

    private final RestClient restClient;

    public TossPaymentClient(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public void pay(PaymentRequest paymentRequest) {
        restClient.post()
                .uri("/confirm")
                .contentType(MediaType.APPLICATION_JSON)
                .body(paymentRequest)
                .retrieve()
                .toBodilessEntity();
    }

    @Override
    public void cancel(PaymentCancelRequest cancelRequest) {
        restClient.post()
                .uri("/{paymentKey}/cancel", cancelRequest.paymentKey())
                .contentType(MediaType.APPLICATION_JSON)
                .body(cancelRequest)
                .retrieve()
                .toBodilessEntity();
    }
}
