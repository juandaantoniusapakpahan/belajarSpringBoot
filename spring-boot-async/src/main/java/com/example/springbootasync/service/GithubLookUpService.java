package com.example.springbootasync.service;


import com.example.springbootasync.model.GithubUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class GithubLookUpService {

    @Autowired
    RestTemplate restTemplate;

    @Async
    public CompletableFuture<GithubUser> findUser(String user) throws InterruptedException{
        log.info("Looking up " + user );
        String url = String.format("https://api.github.com/users/%s", user);
        GithubUser result = restTemplate.getForObject(url, GithubUser.class);
        log.info("artificial delay of 2s....");
        Thread.sleep(2000L);
        return CompletableFuture.completedFuture(result);
    }
}
