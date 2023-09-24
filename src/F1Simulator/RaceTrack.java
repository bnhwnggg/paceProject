package F1Simulator;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

public class RaceTrack {
    private List<Car> cars;
    private double circumference;

    public RaceTrack(int numCars, double circumference) {
        this.circumference = circumference;
        this.cars = new ArrayList<>();

        Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE};

        // Initialize cars
        for (int i = 0; i < numCars; i++) {
            Color carColor = colors[i % colors.length];
            this.cars.add(new Car(carColor));
        }


    }

    // Simulate the movement of all cars for a single tick (unit of time)
    public void simulateTick() {
        for (Car car : cars) {
            car.randomSpeedBoost(); // Randomly increase speed
            car.move();             // Move car forward
            car.checkFinishLine(this.circumference); // Check if car passed finish line
        }
    }

    public List<Car> getCars() {
        return cars;
    }

    public void resetRace() {
        for (Car car : cars) {
            if(!car.color.equals(Color.RED)){
                car.reset();
            }
        }
    }
    
}
