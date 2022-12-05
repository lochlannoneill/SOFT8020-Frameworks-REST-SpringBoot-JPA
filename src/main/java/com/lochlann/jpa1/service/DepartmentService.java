package com.lochlann.jpa1.service;

import com.lochlann.jpa1.entities.Department;

public interface DepartmentService {
    Department saveDepartment(String name, String email);
}
