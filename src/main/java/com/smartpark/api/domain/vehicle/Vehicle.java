package com.smartpark.api.domain.vehicle;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.smartpark.api.domain.parkinglot.ParkingLot;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "vehicle")
public class Vehicle {

    @Column(name = "vehicle_registered_date", nullable = false, updatable = false, columnDefinition = "TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP")
    @CreatedDate
    private LocalDateTime registeredDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "lot_id")
    private ParkingLot parkingLot;

    @Column(name = "vehicle_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vehicle_license_plate")
    private String licensePlate;

    @Column(name = "vehicle_owner_name")
    private String ownerName;

    @Column(name = "vehicle_type")
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "vehicle_status")
    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Type {
        CAR,
        MOTORCYCLE,
        TRUCK
    }

    public enum Status {
        NEW,
        CHECKIN,
        CHECKOUT
    }

    public Vehicle markAsNew() {
        this.status = Status.NEW;
        return this;
    }

    public Vehicle markAsCheckedIn() {
        this.status = Status.CHECKIN;
        return this;
    }

    public Vehicle markAsCheckedOut(){
        this.status = Status.CHECKOUT;
        return this;
    }

    public Vehicle withParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
        return this;
    }

    @JsonIgnore
    public boolean isNew() {
        return this.status == Status.NEW;
    }

    @JsonIgnore
    public boolean isCheckedIn() {
        return this.status == Status.CHECKIN;
    }

    @JsonIgnore
    public boolean isCheckedOut() {
        return this.status == Status.CHECKOUT;
    }

    @PrePersist
    protected void onPrePersist() {
        this.registeredDate = LocalDateTime.now();
    }

}
