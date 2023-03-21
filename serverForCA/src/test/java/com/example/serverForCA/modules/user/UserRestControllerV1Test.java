package com.example.serverForCA.modules.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc

@TestPropertySource(locations = "classpath:application-test.properties")
@Sql(value = "/sql/delete-all.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/sql/create-user.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class UserRestControllerV1Test {

  @Autowired
  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    System.out.println("---SetUp---");
  }

  @AfterEach
  void tearDown() {
    System.out.println("---TearDown---");
  }

  @Test
  @WithMockUser(username = "ret@gmail.com", password = "12345678")
  void getUser() throws Exception {
    Map<String, Object> resBody = new HashMap<>();
    resBody.put("name", "Georgiy");
    resBody.put("email", "ret@gmail.com");
    resBody.put("id", 1);
    resBody.put("age", 12);
    String resBodyJson = new ObjectMapper().writeValueAsString(resBody);

    this.mockMvc.perform(get("/api/v1/users/me"))
            .andExpect(status().isOk())
            .andExpect(content().json(resBodyJson));
  }

  @Test
  void getUserForbidden() throws Exception {
    this.mockMvc.perform(get("/api/v1/users/me"))
            .andExpect(status().isForbidden());
  }

  @Test
  @WithMockUser(username = "ret@gmail.com", password = "12345678")
  void updateUser() throws Exception {
    Map<String, Object> reqBody = new HashMap<>();
    reqBody.put("name", "Georgiy12");
    reqBody.put("age", 54);
    String reqBodyJson = new ObjectMapper().writeValueAsString(reqBody);

    Map<String, Object> resBody = new HashMap<>(reqBody);
    resBody.put("email", "ret@gmail.com");
    resBody.put("id", 1);
    String resBodyJson = new ObjectMapper().writeValueAsString(reqBody);

    this.mockMvc.perform(put("/api/v1/users/me")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(reqBodyJson)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().json(resBodyJson));
  }

  @Test
  void updateUserForbidden() throws Exception {
    Map<String, Object> reqBody = new HashMap<>();
    reqBody.put("name", "Georgiy12");
    reqBody.put("age", 54);
    String reqBodyJson = new ObjectMapper().writeValueAsString(reqBody);

    this.mockMvc.perform(put("/api/v1/users/me")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(reqBodyJson)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isForbidden());
  }
}
