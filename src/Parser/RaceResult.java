package Parser;

import F1Simulator.Car;
import java.util.List;

public class RaceResult {
    private List<PositionalResult> positions;

    public RaceResult(List<PositionalResult> positions) {
        this.positions = positions;
    }

    public List<PositionalResult> getPositions() {
        return positions;
    }

    public Car getTopCar() {
        if (positions == null || positions.isEmpty()) {
            return null;
        }
        return positions.get(0).getCar();
    }
    
}