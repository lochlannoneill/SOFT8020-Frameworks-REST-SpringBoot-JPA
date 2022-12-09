package com.lochlann.assignment2;

import com.lochlann.assignment2.dao.OfficeRepo;
import com.lochlann.assignment2.dao.DepartmentRepo;
import com.lochlann.assignment2.dao.StaffDao;
import com.lochlann.assignment2.entities.Office;
import com.lochlann.assignment2.entities.Department;
import com.lochlann.assignment2.entities.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

// * if persistent database -> data load not required
@Component
@Profile("dev")
public class DataLoader implements CommandLineRunner {
    final String ANSI_TEXT_RESET = "\u001b[0m";
    final String ANSI_TEXT_RED = "\u001b[31m";
    final String ANSI_TEXT_GREEN = "\u001b[32m";
    final String ANSI_TEXT_YELLOW = "\u001b[33m";
    final String ANSI_TEXT_BLUE = "\u001b[34m";
    final String ANSI_TEXT_PURPLE = "\u001b[35m";
    final String ANSI_TEXT_CYAN = "\u001b[36m";
    final String ANSI_BACKGROUND_BLACK = "\uu001b[40m";

    @Autowired
    DepartmentRepo departmentRepo;
    @Autowired
    OfficeRepo officeRepo;
    @Autowired
    StaffDao staffDao;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        Department compsci = departmentRepo.save(new Department("Computer Science", "computersciencecork@department.mtu"));
        Department maths = departmentRepo.save(new Department("Mathematics", "mathematicscork@department.mtu"));
        Department construction = departmentRepo.save(new Department("Construction", "mathematicscork@department.mtu"));
        Department business = departmentRepo.save(new Department("Business", "buesinesscork@department.mtu"));
        officeRepo.save(new Office(3, 1, compsci));
        officeRepo.save(new Office(3, 2, compsci));
        officeRepo.save(new Office(3, 3, compsci));
        officeRepo.save(new Office(2, 0, maths));
        officeRepo.save(new Office(2, 2, maths));
        officeRepo.save(new Office(3, 0, construction));
        officeRepo.save(new Office(5, 5, business));
        Staff hos = staffDao.save(new Staff("hos@staff.mtu", passwordEncoder.encode("secret"), "HOS", false, false));
        Staff hod = staffDao.save(new Staff("hod@staff.mtu", passwordEncoder.encode("secret"), "HOD", false, false));

        // COMPLETED - Find all departments
        System.out.println("\n" + ANSI_BACKGROUND_BLACK + ANSI_TEXT_GREEN + "COMPLETED - Find all departments" + ANSI_TEXT_RESET);
        System.out.println(ANSI_TEXT_YELLOW + "departmentRepo.findAll().forEach(System.out::println);" + ANSI_TEXT_RESET);
        departmentRepo.findAll().forEach(System.out::println);

        // COMPLETED - Find all offices
        System.out.println("\n" + ANSI_BACKGROUND_BLACK + ANSI_TEXT_GREEN + "COMPLETED - Find all offices" + ANSI_TEXT_RESET);
        System.out.println(ANSI_TEXT_YELLOW + "officeRepo.findAll().forEach(System.out::println);" + ANSI_TEXT_RESET);
        officeRepo.findAll().forEach(System.out::println);

        // EXTRA - Find all departments and list their assigned offices
        System.out.println("\n" + ANSI_BACKGROUND_BLACK + ANSI_TEXT_PURPLE + "Extra - Find all departments and list their assigned offices" + ANSI_TEXT_RESET);
        System.out.println(ANSI_TEXT_YELLOW + "System.out.println(\"officeRepo.findAllByName(\\\"Computer Science\\\").forEach(...);\"" + ANSI_TEXT_RESET);
        departmentRepo.findDepartmentsAndOffices().forEach(
                (department -> {
                    System.out.println(department.getName());
                    department.getOfficeList().forEach(
                            (office)->
//                                    System.out.println("\t"+office)
                                    System.out.println("\tOffice "+office.getId())
                    );
                })
        );

