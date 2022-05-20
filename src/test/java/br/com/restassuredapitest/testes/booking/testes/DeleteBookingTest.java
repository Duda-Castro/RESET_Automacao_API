package br.com.restassuredapitest.testes.booking.testes;

import br.com.restassuredapitest.base.BaseTest;
import br.com.restassuredapitest.suites.*;
import br.com.restassuredapitest.testes.booking.requests.DeleteBookingRequest;
import br.com.restassuredapitest.testes.booking.requests.GetBookingRequest;
import br.com.restassuredapitest.testes.booking.requests.payloads.BookingPayloads;
import br.com.restassuredapitest.utils.Utils;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

@Feature("Feature de retorno de reservas")
public class DeleteBookingTest extends BaseTest {

    DeleteBookingRequest deleteBookingRequest = new DeleteBookingRequest();
    GetBookingRequest getBookingRequest = new GetBookingRequest();

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, SecurityTests.class})
    @DisplayName("Excluir uma reserva sem autorização")
    public void validarExclusaoDeReservaSemHeader(){

        deleteBookingRequest.excluiReservaSemHeader(getBookingRequest.bookingFirstId())
                .then()
                .statusCode(200)
                .time(lessThan(5L), TimeUnit.SECONDS);

    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, AcceptanceCriticalTest.class})
    @DisplayName("Excluir um reserva com sucesso token.")
    public void validarExclusaoDeReservaComToken(){

        deleteBookingRequest.excluiReservaComCookie(getBookingRequest.bookingFirstId(),"admin","password123")
                .then()
                .statusCode(200)
                .time(lessThan(5L), TimeUnit.SECONDS);

    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, AcceptanceCriticalTest.class})
    @DisplayName("Excluir um reserva com sucesso basic.")
    public void validarExclusaoDeReservaComBasic(){

        deleteBookingRequest.excluiReservaComAuthorisation(getBookingRequest.bookingFirstId())
                .then()
                .statusCode(200)
                .time(lessThan(5L), TimeUnit.SECONDS);

    }
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, AcceptanceExceptionTest.class})
    @DisplayName("Tentar excluir um reserva que não existe")
    public void validarExclusaoDeReservaInexistente(){

        deleteBookingRequest.excluiReservaComCookie( -1,"admin","password123")
                .then()
                .statusCode(404)
                .time(lessThan(5L), TimeUnit.SECONDS);

    }





}
