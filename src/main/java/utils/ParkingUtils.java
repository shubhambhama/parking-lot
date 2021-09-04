package utils;

import exception.NoParkingLotAvailableException;
import exception.NoVehicleFoundException;
import model.Slot;

import java.util.Vector;

public class ParkingUtils {

    public static Slot whichSlotAvailable(Vector<Slot> slots) throws NoParkingLotAvailableException {
        for (int i = 0; i < slots.size(); i++) {
            if( slots.get(i).isSlotEmpty()) {
                return slots.get(i);
            }
        }
        throw new NoParkingLotAvailableException("No Parking Slots Available");
    }

    public static Slot whichSlotVehicle(Vector<Slot> slots, String vehicleNumber) throws NoVehicleFoundException {
        for (int i = 0; i < slots.size(); i++) {
            Slot slot = slots.get(i);
            if (slot.getVehicle().getVehicleNumber().equals(vehicleNumber)) {
                return slot;
            }
        }
        throw new NoVehicleFoundException("No Vehicle Found Exception");
    }
}
