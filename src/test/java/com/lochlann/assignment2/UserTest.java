package com.lochlann.assignment2;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")  // * finds DataLoaderTest.java
@SpringBootTest("com.lochlann.assignment2")
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.MethodName.class)
public class UserTest {
    @Autowired
    MockMvc mockMvc;

//    @Test
//    @WithMockUser(value = "admin", roles = "ADMIN")
//    void postNewStudioEmptyBody() throws Exception {
//        String jsonString = "";
//        mockMvc.perform( MockMvcRequestBuilders
//                        .post("/studios")
//                        .content(jsonString)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest())
//                .andExpect(result -> {
//                            assertEquals("There is an error with your Json. It could not be deserialised.",
//                                    result.getResponse().getErrorMessage());
//                        }
//                );
}
