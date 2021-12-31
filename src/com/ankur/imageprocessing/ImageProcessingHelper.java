package com.ankur.imageprocessing;

import java.awt.image.BufferedImage;

public class ImageProcessingHelper {
    // here we are using bitmask method to extract the individual colours. so 2nd byte is Red ,
    // we will get only the 2nd byte value and so on for the rest of the colours.
    //after we apply the bitmask on 0x76543210 we end up with 0x00540000, but what we need is 0x00000054
    //we need to shift all the bits in the result of the bitmask to the right., using the >> operator
    public static int getRed(int rgb) {
        return (rgb & 0x00FF0000) >> 16;
    }

    //For the green color extraction, we need to move all the bits 1 byte (8 bits) to the right
    public static int getGreen(int rgb) {
        return (rgb & 0x0000FF00) >> 8;
    }

    //For the blue color extraction, we don't need to perform any shifting since it's already the right-most byt
    public static int getBlue(int rgb) {
        return rgb & 0x000000FF;
    }


    //Combining Color Components into a Pixel
    public static int createRGBFromColours(int red, int green, int blue){
        int rgb = 0;

        rgb |= blue;             //bitwise OR in the last byte.
        rgb |= green << 8;       //bitwise OR in the 3rd byte.
        rgb |= red << 16;        //bitwise OR in the 2nd byte.
        rgb |= 0xFF000000;       //set Transparency level to completely opaque

        return rgb;
    }

    //check if the pixel is shade of grey
    public static boolean isShadeOfGrey(int red, int green, int blue){
        // we need to check if all three colours have the same colour intensity, it has equal combination of red green and blue
        return ( Math.abs(red - green) < 30 &&
                Math.abs(green - blue) < 30 &&
                Math.abs(blue - red) < 30 );

    }

    //to recolour the Pixels.
    public static BufferedImage reColourPixels(BufferedImage originalImage, BufferedImage resultImage , int x , int y){
        int rgb = originalImage.getRGB(x,y); //get info of particular pixel from original image.

        //break the pixels into individual colours.
        int red = getRed(rgb);
        int green  = getGreen(rgb);
        int blue = getBlue(rgb);

        //creation of new Pixel
        int newRed;
        int newGreen;
        int newBlue;


        if(isShadeOfGrey(red,green,blue)){
            //this to get the colour purple. red is enhanced but not more than 255 , green and blue are reduced but not less than 0
            newRed = Math.min(255, red +20);
            newGreen = Math.max(0,green - 80);
            newBlue = Math.max(0, blue - 30);
        }else{
            // we will not touch the non grey pixels.
            newRed = red;
            newGreen = green;
            newBlue = blue;
        }

        //create the new RGBPixel with the new Colours.
        int newRgb =  createRGBFromColours(newRed,newGreen,newBlue);
        //set the new Pixels to the outputImage
        return setRgb(resultImage,x,y,newRgb);
    }

    public static BufferedImage setRgb(BufferedImage outputImage, int x, int y, int rgb){
        outputImage.getRaster().setDataElements(x,y,outputImage.getColorModel().getDataElements(rgb,null));
        return outputImage;
    }
}

