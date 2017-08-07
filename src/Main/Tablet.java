/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Main;

import Math_Engine.WindowUpdated;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**

@author Antonio
*/
public class Tablet extends JPanel{
    /*
    public static void main(String[] args){
    new Tablet().open();
    }
    /*
    */
    
    private WindowUpdated w;
    private int num_pad_mode = 0, num_pad_2nd_mode = 1, trig_mode = 2, log_mode = 3, other_mode = 4, other_mode2 = 5, other_mode3 = 6;
    private int mode = 0;
    private boolean bottom_right = false;
    
    public Tablet(){
        w = new WindowUpdated(520, 530+w.unscaleY(y), "Calculator Keypad by Antonio Kim");
        //        setBottomRight();
        w.setDisposable();
        w.setResizable(false);
        Mouse mouse = new Mouse();
        this.addMouseListener(mouse);
        this.addMouseMotionListener(mouse);
        w.getFrame().add(this);
        max_height = w.getHeight()-w.scaleY(15);
    }
    
    public void open(){
        mode = 0;
        bottom_right = true;
        setBottomRight();
        w.setVisible();
    }
    
    public WindowUpdated getFrame(){
        return w;
    }
    public void setBottomRight(){
        if (bottom_right){
            w.getFrame().setLocationRelativeTo(null);
            w.setLocation(w.unscaleX(w.getX()), w.unscaleY(Main.getWindow().getY()+Main.getWindow().getHeight()));
            bottom_right = false;
        }
        else{
            w.bottomRightAlign();
            bottom_right = true;
        }
    }
    
    private void press(String button){
        
    }
    private void releaseAll(){
        for (int a = 0; a<global_button_pressed.length; a++){
            global_button_pressed[a] = false;
        }
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        if (mode == num_pad_mode){         //Numpad Mode
            drawNumPad(g2);
        }
        else if (mode == num_pad_2nd_mode){     //Numpad 2nd Mode
            drawNumPad2nd(g2);
        }
        else if (mode == trig_mode){           //Trig Mode
            drawTrigPad(g2);
        }
        else if (mode == log_mode){         //Logarithm Mode
            drawLogarithmPad(g2);
        }
        else if (mode == other_mode){         //Other Functions Mode
            drawOtherPad1(g2);
        }
        else if (mode == other_mode2){       //Other Functions 2 Mode
            drawOtherPad2(g2);
        }
        else if (mode == other_mode3){         //Other Functions 3 Mode
            drawOtherPad3(g2);
        }
        drawGlobalGrid(g2);
    }
    
    private int x = w.scaleX(150), y = w.scaleY(80), max_height;
    private int[] global_mode = {num_pad_mode, trig_mode, log_mode, other_mode};
    private String[] global_button_strings = {"Num", "Trig", "Log", "Other"};
    private boolean[] global_button_hover = {false, false, false, false};
    private boolean[] global_button_pressed = {false, false, false, false};
    private void drawGlobalGrid(Graphics2D g){
        int button_height = (max_height-y)/4;
        g.setStroke(new BasicStroke(w.scaleFont(3)));
        g.setFont(new Font("Calibri", Font.PLAIN, w.scaleFont(45)));
        for (int a = 1; a<5; a++){
            if (global_button_hover[a-1] == true || mode == global_mode[a-1]){
                if (global_button_pressed[a-1] == true){
                    g.setColor(Color.GRAY);
                }
                else if (mode == global_mode[a-1]){
                    g.setColor(Color.LIGHT_GRAY);
                }
                else{
                    g.setColor(Color.WHITE);
                }
                g.fillRect(0, y+button_height*(a-1), x, button_height);
                g.setColor(Color.BLACK);
            }
        }
        for (int a = 1; a<5; a++){
            g.drawLine(0, y+button_height*a, x, y+button_height*a);
            if (a == 4 && mode == other_mode){
                g.drawString("(1/3)", x/2-p.stringWidth("(1/3)", g.getFont())/2,
                        y+button_height*a-button_height/2+p.stringHeight("(1/3)", g.getFont())/4);
            }
            else if (a == 4 && mode == other_mode2){
                g.drawString("(2/3)", x/2-p.stringWidth("(2/3)", g.getFont())/2,
                        y+button_height*a-button_height/2+p.stringHeight("(2/3)", g.getFont())/4);
            }
            else if (a == 4 && mode == other_mode3){
                g.drawString("(3/3)", x/2-p.stringWidth("(3/3)", g.getFont())/2,
                        y+button_height*a-button_height/2+p.stringHeight("(3/3)", g.getFont())/4);
            }
            else{
                g.drawString(global_button_strings[a-1], x/2-p.stringWidth(global_button_strings[a-1], g.getFont())/2,
                        y+button_height*a-button_height/2+p.stringHeight(global_button_strings[a-1], g.getFont())/4);
            }
        }
        g.setStroke(new BasicStroke(w.scaleFont(3.75)));
        g.drawLine(x, y, x, max_height);
        g.drawLine(0, y, w.getWidth(), y);
        //        g.drawLine(x, button_height, w.getWidth(), button_height);
    }
    
