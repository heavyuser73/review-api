package review;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner demo(ReviewRepository repository) {
        return (args) -> {
            // save a couple of customers
            repository.save(new Review("aaa", "aaa", 5, "https:www.youtube.com"));
            repository.save(new Review("bbb", "bbb", 5, "https:www.youtube.com"));
            repository.save(new Review("ccc", "ccc", 5, "https:www.youtube.com"));
            repository.save(new Review("ddd", "ddd", 5, "https:www.youtube.com"));
            repository.save(new Review("eee", "eee", 5, "https:www.youtube.com"));

            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Review customer : repository.findAll()) {
                log.info(customer.toString());
            }
            log.info("");

            // fetch an individual customer by ID
            repository.findById(1L)
                    .ifPresent(customer -> {
                        log.info("Customer found with findById(1L):");
                        log.info("--------------------------------");
                        log.info(customer.toString());
                        log.info("");
                    });

            // fetch customers by last name
            log.info("Customer found with findByTitleAllIgnoreCase('CCC'):");
            log.info("--------------------------------------------");
            repository.findByTitleAllIgnoreCase("CCC").forEach(bauer -> {
                log.info(bauer.toString());
            });
            // for (Customer bauer : repository.findByLastName("Bauer")) {
            // 	log.info(bauer.toString());
            // }
            log.info("");
        };
    }

}