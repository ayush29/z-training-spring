package com.ayush.ztrainingspring;

import com.ayush.ztrainingspring.user_auth.User;
import com.ayush.ztrainingspring.user_auth.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ZTrainingSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZTrainingSpringApplication.class, args);
	}
//	@Bean
//	public CommandLineRunner demo(UserRepository userRepository) {
//		return (args) -> {
//////			// save a few customers
//////			repository.save(new Customer("Jack", "Bauer"));
//////			repository.save(new Customer("Chloe", "O'Brian"));
//////			repository.save(new Customer("Kim", "Bauer"));
//////			repository.save(new Customer("David", "Palmer"));
//////			repository.save(new Customer("Michelle", "Dessler"));
//			//adding dummy user records
//	        userRepository.save(new User("Ayush Jain","ayush@xyz.com","9090909090","ayush123"));
//            userRepository.save(new User("Zatin Meraz","zatin@xyz.com","9090909090","zatin123"));
//            userRepository.save(new User("Sai Kamal","sai@xyz.com","9090909090", "sai123"));
//            userRepository.save(new User("Naman","naman@xyz.com","9090909090","naman123"));
//            userRepository.save(new User("Sidharth","sidharth@xyz.com","9090909090","sid123"));
//////
//////			// fetch all customers
//////			log.info("Customers found with findAll():");
//////			log.info("-------------------------------");
//////			for (Customer customer : repository.findAll()) {
//////				log.info(customer.toString());
//////			}
//////			log.info("");
//////
//////			// fetch an individual customer by ID
//////			Customer customer = repository.findById(1L);
//////			log.info("Customer found with findById(1L):");
//////			log.info("--------------------------------");
//////			log.info(customer.toString());
//////			log.info("");
//////
//////			// fetch customers by last name
//////			log.info("Customer found with findByLastName('Bauer'):");
//////			log.info("--------------------------------------------");
//////			repository.findByLastName("Bauer").forEach(bauer -> {
//////				log.info(bauer.toString());
//////			});
//////			// for (Customer bauer : repository.findByLastName("Bauer")) {
//////			//  log.info(bauer.toString());
//////			// }
//////			log.info("");
//		};
//	}

}
