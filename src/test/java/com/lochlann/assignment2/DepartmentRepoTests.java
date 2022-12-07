package com.lochlann.assignment2;

import com.lochlann.assignment2.dao.DepartmentRepo;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
@ActiveProfiles("test")  // * finds DataLoaderTest.java
class DepartmentRepoTests {

	@Autowired
	private DepartmentRepo departmentRepo;

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
