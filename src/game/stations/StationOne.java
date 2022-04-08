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

        JLabel inputLabel = new JLabel("Player ID Number:");
        frame.add(inputLabel);

        JTextField inputField = new JTextField();
        inputField.setPreferredSize(new Dimension(100, 30));
        frame.add(inputField);

        JButton authenticateButton = new JButton("Get player profile");
//        authenticateButton.setSize(new Dimension(70, 30));

        JTextArea profileDisplay = new JTextArea();
        profileDisplay.setWrapStyleWord(true);
        profileDisplay.setLineWrap(true);
        profileDisplay.setEditable(false);
        profileDisplay.setPreferredSize(new Dimension(250, 200));

        frame.add(profileDisplay);

        authenticateButton.addActionListener((e) -> {
            authenticatePlayer(inputField.getText());
            profileDisplay.setText(this.currentUserProfile.toString().equals("{}") ? "Invalid ID"
                    : this.currentUserProfile.toString());
        });
        frame.add(authenticateButton);

        JTextField addProfileInput = new JTextField();
        addProfileInput.setPreferredSize(new Dimension(150, 30));
        frame.add(addProfileInput);

        JButton updateProfileButton = new JButton("Update Profile");
        updateProfileButton.addActionListener(e -> {
            String[] input = addProfileInput.getText().split(",");
            addToProfile(input[0], input[1]);
            updateUserProfileOnServer();
        });
        frame.add(updateProfileButton);

        frame.pack();
        frame.revalidate();

    }

    public static void main(String... args) throws IOException, InterruptedException {
        StationOne st = new StationOne("StationOne");

//        Thread.sleep(3000);
//
//        Scanner s = new Scanner(System.in);
//        System.out.println("Please enter your player ID");
//        st.authenticatePlayer(s.next());

    }

}
