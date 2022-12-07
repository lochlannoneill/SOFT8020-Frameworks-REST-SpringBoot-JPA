package com.lochlann.assignment2.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "offices")  // *changes the name of the table in the localhost
public class Office {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
//    private String name;
    private int maxOccupancy;
    private int currentOccupancy;

    @ManyToOne
    @JsonIgnore
    private Department department;

    public Office(int maxOccupancy, int currentOccupancy, Department department) {
//        this.name = "" + department.getName().charAt(0) + department.getName().charAt((department.getName()).length()-1) + this.id;
        this.maxOccupancy = maxOccupancy;
        this.currentOccupancy = currentOccupancy;
        this.department = department;
    }
}
