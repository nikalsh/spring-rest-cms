package se.nackademin.restcms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;


@EnableResourceServer
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class Application {


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

// could not get cascade to work from here (onetone blogadmin - blog)


//    @Bean
//    public CommandLineRunner demo(BlogAdminRepository repo) {
//        return (args) -> {
//            repo.save(new BlogAdmin("root@root.root", "root"));
//            repo.save(new BlogAdmin("niklas@niklas.niklas", "niklas"));
//            repo.save(new BlogAdmin("josef@josef.josef", "josef"));
//            repo.save(new BlogAdmin("johan@johan.johan", "johan"));
//        };
//    }
}
