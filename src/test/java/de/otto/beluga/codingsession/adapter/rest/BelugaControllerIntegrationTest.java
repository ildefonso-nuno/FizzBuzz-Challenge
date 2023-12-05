package de.otto.beluga.codingsession.adapter.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BelugaControllerIntegrationTest {

  @Autowired private WebApplicationContext webApplicationContext;

  private MockMvc mockMvc;

  @BeforeEach()
  public void setup() {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @Test
  void shouldReturnHelloBelugaWhenEndpointIsCalled() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.get("/beluga"))
        .andExpect(status().isOk())
        .andExpect(content().string("Hello Beluga!"))
        .andReturn();
  }

  @Test
  void shouldReturnNotFoundWhenWrongEndpointIsCalled() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.get("/wrong"))
        .andExpect(status().isNotFound())
        .andReturn();
  }

  @Test
  void shouldReturn200WhenCorrectBodyIsPosted() throws Exception {
    String requestBody = "{\"start\": 1, \"end\": 10}"; // JSON body

    mockMvc
            .perform(MockMvcRequestBuilders.post("/beluga/fizzbuzz")
                    .contentType(MediaType.APPLICATION_JSON) // Set the content type
                    .content(requestBody)) // Include the request body
            .andExpect(status().isOk())
            .andReturn();
  }

  @Test
  void shouldReturnBadRequestWhenNoBodyIsPosted() throws Exception {
    mockMvc
            .perform(MockMvcRequestBuilders.post("/beluga/fizzbuzz"))
            .andExpect(status().isBadRequest())
            .andReturn();
  }

  @Test
  void shouldReturnBadRequestWhenWrongIsPosted() throws Exception {
    String requestBody = "{\"start\": , \"end\": 1}"; // JSON body

    mockMvc
            .perform(MockMvcRequestBuilders.post("/beluga/fizzbuzz")
                    .contentType(MediaType.APPLICATION_JSON) // Set the content type
                    .content(requestBody)) // Include the request body
            .andExpect(status().isBadRequest())
            .andReturn();
  }

  @Test
  void shouldReturnBadRequestWhenStartLargerThanEndPosted() throws Exception {
    String requestBody = "{\"start\": 10, \"end\": 1}"; // JSON body

    mockMvc
            .perform(MockMvcRequestBuilders.post("/beluga/fizzbuzz")
                    .contentType(MediaType.APPLICATION_JSON) // Set the content type
                    .content(requestBody)) // Include the request body
            .andExpect(status().isBadRequest())
            .andReturn();
  }

  @Test
  void shouldReturnBadRequestWhenNumberZeroPosted() throws Exception {
    String requestBody = "{\"start\": 0, \"end\": 1}"; // JSON body

    mockMvc
            .perform(MockMvcRequestBuilders.post("/beluga/fizzbuzz")
                    .contentType(MediaType.APPLICATION_JSON) // Set the content type
                    .content(requestBody)) // Include the request body
            .andExpect(status().isBadRequest())
            .andReturn();
  }

  @Test
  void shouldReturnBadRequestWhenNumberSmallerThan1Posted() throws Exception {
    String requestBody = "{\"start\": -10, \"end\": 1}"; // JSON body

    mockMvc
            .perform(MockMvcRequestBuilders.post("/beluga/fizzbuzz")
                    .contentType(MediaType.APPLICATION_JSON) // Set the content type
                    .content(requestBody)) // Include the request body
            .andExpect(status().isBadRequest())
            .andReturn();
  }

}
