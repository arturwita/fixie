package fixie.dictionary_service;

import fixie.common.GlobalConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Collections;

@SpringBootApplication
public class DictionaryServiceApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(DictionaryServiceApplication.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", GlobalConfig.DICTIONARY_SERVICE_PORT));
        app.run(args);
    }

}
