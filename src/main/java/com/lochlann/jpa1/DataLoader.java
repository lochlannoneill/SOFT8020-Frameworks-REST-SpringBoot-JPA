package com.lochlann.jpa1;

import com.lochlann.jpa1.dao.OfficeRepo;
import com.lochlann.jpa1.dao.DepartmentRepo;
import com.lochlann.jpa1.entities.Office;
import com.lochlann.jpa1.entities.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// * if persistent database -> data load not required
@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    DepartmentRepo departmentRepo;

    @Autowired
    OfficeRepo officeRepo;

    @Override
    public void run(String... args) throws Exception {
        //6.2 Introducing JPA
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


        String testName;

        //6.2 Introducing JPA
        System.out.println();
        System.out.println("departmentRepo.findAll().forEach(System.out::println);");
        departmentRepo.findAll().forEach(System.out::println);

        //6.3 Derived Queries
        System.out.println();
        System.out.println("System.out.println(departmentRepo.findByName(\"name\"));");
        testName = "Business";
        departmentRepo.findByName("Business").ifPresentOrElse(System.out::println, ()-> System.out.println("Error - {" + testName + "} does not exists"));
        departmentRepo.findByName("Not exists").ifPresentOrElse(System.out::println, ()-> System.out.println("Error - {" + testName + "} does not exists"));

        //6.3 Derived Queries
        System.out.println();
        System.out.println("departmentRepo.findAllByOrderByYearFounded().forEach(System.out::println);");
        departmentRepo.findAllByOrderByEmail().forEach(System.out::println);

        //6.3 Derived Queries
        System.out.println();
        System.out.println("departmentRepo.findByNameContaining(\"e\").forEach(System.out::println);");
        departmentRepo.findByNameContaining("e").forEach(System.out::println);

        //6.3 Derived Queries
        System.out.println();
        System.out.println("departmentRepo.findAllDepartmentsAlphabetically().forEach(System.out::println);");
        departmentRepo.findAllDepartmentsAlphabetically().forEach(System.out::println);

        //6.3 Derived Queries
        System.out.println();
        System.out.println("departmentRepo.changeName(1, \"Value Changed\");");
        System.out.println(departmentRepo.findById(3));
        departmentRepo.changeName(3, "Value Changed");
        System.out.println(departmentRepo.findById(3));

        // 6.4 Adding a Second Table
        System.out.println();
        System.out.println("officeRepo.findAllByName(\"Computer Science\").forEach(System.out::println);");
        officeRepo.findAllByName("Computer Science").forEach(System.out::println);

        // 6.4 Adding a Second Table
        System.out.println();
        System.out.println("officeRepo.findAllByDepartment_Name(\"Warner Brothers\").forEach(System.out::println);");
        officeRepo.findAllByDepartment_Name("Computer Science").forEach(System.out::println);

        //6.4 Join Fetch
        System.out.println();
        System.out.println("officeRepo.findAllByName(\"Computer Science\").forEach(...);");
        departmentRepo.findDepartmentsAndCharacters().forEach(
                (department -> {
                    System.out.println(department.getName());
                    department.getOfficeList().forEach(
                            (office)->
//                                    System.out.println("\t"+office)
                                    System.out.println("\t"+office.getId())
                    );
                })
        );






    }
}
