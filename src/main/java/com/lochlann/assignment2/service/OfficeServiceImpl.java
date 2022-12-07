package com.lochlann.assignment2.service;

import com.lochlann.assignment2.dao.OfficeRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class OfficeServiceImpl implements OfficeService {
    @Autowired
    OfficeRepo officeRepo;

//    @Override
//    public boolean deleteOffice(int id) {
//        if (officeRepo.existsById(id))
//            return officeRepo.deleteById(id);
//        return false;
//    }

//    @Override
//    public boolean deleteOffice(int id) {
//        if (! officeRepo.findAllByOccupancyNotFull())
//            return officeRepo.deleteById(id);
//        return false;
//    }
}
