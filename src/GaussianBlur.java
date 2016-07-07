import java.awt.image.BufferedImage;

public class GaussianBlur {

	// The mask used for the blur
	private int[][] mask = { { 1, 2, 1 }, { 2, 4, 2 }, { 1, 2, 1 } };

	// The Picture representation of the image supplied by the user
	private Picture image;

	// The resulting BufferedImage with Gaussian blur applied
	private BufferedImage resultImage;

	public GaussianBlur(BufferedImage image) {
		this.image = new Picture(image);
		resultImage = new BufferedImage(image.getWidth(), image.getHeight(),
				image.getType());
	}

	/*
	 * To visualize, think first quadrant of Cartesian coordinate system, then
	 * reflect entire system by x-axis (x, y) = matrix[y][x] x = x - coordinates
	 * = column y = y - coordinates = row
	 */
	public int calcWeightedValueCenter(int x, int y) {
		int div = mask[0][0] + mask[0][1] + mask[0][2] + mask[1][0]
				+ mask[1][1] + mask[1][2] + mask[2][0] + mask[2][1]
				+ mask[2][2];

		/*
		 * extracts the red pixel values
		 */
		int red = (image.getPixelRedValue(x - 1, y - 1) * mask[0][0]
				+ image.getPixelRedValue(x, y - 1) * mask[0][1]
				+ image.getPixelRedValue(x + 1, y - 1) * mask[0][2]
				+ image.getPixelRedValue(x - 1, y) * mask[1][0]
				+ image.getPixelRedValue(x, y) * mask[1][1]
				+ image.getPixelRedValue(x + 1, y) * mask[1][2]
				+ image.getPixelRedValue(x - 1, y + 1) * mask[2][0]
				+ image.getPixelRedValue(x, y + 1) * mask[2][1] 
				+ image.getPixelRedValue(x + 1, y + 1) * mask[2][2])
				/ div;

		/*
		 * extracts the green pixel values
		 */

		int green = (image.getPixelGreenValue(x - 1, y - 1) * mask[0][0]
				+ image.getPixelGreenValue(x, y - 1) * mask[0][1]
				+ image.getPixelGreenValue(x + 1, y - 1) * mask[0][2]
				+ image.getPixelGreenValue(x - 1, y) * mask[1][0]
				+ image.getPixelGreenValue(x, y) * mask[1][1]
				+ image.getPixelGreenValue(x + 1, y) * mask[1][2]
				+ image.getPixelGreenValue(x - 1, y + 1) * mask[2][0]
				+ image.getPixelGreenValue(x, y + 1) * mask[2][1] 
				+ image.getPixelGreenValue(x + 1, y + 1) * mask[2][2])
				/ div;

		/*
		 * extracts the blue pixel values
		 */
		int blue = (image.getPixelBlueValue(x - 1, y - 1) * mask[0][0]
				+ image.getPixelBlueValue(x, y - 1) * mask[0][1]
				+ image.getPixelBlueValue(x + 1, y - 1) * mask[0][2]
				+ image.getPixelBlueValue(x - 1, y) * mask[1][0]
				+ image.getPixelBlueValue(x, y) * mask[1][1]
				+ image.getPixelBlueValue(x + 1, y) * mask[1][2]
				+ image.getPixelBlueValue(x - 1, y + 1) * mask[2][0]
				+ image.getPixelBlueValue(x, y + 1) * mask[2][1] 
				+ image.getPixelBlueValue(x + 1, y + 1) * mask[2][2])
				/ div;

		int rgbValue = red * 65536 + green * 256 + blue;

		return rgbValue;
	}

