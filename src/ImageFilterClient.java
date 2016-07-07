import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ImageFilterClient {

	public static void main(String[] args) {
		// Create a new File object with the image file name as argument (i.e.
		// image.jpg)

		// Create a new LoadImage object with the loaded File as an argument
		// Get the BufferedImage from the LoadImage object and save it in a
		// variable
		// (originalImage)
		// This BufferedImage variable will represent the original, unchanged
		// image

		// Create another LoadImage object with the same File as an argument
		// Get the BufferedImage from the LoadImage object and save it in
		// another
		// variable (resultImage)
		// This BufferedImage variable will be used with the GaussianBlur class

		// Using the resultImage, create a GaussianBlur object, call the
		// applyFilter() method,
		// and store the result back into resultImage

		// Create the GUI components

		File loadFile = new File("/home/manmohit/Desktop/Noisy.jpg");
		// LoadImage li = new LoadImage(loadFile);
		// LoadImage li2 = new LoadImage(loadFile);
		BufferedImage orignalImage = null;
		BufferedImage resultImage = null;

		try {
			orignalImage = ImageIO.read(loadFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			resultImage = ImageIO.read(loadFile);
			GaussianBlur gb = new GaussianBlur(resultImage);
			resultImage = gb.applyFilter();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JFrame frame = new JFrame();

		GridLayout layout = new GridLayout(1, 2);

		JPanel panel = new JPanel(layout);

		ImageIcon icon1 = new ImageIcon();
		ImageIcon icon2 = new ImageIcon();
		icon1.setImage(orignalImage);
		icon2.setImage(resultImage);

		panel.add(new JLabel(icon1));
		panel.add(new JLabel(icon2));

		frame.add(panel);
		frame.pack();
		frame.setTitle("Image Filter");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

}
