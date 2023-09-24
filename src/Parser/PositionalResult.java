package Parser;
import F1Simulator.Car;

class PositionalResult implements Comparable<PositionalResult> {
    private Car car;
    private int points;

    public PositionalResult(Car car, int points) {
        this.car = car;
        this.points = points;
    }

    public Car getCar() {
        return car;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public int compareTo(PositionalResult other) {
        return Integer.compare(other.points, this.points); // To sort in descending order by points
    }
}
