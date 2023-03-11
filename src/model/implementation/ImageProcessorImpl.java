package model.implementation;

import model.interfaces.Image;
import model.interfaces.ImageProcessor;
import model.interfaces.Pixel;

public class ImageProcessorImpl implements ImageProcessor {

  @Override
  public Image getChannel(Image image, int channel) {
    return null;
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
  public Image[] getSplitChannels(Image image) {
    return new Image[]{image.getImageChannel(0), image.getImageChannel(1), image.getImageChannel(2)};
  }

  @Override
  public Image combineGreyScaleImages(Image first, Image second, Image third) {
    Pixel[][] pixels = new PixelRGB[first.getHeight()][first.getWidth()];
    for(int i = 0; i < first.getHeight(); i++){
      for (int j = 0; j < first.getWidth(); j++){
        pixels[i][j] = new PixelRGB(first.getPixelChannel(i,j, 0),
            second.getPixelChannel(i,j, 1), third.getPixelChannel(i,j, 2));
      }
    }
    return new ImageImpl(pixels);
  }
}
