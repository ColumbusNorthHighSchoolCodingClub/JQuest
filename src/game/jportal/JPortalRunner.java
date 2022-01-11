package game.jportal;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.Image;
import javax.swing.border.Border;

public class JPortalRunner extends JFrame {
	static ArrayList<Integer> timelineImg;
	static ArrayList<Image> timelineImages;
	static int upInd;
	static int midInd;
	static int downInd;
	static String command;

	// JFrame
	static JFrame f;
	// JButtons up and down to scroll through timelines
	// eventually will be replaced with QR Commands
	static JButton upBtn, downBtn;
	// Label to display text // eventaully will be images of timelines
	static JLabel upImg, midImg, downImg, portImg;

	// Main class
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		JPortalRunner jport = new JPortalRunner();
		timelineImages = jport.addImages();

		upInd = timelineImages.size() - 1;
		midInd = 0;
		downInd = 1;

		// Creating a new frame to store text field and button
		f = new JFrame("JPortal");

		// three images displayed to screen
		upImg = new JLabel(new ImageIcon(timelineImages.get(upInd)));
		midImg = new JLabel(new ImageIcon(timelineImages.get(midInd)));
		Border border = BorderFactory.createLineBorder(Color.BLUE, 5);
		// border over middle image
		midImg.setBorder(border);
		portImg = new JLabel(new ImageIcon(timelineImages.get(midInd)));
		//
		portImg.setBounds(0, 0, 300, 300);
		downImg = new JLabel(new ImageIcon(timelineImages.get(downInd)));

		// change indices of image when buttons are pressed
		jport.buttons();

		// Create image panel
		JPanel imgP = new JPanel();
		imgP.setLayout(new BorderLayout());

		imgP.add(upImg, "North");
		imgP.add(midImg, "Center");
		imgP.add(downImg, "South");

		// scroll panel
		JPanel scrollP = new JPanel();
		scrollP.setLayout(new BorderLayout());

		scrollP.add(upBtn, "North");
		scrollP.add(imgP, "Center");
		scrollP.add(downBtn, "South");

		// portal panel// circle not working lol!
//        CirclePanel portalP = new CirclePanel();
		JPanel portalP = new JPanel();
		portalP.setLayout(new BorderLayout());

		portalP.add(portImg, "Center");
		// portalP.setBounds(0,0)

		f.getContentPane().add(scrollP, "West");
		f.getContentPane().add(portalP, "Center");
		// set size // eventually will be full screen
		f.setSize(900, 700);

		f.show();
	}

	// change indices of images if buttons are pressed
	// eventually will respond to commands from the QR
	public void buttons() {
		upBtn = new JButton("up");
		upBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				upInd--;
				midInd--;
				downInd--;
				checkDisplay();
			}
		});
		downBtn = new JButton("down");
		downBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				upInd++;
				midInd++;
				downInd++;
				checkDisplay();
			}
		});
	}

	public void checkDisplay() {
		// scroll back to end of list
		if (upInd < 0) {
			upInd = timelineImages.size() + upInd;
		}
		if (midInd < 0) {
			midInd = timelineImages.size() + midInd;
		}
		if (downInd < 0) {
			downInd = timelineImages.size() + downInd;
		}

		// scroll up to top of list
		if (upInd == timelineImages.size()) {
			upInd = 0 + (upInd - timelineImages.size());
		}
		if (midInd == timelineImages.size()) {
			midInd = 0 + (midInd - timelineImages.size());
		}
		if (downInd == timelineImages.size()) {
			downInd = 0 + (downInd - timelineImages.size());
		}

		// update images
		upImg.setIcon(new ImageIcon(timelineImages.get(upInd)));
		midImg.setIcon(new ImageIcon(timelineImages.get(midInd)));
		downImg.setIcon(new ImageIcon(timelineImages.get(downInd)));
		portImg.setIcon(new ImageIcon(timelineImages.get(midInd)));
	}

	public ArrayList<Image> addImages() {
		ArrayList<Image> images = new ArrayList<>();
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		// how to resize properly/according to screen size?
		// some will be locked , check player status first
		Image cnhs = toolkit.getImage(this.getClass().getResource("/images/CNHS.jpg"));
		Image cnhsSc = cnhs.getScaledInstance(300, 200, Image.SCALE_DEFAULT);
		images.add(cnhsSc);

		Image wildWest = toolkit.getImage(this.getClass().getResource("/images/Wild West.jpg"));
		Image wildWestSc = wildWest.getScaledInstance(300, 200, Image.SCALE_DEFAULT);
		images.add(wildWestSc);

		Image eighties = toolkit.getImage(this.getClass().getResource("/images/80s.jpg"));
		Image eightiesSc = eighties.getScaledInstance(300, 200, Image.SCALE_DEFAULT);
		images.add(eightiesSc);

		Image medieval = toolkit.getImage(this.getClass().getResource("/images/medieval.jpg"));
		Image medievalSc = medieval.getScaledInstance(300, 200, Image.SCALE_DEFAULT);
		images.add(medievalSc);

		Image dinosaur = toolkit.getImage(this.getClass().getResource("/images/Dinosaur.jpg"));
		Image dinosaurSc = dinosaur.getScaledInstance(300, 200, Image.SCALE_DEFAULT);
		images.add(dinosaurSc);
		// add images

		return images;
	}

	public static class CirclePanel extends JPanel {
		@Override
		protected void paintComponent(Graphics g) {
			g.drawOval(0, 0, g.getClipBounds().width, g.getClipBounds().height);
		}
	}
}