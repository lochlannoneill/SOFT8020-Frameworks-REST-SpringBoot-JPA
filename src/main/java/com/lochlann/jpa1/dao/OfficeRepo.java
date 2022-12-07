package com.lochlann.jpa1.dao;

import com.lochlann.jpa1.entities.Department;
import com.lochlann.jpa1.entities.Office;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

//    @Modifying
    @Modifying(clearAutomatically = true)  // forget the persistence context and make a new call
    @Transactional
    @Query("update Office o set o.department = :newDepartment where o.id = :id")
    void changeDepartment(@Param("id") int id, @Param("newDepartment") Department newDepartment);


    //    @Query("")
//    boolean moveToDepartment(int departmentId)
    boolean existsById(int id);
//    boolean deleteById(int id);
}
