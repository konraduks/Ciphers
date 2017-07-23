/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ciphers;

/**
 *
 * @author Konrad
 */
public class inputData {
    
    public static String standardizeData(String text){        
        String temp;
        temp = text.toUpperCase();        
        temp = temp.replaceAll("\\s", "");        
        temp = temp.replaceAll("Ą", "A");        
        temp = temp.replaceAll("Ć", "C");
        temp = temp.replaceAll("Ę", "E");
        temp = temp.replaceAll("Ł", "L");
        temp = temp.replaceAll("Ó", "O");
        temp = temp.replaceAll("Ś", "S");
        temp = temp.replaceAll("Ż", "Z");
        temp = temp.replaceAll("Ź", "Z");        
        temp = temp.replaceAll("\\,", "");
        temp = temp.replaceAll("\\.", "");        
        return temp;
    }    
}
