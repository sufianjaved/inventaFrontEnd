package utils;

public class ApplicationConfiguration {

    public static final String domain = DefaultConfiguration.getProperty("vin.application.domain");
    public static final String username = DefaultConfiguration.getProperty("vin.username");
    public static final String password = DefaultConfiguration.getProperty("vin.password");

    public static final String baseURL_InventaService = DefaultConfiguration.getProperty("baseURL_InventaService");
    public static final String baseURL_AdapterService = DefaultConfiguration.getProperty("baseURL_AdapterService");

    public static final String secretKey = DefaultConfiguration.getProperty("secretKey");
    public static final String expirationTime = DefaultConfiguration.getProperty("expirationTime");
    public static final String subject = DefaultConfiguration.getProperty("subject");

    public static final String mongoDB_UserName = DefaultConfiguration.getProperty("mongodb.username");
    public static final String mongoDB_Password = DefaultConfiguration.getProperty("mongodb.password");
    public static final String mongoDB_Host = DefaultConfiguration.getProperty("mongodb.host");
    public static final String mongoDB_Port = DefaultConfiguration.getProperty("mongodb.port");
    public static final String mongoDB_AuthDB = DefaultConfiguration.getProperty("mongodb.authentication-db");
    public static final String mongoDB_DBName = DefaultConfiguration.getProperty("mongodb.dbname");

    public static String getApplicationURL(){
        return domain;
    }

    public static String getUsername(){
        return username;
    }

    public static String getUserPassword(){ return password; }

    public static String getBaseURL_InventaService(){ return baseURL_InventaService; }

    public static String getBaseURL_AdapterService(){ return baseURL_AdapterService; }

    public static String getSecretKey(){ return secretKey; }

    public static String getExpirationTime(){ return expirationTime; }

    public static String getSubject(){ return subject; }

    public static String getMongoDB_UserName(){ return mongoDB_UserName; }

    public static String getMongoDB_Password(){ return mongoDB_Password; }

    public static String getMongoDB_Host(){ return mongoDB_Host; }

    public static String getMongoDB_Port(){ return mongoDB_Port; }

    public static String getMongoDB_AuthDB(){ return mongoDB_AuthDB; }

    public static String getMongoDB_DBName(){ return mongoDB_DBName; }
}
