package servlet.handler;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import org.junit.jupiter.api.Test;
import servlet.http.request.Request;
import servlet.http.request.RequestBody;
import servlet.http.request.RequestHeaders;
import servlet.http.request.RequestLine;
import servlet.http.response.Response;

class ResourceHandlerTest {

    private final Handler resourceHandler = new ResourceHandler();

    @Test
    void 정적_리소스를_반환한다() {
        // given
        RequestLine requestLine = new RequestLine("GET /index.html HTTP/1.1");
        Map<String, String> headers = Map.of(
                "Host", "localhost:8080",
                 "Connection", "keep-alive"
        );
        RequestHeaders requestHeaders = new RequestHeaders(headers);
        RequestBody requestBody = RequestBody.from(null);
        Request request = new Request(requestLine, requestHeaders, requestBody);
        Response response = new Response();

        // when
        resourceHandler.handleRequest(request, response);

        // then
        assertThat(response.getViewName()).isEqualTo("/index.html");
    }
}
