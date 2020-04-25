package SpringBootMongoDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootMongoDbApplication  {




	public static void main(String[] args) {


		SpringApplication.run(SpringBootMongoDbApplication.class, args);
		System.out.println("Spring Boot server started successfully.");
	}



}
