package de.otto.beluga.codingsession.exception;

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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GlobalExceptionHandlerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach()
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testHandleMethodArgumentNotValid() throws Exception {
        String requestBody = "{\"start\": 0, \"end\": -1}"; // JSON body

        mockMvc
                .perform(MockMvcRequestBuilders.post("/beluga/fizzbuzz")
                        .contentType(MediaType.APPLICATION_JSON) // Set the content type
                        .content(requestBody)) // Include the request body
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").isNotEmpty())
                .andExpect(jsonPath("$.path").value("uri=/beluga/fizzbuzz"))
                .andExpect(jsonPath("$.timestamp").isNotEmpty());
    }

    @Test
    public void testStartLargerThanEndException() throws Exception {
        String requestBody = "{\"start\": 10, \"end\": 1}"; // JSON body

        mockMvc
                .perform(MockMvcRequestBuilders.post("/beluga/fizzbuzz")
                        .contentType(MediaType.APPLICATION_JSON) // Set the content type
                        .content(requestBody)) // Include the request body
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value("Start number cannot be larger than end number"))
                .andExpect(jsonPath("$.path").value("uri=/beluga/fizzbuzz"))
                .andExpect(jsonPath("$.timestamp").isNotEmpty());
    }
}
