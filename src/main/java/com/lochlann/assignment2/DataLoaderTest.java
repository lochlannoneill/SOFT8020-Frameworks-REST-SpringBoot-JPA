package com.lochlann.assignment2;

import com.lochlann.assignment2.dao.OfficeRepo;
import com.lochlann.assignment2.dao.DepartmentRepo;
import com.lochlann.assignment2.entities.Office;
import com.lochlann.assignment2.entities.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
public class DataLoaderTest implements CommandLineRunner {
    @Autowired
    DepartmentRepo departmentRepo;

    @Autowired
    OfficeRepo officeRepo;

    @Override
    public void run(String... args) throws Exception {
        Department compsci = departmentRepo.save(new Department("Computer Science", "computersciencecork@mtu.ie"));
        Department maths = departmentRepo.save(new Department("Mathematics", "mathematicscork@mtu.ie"));
        Department construction = departmentRepo.save(new Department("Construction", "mathematicscork@mtu.ie"));
        Department business = departmentRepo.save(new Department("Business", "buesinesscork@mtu.ie"));

        officeRepo.save(new Office(3, 1, compsci));
        officeRepo.save(new Office(3, 2, compsci));
        officeRepo.save(new Office(3, 3, compsci));
        officeRepo.save(new Office(2, 0, maths));
        officeRepo.save(new Office(2, 2, maths));
        officeRepo.save(new Office(3, 0, construction));
        officeRepo.save(new Office(5, 5, business));
    }
}
