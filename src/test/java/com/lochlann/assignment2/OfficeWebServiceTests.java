package com.lochlann.assignment2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lochlann.assignment2.controllers.webservices.body.NewOffice;
import lombok.SneakyThrows;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")  // * finds DataLoaderTest.java
@SpringBootTest("com.lochlann.assignment2")
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.MethodName.class)
public class OfficeWebServiceTests {
    @Autowired
    MockMvc mockMvc;

    // COMPLETED - get all offices
    @Test
    @Order(1)
    @SneakyThrows
    @WithMockUser(roles="HOS")
    void findAllOffices() {
        mockMvc.perform(MockMvcRequestBuilders.get("/offices"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("_embedded.offices", Matchers.hasSize(6)));
    }

    // COMPLETED - office exists and is deleted (by HOD or HOS)
    @Test
    @Order(3)
    @SneakyThrows
    @WithMockUser(roles="HOD")
    void deleteOffice() {
        mockMvc.perform(MockMvcRequestBuilders.delete("/offices/{id}", 7))
                .andExpect(status().isOk());
    }

    // COMPLETED - office exists but cannot be deleted because not user was provided
    @Test
    @Order(3)
    @SneakyThrows
    void deleteOfficeNoUser() {
        mockMvc.perform(MockMvcRequestBuilders.delete("/offices/{id}", 3))
                .andExpect(status().isUnauthorized());
    }

    // COMPLETED - office does not exist and cannot be deleted
    @Test
    @Order(2)
    @SneakyThrows
    @WithMockUser(roles="HOS")
    void deleteOfficeNonexistent() {
        mockMvc.perform(MockMvcRequestBuilders.delete("/offices/{id}", 777))
                .andExpect(status().isNotFound());
    }

    @Test
    @Order(4)
    @SneakyThrows
    @WithMockUser(roles="HOS")
    void postNewOfficeOk() {
        String jsonString = new ObjectMapper().writeValueAsString( new NewOffice(2, 0, 1));
        mockMvc.perform(MockMvcRequestBuilders.post("/offices")
                                        .content(jsonString)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$._links.self").exists());
    }

}
