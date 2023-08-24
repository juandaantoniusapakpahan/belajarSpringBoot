package com.example.schedulertest.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SpringConfig
{

    Logger log = LoggerFactory.getLogger(SpringConfig.class);
    //    @Scheduled(fixedDelay = 1000)
//    public void scheduleFixedDelayTask(){
//        System.out.println(
//                "Fixed delay task - " + System.currentTimeMillis() / 1000
//        );
//    }

//    @Scheduled(fixedRate = 1000)
//    public void scheduleFixedRateTask(){
//        System.out.println(
//                "Fixed rate task - " + System.currentTimeMillis() / 1000
//        );
//    }

//    @Scheduled(fixedDelay = 1000, initialDelay = 1000)
//    public void scheduleFixedRateWithInitialDelayTask(){
//        long now = System.currentTimeMillis() / 1000;
//        System.out.println(
//                "Fixed rate task with one second initial delay - " + now);
//    }

    @Scheduled(cron = "* * 16 * * ?")
    public void scheduleTaskUsingCronExpression() {
        long now = System.currentTimeMillis() / 1000;
        log.info("schedule tasks using cron jobs - " + now);
    }
}
