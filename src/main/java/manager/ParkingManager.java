package manager;

import exception.ApplicationException;
import exception.InvalidSlotNumberException;
import exception.NoParkingLotAvailableException;
import exception.NoVehicleFoundException;
import interfaces.CostCalculateStrategy;
import model.Slot;
import model.Vehicle;
import utils.ParkingUtils;
import utils.ValidationUtils;

import java.util.UUID;
import java.util.Vector;

public class ParkingManager {
    private static ParkingManager mManager = null;
    private final Vector<Slot> slots;
    private final int floorNumber;


    /**
     * Private Constructor
     */
    private ParkingManager(int floorNumber) {
        slots = new Vector<>();
        this.floorNumber = floorNumber;
    }

    public static ParkingManager getInstance(int floorNumber) {
        if (mManager == null) {
            mManager = new ParkingManager(floorNumber);
            return mManager;
        }
        return mManager;
    }

    public Vector<Slot> createParkingLot(int value) {
        try {
            if (!ValidationUtils.isValidSlot(value)) {
                return new Vector<>();
            }
            for (int i = 1; i <= value; i++) {
                Slot mSlot = new Slot(UUID.randomUUID().toString().substring(0, 7), i);
                slots.add(mSlot);
            }
            return slots;
        } catch (InvalidSlotNumberException e) {
            return new Vector<>();
        }
    }

    public int parkVehicle(Vehicle vehicle) throws NoParkingLotAvailableException {
        try {
            Slot which = ParkingUtils.whichSlotAvailable(slots);
            which.parkVehicle(vehicle);
            return which.getSlotNumber();
        } catch (NoParkingLotAvailableException e) {
            throw new NoParkingLotAvailableException("No Parking Slots Available");
        }
    }

    public int leaveVehicle(String vehicleNumber, int hours, CostCalculateStrategy costCalculateStrategy) throws NoVehicleFoundException, ApplicationException {
        if (hours <= 0) {
            throw new ApplicationException("Hours are not in proper format");
        }
        try {
            Slot slot = ParkingUtils.whichSlotVehicle(slots, vehicleNumber);
            slots.remove(slot);
            int costOfHours = costCalculateStrategy.costAsPerHour(hours);
            return costOfHours;
        } catch (NoVehicleFoundException e) {
            throw new NoVehicleFoundException("No Vehicle found with " + vehicleNumber + " vehicle number.");
        }
    }

    public Vector<Slot> getActiveReports() {
        return slots;
    }
}
