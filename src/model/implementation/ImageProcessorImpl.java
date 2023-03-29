package model.implementation;

import model.interfaces.Image;
import model.interfaces.ImageProcessor;
import model.interfaces.Pixel;

/**
 * Implementation for ImageProcessor, to process given images.
 */
public class ImageProcessorImpl implements ImageProcessor {

  @Override
  public Image getChannel(Image image, int channel) {
    return image.getImageChannel(channel);
  }

  @Override
  public Image flipImage(Image image, boolean horizontal) {
    return image.flipImage(horizontal);
  }

  @Override
  public Image getValue(Image image) {
    return image.getValueImage();
  }

  @Override
  public Image getIntensity(Image image) {
    return image.getIntensityImage();
  }

  @Override
  public Image getLuma(Image image) {
    return image.getLumaImage();
  }

  @Override
  public Image alterBrightness(Image image, int value) {
    return image.alterBrightness(value);
  }

  @Override
  public Image getGreyscale(Image image) {
    double[][] grey = Filters.LUMA;
    return image.linearTransformation(grey);
  }

  @Override
  public Image[] getSplitChannels(Image image) {
    return new Image[]{image.getImageChannel(0), image.getImageChannel(1),
            image.getImageChannel(2)};
  }

  @Override
  public Image combineGreyScaleImages(Image first, Image second, Image third) {
    Pixel[][] pixels = new PixelRGB[first.getHeight()][first.getWidth()];
    for (int i = 0; i < first.getHeight(); i++) {
      for (int j = 0; j < first.getWidth(); j++) {
        pixels[i][j] = new PixelRGB(first.getPixelChannel(i, j, 0),
                second.getPixelChannel(i, j, 1), third.getPixelChannel(i, j, 2));
      }
    }
    return new ImageImpl(pixels);
  }

  @Override
  public Image sepiaTone(Image image) {
    double[][] sepia = Filters.SEPIA;
    return image.linearTransformation(sepia);
  }

  @Override
  public Image applyFilter(Image image, int sizeX, int sizeY, float[][] filter) {

    if (sizeX * sizeY != filter.length * filter[0].length) {
      throw new IllegalArgumentException("The filter sizes X: " + sizeX + " Y: " + sizeY
              + " should be equal to the length of the filter: " + filter.length);
    }

    if (sizeX % 2 == 0 || sizeY % 2 == 0) {
      throw new IllegalArgumentException("The filter dimensions must be odd! Given sizeX: "
              + sizeX + " sizeY: " + sizeY);
    }

    if (sizeX > image.getWidth() || sizeY > image.getHeight()) {
      throw new IllegalArgumentException("Size of filter is greater than the image dimension! "
              + "Filter size X: " + sizeX + " Y: " + sizeY + " Image size: " + image.getSize());
    }

    int padWidth = sizeX / 2;
    int padHeight = sizeY / 2;

    // TODO: make function for this in Image?
    Pixel[][] newImage = new Pixel[image.getHeight()][image.getWidth()];
    for (int row = 0; row < image.getHeight(); row++) {
      for (int col = 0; col < image.getWidth(); col++) {
        newImage[row][col] = new PixelRGB(0, 0, 0);
      }
    }
    Image filteredImage = new ImageImpl(newImage);


    for (int row = 0; row < image.getHeight(); row++) {
      for (int col = 0; col < image.getWidth(); col++) {
        for (int ch = 0; ch < image.getNumberOfChannels(); ch++) {

          float sum = 0;

          for (int filterRow = 0; filterRow < sizeY; filterRow++) {
            for (int filterCol = 0; filterCol < sizeX; filterCol++) {
              int imageRow = row - padHeight + filterRow;
              int imageCol = col - padWidth + filterCol;

              if (imageRow >= 0 && imageRow < image.getHeight() &&
                      imageCol >= 0 && imageCol < image.getWidth()) {
                sum += image.getPixelChannel(imageRow, imageCol, ch) * filter[filterRow][filterCol];
              }
            }
          }

          int newValue = (int) Math.round(sum);
          newValue = Math.max(0, Math.min(255, newValue)); // Clamp the value between 0 and 255.
          filteredImage.setPixelChannel(row, col, ch, newValue);
        }
      }
    }
    return filteredImage;
  }

  /**
   * Applies a blur effect to the given image using a Gaussian blur filter.
   *
   * @param image the image to which the blur effect will be applied.
   * @return a new Image with the blur effect applied.
   */
  @Override
  public Image applyBlur(Image image) {
    float[][] arr = new float[][]{
            {1f / 16, 1f / 8, 1f / 16},
            {1f / 8, 1f / 4, 1f / 8},
            {1f / 16, 1f / 8, 1f / 16}};

    return applyFilter(image, 3, 3, arr);
  }

  /**
   * Applies a sharpening effect to the given image using a custom sharpening filter.
   *
   * @param image the image to which the sharpening effect will be applied.
   * @return a new Image with the sharpening effect applied.
   */
  @Override
  public Image applySharpening(Image image) {
    float[][] arr = new float[][]{
            {-1f / 8, -1f / 8, -1f / 8, -1f / 8, -1f / 8},
            {-1f / 8, 1f / 4, 1f / 4, 1f / 4, -1f / 8},
            {-1f / 8, 1f / 4, 1f, 1f / 4, -1f / 8},
            {-1f / 8, 1f / 4, 1f / 4, 1f / 4, -1f / 8},
            {-1f / 8, -1f / 8, -1f / 8, -1f / 8, -1f / 8}
    };

    return applyFilter(image, 5, 5, arr);
  }

  private Image combineThreeImages(Image first, Image second, Image third) {
    Pixel[][] pixels = new PixelRGB[first.getHeight()][first.getWidth()];
    for (int i = 0; i < first.getHeight(); i++) {
      for (int j = 0; j < first.getWidth(); j++) {
        pixels[i][j] = new PixelRGB(first.getPixelChannel(i, j, 0),
                second.getPixelChannel(i, j, 1),
                third.getPixelChannel(i, j, 2));
      }
    }
    return new ImageImpl(pixels);
  }
}
