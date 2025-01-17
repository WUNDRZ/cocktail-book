package org.avasylchuk.cocktailbook;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class UserControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    void register() throws Exception {
//        mockMvc.perform(post("/users/register")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("""
//                                    {
//                                         "username": "exampleUser",
//                                         "password": "examplePassword"
//                                }
//                                """))
//                .andExpect(status().is2xxSuccessful());
//    }
//
//    @Test
//    void login() {
//    }
//
//    @Test
//    void getCurrentUser() {
//    }
}