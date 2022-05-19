package br.com.restassuredapitest.testes.auth.requests;

import br.com.restassuredapitest.testes.auth.requests.payload.AuthPayloads;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.http.auth.AUTH;
import org.json.JSONException;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class PostAuthRequest {
    AuthPayloads authPayloads = new AuthPayloads();

    @Step("Retorna o token")
    public Response tokenReturn() throws JSONException {

        return given()
                .header("Content-Type", "Application/json")
                .when()
                .body(authPayloads.jsonAuthLogin().toString())
                .post("auth");


    }
    @Step("Busca o token")
    public String getToken() throws JSONException {

        return "token" + this.tokenReturn()
                .then()
                .statusCode(200)
                .extract()
                .path("token");
    }

}
