package br.com.restassuredapitest.testes.booking.testes;

import br.com.restassuredapitest.base.BaseTest;
import br.com.restassuredapitest.suites.*;
import br.com.restassuredapitest.testes.booking.requests.DeleteBookingRequest;
import br.com.restassuredapitest.testes.booking.requests.GetBookingRequest;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Feature("Feature de exclusão de reservas")
public class DeleteBookingTest extends BaseTest {

    DeleteBookingRequest deleteBookingRequest = new DeleteBookingRequest();
    GetBookingRequest getBookingRequest = new GetBookingRequest();

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, SecurityTests.class})
    @DisplayName("Não permitir a exclusão de uma reserva sem autorização.")
    public void barrarExclusaoDeReservaSemHeader(){

        deleteBookingRequest.excluiReservaSemHeader(getBookingRequest.bookingFirstId())
                .then()
                .statusCode(403);

    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Category({AllTests.class, AcceptanceCriticalTest.class})
    @DisplayName("Excluir um reserva com sucesso usando token.")
    public void validarExclusaoDeReservaComToken(){

        deleteBookingRequest.excluiReservaComCookie(getBookingRequest.bookingFirstId(),"admin","password123")
                .then()
                .statusCode(201);

    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Category({AllTests.class, AcceptanceCriticalTest.class})
    @DisplayName("Excluir um reserva com sucesso usando basic.")
    public void validarExclusaoDeReservaComBasic(){

        deleteBookingRequest.excluiReservaComAuthorisation(getBookingRequest.bookingFirstId())
                .then()
                .statusCode(201);

    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceExceptionTest.class})
    @DisplayName("Não permitir excluir um reserva que não existe")
    public void validarExclusaoDeReservaInexistente(){

        deleteBookingRequest.excluiReservaComCookie( -1,"admin","password123")
                .then()
                .statusCode(405);

    }

}
