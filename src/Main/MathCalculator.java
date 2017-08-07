/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Main;

import Images.Images;
import Input.KeyBoard;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
*
* @author Antonio's Laptop
*/
public class MathCalculator extends JPanel{
    
    public LinkedList<JTextField> fields = new LinkedList<>();
    public LinkedList<JTextField> areas = new LinkedList<>();
    public LinkedList<KeyBoard> keys = new LinkedList<>();
    protected List<Boolean> removehover = new ArrayList<>();
    protected List<Boolean> removepressed = new ArrayList<>();
    protected boolean areaDrag = false;
    
    public MathCalculator(){
        setLayout(null);
        //System.out.println(p.convertX(2)+", "+p.convertY(90));
        setLocation(p.convertScreenX(1), p.convertScreenY(58));
        setPreferredSize(new Dimension(Main.getPane().getWidth(), p.convertScreenY(50)));
        addField();
        addMouseListeners();
    }
    
    public void addMouseListeners(){
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent me) {
                if (fields.size() > 1){
                    int mX = me.getX(), mY = me.getY();
                    if (mX>fields.get(0).getWidth() && mX < fields.get(0).getWidth()+395){
                        for (int a = 0; a<fields.size(); a++){
                            removehover.set(a, false);
                            if (mY > 1+a*increment && mY< 36+a*increment){
                                removehover.set(a, true);
                                break;
                            }
                        }
                    }
                    else{
                        for (int a = 0; a<fields.size(); a++){
                            removehover.set(a, false);
                        }
                    }
                    repaint();
                }
            }
        });
        addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent me) {
                if (fields.size() > 1){
                    for (int a = 0; a<removepressed.size(); a++){
                        removepressed.set(a, false);
                        if (removehover.get(a) == true){
                            removepressed.set(a, true);
                        }
                    }
                    repaint();
                }
            }
            
            @Override
            public void mouseReleased(MouseEvent me) {
                if (fields.size() > 1){
                    for (int a = 0; a<removepressed.size(); a++){
                        if (removepressed.get(a) == true){
                            removeField(a);
                            a = -1;
                        }
                        else{
                            removepressed.set(a, false);
                        }
                    }
                    repaint();
                }
            }
        });
    }
    
    int fontSize = p.getFontSize(45);
    int increment = p.convertScreenY(76);
    public final void addField(){
        int index = isFocusOwner+1;
        JTextField field = new JTextField();
        field.setLocation(p.convertScreenX(3), p.convertScreenY(3)+index*increment);
        field.setFont(p.font(fontSize));
        field.setBackground(this.getBackground());
        field.setBorder(p.border(0));
        field.setText("");
        
        JTextField area = new JTextField();
        area.setLocation(p.convertScreenX(31), (increment/2 + p.convertScreenX(3))+index*increment);
        area.setFont(p.font((int)(fontSize*9.0/10.0)));
        area.setBackground(this.getBackground());
        area.setBorder(p.border(0));
        area.setEditable(false);
        area.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                field.requestFocus();
                String str = area.getText().trim();
                if (me.isControlDown()){
                    int index = str.indexOf("=");
                    if (index != -1){
                        field.setText(str.substring(index+1).trim());
                    }
                }
                else if (me.getClickCount() == 2){
                    area.setCaretPosition(0);
                }
                else if (me.getClickCount() == 3){
                    area.setCaretPosition(str.length());
                }
            }
        });
        area.setText("");
        
        KeyBoard key = new KeyBoard(field, area);
        field.addKeyListener(key);
        field.addFocusListener(new FocusAdapter(){
            @Override
            public void focusGained(FocusEvent fe){
                isFocusOwner = fields.indexOf(field);
                for (int a = 0; a<fields.size(); a++){
                    if (a != isFocusOwner){
                        fields.get(a).getCaret().setVisible(false);
                    }
                    else{
                        fields.get(a).getCaret().setVisible(true);
                    }
                }
            }
            @Override
            public void focusLost(FocusEvent fe){
                field.getCaret().setVisible(true);
            }
        });
        if (!Main.getOptions().getExpanded() || fields.size() <=1 || fields.size()*increment>getHeight()){
            setPreferredSize(new Dimension(getWidth(), fields.size()*increment));
        }
        
        if (index == fields.size()){
            fields.add(field);
            areas.add(area);
            keys.add(key);
        }
        else{
            JTextField temp_field = fields.get(index);
            fields.set(index, field);
            JTextField temp_area = areas.get(index);
            areas.set(index, area);
            KeyBoard temp_key = keys.get(index);
            keys.set(index, key);
            for (int a = index+1; a<fields.size(); a++){
                JTextField temp_field2 = fields.get(a);
                fields.set(a, temp_field);
                temp_field = temp_field2;
                JTextField temp_area2 = fields.get(a);
                areas.set(a, temp_area);
                temp_area = temp_area2;
                KeyBoard temp_key2 = keys.get(a);
                keys.set(a, temp_key);
                temp_key = temp_key2;
            }
            fields.add(temp_field);
            areas.add(temp_area);
            keys.add(temp_key);
            for (int a = 0; a<fields.size(); a++){
                fields.get(a).setLocation(p.convertScreenX(3), 5+a*increment);
                areas.get(a).setLocation(p.convertScreenX(31), (increment/2 + 5)+a*increment);
            }
        }
        add(area);
        add(field);
        
        removehover.add(false);
        removepressed.add(false);
        isFocusOwner = fields.indexOf(field);
        field.requestFocus();
        
        if (fields.size() > 1){
            if (!Main.getOptions().expanded && 140+fields.size()*increment < 865){
                //155+Main.getCalculator().getFields().size()*100 > Main.getWindow().getHeight() &&
                resizeWindow();
                Main.getWindow().adjustSize();
            }
            Main.getWindow().setSize(Main.getWindow().getWidth(), Main.getWindow().getHeight()+1);
            Main.getWindow().adjustSize();
            Main.getWindow().setSize(Main.getWindow().getWidth(), Main.getWindow().getHeight()-1);
            Main.getWindow().adjustSize();
        }
        repaint();
    }
    
    public void resizeWindow(){
        Main.getWindow().setSize(Main.getWindow().getWidth(), (int)(p.getScreenHeight()/20+p.getScreenHeight()/11.25)+(fields.size())*increment);
    }
    
    public List<JTextField> getFields(){
        return fields;
    }
    
    private int isFocusOwner = -1;
    public int getFocused(){
        return isFocusOwner;
    }
    
    public void removeField(int a){
        remove(fields.get(a));
        remove(areas.get(a));
        fields.remove(a);
        keys.remove(a);
        areas.remove(a);
        for (int b = 0; b<fields.size(); b++){
            fields.get(b).setLocation(p.convertScreenX(3), 5+b*increment);
            areas.get(b).setLocation(p.convertScreenX(31), (increment/2 + 5)+b*increment);
        }
        removehover.remove(a);
        removepressed.remove(a);
        if (!Main.getOptions().expanded){
            resizeWindow();
        }
        if (fields.size()*increment<getHeight()){
            setPreferredSize(new Dimension(getWidth(), fields.size()*increment));
        }
        Main.getWindow().setSize(Main.getWindow().getWidth(), Main.getWindow().getHeight()+1);
        Main.getWindow().adjustSize();
        Main.getWindow().setSize(Main.getWindow().getWidth(), Main.getWindow().getHeight()-1);
        Main.getWindow().adjustSize();
        if (a-1 >= 0){
            fields.get(a-1).requestFocus();
        }
    }
    
    @Override
    public void paintComponent (Graphics g){
        super.paintComponent(g);
        for (int a = 1; a<fields.size(); a++){
            g.drawLine(0, a*increment, Main.getPane().getWidth(), a*increment);
        }
        if (fields.size() > 1){
            for (int a = 0; a<fields.size(); a++){
                if (removehover.get(a) == true){
                    if (removepressed.get(a) == true){
                        g.setColor(Color.GRAY);
                    }
                    else{
                        g.setColor(Color.LIGHT_GRAY);
                    }
                    g.fillRoundRect(fields.get(a).getWidth()+5, 7+a*increment, 40, 40, 10, 10);
                }
                p.drawImage(g, Images.remove, fields.get(a).getWidth(), 1+a*increment, 0.4);
            }
        }
    }
    
    
    
}
