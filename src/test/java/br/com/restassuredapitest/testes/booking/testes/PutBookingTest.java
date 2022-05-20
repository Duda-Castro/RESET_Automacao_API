package br.com.restassuredapitest.testes.booking.testes;

import br.com.restassuredapitest.base.BaseTest;
import br.com.restassuredapitest.suites.AcceptanceCriticalTest;
import br.com.restassuredapitest.suites.AllTests;
import br.com.restassuredapitest.suites.SchemaTest;
import br.com.restassuredapitest.suites.SecurityTests;
import br.com.restassuredapitest.testes.auth.requests.PostAuthRequest;
import br.com.restassuredapitest.testes.booking.requests.GetBookingRequest;
import br.com.restassuredapitest.testes.booking.requests.PutBookingRequest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.json.JSONException;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.Matchers.greaterThan;

@Feature("Feature de atualização da ação de reservas")
public class PutBookingTest extends BaseTest {
    PutBookingRequest putBookingRequest = new PutBookingRequest();
    GetBookingRequest getBookingRequest = new GetBookingRequest();
    PostAuthRequest postAuthRequest = new PostAuthRequest();
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceCriticalTest.class})
    @DisplayName("Alterar uma reserva usando o token")
    public void validarAlteracaoDeUmaReservaUtilizandoToken() throws JSONException {


        putBookingRequest.updateBookingToken(getBookingRequest.bookingFirstId(), postAuthRequest.getToken("admin","password123"),"Jim","Brown",
                        111,true,"2018-01-01","2019-01-01")
                .then()
                .log().all()
                .statusCode(200)
                .body("size()",greaterThan(0));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceCriticalTest.class})
    @DisplayName("Alterar uma reserva usando o Basic auth")
    public void validarAlteracaoDeUmaReservaUtilizandoBasic() throws JSONException {


        putBookingRequest.updateBookingBasic(getBookingRequest.bookingFirstId(),"Jim","Brown",
                        111,true,"2018-01-01","2019-01-01")
                .then()
                .log().all()
                .statusCode(200)
                .body("size()",greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, SecurityTests.class})
    @DisplayName("Alterar uma reserva quando o token não for enviado")
    public void validarAlteracaoDeUmaReservaSemToken() throws JSONException {


        putBookingRequest.updateBookingToken(getBookingRequest.bookingFirstId(), "","Jim","Brown",
                        111,true,"2018-01-01","2019-01-01")
                .then()
                .log().all()
                .statusCode(403);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, SecurityTests.class})
    @DisplayName("Alterar uma reserva quando o token não for enviado")
    public void validarAlteracaoDeUmaReservaSemBasic() throws JSONException {


        putBookingRequest.updateBookingNoBasic(getBookingRequest.bookingFirstId(),"Jim","Brown",
                        111,true,"2018-01-01","2019-01-01")
                .then()
                .log().all()
                .statusCode(403);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, SecurityTests.class})
    @DisplayName("Alterar uma reserva quando o token enviado for inválido")
    public void validarAlteracaoDeUmaReservaComTokenInvalido() throws JSONException {


        putBookingRequest.updateBookingWrongToken(getBookingRequest.bookingFirstId(),"Jim","Brown",
                        111,true,"2018-01-01","2019-01-01")
                .then()
                .log().all()
                .statusCode(403);
    }
}


