package com.lingyu.steward.manager.controller;

import com.lingyu.steward.manager.base.WebTestBase;
import org.junit.Test;
import org.springframework.util.DigestUtils;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.*;

/**
 * Created by allan on 13/11/2017.
 */
public class IndexControllerTest extends WebTestBase {
    @Test
    public void index() throws Exception {
        mockMvc.perform(get("/index").with(user("administrator").password("lingyu123!@#")))
                .andExpect(authenticated());
    }
}