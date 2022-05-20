package br.com.restassuredapitest.runners;

import br.com.restassuredapitest.testes.auth.testes.PostAuthTest;

import br.com.restassuredapitest.testes.booking.testes.DeleteBookingTest;
import br.com.restassuredapitest.testes.booking.testes.GetBookingTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(br.com.restassuredapitest.suites.AcceptanceCriticalTest.class)
@Suite.SuiteClasses({
        GetBookingTest.class,
        DeleteBookingTest.class
})

public class AcceptanceCriticalTest {



}
