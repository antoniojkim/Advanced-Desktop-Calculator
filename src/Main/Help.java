/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Main;

import Math_Evaluation_Library.Engine.Engine;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

/**
 *
 * @author Antonio's Laptop
 */
public class Help extends JFrame{
    
    JTabbedPane menus = new JTabbedPane();
    String var = Engine.var;
    /*
    public static void main (String[] args){
//        System.out.println(p.stringWidth("a+b, such that a and b are both real numbers, produces", p.font(20)));
        List<String> formatted = new ArrayList<>();
        while(true){
            System.out.print("Name of Function:   ");
            String function = p.getString();
            System.out.print("Syntax:  ");
            String syntax = p.getString().replaceAll("var", "\"+Engine.var+\"").replaceAll("\"", "\\\"");
            System.out.print("Restriction:   ");
            String restriction = p.getString().replaceAll("var", "\"+Engine.var+\"").replaceAll("\"", "\\\"");
            System.out.print("Description:   ");
            String description = p.getString().replaceAll("var", "\"+Engine.var+\"").replaceAll("\"", "\\\"");
            String string =  syntax+", "+restriction+", "+description+"\\n\\n\"+";
            String[] array = string.split(" ");
            String format = "";
            String str = "";
            for (int a = 0; a<array.length; a++){
                if (p.stringWidth((str+array[a]), p.font(20)) > 450){
                    format += str+"\\n       ";
                    str = array[a]+" ";
                }
                else{
                    str += array[a]+" ";
                }
            }
            format += str;
            System.out.println("\n");
            formatted.add("\""+function+":\\n\"\n"+"+\"       "+format);
            for (int a = 0; a<formatted.size(); a++){
                System.out.println(formatted.get(a));
            }
            System.out.println("\n");
        }
    }
    /*
    */
    public Help(){
        super ("Help Menu");
        setLayout(new BorderLayout());
        setSize(550, 650);
        setMinimumSize(new Dimension(435, 235));
//        setLocationRelativeTo(Main.getWindow());
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
//        menus.add("GUI Help", gui());
        menus.add("List of Functions", functions());
        add(menus);
    }
    
    public final JPanel gui(){
        JPanel gui = new JPanel();
        gui.setLayout(null);
        JTabbedPane categories = new JTabbedPane();
        categories.setLocation(0, 0);
        categories.setSize(getWidth()-11, getHeight()-57);
        categories.add("User Interface", UI());
        categories.add("HotKeys", hotkeys());
        gui.add(categories);
        return gui;
    }
    
    public final JPanel UI(){
        JPanel ui = new JPanel();
        ui.setLayout(null);
        JScrollPane pane = p.JScrollPane(0, 0, getWidth()-15, getHeight()-67);
        JTextArea area = p.JTextArea(0, 0, 20, false, false);
        return ui;
    }
    
    public final JPanel hotkeys(){
        JPanel keys = new JPanel();
        keys.setLayout(null);
        JScrollPane pane = p.JScrollPane(0, 0, getWidth()-15, getHeight()-67);
        JTextArea area = p.JTextArea(0, 0, 20, false, false);
        return keys;
    }
    
    
    public final JPanel functions(){
        JPanel functions = new JPanel();
        functions.setLayout(null);
        JTabbedPane categories = new JTabbedPane();
        categories.setLocation(0, 0);
        categories.setSize(getWidth()-11, getHeight()-57);
        categories.add("Arithmetic", arithmetic());
        categories.add("Trigonometric Functions", trig());
        categories.add("Differential Equations", Differentials());
        categories.add("Combinatorics", Combinatorics());
        categories.add("Miscellaneous", Miscellaneous());
        functions.add(categories);
        return functions;
    }
    
    public final JPanel arithmetic(){
        JPanel a = new JPanel();
        a.setLayout(null);
        JScrollPane pane = p.JScrollPane(0, 0, getWidth()-15, getHeight()-67);
        JTextArea area = p.JTextArea(0, 0, 20, false, false);
        area.setText(
                ""+
                        "Addition:\n"
                        +"       a+b, such that a and b are both real numbers, produces \n       the sum of a and b\n\n"+
                        "Subtraction:\n"
                        +"       a-b, such that a and b are both real numbers, produces \n       the difference between a and b\n\n"+
                        "Multiplication:\n"
                        +"       a*b, such that a and b are both real numbers, produces \n       the product of a and b\n\n"+
                        "Division:\n"
                        +"       a/b, such that a and b are both real numbers and b is \n       non-zero, produces the quotient of a and b\n\n"+
                        "Modular Division:\n"
                        +"       a%b, such that a and b are both real numbers and b is \n       non-zero, produces the remainder when dividing a by \n       b\n\n"+
                        "Exponents:\n"
                        +"       a^b, such that a and b are both real numbers and a is \n       non-zero, produces a to the power of b\n\n"+
                        "Square Root:\n"
                        +"       √a/sqrt(a), such that a must be greater than or equal \n       to zero, produces the square root of a number or in \n       other words, produces the number a to the power of \n       0.5\n\n"+
                        ""
        );
        pane.setViewportView(area);
        area.setCaretPosition(0);
        a.add(pane);
        return a;
    }
    
