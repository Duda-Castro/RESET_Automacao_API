package br.com.restassuredapitest.testes.auth.testes;

import br.com.restassuredapitest.base.BaseTest;
import br.com.restassuredapitest.suites.AllTests;
import br.com.restassuredapitest.testes.auth.requests.PostAuthRequest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.json.JSONException;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.*;

@Feature("Feature de autenticação")
public class PostAuthTest extends BaseTest {

    PostAuthRequest postAuthRequest = new PostAuthRequest();


    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class})
    @DisplayName("Retorna token para o usuário")
    public void validarRetornoTokenParaUsuario(){

        postAuthRequest.tokenReturn("admin","password123")
                .then()
                .statusCode(200)
                .body("token",notNullValue());

    }


}
