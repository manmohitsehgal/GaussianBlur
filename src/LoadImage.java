import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class LoadImage {

	private BufferedImage image;

	public LoadImage(File f) {
		// TODO: load image from file and store in instance variable
		try {
			image = ImageIO.read(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public BufferedImage getOriginalImage() {
		return image;
		// TODO: return BufferedImage instance variable
	}
}
