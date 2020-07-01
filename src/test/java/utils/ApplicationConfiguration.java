package utils;

public class ApplicationConfiguration {

    public static final String domain = DefaultConfiguration.getProperty("vin.application.domain");
    public static final String username = DefaultConfiguration.getProperty("vin.username");
    public static final String password = DefaultConfiguration.getProperty("vin.password");

    public static final String baseURL = DefaultConfiguration.getProperty("baseURL");

    public static final String secretKey = DefaultConfiguration.getProperty("secretKey");
    public static final String expirationTime = DefaultConfiguration.getProperty("expirationTime");
    public static final String subject = DefaultConfiguration.getProperty("subject");

    public static String getApplicationURL(){
        return domain;
    }

    public static String getUsername(){
        return username;
    }

    public static String getUserPassword(){

        return password;
    }

    public static String getBaseURL(){

        return baseURL;
    }

    public static String getSecretKey(){

        return secretKey;
    }

    public static String getExpirationTime(){

        return expirationTime;
    }

    public static String getSubject(){

        return subject;
    }
}
