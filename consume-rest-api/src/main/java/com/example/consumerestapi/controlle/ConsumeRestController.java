package com.example.consumerestapi.controlle;

import com.example.consumerestapi.model.Quote;
import com.example.consumerestapi.model.QuoteI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class ConsumeRestController {
    @Autowired RestTemplate restTemplate;

    @Value("${API.QUOTE.URL}") private String API_QUOTE_URL;


    @GetMapping
    public HttpEntity<QuoteI[]> allQuotes(){
        ResponseEntity<QuoteI[]> responseEntity = restTemplate.getForEntity(API_QUOTE_URL, QuoteI[].class);
        QuoteI[] objects = responseEntity.getBody();
        return new ResponseEntity<>(objects, HttpStatus.OK);
    }


    @GetMapping("/random")
    public ResponseEntity<QuoteI> getQuoteRandom(){
        QuoteI quote = restTemplate.getForObject(API_QUOTE_URL.concat("/random"),QuoteI.class);
        return new ResponseEntity<>(quote,HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<?> listQuotes(){
     try {
         ResponseEntity<List<QuoteI>> responseEntity = restTemplate.exchange(API_QUOTE_URL, HttpMethod.GET, null,
                 new ParameterizedTypeReference<List<QuoteI>>() {});
         List<QuoteI> quotes = responseEntity.getBody();
         return ResponseEntity.ok(quotes);
     }catch (Exception e){
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
     }
    }
}
