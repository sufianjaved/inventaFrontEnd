package utils.inventa.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Connector {

    @Autowired
    MongoTemplate mongoTemplate;

    @Value("${dbName}")
    private String dbName;

    public void testConnection(){
        String propTest = dbName;
        System.out.println("dbName is "+dbName);
        Query query = new Query();
        Criteria criteria = Criteria.where("userName").is("admininventa");
        query.addCriteria(criteria);
        Map map = new HashMap();
        map.put("key1","value1");
        map.put("key2","value2");
        mongoTemplate.save((Object)map,"conn_test");
//        mongoTemplate.find(query, Document.class,"admin_users");
        System.out.println("I am here!");
    }
}
