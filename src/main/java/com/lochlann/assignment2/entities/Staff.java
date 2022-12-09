package com.lochlann.assignment2.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "staff")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Staff {
    @Id
    private String email;
    @Column
    private String password;
    @Column
    private String role;
    @Column
    private boolean disabled, locked;
}