    public final JPanel trig(){
        JPanel trig = new JPanel();
        trig.setLayout(null);
        JScrollPane pane = p.JScrollPane(0, 0, getWidth()-15, getHeight()-67);
        JTextArea area = p.JTextArea(0, 0, 20, false, false);
        area.setText(
                "**The Syntax for the following does not require the use\n       of parentheses**\n\n"+
                        "Sine:\n"
                        +"      sin("+var+"), where "+var+" is a real number, produces\n       the sine value at the numerical value of "+var+"\n\n"+
                        "Cosine:\n"
                        + "      cos("+var+"), where "+var+" is a real number, produces\n      the cosine value at the numerical value of "+var+"\n\n"+
                        "Tangent:\n"
                        + "      tan("+var+"), where "+var+" is a real number such that "+var+"\n      cannot be a multiple of π/2, produces the tangent\n      value at the numerical value of "+var+"\n\n"+
                        "Cosecant:\n"
                        + "      csc("+var+"), where "+var+" is a real number such that "+var+"\n      cannot be a multiple of π, produces the cosecant\n      value at the numerical value of "+var+"\n\n"+
                        "Secant:\n"
                        + "      sec("+var+"), where "+var+" is a real number such that "+var+"\n      cannot be an odd multiple of π, produces the\n      secant value at the numerical value of "+var+"\n\n"+
                        "Cotangent:\n"
                        + "      cot("+var+"), where "+var+" is a real number such that "+var+"\n      cannot be a multiple of π, produces the cotangent\n      value at the numerical value of "+var+"\n\n"+
                        "Arcsine:\n"
                        +"      arcsin("+var+")/asin("+var+"), where "+var+" is a real number such that\n      the absolute value of "+var+" is less than one, produces\n      the arcsine value at the numerical value of "+var+"\n\n"+
                        "Arccosine:\n"
                        +"      arccos("+var+")/acos("+var+"), where "+var+" is a real number such that\n      the absolute value of "+var+" is less than one, produces\n      the arccosine value at the numerical value of "+var+"\n\n"+
                        "Arctangent:\n"
                        + "      arctan("+var+")/atan("+var+"), where "+var+" is a real number, produces\n      the arctangent value at the numerical value of "+var+"\n\n"+
                        "Arccosecant:\n"
                        + "      arccsc("+var+")/acsc("+var+"), where "+var+" is a real number such that\n      the absolute value of "+var+" is greater than one, produces\n      the arccosecant value at the numerical value of "+var+"\n\n"+
                        "Arcsecant:\n"
                        + "      arcsec("+var+")/asec("+var+"), where "+var+" is a real number such that\n      the absolute value of "+var+" is greater than one, produces\n      the arcsecant value at the numerical value of "+var+"\n\n"+
                        "Arccotangent:\n"
                        + "      arccot("+var+")/acot("+var+"), where "+var+" is a real number, produces\n      the arccotangent value at the numerical value of "+var+"\n\n"+
                        "Hyperbolic Sine:\n"
                        +"      sinh("+var+"), where "+var+" is a real number, produces\n      the hyperbolic sine value at the numerical value of "+var+"\n\n"+
                        "Hyperbolic Cosine:\n"
                        + "      cosh("+var+"), where "+var+" is a real number, produces\n      the hyperbolic cosine value at the numerical value of "+var+"\n\n"+
                        "Hyperbolic Tangent:\n"
                        + "      tanh("+var+"), where "+var+" is a real number such that "+var+"\n      cannot be a multiple of π/2, produces the hyperbolic\n       tangent value at the numerical value of "+var+"\n\n"+
                        "Hyperbolic Cosecant:\n"
                        + "      csch("+var+"), where "+var+" is a real number such that "+var+"\n      cannot be a multiple of π, produces the hyperbolic\n       cosecant value at the numerical value of "+var+"\n\n"+
                        "Hyperbolic Secant:\n"
                        + "      sech("+var+"), where "+var+" is a real number such that "+var+"\n      cannot be an odd multiple of π, produces the\n      hyperbolic secant value at the numerical value of "+var+"\n\n"+
                        "Hyperbolic Cotangent:\n"
                        + "      coth("+var+"), where "+var+" is a real number such that "+var+"\n      cannot be a multiple of π, produces the hyperbolic\n      cotangent value at the numerical value of "+var+"\n\n"+
                        ""
        );
        pane.setViewportView(area);
        area.setCaretPosition(0);
        trig.add(pane);
        return trig;
    }
    
