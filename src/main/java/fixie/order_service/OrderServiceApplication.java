package fixie.order_service;

import fixie.common.GlobalConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Collections;

@SpringBootApplication
@ComponentScan(basePackages = {"fixie.common", "fixie.order_service"})
public class OrderServiceApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(OrderServiceApplication.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", GlobalConfig.REQUEST_SERVICE_PORT));
        app.run(args);
    }
}
