package br.com.restassuredapitest.testes.booking.requests;

import br.com.restassuredapitest.testes.auth.requests.PostAuthRequest;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.JSONException;

import static io.restassured.RestAssured.given;

public class DeleteBookingRequest {
    PostAuthRequest postAuthRequest = new PostAuthRequest();


    @Step("Exclui uma reserva.")
    public Response excluiReservaSemHeader(int id){

        return given()
                .when()
                .queryParam("id",id)
                .delete("booking/"+id);



    }
    @Step("Exclui uma reserva com token.")
    public Response excluiReservaComCookie(int id,String username,String password){

        return given()
                .header("Cookie",postAuthRequest.getToken(username,password))
                .when()
                .queryParam("id",id)
                .delete("booking/"+id);



    }
    @Step("Exclui uma reserva com Basic.")
    public Response excluiReservaComAuthorisation(int id){

        return given()
                .header("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .when()
                .queryParam("id",id)
                .delete("booking/"+id);



    }
}
