package br.com.restassuredapitest.testes.booking.testes;

import br.com.restassuredapitest.base.BaseTest;
import br.com.restassuredapitest.suites.AcceptanceCriticalTest;
import br.com.restassuredapitest.suites.AcceptanceExceptionTest;
import br.com.restassuredapitest.suites.AllTests;
import br.com.restassuredapitest.suites.SchemaTest;
import br.com.restassuredapitest.testes.booking.requests.PostBookingRequest;
import br.com.restassuredapitest.utils.Utils;
import com.github.javafaker.Faker;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.*;

@Feature("Feature de cadastro de reservas")
public class PostBookingTest extends BaseTest {

    PostBookingRequest postBookingRequest = new PostBookingRequest();


    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, AcceptanceCriticalTest.class})
    @DisplayName("Criar uma nova reserva com sucesso.")
    public void validarCadastroDeNovaReserva(){

        String firstname = new Faker().lordOfTheRings().character();
        String lastname = "The Mighty";
        int totalprice = new Faker().random().nextInt(0,1000);
        boolean depositpaid = true;
        String checkin = "2022-0" + new Faker().random().nextInt(1,9) + "-01";
        String checkout = "2023-0" + new Faker().random().nextInt(1,9) + "-01";

        postBookingRequest.bookingCreate(firstname,lastname,
                        totalprice,depositpaid,checkin,checkout)
                .then()
                .statusCode(200)
                .body("size()",greaterThan(0))
                .body("booking.firstname",equalTo(firstname))
                .body("booking.lastname",equalTo(lastname))
                .body("booking.totalprice",equalTo(totalprice))
                .body("booking.depositpaid",equalTo(depositpaid))
                .body("booking.bookingdates.checkin",equalTo(checkin))
                .body("booking.bookingdates.checkout",equalTo(checkout));

    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, AcceptanceExceptionTest.class})
    @DisplayName("Falhar ao fazer a criação de reserva com payload inválido.")
    public void validarCadastroDeNovaReservaComPayloadInvalido(){

        postBookingRequest.bookingCreateInvalid()
                .then()
                .statusCode(500);

    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, AcceptanceExceptionTest.class})
    @DisplayName("Falhar ao fazer uma reserva enviando mais parâmetros no payload.")
    public void validarCadastroDeNovaReservaComInfoExtra(){
        String firstname = new Faker().lordOfTheRings().character();
        String lastname = "The Mighty";
        int totalprice = new Faker().random().nextInt(0,1000);
        boolean depositpaid = true;
        String checkin = "2022-0" + new Faker().random().nextInt(1,9) + "-01";
        String checkout = "2023-0" + new Faker().random().nextInt(1,9) + "-01";
        int peso = new Faker().random().nextInt(0,150);
        double altura = 1.75;
        String signo = new Faker().gameOfThrones().house();
        String pet = ""+new Faker().animal()+"";
        postBookingRequest.bookingCreateInfoExtra(firstname,lastname,
                        totalprice,depositpaid,checkin,checkout,peso,
                        altura,signo,pet)
                .then()
                .statusCode(500)
                .body("size()",greaterThan(0))
                .body("booking.firstname",equalTo(firstname))
                .body("booking.lastname",equalTo(lastname))
                .body("booking.totalprice",equalTo(totalprice))
                .body("booking.depositpaid",equalTo(depositpaid))
                .body("booking.bookingdates.checkin",equalTo(checkin))
                .body("booking.bookingdates.checkout",equalTo(checkout))
                .body("booking.peso",equalTo(peso))
                .body("booking.altura",equalTo(altura))
                .body("booking.signo",equalTo(signo))
                .body("booking.pet",equalTo(pet));

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceExceptionTest.class})
    @DisplayName("Validar a criação de mais de uma reserva em sequência.")
    public void validarCadastroDeDuasNovasReservas(){

        String firstname = new Faker().lordOfTheRings().character();
        String lastname = "The Mighty";
        int totalprice = new Faker().random().nextInt(0,1000);
        boolean depositpaid = true;
        String checkin = "2022-0" + new Faker().random().nextInt(1,9) + "-01";
        String checkout = "2023-0" + new Faker().random().nextInt(1,9) + "-01";

        postBookingRequest.bookingCreate(firstname,lastname,
                        totalprice,depositpaid,checkin,checkout)
                .then()
                .statusCode(200)
                .body("size()",greaterThan(0))
                .body("booking.firstname",equalTo(firstname))
                .body("booking.lastname",equalTo(lastname))
                .body("booking.totalprice",equalTo(totalprice))
                .body("booking.depositpaid",equalTo(depositpaid))
                .body("booking.bookingdates.checkin",equalTo(checkin))
                .body("booking.bookingdates.checkout",equalTo(checkout));

        String firstname2 = new Faker().lordOfTheRings().character();
        String lastname2 = "The Mighty";
        int totalprice2 = new Faker().random().nextInt(0,1000);
        boolean depositpaid2 = true;
        String checkin2 = "2022-0" + new Faker().random().nextInt(1,9) + "-01";
        String checkout2 = "2023-0" + new Faker().random().nextInt(1,9) + "-01";

        postBookingRequest.bookingCreate(firstname2,lastname2,
                        totalprice2,depositpaid2,checkin2,checkout2)
                .then()
                .statusCode(200)
                .body("size()",greaterThan(0))
                .body("booking.firstname",equalTo(firstname2))
                .body("booking.lastname",equalTo(lastname2))
                .body("booking.totalprice",equalTo(totalprice2))
                .body("booking.depositpaid",equalTo(depositpaid2))
                .body("booking.bookingdates.checkin",equalTo(checkin2))
                .body("booking.bookingdates.checkout",equalTo(checkout2));

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
