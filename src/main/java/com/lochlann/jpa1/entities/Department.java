package com.lochlann.jpa1.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
//    @Column(name = "department_name", nullable=false, unique=true)  // manually decide column name instead of auto camelcase-detection
    private String name;
    private String email;

    @OneToMany(mappedBy = "department") //see Office variables
    @ToString.Exclude
    @JsonIgnore
    private List<Office> officeList;

    // constructor overloading
    public Department(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