    private String[][] num_pad_strings = {{"2ⁿᵈ", "/", "×", "←"}, {"7", "8", "9", ","}, {"4", "5", "6", "-"}, {"1", "2", "3", "+"}, {".", "0", "(", ")"}};
    private boolean[][] num_pad_hover = {{false, false, false, false}, {false, false, false, false}, {false, false, false, false}, {false, false, false, false}, {false, false, false, false}};
    private boolean[][] num_pad_pressed = {{false, false, false, false}, {false, false, false, false}, {false, false, false, false}, {false, false, false, false}, {false, false, false, false}};
    private void drawNumPad(Graphics2D g){
        int button_width = (w.getWidth()-x)/4, button_height = (max_height-y)/5;
        for (int a = 0; a<num_pad_hover.length; a++){
            for (int b = 0; b<num_pad_hover[b].length; b++){
                if (num_pad_hover[a][b] == true){
                    if (num_pad_pressed[a][b] == true){
                        g.setColor(Color.LIGHT_GRAY);
                    }
                    else{
                        g.setColor(Color.WHITE);
                    }
                    g.fillRect(x+button_width*b, y+button_height*a, button_width, button_height);
                    g.setColor(Color.BLACK);
                    a = num_pad_hover.length;
                    break;
                }
            }
        }
        g.setStroke(new BasicStroke(w.scaleFont(2)));
        for (int a = 1; a<4; a++){
            g.drawLine(x+button_width*a, y, x+button_width*a, max_height);
        }
        for (int a = 1; a<5; a++){
            g.drawLine(x, y+button_height*a, w.getWidth(), y+button_height*a);
        }
        g.setFont(new Font("Calibri", Font.PLAIN, w.scaleFont(40)));
        for (int a = 0; a<num_pad_strings.length; a++){
            for (int b = 0; b<num_pad_strings[a].length; b++){
                g.drawString(num_pad_strings[a][b],
                        x+button_width*(b+1)-button_width/2-p.stringWidth(num_pad_strings[a][b], g.getFont())/2,
                        y+button_height*(a+1)-button_height/2+p.stringHeight(num_pad_strings[a][b], g.getFont())/4);
            }
        }
        g.setFont(new Font("Calibri", Font.PLAIN, w.scaleFont(30)));
        g.setColor(Color.GRAY);
        for (int a = 0; a<num_pad_2nd_strings.length; a++){
            for (int b = 0; b<num_pad_2nd_strings[a].length; b++){
                if (!num_pad_2nd_strings[a][b].equals(num_pad_strings[a][b])){
                    g.drawString(num_pad_2nd_strings[a][b],
                            x+button_width*(b+1)-w.scaleX(15)-p.stringWidth(num_pad_2nd_strings[a][b], g.getFont())/4*3,
                            y+button_height*(a)+w.scaleY(15)+p.stringHeight(num_pad_2nd_strings[a][b], g.getFont())/4);
                }
            }
        }
        g.setColor(Color.BLACK);
    }
    
    private String[][] num_pad_2nd_strings = {{"2ⁿᵈ", "/", "*", "C"}, {"√", "%", "^", "="}, {"!", "nCr", "nPr", "-"}, {",", "e", "π", "+"}, {"x", "y", "[", "]"}};
    private boolean[][] num_pad_2nd_hover = {{false, false, false, false}, {false, false, false, false}, {false, false, false, false}, {false, false, false, false}, {false, false, false, false}};
    private boolean[][] num_pad_2nd_pressed = {{false, false, false, false}, {false, false, false, false}, {false, false, false, false}, {false, false, false, false}, {false, false, false, false}};
    private void drawNumPad2nd(Graphics2D g){
        int button_width = (w.getWidth()-x)/4, button_height = (max_height-y)/5;
        for (int a = 0; a<num_pad_2nd_hover.length; a++){
            for (int b = 0; b<num_pad_2nd_hover[b].length; b++){
                if (num_pad_2nd_hover[a][b] == true || (a == 0 && b == 0)){
                    if (num_pad_2nd_pressed[a][b] == true || (a == 0 && b == 0)){
                        g.setColor(Color.LIGHT_GRAY);
                    }
                    else{
                        g.setColor(Color.WHITE);
                    }
                    g.fillRect(x+button_width*b, y+button_height*a, button_width, button_height);
                    g.setColor(Color.BLACK);
                    a = num_pad_2nd_hover.length;
                    break;
                }
            }
        }
        g.setStroke(new BasicStroke(w.scaleFont(2)));
        for (int a = 1; a<4; a++){
            g.drawLine(x+button_width*a, y, x+button_width*a, max_height);
        }
        for (int a = 1; a<5; a++){
            g.drawLine(x, y+button_height*a, w.getWidth(), y+button_height*a);
        }
        g.setFont(new Font("Calibri", Font.PLAIN, w.scaleFont(40)));
        for (int a = 0; a<num_pad_2nd_strings.length; a++){
            for (int b = 0; b<num_pad_2nd_strings[a].length; b++){
                g.drawString(num_pad_2nd_strings[a][b],
                        x+button_width*(b+1)-button_width/2-p.stringWidth(num_pad_2nd_strings[a][b], g.getFont())/2,
                        y+button_height*(a+1)-button_height/2+p.stringHeight(num_pad_2nd_strings[a][b], g.getFont())/4);
            }
        }
    }
    
