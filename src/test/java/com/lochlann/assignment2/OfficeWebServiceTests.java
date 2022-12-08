package com.lochlann.assignment2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lochlann.assignment2.controllers.NewOffice;
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
    // COMPLETED - delete an office
        // TODO - office exists and is deleted (by HOD or HOS)
        // COMPLETED - office does not exist and cannot be deleted
        // TODO - office exists but cannot be deleted because not user was provided
    // TODO - posting a department you must write several requests
        // TODO - posting a department you must write several requests
        // TODO - data is correct and it can be created (by a HOS)
        // TODO - data is correct but the user is not HOS (only if you have added security)
        // TODO - data is correct but no user provided (only if you have added security)
        // TODO - data correct but cannot be created because of a conflict
        // TODO - data not able to bind to the DTO e.g. a required field is blank or a constraint is not adhered to

    // COMPLETED - get all offices
    @Test
    @Order(1)
    @SneakyThrows
    void findAllOffices() {
        mockMvc.perform(MockMvcRequestBuilders.get("/offices"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("_embedded.offices", Matchers.hasSize(6)));

    }

    // COMPLETED - delete an office
    @Test
    @Order(3)
    @SneakyThrows
    void deleteOffice() {
        mockMvc.perform(MockMvcRequestBuilders.delete("/offices/{id}", 7))
                .andExpect(status().isOk());
    }

    // COMPLETED - office does not exist and cannot be deleted
    @Test
    @Order(2)
    @SneakyThrows
    void deleteOfficeNonexistent() {
        mockMvc.perform(MockMvcRequestBuilders.delete("/offices/{id}", 777))
                .andExpect(status().isNotFound());
    }

    // TODO - office exists but cannot be deleted because not user was provided






    @Test
    @Order(4)
    @SneakyThrows
    void postNewOfficeOk() {
        String jsonString = new ObjectMapper().writeValueAsString( new NewOffice(2, 0, 1));
        mockMvc.perform(MockMvcRequestBuilders.post("/offices")
                                        .content(jsonString)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$._links.self").exists());
    }














//    @Test
//    void deleteOffice() {
//        int testid = 6;
//        Assertions.assertTrue(officeRepo.findById(testid).isPresent());
//        officeRepo.deleteById(testid);
//        Assertions.assertTrue(officeRepo.findById(testid).isEmpty());
//    }


    //	@Order(1)
//	@Test
//	void countStudios() {
//		Assertions.assertEquals(4, departmentRepo.count());
//	};
//
//	// ! this is causing issues with separate test for 'count'
//	@Order(2)
//	@Test
//	void deleteStudioExistingId() {
//		int testId = 4;
//		Assertions.assertTrue(departmentRepo.findById(testId).isPresent());
//		departmentRepo.deleteById(testId);
//		Assertions.assertTrue(departmentRepo.findById(testId).isEmpty());
//	};
//
//	@Test
//	void deleteStudioNotExistingId() {
//		int testId = 99;
//		Assertions.assertThrows(EmptyResultDataAccessException.class, ()->departmentRepo.deleteById(testId));
//	};

}
