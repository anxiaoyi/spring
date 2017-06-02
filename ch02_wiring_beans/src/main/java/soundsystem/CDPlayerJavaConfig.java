package soundsystem;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zk on 17-6-2.
 */
@Configuration
public class CDPlayerJavaConfig {

    @Bean
    public CompactDisc sgtPeppers() {
        return new SgtPeppers();
    }

}
