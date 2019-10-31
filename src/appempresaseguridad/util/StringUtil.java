/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appempresaseguridad.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author alejrudu
 */
public class StringUtil {
    
    public static final String MESAJE_ERROR_PASS = "la contraseña no puede contener los caracteres  $,%,&,/,*,-,ñ";

    /**
     * metodo para verifiar si una cadena de caracteres cumple con una expresino
     * regular dada
     *
     * @param expression
     */
    public static boolean checkExpression(String expression, String input) {
        Pattern p = Pattern.compile(expression);
        Matcher m = p.matcher(input);
        if(m.matches()){
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * Metodo para verificar la validez de la contraseña
     * @param input
     * @return 
     */
    public static boolean checkExpressionPass(String input) {
        Pattern p = Pattern.compile("[^ñ$%&/*-]*");
        Matcher m = p.matcher(input);
        if(m.matches()){
            return true;
        }else{
            return false;
        }
    }

}
