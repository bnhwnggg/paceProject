import java.awt.Color;

public class Car {
    private double speed; 
    private int points;
    private double position; 
    private Color color; // New Color property
    private boolean isBoosted;
    private int boostTimeRemaining;

    public Car(Color color) {
        this.speed = 0.5; 
        this.points = 0;
        this.position = 0;
        this.color = color; // Set the color in constructor
        this.isBoosted = false;
        this.boostTimeRemaining = 0; 
        
    }

    // Method to move the car forward based on its speed
    public void move() {
        this.position += this.speed;
    }

    // Method to increase speed randomly
    public void randomSpeedBoost() {
        if (isBoosted || Math.random() < 0.2) { // 20% chance to boost
            this.speed += 0.05; // Boost speed (you can adjust this value)
            if (!isBoosted) { // If not already boosted
                isBoosted = true;
                boostTimeRemaining = 5; // Boost lasts for 10 ticks (you can adjust this value)
            }
        }
        updateBoostStatus();
    }

    private void updateBoostStatus() {
        if (isBoosted) {
            boostTimeRemaining--;
            if (boostTimeRemaining <= 0) {
                isBoosted = false;
                this.speed -= 0.05; // Revert back to normal speed (ensure this matches the boost increment)
            }
        }
    }


    // Check if the car passed the finish line
    public void checkFinishLine(double trackCircumference) {
        if (this.position >= trackCircumference) {
            this.position -= trackCircumference; // Reset position
            this.points++; // Increase points
        }
    }

    public Color getColor() {
        return color;
    }

    public double getPosition() {
        return position;
    }

    public int getPoints(){
        return points;
    }

    public String getColorName() {
        if (color.equals(Color.RED)) {
            return "RED";
        } else if (color.equals(Color.BLUE)) {
            return "BLUE";
        } else if (color.equals(Color.GREEN)) {
            return "GREEN";
        } else if (color.equals(Color.YELLOW)) {
            return "YELLOW";
        } else if (color.equals(Color.ORANGE)) {
            return "ORANGE";
        } else {
            // For any other color that hasn't been added
            return "UNKNOWN";
        }
    }

    public void reset() {
        this.speed = 1;
        this.points = 0;
        this.position = 0;
        this.isBoosted = false;
        this.boostTimeRemaining = 0;
    }
    
    
}
