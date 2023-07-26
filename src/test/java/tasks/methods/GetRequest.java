package tasks.methods;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import java.util.HashMap;
import java.util.Map;

public class GetRequest implements Task {


    private String endpoint;
    private Map<String, Object> queryParams;
    private Map<String, Object> pathParams;
    private Map<String, Object> headers;
    private Map<String, Object> body;

    public GetRequest (String endpoint, Map<String, Object> queryParams, Map<String, Object> pathParams, Map<String, Object> headers, Map<String, Object> body) {
        this.endpoint = endpoint;
        this.queryParams = queryParams != null ? queryParams : new HashMap<>();
        this.pathParams = pathParams != null ? pathParams : new HashMap<>();
        this.headers = headers != null ? headers : new HashMap<>();
        this.body = body != null ? body : new HashMap<>();
    }

    public static SendGetRequestBuilder toEndpoint(String endpoint) {
        return new SendGetRequestBuilder(endpoint);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                Get.resource(endpoint)
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

    public static class SendGetRequestBuilder {
        private String endpoint;
        private Map<String, Object> queryParams;
        private Map<String, Object> pathParams;
        private Map<String, Object> headers;
        private Map<String, Object> body;

        public SendGetRequestBuilder(String endpoint) {
            this.endpoint = endpoint;
        }

        public SendGetRequestBuilder withQueryParams(Map<String, Object> queryParams) {
            this.queryParams = queryParams;
            return this;
        }

        public SendGetRequestBuilder withPathParams(Map<String, Object> pathParams) {
            this.pathParams = pathParams;
            return this;
        }

        public SendGetRequestBuilder withHeaders(Map<String, Object> headers) {
            this.headers = headers;
            return this;
        }

        public SendGetRequestBuilder withBody(Map<String, Object> body) {
            this.body = body;
            return this;
        }

        public GetRequest build() {
            return new GetRequest(endpoint, queryParams, pathParams, headers, body);
        }

    }



}
