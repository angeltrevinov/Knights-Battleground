/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Knights.battlegrounds;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JApplet;

/**
 *ImageLoader
 * 
 * clase oara cargar images 
 * 
 * @author Angel Odiel Treviño Villanueva A01336559
 */
public class ImageLoader extends JApplet{
    /**
     * To get image from the file path
     *
     * @param path it is the path of the file
     * @return the <bold>BufferedImage</bold>object
     */
    public static BufferedImage loadImage(String path){
        BufferedImage bi = null;
        try{                                //Get the resource from the path received
            bi = ImageIO.read(ImageLoader.class.getResource(path));
        } catch (IOException ioe){          //If occurs an error the throw a warning
            System.out.print("Error loading image" + path + ioe.toString());
            System.exit(1);
        }
        return bi;
    }
}
