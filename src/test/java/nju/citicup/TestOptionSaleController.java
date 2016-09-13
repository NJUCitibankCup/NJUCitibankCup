package nju.citicup;

import nju.citicup.WebController.OptionSaleController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

/**
 * Created by admin on 2016/9/13.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = NjuCitiCupApplication.class)
@SpringBootTest
public class TestOptionSaleController {

    @Autowired
    WebApplicationContext context;

    @Autowired
    OptionSaleController optionSaleController;

    MockMvc mockMvc;

    @Before
    public void before(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testGetFutureId() throws Exception {
        mockMvc.perform(get("/api/getFuturesId").param("type","大豆"))
                .andDo(print());
    }

    @Test
    public void testGetFutureType() throws Exception {
        mockMvc.perform(get("/api/getFuturesType"))
                .andDo(print());
    }

}
