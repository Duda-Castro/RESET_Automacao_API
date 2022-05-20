package br.com.restassuredapitest.testes.booking.requests;

import br.com.restassuredapitest.testes.booking.requests.payloads.BookingPayloads;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.JSONException;

import static io.restassured.RestAssured.given;

public class PutBookingRequest {
    BookingPayloads bookingPayloads = new BookingPayloads();

    @Step("Alterar uma reserva usando o token.")
    public Response updateBookingToken(Integer id, String token,String firstname,String lastname,Number totalprice,Boolean depositpaid,
                                       String checkin,String checkout) throws JSONException {

        return given()
                .header("Content-Type","application/json")
                .header("Accept","application/json")
                .header("Cookie",token)
                .when()
                .log().all()
                .body(bookingPayloads.payloadValidoBooking(firstname,lastname,totalprice,depositpaid,checkin,checkout).toString())
                .get("booking/" + id);




    }


    @Step("Alterar uma reserva usando o Basic auth.")
    public Response updateBookingBasic(int id,String firstname,String lastname,Number totalprice,Boolean depositpaid,
                                      String checkin,String checkout){

        return given()
                .header("Content-Type","application/json")
                .header("Accept","application/json")
                .header("Authorisation","Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .queryParam("id",id)
                .when()
                .log().all()
                .body(bookingPayloads.payloadValidoBooking(firstname,lastname,totalprice,depositpaid,checkin,checkout).toString())
                .put("booking/" + id);

    }

    @Step("Alterar uma reserva sem o Basic auth.")
    public Response updateBookingNoBasic(int id,String firstname,String lastname,Number totalprice,Boolean depositpaid,
                                      String checkin,String checkout){

        return given()
                .header("Content-Type","application/json")
                .header("Accept","application/json")
                .header("Authorisation"," ")
                .queryParam("id",id)
                .when()
                .log().all()
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
                .log().all()
                .body(bookingPayloads.payloadValidoBooking(firstname,lastname,totalprice,depositpaid,checkin,checkout).toString())
                .put("booking/" + id);

    }

}
