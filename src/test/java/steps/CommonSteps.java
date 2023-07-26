package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.core.util.EnvironmentVariables;
import questions.ResponseStatusCode;
import tasks.booking.CreateBookingTask;
import tasks.methods.GetRequest;
import tasks.token.CreateTokenTask;

import java.util.Map;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;


public class CommonSteps {
    private Actor actor;
    private Map<String, Object> pathParams;
    private Map<String, Object> queryParams;
    private Map<String, Object> requestBody;
    private String theRestApiBaseUrl;
    private EnvironmentVariables environmentVariables;


    @Before
    public void set_the_stage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("{word} registered on the API")
    public void onTheApi(String actorName) {
        theRestApiBaseUrl = environmentVariables.optionalProperty("restapi.baseurl").orElse("https://restful-booker.herokuapp.com");
        actor = Actor.named(actorName);
        actor.whoCan(CallAnApi.at(theRestApiBaseUrl));
    }

    @And("{word} access POST the API {string} request endpoint the following body in the request:")
    public void josimarIncludesTheFollowingBodyInTheRequest(String actornamed, String endpoint, DataTable dataTable) {
        actor.attemptsTo(CreateBookingTask.createBookingTask(dataTable,endpoint));
        //Map<String, String> data = dataTable.asMap(String.class, String.class);
    }


    @Then("Verify the response status code {int}")
    public void verifyTheResponseStatusCode(int expectedStatusCode) {
        actor.should(
                seeThat(ResponseStatusCode.value(), equalTo(expectedStatusCode))

        );
    }

    @And("John includes the following path params in the request:")
    public void johnIncludesTheFollowingPathParamsInTheRequest(DataTable pathParamsTable) {
        pathParams = pathParamsTable.asMap(String.class, Object.class);
    }

    @When("Josimar access GET the API {string} request endpoint the following body in the request:")
    public void josimarAccessGETTheAPIRequestEndpointTheFollowingBodyInTheRequest(String endpoint) {

        Task sendGetRequest = GetRequest.toEndpoint(endpoint)
                .withQueryParams(queryParams)
                .withPathParams(pathParams)
                .build();

        actor.attemptsTo(sendGetRequest);

    }

    @And("John includes the following query params in the request:")
    public void johnIncludesTheFollowingQueryParamsInTheRequest(DataTable queryParamsTable) {
        queryParams = queryParamsTable.asMap(String.class, Object.class);
    }

    @When("Josimar access POST the API {string} request endpoint token the following body in the request:")
    public void josimarAccessPOSTTheAPIRequestEndpointTokenTheFollowingBodyInTheRequest( String endpoint, DataTable dataTable) {
        actor.attemptsTo(CreateTokenTask.createTokenTask(dataTable,endpoint));
    }
}