    private String[][] trig_pad_strings = {{"π", "deg", "rad"}, {"sin", "cos", "tan"}, {"csc", "sec", "cot"}, {"sin⁻¹", "cos⁻¹", "tan⁻¹"}, {"csc⁻¹", "sec⁻¹", "cot⁻¹"}};
    private boolean[][] trig_pad_hover = {{false, false, false}, {false, false, false}, {false, false, false}, {false, false, false}, {false, false, false}};
    private boolean[][] trig_pad_pressed = {{false, false, false}, {false, false, false}, {false, false, false}, {false, false, false}, {false, false, false}};
    private void drawTrigPad(Graphics2D g){
        int button_width = (w.getWidth()-x)/3, button_height = (max_height-y)/5;
        for (int a = 0; a<trig_pad_hover.length; a++){
            for (int b = 0; b<trig_pad_hover[b].length; b++){
                if (trig_pad_hover[a][b] == true){
                    if (trig_pad_pressed[a][b] == true){
                        g.setColor(Color.LIGHT_GRAY);
                    }
                    else{
                        g.setColor(Color.WHITE);
                    }
                    g.fillRect(x+button_width*b, y+button_height*a, button_width, button_height);
                    g.setColor(Color.BLACK);
                    a = trig_pad_hover.length;
                    break;
                }
            }
        }
        g.setStroke(new BasicStroke(w.scaleFont(2)));
        for (int a = 1; a<3; a++){
            g.drawLine(x+button_width*a, y, x+button_width*a, max_height);
        }
        for (int a = 1; a<5; a++){
            g.drawLine(x, y+button_height*a, w.getWidth(), y+button_height*a);
        }
        g.setFont(new Font("Calibri", Font.PLAIN, w.scaleFont(40)));
        for (int a = 0; a<trig_pad_strings.length; a++){
            for (int b = 0; b<trig_pad_strings[a].length; b++){
                g.drawString(trig_pad_strings[a][b],
                        x+button_width*(b+1)-button_width/2-p.stringWidth(trig_pad_strings[a][b], g.getFont())/2,
                        y+button_height*(a+1)-button_height/2+p.stringHeight(trig_pad_strings[a][b], g.getFont())/4);
            }
        }
    }
    
    private String[][] log_pad_strings = {{"e", "ln", "log₁₀"}, {"sinh", "cosh", "tanh"}, {"csch", "sech", "coth"}, {"sinh⁻¹", "cosh⁻¹", "tanh⁻¹"}, {"csch⁻¹", "sech⁻¹", "coth⁻¹"}};
    private boolean[][] log_pad_hover = {{false, false, false}, {false, false, false}, {false, false, false}, {false, false, false}, {false, false, false}};
    private boolean[][] log_pad_pressed = {{false, false, false}, {false, false, false}, {false, false, false}, {false, false, false}, {false, false, false}};
    private void drawLogarithmPad(Graphics2D g){
        int button_width = (w.getWidth()-x)/3, button_height = (max_height-y)/5;
        for (int a = 0; a<log_pad_hover.length; a++){
            for (int b = 0; b<log_pad_hover[b].length; b++){
                if (log_pad_hover[a][b] == true){
                    if (log_pad_pressed[a][b] == true){
                        g.setColor(Color.LIGHT_GRAY);
                    }
                    else{
                        g.setColor(Color.WHITE);
                    }
                    g.fillRect(x+button_width*b, y+button_height*a, button_width, button_height);
                    g.setColor(Color.BLACK);
                    a = log_pad_hover.length;
                    break;
                }
            }
        }
        g.setStroke(new BasicStroke(w.scaleFont(2)));
        for (int a = 1; a<3; a++){
            g.drawLine(x+button_width*a, y, x+button_width*a, max_height);
        }
        for (int a = 1; a<5; a++){
            g.drawLine(x, y+button_height*a, w.getWidth(), y+button_height*a);
        }
        g.setFont(new Font("Calibri", Font.PLAIN, w.scaleFont(40)));
        for (int a = 0; a<log_pad_strings.length; a++){
            for (int b = 0; b<log_pad_strings[a].length; b++){
                g.drawString(log_pad_strings[a][b],
                        x+button_width*(b+1)-button_width/2-p.stringWidth(log_pad_strings[a][b], g.getFont())/2,
                        y+button_height*(a+1)-button_height/2+p.stringHeight(log_pad_strings[a][b], g.getFont())/4);
            }
        }
    }
    
    private String[][] other_pad_strings1 = {{"ceil", "floor", "max"}, {"gcd", "lcm", "min"}, {"nint", "dx", "abs"}, {"int", "deriv", "newton"}, {"EEA", "LDE", "quad"}};
    private boolean[][] other_pad_hover1 = {{false, false, false}, {false, false, false}, {false, false, false}, {false, false, false}, {false, false, false}};
    private boolean[][] other_pad_pressed1 = {{false, false, false}, {false, false, false}, {false, false, false}, {false, false, false}, {false, false, false}};
    private void drawOtherPad1(Graphics2D g){
        int button_width = (w.getWidth()-x)/3, button_height = (max_height-y)/5;
        for (int a = 0; a<other_pad_hover1.length; a++){
            for (int b = 0; b<other_pad_hover1[b].length; b++){
                if (other_pad_hover1[a][b] == true){
                    if (other_pad_pressed1[a][b] == true){
                        g.setColor(Color.LIGHT_GRAY);
                    }
                    else{
                        g.setColor(Color.WHITE);
                    }
                    g.fillRect(x+button_width*b, y+button_height*a, button_width, button_height);
                    g.setColor(Color.BLACK);
                    a = other_pad_hover1.length;
                    break;
                }
            }
        }
        g.setStroke(new BasicStroke(w.scaleFont(2)));
        for (int a = 1; a<3; a++){
            g.drawLine(x+button_width*a, y, x+button_width*a, max_height);
        }
        for (int a = 1; a<5; a++){
            g.drawLine(x, y+button_height*a, w.getWidth(), y+button_height*a);
        }
        g.setFont(new Font("Calibri", Font.PLAIN, w.scaleFont(38)));
        for (int a = 0; a<other_pad_strings1.length; a++){
            for (int b = 0; b<other_pad_strings1[a].length; b++){
                g.drawString(other_pad_strings1[a][b],
                        x+button_width*(b+1)-button_width/2-p.stringWidth(other_pad_strings1[a][b], g.getFont())/2,
                        y+button_height*(a+1)-button_height/2+p.stringHeight(other_pad_strings1[a][b], g.getFont())/4);
            }
        }
    }
    
