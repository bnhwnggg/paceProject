package Parser;

import F1Simulator.Car;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Parser {

    public static RaceResult parse(List<Car> allCars) {
        List<PositionalResult> positionalResults = new ArrayList<>();
        
        for (Car car : allCars) {
            positionalResults.add(new PositionalResult(car, car.getPoints()));
        }
        
        Collections.sort(positionalResults);
        
        return new RaceResult(positionalResults);
    }
}
