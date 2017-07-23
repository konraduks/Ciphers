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
public class RailFence {

    private int levels;
    private String text;
    private String[] tab;
    private String result;

    public RailFence(int levels, String text) {
        this.levels = levels;
        this.text = text;

        tab = new String[levels];
        for (int i = 0; i < levels; i++) {
            tab[i] = "";
        }
        result = "";
    }

    public void code() {        
        calculate();
        for (int i = 0; i < levels; i++) {            
            result += tab[i];
        }
    }

    public void decode() {
        calculate();

        int textLength = text.length();

        for (int i = 0; i < levels; i++) {
            tab[i] = text.substring(0, tab[i].length());
            text = text.substring(tab[i].length(), text.length());
        }

        for (int i = 0, lev = 0, temp = 0; i < textLength; i++) {
            if (i % (levels - 1) == 0 && i != 0) {
                if (lev == 0) {
                    lev = 1;
                } else {
                    lev = 0;
                }
            }
            result += tab[temp].charAt(0);
            tab[temp] = tab[temp].substring(1, tab[temp].length());
            if (lev == 0) {
                temp++;
            } else {
                temp--;
            }
        }
    }
    
    private void calculate(){
        for (int i = 0, lev = 0, temp = 0; i < text.length(); i++) {
            if (i % (levels - 1) == 0 && i != 0) {
                if (lev == 0) {
                    lev = 1;
                } else {
                    lev = 0;
                }
            }
            if (lev == 0) {
                tab[temp++] += text.charAt(i);
            } else {
                tab[temp--] += text.charAt(i);
            }
        }
    }

    @Override
    public String toString() {
        return result;
    }

}
