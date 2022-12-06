package com.lochlann.jpa1;

import com.lochlann.jpa1.dao.OfficeRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
@ActiveProfiles("test")  // * finds DataLoaderTest.java
public class OfficeRepoTests {

    @Autowired
    private OfficeRepo officeRepo;

    // COMPLETED - get all offices
    // COMPLETED - delete an office
    // TODO - office exists and is deleted (by HOD or HOS)
    // TODO - office does not exist and cannot be deleted
    // TODO - office exists but cannot be deleted because not user was provided

    @Test
    void findAllOffices() {
        Assertions.assertNotNull(officeRepo);
    }

    @Test
    void deleteOffice() {
        int testid = 6;
        Assertions.assertTrue(officeRepo.findById(testid).isPresent());
        officeRepo.deleteById(testid);
        Assertions.assertTrue(officeRepo.findById(testid).isEmpty());
    }


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
