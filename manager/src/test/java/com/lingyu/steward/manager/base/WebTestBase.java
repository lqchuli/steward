package com.lingyu.steward.manager.base;


import com.lingyu.steward.manager.config.WebTestConfig;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;


/**
 * API测试
 * Created by allan on 29/10/2017.
 */
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebTestConfig.class})
@WebAppConfiguration
@Transactional
public class WebTestBase extends AbstractWebTestBase {

    @Override
    protected void createMockMVC() {
        mockMvc = webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }
}
