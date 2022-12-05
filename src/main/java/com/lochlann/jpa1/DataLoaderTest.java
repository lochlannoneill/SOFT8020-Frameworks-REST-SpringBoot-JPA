package com.lochlann.jpa1;

import com.lochlann.jpa1.dao.OfficeRepo;
import com.lochlann.jpa1.dao.DepartmentRepo;
import com.lochlann.jpa1.entities.Office;
import com.lochlann.jpa1.entities.Department;
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

        //6.4 Adding a Second Table
        officeRepo.save(new Office(30, 11, compsci));
        officeRepo.save(new Office(30, 12, compsci));
        officeRepo.save(new Office(30, 13, compsci));
        officeRepo.save(new Office(30, 21, maths));
        officeRepo.save(new Office(30, 22, maths));
        officeRepo.save(new Office(30, 31, construction));
        officeRepo.save(new Office(50, 41, business));
    }
}
