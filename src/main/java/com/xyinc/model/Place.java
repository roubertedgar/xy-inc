package com.xyinc.model;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table
@Getter
public class Place {
    @Id
    @Column
    @GeneratedValue
    Long id;

    @Column
    String name;

    @Column
    Double coordinateX;

    @Column
    Double coordinateY;
}
