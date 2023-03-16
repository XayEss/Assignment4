Explanation of Design
We used a design pattern called MVC. In our program, Model handles the processing and storing of images. The controller works with image loading from the filesystem,
saving them to the filesystem, reading user inputs, appropriately calling the model to process images and send output to the view.
  Model:
    Interfaces:
      - Pixel: An interface that represents a pixel and provides all necessary pixel operations.
      - Image: An interface that represents an image and provides all necessary image operations.
      - ImageProcessor: An interface that provides methods to operate on groups of images.
      - ImageHandler: An interface to handle image storing and delegating image manipulation. Serves as the main module class.
     Classes:
      - PixelRGB - an implementation of Pixel, that represents a pixel with three components - red, green and blue.
      - ImageImpl - an implementation of Image, the image is represented by a matrix of pixels. Does operations on an image.
      - ImageProcessorImpl - an implementation of ImageProcessor that does operations on couoples of images. Is also a global class
        through which all image manipulations are called.
      - ImageHandlerImpl - an implementation of ImageHandler to handle the storing and extraction of images as well as delegating to an ImageManipulator for
        manipulating images.
      - NoSuchImageException - An Exception to that is thrown when there is not image stored with the given name.
  Controller:
    Interfaces:
      - Controller:
      - ImageInput:
      - Input:
      - ImageSaver:
