package com.lochlann.assignment2.controllers.webservices;

import com.lochlann.assignment2.controllers.ChangeDepartment;
import com.lochlann.assignment2.controllers.NewOffice;
import com.lochlann.assignment2.controllers.dto.OfficeDto;
import com.lochlann.assignment2.controllers.dto.OfficeDtoMapper;
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
import java.util.Optional;

@RestController
public class OfficeWebService {
    @Autowired
    private OfficeRepo officeRepo;
    @Autowired
    private OfficeDtoMapper officeDtoMapper;

    @Autowired
    private DepartmentRepo departmentRepo;

    // * GET - https://localhost:1337/offices
    @GetMapping("/offices")
    public CollectionModel<OfficeDto> getAll() {
        return officeDtoMapper.toCollectionModel(officeRepo.findAll());
    }

    // * GET - https://localhost:1337/offices/empty
    @GetMapping("/offices/empty")
    public CollectionModel<OfficeDto> getAllEmpty() {
        return officeDtoMapper.toCollectionModel(officeRepo.findAllByOccupancyEmpty());
    }

    // * GET - https://localhost:1337/notFull
    @GetMapping("/offices/notFull")
    public CollectionModel<OfficeDto> getAllNotFull() {
        return officeDtoMapper.toCollectionModel(officeRepo.findAllByOccupancyNotFull());
    }

    // * GET - http://localhost:1337/offices/1
    @GetMapping("/offices/{id}")
    public OfficeDto getById(@PathVariable("id") int id) {
        Optional<Office> officeOptional = officeRepo.findById(id);
        if (officeOptional.isPresent()) return officeDtoMapper.toModel(officeOptional.get());
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Office id {" + id + "} not found");
    }

    // * POST - http://localhost:1337/offices
    @PostMapping({"/offices", "/office/"})
    @ResponseStatus(HttpStatus.CREATED)
    public OfficeDto addOffice(@RequestBody@Valid NewOffice payload, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid JSON key-value data");
        Optional<Department> departmentOptional = departmentRepo.findById(payload.departmentId());
        if (departmentOptional.isPresent()) {
            Office newOffice = new Office(payload.maxOccupancy(), payload.currentOccupancy(), departmentOptional.get());
            return officeDtoMapper.toModel(officeRepo.save(newOffice));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department id {" + payload.departmentId() + "} not found");
        }
    }

    // * PATCH - http://localhost:1337/offices
    @PatchMapping({"/offices/{id}/changeDepartment"})
    public OfficeDto changeDepartment(@PathVariable("id")int id, @Valid@RequestBody ChangeDepartment payload, BindingResult bindingResult) {
        try {
            if(bindingResult.hasErrors())
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid JSON key-value data");
            // ! ERROR - Argument [1] of type [java.lang.Integer] did not match parameter type [com.lochlann.assignment2.entities.Department (n/a)]
            officeRepo.changeDepartment(id, payload.departmentId());
            return officeDtoMapper.toModel(officeRepo.findById(id).get());
        }
        catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Studio id {" + payload.departmentId() + " not found");
        }
    }

    // * DELETE - http://localhost:1337/offices/1 DELETE
    @DeleteMapping("/offices/{id}")
    public void deleteById(@PathVariable("id") int id) {
        try {
            officeRepo.deleteById(id);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Office id {" + id + "} not found");
        }
    }

}
