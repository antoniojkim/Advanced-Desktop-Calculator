/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Input;

import Main.Main;
import Main.p;
import Math_Evaluation_Library.Constants.GreekLetters;
import Math_Evaluation_Library.Constants.StringReplacements;
//import Math_Evaluation_Library.GraphingTechnology.GraphingCalculator;
import Math_Evaluation_Library.Engine.Engine;
import Math_Evaluation_Library.Miscellaneous.MathRound;
import Math_Evaluation_Library.Miscellaneous.Simplify;
import Math_Evaluation_Library.Objects.Fraction;
import Math_Evaluation_Library.Objects._Number_;
import java.awt.Desktop;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JTextField;

/**

@author Antonio
*/
public class KeyBoard implements KeyListener{
    
    JTextField input;
    JTextField output;
    String previous = "";
    boolean pressed = true;
    String[] enter_compute = {"slopeF", "sf", "df", "dict", "thes"};
    
    public KeyBoard(JTextField field, JTextField area){
        input = field;
        output = area;
    }
    
    @Override
    public void keyPressed(KeyEvent ke) {
        int keyCode = ke.getKeyCode();
        String text = input.getText().trim();
        if (keyCode == KeyEvent.VK_UP){
            int index = Main.getCalculator().fields.indexOf(input);
            if (index > 0){
                Main.getCalculator().fields.get(index-1).requestFocus();
            }
        }
        else if(keyCode == KeyEvent.VK_DOWN){
            int index = Main.getCalculator().fields.indexOf(input);
            if (index < Main.getCalculator().fields.size()-1){
                Main.getCalculator().fields.get(index+1).requestFocus();
            }
        }
        else if(keyCode == KeyEvent.VK_BACK_SPACE && Main.getCalculator().fields.size() > 1 && text.equals("")){
            Main.getCalculator().removeField(Main.getCalculator().fields.indexOf(input));
        }
        else if ((keyCode == KeyEvent.VK_C || keyCode == KeyEvent.VK_BACK_SPACE) && ke.isAltDown()){
            input.setText("");
        }
        else if (ke.isAltDown() && keyCode == KeyEvent.VK_D){
            if(Desktop.isDesktopSupported()){
                try {
                    Desktop.getDesktop().browse(new URI("https://www.desmos.com/calculator"));
                } catch (IOException ex) {} catch (URISyntaxException ex) {}
            }
        }
        else if (ke.isAltDown() && (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_EQUALS)){
            if(Desktop.isDesktopSupported()){
                try {
                    Desktop.getDesktop().browse(new URI("http://www.wolframalpha.com/"));
                } catch (IOException ex) {} catch (URISyntaxException ex) {}
            }
        }
        else if (ke.isAltDown() && keyCode == KeyEvent.VK_I){
            if(Desktop.isDesktopSupported()){
                try {
                    Desktop.getDesktop().browse(new URI("http://www.integral-calculator.com/"));
                } catch (IOException ex) {} catch (URISyntaxException ex) {}
            }
        }
        else if (ke.isControlDown() && input.getText().trim().contains("rand")){
            compute(input, output);
        }
        else
//            if (keyCode != KeyEvent.VK_LEFT  && keyCode != KeyEvent.VK_RIGHT){
            //            if (Main.getOptions().isTabletPressed()){
            //                Main.getTablet().keyPressed(ke.getKeyChar()+"");
            //            }
            if (keyCode == KeyEvent.VK_ENTER){
                if (output.getText().startsWith("Press Enter to ")){
                    compute(input, output);
                }
                else {
                    Main.getCalculator().addField();
                    if (!Main.getOptions().expanded){
                        if (140+Main.getCalculator().getFields().size()*100 < 865){
                            //155+Main.getCalculator().getFields().size()*100 > Main.getWindow().getHeight() &&
                            Main.mc.resizeWindow();
                        }
                    }
                    Main.getWindow().setSize(Main.getWindow().getWidth()+1, Main.getWindow().getHeight()+1);
                    Main.getWindow().adjustSize();
                    Main.getWindow().setSize(Main.getWindow().getWidth()-1, Main.getWindow().getHeight()-1);
                    Main.getWindow().adjustSize();
                }
//            }
            }
    }
    
