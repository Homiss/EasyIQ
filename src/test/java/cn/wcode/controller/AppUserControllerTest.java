package cn.wcode.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import cn.wcode.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by homiss on 2017/6/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@Transactional
@TransactionConfiguration(defaultRollback = false)
@AutoConfigureMockMvc
@SpringBootTest(classes = Application.class)
public class AppUserControllerTest {

  @Autowired
  private MockMvc mvc;

  @Test
  public void regist() throws Exception {
    MvcResult result = mvc.perform(post("/api/app/user/v1/regist")
        .param("name", "Homiss")
        .param("phone", "13551894757")
        .param("password", "123456"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andReturn();
  }

  @Test
  public void login() throws Exception {
    MvcResult result = mvc.perform(post("/api/app/user/v1/login")
        .param("phone", "13551894757")
        .param("password", "123456"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andReturn();
  }

}