import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class F1Simulator {
    private JFrame frame;
    private RaceTrack track;
    private GraphicsRaceTrack graphicsTrack;
    private Timer raceTimer;
    private Timer endRaceTimer;
    private JButton startButton;
    private JButton resetButton;

    public F1Simulator() {
        track = new RaceTrack(5, 100);
        graphicsTrack = new GraphicsRaceTrack(track.getCars(), 100);

        frame = new JFrame("F1 Simulator");
        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        setupUI();
        frame.setVisible(true);
    }

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
    }

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
        });
        endRaceTimer.setRepeats(false);
        endRaceTimer.start();
        System.out.println("End race timer started."); // Debug message
    }
    

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
