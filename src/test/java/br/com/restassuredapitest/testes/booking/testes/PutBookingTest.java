package br.com.restassuredapitest.testes.booking.testes;

import br.com.restassuredapitest.base.BaseTest;
import br.com.restassuredapitest.suites.*;
import br.com.restassuredapitest.testes.auth.requests.PostAuthRequest;
import br.com.restassuredapitest.testes.booking.requests.GetBookingRequest;
import br.com.restassuredapitest.testes.booking.requests.PutBookingRequest;
import com.github.javafaker.Faker;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.json.JSONException;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

@Feature("Feature de atualização da ação de reservas")
public class PutBookingTest extends BaseTest {
    PutBookingRequest putBookingRequest = new PutBookingRequest();
    GetBookingRequest getBookingRequest = new GetBookingRequest();
    PostAuthRequest postAuthRequest = new PostAuthRequest();
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceCriticalTest.class})
    @DisplayName("Alterar uma reserva usando o token.")
    public void validarAlteracaoDeUmaReservaUtilizandoToken() throws JSONException {
        String nomeTrocado = new Faker().elderScrolls().firstName();
        String sobrenomeTrocado = new Faker().elderScrolls().lastName();
        int totalNewPrice = new  Faker().number().numberBetween(0,2000);

        putBookingRequest.updateBookingToken(postAuthRequest.getToken("admin","password123"), getBookingRequest.bookingFirstId(),
                        nomeTrocado,sobrenomeTrocado,
                        totalNewPrice,false,"2018-01-01","2019-01-01")
                .then()
                .statusCode(200)
                .body("size()",greaterThan(0))
                .body("firstname",equalTo(nomeTrocado))
                .body("lastname",equalTo(sobrenomeTrocado))
                .body("totalprice",equalTo(totalNewPrice))
                .body("depositpaid",equalTo(false))
                .body("bookingdates.checkin",equalTo("2018-01-01"))
                .body("bookingdates.checkout",equalTo("2019-01-01"));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceCriticalTest.class})
    @DisplayName("Alterar uma reserva usando o Basic auth.")
    public void validarAlteracaoDeUmaReservaUtilizandoBasic() throws JSONException {
        String nomeTrocado = new Faker().elderScrolls().firstName();
        String sobrenomeTrocado = new Faker().elderScrolls().lastName();
        int totalNewPrice = new  Faker().number().numberBetween(0,2000);

        putBookingRequest.updateBookingBasic(getBookingRequest.bookingFirstId(),nomeTrocado,sobrenomeTrocado,
                        totalNewPrice,false,"2018-01-01","2019-01-01")
                .then()
                .statusCode(200)
                .body("size()",greaterThan(0))
                .body("firstname",equalTo(nomeTrocado))
                .body("lastname",equalTo(sobrenomeTrocado))
                .body("totalprice",equalTo(totalNewPrice))
                .body("depositpaid",equalTo(false))
                .body("bookingdates.checkin",equalTo("2018-01-01"))
                .body("bookingdates.checkout",equalTo("2019-01-01"));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, SecurityTests.class})
    @DisplayName("Falhar ao alterar uma reserva quando o token não for enviado.")
    public void validarAlteracaoDeUmaReservaSemToken() throws JSONException {
        String nomeTrocado = new Faker().elderScrolls().firstName();
        String sobrenomeTrocado = new Faker().elderScrolls().lastName();
        int totalNewPrice = new  Faker().number().numberBetween(0,2000);

        putBookingRequest.updateBookingToken(postAuthRequest.getToken("",""),getBookingRequest.bookingFirstId(),nomeTrocado,sobrenomeTrocado,
                        totalNewPrice,true,"2018-01-01","2019-01-01")
                .then()
                .statusCode(403);
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, SecurityTests.class})
    @DisplayName("Falhar ao alterar uma reserva quando o basic não for enviado")
    public void validarAlteracaoDeUmaReservaSemBasic() throws JSONException {
        String nomeTrocado = new Faker().elderScrolls().firstName();
        String sobrenomeTrocado = new Faker().elderScrolls().lastName();
        int totalNewPrice = new  Faker().number().numberBetween(0,2000);


        putBookingRequest.updateBookingNoBasic(getBookingRequest.bookingFirstId(),nomeTrocado,sobrenomeTrocado,
                        totalNewPrice,true,"2018-01-01","2019-01-01")
                .then()
                .statusCode(403);
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, SecurityTests.class})
    @DisplayName("Falhar ao alterar uma reserva quando o token enviado for inválido")
    public void validarAlteracaoDeUmaReservaComTokenInvalido() throws JSONException {
        String nomeTrocado = new Faker().elderScrolls().firstName();
        String sobrenomeTrocado = new Faker().elderScrolls().lastName();
        int totalNewPrice = new  Faker().number().numberBetween(0,2000);


        putBookingRequest.updateBookingWrongToken(getBookingRequest.bookingFirstId(),nomeTrocado,sobrenomeTrocado,
                        totalNewPrice,true,"2018-01-01","2019-01-01")
                .then()
                .statusCode(403);
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, AcceptanceExceptionTest.class})
    @DisplayName("Falhar ao alterar uma reserva que não existe.")
    public void validarAlteracaoDeReservaInexistente(){
        String nomeTrocado = new Faker().elderScrolls().firstName();
        String sobrenomeTrocado = new Faker().elderScrolls().lastName();
        int totalNewPrice = new  Faker().number().numberBetween(0,2000);
        putBookingRequest.updateBookingToken(postAuthRequest.getToken("admin","password123"),-1, nomeTrocado,sobrenomeTrocado,
                        totalNewPrice,true,"2018-01-01","2019-01-01")
                .then()
                .statusCode(405);
    }
}


