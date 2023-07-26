package tasks.token;

import io.cucumber.datatable.DataTable;
import models.User;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import tasks.methods.PostRequest;

import java.util.List;
import java.util.Map;

public class CreateTokenTask implements Task {
    private final DataTable dataTable;
    private final String endpoint;

    public CreateTokenTask(DataTable dataTable, String endpoint) {
        this.dataTable = dataTable;
        this.endpoint = endpoint;
    }

    public static Performable createTokenTask(DataTable dataTable, String endpoint) {
        return Tasks.instrumented(CreateTokenTask.class, dataTable,endpoint);
    }
    @Override
    public <T extends Actor> void performAs(T actor) {
        List<Map<String, String>> table = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : table) {
            Task sendPostRequest = PostRequest.toEndpoint(endpoint)
                    .withBody(User.builder()
                            .username(row.get("username"))
                            .password(row.get("password"))
                            .build()
                    ).build();

            actor.attemptsTo(sendPostRequest);
        }
    }
}
