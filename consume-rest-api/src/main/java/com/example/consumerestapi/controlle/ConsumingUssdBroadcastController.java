package com.example.consumerestapi.controlle;

import com.example.consumerestapi.model.UssdBroadcastRequest;
import com.example.consumerestapi.model.UssdBroadcastResponse;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import org.slf4j.Logger;


@RestController
@RequestMapping("/api/ussd")
public class ConsumingUssdBroadcastController {

    static final Logger logger = LoggerFactory.getLogger(ConsumingUssdBroadcastController.class);
    @Autowired
    @Qualifier("restTemplateUssd")
    RestTemplate restTemplate;

    @Value("${API.USSD.BROADCAST.URL}") private String API_USSD_BROADCAST_URL;
    @Value("${SERVICE.USSD.BROADCAST.SIGNATURE}") private String SERVICE_USSD_BROADCAST_SIGNATURE;

    @PostMapping("/push")
    public ResponseEntity<?> pushUssd(@RequestBody UssdBroadcastRequest ussdBroadcastRequest){

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("user-agent", "Application");
        headers.add("X-signature", SERVICE_USSD_BROADCAST_SIGNATURE);

        HttpEntity<UssdBroadcastRequest> entity = new HttpEntity<UssdBroadcastRequest>(ussdBroadcastRequest,headers);
        logger.info("entity={}", entity.getBody());
        logger.info("headers={}", entity.getHeaders());

        try{
            ResponseEntity<UssdBroadcastResponse> responseEntity = restTemplate.exchange(API_USSD_BROADCAST_URL,
                    HttpMethod.POST,entity,UssdBroadcastResponse.class);
            logger.info("response={}", responseEntity.getBody());
            return ResponseEntity.ok(responseEntity.getBody());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
