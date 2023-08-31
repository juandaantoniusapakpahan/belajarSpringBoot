package com.example.springbootasync.controller;


import com.example.springbootasync.model.GithubUser;
import com.example.springbootasync.service.GithubLookUpService;
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
public class AsyncController {
    @Autowired
    GithubLookUpService githubLookUpService;

    @GetMapping("/github/user")
    public ResponseEntity<List<GithubUser>> findGithubUser() throws Exception{
        long start = System.currentTimeMillis();

        CompletableFuture<GithubUser> page1 = githubLookUpService.findUser("PivotalSoftware");
        CompletableFuture<GithubUser> page2 = githubLookUpService.findUser("CloudFoundry");
        CompletableFuture<GithubUser> page3 = githubLookUpService.findUser("Spring-Projects");

        CompletableFuture.allOf(page1,page2, page1);

        log.info("Elapsed time: "+(System.currentTimeMillis()-start));
        log.info("--> "+ page1.get());
        log.info("--> "+ page2.get());
        log.info("--> "+ page3.get());
        List<GithubUser>list = new ArrayList<>();
        list.add(page1.get());
        list.add(page2.get());
        list.add(page3.get());

        return ResponseEntity.ok(list);
    }
}
