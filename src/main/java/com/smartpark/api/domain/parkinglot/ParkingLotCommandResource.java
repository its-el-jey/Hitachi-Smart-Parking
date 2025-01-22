package com.smartpark.api.domain.parkinglot;

import com.smartpark.api.config.RestMessage;
import com.smartpark.api.domain.vehicle.Vehicle;
import com.smartpark.api.domain.vehicle.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

/**
 * @author Loida Jane Anfone <anfoneloidajane@gmail.com>
 */
@RestController
@RequestMapping("/parking-lot")
public class ParkingLotCommandResource {

    @Autowired
    ParkingLotRepository parkingLotRepository;

    @Autowired
    VehicleRepository vehicleRepository;

    @PostMapping("/register")
    public ResponseEntity<RestMessage> registerParkingLot(@RequestBody ParkingLotDto data) {

        if (data.getOccupiedSpaces() > data.getCapacity()) {
            RestMessage errorMessage = new RestMessage("Error: The number of occupied spaces exceeds the capacity", null, HttpStatus.BAD_REQUEST);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }

        ParkingLot newParkingLot = new ParkingLot();
        newParkingLot.setLocation(data.getLocation());
        newParkingLot.setCapacity(data.getCapacity());
        newParkingLot.setOccupiedSpaces(data.getOccupiedSpaces());

        ParkingLot savedNewParkingLot = parkingLotRepository.save(newParkingLot.markAsAvailable());

        // return response
        RestMessage restMessage = new RestMessage("Vehicle check-in successfully", newParkingLot, HttpStatus.OK);

        return ResponseEntity.status(HttpStatus.OK).body(restMessage);
    }

    @PostMapping("/check-in-vehicle")
    public ResponseEntity<RestMessage> checkInVehicle(@RequestParam(required = true) UUID lotId,
                                                         @RequestParam(required = true) String licensePlate) {

        ParkingLot parkingLot = parkingLotRepository.findByLotId(lotId).orElseThrow(NoSuchElementException::new);
        Vehicle vehicle = vehicleRepository.findByLicensePlate(licensePlate).orElseThrow(NoSuchElementException::new);

        // validate vehicle status
        if(vehicle.isCheckedIn()) {
            RestMessage errorMessage = new RestMessage("Error: The vehicle is already checked in to another parkingLot",  null, HttpStatus.BAD_REQUEST);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
        // validate parking lot capacity
        if (parkingLot.getOccupiedSpaces() == parkingLot.getCapacity()) {
            RestMessage errorMessage = new RestMessage("Error: The parking lot is already full", null, HttpStatus.BAD_REQUEST);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }

        // update and save parking lot
        parkingLot.setOccupiedSpaces(parkingLot.getOccupiedSpaces() + 1);
        parkingLotRepository.save(parkingLot.addVehicle(vehicle).updateStatus());

        // update and save vehicle
        vehicleRepository.save(vehicle.withParkingLot(parkingLot).markAsCheckedIn());

        // return response
        RestMessage restMessage = new RestMessage("Vehicle check-in successfully", parkingLot, HttpStatus.OK);

        return ResponseEntity.status(HttpStatus.OK).body(restMessage);
    }

    @PostMapping("/check-out-vehicle")
    public ResponseEntity<RestMessage> checkOutVehicle(@RequestParam(required = true) UUID lotId,
                                                      @RequestParam(required = true) String licensePlate) {

        ParkingLot parkingLot = parkingLotRepository.findByLotId(lotId).orElseThrow(NoSuchElementException::new);
        Vehicle vehicle = vehicleRepository.findByLicensePlate(licensePlate).orElseThrow(NoSuchElementException::new);

        // save and update vehicle
        vehicle.setParkingLot(null);
        vehicleRepository.save(vehicle.markAsCheckedOut());

        // save and update parking
        parkingLot.setOccupiedSpaces(parkingLot.getOccupiedSpaces() - 1);
        parkingLot.getVehicles().remove(vehicle);
        parkingLotRepository.save(parkingLot.updateStatus());

        // return response
        RestMessage restMessage = new RestMessage("Vehicle check-in successfully", parkingLot, HttpStatus.OK);

        return ResponseEntity.status(HttpStatus.OK).body(restMessage);
    }

}
