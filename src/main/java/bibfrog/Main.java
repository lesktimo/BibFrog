package bibfrog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EnableJpaRepositories("com...jpa")
@EntityScan("com...jpa")
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        //Boot Spring application
        SpringApplication.run(Main.class, args);
    }
}
