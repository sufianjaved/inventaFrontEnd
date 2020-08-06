package utils.inventa.common;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;

public class ConnectDB {

    public String collectionId;
    public String name;

    public static String getCollection(DBCollection collection) {
        DBObject dbObject = collection.findOne();
        ObjectId collectionId = (ObjectId) dbObject.get("_id");
        // System.out.println(collectionId.toString());
        return collectionId.toString();
    }

    public static String getSavedQueryCollection(DBCollection collection, String type) {
        DBObject query = new BasicDBObject("type", type);
        DBObject dbObject = collection.findOne(query);
        String queryName = (String) dbObject.get("name");
        System.out.println(queryName.toString());
        return queryName.toString();
    }
}
