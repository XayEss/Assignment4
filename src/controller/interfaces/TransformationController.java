package controller.interfaces;

public interface TransformationController extends Controller{

  void createSepiaImage(String name, String saveName);

  void ditherImage(String name, String saveName);

  void blurImage(String name, String saveName);

  void sharpenImage(String name, String saveImage);
}
