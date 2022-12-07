package com.lochlann.jpa1.controllers.webservices;

import com.lochlann.jpa1.dao.DepartmentRepo;
import com.lochlann.jpa1.dao.OfficeRepo;
import com.lochlann.jpa1.entities.Department;
import com.lochlann.jpa1.entities.Office;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class DepartmentWebService {

    @Autowired
    private DepartmentRepo departmentRepo;

    @Autowired
    private OfficeRepo officeRepo;

    // * GET - http://localhost:1337/departments
    // HTTPS NOT SUPPORTED
    @GetMapping("/departments")
    List<Department> getAll() {
        return departmentRepo.findAll();
    }

    // * GET - http://localhost:1337/departments/1
    @GetMapping("/departments/{id}")
    public Department getById(@PathVariable("id") int id) {
        Optional<Department> departmentOptional = departmentRepo.findById(id);
        //if (departmentOptional.isPresent()) return departmentOptional.get();
        //throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department id {" + id + "} not found");
        return departmentOptional.orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Department Id {" + id + "} not found")
        );
    }

    // * GET - http://localhost:1337/departments/offices
    @GetMapping("/departments/{id}/offices")
    public List<Office> getOfficesInDepartment(@PathVariable("id") int id) {
            if (departmentRepo.existsById(id))
                return officeRepo.findAllByDepartmentId(id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department id {" + id + "} not found");
    }

    // ! ERROR - could not execute statement; SQL statement: delete from department where id=? [23503-214]
    // GET - http://localhost:1337/departments/1
    @DeleteMapping("/departments/{id}")
    public void deleteById(@PathVariable("id") int id) {
        try {
            departmentRepo.deleteById(id);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department id {" + id + "} not found");
        }
    }

}
