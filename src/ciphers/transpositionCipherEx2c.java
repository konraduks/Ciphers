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
public class transpositionCipherEx2c {

    private String text;
    private String key;
    private int[] keyPos;
    private int[] lengthOfLine;
    private String result;

    private String[] tab;

    public transpositionCipherEx2c(String key, String text) {
        this.key = key;
        this.text = text;

        keyPos = new int[key.length()];
        lengthOfLine = new int[key.length()];
        tab = new String[key.length()];
        result = "";
        for (int i = 0; i < keyPos.length; i++) {
            tab[i] = "";
        }
        Positionsweight();
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
            lengthOfLine[keyPos[i] - 1] = i + 1;
        }        
    }

    public void code() {
        for (int i = 0, j = 0, length = 0; i < text.length(); i++, length++) {
            if (lengthOfLine[j] == length) {
                length = 0;
                j++;
            }
            tab[j] += text.charAt(i);
        }
        for (int temp = 1, i = 0, line = 0; temp <= key.length(); i = 0, line = 0) {
            while (keyPos[i] != temp) {
                i++;
            }
            for (; line < keyPos.length; line++) {
                if (tab[line].length() > i) {
                    result += tab[line].charAt(i);
                }
            }
            temp++;
        }        
    }

    public void decode() {
        int[][] temp_tab = new int[key.length()][key.length()];
        char[][] temp_char;
        for (int i = 0, row = 0, signsofLine = 0; i < text.length(); i++, signsofLine++) {
            if (lengthOfLine[row] == signsofLine) {
                signsofLine = 0;
                row++;
            }            
            temp_tab[row][signsofLine] = 1;

        }

        int temp_height = keyPos.length;
        outerloop:
        for (int i = keyPos.length - 1; i >= 0; i--) {
            for (int j = keyPos.length - 1; j >= 0; j--) {
                if (temp_tab[i][j] == 1) {
                    break outerloop;
                }
            }
            temp_height--;
        }

        temp_char = new char[temp_height][key.length()];

        for (int i = 0, sign = 0, j = 0; i < keyPos.length; i++, j = 0) {
            while (keyPos[j] != (i + 1)) {
                j++;
            }
            System.out.println(j + " oraz " + i);
            for (int row = 0; row < temp_height; row++) {
                if (temp_tab[row][j] == 1) {
                    temp_char[row][j] = text.charAt(sign++);
                }
            }
        }

        for (int i = 0; i < temp_height; i++) {
            for (int j = 0; j < keyPos.length; j++) {
                if (temp_tab[i][j] == 1) {
                    result += temp_char[i][j];
                }
            }
        }       
    }

    @Override
    public String toString() {
        return result;
    }

}