	/**
	 * Mode 1: top left corner Mode 2: top right corner Mode 3: bottom right
	 * corner Mode 4: bottom left corner
	 */
	public int calcWeightedValueCorner(int x, int y, int mode) {
		int div = 0;
		int red = 0;
		int green = 0;
		int blue = 0;
		int rgbValue = 0;

		// Top left corner
		if (mode == 1) {
			div = mask[1][1] + mask[1][2] + mask[2][1] + mask[2][2];

			red = (image.getPixelRedValue(x, y) * mask[1][1]
					+ image.getPixelRedValue(x + 1, y) * mask[1][2]
					+ image.getPixelRedValue(x, y + 1) * mask[2][1] 
					+ image.getPixelRedValue(x + 1, y + 1) * mask[2][2])
					/ div;

			green = (+image.getPixelGreenValue(x, y) * mask[1][1]
					+ image.getPixelGreenValue(x + 1, y) * mask[1][2]
					+ image.getPixelGreenValue(x, y + 1) * mask[2][1] 
					+ image.getPixelGreenValue(x + 1, y + 1) * mask[2][2])
					/ div;

			blue = (+image.getPixelBlueValue(x, y) * mask[1][1]
					+ image.getPixelBlueValue(x + 1, y) * mask[1][2]
					+ image.getPixelBlueValue(x, y + 1) * mask[2][1] 
					+ image.getPixelBlueValue(x + 1, y + 1) * mask[2][2])
					/ div;

			rgbValue = red * 65536 + green * 256 + blue;

			return rgbValue;
		}

		// Top right corner
		if (mode == 2) {
			div = mask[1][0] + mask[1][1] + mask[2][0] + mask[2][1];

			red = (image.getPixelRedValue(x - 1, y) * mask[1][0]
					+ image.getPixelRedValue(x, y) * mask[1][1]
					+ image.getPixelRedValue(x - 1, y + 1) * mask[2][0] 
					+ image.getPixelRedValue(x, y + 1) * mask[2][1])
					/ div;

			green = (image.getPixelGreenValue(x - 1, y) * mask[1][0]
					+ image.getPixelGreenValue(x, y) * mask[1][1]
					+ image.getPixelGreenValue(x - 1, y + 1) * mask[2][0] 
					+ image.getPixelGreenValue(x, y + 1) * mask[2][1])
					/ div;

			blue = (image.getPixelBlueValue(x - 1, y) * mask[1][0]
					+ image.getPixelBlueValue(x, y) * mask[1][1]
					+ image.getPixelBlueValue(x - 1, y + 1) * mask[2][0] 
					+ image.getPixelBlueValue(x, y + 1) * mask[2][1])
					/ div;

			rgbValue = red * 65536 + green * 256 + blue;

			return rgbValue;
		}

		// Bottom right corner
		if (mode == 3) {
			div = mask[0][0] + mask[0][1] + mask[1][0] + mask[1][1];

			red = (image.getPixelRedValue(x - 1, y - 1) * mask[0][0]
					+ image.getPixelRedValue(x, y - 1) * mask[0][1]
					+ image.getPixelRedValue(x - 1, y) * mask[1][0] 
					+ image.getPixelRedValue(x, y) * mask[1][1])
					/ div;

			green = (image.getPixelGreenValue(x - 1, y - 1) * mask[0][0]
					+ image.getPixelGreenValue(x, y - 1) * mask[0][1]
					+ image.getPixelGreenValue(x - 1, y) * mask[1][0] 
					+ image.getPixelGreenValue(x, y) * mask[1][1])
					/ div;

			blue = (image.getPixelBlueValue(x - 1, y - 1) * mask[0][0]
					+ image.getPixelBlueValue(x, y - 1) * mask[0][1]
					+ image.getPixelBlueValue(x - 1, y) * mask[1][0] 
					+ image.getPixelBlueValue(x, y) * mask[1][1])
					/ div;

			rgbValue = red * 65536 + green * 256 + blue;

			return rgbValue;
		}

		// Bottom left corner
		if (mode == 4) {
			div = mask[0][1] + mask[0][2] + mask[1][1] + mask[1][2];

			red = (image.getPixelRedValue(x, y - 1) * mask[0][1]
					+ image.getPixelRedValue(x + 1, y - 1) * mask[0][2]
					+ image.getPixelRedValue(x, y) * mask[1][1] 
					+ image.getPixelRedValue(x + 1, y) * mask[1][2])
					/ div;

			green = (image.getPixelGreenValue(x, y - 1) * mask[0][1]
					+ image.getPixelGreenValue(x + 1, y - 1) * mask[0][2]
					+ image.getPixelGreenValue(x, y) * mask[1][1] 
					+ image.getPixelGreenValue(x + 1, y) * mask[1][2])
					/ div;

			blue = (image.getPixelBlueValue(x, y - 1) * mask[0][1]
					+ image.getPixelBlueValue(x + 1, y - 1) * mask[0][2]
					+ image.getPixelBlueValue(x, y) * mask[1][1] 
					+ image.getPixelBlueValue(x + 1, y) * mask[1][2])
					/ div;

			rgbValue = red * 65536 + green * 256 + blue;

			return rgbValue;
		}

		return -1;
	}

