package br.com.restassuredapitest.testes.booking.requests;

import br.com.restassuredapitest.testes.booking.requests.payloads.BookingPayloads;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PostBookingRequest {
    BookingPayloads bookingPayloads = new BookingPayloads();
    @Step("Cria nova reserva.")
    public Response bookingCreate(){

        return given()
                .header("Content-Type","application/json")
                .header("Accept","application/json")
                .when()
                .body(bookingPayloads.payloadValidoBooking().toString())
                .post("booking");




    }
    }

