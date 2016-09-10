package nju.citicup;

import nju.citicup.data.dao.FutureDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = NjuCitiCupApplication.class)
public class NjuCitiCupApplicationTests {

	@Autowired
	FutureDao futureDao;

	@Test
	public void contextLoads() {
	}

}
