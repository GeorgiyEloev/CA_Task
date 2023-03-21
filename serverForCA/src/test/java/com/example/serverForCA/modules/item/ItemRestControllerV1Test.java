package com.example.serverForCA.modules.item;

import com.example.serverForCA.modules.item.dto.CreateItemDto;
import com.example.serverForCA.modules.item.dto.UpdateItemDto;
import com.example.serverForCA.utils.constans.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc

@TestPropertySource(locations = "classpath:application-test.properties")
@Sql(value = "/sql/delete-all.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/sql/create-user.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/sql/create-items.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class ItemRestControllerV1Test {

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
  void getAll() throws Exception {
    List<Map<String, Object>> resBody = new ArrayList<>();

    Map<String, Object> item1 = new HashMap<>();
    item1.put("name", "Georgiy");
    item1.put("description", "str");
    item1.put("id", 1);
    item1.put("count", 12);

    Map<String, Object> item2 = new HashMap<>();
    item1.put("name", "Ret");
    item1.put("description", "str");
    item1.put("id", 2);
    item1.put("count", 12);

    Map<String, Object> item3 = new HashMap<>();
    item1.put("name", "ret");
    item1.put("description", "str");
    item1.put("id", 3);
    item1.put("count", 12);

    Map<String, Object> item4 = new HashMap<>();
    item1.put("name", "Ter");
    item1.put("description", "str");
    item1.put("id", 4);
    item1.put("count", 12);

    Map<String, Object> item5 = new HashMap<>();
    item1.put("name", "RET");
    item1.put("description", "str");
    item1.put("id", 5);
    item1.put("count", 12);

    resBody.add(item1);
    resBody.add(item2);
    resBody.add(item3);
    resBody.add(item4);
    resBody.add(item5);

    String resBodyJson = new ObjectMapper().writeValueAsString(resBody);

    this.mockMvc
            .perform(get("/api/v1/items?page=0&size=5"))
            .andExpect(status().isOk()).andExpect(content()
                    .json(resBodyJson));
  }

  @Test
  @WithMockUser(username = "ret@gmail.com", password = "12345678")
  void findItem() throws Exception {
    List<Map<String, Object>> resBody = new ArrayList<>();

    Map<String, Object> item1 = new HashMap<>();
    item1.put("name", "Georgiy");
    item1.put("description", "str");
    item1.put("id", 1);
    item1.put("count", 12);

    resBody.add(item1);

    String resBodyJson = new ObjectMapper().writeValueAsString(resBody);

    this.mockMvc
            .perform(get("/api/v1/items?page=0&size=5&name=Georgiy"))
            .andExpect(status().isOk()).andExpect(content()
                    .json(resBodyJson));
  }

  @Test
  void getAllForbidden() throws Exception {
    this.mockMvc
            .perform(get("/api/v1/items"))
            .andExpect(status().isForbidden());
  }

  @Test
  @WithMockUser(username = "ret@gmail.com", password = "12345678")
  void getById() throws Exception {

    Map<String, Object> resBody = new HashMap<>();
    resBody.put("name", "Georgiy");
    resBody.put("description", "str");
    resBody.put("id", 1);
    resBody.put("count", 12);

    String resBodyJson = new ObjectMapper().writeValueAsString(resBody);

    this.mockMvc
            .perform(get("/api/v1/items/1"))
            .andExpect(status().isOk()).andExpect(content()
                    .json(resBodyJson));
  }

  @Test
  @WithMockUser(username = "ret@gmail.com", password = "12345678")
  void getByIdNotFound() throws Exception {
    this.mockMvc
            .perform(get("/api/v1/items/100"))
            .andExpect(status().isNotFound());
  }

  @Test
  void getByIdForbidden() throws Exception {
    this.mockMvc
            .perform(get("/api/v1/items/1"))
            .andExpect(status().isForbidden());
  }

  @Test
  @WithMockUser(username = "ret@gmail.com", password = "12345678")
  void createItem() throws Exception {

    Map<String, Object> body = new HashMap<>();
    body.put("name", "Georgiy");
    body.put("description", "str");
    body.put("count", 12);

    String bodyJson = new ObjectMapper().writeValueAsString(body);

    this.mockMvc
            .perform(post("/api/v1/items")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(bodyJson)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content()
                    .json(bodyJson));
  }

  @Test
  @WithMockUser(username = "ret@gmail.com", password = "12345678")
  void createItemBadRequest() throws Exception {

    Map<String, Object> body = new HashMap<>();
    body.put("name", "Georgiy");
    body.put("description", "str");
    body.put("count", null);

    String bodyJson = new ObjectMapper().writeValueAsString(body);

    this.mockMvc
            .perform(post("/api/v1/items")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(bodyJson)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
  }

  @Test
  void createItemForbidden() throws Exception {

    this.mockMvc
            .perform(post("/api/v1/items"))
            .andExpect(status().isForbidden());
  }

  @Test
  @WithMockUser(username = "ret@gmail.com", password = "12345678")
  void updateItem() throws Exception {

    Map<String, Object> body = new HashMap<>();
    body.put("name", "Georgiy1");
    body.put("description", "str1");
    body.put("count", 1111);

    String reqBodyJson = new ObjectMapper().writeValueAsString(body);
    body.put("id", 1);

    String resBodyJson = new ObjectMapper().writeValueAsString(body);


    this.mockMvc
            .perform(put("/api/v1/items/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(reqBodyJson)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content()
                    .json(resBodyJson));
  }

  @Test
  @WithMockUser(username = "ret@gmail.com", password = "12345678")
  void updateItemNotFound() throws Exception {
    Map<String, Object> body = new HashMap<>();
    body.put("name", "Georgiy1");
    body.put("description", "str1");
    body.put("count", 1111);

    String reqBodyJson = new ObjectMapper().writeValueAsString(body);

    this.mockMvc
            .perform(put("/api/v1/items/10000000")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(reqBodyJson)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
  }

  @Test
  void updateItemForbidden() throws Exception {
    this.mockMvc
            .perform(put("/api/v1/items/1"))
            .andExpect(status().isForbidden());
  }

  @Test
  @WithMockUser(username = "ret@gmail.com", password = "12345678")
  void deleteItem() throws Exception {
    this.mockMvc
            .perform(delete("/api/v1/items/1"))
            .andExpect(status().isOk());
  }

  @Test
  @WithMockUser(username = "ret@gmail.com", password = "12345678")
  void deleteItemNotFound() throws Exception {
    this.mockMvc
            .perform(delete("/api/v1/items/1000000"))
            .andExpect(status().isNotFound());
  }

  @Test
  void deleteItemForbidden() throws Exception {
    this.mockMvc
            .perform(delete("/api/v1/items/1"))
            .andExpect(status().isForbidden());
  }
}