    private String[][] other_pad_strings2 = {{"volume", "area"}, {"RREF", "det"}, {"graph", "slopeF"}, {"sum", "product"}, {"random", "randomint"}};
    private boolean[][] other_pad_hover2 = {{false, false, false}, {false, false, false}, {false, false, false}, {false, false, false}, {false, false, false}};
    private boolean[][] other_pad_pressed2 = {{false, false, false}, {false, false, false}, {false, false, false}, {false, false, false}, {false, false, false}};
    private void drawOtherPad2(Graphics2D g){
        int button_width = (w.getWidth()-x)/2, button_height = (max_height-y)/5;
        for (int a = 0; a<other_pad_hover2.length; a++){
            for (int b = 0; b<other_pad_hover2[b].length; b++){
                if (other_pad_hover2[a][b] == true){
                    if (other_pad_pressed2[a][b] == true){
                        g.setColor(Color.LIGHT_GRAY);
                    }
                    else{
                        g.setColor(Color.WHITE);
                    }
                    g.fillRect(x+button_width*b, y+button_height*a, button_width, button_height);
                    g.setColor(Color.BLACK);
                    a = other_pad_hover2.length;
                    break;
                }
            }
        }
        g.setStroke(new BasicStroke(w.scaleFont(2)));
        for (int a = 1; a<4; a++){
            g.drawLine(x+button_width*a, y, x+button_width*a, max_height);
        }
        for (int a = 1; a<5; a++){
            g.drawLine(x, y+button_height*a, w.getWidth(), y+button_height*a);
        }
        g.setFont(new Font("Calibri", Font.PLAIN, w.scaleFont(38)));
        for (int a = 0; a<other_pad_strings2.length; a++){
            for (int b = 0; b<other_pad_strings2[a].length; b++){
                g.drawString(other_pad_strings2[a][b],
                        x+button_width*(b+1)-button_width/2-p.stringWidth(other_pad_strings2[a][b], g.getFont())/2,
                        y+button_height*(a+1)-button_height/2+p.stringHeight(other_pad_strings2[a][b], g.getFont())/4);
            }
        }
    }
    
    private String[][] other_pad_strings3 = {{"line", "tangent"}, {"dot", "cross"}, {"proj", "perp"}, {"prime", "complex"}, {"mod", "congruence"}};
    private boolean[][] other_pad_hover3 = {{false, false, false}, {false, false, false}, {false, false, false}, {false, false, false}, {false, false, false}};
    private boolean[][] other_pad_pressed3 = {{false, false, false}, {false, false, false}, {false, false, false}, {false, false, false}, {false, false, false}};
    private void drawOtherPad3(Graphics2D g){
        int button_width = (w.getWidth()-x)/2, button_height = (max_height-y)/5;
        for (int a = 0; a<other_pad_hover3.length; a++){
            for (int b = 0; b<other_pad_hover3[b].length; b++){
                if (other_pad_hover3[a][b] == true){
                    if (other_pad_pressed3[a][b] == true){
                        g.setColor(Color.LIGHT_GRAY);
                    }
                    else{
                        g.setColor(Color.WHITE);
                    }
                    g.fillRect(x+button_width*b, y+button_height*a, button_width, button_height);
                    g.setColor(Color.BLACK);
                    a = other_pad_hover3.length;
                    break;
                }
            }
        }
        g.setStroke(new BasicStroke(w.scaleFont(2)));
        for (int a = 1; a<4; a++){
            g.drawLine(x+button_width*a, y, x+button_width*a, max_height);
        }
        for (int a = 1; a<5; a++){
            g.drawLine(x, y+button_height*a, w.getWidth(), y+button_height*a);
        }
        g.setFont(new Font("Calibri", Font.PLAIN, w.scaleFont(38)));
        for (int a = 0; a<other_pad_strings3.length; a++){
            for (int b = 0; b<other_pad_strings3[a].length; b++){
                g.drawString(other_pad_strings3[a][b],
                        x+button_width*(b+1)-button_width/2-p.stringWidth(other_pad_strings3[a][b], g.getFont())/2,
                        y+button_height*(a+1)-button_height/2+p.stringHeight(other_pad_strings3[a][b], g.getFont())/4);
            }
        }
    }
    
    private class Mouse extends MouseAdapter{
        
