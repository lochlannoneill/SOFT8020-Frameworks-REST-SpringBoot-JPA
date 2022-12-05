package com.lochlann.jpa1.service;

import com.lochlann.jpa1.dao.DepartmentRepo;
import com.lochlann.jpa1.entities.Department;
import org.springframework.beans.factory.annotation.Autowired;

public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    DepartmentRepo departmentRepo;

    @Override
    public Department saveDepartment(String name, String email) {
        if (! departmentRepo.existsByName(name))
            return departmentRepo.save(new Department(name, email));
        return null;
    }
}
