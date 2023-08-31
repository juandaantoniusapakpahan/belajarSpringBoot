package com.example.springbootasync.controller;

import com.example.springbootasync.model.Github;
import com.example.springbootasync.service.GithubService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@Slf4j
public class AsyncGithubController {
    @Autowired
    GithubService githubService;

    @GetMapping("/github/info")
    public ResponseEntity<List<Github>> getInfo() throws Exception{
        long start = System.currentTimeMillis();

        CompletableFuture<Github> result1 = githubService.getInfo();
        CompletableFuture<Github> result2 = githubService.getInfo();
        CompletableFuture<Github> result3 = githubService.getInfo();

        CompletableFuture.allOf(result1,result2,result3);
        log.info("Elapsed time: "+(System.currentTimeMillis()-start));
        log.info("--> "+ result1.get());
        log.info("--> "+ result2.get());
        log.info("--> "+ result3.get());
        List<Github>list = new ArrayList<>();
        list.add(result1.get());
        list.add(result2.get());
        list.add(result3.get());

        return ResponseEntity.ok(list);
    }
}
