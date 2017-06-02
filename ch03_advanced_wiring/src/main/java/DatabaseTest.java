import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by zk on 17-6-2.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={DatabaseConfig.class})
@ActiveProfiles("dev")
public class DatabaseTest {
}
