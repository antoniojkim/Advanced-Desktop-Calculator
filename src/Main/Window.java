/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

/**
*
* @author Antonio's Laptop
*/
public class Window extends JFrame{
    
    public int x = 0, y = 0;
    
    public Window(){
        super("Advanced Scientific Calculator by Antonio Kim");
        setLayout(new BorderLayout());
        if (Main.tabletMode){
            setSize((int)(p.getScreenWidth()/3), (int)(p.getScreenHeight()/20*3+p.getScreenHeight()/11.25));
            setMinimumSize(new Dimension(getWidth(), getHeight()));
        }
        else{
            setSize((int)(p.getScreenWidth()/3.6), (int)(p.getScreenHeight()/20*3+p.getScreenHeight()/11.25));
            setMinimumSize(new Dimension(getWidth(), getHeight()));
        }
        setLocationRelativeTo(null);
        setLocation(getX(), (int)(p.getScreenHeight()/7));
        this.x = getX();
        this.y = getY();
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addWindowStateListener((WindowEvent we) -> {
            adjustSize();
            Main.getOptions().setExpanded(true);
        });
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent ce) {
                adjustSize();
//                Main.getOptions().setExpanded(false);
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                adjustSize();
            }
            
            @Override
            public void mouseEntered(MouseEvent me) {
                adjustSize();
            }
            
            @Override
            public void mouseExited(MouseEvent me) {
                adjustSize();
            }
        });
    }
    
    Thread resize = new Thread();
    public void adjustSize(){
        if (resize.isAlive()){
            resize.interrupt();
        }
        resize = new Thread(()->{
            if (Main.getPane().getWidth() != getWidth()-p.convertScreenX(13) || !(Main.getPane().getHeight() == getHeight()-p.convertScreenY(82) ||
                    Main.getPane().getHeight() == getHeight()-p.convertScreenY(343)) || Main.getOptions().isTabletBeingSet()){
                Main.getPane().setSize(getWidth()-p.convertScreenX(13), getHeight()-p.convertScreenY(82));
                List<Thread> threads = new ArrayList<>();
                threads.add(new Thread(()->{
                }));
                for (int A = 0; A<Main.getCalculator().fields.size(); A++){
                    int a = A;
                    threads.add(new Thread(()->{
                        Main.getCalculator().fields.get(a).setSize(Main.getPane().getWidth()-p.convertScreenX(40), (int)(p.getScreenHeight()/20));
                        Main.getCalculator().areas.get(a).setSize(Main.getPane().getWidth()-(Main.getCalculator().areas.get(a).getX()+p.convertScreenX(12)), (int)(p.getScreenHeight()/20));
                    }));
                }
                threads.add(new Thread(()->{
                    Main.getOptions().setSize(getWidth(), Main.getOptions().height);
                    Main.getOptions().addButtons();
                }));
                for (int a = 0; a<threads.size(); a++){
                    threads.get(a).start();
                }
                for (int a = 0; a<threads.size(); a++){
                    try{
                        threads.get(a).join();
                    }catch(InterruptedException e){}
                }
                
//            System.out.println("adjusted");
            }
        });
        resize.start();
    }
    
    public double getWindowDiagonal(){
        return Math.sqrt(Math.pow(getWidth(), 2)+Math.pow(getHeight(), 2));
    }
    
    public void topLeftAlign(){
        setLocation(-9, 0);
        adjustSize();
    }
    public void topCenterAlign(){
        setLocation((int)(p.getScreenWidth()/2.0-getWidth()/2.0), 0);
        adjustSize();
    }
    public void topRightAlign(){
        setLocation((int)(p.getScreenWidth()-getWidth()+9), 0);
        adjustSize();
    }
    public void middleLeftAlign(){
        setLocation(-9, (int)(p.getScreenHeight()/2.0-getHeight()/2.0));
        adjustSize();
    }
    public void middleCenterAlign(){
        setLocationRelativeTo(null);
        adjustSize();
    }
    public void middleRightAlign(){
        setLocation((int)(p.getScreenWidth()-getWidth()+9), (int)(p.getScreenHeight()/2.0-getHeight()/2.0));
        adjustSize();
    }
    public void bottomLeftAlign(){
        setLocation(-9, (int)(p.getScreenHeight()-getHeight()-32));
        adjustSize();
    }
    public void bottomCenterAlign(){
        setLocation((int)(p.getScreenWidth()/2.0-getWidth()/2.0), (int)(p.getScreenHeight()-getHeight()-32));
        adjustSize();
    }
    public void bottomRightAlign(){
        setLocation((int)(p.getScreenWidth()-getWidth()+9), (int)(p.getScreenHeight()-getHeight()-32));
        adjustSize();
    }
    public void centerAlign(){
        setLocation((int)(p.getScreenWidth()/2.0-getWidth()/2.0), getY());
        adjustSize();
    }
    public void maximizeWidth(){
        setSize((int)(p.getScreenWidth()), getHeight());
        centerAlign();
        adjustSize();
    }
    public void minimizeWidth(){
        setSize(0, getHeight());
        adjustSize();
    }
    public void maximizeHeight(){
        setSize(getWidth(), (int)(p.getScreenHeight()));
        setLocation(getX(), 0);
        adjustSize();
    }
    public void minimizeHeight(){
        setSize(getWidth(), 0);
        adjustSize();
    }
    
}
