package servlet.http.request.uri;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class URI {

    private static final String URI_DELIMITER = "\\?";
    private static final int QUERY_PARAMS_EXIST_LENGTH = 2;
    private static final int PATH_INDEX = 0;
    private static final int QUERY_PARAMS_INDEX = 1;
    private static final int MAX_URI_LENGTH = 2048;

    private final Path path;

    private final QueryParams queryParams;

    public URI(String uri) {
        validateUri(uri);
        String decodedUri = URLDecoder.decode(uri, StandardCharsets.UTF_8);
        String[] uriParts = split(decodedUri);
        this.path = new Path(uriParts[PATH_INDEX]);
        this.queryParams = QueryParams.from(extractQueryParams(uriParts));
    }

    private void validateUri(String uri) {
        if (uri == null || uri.isBlank()) {
            throw new IllegalArgumentException("URI는 필수입니다.");
        }
        if (uri.length() > MAX_URI_LENGTH) {
            throw new IllegalArgumentException("URI는 %s자를 넘을 수 없습니다.".formatted(MAX_URI_LENGTH));
        }
    }

    private String[] split(String uri) {
        String[] uriParts = uri.split(URI_DELIMITER);
        if (uriParts.length > QUERY_PARAMS_EXIST_LENGTH) {
            throw new IllegalArgumentException("잘못된 URI입니다. uri: %s".formatted(uri));
        }
        return uriParts;
    }

    private String extractQueryParams(String[] uriParts) {
        if (uriParts.length == QUERY_PARAMS_EXIST_LENGTH) {
            return uriParts[QUERY_PARAMS_INDEX];
        }
        return null;
    }

    public String getPath() {
        return this.path.getPath();
    }

    public String getQueryParamValue(String key) {
        return this.queryParams.get(key);
    }

    public boolean existQueryParams() {
        return this.queryParams.existQueryParams();
    }
}
