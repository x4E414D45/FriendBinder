package edu.cpp.Rafikie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import edu.cpp.Rafikie.data.provider.FSUserManager;
import edu.cpp.Rafikie.data.provider.UserManager;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class App {

	private static final Logger logger = LoggerFactory.getLogger(App.class.getName());
  
    @Bean
    public UserManager userManager() {
        UserManager userManager =  new FSUserManager();
        return userManager;
    }

    public static void main(String[] args) throws Exception {
        // Run Spring Boot
        SpringApplication.run(App.class, args);
    }
}
