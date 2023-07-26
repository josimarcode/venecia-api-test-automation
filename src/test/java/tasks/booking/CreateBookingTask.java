package tasks.booking;

import io.cucumber.datatable.DataTable;
import models.Booking;
import models.CheckBooking;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import tasks.methods.PostRequest;

import java.util.List;
import java.util.Map;

public class CreateBookingTask implements Task {
    private final DataTable dataTable;
    private final String endpoint;

    public CreateBookingTask(DataTable dataTable,String endpoint) {
        this.dataTable = dataTable;
        this.endpoint = endpoint;
    }

    public static Performable createBookingTask(DataTable dataTable,String endpoint) {
        return Tasks.instrumented(CreateBookingTask.class, dataTable,endpoint);
    }
    @Override
    public <T extends Actor> void performAs(T actor) {
        List<Map<String, String>> table = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : table) {
            Task sendPostRequest = PostRequest.toEndpoint(endpoint)
                    .withBody(Booking.builder()
                            .firstname(row.get("firstname"))
                            .lastname(row.get("lastname"))
                            .totalPrice(Integer.parseInt(row.get("totalprice")))
                            .depositPaid(Boolean.parseBoolean(row.get("depositpaid")))
                            .bookingDates(CheckBooking.builder()
                                    .checkin(row.get("checkin"))
                                    .checkout(row.get("checkout"))
                                    .build())
                            .additionalNeeds(row.get("additionalneeds"))
                            .build()
                    ).build();

            actor.attemptsTo(sendPostRequest);
        }
    }
}
