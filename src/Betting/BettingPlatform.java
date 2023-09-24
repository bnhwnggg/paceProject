package Betting;

import F1Simulator.Car;
import Parser.*;
import java.util.List;

public class BettingPlatform {
    private Bet currentBet;
    private RaceResult raceResult;
    private User user;

    public BettingPlatform(User user) {
        this.user = user;
    }

    public void placeBet(Car car) {
        currentBet = new Bet(user, car);
    }

    public boolean checkBet() {
        if (currentBet == null || raceResult == null) {
            return false;
        }
    
        Car topCar = raceResult.getTopCar();
        if (currentBet.getChosenCar().equals(topCar)) {
            user.addCoin(new Coin(1));  // Reward the user with a coin
            return true;
        }
    
        return false;
    }
    
    

    public void setRaceResult(RaceResult raceResult) {
        this.raceResult = raceResult;
    }
}
