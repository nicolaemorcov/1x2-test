import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"org.test"})
@EntityScan(basePackages = {"org.test"})
@EnableJpaRepositories
public class MainApp extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);
    }

}

