package cueare;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class MappingUtil {

	public String nums = "0123456789";
	public String abc = "abcdefghijklmnopqrstuvwxyz";
	public String alphabet;

	public static void main(String... args) {
		try {

			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		MappingUtil u = new MappingUtil();

		u.encodeAndDisplay();
	}

	public MappingUtil() {
		alphabet = nums + abc.toUpperCase() + abc + "% ";
	}

	public boolean[][] encode(String text) {

		text = text + "%".repeat(7 - text.length());

		boolean[][] spec = new boolean[7][7];

		int sum = 0;

		int[] regIndex = { 0, 2, 3, 4, 6 };

		for (int i : regIndex) {
			boolean[] bin = charToBinary(text.charAt(i));

			for (int j = 0; j < 6; j++) {
				spec[j][i] = bin[j];
				sum += bin[j] ? 1 : 0;

				if (j == 5 && i == 6) {
					// System.out.println(Arrays.toString(bin));
				}

			}

		}

		for (int i = 1; i < 6; i += 4) {
			boolean[] bin = charToBinary(text.charAt(i));
			int ind = 0;
			for (int j : regIndex) {
				spec[j][i] = bin[ind];
				sum += bin[ind] ? 1 : 0;
				ind++;
			}
		}

		spec[6][0] = charToBinary(text.charAt(1))[5];
		spec[6][6] = charToBinary(text.charAt(5))[5];

		sum += spec[6][0] ? 1 : 0;
		sum += spec[6][6] ? 1 : 0;

		// Orientation
		spec[1][5] = true;
		spec[5][1] = true;
		spec[5][5] = true;

		int newSum = 0;
		for (boolean[] e : spec) {
			for (boolean i : e) {
				newSum += i ? 1 : 0;
			}
		}

		// System.out.println("Checksum encoded as " + (newSum % 8));

		String ee = Integer.toBinaryString(newSum % 8);
		ee = "0".repeat(3 - ee.length()) + ee;
		// System.out.println(sum + " "+ sum%8 + " " + ee);

		for (int i = 2; i < 5; i++) {
			spec[6][i] = ee.charAt(i - 2) == '1';
		}

		return spec;
	}

	public String decode(boolean[][] spec) throws Exception {
		int[] regIndex = new int[] { 0, 2, 3, 4, 6 };

		ArrayList<boolean[]> chars = new ArrayList<>();

		int sum = 0;

		for (int i : regIndex) {
			boolean[] bin = new boolean[6];

			for (int j = 0; j < 6; j++) {
				bin[j] = spec[j][i];
				sum += spec[j][i] ? 1 : 0;

				if (j == 5 && i == 6) {
					// System.out.println(Arrays.toString(bin));
				}

			}
			chars.add(bin);

		}

		for (int i = 1; i < 6; i += 4) {
			boolean[] bin = new boolean[6];
			int ind = 0;
			for (int j : regIndex) {
				bin[ind] = spec[j][i];
				sum += spec[j][i] ? 1 : 0;
				ind++;
			}
			bin[5] = i == 2 ? spec[6][0] : spec[6][6]; // here???
			sum += (spec[6][0] ? 1 : 0) + (spec[6][6] ? 1 : 0);
			chars.add(i, bin);
		}

		int newSum = 0;
		for (boolean[] e : spec) {
			for (boolean i : e) {
				newSum += i ? 1 : 0;
			}
		}

		boolean[] swap = chars.get(1);
		swap[5] = spec[6][0];
		swap = chars.get(6).clone();
		swap[5] = spec[6][6];

		int correctSum = Integer
				.parseInt("" + (spec[6][2] ? '1' : '0') + (spec[6][3] ? '1' : '0') + (spec[6][4] ? '1' : '0'), 2);

		String str = Integer.toBinaryString(correctSum);
		int ct = (int) str.chars().filter(ch -> ch != '0').count();

		newSum -= ct;
		newSum = newSum % 8;

		// System.out.println(newSum + " " + correctSum);

		if (correctSum != newSum) {

			throw new Exception("Checksum invalid");
		}

		String output = "";
		for (boolean[] c : chars) {
			// System.out.println(Arrays.toString(c));
			output += binToText(c);
		}
		return output;

	}

	String binToText(boolean[] c) {
		String j = "";
		for (boolean a : c) {
			j += a ? 1 : 0;
		}

		int i = Integer.parseInt(j, 2);
		return (alphabet.charAt(i) + "").replace("%", "");
	}

	public boolean[] charToBinary(char a) {
		boolean[] out = new boolean[6];

		int num = alphabet.indexOf(a);
		String bin = Integer.toBinaryString(num);
		bin = "0".repeat(6 - bin.length()) + bin;

		for (int i = 0; i < 6; i++) {
			out[i] = bin.charAt(i) == '1' ? true : false;
		}
		return out;
	}

	public boolean[][] orient(boolean[][] a) {

		boolean[] orienters = { a[1][1], a[1][5], a[5][1], a[5][5] };

		int which = 0;
		int count = 0;
		for (int i = 0; i < orienters.length; i++) {
			if (!orienters[i]) {
				which = i;
			} else {
				count++;
			}
		}

		assert count == 3 : "Orientation markers are invalid";

		int[] rots = { 0, 270, 90, 180 };
		if (which == 0) {
			return a;
		} else {
			return rotate(a, rots[which]);
		}

	}

	public boolean[][] rotate(boolean[][] mat, int i) {
		final int M = mat.length;

		boolean[][] ret = null;
		for (int j = 0; j < i / 90; j++) {
			if (j > 0) {
				mat = ret;
			}
			ret = new boolean[M][M];

			for (int r = 0; r < M; r++) {
				for (int c = 0; c < M; c++) {
					ret[c][M - 1 - r] = mat[r][c];
				}
			}

		}

		return ret;
	}

	Image encoded;
	BufferedLoader b = new BufferedLoader();

	public void encodeAndDisplay() {

		JFrame frame = new JFrame();
		frame.getContentPane().setLayout(new FlowLayout());

		frame.setPreferredSize(new Dimension(650, 500));
		frame.setLocation(722, 240);

		BufferedImage img = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);

		JPanel p = new JPanel();
		p.add(new JLabel(new ImageIcon(img)));

		frame.add(p);
		JButton openSite = new JButton("Encode Link");

		JButton print = new JButton("Print");

		JTextField linkInput = new JTextField();
		linkInput.setPreferredSize(new Dimension(120, 30));
		frame.add(linkInput);
		frame.add(openSite);
		frame.add(print);
		openSite.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String toEncode = linkInput.getText();

				encoded = specToImage(encode(toEncode), false);

				encoded = encoded.getScaledInstance(400, 400, BufferedImage.SCALE_SMOOTH);

				p.removeAll();
				p.add(new JLabel(new ImageIcon(encoded)));
				p.repaint();
				frame.repaint();
				// frame.update(frame.getGraphics());
				frame.revalidate();

				// BigBrainCornerDetector.main("");
			}

		});

		print.addActionListener(e -> b.printImage(BufferedLoader.toBufferedImage(encoded)));

		frame.pack();
		frame.setVisible(true);

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	public BufferedImage specToImage(boolean[][] spec, boolean jFrame) {
		BufferedImage img = new BufferedImage(680, 680, BufferedImage.TYPE_INT_RGB); // old: 660 660
		Graphics g = img.getGraphics();
		g.fillRect(40, 40, 600, 600); // old: 30,30

		for (int i = 0; i < spec.length; i++) {
			for (int j = 0; j < spec[0].length; j++) {
				g.setColor(spec[j][i] ? Color.black : Color.white);
				g.fillRect(i * 80 + 55, j * 80 + 55, 80, 80); // old 45 45
			}
		}
		if (jFrame) {
			displayWithInfo(spec, img.getScaledInstance(400, 400, BufferedImage.SCALE_SMOOTH));

		}
		return img;
	}

	public void display(BufferedImage... img) {
		JFrame frame = new JFrame();
		frame.getContentPane().setLayout(new FlowLayout());

		for (BufferedImage i : img) {
			frame.getContentPane().add(new JLabel(new ImageIcon(i)));
		}

		frame.pack();
		// frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	String dec = "Decoded Message:  ";
	String msg = "";

	public void displayWithInfo(boolean[][] spec, Image i) {

		dec = "Decoded Message:  ";

		spec = this.orient(spec);
		// String dec = "Decoded Message: ";
		try {
			msg = this.decode(spec);
			dec += msg;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.err.print(e);
			msg = "Invalid checksum";
			dec += msg;
		}

		JLabel lab = new JLabel(dec);

		lab.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));

		JButton openSite = new JButton("Open Bit.ly Site");

		openSite.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String site = "https://www.bit.ly/" + MappingUtil.this.msg;
				try {
					Desktop.getDesktop().browse(new URI(site));
				} catch (Exception ee) {
					ee.printStackTrace();
				}
			}

		});

//		System.out.println(dec + " yeet");
		JFrame frame = new JFrame();
		frame.getContentPane().setLayout(new FlowLayout());

		frame.setPreferredSize(new Dimension(1000, 580));
		// frame.setPreferredSize(new Dimension(1000,680));

		frame.getContentPane().add(new JLabel(new ImageIcon(i)));

		frame.add(lab);
		frame.add(openSite);
		frame.pack();
		// frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

}
