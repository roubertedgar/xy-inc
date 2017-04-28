package com.xyinc.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
public class Place {
    @Id
    @Column
    @GeneratedValue
    Long id;

    @Column
    String name;

    @Column
    Double longitude;

    @Column
    Double latitude;
}
