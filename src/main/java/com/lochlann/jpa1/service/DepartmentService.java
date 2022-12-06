package com.lochlann.jpa1.service;

import com.lochlann.jpa1.entities.Department;

public interface DepartmentService {
    Department createDepartment(String name, String email);
}