    public final JPanel Differentials(){
        JPanel d = new JPanel();
        d.setLayout(null);
        JScrollPane pane = p.JScrollPane(0, 0, getWidth()-15, getHeight()-67);
        JTextArea area = p.JTextArea(0, 0, 20, false, false);
        area.setText(
                ""+
                        "Numerical Derivatives:\n"
                        +"       dx(f("+var+"), "+var+")/nderiv(f("+var+"), "+var+"), where f("+var+") is the function whose\n       numerical derivative is to be found and "
                        +var+" is the numerical\n       value within the functions domain that the numerical\n       derivative is to be found at. This effectively procudes f'("+var+")\n\n"+
                        "Numerical Integration (Definite Integral):\n"
                        +"      nint(f("+var+"), a, b), where f("+var+") is the integrand, a is the lower\n      bound of integration and b is the upper bound of\n      integration. This produces the definite integral of f("+var+") from\n      a to b\n\n"+
                        "Antiderivative Evaluator:\n"
                        +"       [F(x), a, b], such that a and b are both within the \n       domain of F(x) and F'(x), evalutes the antiderivative \n       from a to b. In other words, it produces F(b)-F(a)\n\n"+
                        ""
        );
        pane.setViewportView(area);
        area.setCaretPosition(0);
        d.add(pane);
        return d;
    }
    
    public final JPanel Combinatorics(){
        JPanel c = new JPanel();
        c.setLayout(null);
        JScrollPane pane = p.JScrollPane(0, 0, getWidth()-15, getHeight()-67);
        JTextArea area = p.JTextArea(0, 0, 20, false, false);
        area.setText(
                ""+
                        "Permutations:\n"
                        +"       aPb, such that a and b are integers that are greater \n       than or equal to zero and b is less than or equal to a, \n       produces the numerical value that is a permute b or a \n       \"pick\" b\n\n"+
                        "Combinations:\n"
                        +"       aCb, such that a and b are integers that are greater \n       than or equal to zero and b is less than or equal to a, \n       produces the numerical value that is a choose b\n\n"+
                        "Factorial:\n"
                        +"       a!, such that a is an integer that is greater than or \n       equal to zero, produces the factorial of a \n       (a*(a-1)*(a-2)...3*2*1)\n\n"+
                        ""
        );
        pane.setViewportView(area);
        area.setCaretPosition(0);
        c.add(pane);
        return c;
    }
    
    public final JPanel Miscellaneous(){
        JPanel m = new JPanel();
        m.setLayout(null);
        JScrollPane pane = p.JScrollPane(0, 0, getWidth()-15, getHeight()-67);
        JTextArea area = p.JTextArea(0, 0, 20, false, false);
        area.setText(
                ""+
                        "**The Syntax for the following does not require the use\n       of parentheses**\n\n"+
                        "Function Evaluator:\n"
                        +"       [f(x), a], such that a lies within the domain of f, \n       evalutes f(x) at a. In other words, produces f(a)\n\n"+
                        "Decadic Logarithms:\n"
                        +"       log("+Engine.var+"), such that "+Engine.var+" is \n       greater than 0, produces the base 10 logarithmic value \n       of "+Engine.var+"\n\n"+
                        "Natural Logarithms:\n"
                        +"       ln("+Engine.var+"), such that "+Engine.var+" is \n       greater than 0, produces the natural logarithmic value \n       (base e) of "+Engine.var+"\n\n"+
                        "Absolute Value:\n"
                        +"       abs(x), such that "+Engine.var+" is a real value, \n       produces the absolute value of "+Engine.var+", in \n       other words, returns the distance of "+Engine.var+" \n       from 0\n\n"+
                        "Degrees to Radians:\n"
                        +"       rad("+Engine.var+"), such that "+Engine.var+" is a \n       real number, assumes that "+Engine.var+" is a value \n       in degrees and returns the radian equivalent\n\n"+
                        "Radians to Degrees:\n"
                        +"       deg("+Engine.var+"), such that "+Engine.var+" is a \n       real number, assumes that "+Engine.var+" is a value \n       in radians and returns the degree equivalent\n\n"+
                        "Floor Function:\n"
                        +"       floor("+Engine.var+"), such that "+Engine.var+" is \n       a real number, returns the closest integer that is lower \n       than "+Engine.var+"\n\n"+
                        "Ceiling Function:\n"
                        +"       ceil("+Engine.var+"), such that "+Engine.var+" is a \n       real number, returns the closest integer that is higher \n       than "+Engine.var+"\n\n"+
                        ""
        );
        pane.setViewportView(area);
        area.setCaretPosition(0);
        m.add(pane);
        return m;
    }
    
}
