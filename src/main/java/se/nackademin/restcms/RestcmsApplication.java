package se.nackademin.restcms;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import se.nackademin.restcms.CrudRepositories.BlogAdminRepository;
import se.nackademin.restcms.Entities.BlogAdmin;


@SpringBootApplication
public class RestcmsApplication {


    public static void main(String[] args) {
        SpringApplication.run(RestcmsApplication.class, args);
    }


    @Bean
    public CommandLineRunner demo(BlogAdminRepository repo) {
        return (args) -> {
            repo.save(new BlogAdmin("kuk@huvud.se", "kukhuve", "khuvud"));
            repo.save(new BlogAdmin("root@root.root", "root"));
            repo.save(new BlogAdmin("niklas@niklas.niklas", "niklas", "niklas"));
            repo.save(new BlogAdmin("josef@josef.josef", "josef", "josef"));
            repo.save(new BlogAdmin("johan@johan.johan", "johan", "johan"));
        };
    }
}
