package controller.interfaces;

/**
 * This interface defines the filtering operations that can be performed on an Image.
 */
public interface TransformationController extends Controller {

  void createSepiaImage(String name, String saveName);

  void ditherImage(String name, String saveName);

  void blurImage(String name, String saveName);

  void sharpenImage(String name, String saveImage);
}
