package com.lochlann.assignment2.controllers.webservices;

import com.lochlann.assignment2.controllers.webservices.body.NewDepartment;
import com.lochlann.assignment2.controllers.dto.DepartmentDto;
import com.lochlann.assignment2.controllers.dto.DepartmentDtoMapper;
import com.lochlann.assignment2.dao.DepartmentRepo;
import com.lochlann.assignment2.dao.OfficeRepo;
import com.lochlann.assignment2.entities.Department;
import com.lochlann.assignment2.entities.Office;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class DepartmentWebService {

    @Autowired
    private DepartmentRepo departmentRepo;
    @Autowired
    private DepartmentDtoMapper departmentDtoMapper;
    @Autowired
    private OfficeRepo officeRepo;

    // * Get all departments
    // GET - http://localhost:1337/departments
    @GetMapping("/departments")
    public CollectionModel<DepartmentDto> getAll() {
        return departmentDtoMapper.toCollectionModel(departmentRepo.findAll());
    }

    // * Get department by id
    // GET - http://localhost:1337/departments/1
    @GetMapping("/departments/{id}")
    public DepartmentDto getById(@PathVariable("id") int id) {
        Optional<Department> departmentOptional = departmentRepo.findById(id);
        //if (departmentOptional.isPresent()) return departmentOptional.get();
        //throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department id {" + id + "} not found");
        return departmentDtoMapper.toModel(departmentOptional.orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Department Id {" + id + "} not found")
        ));
    }

    // * Get offices in department by id
    // GET - http://localhost:1337/departments/offices
    @GetMapping("/departments/{id}/offices")
    public List<Office> getOfficesInDepartmentById(@PathVariable("id") int id) {
            if (departmentRepo.existsById(id))
                return officeRepo.findAllByDepartmentId(id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department id {" + id + "} not found.");
    }

    // * Post new department
    // POST - http://localhost:1337/departments
    @PostMapping({"/departments", "/department/"})
    @ResponseStatus(HttpStatus.CREATED)
    public DepartmentDto addDepartment(@RequestBody@Valid NewDepartment payload, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid JSON key-value data");
        try {
            Department newDepartment = new Department(payload.name(), payload.email());
            return departmentDtoMapper.toModel(departmentRepo.save(newDepartment));
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Department {" + payload.name() + "} already exists");
        }
    }

    // * Delete department by id
    // DELETE - http://localhost:1337/departments/1
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
