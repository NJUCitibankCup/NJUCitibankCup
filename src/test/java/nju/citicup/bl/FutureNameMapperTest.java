package nju.citicup.bl;

import nju.citicup.NjuCitiCupApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = BlConfig.class)
public class FutureNameMapperTest {

    @Autowired
    FutureNameMapper mapper;

    @Test
    public void convert() throws Exception {
        assertThat(mapper.convert("A"),equalTo("大豆"));
        assertThat(mapper.convert("大豆"),equalTo("A"));
        assertThat(mapper.convert("A1701"),equalTo("大豆1701"));
        assertThat(mapper.convert("大豆1701"),equalTo("A1701"));
    }


}