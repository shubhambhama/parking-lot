import manager.ParkingLayer;

import java.util.Scanner;

public class Customer {

    public static void main(String[] args) {
        Scanner mScanner = new Scanner(System.in);
        System.out.println("*************  Parking Lot Engine  *************");
        System.out.println("1) Create Parking Lot (Type \"create\") {Capacity} (Example: create 4)");
        System.out.println("2) Park the vehicle (Type \"park\") {Car Number} {Type} {Color} (Example: park 4144 4-Wheeler Black)");
        System.out.println("3) Leave the parking (Type \"leave\") {VehicleNUmber} {Hours} (Example: leave 4144 3)");
        System.out.println("4) All Active Parking Report (Type \"status\")");
        System.out.println("5) Close the Parking (Type \"close\")");

        String input = "";
        do {
            System.out.print("Enter the input: ");
            input = mScanner.nextLine();

            String[] values = input.split(" ");
            ParkingLayer parkingLayer = ParkingLayer.valueOf(values[0]);
            parkingLayer.submit(values);
        } while (!input.equals("close"));
        System.out.println("************* Parking Software Terminated *************");
    }
}
