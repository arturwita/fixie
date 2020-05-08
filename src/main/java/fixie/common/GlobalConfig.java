package fixie.common;

public class GlobalConfig {
    // General
    public static final String PROTOCOL = "HTTPS";
    public static final String HOST = "://localhost:";

    // Microservices' ports
    public static final String USER_SERVICE_PORT = "8000";
    public static final String AUTHORIZATION_SERVICE_PORT = "8001";
    public static final String DICTIONARY_SERVICE_PORT = "8002";
    public static final String PART_SERVICE_PORT = "8003";
    public static final String REQUEST_SERVICE_PORT = "8004";
    public static final String ACTIVITY_SERVICE_PORT = "8005";

    // Microservices' URLs
    public static final String USER_SERVICE_URL = PROTOCOL + HOST + USER_SERVICE_PORT;
    public static final String AUTHORIZATION_SERVICE_URL = PROTOCOL + HOST + AUTHORIZATION_SERVICE_PORT;
    public static final String DICTIONARY_SERVICE_URL = PROTOCOL + HOST + DICTIONARY_SERVICE_PORT;
    public static final String PART_SERVICE_URL = PROTOCOL + HOST + PART_SERVICE_PORT;
    public static final String REQUEST_SERVICE_URL = PROTOCOL + HOST + REQUEST_SERVICE_PORT;
    public static final String ACTIVITY_SERVICE_URL = PROTOCOL + HOST + ACTIVITY_SERVICE_PORT;
}
