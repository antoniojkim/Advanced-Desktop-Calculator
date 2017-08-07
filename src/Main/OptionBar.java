/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Main;

import Images.Images;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

/**
*
* @author Antonio's Laptop
*/
public class OptionBar extends JPanel{
    
    public boolean expanded = false, tablet = Main.tabletMode, disable = false,
            leftaligned = false, settingTablet = false, rightClicked = false;
    protected Buttons buttons = new Buttons();
    protected JPopupMenu popup = new JPopupMenu();
    
    public boolean getExpanded(){
        return expanded;
    }
    public void setExpanded(boolean set){
        expanded = set;
    }
    public boolean isTabletPressed(){
        return tablet;
    }
    public void setTablet(boolean set){
        tablet = set;
    }
    public boolean isTabletBeingSet(){
        return settingTablet;
    }
    public boolean getDisable(){
        return disable;
    }
    public void setDisable(boolean set){
        disable = set;
    }
    
    int height = (int)(p.getScreenHeight()/15);
    public OptionBar(){
        int width = Main.getWindow().getWidth();
        setPreferredSize(new Dimension(width, height));
        setLocation(0, 0);
        setupButtons();
        addButtons();
        popupSetup();
        addMouseMotionAdapter();
        addMouseAdapter();
        //addKeyAdapter();
    }
    
    Button help, tabletMode, maximize, minimize, add;
    private void setupButtons(){
        int width = Main.getWindow().getWidth();
        int x = 45;
        help = new Button(Images.help, "Help", width-p.convertScreenX(x), p.convertScreenY(11));
        tabletMode = new Button(Images.tabletmode, "Tablet Mode", width-p.convertScreenX(x+31), p.convertScreenY(11));
        maximize = new Button(Images.maximize, "Expand", width-p.convertScreenX(x+66), p.convertScreenY(13));
        minimize = new Button(Images.minimize, "Shrink", width-p.convertScreenX(x+103), p.convertScreenY(13));
        add = new Button(Images.add, "Add", p.convertScreenX(10), p.convertScreenY(11));
        double size = 1;
        help.setScale(0.45 *p.getFontSize(size));
        tabletMode.setScale(0.45 *p.getFontSize(size));
        maximize.setScale(0.39 *p.getFontSize(size));
        minimize.setScale(0.39 *p.getFontSize(size));
        add.setScale(0.4 *p.getFontSize(size));
        buttons.clear();
        buttons.add(help);
        buttons.add(tabletMode);
        buttons.add(maximize);
        buttons.add(minimize);
        buttons.add(add);
        repaint();
    }
    
    public void addButtons(){
        int width = Main.getWindow().getWidth();
        int x = 45;
        help.setX(width-p.convertScreenX(x));
        tabletMode.setX(width-p.convertScreenX(x+31));
        maximize.setX(width-p.convertScreenX(x+66));
        minimize.setX(width-p.convertScreenX(x+103));
        repaint();
    }
    
