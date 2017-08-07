/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Input;

import Main.Main;
import Main.p;
import Math_Evaluation_Library.GraphingTechnology.GraphingCalculator;
import Math_Evaluation_Library.Engine.Engine;
import Math_Evaluation_Library.Miscellaneous.MathRound;
import Math_Evaluation_Library.Miscellaneous.Simplify;
import Math_Evaluation_Library.Objects.Fraction;
import Math_Evaluation_Library.Objects.Function;
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
    String[] enter_compute = {"nint", "slopeF", "sf", "df", "dict", "thes"};
    
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
        else if (ke.isControlDown() && input.getText().trim().contains("random")){
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
            input.setText("("+string+")");
            if (keyCode == KeyEvent.VK_9){
                input.setCaretPosition(0);
            }
            else if (keyCode == KeyEvent.VK_0){
                input.setCaretPosition(input.getText().length());
            }
        }
        else if (!string.equals(previous)){
            //System.out.println(string);
            previous = string;
            keyAction(input, output);
        }
    }
    
    public static final String pi = "3.141592653589793238462643383279502884197169399375105820974944592307816406286208998628034825342117";
    public static final String e = "2.718281828459045235360287471352662497757247093699959574966967627724076630353547594571382178525";
    public static final String gr = "1.6180339887498948482045868343656381177203091798057628621354486227052604628189024497072072";
    public static final String sqrt2 = "1.41421356237309504880168872420969807856967187537694807317667973799073247846210703885038753432764";
    public static final String euler_macheroni = "0.577215664901532860606512090082402431042159335939923598805767234884867726777664670936947063291";
    
    public static String[] greekAlphaString = {"alpha", "beta", "gamma", "delta", "kappa", "tau", "theta", "rho", "phi", "psi", "sigma", "mu", "lambda", "omega", "pi", "iota"};
    public static String[] greekAlpha =       {"α",     "β",    "γ",     "δ",     "κ",     "τ",   "θ",     "ρ",   "ϕ",   "ψ",   "σ",     "μ",  "λ",      "ω",     "π",  "ι"};
    public static String[] greekCapitalAlphaString = {"Alpha", "Beta", "Gamma", "Delta", "Kappa", "Tau", "Theta", "Rho", "Phi", "Psi", "Sigma", "Mu", "Lambda", "Omega", "Pi", "Iota"};
    public static String[] greekCapitalAlpha =       {"Α",     "Β",    "Γ",     "Δ",     "Κ",     "Τ",   "Θ",     "Ρ",   "Φ",   "Ψ",   "Σ",     "Μ",  "Λ",      "Ω",     "Π",  "Ι"};
    public static final String[] reserved = {"π", "ϕ", "Σ", "Π", "γ", "Ι", "ι", "η"};
    
    public void keyAction(JTextField input, JTextField output) {
        String string = input.getText().trim();
        List<String> replaceable = new ArrayList<>(Arrays.asList("sqrt", "\\.", "\\dot", "^deg", "^o", "^O", "〖", "〗", "⁡", "_r", "^r", "sum", "product", "gr", "E_M", "->"));
        List<String> replaceWith = new ArrayList<>(Arrays.asList("√"   , "·",    "·",     "°",   "°",  "°",  "(",  ")", "",  "ʳ",  "ʳ",  "Σ",   "Π",       "ϕ",  "γ",   "→"));
        replaceable.addAll(Arrays.asList(greekAlphaString));
        replaceWith.addAll(Arrays.asList(greekAlpha));
        replaceable.addAll(Arrays.asList(greekCapitalAlphaString));
        replaceWith.addAll(Arrays.asList(greekCapitalAlpha));
        replaceable.add("ϕaph");
        replaceWith.add("graph");
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
        else if (string.trim().equals("π")){
            output.setText("≈ "+pi);
            output.setCaretPosition(0);
        }
        else if (string.trim().equals("e")){
            output.setText("≈ "+e);
            output.setCaretPosition(0);
        }
        else if (string.trim().equals("ϕ")){
            output.setText("≈ "+gr);
            output.setCaretPosition(0);
        }
        else if (string.trim().equals("√2")){
            output.setText("≈ "+sqrt2);
            output.setCaretPosition(0);
        }
        else if (string.trim().equals("γ")){
            output.setText("≈ "+euler_macheroni);
            output.setCaretPosition(0);
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
    
    private static GraphingCalculator gc;
    public boolean specialCalculate(JTextField input, JTextField output){
        String inputStr = input.getText().trim();
        String outputStr = output.getText().trim();
        if (outputStr.equals("Press Enter to Graph") || outputStr.equals("Press Enter to Plot")){
            if (Main.getCalculator().fields.size() == 1){
                Main.w.minimizeHeight();
                Main.w.setSize((int)(p.getScreenWidth()/2), Main.w.getHeight());
                Main.w.centerAlign();
                Main.w.setLocation(Main.w.getX(), Main.w.getHeight()/2);
            }
            if (gc != null && gc.isVisible()){
                gc.dispose();
            }
            try{
                String parameter = inputStr.substring(inputStr.indexOf("(")+1, inputStr.lastIndexOf(")"));
                new Thread(()->{
                    if (outputStr.equals("Press Enter to Plot")){
                        int xmax = 25, xmin = -xmax, ymax = xmax, ymin = xmin;
                        String[] parameters = parameter.split(",");
                        try{
                            xmin = Integer.parseInt(parameters[1].replaceAll(" ", ""));
                            xmax = Integer.parseInt(parameters[2].replaceAll(" ", ""));
                            ymin = Integer.parseInt(parameters[3].replaceAll(" ", ""));
                            ymax = Integer.parseInt(parameters[4].replaceAll(" ", ""));
                        }catch(NumberFormatException | ArrayIndexOutOfBoundsException e){}
                        gc = new GraphingCalculator("Graphing Calculator by Antonio Kim", parameters[0], xmin, xmax, ymin, ymax);
                        gc.Open();
                        output.setText("Ploted "+parameters[0]+" from "+xmin+" to "+xmax);
                    }
                    else {
                        gc = new GraphingCalculator("Graphing Calculator by Antonio Kim", parameter.split(","));
                        gc.Open();
                        output.setText("Graphed "+parameter);
                    }
                }).start();
                output.setText("Graphing...");
                return true;
            }catch(StringIndexOutOfBoundsException e){
                output.setText("Syntax Error");
            }
        }
        return false;
    }
    
    Thread calculating = new Thread();
    public void calculate(JTextField input, JTextField output, String original){
        String function = input.getText().trim();
        if (function.equalsIgnoreCase("clear")){
            Engine.getVariables().clear();
            Engine.getValues().clear();
            output.setText("cleared");
        }
        else if(function.toLowerCase().contains("=")){
            int index = function.indexOf("=");
            String variable = function.substring(0, index).trim();
            double value = Engine.evaluate(function.substring(index+1, function.length()));
            if (!_Number_.isNumber(value)){
                output.setText("Math Error");
            }
            else if (Engine.getVariables().contains(variable)){
                Engine.getValues().set(Engine.getVariables().indexOf(variable), value);
                output.setText("= "+MathRound.roundf(value, 16));
                for (int a = 0; a<Main.getCalculator().fields.size(); a++){
                    String text = Main.getCalculator().fields.get(a).getText().replaceAll(" ", "");
                    if (text.contains(variable) && !original.equals(text)){
                        index = text.indexOf("=");
                        if (index == -1){
                            calculate(Main.getCalculator().fields.get(a), Main.getCalculator().areas.get(a), original);
                        }
                        boolean contains = false;
                        if (!text.contains("random")){
                            for (int b = 0; b<enter_compute.length; b++){
                                if (text.contains(enter_compute[b])){
                                    contains = true;
                                    break;
                                }
                            }
                        }
                        else{
                            value = Engine.evaluate(text.substring(index+1, text.length()));
                            Engine.getValues().set(Engine.getVariables().indexOf(text.substring(0, index)), value);
                            Main.getCalculator().areas.get(a).setText("= "+MathRound.roundf(value, 16));
                        }
                    }
                }
            }
            else if ((variable.length() > 1 && Engine.orderIndex(variable) == -1) ||
                    !variable.toLowerCase().equals(variable) ||
                    (!Arrays.asList(reserved).contains(variable) &&
                    (Arrays.asList(greekAlpha).contains(variable) || Arrays.asList(greekCapitalAlpha).contains(variable)))){
                Engine.getVariables().add(variable);
                Engine.getValues().add(value);
                output.setText("= "+MathRound.roundf(value, 16));
            }
            else if (!output.getText().endsWith("*")){
                output.setText(output.getText()+"*");
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
                            String simplified = Simplify.simplify(function);
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
