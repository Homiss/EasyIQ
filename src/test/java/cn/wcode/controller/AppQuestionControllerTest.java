package cn.wcode.controller;

import static org.junit.Assert.*;
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
 * Created by homiss on 2017/6/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@Transactional
@TransactionConfiguration(defaultRollback = false)
@AutoConfigureMockMvc
@SpringBootTest(classes = Application.class)
public class AppQuestionControllerTest {



  private Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  private MockMvc mvc;



  @Test
  public void addQuestionGroup() throws Exception {
    MvcResult result = mvc.perform(post("/api/app/question/v1/add")
        .param("userId", "6")
        .param("token", "1bb9881b-11fe-46b8-adc5-795b433568ae")
        .param("qGroupId", "1"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andReturn();
  }

  @Test
  public void importQuestion() throws Exception {

  }

  @Test
  public void importData() throws Exception {
    MvcResult result = mvc.perform(post("/question/import")
        .param("filepath", "/Users/homiss/Desktop/Java面试题之多线程(三).html"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andReturn();
  }

}