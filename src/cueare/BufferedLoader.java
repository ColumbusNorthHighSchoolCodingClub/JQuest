package cueare;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * @author 22raor Contains BufferedImage utility classes
 */
public class BufferedLoader {

	public void printImage(BufferedImage img) {
		
		PrintActionListener m = new PrintActionListener(img);
		
		 new Thread(m).start();
		 
		 System.out.println("Printing...");
	}

	public static BufferedImage copyImage(BufferedImage source) {
		BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics g = b.getGraphics();
		g.drawImage(source, 0, 0, null);
		g.dispose();
		return b;
	}

	public static BufferedImage getImage(String path) {
		BufferedImage a = null;
		try {
			a = ImageIO.read(new File(path));
		} catch (Exception e) {
		}
		return a;
	}

	public static void saveImage(String path, BufferedImage img) {
		try {
			ImageIO.write(img, "png", new File(path));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void display(BufferedImage... img) {
		JFrame frame = new JFrame();
		frame.getContentPane().setLayout(new FlowLayout());

		for (BufferedImage i : img) {
			frame.getContentPane().add(new JLabel(new ImageIcon(i)));
		}

		frame.pack();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static BufferedImage toBufferedImage(Image img) {
		if (img instanceof BufferedImage) {
			return (BufferedImage) img;
		}

		BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

		Graphics2D bGr = bimage.createGraphics();
		bGr.drawImage(img, 0, 0, null);
		bGr.dispose();

		return bimage;
	}

	public class PrintActionListener implements Runnable {

		private BufferedImage image;

		public PrintActionListener(BufferedImage image) {
			this.image = image;
		}

		@Override
		public void run() {
			PrinterJob printJob = PrinterJob.getPrinterJob();
			printJob.setPrintable(new ImagePrintable(printJob, image));

			if (printJob.printDialog()) {
				try {
					printJob.print();
				} catch (PrinterException prt) {
					prt.printStackTrace();
				}
			}
		}

		public class ImagePrintable implements Printable {

			private double x, y, width;

			private int orientation;

			private BufferedImage image;

			public ImagePrintable(PrinterJob printJob, BufferedImage image) {
				PageFormat pageFormat = printJob.defaultPage();
				this.x = pageFormat.getImageableX();
				this.y = pageFormat.getImageableY();
				this.width = pageFormat.getImageableWidth();
				this.orientation = pageFormat.getOrientation();
				this.image = image;
			}

			@Override
			public int print(Graphics g, PageFormat pageFormat, int pageIndex) throws PrinterException {
				if (pageIndex == 0) {
					int pWidth = 0;
					int pHeight = 0;
					if (orientation == PageFormat.PORTRAIT) {
						pWidth = (int) Math.min(width, (double) image.getWidth());
						pHeight = pWidth * image.getHeight() / image.getWidth();
					} else {
						pHeight = (int) Math.min(width, (double) image.getHeight());
						pWidth = pHeight * image.getWidth() / image.getHeight();
					}
					g.drawImage(image, (int) x, (int) y, pWidth, pHeight, null);
					return PAGE_EXISTS;
				} else {
					return NO_SUCH_PAGE;
				}
			}

		}

	}
}
