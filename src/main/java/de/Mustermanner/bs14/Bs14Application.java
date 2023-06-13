package de.Mustermanner.bs14;

import de.Mustermanner.bs14.repository.PurchaseRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = PurchaseRepository.class)
public class Bs14Application {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Bs14Application.class, args);
	}
}
