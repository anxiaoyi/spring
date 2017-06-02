import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by zk on 17-6-2.
 */
@Configuration
public class DatabaseConfig {

    @Bean
    @Profile("dev")
    public String devDatasource() {
        return "development-database";
    }

    @Bean
    @Profile("production")
    public String productionDatasource() {
        return "production-database";
    }

}
