package com.smartpark.api.domain.vehicle;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class VehicleDto {

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9 .-]+$", message = "can only contain alphanumeric characters, spaces, dots, and dashes")
    private String licensePlate;

    @NotBlank
    @Pattern(regexp = "CAR|MOTORCYCLE|TRUCK", message = "NOT VALID")
    private Vehicle.Type type;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9 .-]+$", message = "can only contain alphanumeric characters, spaces, dots, and dashes")
    private String ownerName;

}
