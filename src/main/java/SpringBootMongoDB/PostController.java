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

@RestController
public class PostController {

    @RequestMapping(path="/post"
            ,method = RequestMethod.POST
            , produces = MediaType.APPLICATION_JSON_VALUE)
    public void postPerson()
    {

        MongoCollection mongoCollection = DBConnector.getCollection();

    }

}
