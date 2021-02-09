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

    @ManyToOne
    private Friend friend;

    @OneToOne
    private Item item;

    @Column(nullable = false)
    private LocalDate localDate;
}
