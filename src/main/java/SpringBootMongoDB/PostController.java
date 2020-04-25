package SpringBootMongoDB;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PostController {

    @RequestMapping(path="/post"
            ,method = RequestMethod.POST
            , produces = MediaType.APPLICATION_JSON_VALUE
            , consumes = MediaType.APPLICATION_JSON_VALUE)
    public Person postPerson(@RequestBody Person person)
    {

        MongoCollection mongoCollection = DBConnector.getCollection();

        Document d = new Document();

        d.put("firstName",person.getFirstName());
        d.put("lastName",person.getLastName());
        d.put("age",person.getAge());
        d.put("job",person.getJob());
        d.put("description",person.getDescription());


        mongoCollection.insertOne(d);

        return person;


    }

}
