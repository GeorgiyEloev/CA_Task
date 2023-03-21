package com.example.serverForCA.modules.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc

@TestPropertySource(locations = "classpath:application-test.properties")
@Sql(value = "/sql/delete-all.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/sql/create-user.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class AuthRestControllerV1Test {

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
  void login() throws Exception {
    Map<String, String> body = new HashMap<>();
    body.put("email", "ret@gmail.com");
    body.put("password", "12345678");
    String bodyJson = new ObjectMapper().writeValueAsString(body);

    this.mockMvc.perform(post("/api/v1/auth/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(bodyJson)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
  }

  @Test
  void loginUnauthorized() throws Exception {
    Map<String, String> body = new HashMap<>();
    body.put("email", "ret@gmail.com");
    body.put("password", "123456789");
    String bodyJson = new ObjectMapper().writeValueAsString(body);

    this.mockMvc.perform(post("/api/v1/auth/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(bodyJson)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isUnauthorized())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
  }

  @Test
  void loginBadRequest() throws Exception {
    Map<String, String> body = new HashMap<>();
    body.put("email", "retgmail.com");
    body.put("password", "12345678");
    String bodyJson = new ObjectMapper().writeValueAsString(body);

    this.mockMvc.perform(post("/api/v1/auth/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(bodyJson)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
  }

  @Test
  void singUp() throws Exception {
    Map<String, Object> body = new HashMap<>();
    body.put("email", "ret1@gmail.com");
    body.put("password", "12345678");
    body.put("age", 15);
    body.put("name", "Stanford");
    body.put("id", 2);

    String bodyJson = new ObjectMapper().writeValueAsString(body);

    this.mockMvc.perform(post("/api/v1/auth/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(bodyJson)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
  }

  @Test
  void singUpUnauthorized() throws Exception {
    Map<String, Object> body = new HashMap<>();
    body.put("email", "ret@gmail.com");
    body.put("password", "12345678");
    body.put("age", 15);
    body.put("name", "Stanford");
    body.put("id", 3);

    String bodyJson = new ObjectMapper().writeValueAsString(body);

    this.mockMvc.perform(post("/api/v1/auth/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(bodyJson)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isUnauthorized())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
  }

  @Test
  void singUpBadRequest() throws Exception {
    Map<String, Object> body = new HashMap<>();
    body.put("email", "ret1gmail.com");
    body.put("password", null);
    body.put("age", 15);
    body.put("name", "Stanford");
    body.put("id", 3);

    String bodyJson = new ObjectMapper().writeValueAsString(body);

    this.mockMvc.perform(post("/api/v1/auth/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(bodyJson)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
  }
}
