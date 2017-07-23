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
public class transpositionCipherEx2b {

    private String text;
    private String key;
    private int[] keyPos;
    private String result;

    private String[] tab;

    public transpositionCipherEx2b(String key, String text) {
        this.key = key;
        this.text = text;
        
        keyPos = new int[key.length()];
        tab = new String[key.length()];
        result = "";
        Positionsweight();

        for (int i = 0; i < keyPos.length; i++) {
            tab[i] = "";
        }

        /*for (int i = 0; i < keyPos.length; i++) {
         System.out.println(tab[i]);
         //System.out.print(keyPos[i] + " ");
         }*/
    }

    private void Positionsweight() {
        char c;

        for (int temp = 1, i = 0; temp <= key.length(); i = 0) {
            while (keyPos[i] != 0) {
                i++;
            }
            keyPos[i] = temp;
            c = key.charAt(i);
            for (int count = 0; count < key.length(); count++) {
                if (c > key.charAt(count) && keyPos[count] == 0) {
                    c = key.charAt(count);
                    keyPos[i] = 0;
                    i = count;
                    keyPos[count] = temp;
                }
            }
            temp++;
        }
    }

    public void code() {
        for (int i = 0; i < text.length(); i++) {
            tab[i % key.length()] += text.charAt(i);
        }

        for (int i = 1; i <= keyPos.length; i++) {
            for (int j = 1;; j++) {
                if (keyPos[j - 1] == i) {
                    result += tab[j - 1];
                    break;
                }
            }
        }

        System.out.println(result);
    }

    public void decode() {
        int[] amountPos = new int[key.length()];
        for (int i = 0; i < text.length(); i++) {
            amountPos[i % key.length()]++;
        }
              
        int temp = 1;
        for (int i = 0, j = 0, numberOfSign = 0; i < tab.length; i++, j = 0) {            
            while (keyPos[j++] != temp);
            for (int sign = 0; sign < amountPos[i]; sign++) {
                tab[j - 1] += text.charAt(numberOfSign++);
            }
            temp++;            
        }
        for (int i = 0; i < text.length(); i++) {
            if (tab[i%keyPos.length].length() > 0) {
                result += tab[i%keyPos.length].charAt(0);
                tab[i%keyPos.length] = tab[i%keyPos.length].substring(1, tab[i%keyPos.length].length());
            }
        }
        
        /*for (int i = 0; i < key.length(); i++) {
            //System.out.println(amountPos[i]);
            System.out.println(tab[i]);
        }*/
        System.out.println(result);
    }
    
    @Override
    public String toString(){
        return result;
    }

}
