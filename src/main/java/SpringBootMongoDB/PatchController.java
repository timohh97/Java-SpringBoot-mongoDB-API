package SpringBootMongoDB;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class PatchController {


    @RequestMapping(path="/patch/{id}"
            ,method = RequestMethod.PATCH
            , produces = MediaType.APPLICATION_JSON_VALUE
            ,consumes = MediaType.APPLICATION_JSON_VALUE)
    public Document patchPerson(@RequestBody Person person , @PathVariable String id)
    {
        Document newDocument= new Document();

        newDocument.put("_id",new ObjectId(id));
        newDocument.put("firstName",person.getFirstName());
        newDocument.put("lastName",person.getLastName());
        newDocument.put("age",person.getAge());
        newDocument.put("job",person.getJob());
        newDocument.put("description",person.getDescription());

        MongoCollection mongoCollection = DBConnector.getCollection();

        FindIterable<Document> findIterable =mongoCollection.find();

        for(Document d : findIterable)
        {
            if(d.getObjectId("_id").toHexString().equals(id))
            {
                mongoCollection.updateOne(d,newDocument,true);
            }
        }

        System.out.println("Delete request successful.");


        return newDocument;


    }
}
