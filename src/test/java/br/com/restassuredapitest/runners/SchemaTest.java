package br.com.restassuredapitest.runners;

import br.com.restassuredapitest.testes.booking.testes.GetBookingTest;
import br.com.restassuredapitest.testes.booking.testes.PostBookingTest;
import br.com.restassuredapitest.testes.ping.testes.GetPingTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(br.com.restassuredapitest.suites.SchemaTest.class)
@Suite.SuiteClasses({
        PostBookingTest.class,
        GetBookingTest.class
})
public class SchemaTest {
}
