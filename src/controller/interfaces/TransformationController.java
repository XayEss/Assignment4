package controller.interfaces;

/**
 * This interface defines the filtering operations that can be performed on an Image.
 */
public interface TransformationController extends Controller {

  /**
   * Creates a sepia version of the image with the given name, and saves it with the provided save name.
   *
   * @param name     the name of the image to be processed
   * @param saveName the name to save the sepia image as
   */
  void createSepiaImage(String name, String saveName);

  /**
   * Performs dithering on the image with the given name, and saves it with the provided save name.
   *
   * @param name     the name of the image to be processed
   * @param saveName the name to save the dithered image as
   */
  void ditherImage(String name, String saveName);

  /**
   * Blurs the image with the given name, and saves it with the provided save name.
   *
   * @param name     the name of the image to be processed
   * @param saveName the name to save the blurred image as
   */
  void blurImage(String name, String saveName);

  /**
   * Sharpens the image with the given name, and saves it with the provided save name.
   *
   * @param name     the name of the image to be processed
   * @param saveName the name to save the sharpened image as
   */
  void sharpenImage(String name, String saveName);
}
