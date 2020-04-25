package SpringBootMongoDB;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.Document;

import javax.swing.*;


public class DBConnector {

    public static FindIterable<Document> getAllDocuments()
    {
        String password = new PasswordGetter().getPassword();

        MongoClientURI uri = new MongoClientURI(
                "mongodb+srv://mainuser:"+password+"@cluster-0gtou.mongodb.net/test?retryWrites=true&w=majority");

        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase database = mongoClient.getDatabase("test");

        MongoCollection<Document> mongoCollection = database.getCollection("posts");

        FindIterable<Document> findIterable=mongoCollection.find();

        return findIterable;




    }
}
