package fixie.dictionary_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Collections;

@SpringBootApplication
public class DictionaryServiceApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(fixie.dictionary_service.DictionaryServiceApplication.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", "8002"));
        app.run(args);
    }
}
