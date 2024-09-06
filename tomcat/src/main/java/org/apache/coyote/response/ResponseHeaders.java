package org.apache.coyote.response;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ResponseHeaders implements Assemblable {

    private final Map<String, String> headers;

    private ResponseHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    protected static ResponseHeaders create() {
        return new ResponseHeaders(new LinkedHashMap<>());
    }

    protected void contentType(String contentType) {
        headers.put("Content-Type", "%s;charset=utf-8".formatted(contentType));
    }

    protected void contentLength(int contentLength) {
        headers.put("Content-Length", String.valueOf(contentLength));
    }

    protected void location(String location) {
        headers.put("Location", location);
    }

    @Override
    public void assemble(StringBuilder builder) {
        for (Entry<String, String> entry : headers.entrySet()) {
            builder.append(convert(entry));
        }
        builder.append("\r\n");
    }

    private String convert(Entry<String, String> entry) {
        return "%s: %s \r\n".formatted(entry.getKey(), entry.getValue());
    }
}
