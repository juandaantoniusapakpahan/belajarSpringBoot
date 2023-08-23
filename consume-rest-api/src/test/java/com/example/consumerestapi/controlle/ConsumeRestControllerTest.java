package com.example.consumerestapi.controlle;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

class ConsumeRestControllerTest {

    @Autowired
    RestTemplate restTemplate;

    @Value("${API.QUOTE.URL}") private String API_QUOTE_URL;

    private static WireMockServer wireMockServer;


    @BeforeAll
     static void setUp() {
        wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig()
                .dynamicPort());
        wireMockServer.start();
    }

    @AfterAll
   static void tearDown() {
        wireMockServer.stop();
    }

    @Test
    void testWireMock(){
       System.out.println( wireMockServer.port());
        assertTrue(wireMockServer.isRunning());
    }

    @Test
    void allQuotes() {
    }

    @Test
    void getQuoteRandom() {
    }
}