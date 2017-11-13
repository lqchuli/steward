package com.lingyu.steward.api.base;

import org.junit.Before;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by allan on 14/11/2017.
 */

public class WebTestBase {
    @Autowired
    protected WebApplicationContext context;
    /**
     * mock请求
     **/
    @Autowired
    protected MockHttpServletRequest request;
    /**
     * mockMvc等待初始化
     **/
    protected MockMvc mockMvc;

    @Before
    public void initTest() {
        MockitoAnnotations.initMocks(this);
        mockMvc = webAppContextSetup(context)
                .build();
    }
}
