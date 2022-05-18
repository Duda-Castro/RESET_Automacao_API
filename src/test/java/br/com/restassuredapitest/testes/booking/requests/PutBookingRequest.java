package br.com.restassuredapitest.testes.booking.requests;

import br.com.restassuredapitest.testes.booking.requests.payloads.BookingPayloads;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.JSONException;

import static io.restassured.RestAssured.given;

public class PutBookingRequest {
    BookingPayloads bookingPayloads = new BookingPayloads();
    @Step("Atualiza uma reserva específica com um token")
    public Response updateBookingToken(Integer id, String token) throws JSONException {

        return given()
                .header("Content-Type","application/json")
                .header("Accept","application/json")
                .header("Cookie",token)
                .when()
                .body(bookingPayloads.payloadValidoBooking().toString())
                .get("booking/" + id);




    }


}
