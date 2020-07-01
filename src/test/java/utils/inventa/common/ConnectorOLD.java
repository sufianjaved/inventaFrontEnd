package utils.inventa.common;

import java.sql.*;

import utils.DefaultConfiguration;

public class ConnectorOLD {

    public static Connection connection = null;

    private static String url;
    private static String login;
    private static String password;
    private static String server;
    private static String port;
    private static String driver;
    private static String dbname;
    private static String host;

    public void getPropValues(){

        try {
            // get the property value and print it out
            server = DefaultConfiguration.getProperty("db.server");
            port = DefaultConfiguration.getProperty("db.port");
            url = DefaultConfiguration.getProperty("db.url");
            login = DefaultConfiguration.getProperty("db.login");
            password = DefaultConfiguration.getProperty("db.password");
            driver = DefaultConfiguration.getProperty("db.driver");
            dbname = DefaultConfiguration.getProperty("db.dbname");
            setHost(url + "://" + server + ":" + port + "/" + dbname);

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }


    public Connection getConnection() throws Exception {
        this.getPropValues();
        Class.forName(driver);
        return DriverManager.getConnection(getHost(), login, password);
    }


    public static String getHost() {
        return host;
    }


    public static void setHost(String host) {
        ConnectorOLD.host = host;
    }
}
