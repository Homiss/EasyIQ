package cn.wcode.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import cn.wcode.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * Created by homiss on 2017/6/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@Transactional
@TransactionConfiguration(defaultRollback = false)
@AutoConfigureMockMvc
@SpringBootTest(classes = Application.class)
public class AppReciteControllerTest {



  private Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  private MockMvc mvc;

  @Test
  public void testAddQuestionGroup() throws Exception {
    MvcResult result = mvc.perform(post("/api/app/v1/recite/add").param("groupId", "1"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andReturn();
  }

  @Test
  public void selectTodayTask() throws Exception {
    MvcResult result = mvc.perform(post("/api/app/recite/v1/today/task"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andReturn();
  }

  @Test
  public void finishRecord() throws Exception {
    MvcResult result = mvc.perform(post("/api/app/recite/v1/finish/record")
        .param("userId", "6")
        .param("token", "3d7f4bf6-0223-4c77-b508-b5b5fd01866e"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andReturn();
  }
}
