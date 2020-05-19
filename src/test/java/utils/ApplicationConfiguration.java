package utils;

public class ApplicationConfiguration {

    public static final String domain = DefaultConfiguration.getProperty("vin.application.domain");
    public static final String username = DefaultConfiguration.getProperty("vin.username");
    public static final String password = DefaultConfiguration.getProperty("vin.password");

    public static String getApplicationURL(){
        return domain;
    }

    public static String getUsername(){
        return username;
    }

    public static String getUserPassword(){
        return password;
    }
}
