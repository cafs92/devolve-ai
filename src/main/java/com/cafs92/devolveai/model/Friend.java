package com.cafs92.devolveai.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
}