        @Override
        public void mouseMoved(MouseEvent me){
            int mx = me.getX(), my = me.getY();
            int button_height = 0;
            button_height = (max_height-y)/4;
            for (int a = 1; a<5; a++){
                global_button_hover[a-1] = false;
                if (mx < x && my > y+button_height*(a-1) && my < y+button_height*a){
                    global_button_hover[a-1] = true;
                }
            }
            if (mode == num_pad_mode){
                int button_width = (w.getWidth()-x)/4;
                button_height = (max_height-y)/5;
                for (int a = 0; a<num_pad_hover.length; a++){
                    for (int b = 0; b<num_pad_hover[a].length; b++){
                        num_pad_hover[a][b] = false;
                        if (mx > x+button_width*b && mx < x+button_width*(b+1) && my > y+button_height*(a) && my < y+button_height*(a+1)){
                            num_pad_hover[a][b] = true;
                        }
                    }
                }
            }
            else if (mode == num_pad_2nd_mode){
                int button_width = (w.getWidth()-x)/4;
                button_height = (max_height-y)/5;
                for (int a = 0; a<num_pad_2nd_hover.length; a++){
                    for (int b = 0; b<num_pad_2nd_hover[a].length; b++){
                        num_pad_2nd_hover[a][b] = false;
                        if (mx > x+button_width*b && mx < x+button_width*(b+1) && my > y+button_height*(a) && my < y+button_height*(a+1)){
                            num_pad_2nd_hover[a][b] = true;
                        }
                    }
                }
            }
            else if (mode == trig_mode){
                int button_width = (w.getWidth()-x)/3;
                button_height = (max_height-y)/5;
                for (int a = 0; a<trig_pad_hover.length; a++){
                    for (int b = 0; b<trig_pad_hover[a].length; b++){
                        trig_pad_hover[a][b] = false;
                        if (mx > x+button_width*b && mx < x+button_width*(b+1) && my > y+button_height*(a) && my < y+button_height*(a+1)){
                            trig_pad_hover[a][b] = true;
                        }
                    }
                }
            }
            else if (mode == log_mode){
                int button_width = (w.getWidth()-x)/3;
                button_height = (max_height-y)/5;
                for (int a = 0; a<log_pad_hover.length; a++){
                    for (int b = 0; b<log_pad_hover[a].length; b++){
                        log_pad_hover[a][b] = false;
                        if (mx > x+button_width*b && mx < x+button_width*(b+1) && my > y+button_height*(a) && my < y+button_height*(a+1)){
                            log_pad_hover[a][b] = true;
                        }
                    }
                }
            }
            else if (mode == other_mode){
                int button_width = (w.getWidth()-x)/3;
                button_height = (max_height-y)/5;
                for (int a = 0; a<other_pad_hover1.length; a++){
                    for (int b = 0; b<other_pad_hover1[a].length; b++){
                        other_pad_hover1[a][b] = false;
                        if (mx > x+button_width*b && mx < x+button_width*(b+1) && my > y+button_height*(a) && my < y+button_height*(a+1)){
                            other_pad_hover1[a][b] = true;
                        }
                    }
                }
            }
            else if (mode == other_mode2){
                int button_width = (w.getWidth()-x)/2;
                button_height = (max_height-y)/5;
                for (int a = 0; a<other_pad_hover2.length; a++){
                    for (int b = 0; b<other_pad_hover2[a].length; b++){
                        other_pad_hover2[a][b] = false;
                        if (mx > x+button_width*b && mx < x+button_width*(b+1) && my > y+button_height*(a) && my < y+button_height*(a+1)){
                            other_pad_hover2[a][b] = true;
                        }
                    }
                }
            }
            else if (mode == other_mode3){
                int button_width = (w.getWidth()-x)/2;
                button_height = (max_height-y)/5;
                for (int a = 0; a<other_pad_hover3.length; a++){
                    for (int b = 0; b<other_pad_hover3[a].length; b++){
                        other_pad_hover3[a][b] = false;
                        if (mx > x+button_width*b && mx < x+button_width*(b+1) && my > y+button_height*(a) && my < y+button_height*(a+1)){
                            other_pad_hover3[a][b] = true;
                        }
                    }
                }
            }
            repaint();
        }
        
