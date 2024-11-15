package servlet.handler;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import org.junit.jupiter.api.Test;
import org.apache.coyote.http.request.Request;
import org.apache.coyote.http.request.RequestBody;
import org.apache.coyote.http.request.RequestHeaders;
import org.apache.coyote.http.request.RequestLine;
import org.apache.coyote.http.response.Response;

class WelcomeHandlerTest {

    private final Handler welcomeHandler = WelcomeHandler.getInstance();

    @Test
    void welcome_경로를_반환한다() {
        // given
        RequestLine requestLine = new RequestLine("GET / HTTP/1.1");
        Map<String, String> headers = Map.of(
                "Host", "localhost:8080",
                "Connection", "keep-alive"
        );
        RequestHeaders requestHeaders = new RequestHeaders(headers);
        RequestBody requestBody = RequestBody.from(null);
        Request request = new Request(requestLine, requestHeaders, requestBody);
        Response response = new Response();

        // when
        welcomeHandler.handleRequest(request, response);

        // then
        assertThat(response.getViewName()).isEqualTo("/welcome");
    }
}
