package com.lochlann.jpa1;

import com.lochlann.jpa1.dao.StudioRepo;
import com.lochlann.jpa1.entities.Studio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// * if persistent database -> data load not required
@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    StudioRepo studioRepo;

    @Override
    public void run(String... args) throws Exception {
        studioRepo.save(new Studio("Warner Brothers", 1923));
        studioRepo.save(new Studio("Walt Disney", 1923));
        studioRepo.save(new Studio("Paramount", 1912));
        studioRepo.save(new Studio("Universal", 1911));

        System.out.println();
        System.out.println("studioRepo.findAll().forEach(System.out::println);");
        studioRepo.findAll().forEach(System.out::println);

        System.out.println();
        System.out.println("System.out.println(studioRepo.findByStudioName(\"studioName\"));");
        String studioName = "Universal";
        studioRepo.findByStudioName("Universal").ifPresentOrElse(System.out::println, ()-> System.out.println("Error - {" + studioName + "}does not exists"));
        studioRepo.findByStudioName("Not exists").ifPresentOrElse(System.out::println, ()-> System.out.println("Error - {" + studioName + "} does not exists"));

        System.out.println();
        System.out.println("studioRepo.changeName(1, \"Value Changed\");");
        System.out.println(studioRepo.findByStudioId(1));
        studioRepo.changeName(1, "Value Changed");
        System.out.println(studioRepo.findByStudioId(1));

        System.out.println();
        System.out.println("studioRepo.findAllStudiosAlphabetically().forEach(System.out::println);");
        studioRepo.findAllStudiosAlphabetically().forEach(System.out::println);


//        System.out.println();
//        System.out.println("studioRepo.findAllByOrderByYearFounded().forEach(System.out::println);");
//        studioRepo.findAllByOrderByYearFounded().forEach(System.out::println);

//        System.out.println();
//        System.out.println("studioRepo.findByStudioNameContaining(\"e\").forEach(System.out::println);");
//        studioRepo.findByStudioNameContaining("e").forEach(System.out::println);

    }
}
