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
public class CaesarCipher {

    private String text;
    private String result;
    private int k0 = 5;
    private int k1 = 7;
    private int n = 26;
    private int yn = 61;

    public CaesarCipher(String text) {
        this.text = text;
        result = "";
    }

    public void code() {
        for (int i = 0; i < text.length(); i++) {            
            result += (char) ((((text.charAt(i) - 65) * k1 + k0) % n) + 65);
        }
    }

    public void decode() {
        for (int i = 0, temp = 0; i < text.length(); i++) {           
            temp = (((text.charAt(i)-65) - k0)*15) %n;
            if (temp < 0) {
                temp += n;
            }
            result += (char) (temp + 65);            
        }
    }

    @Override
    public String toString() {
        return result;
    }
}
