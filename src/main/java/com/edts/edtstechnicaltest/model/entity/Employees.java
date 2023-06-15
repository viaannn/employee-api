package com.edts.edtstechnicaltest.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Employees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "grade")
    private int grade;

    @Column(name = "salary")
    private long salary;
}
