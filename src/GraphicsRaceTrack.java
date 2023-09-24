import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GraphicsRaceTrack extends JPanel {
    private List<Car> cars;
    private double trackCircumference;
    private List<String> raceResults = new ArrayList<>();
    private double remainingTime = 30;

    public GraphicsRaceTrack(List<Car> cars, double trackCircumference) {
        this.cars = cars;
        this.trackCircumference = trackCircumference;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    
        // Draw track
        g.drawOval(50, 50, 400, 400);
    
        // Draw finish line
        g.setColor(Color.BLACK);
        g.drawLine(50, 250, 450, 250); // A horizontal line at the top of the track
    
        // Draw cars and their points
        int yOffset = 0;
        for (Car car : cars) {
            double angle = 2 * Math.PI * (car.getPosition() / trackCircumference);
            int carX = 250 + (int)(200 * Math.cos(angle)) - 5;
            int carY = 250 + (int)(200 * Math.sin(angle)) - 5;
        
            g.setColor(car.getColor());
            g.fillOval(carX, carY, 10, 10);
            
            // Draw car points and color name
            g.setColor(car.getColor());
            String carDetails = car.getColorName() + ": " + car.getPoints() + " points";
            g.drawString(carDetails, 50, 450 + yOffset);
            
            yOffset += 15;
        }
    
        int yOffsetResults = yOffset;
        for (String result : raceResults) {
            g.drawString(result, 50, 500 + yOffsetResults);
            yOffsetResults += 15;
        }
    
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Time left: " + String.format("%.1f", remainingTime) + "s", 50, 30);
    }
    
public void recordRaceResults() {
        raceResults.clear();
        for (Car car : cars) {
            String result = car.getColorName() + ": " + car.getPoints() + " points";
            raceResults.add(result);
        }
        repaint();
}
    

public void setRemainingTime(double seconds) {
    this.remainingTime = seconds;
    repaint();
}


public void resetGraphics() {
    raceResults.clear();
    repaint();
}

public double getRemainingTime() {
    return remainingTime;
}

}
