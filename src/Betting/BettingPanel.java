package Betting;

import javax.swing.*;
import java.awt.event.*;

public class BettingPanel extends JPanel {

    private JButton placeBetButton;
    private JComboBox<String> carChoices; // dropdown with car names
    private JLabel questionLabel;
    
    public BettingPanel() {
        initializeUI();
    }

    private void initializeUI() {
        placeBetButton = new JButton("Place Bet");
        carChoices = new JComboBox<>(); // you'll need to populate this with car names
        
        placeBetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic to handle the bet placement
                String selectedCar = (String) carChoices.getSelectedItem();
                // Store the user's choice and wait for race results
            }
        });
    }
    
}
