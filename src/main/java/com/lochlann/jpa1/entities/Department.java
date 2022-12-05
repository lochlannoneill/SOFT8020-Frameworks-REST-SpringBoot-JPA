package com.lochlann.jpa1.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // relies on an auto-incremented column
    private int id;
//    @Column(name = "department_name", nullable=false, unique=true)  // manually decide column name instead of auto camalcase-detection
    private String name;
    private String email;

    @OneToMany(mappedBy = "department") // ! should this be deparment/office???  //see Office variables
    @ToString.Exclude
    private List<Office> officeList;
    // constructor overloading

    public Department(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
