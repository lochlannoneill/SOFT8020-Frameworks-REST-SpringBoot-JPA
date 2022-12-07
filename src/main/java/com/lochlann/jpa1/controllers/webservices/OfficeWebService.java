package com.lochlann.jpa1.controllers.webservices;

import com.lochlann.jpa1.dao.OfficeRepo;
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
public class OfficeWebService {

    @Autowired
    private OfficeRepo officeRepo;

    // * GET - https://localhost:1337/offices GET
    @GetMapping("/offices")
    public List<Office> getAll() {
        return officeRepo.findAll();
    }

    // * GET - http://localhost:1337/offices/1 GET
    @GetMapping("/offices/{id}")
    public Office getById(@PathVariable("id") int id) {
        Optional<Office> officeOptional = officeRepo.findById(id);
        if (officeOptional.isPresent()) return officeOptional.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Office id {" + id + "} not found");
    }

    // * GET - http://localhost:1337/offices/1 DELETE
    @DeleteMapping("/offices/{id}")
    public void deleteById(@PathVariable("id") int id) {
        try {
            officeRepo.deleteById(id);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Office id {" + id + "} not found");
        }
    }

}
