package com.lochlann.assignment2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lochlann.assignment2.controllers.webservices.body.NewDepartment;
import lombok.SneakyThrows;
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
public class DepartmentWebServiceTests {
    @Autowired
    MockMvc mockMvc;

    // COMPLETED - data is correct and it can be created (by a HOS)
    @Test
    @Order(3)
    @SneakyThrows
    @WithMockUser(roles="HOS")
    void postNewDepartmentOK() {
        String jsonString = new ObjectMapper().writeValueAsString( new NewDepartment("Test Ok", "test@ok.mtu"));
        mockMvc.perform(MockMvcRequestBuilders.post("/departments")
                        .content(jsonString)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    // COMPLETED - data is correct but the user is not HOS (only if you have added security)
    @Test
    @Order(3)
    @SneakyThrows
    @WithMockUser(roles="HOD")
    void postNewDepartmentForbiddenUser() {
        String jsonString = new ObjectMapper().writeValueAsString( new NewDepartment("Test Ok", "test@ok.mtu"));
        mockMvc.perform(MockMvcRequestBuilders.post("/departments")
                        .content(jsonString)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    // COMPLETED - data is correct but no user provided (only if you have added security)
    @Test
    @Order(3)
    @SneakyThrows
    void postNewDepartmentNoUser() {
        String jsonString = new ObjectMapper().writeValueAsString( new NewDepartment("Test Ok", "test@ok.mtu"));
        mockMvc.perform(MockMvcRequestBuilders.post("/departments")
                        .content(jsonString)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    // COMPLETED - data correct but cannot be created because of a conflict
    @Test
    @SneakyThrows
    @WithMockUser(roles="HOS")
    void postNewDepartmentConflict() {
        // ! ERROR - expected 409, result 201 - NAME IS NOT UNIQUE
        String jsonString = new ObjectMapper().writeValueAsString( new NewDepartment("Computer Science", "computersciencecork@mtu.ie"));
        mockMvc.perform(MockMvcRequestBuilders.post("/departments")
                        .content(jsonString)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    // COMPLETED - data not able to bind to the DTO e.g. a required field is blank or a constraint is not adhered to
    @Test
    @SneakyThrows
    @WithMockUser(roles="HOS")
    void postNewDepartmentWrongJson() {
        String jsonString = new ObjectMapper().writeValueAsString( new NewDepartment("testWrongJson", ""));
        mockMvc.perform(MockMvcRequestBuilders.post("/departments")
                        .content(jsonString)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}
