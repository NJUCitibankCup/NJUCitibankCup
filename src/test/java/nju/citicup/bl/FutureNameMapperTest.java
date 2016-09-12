package nju.citicup.bl;

import nju.citicup.NjuCitiCupApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = BlConfig.class)
public class FutureNameMapperTest {

    @Autowired
    FutureNameMapper mapper;

    @Test
    public void getFuturesName() throws Exception {

    }

    @Test
    public void getFuturesId() throws Exception {

    }

}