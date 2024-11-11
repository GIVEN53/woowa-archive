package org.apache.coyote.http.response;

import org.apache.catalina.session.Session;
import org.apache.coyote.http.MimeType;
import org.apache.coyote.http.StatusCode;

public class Response {

    private final ResponseLine responseLine;

    private final ResponseHeaders responseHeaders;

    private final ResponseBody responseBody;

    private String viewName;

    public Response() {
        this.responseLine = new ResponseLine();
        this.responseHeaders = new ResponseHeaders();
        this.responseBody = new ResponseBody();
    }

    public void configureViewAndStatus(String viewName, StatusCode statusCode) {
        this.viewName = viewName;
        responseLine.setStatusCode(statusCode);

        if (isRedirect()) {
            responseHeaders.setLocation(viewName);
        }
    }

    public boolean isRedirect() {
        return responseLine.isRedirect();
    }

    public void setContentType(MimeType mimeType) {
        responseHeaders.setContentType(mimeType.getType());
    }

    public void addSessionCookie(Session session) {
        responseHeaders.addSessionCookie(session);
    }

    public void setBody(String body) {
        responseBody.setBody(body);
        responseHeaders.setContentLength(responseBody.length());
    }

    public String getViewName() {
        if (viewName == null) {
            throw new IllegalStateException("viewName이 설정되지 않았습니다.");
        }
        return viewName;
    }

    public byte[] getBytes() {
        return build().getBytes();
    }

    private String build() {
        StringBuilder builder = new StringBuilder();
        responseLine.assemble(builder);
        responseHeaders.assemble(builder);
        responseBody.assemble(builder);
        return builder.toString();
    }
}