    @Override
    public void keyReleased(KeyEvent ke) {
        int keyCode = ke.getKeyCode();
        String string = input.getText().trim();
        pressed = true;
        if (keyCode == KeyEvent.VK_ESCAPE){
            System.exit(0);
        }
        else if ((keyCode == KeyEvent.VK_LEFT  || keyCode == KeyEvent.VK_RIGHT) && ke.isControlDown()){
            if (keyCode == KeyEvent.VK_LEFT){
                input.setCaretPosition(0);
            }
            else if (keyCode == KeyEvent.VK_RIGHT){
                input.setCaretPosition(input.getText().length()-1);
            }
        }
        else if ((keyCode == KeyEvent.VK_9  || keyCode == KeyEvent.VK_0) && ke.isControlDown()){
            if (input.getSelectedText() != null){
                int start = input.getSelectionStart();
                int end = input.getSelectionEnd();
                input.setText(string.substring(0, start)+"("+string.substring(start, end)+")"+string.substring(end));
                if (keyCode == KeyEvent.VK_9){
                    input.setCaretPosition(start);
                }
                else if (keyCode == KeyEvent.VK_0){
                    input.setCaretPosition(end+2);
                }
            }
            else{
                input.setText("("+string+")");
                if (keyCode == KeyEvent.VK_9){
                    input.setCaretPosition(0);
                }
                else if (keyCode == KeyEvent.VK_0){
                    input.setCaretPosition(input.getText().length());
                }
            }
        }
        else if ((keyCode == KeyEvent.VK_OPEN_BRACKET || keyCode == KeyEvent.VK_CLOSE_BRACKET) && ke.isControlDown()){
            if (input.getSelectedText() != null){
                int start = input.getSelectionStart();
                int end = input.getSelectionEnd();
                input.setText(string.substring(0, start)+"{"+string.substring(start, end)+"}"+string.substring(end));
                if (keyCode == KeyEvent.VK_9){
                    input.setCaretPosition(start);
                }
                else if (keyCode == KeyEvent.VK_0){
                    input.setCaretPosition(end+2);
                }
            }
            else{
                input.setText("{"+string+"}");
                if (keyCode == KeyEvent.VK_9){
                    input.setCaretPosition(0);
                }
                else if (keyCode == KeyEvent.VK_0){
                    input.setCaretPosition(input.getText().length());
                }
            }
        }
        else if (!string.equals(previous)){
            //System.out.println(string);
            previous = string;
            keyAction(input, output);
        }
    }
    
//    public static String[] greekAlphaString = {"alpha", "beta", "gamma", "delta", "kappa", "tau", "theta", "rho", "phi", "psi", "sigma", "mu", "lambda", "omega", "pi", "iota"};
//    public static String[] greekAlpha =       {"α",     "β",    "γ",     "δ",     "κ",     "τ",   "θ",     "ρ",   "ϕ",   "ψ",   "σ",     "μ",  "λ",      "ω",     "π",  "ι"};
//    public static String[] greekCapitalAlphaString = {"Alpha", "Beta", "Gamma", "Delta", "Kappa", "Tau", "Theta", "Rho", "Phi", "Psi", "Sigma", "Mu", "Lambda", "Omega", "Pi", "Iota"};
//    public static String[] greekCapitalAlpha =       {"Α",     "Β",    "Γ",     "Δ",     "Κ",     "Τ",   "Θ",     "Ρ",   "Φ",   "Ψ",   "Σ",     "Μ",  "Λ",      "Ω",     "Π",  "Ι"};
    public static final String[] reserved = {"π", "ϕ", "γ", "Ι", "ι", "η"};
    
    
    public void keyAction(JTextField input, JTextField output) {
        String string = input.getText().trim();
        List<String> replaceable = new ArrayList<>();
        List<String> replaceWith = new ArrayList<>();
        for (String[] replaceStrs : StringReplacements.dynamicInputReplacement){
            replaceable.add(replaceStrs[0]);
            replaceWith.add(replaceStrs[1]);
        }
        for (String[] pair : GreekLetters.greekLetterPairs){
            replaceable.add(pair[0]);
            replaceWith.add(pair[1]);
        }
        if (string.contains("x")){
            replaceable.addAll(Arrays.asList("**"));
            replaceWith.addAll(Arrays.asList("^"));
        }
        else{
            replaceable.addAll(Arrays.asList("×*", "××", "*"));
            replaceWith.addAll(Arrays.asList("^", "^", "×"));
        }
        int caret = input.getCaretPosition();
        for (int a = 0; a<replaceable.size();){
            int index = string.indexOf(replaceable.get(a));
            if (index != -1){
                string = string.substring(0, index)+replaceWith.get(a)+string.substring(index+replaceable.get(a).length());
                caret = index+replaceWith.get(a).length();
            }
            else{
                a++;
            }
        }
        if (!string.equals(input.getText().trim())){
            input.setText(string);
            input.setCaretPosition(caret);
        }
        if (string.trim().equals("")){
            output.setText("");
        }
        else if (string.startsWith("graph")){
            output.setText("Press Enter to Graph");
        }
        else if (string.startsWith("plot") || string.startsWith("plt")){
            output.setText("Press Enter to Plot");
        }
        else{
            boolean contains = false;
            for (int a = 0; a<enter_compute.length; a++){
                if (string.startsWith(enter_compute[a]+"(")){
                    contains = true;
                    break;
                }
            }
            if (contains){
                output.setText("Press Enter to Compute");
            }
            else{
                compute(input, output);
            }
        }
//            if (Main.getOptions().isTabletPressed()){
//                Main.getTablet().clearPressed();
//            }
    }
    public void compute(JTextField input, JTextField output){
        if (input.getText().trim().equals("")){
            output.setText("");
        }
        else if (!specialCalculate(input, output)){
            calculate(input, output, input.getText().replaceAll(" ", ""));
        }
    }
    
//    private static GraphingCalculator gc;
    public boolean specialCalculate(JTextField input, JTextField output){
        String inputStr = input.getText().trim();
        String outputStr = output.getText().trim();
        if (outputStr.equals("Press Enter to Graph") || outputStr.equals("Press Enter to Plot")){
//            if (Main.getCalculator().fields.size() == 1){
//                Main.w.minimizeHeight();
//                Main.w.setSize((int)(p.getScreenWidth()/2), Main.w.getHeight());
//                Main.w.centerAlign();
//                Main.w.setLocation(Main.w.getX(), Main.w.getHeight()/2);
//            }
//            if (gc != null && gc.isVisible()){
//                gc.dispose();
//            }
//            try{
//                String parameter = inputStr.substring(inputStr.indexOf("(")+1, inputStr.lastIndexOf(")"));
//                new Thread(()->{
//                    if (outputStr.equals("Press Enter to Plot")){
//                        int xmax = 25, xmin = -xmax, ymax = xmax, ymin = xmin;
//                        String[] parameters = parameter.split(",");
//                        try{
//                            xmin = Integer.parseInt(parameters[1].replaceAll(" ", ""));
//                            xmax = Integer.parseInt(parameters[2].replaceAll(" ", ""));
//                            ymin = Integer.parseInt(parameters[3].replaceAll(" ", ""));
//                            ymax = Integer.parseInt(parameters[4].replaceAll(" ", ""));
//                        }catch(NumberFormatException | ArrayIndexOutOfBoundsException e){}
//                        gc = new GraphingCalculator("Graphing Calculator by Antonio Kim", parameters[0], xmin, xmax, ymin, ymax);
//                        gc.Open();
//                        output.setText("Ploted "+parameters[0]+" from "+xmin+" to "+xmax);
//                    }
//                    else {
//                        gc = new GraphingCalculator("Graphing Calculator by Antonio Kim", parameter.split(","));
//                        gc.Open();
//                        output.setText("Graphed "+parameter);
//                    }
//                }).start();
//                output.setText("Graphing...");
//                return true;
//            }catch(StringIndexOutOfBoundsException e){
//                output.setText("Syntax Error");
//            }
        }
        return false;
    }
    
