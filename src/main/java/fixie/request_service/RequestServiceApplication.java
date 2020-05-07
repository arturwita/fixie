package fixie.request_service;

import fixie.common.GlobalConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Collections;

@SpringBootApplication
@ComponentScan(basePackages = {"fixie.common", "fixie.request_service"})
public class RequestServiceApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(RequestServiceApplication.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", GlobalConfig.REQUEST_SERVICE_PORT));
        app.run(args);
    }
}
