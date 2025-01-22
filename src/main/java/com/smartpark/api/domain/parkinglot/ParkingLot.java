package com.smartpark.api.domain.parkinglot;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.smartpark.api.domain.vehicle.Vehicle;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * @author Loida Jane Anfone <anfoneloidajane@gmail.com>
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "parking_lot")
public class ParkingLot {

    @Column(name = "lot_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lot_registered_date", nullable = false, updatable = false, columnDefinition = "TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP")
    @CreatedDate
    private LocalDateTime registeredDate;

    @Column(name = "lot_uuid", columnDefinition = "BINARY(16)")
    private UUID lotId;

    @Column(name = "lot_location")
    private String location;

    @Column(name = "lot_capacity")
    private int capacity;

    @Column(name = "lot_occupied_spaces")
    private int occupiedSpaces;

    @Column(name = "lot_status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "parkingLot", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Vehicle> vehicles = new ArrayList<>();

    public enum Status {
        AVAILABLE,
        FULL
    }

    public ParkingLot markAsAvailable() {
        this.status = Status.AVAILABLE;
        return this;
    }

    public ParkingLot markAsFull() {
        this.status = Status.FULL;
        return this;
    }

    public ParkingLot updateStatus() {
         if (this.occupiedSpaces == this.capacity) {
             this.status = Status.FULL;
         } else {
             this.status = Status.AVAILABLE;
         }
         return this;
    }

    public ParkingLot addVehicle(Vehicle vehicle) {
        this.vehicles.add(vehicle);
        return this;
    }


    @PrePersist
    protected void onPrePersist() {
        this.lotId = UUID.randomUUID();
        this.registeredDate = LocalDateTime.now();
    }

}
