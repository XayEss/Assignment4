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

**Unzip resources folder**
Run script instructions:
To run a script file simply type the run command and provide the filepath to the script file as the command line argument.
A script file with the name example-script is already given, to execute it type:
run resources/example-script.txt

##Example image created by Alexander Seljuk, all rights reserved.
