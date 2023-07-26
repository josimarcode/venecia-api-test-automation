package tasks.methods;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import java.util.HashMap;
import java.util.Map;

public class PostRequest implements Task {

    private String endpoint;
    private Map<String, Object> queryParams;
    private Map<String, Object> pathParams;
    private Map<String, Object> headers;
    private Object body;



    public PostRequest(String endpoint, Map<String, Object> queryParams, Map<String, Object> pathParams, Map<String, Object> headers, Object body) {
        this.endpoint = endpoint;
        this.queryParams = queryParams != null ? queryParams : new HashMap<>();
        this.pathParams = pathParams != null ? pathParams : new HashMap<>();
        this.headers = headers != null ? headers : new HashMap<>();
        this.body = body != null ? body : new HashMap<>();

    }

    public static SendPostRequestBuilder toEndpoint(String endpoint) {
        return new SendPostRequestBuilder(endpoint);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                Post.to(endpoint)
                        .with(request -> {
                            request.queryParams(queryParams);
                            request.pathParams(pathParams);
                            request.headers(headers);
                            request.contentType(ContentType.JSON);
                            request.body(body);
                            return request;
                        })
        );
    }

    public static class SendPostRequestBuilder {
        private String endpoint;
        private Map<String, Object> queryParams;
        private Map<String, Object> pathParams;
        private Map<String, Object> headers;
        private Object body;

        public SendPostRequestBuilder(String endpoint) {
            this.endpoint = endpoint;
        }

        public SendPostRequestBuilder withQueryParams(Map<String, Object> queryParams) {
            this.queryParams = queryParams;
            return this;
        }

        public SendPostRequestBuilder withPathParams(Map<String, Object> pathParams) {
            this.pathParams = pathParams;
            return this;
        }

        public SendPostRequestBuilder withHeaders(Map<String, Object> headers) {
            this.headers = headers;
            return this;
        }

        public SendPostRequestBuilder withBody(Object body) {
            this.body = body;
            return this;
        }

        public PostRequest build() {
            return new PostRequest(endpoint, queryParams, pathParams, headers, body);
        }

    }
}
