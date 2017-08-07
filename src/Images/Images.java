/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Images;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**

 @author Antonio
 */
public class Images {
    
    public static BufferedImage tabletmode;
    public static BufferedImage help;
    public static BufferedImage maximize;
    public static BufferedImage minimize;
    public static BufferedImage add;
    public static BufferedImage remove;

    public Images() {
        try {
            tabletmode = ImageIO.read(getClass().getResourceAsStream("Tablet Mode.png"));
            help = ImageIO.read(getClass().getResourceAsStream("Help.png"));
            maximize = ImageIO.read(getClass().getResourceAsStream("Maximize.png"));
            minimize = ImageIO.read(getClass().getResourceAsStream("Minimize.png"));
            add = ImageIO.read(getClass().getResourceAsStream("Add.png"));
            remove = ImageIO.read(getClass().getResourceAsStream("Remove.png"));
        } catch (IOException ex) {        }
    }
    
}
