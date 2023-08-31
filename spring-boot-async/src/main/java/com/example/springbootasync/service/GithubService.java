package com.example.springbootasync.service;

import com.example.springbootasync.model.Github;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class GithubService {

    @Autowired
    RestTemplate restTemplate;

    @Async
    public CompletableFuture<Github> getInfo() throws InterruptedException{
        String url = "https://api.github.com";
        Github result = restTemplate.getForObject(url, Github.class);
        log.info("artificial delay of 2s ....");

      return CompletableFuture.completedFuture(result);
    }
}
