package com.lochlann.assignment2.dao;

import com.lochlann.assignment2.entities.Department;
import com.lochlann.assignment2.entities.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface OfficeRepo extends JpaRepository<Office, Integer> {
    @Query("select o from Office o where o.department.id=?1") // the first parameter {name} mapped to =?1, could also use @Param like below
    List<Office> findAllByDepartmentId(int id);
    @Query("select o from Office o where o.department.name=?1") // the first parameter {name} mapped to =?1, could also use @Param like below
    List<Office> findAllByDepartmentName(String name);

    @Query("select o from Office o where o.currentOccupancy = 0")
    List<Office> findAllByOccupancyEmpty();

    @Query("select o from Office o where o.currentOccupancy < o.maxOccupancy")
    List<Office> findAllByOccupancyNotFull();

    @Modifying(clearAutomatically = true)  // forget the persistence context and make a new call
    @Transactional
    @Query("update Office o set o.department = :newDepartment where o.id = :id")
    void changeDepartment(@Param("id") int id, @Param("newDepartment") Department newDepartment);

    @Modifying(clearAutomatically = true)  // forget the persistence context and make a new call
    @Transactional
    @Query("update Office o set o.department = :departmentId where o.id = :id")
    // ? should i overload this constructor to allow for departmentId instead of object, for the PATCH request???
    void changeDepartment(@Param("id") int id, @Param("departmentId") int departmentId);

    boolean existsById(int id);

}
