package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {
    @JsonProperty(value = "firstname")
    private String firstname;

    @JsonProperty(value = "lastname")
    public String lastname;

    @JsonProperty(value = "totalprice")
    public Integer totalPrice;

    @JsonProperty(value = "depositpaid")
    public Boolean depositPaid;

    @JsonProperty(value = "bookingdates")
    public CheckBooking bookingDates;

    @JsonProperty(value = "additionalneeds")
    public String additionalNeeds;
}
