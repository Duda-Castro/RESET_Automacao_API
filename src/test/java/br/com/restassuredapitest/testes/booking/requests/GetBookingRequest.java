package br.com.restassuredapitest.testes.booking.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetBookingRequest {
    @Step("Retorna os IDs da listagem de reservas")
    public Response bookingReturnIds(){

        return given()
                .when()
                .get("booking");




    }

}
