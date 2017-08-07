/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Math_Engine;

import Main.Tablet;
import Main.p;
import java.awt.Dimension;
import javax.swing.JFrame;

/**

@author Antonio
*/
public class WindowUpdated {
    
    /*
    public static void main (String[] args){
        new Tablet().open();
    }
    /*
    */
    
    private JFrame f;
    
    public WindowUpdated(double width, double height, String name){
        f = new JFrame(name);
        setSize(width, height);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void open(){
        addContent();
        f.setVisible(true);
    }
    
    private void addContent(){}
    
    public int getX(){
        return f.getX();
    }
    public int getY(){
        return f.getY();
    }
    public void setLocation(double x, double y){
        f.setLocation(scaleX(x), scaleY(y));
    }
    public void setSize(double width, double height){
        f.setSize(scaleX(width), scaleY(height));
    }
    public int getWidth(){
        return f.getWidth();
    }
    public int getHeight(){
        return f.getHeight();
    }
    public void setMinimumSize(double width, double height){
        f.setMinimumSize(new Dimension(scaleX(width), scaleY(height)));
    }
    public int getWindowDiagonal(){
        return (int)Math.sqrt(f.getWidth()*f.getWidth()+f.getHeight()*f.getHeight());
    }
    public JFrame getFrame(){
        return f;
    }
    
    public boolean isVisible(){
        return f.isVisible();
    }
    public void setDisposable(){
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public void setResizable(boolean resizable){
        f.setResizable(resizable);
    }
    public void setVisible(){
        f.setVisible(true);
    }
    public void setVisible(boolean visible){
        f.setVisible(visible);
    }
    
    public static int scaleX(double x){
        return (int)(x*p.getScreenWidth()/1920.0);
    }
    public static int scaleY(double y){
        return (int)(y*p.getScreenHeight()/1080.0);
    }
    public static int scaleFont(double fontsize){
        return (int)(fontsize*p.getScreenDiagonal()/2202.9071700822983);
    }
    public static int unscaleX(double x){
        return (int)(x*1920.0/p.getScreenWidth());
    }
    public static int unscaleY(double y){
        return (int)(y*1080.0/p.getScreenHeight());
    }
    public static int unscaleFont(double fontsize){
        return (int)(fontsize*2202.9071700822983/p.getScreenDiagonal());
    }
    
    private void adjustSize(){}
    
    public void topLeftAlign(){
        f.setLocation(-9, 0);
        adjustSize();
    }
    public void topCenterAlign(){
        f.setLocation((int)(p.getScreenWidth()/2.0-f.getWidth()/2.0), 0);
        adjustSize();
    }
    public void topRightAlign(){
        f.setLocation((int)(p.getScreenWidth()-f.getWidth()), 0);
        adjustSize();
    }
    public void middleLeftAlign(){
        f.setLocation(-9, (int)(p.getScreenHeight()/2.0-f.getHeight()/2.0));
        adjustSize();
    }
    public void middleCenterAlign(){
        f.setLocationRelativeTo(null);
        adjustSize();
    }
    public void middleRightAlign(){
        f.setLocation((int)(p.getScreenWidth()-f.getWidth()), (int)(p.getScreenHeight()/2.0-f.getHeight()/2.0));
        adjustSize();
    }
    public void bottomLeftAlign(){
        f.setLocation(-9, (int)(p.getScreenHeight()-f.getHeight()-p.getTaskbarHeight()));
        adjustSize();
    }
    public void bottomCenterAlign(){
        f.setLocation((int)(p.getScreenWidth()/2.0-f.getWidth()/2.0), (int)(p.getScreenHeight()-f.getHeight()-p.getTaskbarHeight()));
        adjustSize();
    }
    public void bottomRightAlign(){
        f.setLocation((int)(p.getScreenWidth()-f.getWidth()), (int)(p.getScreenHeight()-f.getHeight()-p.getTaskbarHeight()));
        adjustSize();
    }
    public void centerAlign(){
        f.setLocation((int)(p.getScreenWidth()/2.0-f.getWidth()/2.0), f.getY());
        adjustSize();
    }
    public void maximizeWidth(){
        f.setSize((int)(p.getScreenWidth()), f.getHeight());
        centerAlign();
        adjustSize();
    }
    public void minimizeWidth(){
        f.setSize(0, f.getHeight());
        adjustSize();
    }
    public void maximizeHeight(){
        f.setSize(f.getWidth(), (int)(p.getScreenHeight()));
        f.setLocation(f.getX(), 0);
        adjustSize();
    }
    public void minimizeHeight(){
        f.setSize(f.getWidth(), 0);
        adjustSize();
    }
    
}
