import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Created by zk on 17-6-2.
 */
@Configuration
public class PrimaryBeanConfig {

    @Bean
    @Primary
    public IPrimaryBean primaryBean() {
        return new PrimaryBean();
    }

}
