package utils;

import com.auth0.jwt.JWT;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.After;
import org.junit.Before;

import java.io.IOException;
import java.util.Date;
import java.util.Random;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

public class BaseClassBackEnd {

    protected CloseableHttpClient client;
    protected CloseableHttpResponse response;

    protected static final String BASE_ENDPOINT = ApplicationConfiguration.getBaseURL_InventaService();
    protected static final String BASE_ENDPOINT_ADAPTER = ApplicationConfiguration.getBaseURL_AdapterService();

    //For Token
    public static final String SUBJECT = ApplicationConfiguration.getSubject();
    public static final int EXPIRATION_TIME = Integer.parseInt(ApplicationConfiguration.getExpirationTime());
    public static final String SECRET = ApplicationConfiguration.getSecretKey();

    protected static final String token = JWT.create()
            .withSubject(SUBJECT)
            .withExpiresAt(new Date(System.currentTimeMillis()+ EXPIRATION_TIME))
            .sign(HMAC512(SECRET.getBytes()));



    static Random rand = new Random();
    protected static final  int value = rand.nextInt(5000);

    @Before
    public void setup(){
        client  = HttpClientBuilder.create().build();
    }

    @After
    public void closeResources() throws IOException {
        client.close();
        response.close();
    }

}
