Explanation of Design
We used a design pattern called MVC. In our program, Model handles the processing and storing of images. The controller works with image loading from the filesystem,
saving them to the filesystem, reading user inputs, appropriately calling the model to process images and send output to the view.
  Model:
    Interfaces:
      - Pixel - An interface that represents a pixel and provides all necessary pixel operations.
      - Image - An interface that represents an image and provides all necessary image operations.
      - ImageProcessor - An interface that provides methods to operate on groups of images.
      - ImageHandler - An interface to handle image storing and delegating image manipulation. Serves as the main model class.
     Classes:
      - PixelRGB - an implementation of Pixel, that represents a pixel with three components - red, green and blue.
      - ImageImpl - an implementation of Image, the image is represented by a matrix of pixels. Does operations on an image.
      - ImageProcessorImpl - an implementation of ImageProcessor that does operations on couples of images. Is also a global class
        through which all image manipulations are called.
      - ImageHandlerImpl - an implementation of ImageHandler to handle the storing and extraction of images as well as delegating to an ImageManipulator for
        manipulating images.
      - NoSuchImageException - An Exception to that is thrown when there is no image stored with the given name.
  Controller:
    Interfaces:
      - Controller - The main controller interface to control the program flow.
      - ImageInput - An interface to input images from the filesystem.
      - Input - An interface for getting user inputs.
      - ImageSaver - An interface to save images to the filesystem.
    Classes:
      - ControllerImpl - An implementation of the Controller interface, connects all pieces of the program together and calls the necessary methods.
      - ImageUtil - An implementation of the ImageInput interface, that loads .ppm P3 files from the filesystem and returns an Image.
      - PPMImageSaver - An implementation of the ImageSaver interface, that saves images in the .ppm format to the filesystem.
      - CommandLineInput - An implementation of Input that reads commands from the command line and calls the Controller to execute them.

    Assignment 5 Changes:
        Model now doesn't leak the Image implementation, when loading an image an InputStream will be returned, previously - Image. When saving and image
      the model no longer sends the Image to the controller to save, instead an InputStream. This provides a better independence of model - controller relationship,
      as the controller doesn't rely on specific implementation of model. An Image to InputStream an vice-versa converter was added.
        The support of linear transformations was added to Pixel interface and to the Image interface. Dithering operation added to Image interface.
      ImageProcessor interface was updated to support sepia, dither, blur, sharpen, previous greyscale implementation was replaced with linear transformation.
      TransformImageHandle interface added to introduce new operations without changing the previous ImageHandler.
      TransformationController interface added to support new operations(dither, sepia, blur, sharpen) for the controller,
      TextInput class extends CommandLineInput and adds new commands input. Inputs now use InputStream as input instead of predefined System.in.
        The new features for the controller and main model class were chosen to be added to new interfaces - TransformImageHandler and TransformationController
      to make the initial controller-model pair work with the same functionality as before. The transform functionality was added to existing Image,
      Pixel interfaces and dithering to Image, as they are needed features. Transformation is universal, subsequently helps with a lot of pixel and image manipulations.
      The filter function was added to ImageProcessor and is also universal, because applies a matrix of float values to an image, allowing the change of
      any pixel value based on nearby pixels, subsequently is and important operation to be in the base interface.

      Additions:
        Model:
          Interfaces:
          - TransformImageHandler - extends the ImageHandler interface and provides the blur, sharpen, sepia, dither image manipulations.
          Classes:
          - TransformImageHandler - extends ImageHandlerImpl and implements TransformImageHandler, provides implementation for new operations.
          - ImageConverter - provides operation of Image to InputStream conversion in both directions.
          - Filters - a class that contains matrix constants with double values to perform transformation operations.
        Controller:
          Interfaces:
          - TransformationController - Extends controller interface and provides new operations: blurImage, sharpenImage, ditherImage, sepiaImage.
          Classes:
          - TransformationControllerImpl - extends ControllerImpl, implements TransformationController. Implements new controller functionality to control new model operations.
          - UniversalImageLoader - implements ImageInput interface, is capable of loading images from various formats.
          - UniversalImageSaver - implements ImageSaver interface, is capable of saving images in various formats.
          - TextInput - extends CommandLineInput and adds new commands to parseInput method, calling parent class if an old command is being read.

**Unzip all zipped folders**
Run script instructions:
To run a script file simply type the run command and provide the filepath to the script file as the command line argument.
A script file with the name example-script is already given, to execute it type:
run resources/example-script.txt
or if using the jar file:
java -jar Assignment4.jar -file example-script.txt

Run the .jar file to run the GUI based program, instructions are in the USEME.txt file.

©Example image(raiden.png) created by 小弃, https://www.pixiv.net/en/artworks/104691815.
