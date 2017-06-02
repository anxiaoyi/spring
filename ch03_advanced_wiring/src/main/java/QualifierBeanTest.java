import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zk on 17-6-2.
 */
public class QualifierBeanTest {

    @Autowired
    @A
    @B
    IQualifierBean qualifierBean;

}
