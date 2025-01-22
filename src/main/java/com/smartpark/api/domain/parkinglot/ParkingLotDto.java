package com.smartpark.api.domain.parkinglot;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.stereotype.Component;

@Data
@Component
public class ParkingLotDto {

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9 .-]+$", message = "can only contain alphanumeric characters, spaces, dots, and dashes")
    private String location;

    @NotBlank
    private int capacity;

    @NotBlank
    private int occupiedSpaces;

}
