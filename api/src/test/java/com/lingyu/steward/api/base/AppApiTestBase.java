package com.lingyu.steward.api.base;

import com.lingyu.steward.api.common.AppSysRequestData;
import com.lingyu.steward.api.config.ApiTestConfig;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Api测试
 * Created by allan on 14/11/2017.
 */
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApiTestConfig.class})
@WebAppConfiguration
@Transactional
public class AppApiTestBase extends WebTestBase {
    protected AppSysRequestData mockSysData;

    protected HttpHeaders httpHeaders = new HttpHeaders();

    @Before
    public void setUp() throws Exception {
        mockSysData = new AppSysRequestData();
        mockSysData.setAppVersion(UUID.randomUUID().toString());

        httpHeaders.add("appVersion", mockSysData.getAppVersion());
    }
}
