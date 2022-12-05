package com.lochlann.jpa1.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Studio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // relies on an auto-incremented column
    private int studioId;
    @Column(name = "studio_name", nullable=false, unique=true)  // manually decide column name instead of auto camalcase-detection
    private String studioName;
    private int yearFounded;

    // constructor overloading
    public Studio(String studioName, int yearFounded) {
        this.studioName = studioName;
        this.yearFounded = yearFounded;
    }
}
