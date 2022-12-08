package com.lochlann.assignment2.dao;

import com.lochlann.assignment2.entities.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffDao extends JpaRepository<Staff, String> {
}
