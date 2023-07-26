package questions;

import io.cucumber.datatable.DataTable;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.Tasks;
import tasks.booking.CreateBookingTask;

import static net.serenitybdd.rest.SerenityRest.lastResponse;

public class ResponseStatusCode implements Question<Integer> {

    @Override
    public Integer answeredBy(Actor actor) {
        return lastResponse().statusCode();
    }

    public static ResponseStatusCode value() {
        return new ResponseStatusCode();
    }
}
