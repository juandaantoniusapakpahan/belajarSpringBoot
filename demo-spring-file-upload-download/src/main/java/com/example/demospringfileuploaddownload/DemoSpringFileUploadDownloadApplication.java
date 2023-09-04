package com.example.demospringfileuploaddownload;

import com.example.demospringfileuploaddownload.config.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class DemoSpringFileUploadDownloadApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringFileUploadDownloadApplication.class, args);
	}

}
