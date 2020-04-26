package SpringBootMongoDB;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.ArrayList;

@RestController
public class PatchController {


    @RequestMapping(path="/patch/{id}"
            ,method = RequestMethod.PATCH
            , produces = MediaType.APPLICATION_JSON_VALUE
            ,consumes = MediaType.APPLICATION_JSON_VALUE)
    public Document patchPerson(@RequestBody Person person , @PathVariable String id)
    {
        Document documentToUpdate = new Document();

        MongoCollection mongoCollection = DBConnector.getCollection();

        FindIterable<Document> findIterable =mongoCollection.find();

        for(Document d : findIterable)
        {
            if(d.getObjectId("_id").toHexString().equals(id))
            {
                documentToUpdate=d;
            }
        }

        Document newDocument= new Document();

        String firstNameOfRequest = person.getFirstName();
        String lastNameOfRequest = person.getLastName();
        Integer ageOfRequest = person.getAge();
        String jobOfRequest = person.getJob();
        String descriptionOfRequest = person.getDescription();

        if(firstNameOfRequest==null)
        {
            firstNameOfRequest=documentToUpdate.getString("firstName");
        }
        if(lastNameOfRequest==null)
        {
            lastNameOfRequest=documentToUpdate.getString("lastName");
        }
        if(ageOfRequest==null)
        {
            ageOfRequest=documentToUpdate.getInteger("age");
        }
        if(jobOfRequest==null)
        {
            jobOfRequest=documentToUpdate.getString("job");
        }
        if(descriptionOfRequest==null)
        {
            descriptionOfRequest=documentToUpdate.getString("description");
        }

        newDocument.put("firstName",firstNameOfRequest);
        newDocument.put("lastName",lastNameOfRequest);
        newDocument.put("age",ageOfRequest);
        newDocument.put("job",jobOfRequest);
        newDocument.put("description",descriptionOfRequest);

        mongoCollection.replaceOne(documentToUpdate,newDocument);



        System.out.println("Patch request successful.");


        return newDocument;


    }
}
