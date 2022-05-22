package br.com.restassuredapitest.testes.booking.requests;

import br.com.restassuredapitest.testes.auth.requests.PostAuthRequest;
import br.com.restassuredapitest.testes.auth.testes.PostAuthTest;
import br.com.restassuredapitest.testes.booking.requests.payloads.BookingPayloads;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.JSONException;

import static io.restassured.RestAssured.given;

public class PutBookingRequest {
    BookingPayloads bookingPayloads = new BookingPayloads();
    PostAuthRequest postAuthRequest = new PostAuthRequest();
    @Step("Alterar uma reserva usando o token.")
    public Response updateBookingToken(String token,Integer id,String firstname,String lastname,Number totalprice,Boolean depositpaid,
                                       String checkin,String checkout){

        return given()
                .header("Content-Type","application/json")
                .header("Accept","application/json")
                .header("Cookie",token)
                .when()
                .body(bookingPayloads.payloadValidoBooking(firstname,lastname,totalprice,depositpaid,checkin,checkout).toString())
                .put("booking/" + id);




    }


    @Step("Alterar uma reserva usando o Basic auth.")
    public Response updateBookingBasic(int id,String firstname,String lastname,Number totalprice,Boolean depositpaid,
                                      String checkin,String checkout){

        return given()
                .header("Content-Type","application/json")
                .header("Accept","application/json")
                .header("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .queryParam("id",id)
                .when()
                .body(bookingPayloads.payloadValidoBooking(firstname,lastname,totalprice,depositpaid,checkin,checkout).toString())
                .put("booking/" + id);

    }

    @Step("Alterar uma reserva sem o Basic auth.")
    public Response updateBookingNoBasic(int id,String firstname,String lastname,Number totalprice,Boolean depositpaid,
                                      String checkin,String checkout){

        return given()
                .header("Content-Type","application/json")
                .header("Accept","application/json")
                .header("Authorization"," ")
                .queryParam("id",id)
                .when()
                .body(bookingPayloads.payloadValidoBooking(firstname,lastname,totalprice,depositpaid,checkin,checkout).toString())
                .put("booking/" + id);

    }

    @Step("Alterar uma reserva quando o token enviado for inválido.")
    public Response updateBookingWrongToken(int id,String firstname,String lastname,Number totalprice,Boolean depositpaid,
                                         String checkin,String checkout){

        return given()
                .header("Content-Type","application/json")
                .header("Accept","application/json")
                .header("Cookie","wrongcookie123")
                .queryParam("id",id)
                .when()
                .body(bookingPayloads.payloadValidoBooking(firstname,lastname,totalprice,depositpaid,checkin,checkout).toString())
                .put("booking/" + id);

    }

}
