package br.com.restassuredapitest.testes.booking.testes;

import br.com.restassuredapitest.base.BaseTest;
import br.com.restassuredapitest.suites.AcceptanceCriticalTest;
import br.com.restassuredapitest.suites.AcceptanceExceptionTest;
import br.com.restassuredapitest.suites.AllTests;
import br.com.restassuredapitest.suites.SchemaTest;
import br.com.restassuredapitest.testes.booking.requests.GetBookingRequest;
import br.com.restassuredapitest.testes.booking.requests.PostBookingRequest;
import br.com.restassuredapitest.utils.Utils;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.*;

@Feature("Feature de retorno de reservas")
public class GetBookingTest extends BaseTest {
    PostBookingRequest postBookingRequest = new PostBookingRequest();
    GetBookingRequest getBookingRequest = new GetBookingRequest();

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, AcceptanceCriticalTest.class})
    @DisplayName("Listar IDs das reservas.")
    public void validarListagemDeIdsDasReservas(){

        getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .body("size()",greaterThan(0))
                .body("bookingid",notNullValue());

    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class,AcceptanceCriticalTest.class})
    @DisplayName("Listar IDs de reservas utilizando o filtro firstname.")
    public void validarListagemDeIdsComFiltroFirstName(){
        postBookingRequest.bookingCreate("Jim","Brown",111,true,"2017-07-29","2018-04-29");
        getBookingRequest.bookingReturnIdsWithFilters("firstname","Jim","","",
                        "","","","")
                .then()
                .statusCode(200)
                .body("size()",greaterThan(0))
                .body("bookingid",notNullValue());;

    }
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class,AcceptanceCriticalTest.class})
    @DisplayName("Listar IDs de reservas utilizando o filtro lastname.")
    public void validarListagemDeIdsComFiltroLastName(){
        postBookingRequest.bookingCreate("Jim","Brown",111,true,"2017-07-29","2018-04-29");
        getBookingRequest.bookingReturnIdsWithFilters("lastname","Brown","","",
                        "","","","")
                .then()
                .statusCode(200)
                .body("size()",greaterThan(0))
                .body("bookingid",notNullValue());;

    }
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class,AcceptanceCriticalTest.class})
    @DisplayName("Listar IDs de reservas utilizando o filtro checkin.")
    public void validarListagemDeIdsComFiltroCheckin(){
        postBookingRequest.bookingCreate("Jim","Brown",111,true,"2000-01-01","2001-01-01");
        getBookingRequest.bookingReturnIdsWithFilters("checkin","1111-01-01","","",
                        "","","","")
                .then()
                .log().all()
                .statusCode(200)
                .body("size()",greaterThan(0))
                .body("bookingid",notNullValue());;

    }
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class,AcceptanceCriticalTest.class})
    @DisplayName("Listar IDs de reservas utilizando o filtro checkout.")
    public void validarListagemDeIdsComFiltroCheckout(){
        postBookingRequest.bookingCreate("Jim","Brown",111,true,"2017-07-29","2018-04-29");
        getBookingRequest.bookingReturnIdsWithFilters("checkout","2222-01-01","","",
                        "","","","")
                .then()
                .log().all()
                .statusCode(200)
                .body("size()",greaterThan(0))
                .body("bookingid",notNullValue());


    }
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class,AcceptanceCriticalTest.class})
    @DisplayName("Listar IDs de reservas utilizando o filtro checkin e checkout.")
    public void validarListagemDeIdsComFiltroCheckinECheckout(){
        postBookingRequest.bookingCreate("Jim","Brown",111,true,"9798-07-29","9898-04-29");
        getBookingRequest.bookingReturnIdsWithFilters("checkin","1111-01-01","checkout","2222-01-01",
                        "","","","")
                .then()
                .log().all()
                .statusCode(200)
                .body("size()",greaterThan(0))
                .body("bookingid",notNullValue());

    }
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class,AcceptanceCriticalTest.class})
    @DisplayName("Listar IDs de reservas utilizando o filtro checkin e checkout.")
    public void validarListagemDeIdsComFiltroNomeECheckinECheckout(){
        postBookingRequest.bookingCreate("Jim","Brown",111,true,"2017-07-29","2018-04-29");
        getBookingRequest.bookingReturnIdsWithFilters("firstname","Jim","checkin","1111-01-01",
                        "checkout","2222-01-01","","")
                .then()
                .log().all()
                .statusCode(200)
                .body("bookingid",notNullValue());

    }


    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, AcceptanceCriticalTest.class})
    @DisplayName("Listar uma reserva específica.")
    public void validarBuscaDeReservasPorId(){
        int bookingFirstId = getBookingRequest.bookingFirstId();

        getBookingRequest.bookingById(bookingFirstId)
                .then()
                .statusCode(200)
                .body("firstname",notNullValue())
                .body("lastname",notNullValue())
                .body("totalprice",notNullValue())
                .body("depositpaid",notNullValue())
                .body("bookingdates",notNullValue());

    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({SchemaTest.class,AllTests.class})
    @DisplayName("Garantir o schema do retorno da listagem de reservas.")
    public void validaSchemaDaListagemDeReservas(){
        getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .body(matchesJsonSchema(new File(Utils.getSchemaBasePath("booking","bookings"))));

    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({SchemaTest.class,AllTests.class})
    @DisplayName("Garantir o schema do retorno da listagem de reservas com filtro.")
    public void validaSchemaDaListagemDeReservasComFiltro(){
        getBookingRequest.bookingReturnIdsWithFilters("firstname","Jim","","",
                        "","","","")
                .then()
                .statusCode(200)
                .body(matchesJsonSchema(new File(Utils.getSchemaBasePath("booking","bookings"))));

    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({SchemaTest.class,AllTests.class})
    @DisplayName("Garantir o schema do retorno da reserva por id.")
    public void validaSchemaDaListagemDeReservaPorId(){
        getBookingRequest.bookingById(getBookingRequest.bookingFirstId())
                .then()
                .statusCode(200)
                .body(matchesJsonSchema(new File(Utils.getSchemaBasePath("booking","getBookingById"))));

    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, AcceptanceExceptionTest.class})
    @DisplayName("Fazer consulta de reservas enviando filtro mal formatado.")
    public void validarListagemDeIdsComFiltroMalFormatado(){

        getBookingRequest.bookingReturnIdsWithFilters("firstname","45236698","checkin","2017-50-50",
                        "checkout","2018-04-29","","")
                .then()
                .statusCode(500);

    }


}
