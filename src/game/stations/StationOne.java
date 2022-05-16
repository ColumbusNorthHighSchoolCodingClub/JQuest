package game.stations;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import game.TemplateStation;

public class StationOne extends TemplateStation {

    public StationOne(String stationName) throws IOException {
        super(stationName);

        JFrame frame = new JFrame("Station One");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(650, 395));
        frame.setLayout(new FlowLayout());
        frame.pack();
        frame.setVisible(true);

        JLabel inputLabel = new JLabel("Player ID:");
        frame.add(inputLabel);

        JTextField inputField = new JTextField();
        inputField.setPreferredSize(new Dimension(100, 30));
        frame.add(inputField);

        JButton authenticateButton = new JButton("Set current player");
//        authenticateButton.setSize(new Dimension(70, 30));

        JTextArea profileDisplay = new JTextArea();
        profileDisplay.setWrapStyleWord(true);
        profileDisplay.setLineWrap(true);
        profileDisplay.setEditable(false);
        profileDisplay.setPreferredSize(new Dimension(250, 200));

        frame.add(profileDisplay);

        authenticateButton.addActionListener((e) -> {
            this.currentUserId = inputField.getText();

        });
        frame.add(authenticateButton);

        JTextField addMoneyInput = new JTextField();
        addMoneyInput.setPreferredSize(new Dimension(150, 30));
        frame.add(addMoneyInput);

        JButton addMoneyButton = new JButton("Add Monies");
        addMoneyButton.addActionListener(e -> {
            int moneyAmount = Integer.parseInt(addMoneyInput.getText());
            this.addMoneyToPlayer(moneyAmount);
            try {
                profileDisplay.setText(Integer.toString(this.getPlayerMoney()));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        frame.add(addMoneyButton);

        frame.pack();
        frame.revalidate();

    }

    public static void main(String... args) throws IOException, InterruptedException {
        StationOne st = new StationOne("StationOne");

    }

}
