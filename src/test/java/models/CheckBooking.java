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
public class CheckBooking {

    @JsonProperty(value = "checkin")
    private String checkin;

    @JsonProperty(value = "checkout")
    private String checkout;
}