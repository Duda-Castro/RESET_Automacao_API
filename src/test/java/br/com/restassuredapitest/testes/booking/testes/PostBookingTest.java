package br.com.restassuredapitest.testes.booking.testes;

import br.com.restassuredapitest.base.BaseTest;
import br.com.restassuredapitest.suites.AcceptanceCriticalTest;
import br.com.restassuredapitest.suites.AllTests;
import br.com.restassuredapitest.suites.SchemaTest;
import br.com.restassuredapitest.testes.booking.requests.PostBookingRequest;
import br.com.restassuredapitest.utils.Utils;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

@Feature("Feature de cadastro de reservas")
public class PostBookingTest extends BaseTest {

    PostBookingRequest postBookingRequest = new PostBookingRequest();


    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, SchemaTest.class, AcceptanceCriticalTest.class})
    @DisplayName("Criar uma nova reserva.")
    public void validarCadastroDeNovaReserva(){

        postBookingRequest.bookingCreate("Jim","Brown",
                        111,true,"2018-01-01","2019-01-01")
                .then()
                .log().all()
                .statusCode(200)
                .body("size()",greaterThan(0))
                .time(lessThan(5L), TimeUnit.SECONDS);

    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({SchemaTest.class,AllTests.class})
    @DisplayName("Garantir o schema do retorno da postagem da reserva.")
    public void validaSchemaDaPostagemDeReserva(){

        postBookingRequest.bookingCreate("Jim","Brown",
                        111,true,"2018-01-01","2019-01-01")
                .then()
                .statusCode(200)
                .body(matchesJsonSchema(new File(Utils.getSchemaBasePath("booking","postBooking"))));


    }

}
