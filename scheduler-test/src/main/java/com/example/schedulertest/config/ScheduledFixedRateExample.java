package com.example.schedulertest.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableAsync
public class ScheduledFixedRateExample {
    // If we want to support parallel behavior in scheduled tasks, we need to add the @Async annotation:
//    @Async
//    @Scheduled(fixedRate = 1000)
//    public void scheduleFixedRateTaskAsync() throws InterruptedException{
//        System.out.println(
//                "Fixed rate task async - " + System.currentTimeMillis() / 1000);
//        Thread.sleep(2000);
//    }



}
