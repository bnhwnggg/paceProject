package Betting;

import F1Simulator.Car;

public class Bet {
    private User user;
    private Car chosenCar;

    public Bet(User user, Car chosenCar) {
        this.user = user;
        this.chosenCar = chosenCar;
    }

    public Car getChosenCar() {
        return chosenCar;
    }
}
