package br.com.restassuredapitest.testes.booking.testes;

import br.com.restassuredapitest.base.BaseTest;
import br.com.restassuredapitest.suites.AllTests;
import br.com.restassuredapitest.suites.SchemaTest;
import br.com.restassuredapitest.testes.booking.requests.GetBookingRequest;
import br.com.restassuredapitest.testes.booking.requests.payloads.BookingPayloads;
import br.com.restassuredapitest.utils.Utils;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.*;

@Feature("Feature de retorno de reservas")
public class GetBookingTest extends BaseTest {

    GetBookingRequest getBookingRequest = new GetBookingRequest();

    //Listar IDs das reservas
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class})
    @DisplayName("Listar IDs de reservas")
    public void validarListagemDeIdsDasReservas(){

        getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .body("size()",greaterThan(0))
                .time(lessThan(5L), TimeUnit.SECONDS);

    }

    //Listar IDs de reservas utilizando o filtro firstname
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class})
    @DisplayName("Listar IDs de reservas utilizando o filtro firstname.")
    public void validarListagemDeIdsComFiltroFirstName(){

        getBookingRequest.bookingReturnIdsWithFilters("firstname","Jim","","",
                        "","","","")
                .then()
                .statusCode(200)
                .body("size()",greaterThan(0))
                .body("bookingid",notNullValue())
                .time(lessThan(5L), TimeUnit.SECONDS);

    }
    //Listar IDs de reservas utilizando o filtro lastname
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class})
    @DisplayName("Listar IDs de reservas utilizando o filtro lastname.")
    public void validarListagemDeIdsComFiltroLastName(){

        getBookingRequest.bookingReturnIdsWithFilters("lastname","Brown","","",
                        "","","","")
                .then()
                .statusCode(200)
                .body("size()",greaterThan(0))
                .body("bookingid",notNullValue())
                .time(lessThan(5L), TimeUnit.SECONDS);

    }
    //Listar IDs de reservas utilizando o filtro checkin
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class})
    @DisplayName("Listar IDs de reservas utilizando o filtro checkin.")
    public void validarListagemDeIdsComFiltroCheckin(){

        getBookingRequest.bookingReturnIdsWithFilters("checkin","2017-07-29","","",
                        "","","","")
                .then()
                .statusCode(200)
                .body("size()",greaterThan(0))
                .body("bookingid",notNullValue())
                .time(lessThan(5L), TimeUnit.SECONDS);

    }
    //Listar IDs de reservas utilizando o filtro checkout
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class})
    @DisplayName("Listar IDs de reservas utilizando o filtro checkout.")
    public void validarListagemDeIdsComFiltroCheckout(){

        getBookingRequest.bookingReturnIdsWithFilters("checkout","2018-04-29","","",
                        "","","","")
                .then()
                .statusCode(200)
                .body("size()",greaterThan(0))
                .body("bookingid",notNullValue())
                .time(lessThan(5L), TimeUnit.SECONDS);

    }
    //Listar IDs de reservas utilizando o filtro checkin and checkout
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class})
    @DisplayName("Listar IDs de reservas utilizando o filtro checkin e checkout.")
    public void validarListagemDeIdsComFiltroCheckinECheckout(){

        getBookingRequest.bookingReturnIdsWithFilters("checkin","2017-07-29","checkout","2018-04-29",
                        "","","","")
                .then()
                .statusCode(200)
                .body("size()",greaterThan(0))
                .body("bookingid",notNullValue())
                .time(lessThan(5L), TimeUnit.SECONDS);

    }
    //Listar IDs de reservas utilizando o filtro name, checkin and checkout date
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class})
    @DisplayName("Listar IDs de reservas utilizando o filtro checkin e checkout.")
    public void validarListagemDeIdsComFiltroNomeECheckinECheckout(){

        getBookingRequest.bookingReturnIdsWithFilters("firstname","Jim","checkin","2017-07-29",
                        "checkout","2018-04-29","","")
                .then()
                .statusCode(200)
                .body("size()",greaterThan(0))
                .body("bookingid",notNullValue())
                .time(lessThan(5L), TimeUnit.SECONDS);

    }


    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class})
    @DisplayName("Listar reserva por id.")
    public void validarBuscaDeReservasPorId(){
        int bookingFirstId = getBookingRequest.bookingFirstId();
        BookingPayloads bookingPayloads = new BookingPayloads();

        getBookingRequest.bookingById(bookingFirstId)
                .then()
                .statusCode(200)
                .body("firstname",notNullValue())
                .body("lastname",notNullValue())
                .body("totalprice",notNullValue())
                .body("depositpaid",notNullValue())
                .body("bookingdates",notNullValue())
                .time(lessThan(5L), TimeUnit.SECONDS);

    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({SchemaTest.class,AllTests.class})
    @DisplayName("Garantir o schema do retorno da listagem de reservas.")
    public void validaSchemaDaListagemDeReservas(){
        getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .body(matchesJsonSchema(new File(Utils.getSchemaBasePath("booking","bookings"))))
                .time(lessThan(5L), TimeUnit.SECONDS);

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
                .body(matchesJsonSchema(new File(Utils.getSchemaBasePath("booking","bookings"))))
                .time(lessThan(5L), TimeUnit.SECONDS);

    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({SchemaTest.class,AllTests.class})
    @DisplayName("Garantir o schema do retorno da reserva por id.")
    public void validaSchemaDaListagemDeReservaPorId(){
        getBookingRequest.bookingById(getBookingRequest.bookingFirstId())
                .then()
                .statusCode(200)
                .body(matchesJsonSchema(new File(Utils.getSchemaBasePath("booking","getBookingById"))))
                .time(lessThan(5L), TimeUnit.SECONDS);

    }




}
