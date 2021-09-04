package utils;

import exception.InvalidSlotNumberException;

public class ValidationUtils {

    public static boolean isValidSlot(int value) throws InvalidSlotNumberException {
        if (value <= 0 | value >= 11) {
            throw new InvalidSlotNumberException("Slot Should be greater than 1 and less than 11");
        }
        return true;
    }
}
