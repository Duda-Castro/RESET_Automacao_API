package br.com.restassuredapitest.runners;

import br.com.restassuredapitest.testes.auth.testes.PostAuthTest;
import br.com.restassuredapitest.testes.booking.testes.GetBookingTest;
import br.com.restassuredapitest.testes.booking.testes.PutBookingTest;
import br.com.restassuredapitest.testes.ping.testes.GetPingTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(br.com.restassuredapitest.suites.AllTests.class)
@Suite.SuiteClasses({
        GetBookingTest.class,
        GetPingTest.class,
        PostAuthTest.class,
        PutBookingTest.class
})
public class AllTests {

}
