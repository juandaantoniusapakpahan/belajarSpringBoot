package com.example.springdatamysql;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import jakarta.annotation.PostConstruct;

import org.slf4j.Logger;

public class AllStartupExampleBean implements InitializingBean {
    private static final Logger LOG = LoggerFactory.getLogger(AllStartupExampleBean.class);
    public AllStartupExampleBean(){
        LOG.info("Constructor");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        LOG.info("InitializingBean");
    }

    @PostConstruct
    public void postConstruct(){
        LOG.info("PostConstruct");
    }

    public void init(){
        LOG.info("init-method");
    }
}