	/**
	 * Mode 1: top edge Mode 2: right edge Mode 3: bottom edge Mode 4: left edge
	 */
	public int calcWeightedValueEdge(int x, int y, int mode) {
		int div = 0;
		int red = 0;
		int green = 0;
		int blue = 0;
		int rgbValue = 0;

		// Top edge
		if (mode == 1) {
			div = mask[1][0] + mask[1][1] + mask[1][2] + mask[2][0]
					+ mask[2][1] + mask[2][2];

			red = (image.getPixelRedValue(x - 1, y) * mask[1][0]
					+ image.getPixelRedValue(x, y) * mask[1][1]
					+ image.getPixelRedValue(x + 1, y) * mask[1][2]
					+ image.getPixelRedValue(x - 1, y + 1) * mask[2][0]
					+ image.getPixelRedValue(x, y + 1) * mask[2][1] 
					+ image.getPixelRedValue(x + 1, y + 1) * mask[2][2])
					/ div;

			green = (image.getPixelGreenValue(x - 1, y) * mask[1][0]
					+ image.getPixelGreenValue(x, y) * mask[1][1]
					+ image.getPixelGreenValue(x + 1, y) * mask[1][2]
					+ image.getPixelGreenValue(x - 1, y + 1) * mask[2][0]
					+ image.getPixelGreenValue(x, y + 1) * mask[2][1] 
					+ image.getPixelGreenValue(x + 1, y + 1) * mask[2][2])
					/ div;

			blue = (image.getPixelBlueValue(x - 1, y) * mask[1][0]
					+ image.getPixelBlueValue(x, y) * mask[1][1]
					+ image.getPixelBlueValue(x + 1, y) * mask[1][2]
					+ image.getPixelBlueValue(x - 1, y + 1) * mask[2][0]
					+ image.getPixelBlueValue(x, y + 1) * mask[2][1] 
					+ image.getPixelBlueValue(x + 1, y + 1) * mask[2][2])
					/ div;

			rgbValue = red * 65536 + green * 256 + blue;

			return rgbValue;
		}

		// Right edge
		if (mode == 2) {
			div = mask[0][0] + mask[0][1] + mask[1][0] + mask[1][1]
					+ mask[2][0] + mask[2][1];

			red = (image.getPixelRedValue(x - 1, y - 1) * mask[0][0]
					+ image.getPixelRedValue(x, y - 1) * mask[0][1]
					+ image.getPixelRedValue(x - 1, y) * mask[1][0]
					+ image.getPixelRedValue(x, y) * mask[1][1]
					+ image.getPixelRedValue(x - 1, y + 1) * mask[2][0] 
					+ image.getPixelRedValue(x, y + 1) * mask[2][1])
					/ div;

			green = (image.getPixelGreenValue(x - 1, y - 1) * mask[0][0]
					+ image.getPixelGreenValue(x, y - 1) * mask[0][1]
					+ image.getPixelGreenValue(x - 1, y) * mask[1][0]
					+ image.getPixelGreenValue(x, y) * mask[1][1]
					+ image.getPixelGreenValue(x - 1, y + 1) * mask[2][0] 
					+ image.getPixelGreenValue(x, y + 1) * mask[2][1])
					/ div;

			blue = (image.getPixelBlueValue(x - 1, y - 1) * mask[0][0]
					+ image.getPixelBlueValue(x, y - 1) * mask[0][1]
					+ image.getPixelBlueValue(x - 1, y) * mask[1][0]
					+ image.getPixelBlueValue(x, y) * mask[1][1]
					+ image.getPixelBlueValue(x - 1, y + 1) * mask[2][0] 
					+ image.getPixelBlueValue(x, y + 1) * mask[2][1])
					/ div;

			rgbValue = red * 65536 + green * 256 + blue;

			return rgbValue;
		}

		// Bottom edge
		if (mode == 3) {
			div = mask[0][0] + mask[0][1] + mask[0][2] + mask[1][0]
					+ mask[1][1] + mask[1][2];

			red = (image.getPixelRedValue(x - 1, y - 1) * mask[0][0]
					+ image.getPixelRedValue(x, y - 1) * mask[0][1]
					+ image.getPixelRedValue(x + 1, y - 1) * mask[0][2]
					+ image.getPixelRedValue(x - 1, y) * mask[1][0]
					+ image.getPixelRedValue(x, y) * mask[1][1] 
					+ image.getPixelRedValue(x + 1, y) * mask[1][2])
					/ div;

			green = (image.getPixelGreenValue(x - 1, y - 1) * mask[0][0]
					+ image.getPixelGreenValue(x, y - 1) * mask[0][1]
					+ image.getPixelGreenValue(x + 1, y - 1) * mask[0][2]
					+ image.getPixelGreenValue(x - 1, y) * mask[1][0]
					+ image.getPixelGreenValue(x, y) * mask[1][1] 
					+ image.getPixelGreenValue(x + 1, y) * mask[1][2])
					/ div;

			blue = (image.getPixelBlueValue(x - 1, y - 1) * mask[0][0]
					+ image.getPixelBlueValue(x, y - 1) * mask[0][1]
					+ image.getPixelBlueValue(x + 1, y - 1) * mask[0][2]
					+ image.getPixelBlueValue(x - 1, y) * mask[1][0]
					+ image.getPixelBlueValue(x, y) * mask[1][1] 
					+ image.getPixelBlueValue(x + 1, y) * mask[1][2])
					/ div;

			rgbValue = red * 65536 + green * 256 + blue;

			return rgbValue;
		}

		// Left edge
		if (mode == 4) {
			div = mask[0][1] + mask[0][2] + mask[1][1] + mask[1][2]
					+ mask[2][1] + mask[2][2];

			red = (image.getPixelRedValue(x, y - 1) * mask[0][1]
					+ image.getPixelRedValue(x + 1, y - 1) * mask[0][2]
					+ image.getPixelRedValue(x, y) * mask[1][1]
					+ image.getPixelRedValue(x + 1, y) * mask[1][2]
					+ image.getPixelRedValue(x, y + 1) * mask[2][1] 
					+ image.getPixelRedValue(x + 1, y + 1) * mask[2][2])
					/ div;

			green = (image.getPixelGreenValue(x, y - 1) * mask[0][1]
					+ image.getPixelGreenValue(x + 1, y - 1) * mask[0][2]
					+ image.getPixelGreenValue(x, y) * mask[1][1]
					+ image.getPixelGreenValue(x + 1, y) * mask[1][2]
					+ image.getPixelGreenValue(x, y + 1) * mask[2][1] 
					+ image.getPixelGreenValue(x + 1, y + 1) * mask[2][2])
					/ div;

			blue = (image.getPixelBlueValue(x, y - 1) * mask[0][1]
					+ image.getPixelBlueValue(x + 1, y - 1) * mask[0][2]
					+ image.getPixelBlueValue(x, y) * mask[1][1]
					+ image.getPixelBlueValue(x + 1, y) * mask[1][2]
					+ image.getPixelBlueValue(x, y + 1) * mask[2][1] 
					+ image.getPixelBlueValue(x + 1, y + 1) * mask[2][2])
					/ div;

			rgbValue = red * 65536 + green * 256 + blue;

			return rgbValue;
		}

		return -1;
	}

