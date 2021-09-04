package model;

import lombok.Data;

import java.util.Vector;

@Data
public class Slot {
    private String slotId;
    private int slotNumber;
    private Vehicle vehicle;
    private boolean isSlotEmpty;

    public Slot(String slotId, int slotNumber) {
        this.slotId = slotId;
        this.slotNumber = slotNumber;
        isSlotEmpty = true;
    }

    public void parkVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.isSlotEmpty = false;
    }
}
