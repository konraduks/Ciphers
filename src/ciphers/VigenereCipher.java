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
public class VigenereCipher {

    private String text;
    private String key;
    private String result;
    private char[][] cipherTable = new char[26][26];

    public VigenereCipher(String key,String text) {
        this.text = text;
        this.key = key;
        result = "";
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                cipherTable[i][j] = (char) ((i + j) % 26 + 65);
            }
        }       
    }    

    public void code() {
        for (int i = 0; i < text.length(); i++) {
            result += cipherTable[key.charAt(i % key.length()) - 65][text.charAt(i) - 65];            
        }        
    }

    public void decode() {
        for (int i = 0; i < text.length(); i++) {            
            for (int j = 0; j < 26; j++) {                
                if (cipherTable[j][key.charAt(i % key.length()) - 65] == text.charAt(i)) {                    
                    result += (char) (j + 65);                    
                    break;
                }
            }                      
        }        
    }
    
    @Override
    public String toString(){
        return result;
    }

}
