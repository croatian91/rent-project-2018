package fr.dauphine.rentproject2018;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("fr.dauphine.rentproject2018.repository")
@EnableElasticsearchRepositories(basePackages = "fr.dauphine.rentproject2018.elastic")
public class RentProject2018Application {

    public static void main(String[] args) {
        SpringApplication.run(RentProject2018Application.class, args);
    }
}
