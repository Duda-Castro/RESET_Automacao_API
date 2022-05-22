package br.com.restassuredapitest.testes.auth.requests;

import br.com.restassuredapitest.testes.auth.requests.payload.AuthPayloads;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PostAuthRequest {
    AuthPayloads authPayloads = new AuthPayloads();

    @Step("Retorna o token")
    public Response tokenReturn(String username, String password){

        return given()
                .header("Content-Type", "Application/json")
                .when()
                .body(authPayloads.jsonAuthLogin(username,password).toString())
                .post("auth");


    }
    @Step("Busca o token")
    public String getToken(String username, String password){

        return "token=" + this.tokenReturn(username,password)
                .then()
                .statusCode(200)
                .extract()
                .path("token");
    }

}