        // COMPLETED - find all offices in a department
        System.out.println("\n" + ANSI_BACKGROUND_BLACK + ANSI_TEXT_GREEN + "COMPLETED - find all offices in a department" + ANSI_TEXT_RESET);
        System.out.println(ANSI_TEXT_YELLOW + "officeRepo.findAllByName(\"Computer Science\").forEach(System.out::println);" + ANSI_TEXT_RESET);
        officeRepo.findAllByDepartmentName("Computer Science").forEach(System.out::println);

        // COMPLETED - find a department by its ID
        System.out.println("\n" + ANSI_BACKGROUND_BLACK + ANSI_TEXT_GREEN + "COMPLETED - find a department by its ID" + ANSI_TEXT_RESET);
        System.out.println(ANSI_TEXT_YELLOW + "System.out.println(departmentRepo.findById(1));" + ANSI_TEXT_RESET);
        System.out.println(departmentRepo.findById(1));

        // COMPLETED - find an office by its ID
        System.out.println("\n" + ANSI_BACKGROUND_BLACK + ANSI_TEXT_GREEN + "COMPLETED - find an office by its ID" + ANSI_TEXT_RESET);
        System.out.println(ANSI_TEXT_YELLOW + "System.out.println(officeRepo.findById(1));" + ANSI_TEXT_RESET);
        System.out.println(officeRepo.findById(1));

        // COMPLETED - find all empty offices
        System.out.println("\n" + ANSI_BACKGROUND_BLACK + ANSI_TEXT_GREEN + "COMPLETED - find all empty offices" + ANSI_TEXT_RESET);
        System.out.println(ANSI_TEXT_YELLOW + "officeRepo.findAllByOccupancyEmpty().forEach(System.out::println);" + ANSI_TEXT_RESET);
        officeRepo.findAllByOccupancyEmpty().forEach(System.out::println);

        // COMPLETED - find all offices with space for staff
        System.out.println("\n" + ANSI_BACKGROUND_BLACK + ANSI_TEXT_GREEN + "COMPLETED - find all offices with space for staff" + ANSI_TEXT_RESET);
        System.out.println(ANSI_TEXT_YELLOW + "officeRepo.findAllByOccupancyNotFull().forEach(System.out::println);" + ANSI_TEXT_RESET);
        officeRepo.findAllByOccupancyNotFull().forEach(System.out::println);

        // COMPLETED - add a new department
        System.out.println("\n" + ANSI_BACKGROUND_BLACK + ANSI_TEXT_GREEN + "COMPLETED - add a new department" + ANSI_TEXT_RESET);
        String newDepartment = "DataLoader NewDepartment";
        System.out.println(ANSI_TEXT_YELLOW + "departmentRepo.findByName(newDepartment).ifPresentOrElse(System.out::println, ()-> System.out.println(\"Error - {\" + newDepartment + \"} does not exist\"));" + ANSI_TEXT_RESET);
        departmentRepo.findByName(newDepartment).ifPresentOrElse(System.out::println, ()-> System.out.println("Error - {" + newDepartment + "} does not exist"));
        System.out.println(ANSI_TEXT_YELLOW + "departmentRepo.save(new Department(newDepartment, \"newdepartment@dataloader.com\"));" + ANSI_TEXT_RESET);
        departmentRepo.save(new Department(newDepartment, "newdepartment@dataloader.com"));
        System.out.println(ANSI_TEXT_YELLOW + "departmentRepo.findByName(newDepartment).ifPresentOrElse(System.out::println, ()-> System.out.println(\"Error - {\" + newDepartment + \"} does not exist\"));" + ANSI_TEXT_RESET);
        departmentRepo.findByName(newDepartment).ifPresentOrElse(System.out::println, ()-> System.out.println("Error - {" + newDepartment + "} does not exist"));

