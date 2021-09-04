package manager;

import exception.ApplicationException;
import exception.NoParkingLotAvailableException;
import exception.NoVehicleFoundException;
import interfaces.ParkingLayerI;
import model.Slot;
import model.Vehicle;
import strategy.DefaultParkingStrategy;
import utils.InputUtils;

import java.util.Vector;
import java.util.logging.Logger;

public enum ParkingLayer implements ParkingLayerI {
    create {
        @Override
        public void submit(String[] details) {
            System.out.println("Coming into create");
            Vector<Slot> slots = mManager.createParkingLot(InputUtils.getInt(details[1]));
            if (slots.isEmpty()) {
                logger.severe("Error while creating parking lot.");
                return;
            }
            logger.info("Parking Lot Created with slots: " + slots.size());
        }
    },
    park {
        @Override
        public void submit(String[] details) {
            Vehicle vehicle = new Vehicle();
            vehicle.setVehicleNumber(details[1]);
            vehicle.setVehicleType(details[2]);
            vehicle.setVehicleColor(details[3]);
            try {
                int slotNumber = mManager.parkVehicle(vehicle);
                logger.info("Vehicle is parked with slot number: " + slotNumber);
            } catch (NoParkingLotAvailableException e) {
                logger.severe(e.getMessage());
            }
        }
    },
    leave {
        @Override
        public void submit(String[] details) {
            try {
                String vehicleNumber = details[1];
                System.out.println("vehicleNumber: " + vehicleNumber);
                System.out.println("details[2]: " + details[2]);
                int costOfHours = mManager.leaveVehicle(vehicleNumber, InputUtils.getInt(details[2]), new DefaultParkingStrategy());
                logger.info("Vehicle number " + vehicleNumber + " exited with amount " + costOfHours);
            } catch (NoVehicleFoundException | ApplicationException e) {
                logger.severe(e.getMessage());
            }
        }
    },
    status {
        @Override
        public void submit(String[] details) {
            logger.info("Slot Number | Vehicle number");
            mManager.getActiveReports().forEach(slot -> {
                if (!slot.isSlotEmpty()) {
                    logger.info(slot.getSlotNumber() + " " + slot.getVehicle().getVehicleNumber());
                    logger.info("\n");
                }
            });
        }
    };
    ParkingManager mManager = ParkingManager.getInstance(1);
    Logger logger = Logger.getLogger("ParkingLayer");
}