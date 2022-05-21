package br.com.restassuredapitest.testes.booking.requests;

import br.com.restassuredapitest.testes.auth.requests.PostAuthRequest;
import br.com.restassuredapitest.testes.booking.requests.payloads.BookingPayloads;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.patch;

public class PatchBookingRequest {
    BookingPayloads bookingPayloads = new BookingPayloads();
    PostAuthRequest postAuthRequest = new PostAuthRequest();
    @Step("Alterar parcialmente uma reserva.")

    public Response bookingAlterParcialInfo(int id,String username,String password,String firstname, String lastname){

        return given()
                .header("Content-Type","application/json")
                .header("Accept","application/json")
                .header("Cookie",postAuthRequest.getToken(username,password))
                .queryParam("id",id)
                .when()
                .body(bookingPayloads.payloadParcial(firstname,lastname).toString())
                .patch("booking/" + id);




    }

    public Response bookingAlterParcialInfoBasic(int id,String firstname, String lastname){

        return given()
                .header("Content-Type","application/json")
                .header("Accept","application/json")
                .header("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .queryParam("id",id)
                .when()
                .body(bookingPayloads.payloadParcial(firstname,lastname).toString())
                .patch("booking/" + id);




    }
}
