package br.com.restassuredapitest.runners;

import br.com.restassuredapitest.testes.booking.testes.DeleteBookingTest;
import br.com.restassuredapitest.testes.booking.testes.GetBookingTest;
import br.com.restassuredapitest.testes.booking.testes.PostBookingTest;
import br.com.restassuredapitest.testes.booking.testes.PutBookingTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(br.com.restassuredapitest.suites.AcceptanceCriticalTest.class)
@Suite.SuiteClasses({
        GetBookingTest.class,
        DeleteBookingTest.class,
        PostBookingTest.class,
        PutBookingTest.class
})

public class AcceptanceCriticalTest {



}