    Thread calculating = new Thread();
    public void calculate(JTextField input, JTextField output, String original){
        String function = input.getText().trim();
        if(function.toLowerCase().contains("≔")){
            int index = function.indexOf("≔");
            String variable = function.substring(0, index).trim();
            output.setText(Engine.evaluateDF(function));
            for (int a = 0; a<Main.getCalculator().fields.size(); a++){
                String text = Main.getCalculator().fields.get(a).getText().replaceAll(" ", "");
                if ((text.contains("("+variable+")") || text.contains("${"+variable+"}") || 
                        text.contains("{"+variable+"}") || text.contains("$"+variable) || text.contains(variable+"("))
                        && !original.equals(text) && (!text.contains("≔") || !function.contains(text.substring(text.indexOf("≔"))))){
                    calculate(Main.getCalculator().fields.get(a), Main.getCalculator().areas.get(a), original);
                }
            }
        }
        else {
            if (calculating.isAlive()){
                calculating.interrupt();
            }
            output.setText("computing...");
            calculating = new Thread(() -> {
                if (function.equals(input.getText().trim())){
                    //                    System.out.println(function);
                    //                    System.out.println(Engine.fixSyntax(function));
                    //                    System.out.println(Engine.toPostfix(function));
                    String evaluated = Engine.evaluateDF(function);
                    //                    System.out.println(evaluated);
                    if (function.equals(input.getText().trim())){
                        String answer = "";
                        if (!evaluated.equals("NaN")){
                            answer = evaluated;
                        }
                        else{
                            String simplified = Simplify.simplify(function).infix();
                            if (!simplified.toLowerCase().contains("error")){
                                answer = "= "+simplified;
                            }
                            else{
                                answer = Engine.getError();
                            }
                        }
                        output.setText(answer);
                        output.setCaretPosition(0);
                    }
                }
            });
            calculating.start();
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {      }
    
    
}
