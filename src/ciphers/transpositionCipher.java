/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ciphers;

/**
 *
 * @author Konrad Zadanie 2 na przykładzie 2a
 */
public class transpositionCipher {

    private String text;
    private int d = 5;
    private int[] key = {3, 4, 1, 5, 2};
    //private int[] key = {1, 3, 2, 4};
    private String result;

    private String[] tab = new String[d];
    
    public transpositionCipher(String text) {        
        this.text = text;
        
        for (int i = 0; i < d; i++) {
            tab[i] = "";
        }
        result = "";        
    }

    public void code() {
        for (int i = 0; i < text.length(); i++) {
            tab[i % d] += text.charAt(i);
        }       
        for (int i = 0; i <= text.length(); i++) {
            if (tab[key[i % d] - 1].length() > 0) {
                result += tab[key[i % d] - 1].charAt(0);
                tab[key[i % d] - 1] = tab[key[i % d] - 1].substring(1, tab[key[i % d] - 1].length());
            }
        }
        System.out.println(result);
    }

    public void decode() {
        for (int i = 0; i < text.length(); i++) {
            tab[key[i % d] - 1] += text.charAt(i);
        }
        for (int i = 0; i < text.length(); i++) {
            if (tab[i % d].length() > 0) {
                result += tab[i % d].charAt(0);
                tab[i % d] = tab[i % d].substring(1, tab[i % d].length());
            }
        }
        System.out.println(result);       
    }
    
    @Override
    public String toString(){
        return result;
    }

}
