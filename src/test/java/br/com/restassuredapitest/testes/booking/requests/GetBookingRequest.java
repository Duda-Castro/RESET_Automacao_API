package br.com.restassuredapitest.testes.booking.requests;

import io.qameta.allure.Step;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import org.json.JSONException;

import static io.restassured.RestAssured.given;

public class GetBookingRequest {
    @Step("Retorna os IDs da listagem de reservas sem filtro.")
    public Response bookingReturnIds(){

        return given()
                .when()
                .get("booking");




    }

    @Step("Retorna primeiro ID")
    public int bookingFirstId(){

        return this.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract().path("[0].bookingid");



    }

    @Step("Retorna os IDs da listagem de reservas com dois filtros.")
    public Response bookingReturnIds2Filters(String filtro1, String valor1, String filtro2, String valor2){

        return given()
                .queryParam(filtro1,valor1)
                .queryParam(filtro2,valor2)
                .when()
                .get("booking");




    }
    @Step("Retorna os IDs da listagem de reservas com quatro filtros.")
    public Response bookingReturnIds4Filters(String filtro1, String valor1, String filtro2, String valor2,
                                           String filtro3, String valor3, String filtro4, String valor4){

        return given()
                .queryParam(filtro1,valor1)
                .queryParam(filtro2,valor2)
                .queryParam(filtro3,valor3)
                .queryParam(filtro4,valor4)
                .when()
                .get("booking");




    }

    @Step("Retorna reserva com determinado ID.")
    public Response bookingById() throws JSONException {

        return given()
                .header("Accept","application/json")
                .when()
                .get("booking/" + bookingFirstId());
    }

}
