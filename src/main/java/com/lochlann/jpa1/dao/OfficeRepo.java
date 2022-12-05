package com.lochlann.jpa1.dao;

import com.lochlann.jpa1.entities.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OfficeRepo extends JpaRepository<Office, Integer> {
    @Query("select o from Office o where o.department.name=?1") // the first parameter {name} mapped to =?1
    List<Office> findAllByName(String name);

    List<Office> findAllByDepartment_Name(String name);
}
