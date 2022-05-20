package br.com.restassuredapitest.runners;

import br.com.restassuredapitest.testes.ping.testes.GetPingTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


    @RunWith(Categories.class)
    @Categories.IncludeCategory(br.com.restassuredapitest.suites.HealthCheck.class)
    @Suite.SuiteClasses({
            GetPingTest.class
    })

    public class HealthCheck {

    }

