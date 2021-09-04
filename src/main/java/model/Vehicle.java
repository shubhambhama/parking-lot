package model;

import constants.enums.VehicleType;
import lombok.Data;

@Data
public class Vehicle {
    private String vehicleNumber;
    private String vehicleColor;
    private String vehicleType = VehicleType.FOUR_WHEELER.name();

}
