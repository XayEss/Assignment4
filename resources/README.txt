Explanation of Design
We used a design pattern called MVC. In our program, Model handles the processing and storing of images. The controller works with image loading from the filesystem,
saving them to the filesystem, reading user inputs, appropriately calling the model to process images and output to output information.
  Model:
    Interfaces:
      - Pixel: An interface that represents a pixel and provides all necessary pixel operations.
      - Image: An interface that represents an image and provides all necessary image operations.
      - ImageProcessor: An interface that provides methods to operate on groups of images.
      - ImageHandler: An interface to handle image storing and delegating image manipulating.
  Controller:
    Interfaces:
      - Controller:
      - ImageInput:
      - Input:
      - ImageSaver:
