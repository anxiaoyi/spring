import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * Created by zk on 17-6-2.
 */
@Configuration
@PropertySource("classpath:/app.properties")
public class ExternalPropertyConfig {

    @Autowired
    Environment env;

    @Bean
    public PropertyBean propertyBean() {
        return new PropertyBean(
                env.getProperty("a", "A"),
                env.getProperty("b", "B"),
                env.getProperty("c", Integer.class, 40)
        );
    }

}
