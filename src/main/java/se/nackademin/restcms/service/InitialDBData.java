package se.nackademin.restcms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.nackademin.restcms.crudrepositories.AuthorityRepository;
import se.nackademin.restcms.crudrepositories.UserRepository;
import se.nackademin.restcms.crudrepositories.BlogPostRepository;
import se.nackademin.restcms.crudrepositories.BlogRepository;
import se.nackademin.restcms.entities.*;

import javax.annotation.PostConstruct;

@Service
public class InitialDBData {

    private final UserRepository userRepository;

    private final BlogRepository blogRepository;
    private BlogPostRepository blogPostRepository;
    private final BlogPostService blogPostService;
    private final AuthorityRepository authorityRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public InitialDBData(UserRepository userRepository,
                         AuthorityRepository authorityRepository,
                         PasswordEncoder passwordEncoder,
                         BlogRepository blogRepository,
                         BlogPostService blogPostService,
                         BlogPostRepository blogPostRepository
    ) {
        this.blogPostService = blogPostService;
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
        this.blogRepository = blogRepository;
        this.blogPostRepository = blogPostRepository;
    }

    @PostConstruct
    public void init() {
        Authority user = new Authority(AuthorityType.ROLE_USER);
        Authority admin = new Authority(AuthorityType.ROLE_ADMIN);

        authorityRepository.save(admin);
        authorityRepository.save(user);
        Blog blog = newAdmin("lorem", "lorem", "lorem@lorem.lorem", "lorem", user);
        newAdmin("root", "root", "root@root.root", "root", user);
        newAdmin("NIKLAS BLOGG", "niklas", "niklas@niklas.niklas", "niklas", user);
        newAdmin("JOSEF BLOGG", "josef", "josef@josef.josef", "josef", user);
        newAdmin("JOHAN BLOGG", "johan", "johan@johan.johan", "johan", user);

        newBlogPost(blog,
                "<h2>Vad är Lorem Ipsum?</h2><p><strong>Lorem Ipsum</strong> är en utfyllnadstext från tryck- och förlagsindustrin. Lorem ipsum har varit standard ända sedan 1500-talet, när en okänd boksättare tog att antal bokstäver och blandade dem för att göra ett provexemplar av en bok. Lorem ipsum har inte bara överlevt fem århundraden, utan även övergången till elektronisk typografi utan större förändringar. Det blev allmänt känt på 1960-talet i samband med lanseringen av Letraset-ark med avsnitt av Lorem Ipsum, och senare med mjukvaror som Aldus PageMaker</p>"
        );
        newBlogPost(blog,
                "<h2>Varför använder vi det?</h2><p>Det är ett välkänt faktum att läsare distraheras av läsbar text på en sida när man skall studera layouten. Poängen med Lorem Ipsum är att det ger ett normalt ordflöde, till skillnad från \"Text här, Text här\", och ger intryck av att vara läsbar text. Många publiseringprogram och webbutvecklare använder Lorem Ipsum som test-text, och en sökning efter \"Lorem Ipsum\" avslöjar många webbsidor under uteckling. Olika versioner har dykt upp under åren, ibland av olyckshändelse, ibland med flit (mer eller mindre humoristiska).</p>"
        );
        newBlogPost(blog,
                "<h2>Var kommer det ifrån?</h2><p>I motsättning till vad många tror, är inte Lorem Ipsum slumpvisa ord. Det har sina rötter i ett stycke klassiskt litteratur på latin från 45 år före år 0, och är alltså över 2000 år gammalt. Richard McClintock, en professor i latin på Hampden-Sydney College i Virginia, översatte ett av de mer ovanliga orden, consectetur, från ett stycke Lorem Ipsum och fann dess ursprung genom att studera användningen av dessa ord i klassisk litteratur. Lorem Ipsum kommer från styckena 1.10.32 och 1.10.33 av \"de Finibus Bonorum et Malorum\" (Ytterligheterna av ont och gott) av Cicero, skriven 45 före år 0. Boken är en avhandling i teorier om etik, och var väldigt populär under renäsanssen. Den inledande meningen i Lorem Ipsum, \"Lorem Ipsum dolor sit amet...\", kommer från stycke 1.10.32.</p><p>Den ursprungliga Lorem Ipsum-texten från 1500-talet är återgiven nedan för de intresserade. Styckena 1.10.32 och 1.10.33 från \"de Finibus Bonorum et Malorum\" av Cicero hittar du också i deras originala form, åtföljda av de engelska översättningarna av H. Rackham från 1914.</p>"
        );
        newBlogPost(blog,
                "<h2>Var får jag tag i det?</h2><p>Det finns många olika varianter av Lorem Ipsum, men majoriteten av dessa har ändrats på någotvis. Antingen med inslag av humor, eller med inlägg av ord som knappast ser trovärdiga ut. Skall man använda långa stycken av Lorem Ipsum bör man försäkra sig om att det inte gömmer sig något pinsamt mitt i texten. Lorem Ipsum-generatorer på internet tenderar att repetera Lorem Ipsum-texten styckvis efter behov, något som gör denna sidan till den första riktiga Lorem Ipsum-generatorn på internet. Den använder ett ordförråd på över 200 ord, kombinerat med ett antal meningsbyggnadsstrukturer som tillsamman genererar Lorem Ipsum som ser ut som en normal mening. Lorem Ipsum genererad på denna sidan är därför alltid fri från repetitioner, humorinslag, märkliga ordformationer osv.</p>"
        );
    }

    private Blog newAdmin(String blogName, String username, String email, String rawPassword, Authority role) {
        User user = new User(username, email, passwordEncoder.encode(rawPassword), role);
        Blog blog = new Blog();
        blog.setActive(true);
        blog.setBlogName(blogName);
        user.setBlog(blog);
        userRepository.save(user);
        blog.setUser(user);
        blogRepository.save(blog);
        return blog;
    }

    private void newBlogPost(Blog blog, String data) {
        BlogPost blogPost = new BlogPost(blog, data);
        blogPostRepository.save(blogPost);
    }
}