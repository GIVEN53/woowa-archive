package servlet.handler;

import org.apache.coyote.http.StatusCode;
import org.apache.coyote.http.request.Request;
import org.apache.coyote.http.response.Response;

public class ResourceHandler implements Handler {

    private static ResourceHandler INSTANCE;

    private ResourceHandler() {
    }

    public static ResourceHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ResourceHandler();
        }
        return INSTANCE;
    }

    @Override
    public void handleRequest(Request request, Response response) {
        response.configureViewAndStatus(request.getPath(), StatusCode.OK);
    }
}
