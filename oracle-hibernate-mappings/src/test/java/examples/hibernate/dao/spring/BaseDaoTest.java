package examples.hibernate.dao.spring;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:examples/hibernate/dao/spring/applicationContext.xml")
public abstract class BaseDaoTest {

}
