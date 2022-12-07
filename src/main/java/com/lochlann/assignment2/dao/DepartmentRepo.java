package com.lochlann.assignment2.dao;

import com.lochlann.assignment2.entities.Department;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepo extends JpaRepository<Department, Integer> {
    Optional<Department> findById(int id);
    Optional<Department> findByName(String name);
    List<Department> findAllByOrderByEmail();
    List<Department> findByNameContaining(String snippet);
    boolean existsByName(String name);

    //JPQL - Java Persistence Query Language
    @Query("select d from Department d order by d.name")
    List<Department> findAllDepartmentsAlphabetically();

    @Modifying
//    @Modifying(clearAutomatically = true)  // forget the persistence context and make a new call
    @Transactional
    @Query("update Department d set d.name = :newName where d.id = :id")
    void changeName(@Param("id") int id, @Param("newName") String newName);

    @Query(value = "select * from department d order by d.email", nativeQuery = true)
    List<Department> findAllDepartmentsAlphabeticallyNative();

    @Query("select distinct d from Department d join fetch d.officeList")  // when you're finding the department, also get the Office list
    List<Department> findDepartmentsAndOffices();


}
