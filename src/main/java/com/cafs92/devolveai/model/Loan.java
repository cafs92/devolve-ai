package com.cafs92.devolveai.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Friend friend;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Item item;

    @Column(nullable = false)
    private LocalDate date;
}
