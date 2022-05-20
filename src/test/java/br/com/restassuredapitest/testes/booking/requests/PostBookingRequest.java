package br.com.restassuredapitest.testes.booking.requests;

import br.com.restassuredapitest.testes.booking.requests.payloads.BookingPayloads;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PostBookingRequest {
    BookingPayloads bookingPayloads = new BookingPayloads();
    @Step("Cria nova reserva.")
    public Response bookingCreate(String firstname,String lastname,Number totalprice,Boolean depositpaid,
                                  String checkin,String checkout){

        return given()
                .header("Content-Type","application/json")
                .header("Accept","application/json")
                .when()
                .log().all()
                .body(bookingPayloads.payloadValidoBooking(firstname,lastname,totalprice,depositpaid,checkin,checkout).toString())
                .post("booking");




    }
    }

