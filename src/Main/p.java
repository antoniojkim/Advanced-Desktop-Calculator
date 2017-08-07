/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Main;

import Math_Evaluation_Library.Miscellaneous.MathRound;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import static java.lang.Double.NaN;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
*
* @author Antonio's Laptop
*/
public class p {
    
//    public static void main (String[] args){
//        for (int a = 0; a<15; a++){
//            int r = p.randomint(1, 10000);
//            System.out.println(r+"       "+superscript(r));
//        }
//    }
    
    public static  Color defaultcolor = UIManager.getColor ( "Panel.background" );
    public final static List<String> letters = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"));
        
    //JComponents
    public static Border border(int thickness){
        Border border = new LineBorder(Color.BLACK, thickness);
        return border;
    }
    public static Font font(int size){
        Font font = new Font("Calibri", Font.PLAIN, size);
        return font;
    }
    public static Font boldFont(double size){
        Font font = new Font("Calibri", Font.BOLD, (int)size);
        return font;
    }
    public static void JMessagePane(String message, String header, int type){
        JOptionPane.showMessageDialog (null, message, header, type);
    }
    public static int showConfirmDialog(String message, String header, int mode, int type){
        return JOptionPane.showConfirmDialog(null, message, header, mode, type);
    }
    public static void drawImage(Graphics g, BufferedImage image, int x, int y, double scale){
        g.drawImage(image, x, y, (int)(image.getWidth()*scale), (int)(image.getHeight()*scale), null);
    }
    public static JTextArea JTextArea (int xcoordinate, int ycoordinate, int fontsize, boolean edittable, boolean visible){
        JTextArea area = new JTextArea();
        area.setLocation(xcoordinate, ycoordinate);
        if (!visible){
            area.setBackground(defaultcolor);
        }
        else{
            area.setBorder(new LineBorder(Color.BLACK, 1));
        }
        area.setFont(new Font("Calibri", Font.PLAIN, fontsize));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setEditable(edittable);
        area.setText("");
        return area;
    }
    public static JScrollPane JScrollPane (int xcoordinate, int ycoordinate, int width, int height){
        JScrollPane pane = new JScrollPane();
        pane.setLocation(xcoordinate, ycoordinate);
        pane.setSize(width, height);
        return pane;
    }
    
    //Delay
    public static void delay (double time){
        try{
            Thread.sleep((long)Math.floor(time), (int)((time-Math.floor(time))*1000000));
//            System.out.println("Delayed "+(long)Math.floor(time)+"Milliseconds and "+(int)((time-Math.floor(time))*100000)+" Nanoseconds");
        }catch(InterruptedException e){}
    }
    
    //Buffered Input
    public static BufferedImage loadImage(String path){
        try {
            return(ImageIO.read(new FileInputStream(path)));
        } catch (IOException e) {
        }
        return null;
    }
    public static void saveImage(BufferedImage bufImage, String path){
        try {
            File file = new File(path);
            //            if (!file.exists()){
            //                file.createNewFile();
            //            }
            ImageIO.write(bufImage, "png", file);
        } catch (IOException e) { }
    }
    public static String getString(){
        BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
        try {
            return( br.readLine());
        } catch (IOException ex) {        }
        return "";
    }
    public static int getInt(){
        BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
        try{
            return Integer.parseInt(br.readLine());
        }catch (NumberFormatException | IOException e){        }
        return -1;
    }
    
    //String functions
    public static int stringWidth(String string, Font f){
        AffineTransform affinetransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affinetransform,true,true);
        Font font = f;
        int width = (int)(font.getStringBounds(string, frc).getWidth());
        return width;
    }
    public static int stringHeight(String string, Font f){
        AffineTransform affinetransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affinetransform,true,true);
        Font font = f;
        int height = (int)(font.getStringBounds(string, frc).getHeight());
        return height;
    }
    
    //Print
    public static void print(String[] list){
        System.out.print("(");
        for (int a = 0; a<list.length; a++){
            if (a == 0){
                System.out.print(list[a]);
            }
            else{
                System.out.print(", "+list[a]);
            }
        }
        System.out.println(")");
    }
    public static void print(List<String> list){
        System.out.print("(");
        for (int a = 0; a<list.size(); a++){
            if (a == 0){
                System.out.print(list.get(a));
            }
            else{
                System.out.print(", "+list.get(a));
            }
        }
        System.out.println(")");
    }
    public static void printDouble(List<Double> list){
        System.out.print("(");
        for (int a = 0; a<list.size(); a++){
            if (a == 0){
                System.out.print(list.get(a));
            }
            else{
                System.out.print(", "+list.get(a));
            }
        }
        System.out.println(")");
    }
    public static void printInt(List<Integer> list){
        System.out.print("(");
        for (int a = 0; a<list.size(); a++){
            if (a == 0){
                System.out.print(list.get(a));
            }
            else{
                System.out.print(", "+list.get(a));
            }
        }
        System.out.println(")");
    }
    
    //Buffered Input/Output
    public static BufferedReader filereader(String path){
        try {
            return new BufferedReader (new FileReader (path));
        } catch (FileNotFoundException ex) {        }
        return null;
    }
    public static PrintWriter printwriter(String path){
        try {
            return new PrintWriter (new FileWriter (path));
        } catch (IOException ex) {        }
        return null;
    }
    
    
    //ToolKit
    private static double screenWidth = -1, screenHeight = -1, screenDiagonal = -1;
    private static Rectangle max_windowSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
    private static void getScreenWidthAndHeightAndDiagonal(){
        if (screenHeight < 0 || screenWidth < 0){
            screenHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
            screenWidth = screenHeight *16.0/9.0;
        }
        screenDiagonal = Math.sqrt(Math.pow(screenWidth, 2)+Math.pow(screenHeight, 2));
        //System.out.println(screenDiagonal+" = "+screenWidth+" x "+screenHeight);
    }
    public static double getScreenWidth(){
        if (screenWidth < 0){
            getScreenWidthAndHeightAndDiagonal();
        }
        return screenWidth;
    }
    public static double getScreenHeight(){
        if (screenHeight < 0){
            getScreenWidthAndHeightAndDiagonal();
        }
        return screenHeight;
    }
    public static double getScreenDiagonal(){
        if (screenDiagonal < 0){
            getScreenWidthAndHeightAndDiagonal();
        }
        return screenDiagonal;
    }
    public static int getTaskbarHeight(){
        return (int)(screenHeight - max_windowSize.height);
    }
    public static int getFontSizeSmall(double old){
        if (old == 0){
            return 0;
        }
        return (int)(Main.w.getWindowDiagonal()/(565.685424949238/old));
    }
    public static int getFontSize(double old){
        if (old == 0){
            return 0;
        }
        return (int)(getScreenDiagonal()/(2202.9071700822983/old));
    }
    public static int convertFontSize(double old){
        return (int)((1140.175425099138*old)/getScreenDiagonal());
    }
    public static double getImageScale (double old){
        if (old == 0){
            return 0;
        }
        return (Main.w.getWindowDiagonal()/(1140.175425099138/old));
    }
    public static int convertX(double old){
        if (old == 0){
            return 0;
        }
        return (int)(old*1200.0/getScreenWidth());
    }
    public static int convertY(double old){
        if (old == 0){
            return 0;
        }
        return (int)(old*700.0/getScreenHeight());
    }
    public static int convertScreenX(double old){
        return (int)(getScreenWidth()/(1200.0/old));
    }
    public static int convertScreenY(double old){
        return (int)(getScreenHeight()/(700.0/old));
    }
    
}
