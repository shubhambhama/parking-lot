package utils;

public class InputUtils {

    public static int getInt(String value) {
        try {
            return Integer.parseInt(value);
        }catch (NumberFormatException e) {
            return -1;
        }
    }

    public static String getValueByIndex(String value, String splitWith, int index) {
        try {
            return value.split(splitWith.isEmpty() ? " ": splitWith)[index];
        } catch (ArrayIndexOutOfBoundsException e) {
            return "";
        }
    }
}
