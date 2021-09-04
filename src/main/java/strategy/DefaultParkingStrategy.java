package strategy;

import interfaces.CostCalculateStrategy;

public class DefaultParkingStrategy implements CostCalculateStrategy {
    @Override
    public int costAsPerHour(int hours) {
        return hours <= 2 ? 20 : (hours * 11);
    }
}
