package nju.citicup.data;

import nju.citicup.bl.BlConfig;
import nju.citicup.common.entity.BasicFutureInfo;
import nju.citicup.data.dao.baiscdao.FutureDao;
import nju.citicup.data.dao.configdao.FutureConfigDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;


/**
 * Created by admin on 2016/9/13.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan(basePackages = "nju.citicup.data")
@EnableJpaRepositories
public class FutureDaoTest {

    @Autowired
    FutureDao futureDao;

    @Autowired
    FutureConfigDao futureConfigDao;

    @Test
    public void testGetTargetByType(){
        assert futureConfigDao.findTargetByType("A").contains("A1611");
    }

}
