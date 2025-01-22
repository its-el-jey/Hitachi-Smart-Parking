package com.smartpark.api.domain.parkinglot;

import com.smartpark.api.config.RestMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Loida Jane Anfone <anfoneloidajane@gmail.com>
 */
@RestController
@RequestMapping("/parking-lot")
public class ParkingQueryResource {

    @Autowired
    ParkingLotRepository parkingLotRepository;

    @GetMapping("/all")
    public List<ParkingLot> getAllParkingLots() {
        return parkingLotRepository.findAll();
    }


    @GetMapping("/view-details")
    public ResponseEntity<RestMessage> getParkingLotDetails(@RequestParam UUID lotId) {
        ParkingLot parkingLot = parkingLotRepository.findByLotId(lotId).orElseThrow(() -> new NoSuchElementException("Parking lot not found"));

        RestMessage restMessage = new RestMessage("Parking lot retrieved successfully", parkingLot, HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.OK).body(restMessage);
    }



}
