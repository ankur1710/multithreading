package com.ankur.imageprocessing;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.ankur.imageprocessing.ImageProcessingHelper.reColourPixels;

/**
 * we will use ARGB encoding. A-alpha - Transparent , R- red G-green B-blue
 * each component is represented by 1byte , so we need 4bytes to store a pixel colour value, we can use int type for it.
 */


public class ImageProcessingMain {

    public static final String SOURCE_FILE = "./src/com/ankur/imageprocessing/inputFlower.jpg";
    public static final String OUTPUT_FILE = "./src/com/ankur/imageprocessing/outputFlower3.jpg";

    public static void main(String[] args) throws IOException {

        /*BufferedImage Class represents the image data such as pixels colour space and dimensions.
         * it provides methods to manipulates the image .
         * */
        BufferedImage inputImage = ImageIO.read(new File(SOURCE_FILE));
        // output image will be of same dimension of the original image and image-type = RGB
        BufferedImage outputImage = new BufferedImage(inputImage.getWidth(), inputImage.getHeight(), BufferedImage.TYPE_INT_RGB);

        long startTime = System.currentTimeMillis();

        //sequential approach
//        recolourImage(inputImage, outputImage, 0, 0, outputImage.getWidth(), outputImage.getHeight());
//
//        //singleThreadedApproach
//        singleThreadSolution(inputImage,outputImage);
//
        //multiThreaded approach with 4Threads.
        multiThreadedSolution(inputImage,outputImage,4);

        long endTime = System.currentTimeMillis();

        System.out.println("Total time taken - "+ (endTime - startTime));
        //write the newImage
        ImageIO.write(outputImage, "jpg", new File(OUTPUT_FILE));
    }

    //Doing the task multiThreaded
    public static void multiThreadedSolution(BufferedImage input, BufferedImage output, int numOfThreads) {
        List<Thread> listOfThreads = new ArrayList<>(numOfThreads);

        int width = input.getWidth();
        int height = input.getHeight()/numOfThreads; // here we are dividing the height as per number of thread.
        //suppose the image is 100*100 , then each thread will process 100*25 images in parallel and then add the values in the end.

        //creating the threads as per the width and height.
        for(int i =0; i < numOfThreads ;i++){
            final int threadHeightMultiplier = i;
            Thread thread = new Thread(() ->  {
                    int leftCorner = 0;
                    int topCorner = height * threadHeightMultiplier; //first thread - 0,0 , next - 0,25, then 0, 50 etc.
                    recolourImage(input, output, leftCorner, topCorner,width, height);
                });
            listOfThreads.add(thread);
        }

        //start the threads
        for(Thread thread : listOfThreads){
            thread.start();
        }

        //Join the Threads
        for(Thread thread : listOfThreads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    //Doing the task in a singleThread
    public static void singleThreadSolution(BufferedImage input, BufferedImage output) {
        recolourImage(input, output, 0, 0, input.getWidth(), input.getHeight());
    }


    public static void recolourImage(BufferedImage originalImage, BufferedImage resultImage, int leftCorner, int topCorner, int width, int height) {
        for (int x = leftCorner; x < leftCorner + width && x < originalImage.getWidth(); x++) {
            for (int y = topCorner; y < topCorner + height && y < originalImage.getHeight(); y++) {
                resultImage = reColourPixels(originalImage, resultImage, x, y);
            }
        }
    }


}
