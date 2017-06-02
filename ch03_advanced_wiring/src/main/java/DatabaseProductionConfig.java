import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by zk on 17-6-2.
 */
@Configuration
@Profile("production")
public class DatabaseProductionConfig {

    @Bean
    public String dataSource() {
        return "production-database";
    }

}
