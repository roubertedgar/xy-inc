package com.xyinc.repository;


import com.xyinc.model.Place;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends CrudRepository<Place, Long> {

    @Query(" select distinct place " +
            "from Place place where " +
            "sqrt(power(place.coordinateX-?1, 2) + power(place.coordinateY-?2, 2)) <=?3 ")
    List<Place> findByCoordinate(Double coordinateX, Double coordinateY, Double metersRange);
}
