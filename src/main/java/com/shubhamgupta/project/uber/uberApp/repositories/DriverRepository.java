package com.shubhamgupta.project.uber.uberApp.repositories;

import com.shubhamgupta.project.uber.uberApp.entities.Driver;
import com.shubhamgupta.project.uber.uberApp.entities.User;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {


    @Query(value = "SELECT d.*, ST_Distance(d.current_location, :pickUpLocation) as distance "+
            "from driver as d "+
            "where d.available = true AND ST_DWithin(d.current_location, :pickUpLocation, 10000) "+
            "Order by distance " +
            "Limit 10 ", nativeQuery = true
    )
    List<Driver> findTenNearestDrivers(Point pickUpLocation);


    @Query(value = "SELECT d.* "+
            "from driver as d "+
            "where d.available = true AND ST_DWithin(d.current_location, :pickUpLocation, 15000) "+
            "Order by rating DESC " +
            "Limit 10 ",nativeQuery = true)
    List<Driver> findTenNearByTopRatedDrivers(Point pickUpLocation);

    Optional<Driver> findByUser(User user);
}