	/*
	 * This method applies the Gaussian blur to the Picture image and returns
	 * the computed result as a BufferedImage. You will loop through every pixel
	 * in the image starting at the top left (0, 0), row by row, from left to
	 * right. For each pixel, you should determine if it is a corner, edge, or
	 * center and set its new pixel value by calling the appropriate
	 * corresponding method. You will need to call the method setRGB(int x, int
	 * y, int rgb) from the Picture class to set the pixel value at a given
	 * coordinate pair. The argument rgb should be the return value from your
	 * call to the correct apply mask method.
	 */
	public BufferedImage applyFilter() {
		// for (each row)
		// for (each column)
		// check where in the matrix we are
		// resultImage.setRGB(column, row, applyMask(column, row))

		for (int row = 0; row < resultImage.getHeight(); row++) {
			for (int col = 0; col < resultImage.getWidth(); col++) {

				// Corners
				// Top left corner
				if (row == 0 && col == 0) {
					resultImage.setRGB(col, row,
							calcWeightedValueCorner(col, row, 1));
				}

				// Top right corner
				else if (row == 0 && col == resultImage.getWidth() - 1) {
					resultImage.setRGB(col, row,
							calcWeightedValueCorner(col, row, 2));
				}

				// Bottom right corner
				else if (row == resultImage.getHeight() - 1
						&& col == resultImage.getWidth() - 1) {
					resultImage.setRGB(col, row,
							calcWeightedValueCorner(col, row, 3));
				}

				// Bottom left corner
				else if (row == resultImage.getHeight() - 1 && col == 0) {
					resultImage.setRGB(col, row,
							calcWeightedValueCorner(col, row, 4));
				}

				// Center
				else if ((row > 0 && row < resultImage.getHeight() - 1)
						&& (col > 0 && col < resultImage.getWidth() - 1)) {
					// System.out.println("row" + row + " " + " col" + col +
					// "\n");
					resultImage.setRGB(col, row,
							calcWeightedValueCenter(col, row));
				}

				// Edges
				// Top edge
				else if (row == 0) {
					resultImage.setRGB(col, row,
							calcWeightedValueEdge(col, row, 1));
				}

				// Right edge
				else if (col == resultImage.getWidth() - 1) {
					resultImage.setRGB(col, row,
							calcWeightedValueEdge(col, row, 2));
				}

				// Bottom edge
				else if (row == resultImage.getHeight() - 1) {
					resultImage.setRGB(col, row,
							calcWeightedValueEdge(col, row, 3));
				}

				// Left edge
				else if (col == 0) {
					resultImage.setRGB(col, row,
							calcWeightedValueEdge(col, row, 4));
				}
			}
		}

		return resultImage;
	}
}