        // COMPLETED - add a new office
        System.out.println("\n" + ANSI_BACKGROUND_BLACK + ANSI_TEXT_GREEN + "COMPLETED - add a new office" + ANSI_TEXT_RESET);
        int newOfficeId = (int)officeRepo.count()+1;
        System.out.println(ANSI_TEXT_YELLOW + "officeRepo.findById(newOfficeId).ifPresentOrElse(System.out::println, ()-> System.out.println(\"Error - Office {\" + newOfficeId + \"} does not exist\"));" + ANSI_TEXT_RESET);
        officeRepo.findById(newOfficeId).ifPresentOrElse(System.out::println, ()-> System.out.println("Error - Office {" + newOfficeId + "} does not exist"));
        System.out.println(ANSI_TEXT_YELLOW + "officeRepo.save(new Office(3, 1, compsci));" + ANSI_TEXT_RESET);
        officeRepo.save(new Office(3, 1, compsci));
        System.out.println(ANSI_TEXT_YELLOW + "officeRepo.findById(newOfficeId).ifPresentOrElse(System.out::println, ()-> System.out.println(\"Error - Office {\" + newOfficeId + \"} does not exist\"));" + ANSI_TEXT_RESET);
        officeRepo.findById(newOfficeId).ifPresentOrElse(System.out::println, ()-> System.out.println("Error - Office {" + newOfficeId + "} does not exist"));

        // COMPLETED - delete an office
        System.out.println("\n" + ANSI_BACKGROUND_BLACK + ANSI_TEXT_GREEN + "COMPLETED - service should delete an office" + ANSI_TEXT_RESET);
        System.out.println(ANSI_TEXT_YELLOW + "officeRepo.findById(newOfficeId).ifPresentOrElse(System.out::println, ()-> System.out.println(\"Error - {\" + newOfficeId + \"} does not exist\"));" + ANSI_TEXT_RESET);
        officeRepo.findById(newOfficeId).ifPresentOrElse(System.out::println, ()-> System.out.println("Error - {" + newOfficeId + "} does not exist"));
        System.out.println("officeRepo.deleteById(newOfficeId);");
        officeRepo.deleteById(newOfficeId);
        System.out.println(ANSI_TEXT_YELLOW + "officeRepo.findById(newOfficeId).ifPresentOrElse(System.out::println, ()-> System.out.println(\"Error - {\" + newOfficeId + \"} does not exist\"));" + ANSI_TEXT_RESET);
        officeRepo.findById(newOfficeId).ifPresentOrElse(System.out::println, ()-> System.out.println("Error - {" + newOfficeId + "} does not exist"));

        // COMPLETED - move an office to a different department
        System.out.println("\n" + ANSI_BACKGROUND_BLACK + ANSI_TEXT_GREEN + "COMPLETED - move an office to a different department" + ANSI_TEXT_RESET);
        int officeId = 5;
        System.out.println(ANSI_TEXT_YELLOW + "officeRepo.findById(officeId).ifPresentOrElse(System.out::println, ()-> System.out.println(\"Error - Office {\" + officeId + \"} does not exist\"));" + ANSI_TEXT_RESET);
        officeRepo.findById(officeId).ifPresentOrElse(System.out::println, ()-> System.out.println("Error - Office {" + officeId + "} does not exist"));
        System.out.println(ANSI_TEXT_YELLOW + "officeRepo.changeDepartment(officeId, business);" + ANSI_TEXT_RESET);
        officeRepo.changeDepartment(officeId, business);
        System.out.println(ANSI_TEXT_YELLOW + "officeRepo.findById(officeId).ifPresentOrElse(System.out::println, ()-> System.out.println(\"Error - Office {\" + officeId + \"} does not exist\"));" + ANSI_TEXT_RESET);
        officeRepo.findById(officeId).ifPresentOrElse(System.out::println, ()-> System.out.println("Error - Office {" + officeId + "} does not exist"));

        //ERROR -  update the number in an office, subject to not exceeding the maximum and not being a negative number.
        System.out.println("\n" + ANSI_BACKGROUND_BLACK + ANSI_TEXT_RED + "ERROR - update the number in an office, subject to not exceeding the maximum and not being a negative number." + ANSI_TEXT_RESET);
//        System.out.println(ANSI_TEXT_YELLOW + "officeRepo.findById(officeId).ifPresentOrElse(System.out::println, ()-> System.out.println(\"Error - Office {\" + officeId + \"} does not exist\"));" + ANSI_TEXT_RESET);
//        officeRepo.findById(officeId).ifPresentOrElse(System.out::println, ()-> System.out.println("Error - Office {" + officeId + "} does not exist"));
//        System.out.println(ANSI_TEXT_YELLOW + "officeRepo.changeCurrentOccupancy(officeId, 2);" + ANSI_TEXT_RESET);
//        officeRepo.changeCurrentOccupancy(officeId, 1);
//        System.out.println(ANSI_TEXT_YELLOW + "officeRepo.findById(officeId).ifPresentOrElse(System.out::println, ()-> System.out.println(\"Error - Office {\" + officeId + \"} does not exist\"));" + ANSI_TEXT_RESET);
//        officeRepo.findById(officeId).ifPresentOrElse(System.out::println, ()-> System.out.println("Error - Office {" + officeId + "} does not exist"));