    private void popupSetup(){
        List<JMenuItem> sizeItems = new ArrayList<>();
        JMenuItem item;
        JMenu size = new JMenu("Adjust Size");
        size.setFont(p.font((int)(p.getScreenDiagonal()/4405.814340164597*70)));
        String[] sizeStr = {"Maximize Width", "Minimize Width", "Maximize Height", "Minimize Height",
            "Maximize Width and Minimize Height", "Maximize Height and Minimize Width",
            "Maximize Width and Maximize Height", "Minimize Width and Minimize Height"};
        for (int a = 0; a<sizeStr.length; a++){
            item = new JMenuItem(sizeStr[a]);
            item.setHorizontalTextPosition(JMenu.RIGHT);
            item.setFont(p.font((int)(p.getScreenDiagonal()/4405.814340164597*65)));
            sizeItems.add(item);
            size.add(item);
        }
        sizeItems.get(0).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Main.getWindow().maximizeWidth();
            }
        });
        sizeItems.get(1).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Main.getWindow().minimizeWidth();
                Main.getWindow().centerAlign();
            }
        });
        sizeItems.get(2).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Main.getWindow().maximizeHeight();
            }
        });
        sizeItems.get(3).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Main.getWindow().minimizeHeight();
            }
        });
        sizeItems.get(4).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Main.getWindow().maximizeWidth();
                Main.getWindow().minimizeHeight();
            }
        });
        sizeItems.get(5).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Main.getWindow().minimizeWidth();
                Main.getWindow().maximizeHeight();
            }
        });
        sizeItems.get(6).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Main.getWindow().maximizeWidth();
                Main.getWindow().maximizeHeight();
            }
        });
        sizeItems.get(7).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Main.getWindow().minimizeWidth();
                Main.getWindow().minimizeHeight();
            }
        });
        JMenu location = new JMenu("Adjust Location");
        location.setFont(p.font((int)(p.getScreenDiagonal()/4405.814340164597*70)));
        String[]locationStr = {"Top Left", "Top Center", "Top Right", "Middle Left", "Middle Center",
            "Middle Right", "Bottom Left", "Bottom Center", "Bottom Right"};
        List<JMenuItem> locationItems = new ArrayList<>();
        for (int a = 0; a<locationStr.length; a++){
            item = new JMenuItem(locationStr[a]);
            item.setHorizontalTextPosition(JMenu.RIGHT);
            item.setFont(p.font((int)(p.getScreenDiagonal()/4405.814340164597*65)));
            locationItems.add(item);
            location.add(item);
        }
        locationItems.get(0).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Main.getWindow().topLeftAlign();
            }
        });
        locationItems.get(1).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Main.getWindow().topCenterAlign();
            }
        });
        locationItems.get(2).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Main.getWindow().topRightAlign();
            }
        });
        locationItems.get(3).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Main.getWindow().middleLeftAlign();
            }
        });
        locationItems.get(4).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Main.getWindow().middleCenterAlign();
            }
        });
        locationItems.get(5).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Main.getWindow().middleRightAlign();
            }
        });
        locationItems.get(6).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Main.getWindow().bottomLeftAlign();
            }
        });
        locationItems.get(7).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Main.getWindow().bottomCenterAlign();
            }
        });
        locationItems.get(8).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Main.getWindow().bottomRightAlign();
            }
        });
        popup.add(sizeItems.get(0));
        popup.add(sizeItems.get(1));
        popup.add(size);
        popup.add(locationItems.get(1));
        popup.add(locationItems.get(4));
        popup.add(locationItems.get(7));
        popup.add(location);
    }
    
    private void addMouseMotionAdapter(){
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent me) {
                int mX = me.getX(), mY = me.getY();
                Main.getWindow().adjustSize();
                for (int a = 0; a<buttons.size(); a++){
                    buttons.get(a).hover = false;
                    buttons.isHovering(buttons.get(a), mX, mY);
                }
                repaint();
            }
        });
    }
    
    private void addMouseAdapter(){
        addMouseListener(new MouseAdapter() {
            
            @Override
            public void mousePressed(MouseEvent me) {
                int mX = me.getX(), mY = me.getY();
                if (buttons.get("Add").hover){
                    Main.getCalculator().addField();
                    buttons.get("Add").pressed = true;
                }
                else if (buttons.contains("Options") && buttons.get("Options").hover){
                    buttons.get("Options").pressed = true;
                }
                else if (buttons.get("Help").hover){
                    Help help = new Help();
                    help.setVisible(true);
                    buttons.get("Help").pressed = true;
                }
                else if (buttons.get("Expand").hover){
                    Main.getWindow().setSize((int)(p.getScreenWidth()/3.5), (int)(p.getScreenHeight()*24.0/25.0));
                    Main.getWindow().adjustSize();
                    if (leftaligned){
                        Main.getWindow().topLeftAlign();
                    }
                    else{
                        Main.getWindow().topRightAlign();
                    }
                    if (expanded){
                        setExpanded(false);
                    }
                    else{
                        setExpanded(true);
                    }
                    buttons.get("Expand").pressed = true;
                }
                else if (buttons.get("Shrink").hover){
                    setExpanded(false);
                    Main.w.setSize((int)(p.getScreenWidth()/3.6), (int)(p.getScreenHeight()/20*3+p.getScreenHeight()/11.25));
                    Main.w.setMinimumSize(new Dimension(getWidth(), getHeight()));
                    Main.getWindow().adjustSize();
                    buttons.get("Shrink").pressed = true;
                }
                else if (buttons.get("Tablet Mode").hover){
                    buttons.get("Tablet Mode").pressed = true;
                    if (Main.getTablet().getFrame().isVisible()){
                        Main.getTablet().setBottomRight();
                    }
                    else{
                        Main.w.setSize(Main.getTablet().getFrame().scaleX(530), Main.w.getHeight());
                        Main.getTablet().open();
                    }
                }
                else if (me.getButton() == 3){
                    rightClicked = true;
                    popup.show(Main.getOptions(), mX, mY);
                }
                else{
                    rightClicked = false;
                    Main.getWindow().adjustSize();
                }
                Main.getWindow().adjustSize();
                repaint();
            }
            
            @Override
            public void mouseReleased(MouseEvent me) {
                buttons.setAllPressedFalse();
                repaint();
            }
        });
    }
    
    private void addKeyAdapter(){
        
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if (rightClicked){
                    int keyCode = e.getKeyCode();
                    if (keyCode == KeyEvent.VK_7) {
                        Main.getWindow().topLeftAlign();
                    }
                    else if (keyCode == KeyEvent.VK_8) {
                        Main.getWindow().topCenterAlign();
                    }
                    else if (keyCode == KeyEvent.VK_9){
                        Main.getWindow().topRightAlign();
                    }
                    else if (keyCode == KeyEvent.VK_4){
                        Main.getWindow().middleLeftAlign();
                    }
                    else if (keyCode == KeyEvent.VK_5) {
                        Main.getWindow().middleCenterAlign();
                    }
                    else if (keyCode == KeyEvent.VK_6) {
                        Main.getWindow().middleRightAlign();
                    }
                    else if (keyCode == KeyEvent.VK_1) {
                        Main.getWindow().bottomLeftAlign();
                    }
                    else if (keyCode == KeyEvent.VK_2) {
                        Main.getWindow().bottomCenterAlign();
                    }
                    else if (keyCode == KeyEvent.VK_3) {
                        Main.getWindow().bottomRightAlign();
                    }
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
    }
    
    
    protected int arcDiameter = 50, fontSize = (int)(p.getScreenDiagonal()/100);
    @Override
    public void paintComponent (Graphics g){
        super.paintComponent(g);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRoundRect(10, 10, Main.getWindow().getWidth()-50, getHeight()-20, arcDiameter, arcDiameter);
        for (int a = 0; a<buttons.size(); a++){
            if (buttons.get(a).hover || (buttons.indexOf("Expand") == a && expanded) || (buttons.indexOf("Tablet Mode") == a && tablet)){
                if (buttons.get(a).pressed || (buttons.indexOf("Expand") == a && expanded) || (buttons.indexOf("Tablet Mode") == a && tablet)){
                    g.setColor(Color.WHITE);
                }
                else{
                    g.setColor(this.getBackground());
                }
                g.fillRoundRect(buttons.get(a).x, buttons.get(a).y, buttons.get(a).buttonSizeX, buttons.get(a).buttonSizeY, 10, 10);
                g.setFont(p.font(fontSize));
                g.setColor(Color.BLACK);
                if (buttons.get(a).hover){
                    g.drawString(buttons.get(a).text, (int)(buttons.get(a).x+p.convertScreenX(13)-p.stringWidth(buttons.get(a).text, p.font(fontSize))/2.0), getHeight());
                }
            }
        }
        g.setColor(Color.BLACK);
        //        g.drawLine(20, 25, Main.getWindow().getWidth()-50, 25);
        //        g.drawLine(20, 62, Main.getWindow().getWidth()-50, 62);
        for (int a = 0; a<buttons.size(); a++){
            buttons.get(a).drawButton(g);
        }
    }
    
    private class Buttons {
        
        List<Button> list = new ArrayList<>();
        
        public void add (Button button){
            list.add(button);
        }
        
        public int size(){
            return list.size();
        }
        
        public void clear(){
            list.clear();
        }
        
        public Button get(int a){
            return list.get(a);
        }
        
        public Button get(String text){
            return list.get(indexOf(text));
        }
        
        public int indexOf(String text){
            for (int a = 0; a<list.size(); a++){
                if (list.get(a).text.equals(text)){
                    return a;
                }
            }
            return -1;
        }
        
        public boolean contains(String text){
            for (int a = 0; a<list.size(); a++){
                if (list.get(a).text.equalsIgnoreCase(text)){
                    return true;
                }
            }
            return false;
        }
        
        public Button getLast(){
            return list.get(list.size()-1);
        }
        
        public boolean isHovering(Button button, int mx, int my){
            if (mx>(button.x) && mx<(button.x)+button.buttonSizeX && my>(button.y) && my<(button.y)+button.buttonSizeY){
                button.hover = true;
                return true;
            }
            return false;
        }
        
        public void setAllPressedFalse(){
            for (int a = 0; a<list.size(); a++){
                list.get(a).pressed = false;
            }
        }
        
    }
    
    private class Button {
        
        boolean hover = false, pressed = false, rightaligned = true;
        BufferedImage image;
        String text = "";
        int x = 0, y = p.convertScreenY(25);
        double scale = 1;
        int buttonSizeX = p.convertScreenX(25), buttonSizeY = p.convertScreenY(30);
        
        public Button(BufferedImage image, String text){
            this.image = image;
            this.text = text;
        }
        public Button(BufferedImage image, String text, double x, double y){
            this.image = image;
            this.text = text;
            setX(x);
            setY(y);
            rightaligned = false;
        }
        
        public void drawButton(Graphics g){
            p.drawImage(g, image, x, y, scale);
        }
        
        public void setX(double x){
            this.x = (int)x;
        }
        public void setY(double y){
            this.y = (int)y;
        }
        
        public void setScale(double scale){
            this.scale = scale;
        }
        
        public void setButtonSize(int x, int y){
            buttonSizeX = x;
            buttonSizeY = y;
        }
    }
    
}
