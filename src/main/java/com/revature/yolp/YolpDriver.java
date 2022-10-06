package com.revature.yolp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/*
 * @SpringBootApplication enable this three features:
 * @EnableAutoConfiguration: automatically creates and registers beans based on both the included jar files in the classpath and the beans defined by us.
 * @ComponentScan: enables Spring to scan for things like configurations, controllers, services, and other components we define.
 * @Configuration: allow to register extra beans in the context or import additional configuration classes
 */
@SpringBootApplication
public class YolpDriver extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(YolpDriver.class, args);
    }
}
