package F1Simulator;
import Betting.*;
import Parser.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class F1Simulator {
    private JFrame frame;
    private RaceTrack track;
    private GraphicsRaceTrack graphicsTrack;
    private Timer raceTimer;
    private Timer endRaceTimer;
    private JButton startButton;
    private JButton resetButton;
    private BettingPanel bettingPanel;
    private BettingPlatform bettingPlatform;
    private JComboBox<Car> carChoicesComboBox;
    private User user;
    private JLabel userBalanceLabel;


    //Create a Simulator
    public F1Simulator() {
        track = new RaceTrack(5, 100);
        graphicsTrack = new GraphicsRaceTrack(track.getCars(), 100);

        frame = new JFrame("F1 Simulator");
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        user = new User(1,"Hung","Bui","bnhwng","bnhwng1"); 
        bettingPlatform = new BettingPlatform(user);

        setupUI();
        frame.setVisible(true);
    }


    //setup UI
    private void setupUI() {
        JPanel buttonPanel = new JPanel();
        
        startButton = new JButton("Start Race");
        startButton.addActionListener(this::startRace);
        
        resetButton = new JButton("Reset");
        resetButton.addActionListener(this::resetRace);

        buttonPanel.add(startButton);
        buttonPanel.add(resetButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(graphicsTrack, BorderLayout.CENTER);

        bettingPanel = new BettingPanel();
        frame.add(bettingPanel, BorderLayout.EAST);

        carChoicesComboBox = new JComboBox<>(track.getCars().toArray(new Car[0]));
        JButton placeBetButton = new JButton("Place Bet");
        placeBetButton.addActionListener(e -> {
        Car chosenCar = (Car) carChoicesComboBox.getSelectedItem();
        bettingPlatform.placeBet(chosenCar);
        });
        userBalanceLabel = new JLabel("User Balance: " + user.getUserTotalCoin());

        buttonPanel.add(new JLabel("Select car:"));
        buttonPanel.add(carChoicesComboBox);
        buttonPanel.add(placeBetButton);
        buttonPanel.add(userBalanceLabel);

    }

    private void updateBalanceLabel() {
        userBalanceLabel.setText("User Balance: " + user.getUserTotalCoin());
    }


    //startRace button
    private void startRace(ActionEvent e) {
        raceTimer = new Timer(100, event -> {
            track.simulateTick();
            graphicsTrack.repaint();
        });
        raceTimer.start();
        System.out.println("Race started."); // Debug message
    
        Timer timeUpdater = new Timer(1000, event -> {
            double currentTime = graphicsTrack.getRemainingTime();
            if (currentTime > 0) {
                graphicsTrack.setRemainingTime(currentTime - 1);
            } else {
                ((Timer) event.getSource()).stop();
            }
        });
        timeUpdater.start();
        System.out.println("Time updater started."); // Debug message
    
        endRaceTimer = new Timer(30000, event -> {
            raceTimer.stop();
            timeUpdater.stop();
            endRaceTimer.stop();
            graphicsTrack.recordRaceResults();
            System.out.println("Race should have ended."); // Debug message
            RaceResult result = Parser.parse(track.getCars());
            bettingPlatform.setRaceResult(result);

            bettingPlatform.checkBet();
            updateBalanceLabel();
        });
        endRaceTimer.setRepeats(false);
        endRaceTimer.start();
        System.out.println("End race timer started."); // Debug message
    }
    
    //reset Button
    private void resetRace(ActionEvent e) {
        if (raceTimer != null) {
            raceTimer.stop();
        }
    
        if (endRaceTimer != null) {
            endRaceTimer.stop();
        }
        
        graphicsTrack.setRemainingTime(30); // Reset the displayed timer to 30 seconds
        track.resetRace();
        graphicsTrack.resetGraphics();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(F1Simulator::new);
    }
}