        @Override
        public void mousePressed(MouseEvent me){
            if (me.getY() <= y){
                origin = me.getPoint();
                focused = Main.getCalculator().getFocused();
                caret = Main.getCalculator().fields.get(focused).getCaretPosition();
                if (me.getClickCount() == 2){
                    double_click = true;
                    caret = Main.getCalculator().areas.get(focused).getCaretPosition();
                }
                else{
                    double_click = false;
                    caret = Main.getCalculator().fields.get(focused).getCaretPosition();
                }
            }
            else{
                for (int a = 0; a<global_button_pressed.length; a++){
                    global_button_pressed[a] = false;
                    if (global_button_hover[a] == true){
                        global_button_pressed[a] = true;
                        break;
                    }
                }
                if (mode == num_pad_mode){
                    for (int a = 0; a<num_pad_pressed.length; a++){
                        for (int b = 0; b<num_pad_pressed[a].length; b++){
                            if (num_pad_hover[a][b] == true){
                                num_pad_pressed[a][b] = true;
                                a = num_pad_pressed.length;
                                break;
                            }
                        }
                    }
                }
                else if (mode == num_pad_2nd_mode){
                    for (int a = 0; a<num_pad_2nd_pressed.length; a++){
                        for (int b = 0; b<num_pad_2nd_pressed[a].length; b++){
                            if (num_pad_2nd_hover[a][b] == true){
                                num_pad_2nd_pressed[a][b] = true;
                                a = num_pad_2nd_pressed.length;
                                break;
                            }
                        }
                    }
                }
                else if (mode == trig_mode){
                    for (int a = 0; a<trig_pad_pressed.length; a++){
                        for (int b = 0; b<trig_pad_pressed[a].length; b++){
                            if (trig_pad_hover[a][b] == true){
                                trig_pad_pressed[a][b] = true;
                                a = trig_pad_pressed.length;
                                break;
                            }
                        }
                    }
                }
                else if (mode == log_mode){
                    for (int a = 0; a<log_pad_pressed.length; a++){
                        for (int b = 0; b<log_pad_pressed[a].length; b++){
                            if (log_pad_hover[a][b] == true){
                                log_pad_pressed[a][b] = true;
                                a = log_pad_pressed.length;
                                break;
                            }
                        }
                    }
                }
                else if (mode == other_mode){
                    for (int a = 0; a<other_pad_pressed1.length; a++){
                        for (int b = 0; b<other_pad_pressed1[a].length; b++){
                            if (other_pad_hover1[a][b] == true){
                                other_pad_pressed1[a][b] = true;
                                a = other_pad_pressed1.length;
                                break;
                            }
                        }
                    }
                }
                else if (mode == other_mode2){
                    for (int a = 0; a<other_pad_pressed2.length; a++){
                        for (int b = 0; b<other_pad_pressed2[a].length; b++){
                            if (other_pad_hover2[a][b] == true){
                                other_pad_pressed2[a][b] = true;
                                a = other_pad_pressed2.length;
                                break;
                            }
                        }
                    }
                }
                else if (mode == other_mode3){
                    for (int a = 0; a<other_pad_pressed3.length; a++){
                        for (int b = 0; b<other_pad_pressed3[a].length; b++){
                            if (other_pad_hover3[a][b] == true){
                                other_pad_pressed3[a][b] = true;
                                a = other_pad_pressed3.length;
                                break;
                            }
                        }
                    }
                }
                repaint();
            }
        }
        @Override
        public void mouseReleased(MouseEvent me){
            double_click = false;
            for (int a = 0; a<global_button_pressed.length; a++){
                if (global_button_pressed[a] == true){
                    global_button_pressed[a] = false;
                    //global_button_hover[a] = false;
                    repaint();
                    if (mode == other_mode && a == global_button_pressed.length-1){
                        mode = other_mode2;
                    }
                    else if (mode == other_mode2 && a == global_button_pressed.length-1){
                        mode = other_mode3;
                    }
                    else{
                        mode = global_mode[a];
                    }
                    repaint();
                    return;
                }
            }
            if (mode == num_pad_mode){
                for (int a = 0; a<num_pad_pressed.length; a++){
                    for (int b = 0; b<num_pad_pressed[a].length; b++){
                        if (num_pad_pressed[a][b] == true){
                            num_pad_pressed[a][b] = false;
                            //num_pad_hover[a][b] = false;
                            mode = 0;
                            if (a == 0 && b == 0){
                                mode = 1;
                            }
                            else{
                                int focused = Main.getCalculator().getFocused();
                                int caret = Main.getCalculator().fields.get(focused).getCaretPosition();
                                String str = Main.getCalculator().fields.get(focused).getText();
                                //                                else if (a == 1 && b == 3){
                                //                                    if (!str.equals("")){
                                //                                        str = str.substring(0, caret-1)+str.substring(caret);
                                //                                        Main.getCalculator().fields.get(focused).setText(str);
                                //                                        Main.getCalculator().fields.get(focused).setCaretPosition(caret-1);
                                //                                        Main.getCalculator().keys.get(focused).keyAction(Main.getCalculator().fields.get(focused), Main.getCalculator().areas.get(focused));
                                //                                    }
                                //                                }
                                if (me.getButton() == 3){
                                    if (a == 0 && b == 3){
                                        Main.getCalculator().fields.get(focused).setText("");
                                        Main.getCalculator().areas.get(focused).setText("");
                                    }
                                    else{
                                        if (a == 2 && b == 1){
                                            str = str.substring(0, caret)+"C"+str.substring(caret);
                                            Main.getCalculator().fields.get(focused).setText(str);
                                            Main.getCalculator().fields.get(focused).setCaretPosition(caret+1);
                                        }
                                        else if (a == 2 && b == 2){
                                            str = str.substring(0, caret)+"P"+str.substring(caret);
                                            Main.getCalculator().fields.get(focused).setText(str);
                                            Main.getCalculator().fields.get(focused).setCaretPosition(caret+1);
                                        }
                                        else if (a == 3 && b == 0){
                                            str = str.substring(0, caret)+", "+str.substring(caret);
                                            Main.getCalculator().fields.get(focused).setText(str);
                                            Main.getCalculator().fields.get(focused).setCaretPosition(caret+2);
                                        }
                                        else{
                                            str = str.substring(0, caret)+num_pad_2nd_strings[a][b]+str.substring(caret);
                                            Main.getCalculator().fields.get(focused).setText(str);
                                            Main.getCalculator().fields.get(focused).setCaretPosition(caret+num_pad_2nd_strings[a][b].length());
                                        }
                                        Main.getCalculator().keys.get(focused).keyAction(Main.getCalculator().fields.get(focused), Main.getCalculator().areas.get(focused));
                                    }
                                }
                                else if (a == 0 && b == 3){
                                    if (!str.equals("") && caret > 0){
                                        str = str.substring(0, caret-1)+str.substring(caret);
                                        Main.getCalculator().fields.get(focused).setText(str);
                                        Main.getCalculator().fields.get(focused).setCaretPosition(caret-1);
                                        Main.getCalculator().keys.get(focused).keyAction(Main.getCalculator().fields.get(focused), Main.getCalculator().areas.get(focused));
                                    }
                                }
                                else if (a == 1 && b == 3){
                                    str = str.substring(0, caret)+", "+str.substring(caret);
                                    Main.getCalculator().fields.get(focused).setText(str);
                                    Main.getCalculator().fields.get(focused).setCaretPosition(caret+2);
                                }
                                else{
                                    str = str.substring(0, caret)+num_pad_strings[a][b]+str.substring(caret);
                                    Main.getCalculator().fields.get(focused).setText(str);
                                    Main.getCalculator().fields.get(focused).setCaretPosition(caret+num_pad_strings[a][b].length());
                                    Main.getCalculator().keys.get(focused).keyAction(Main.getCalculator().fields.get(focused), Main.getCalculator().areas.get(focused));
                                }
                            }
                            a = num_pad_pressed.length;
                            break;
                        }
                        
                    }
                }
            }
            else if (mode == num_pad_2nd_mode){
                for (int a = 0; a<num_pad_2nd_pressed.length; a++){
                    for (int b = 0; b<num_pad_2nd_pressed[a].length; b++){
                        if (num_pad_2nd_pressed[a][b] == true){
                            num_pad_2nd_pressed[a][b] = false;
                            //num_pad_2nd_hover[a][b] = false;
                            if (!(a == 0 && b == 0)){
                                int focused = Main.getCalculator().getFocused();
                                int caret = Main.getCalculator().fields.get(focused).getCaretPosition();
                                String str = Main.getCalculator().fields.get(focused).getText();
                                if (a == 0 && b == 3){
                                    if (!str.equals("")){
                                        Main.getCalculator().fields.get(focused).setText("");
                                        Main.getCalculator().areas.get(focused).setText("");
                                        Main.getCalculator().keys.get(focused).keyAction(Main.getCalculator().fields.get(focused), Main.getCalculator().areas.get(focused));
                                    }
                                }
                                else if (a == 1 && b == 3){
                                    Main.getCalculator().keys.get(focused).compute(Main.getCalculator().fields.get(focused), Main.getCalculator().areas.get(focused));
                                }
                                else if (a == 2 && b == 1){
                                    str = str.substring(0, caret)+"C"+str.substring(caret);
                                    Main.getCalculator().fields.get(focused).setText(str);
                                    Main.getCalculator().fields.get(focused).setCaretPosition(caret+1);
                                    Main.getCalculator().keys.get(focused).keyAction(Main.getCalculator().fields.get(focused), Main.getCalculator().areas.get(focused));
                                }
                                else if (a == 2 && b == 2){
                                    str = str.substring(0, caret)+"P"+str.substring(caret);
                                    Main.getCalculator().fields.get(focused).setText(str);
                                    Main.getCalculator().fields.get(focused).setCaretPosition(caret+1);
                                    Main.getCalculator().keys.get(focused).keyAction(Main.getCalculator().fields.get(focused), Main.getCalculator().areas.get(focused));
                                }
                                else if (a == 3 && b == 0){
                                    str = str.substring(0, caret)+", "+str.substring(caret);
                                    Main.getCalculator().fields.get(focused).setText(str);
                                    Main.getCalculator().fields.get(focused).setCaretPosition(caret+2);
                                    Main.getCalculator().keys.get(focused).keyAction(Main.getCalculator().fields.get(focused), Main.getCalculator().areas.get(focused));
                                }
                                else{
                                    str = str.substring(0, caret)+num_pad_2nd_strings[a][b]+str.substring(caret);
                                    Main.getCalculator().fields.get(focused).setText(str);
                                    Main.getCalculator().fields.get(focused).setCaretPosition(caret+num_pad_2nd_strings[a][b].length());
                                    Main.getCalculator().keys.get(focused).keyAction(Main.getCalculator().fields.get(focused), Main.getCalculator().areas.get(focused));
                                }
                            }
                            mode = 0;
                            a = num_pad_2nd_pressed.length;
                            break;
                        }
                    }
                }
            }
            else if (mode == trig_mode){
                for (int a = 0; a<trig_pad_pressed.length; a++){
                    for (int b = 0; b<trig_pad_pressed[a].length; b++){
                        if (trig_pad_pressed[a][b] == true){
                            trig_pad_pressed[a][b] = false;
                            //trig_pad_hover[a][b] = false;
                            int focused = Main.getCalculator().getFocused();
                            int caret = Main.getCalculator().fields.get(focused).getCaretPosition();
                            String str = Main.getCalculator().fields.get(focused).getText();
                            str = str.substring(0, caret)+trig_pad_strings[a][b]+str.substring(caret);
                            Main.getCalculator().fields.get(focused).setText(str);
                            Main.getCalculator().fields.get(focused).setCaretPosition(caret+trig_pad_strings[a][b].length());
                            Main.getCalculator().keys.get(focused).keyAction(Main.getCalculator().fields.get(focused), Main.getCalculator().areas.get(focused));
                            mode = 0;
                            a = trig_pad_pressed.length;
                            break;
                        }
                    }
                }
            }
            else if (mode == log_mode){
                for (int a = 0; a<log_pad_pressed.length; a++){
                    for (int b = 0; b<log_pad_pressed[a].length; b++){
                        if (log_pad_pressed[a][b] == true){
                            log_pad_pressed[a][b] = false;
                            //log_pad_hover[a][b] = false;
                            int focused = Main.getCalculator().getFocused();
                            int caret = Main.getCalculator().fields.get(focused).getCaretPosition();
                            String str = Main.getCalculator().fields.get(focused).getText();
                            str = str.substring(0, caret)+log_pad_strings[a][b]+str.substring(caret);
                            Main.getCalculator().fields.get(focused).setText(str);
                            Main.getCalculator().fields.get(focused).setCaretPosition(caret+log_pad_strings[a][b].length());
                            Main.getCalculator().keys.get(focused).keyAction(Main.getCalculator().fields.get(focused), Main.getCalculator().areas.get(focused));
                            
                            mode = 0;
                            a = log_pad_pressed.length;
                            break;
                        }
                    }
                }
            }
            else if (mode == other_mode){
                for (int a = 0; a<other_pad_pressed1.length; a++){
                    for (int b = 0; b<other_pad_pressed1[a].length; b++){
                        if (other_pad_pressed1[a][b] == true){
                            other_pad_pressed1[a][b] = false;
                            //other_pad_hover[a][b] = false;
                            int focused = Main.getCalculator().getFocused();
                            int caret = Main.getCalculator().fields.get(focused).getCaretPosition();
                            String str = Main.getCalculator().fields.get(focused).getText();
                            str = str.substring(0, caret)+other_pad_strings1[a][b]+str.substring(caret);
                            Main.getCalculator().fields.get(focused).setText(str);
                            Main.getCalculator().fields.get(focused).setCaretPosition(caret+other_pad_strings1[a][b].length());
                            Main.getCalculator().keys.get(focused).keyAction(Main.getCalculator().fields.get(focused), Main.getCalculator().areas.get(focused));
                            
                            mode = 0;
                            a = other_pad_pressed1.length;
                            break;
                        }
                    }
                }
            }
            else if (mode == other_mode2){
                for (int a = 0; a<other_pad_pressed2.length; a++){
                    for (int b = 0; b<other_pad_pressed2[a].length; b++){
                        if (other_pad_pressed2[a][b] == true){
                            other_pad_pressed2[a][b] = false;
                            //other_pad_hover[a][b] = false;
                            int focused = Main.getCalculator().getFocused();
                            int caret = Main.getCalculator().fields.get(focused).getCaretPosition();
                            String str = Main.getCalculator().fields.get(focused).getText();
                            str = str.substring(0, caret)+other_pad_strings2[a][b]+str.substring(caret);
                            Main.getCalculator().fields.get(focused).setText(str);
                            Main.getCalculator().fields.get(focused).setCaretPosition(caret+other_pad_strings2[a][b].length());
                            Main.getCalculator().keys.get(focused).keyAction(Main.getCalculator().fields.get(focused), Main.getCalculator().areas.get(focused));
                            
                            mode = 0;
                            a = other_pad_pressed2.length;
                            break;
                        }
                    }
                }
            }
            else if (mode == other_mode3){
                for (int a = 0; a<other_pad_pressed3.length; a++){
                    for (int b = 0; b<other_pad_pressed3[a].length; b++){
                        if (other_pad_pressed3[a][b] == true){
                            other_pad_pressed3[a][b] = false;
                            //other_pad_hover[a][b] = false;
                            int focused = Main.getCalculator().getFocused();
                            int caret = Main.getCalculator().fields.get(focused).getCaretPosition();
                            String str = Main.getCalculator().fields.get(focused).getText();
                            str = str.substring(0, caret)+other_pad_strings3[a][b]+str.substring(caret);
                            Main.getCalculator().fields.get(focused).setText(str);
                            Main.getCalculator().fields.get(focused).setCaretPosition(caret+other_pad_strings3[a][b].length());
                            Main.getCalculator().keys.get(focused).keyAction(Main.getCalculator().fields.get(focused), Main.getCalculator().areas.get(focused));
                            
                            mode = 0;
                            a = other_pad_pressed3.length;
                            break;
                        }
                    }
                }
            }
            repaint();
        }
        private Point origin;
        private int caret, focused;
        private boolean double_click = false;
        @Override
        public void mouseDragged(MouseEvent me) {
            int mx = me.getX(), my = me.getY();
            if (my <= y){
                JTextField field;
                if (double_click){
                    field = Main.getCalculator().areas.get(focused);
                }
                else{
                    field = Main.getCalculator().fields.get(focused);
                }
                int deltaX = (origin.x - me.getX())/(field.getWidth()/15);
                if (deltaX != 0){
                    if (caret-deltaX < 0){
                        field.setCaretPosition(0);
                    }
                    else if (caret-deltaX > field.getText().length()){
                        field.setCaretPosition(field.getText().length());
                    }
                    else{
                        field.setCaretPosition(caret-deltaX);
                    }
                }
                else{
                    field.setCaretPosition(caret);
                }
            }
        }
    }
    
    
}
