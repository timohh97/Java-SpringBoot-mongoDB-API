package SpringBootMongoDB;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class DeleteController {

    @RequestMapping(path="/delete/{id}"
            ,method = RequestMethod.DELETE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    public Document deletePerson(@PathVariable String id)
    {
        Document resultDocument= new Document();

        MongoCollection mongoCollection = DBConnector.getCollection();

        FindIterable<Document> findIterable =mongoCollection.find();

        for(Document d : findIterable)
        {
            if(d.getObjectId("_id").toHexString().equals(id))
            {
                resultDocument=d;
                mongoCollection.deleteOne(d);

            }
        }

        System.out.println("Delete request successful.");


        return resultDocument;


    }

    @RequestMapping(path="/delete/all"
            ,method = RequestMethod.DELETE)
    public String deleteAll()
    {
        
        MongoCollection mongoCollection = DBConnector.getCollection();

        FindIterable<Document> findIterable =mongoCollection.find();

        for(Document d : findIterable)
        {
            mongoCollection.deleteOne(d);
        }

        System.out.println("Delete request successful.");


        return "Deleted all persons.";


    }
}
