package com.spring.fileupload.configs;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class ResourceWebConfig implements WebMvcConfigurer {

    final private String LOCATION;

    public ResourceWebConfig(@Value("${app.file.storage.mapping}") String LOCATION){
        this.LOCATION = LOCATION;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.
                addResourceHandler("/uploads/**")
                        .addResourceLocations(LOCATION);
    }
}
