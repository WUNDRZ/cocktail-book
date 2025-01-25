package org.avasylchuk.cocktailbook;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UserControllerTest extends IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void register() throws Exception {
        mockMvc.perform(post("/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                    {
                                         "username": "exampleUser",
                                         "password": "examplePassword"
                                }
                                """))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void login() throws Exception {
        mockMvc.perform(post("/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                    {
                                         "username": "exampleUser",
                                         "password": "examplePassword"
                                }
                                """))
                .andExpect(status().is2xxSuccessful());

        String response = mockMvc.perform(post("/users/access-token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                    {
                                         "username": "exampleUser",
                                         "password": "examplePassword"
                                }
                                """))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("accessToken", Matchers.not(Matchers.nullValue())))
                .andReturn()
                .getResponse()
                .getContentAsString();

        String accessToken = new ObjectMapper().readTree(response)
                .get("accessToken").asText();
        System.out.println(accessToken);
    }

    @Test
    void getCurrentUser() throws Exception {

        mockMvc.perform(post("/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                             "username": "exampleUser",
                             "password": "examplePassword"
                        }
                    """))
                .andExpect(status().is2xxSuccessful());

        String response = mockMvc.perform(post("/users/access-token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                             "username": "exampleUser",
                             "password": "examplePassword"
                        }
                    """))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("accessToken", Matchers.not(Matchers.nullValue())))
                .andReturn()
                .getResponse()
                .getContentAsString();

        String accessToken = new ObjectMapper().readTree(response)
                .get("accessToken").asText();

        mockMvc.perform(get("/users/me")
                        .header("Authorization", "Bearer " + accessToken))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("username", Matchers.equalTo("exampleUser")))
                .andExpect(jsonPath("id", Matchers.notNullValue()));
        System.out.println("Test {/me} complete");

    }
}