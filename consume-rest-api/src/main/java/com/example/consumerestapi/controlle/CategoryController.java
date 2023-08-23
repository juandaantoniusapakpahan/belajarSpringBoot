package com.example.consumerestapi.controlle;


import com.example.consumerestapi.model.Category;
import com.example.consumerestapi.model.CategoryResponse;
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
@RequestMapping("/api/category")

public class CategoryController {
    Logger log = LoggerFactory.getLogger(CategoryController.class);

    @Value("${API.CATEGORY.URL}")
    private String API_CATEGORY_RUL;

    @Value("${SERVICE.USSD.BROADCAST.SIGNATURE}")
    private String SERVICE_USSD_BROADCAST_SIGNATURE;

    @Autowired
    @Qualifier("restTemplateCategory")
    RestTemplate restTemplate;




    @PostMapping(path = "/push")
    public ResponseEntity<?> create(@RequestBody Category category){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("user-agent","Application");
        headers.add("X-Key", SERVICE_USSD_BROADCAST_SIGNATURE);
        HttpEntity<Category> entity = new HttpEntity<Category>(category,headers);
        log.info("entity={}",entity.getBody());
        log.info("headers={}", entity.getHeaders());
        try{
            ResponseEntity<CategoryResponse> categoryResponseEntity = restTemplate.exchange(API_CATEGORY_RUL,
                    HttpMethod.POST,
                    entity,
                    CategoryResponse.class);
            log.info("response={}", categoryResponseEntity.getBody());

            return ResponseEntity.ok(categoryResponseEntity.getBody());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

}
