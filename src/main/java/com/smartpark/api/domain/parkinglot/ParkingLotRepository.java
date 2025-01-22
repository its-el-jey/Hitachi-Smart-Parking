package com.smartpark.api.domain.parkinglot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


/**
 * @author Loida Jane Anfone <anfoneloidajane@gmail.com>
 */
@Repository
public interface ParkingLotRepository extends JpaRepository<ParkingLot, Long> {

    Optional<ParkingLot> findByLotId(UUID lotId);

}
