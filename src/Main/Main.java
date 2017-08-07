/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Main;

import Images.Images;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
*
* @author Antonio's Laptop
*/
public class Main {
    
    public static Images images;
    protected static boolean tabletMode = false;
    public static Window w;
    protected static JScrollPane pane;
    protected static OptionBar ob;
    public static MathCalculator mc;
    public static Tablet tablet;
    
    /**
    * @param args the command line arguments
    */
    public static void main(String[] args) {
        w = new Window();
        JPanel panel = new JPanel();
        panel.setLayout(null);
        images = new Images();
        ob = new OptionBar();
        panel.add(ob);
        pane = new JScrollPane();
        pane.setLocation(2, ob.height+p.convertScreenY(3));
        //pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        pane.getVerticalScrollBar().setUnitIncrement(8);
        mc = new MathCalculator();
        pane.setViewportView(mc);
        panel.add(pane);
        w.add(panel);
        w.setVisible(true);
        w.adjustSize();
    }
    
    /**
    * Returns the Window variable
    * @return window variable
    */
    public static Window getWindow(){
        return w;
    }
    
    public static MathCalculator getCalculator(){
        return mc;
    }
    
    public static Tablet getTablet(){
        if (tablet == null){
            tablet = new Tablet();
        }
        return tablet;
    }
    
    public static JScrollPane getPane(){
        return pane;
    }
    
    public static OptionBar getOptions(){
        return ob;
    }
    
}
