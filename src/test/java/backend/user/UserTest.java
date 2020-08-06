package backend.user;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.junit.Assert;
import org.junit.Test;
import utils.BaseClassBackEnd;

import java.io.IOException;

public class UserTest extends BaseClassBackEnd {

    public static final String USER_ENDPOINT = "/users/";
    //public static final String PAGINATION = "?page=0&size=100";
    //public static final String ALL_USERS = "getAllUsers";
    public static final String USER_DETAIL = "userDetail?_id=";
    public static final String USER_ADAPTER_lIST = "adapters/list?_id=";
    public static final String ALL_USER_TAGS = "tags/";
    public static final String USER_TAG = "getUserTag?userId=";
    public static final String USER_NOTE = "getUserNote?userId=";

    public static final String INSERT_NOTE = "insertNote";
    public static final String INSERT_TAG = "insertTag";
    public static final String DELETE_SINGLE_TAG = "deleteSingleTag/?userId=";
    public static final String DELETE_BULK_TAG = "deleteBulkTag";

    public static final String DELETE_NOTE = "deleteNote?userId=";

    public static final String SINGLE_TAG_NAME = "Automation_Tag_Number_3303";
    public static final String USER_ID = "5ef3f81e53c00d7011abe76e";

    public static final String USER_ADAPTER_DATA = "adapter/data?_id=";
    public static final String USER_GENERAL_DETAILS = "general?_id=";


    @Test
    public void getAllUsers() throws IOException {

        //Connector conn = new Connector();
        //conn.testConnection();

        HttpGet get = new HttpGet(BASE_ENDPOINT + USER_ENDPOINT + "getAllUsers" + "?page=0&size=100");
        get.setHeader("Authorization", "Bearer " + token);
        response = client.execute(get);
        int actualStatus = response.getStatusLine().getStatusCode();
        Assert.assertEquals("Invalid Status in Response: ", actualStatus, HttpStatus.SC_OK);
    }
}
