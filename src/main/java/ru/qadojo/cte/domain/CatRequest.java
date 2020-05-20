package ru.qadojo.cte.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CatRequest {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String userEmail;

    private String breed;

    @Enumerated(EnumType.STRING)
    private CatGender gender;

    @Enumerated(EnumType.STRING)
    private State state;


    public enum State {
        CREATED, BOOKED, FINISHED, CANCELED
    }
}
