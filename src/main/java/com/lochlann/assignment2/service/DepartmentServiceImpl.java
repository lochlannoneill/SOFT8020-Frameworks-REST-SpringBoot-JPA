//package com.lochlann.assignment2.service;
//
//import com.lochlann.assignment2.dao.DepartmentRepo;
//import com.lochlann.assignment2.entities.Department;
//import org.springframework.beans.factory.annotation.Autowired;
//
//public class DepartmentServiceImpl implements DepartmentService {
//    @Autowired
//    DepartmentRepo departmentRepo;
//
//    @Override
//    public Department createDepartment(String name, String email) {
//        if (! departmentRepo.existsByName(name))
//            return departmentRepo.save(new Department(name, email));
//        return null;
//    }
//}
