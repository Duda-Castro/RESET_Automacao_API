package br.com.restassuredapitest.runners;

import br.com.restassuredapitest.testes.booking.testes.DeleteBookingTest;
import br.com.restassuredapitest.testes.booking.testes.PutBookingTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(br.com.restassuredapitest.suites.SecurityTests.class)
@Suite.SuiteClasses({
        PutBookingTest.class,
        DeleteBookingTest.class
})

public class SecurityTests {
}
