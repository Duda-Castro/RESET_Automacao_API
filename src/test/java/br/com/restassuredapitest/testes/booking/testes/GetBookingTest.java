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
import org.apache.http.HttpStatus;
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
                .statusCode(HttpStatus.SC_OK)
                .body("bookingid",notNullValue());

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class,AcceptanceCriticalTest.class})
    @DisplayName("Listar IDs de reservas utilizando o filtro firstname.")
    public void validarListagemDeIdsComFiltroFirstName(){
       int id = postBookingRequest.getIdbookingCreate("Jim","Brown",111,true,"3159-07-29","3159-08-29");
        getBookingRequest.bookingReturnIdsWithFilters("firstname","Jim","","",
                        "","","","")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("bookingid",hasItem(id));

    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class,AcceptanceCriticalTest.class})
    @DisplayName("Listar IDs de reservas utilizando o filtro lastname.")
    public void validarListagemDeIdsComFiltroLastName(){
        int id = postBookingRequest.getIdbookingCreate("Jim","Brown",111,true,"3159-07-29","3159-08-29");
        getBookingRequest.bookingReturnIdsWithFilters("lastname","Brown","","",
                        "","","","")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("bookingid",hasItems(id));

    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class,AcceptanceCriticalTest.class})
    @DisplayName("Listar IDs de reservas utilizando o filtro checkin.")
    public void validarListagemDeIdsComFiltroCheckin(){
        int id = postBookingRequest.getIdbookingCreate("Jim","Brown",111,true,"3159-07-29","3159-08-29");
        getBookingRequest.bookingReturnIdsWithFilters("checkin","3159-07-29","","",
                        "","","","")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("bookingid",hasItems(id));

    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class,AcceptanceCriticalTest.class})
    @DisplayName("Listar IDs de reservas utilizando o filtro checkout.")
    public void validarListagemDeIdsComFiltroCheckout(){
        int id = postBookingRequest.getIdbookingCreate("Jim","Brown",111,true,"3159-07-29","3159-08-29");
        getBookingRequest.bookingReturnIdsWithFilters("checkout","3159-08-29","","",
                        "","","","")
                .then()
                .body("bookingid",hasItems(id))
                .statusCode(HttpStatus.SC_OK);


    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class,AcceptanceCriticalTest.class})
    @DisplayName("Listar IDs de reservas utilizando os filtros checkin e checkout.")
    public void validarListagemDeIdsComFiltroCheckinECheckout(){
        int id = postBookingRequest.getIdbookingCreate("Jim","Brown",111,true,"3159-07-29","3159-08-29");
        getBookingRequest.bookingReturnIdsWithFilters("checkin","3159-07-29","checkout","3159-08-29",
                        "","","","")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("bookingid",hasItems(id));


    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class,AcceptanceCriticalTest.class})
    @DisplayName("Listar IDs de reservas utilizando os filtros firstname, checkin e checkout.")
    public void validarListagemDeIdsComFiltroNomeECheckinECheckout(){
        int id = postBookingRequest.getIdbookingCreate("Jim","Brown",111,true,"3159-07-29","3159-08-29");
        getBookingRequest.bookingReturnIdsWithFilters("firstname","Jim","checkin","3159-07-29",
                        "checkout","3159-08-29","","")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("bookingid",hasItems(id));

    }


    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, AcceptanceCriticalTest.class})
    @DisplayName("Listar uma reserva específica.")
    public void validarBuscaDeReservasPorId(){
        String nameValue = "Jim";
        String lastnameValue = "Brown";
        int totalprice = 111;
        Boolean depositpaid = true;
        String checkin = "3159-07-29";
        String checkout = "3159-08-29";

        int id = postBookingRequest.getIdbookingCreate(nameValue,lastnameValue,totalprice,depositpaid,checkin,checkout);


        getBookingRequest.bookingById(id)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("firstname",equalTo(nameValue))
                .body("lastname",equalTo(lastnameValue))
                .body("totalprice",equalTo(totalprice))
                .body("depositpaid",equalTo(depositpaid))
                .body("bookingdates.checkin",equalTo(checkin))
                .body("bookingdates.checkout",equalTo(checkout));

    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({SchemaTest.class,AllTests.class})
    @DisplayName("Garantir o schema do retorno da listagem de reservas.")
    public void validaSchemaDaListagemDeReservas(){
        getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(HttpStatus.SC_OK)
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
                .statusCode(HttpStatus.SC_OK)
                .body(matchesJsonSchema(new File(Utils.getSchemaBasePath("booking","bookings"))));

    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({SchemaTest.class,AllTests.class})
    @DisplayName("Garantir o schema do retorno da reserva por id.")
    public void validaSchemaDaListagemDeReservaPorId(){
        getBookingRequest.bookingById(getBookingRequest.bookingFirstId())
                .then()
                .statusCode(HttpStatus.SC_OK)
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
                .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);

    }


}
