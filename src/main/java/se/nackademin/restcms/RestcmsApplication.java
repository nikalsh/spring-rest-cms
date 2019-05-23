package se.nackademin.restcms;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import se.nackademin.restcms.crudrepos.AdminRepository;
import se.nackademin.restcms.entities.Admin;


@SpringBootApplication
public class RestcmsApplication {


    public static void main(String[] args) {
        SpringApplication.run(RestcmsApplication.class, args);
    }


    @Bean
    public CommandLineRunner demo(AdminRepository repo) {
        return (args) -> {
            repo.save(new Admin("kuk@huvud.se", "kukhuve"));
            repo.save(new Admin("root@root.root", "root"));
            repo.save(new Admin("niklas@niklas.niklas", "niklas"));
            repo.save(new Admin("josef@josef.josef", "josef"));
            repo.save(new Admin("johan@johan.johan", "johan"));
        };
    }
}
