import controller.implementation.ImageUtil;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import model.implementation.ImageImpl;
import model.implementation.ImageProcessorImpl;
import model.implementation.ImageToBufferedImageService;
import model.implementation.PixelRGB;
import model.interfaces.Image;
import model.interfaces.ImageProcessor;
import model.interfaces.Pixel;
import view.impl.VisualizeImage;

public class Main {

  public static void main(String[] args) throws IOException, InterruptedException {
    BufferedImage image = ImageIO.read(new File("resources/images/png/koala-blue-greyscale.png"));
    Pixel pixel = new PixelRGB(1, 1, 1);
    Pixel pixel2 = new PixelRGB(2, 2, 2);
    Pixel pixel3 = new PixelRGB(3, 3, 3);
    Pixel pixel4 = new PixelRGB(4, 4, 4);
    Pixel pixel5 = new PixelRGB(5, 5, 5);
    Pixel pixel6 = new PixelRGB(6, 6, 6);
    Pixel[][] pixelMap = new PixelRGB[2][3];
    pixelMap[0][0] = pixel;
    pixelMap[0][1] = pixel2;
    pixelMap[0][2] = pixel3;
    pixelMap[1][0] = pixel4;
    pixelMap[1][1] = pixel5;
    pixelMap[1][2] = pixel6;
    Image koala = new ImageImpl(ImageUtil.readPPM("resources/images/ppm/koala.ppm"));
    System.out.println("---------------");
    VisualizeImage vi = new VisualizeImage(ImageToBufferedImageService.convertToBuffered(koala));
    Image koalaRedGreyscale = koala.getImageChannel(0).getGreyscaleImage();
    Image koalaGreenGreyscale = koala.getImageChannel(1).getGreyscaleImage();
    Image koalaBlueGreyscale = koala.getImageChannel(2).getGreyscaleImage();
    //new VisualizeImage(ppm(koala.getWidth(), koala.getHeight(), 255, koala.getLumaImage().getBytes()));
    ImageProcessor imageProcessor = new ImageProcessorImpl();
    Image combined = imageProcessor.combineGreyScaleImages(koalaRedGreyscale, koalaGreenGreyscale, koalaBlueGreyscale);
    new VisualizeImage(ImageToBufferedImageService.convertToBuffered(koala.getGreyscaleImage()));
    new VisualizeImage(ImageToBufferedImageService.convertToBuffered(combined.flipImage(true).flipImage(false)));
//    new VisualizeImage(ppm(koala.getWidth(), koala.getHeight(), 255, koalaRedGreyscale.getBytes()));
//    new VisualizeImage(ppm(koala.getWidth(), koala.getHeight(), 255, koalaGreenGreyscale.getBytes()));
//    new VisualizeImage(ppm(koala.getWidth(), koala.getHeight(), 255, koalaBlueGreyscale.getBytes()));
//    new VisualizeImage(ppm(koala.getWidth(), koala.getHeight(), 255, koala.alterBrightness(50).getBytes()));
    //vi.changeImage(ImageToBufferedImageService.convertToBuffered(koala.getImageChannel(0)));
    //new VisualizeImage(ImageToBufferedImageService.convertToBuffered(koala.getLumaImage()));


  }

  static public BufferedImage ppm(int width, int height, int maxcolval, byte[] data){
    if(maxcolval<256){
      BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
      int r,g,b,k=0,pixel;
      if(maxcolval==255){                                      // don't scale
        for(int y=0;y<height;y++){
          for(int x=0;(x<width)&&((k+3)<data.length);x++){
            r=data[k++] & 0xFF;
            g=data[k++] & 0xFF;
            b=data[k++] & 0xFF;
            pixel=0xFF000000+(r<<16)+(g<<8)+b;
            image.setRGB(x,y,pixel);
          }
        }
      }
      else{
        for(int y=0;y<height;y++){
          for(int x=0;(x<width)&&((k+3)<data.length);x++){
            r=data[k++] & 0xFF;r=((r*255)+(maxcolval>>1))/maxcolval;  // scale to 0..255 range
            g=data[k++] & 0xFF;g=((g*255)+(maxcolval>>1))/maxcolval;
            b=data[k++] & 0xFF;b=((b*255)+(maxcolval>>1))/maxcolval;
            pixel=0xFF000000+(r<<16)+(g<<8)+b;
            image.setRGB(x,y,pixel);
          }
        }
      }
      return image;
    }
    else{


      BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
      int r,g,b,k=0,pixel;
      for(int y=0;y<height;y++){
        for(int x=0;(x<width)&&((k+6)<data.length);x++){
          r=(data[k++] & 0xFF)|((data[k++] & 0xFF)<<8);r=((r*255)+(maxcolval>>1))/maxcolval;  // scale to 0..255 range
          g=(data[k++] & 0xFF)|((data[k++] & 0xFF)<<8);g=((g*255)+(maxcolval>>1))/maxcolval;
          b=(data[k++] & 0xFF)|((data[k++] & 0xFF)<<8);b=((b*255)+(maxcolval>>1))/maxcolval;
          pixel=0xFF000000+(r<<16)+(g<<8)+b;
          image.setRGB(x,y,pixel);
        }
      }
      return image;
    }
  }
}