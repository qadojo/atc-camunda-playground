package ru.qadojo.cte.domain;

import lombok.Data;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
public class CatBooking {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(nullable = false)
    private CatRequest catRequest;

    @OneToOne
    @JoinColumn(nullable = false)
    private Cat cat;
}
