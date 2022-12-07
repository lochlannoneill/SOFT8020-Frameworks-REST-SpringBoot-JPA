//package com.lochlann.jpa1.controllers;
//
//import com.lochlann.jpa1.dao.DepartmentRepo;
//import com.lochlann.jpa1.dao.OfficeRepo;
//import com.lochlann.jpa1.entities.Department;
//import com.lochlann.jpa1.entities.Office;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//
// TODO - DELETE THIS FILE
//@RestController
//public class WebService {
//
//    @Autowired
//    private DepartmentRepo departmentRepo;
//
//    @Autowired
//    private OfficeRepo officeRepo;
//
//    // http://localhost:1337/departments
//    // * NOT HTTPS
//    @GetMapping("/departments")
//    List<Department> getAllDepartments() {
//        return departmentRepo.findAll();
//    }
//
//    @GetMapping("/offices")
//    List<Office> getAllOffices() {
//        return officeRepo.findAll();
//    }
//
//    // http://localhost:1337/departments/1
//    @GetMapping("/departments/{id}")
//    Department getDepartmentById(@PathVariable("id") int departmentId) {
//        return departmentRepo.findById(departmentId).get();
//    }
//
//    // http://localhost:1337/offices/1
//    @GetMapping("/offices/{id}")
//    Office getOfficeById(@PathVariable("id") int officeId) {
//        return officeRepo.findById(officeId).get();
//    }
//}
