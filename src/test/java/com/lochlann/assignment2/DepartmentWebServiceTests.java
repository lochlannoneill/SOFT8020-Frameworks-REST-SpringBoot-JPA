package com.lochlann.assignment2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lochlann.assignment2.controllers.NewDepartment;
import com.lochlann.assignment2.controllers.NewOffice;
import lombok.SneakyThrows;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")  // * finds DataLoaderTest.java
@SpringBootTest("com.lochlann.assignment2")
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.MethodName.class)
public class DepartmentWebServiceTests {
    @Autowired
    MockMvc mockMvc;

    @Test
    @SneakyThrows
    void postNewDepartmentConflict() {
        // ! Error - Allowed Duplicate Entry
        String jsonString = new ObjectMapper().writeValueAsString( new NewDepartment("Computer Science", "computersciencecork@mtu.ie"));
        mockMvc.perform(MockMvcRequestBuilders.post("/departments")
                        .content(jsonString)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    @Test
    @SneakyThrows
    void postNewDepartmentWrongJson() {
        String jsonString = new ObjectMapper().writeValueAsString( new NewDepartment("testWrongJson", ""));
        mockMvc.perform(MockMvcRequestBuilders.post("/departments")
                        .content(jsonString)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