        // COMPLETED - Implementation and Architecture
        System.out.println("\n" + ANSI_BACKGROUND_BLACK + ANSI_TEXT_GREEN + "COMPLETED - Implementation and Architecture" + ANSI_TEXT_RESET);
            // COMPLETED - H2 embedded database
            // COMPLETED - Maven
            // COMPLETED - Spring Boot
            // COMPLETED - JPA
            // COMPLETED - REST
            // COMPLETED - HATEOAS
            // COMPLETED - Security
            // COMPLETED - Lombok

        // COMPLETED - AOP
        System.out.println("\n" + ANSI_BACKGROUND_BLACK + ANSI_TEXT_GREEN + "COMPLETED - AOP" + ANSI_TEXT_RESET);

        // COMPLETED - Requests
        System.out.println("\n" + ANSI_BACKGROUND_BLACK + ANSI_TEXT_GREEN + "COMPLETED - Requests -> Use Postman client to make http requests" + ANSI_TEXT_RESET);
            //  COMPLETED - find all departments
            //  COMPLETED - find all offices
            //  COMPLETED - find all offices in a department
            //  COMPLETED - find a department by its ID
            //  COMPLETED - find an office by its ID
            //  COMPLETED - find all empty offices
            //  COMPLETED - find all offices with space for staff
            //  COMPLETED - add a new department
            //  COMPLETED - add a new office
            //  COMPLETED - delete an office
            //  COMPLETED - move an office to a different department
                // ! Error - Parameter value [1] did not match expected type [com.lochlann.assignment2.entities.Department (n/a)];
            //  COMPLETED - update the number in an office, subject to not exceeding the maximum and not being a negative number.
                // ! Error - Bad request

        // COMPLETED - HATEOAS
        System.out.println("\n" + ANSI_BACKGROUND_BLACK + ANSI_TEXT_GREEN + "COMPLETED - HATEOAS" + ANSI_TEXT_RESET);

        // COMPLETED - UNIT TESTS
        System.out.println("\n" + ANSI_BACKGROUND_BLACK + ANSI_TEXT_GREEN + "COMPLETED - UNIT TESTS" + ANSI_TEXT_RESET);
            // COMPLETED - get all offices
            // COMPLETED - delete an office
                // COMPLETED - office exists and is deleted (by HOD or HOS)
                // COMPLETED - office does not exist and cannot be deleted
                // COMPLETED - office exists but cannot be deleted because not user was provided
            // COMPLETED - posting a department you must write several requests
                // COMPLETED - data is correct and it can be created (by a HOS)
                // COMPLETED - data is correct but the user is not HOS (only if you have added security)
                // COMPLETED - data is correct but no user provided (only if you have added security)
                // COMPLETED - data correct but cannot be created because of a conflict
                // COMPLETED - data not able to bind to the DTO e.g. a required field is blank or a constraint is not adhered to

        // COMPLETED - Security
        System.out.println("\n" + ANSI_BACKGROUND_BLACK + ANSI_TEXT_GREEN + "COMPLETED - Security" + ANSI_TEXT_RESET);
            // COMPLETED - HOS and HOD staff can
                // COMPLETED - add a new office
                // COMPLETED - delete an office
                // COMPLETED - modify the number of people in an office
            // COMPLETED - HOS staff can
                // COMPLETED - add a department
                // COMPLETED - move an office from one department to another

        // COMPLETED - Validatio
        System.out.println("\n" + ANSI_BACKGROUND_BLACK + ANSI_TEXT_GREEN + "COMPLETED - Validation" + ANSI_TEXT_RESET);
            // COMPLETED - When data is received on the client side it should be validated and your web service should return an appropriate response code and message to let the client know what went wrong.
    }
}
