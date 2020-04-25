package SpringBootMongoDB;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class GetController {


    @RequestMapping(path="/getpersons"
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

            ObjectId id = d.getObjectId("_id");
            String firstName = d.getString("firstName");
            String lastName = d.getString("lastName");
            int age = d.getInteger("age");
            String birthday = d.getString("birthday");
            String job = d.getString("job");
            String description = d.getString("description");


            p= new Person(id,firstName,lastName,age,birthday,job,description);
            personList.add(p);
        }

        System.out.println("Get request successful.");

        return personList;


    }
}
