package com.xyinc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Entity
@Table
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Place {

    @Id
    @Column
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private Double coordinateX;

    @Column
    private Double coordinateY;
}
