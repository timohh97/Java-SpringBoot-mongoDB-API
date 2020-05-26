package SpringBootMongoDB;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.bson.types.ObjectId.*;

@RestController
public class GetController {


    @RequestMapping(path="/"
            ,method = RequestMethod.GET
            , produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> getPersonList()
    {

        MongoCollection mongoCollection = DBConnector.getCollection();

        FindIterable<Document> findIterable=mongoCollection.find();

        List<Person> personList = new ArrayList<Person>();

        for(Document d : findIterable)
        {
            Person p;

            String id = d.getObjectId("_id").toHexString();
            String firstName = d.getString("firstName");
            String lastName = d.getString("lastName");
            Integer age = d.getInteger("age");
            String job = d.getString("job");
            String description = d.getString("description");

            if(firstName==null)
            {
                firstName="";
            }

            if(lastName==null)
            {
                lastName="";
            }

            if(age==null)
            {
                age=-1;
            }



            if(job==null)
            {
                job="";
            }

            if(description==null)
            {
                description="";
            }


            p= new Person(id,firstName,lastName,age,job,description);
            personList.add(p);

        }

        System.out.println("Get request successful.");


        return personList;


    }

    @RequestMapping(path="/get/{id}"
            ,method = RequestMethod.GET
            , produces = MediaType.APPLICATION_JSON_VALUE)
    public Document getPersonById(@PathVariable String id)
    {

        Document resultDocument = new Document();


        MongoCollection mongoCollection = DBConnector.getCollection();

        FindIterable<Document> findIterable=mongoCollection.find();

        for(Document d : findIterable)
        {
             if(d.getObjectId("_id").toHexString().equals(id))
             {
                 resultDocument= d;
             }

        }

        System.out.println("Get request successful.");


        return resultDocument;


    }
}
