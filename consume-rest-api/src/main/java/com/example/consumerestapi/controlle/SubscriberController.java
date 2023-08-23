package com.example.consumerestapi.controlle;


import com.example.consumerestapi.model.SubscribeRequest;
import com.example.consumerestapi.model.SubscribeResponse;
import com.example.consumerestapi.model.UssdBroadcastRequest;
import com.example.consumerestapi.model.UssdBroadcastResponse;
import org.slf4j.Logger;
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

@RestController
@RequestMapping("/api/subscribe")
public class SubscriberController {
    static final Logger logger = LoggerFactory.getLogger(ConsumingUssdBroadcastController.class);

    @Autowired
    @Qualifier("restTemplateSubscribe")
    private RestTemplate restTemplate;

    @Value("${API.SUBSCRIBE.URL}")
    private String API_SUBSCRIBE_URL;

    @Value("${SERVICE.USSD.BROADCAST.SIGNATURE}") private String SERVICE_USSD_BROADCAST_SIGNATURE;

    @PostMapping(path = "/push")
    public ResponseEntity<?> pushSubscribe(@RequestBody SubscribeRequest subscribeRequest){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("user-agent", "Application");
        headers.add("X-signature", SERVICE_USSD_BROADCAST_SIGNATURE);
        HttpEntity<SubscribeRequest> entity = new HttpEntity<SubscribeRequest>(subscribeRequest,headers);
        logger.info("entity={}", entity.getBody());
        logger.info("headers={}", entity.getHeaders());

        try{
            ResponseEntity<SubscribeResponse> responseEntity = restTemplate.exchange(API_SUBSCRIBE_URL,
                    HttpMethod.POST,entity,SubscribeResponse.class);
            logger.info("response={}", responseEntity.getBody());
            return ResponseEntity.ok(responseEntity.getBody());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
