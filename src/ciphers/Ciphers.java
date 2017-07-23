/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ciphers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Konrad
 */
public class Ciphers extends JFrame implements ComponentListener{

    PlainDocument doc;
    JLabel cipherLabel, modeLabel, extraLabel;
    JComboBox listOfCiphers, mode;
    String[] CiphersStrings = {"Wybierz szyfr",
        "Szyfr płotkowy",
        "Przestawienia macierzowe",
        "Przestawienia macierzowe (2b)",
        "Przestawienia macierzowe (2c)",
        "Szyfr Cezara",
        "Szyfrowanie Vigenere’a"};
    String[] modeStrings = {"Wybierz tryb",
        "kodowanie",
        "dekodowanie"};
    JTextArea source, result;
    JScrollPane jSP1, jSP2;

    JTextField NumberOfLevels = new JTextField();
    JTextField key = new JTextField();

    JButton run, exit;
    
    int flag = 0;

    public Ciphers() {
        new JFrame();
        setVisible(true);
        setLayout(null);

        setSize(600, 850);
        
        this.addComponentListener(this);

        cipherLabel = new JLabel("Szyfr:");
        cipherLabel.setBounds(50, 25, 50, 25);
        add(cipherLabel);
        extraLabel = new JLabel();

        listOfCiphers = new JComboBox(CiphersStrings);
        listOfCiphers.setSelectedIndex(0);
        listOfCiphers.setBounds(110, 25, 250, 25);
        add(listOfCiphers);

        modeLabel = new JLabel("Tryb:");
        modeLabel.setBounds(50, 60, 50, 25);
        mode = new JComboBox(modeStrings);
        mode.setSelectedIndex(0);
        mode.setBounds(110, 60, 250, 25);

        NumberOfLevels.setBounds(160, 90, 210, 25);
        digitFilter();
        NumberOfLevels.setDocument(doc);

        extraLabel = new JLabel();
        extraLabel.setBounds(50, 90, 250, 25);
        key.setBounds(110, 90, 250, 25);

        listOfCiphers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(modeLabel);
                remove(mode);
                remove(extraLabel);
                remove(NumberOfLevels);
                remove(key);
                switch (listOfCiphers.getSelectedIndex()) {
                    case 1:
                        add(modeLabel);
                        add(mode);
                        extraLabel.setText("Liczba poziomów:");
                        add(extraLabel);
                        add(NumberOfLevels);
                        break;
                    case 2:
                        add(modeLabel);
                        add(mode);
                        break;
                    case 3:
                        add(modeLabel);
                        add(mode);
                        extraLabel.setText("Klucz:");
                        add(extraLabel);
                        add(key);
                        break;
                    case 4:
                        add(modeLabel);
                        add(mode);
                        extraLabel.setText("Klucz:");
                        add(extraLabel);
                        add(key);
                        break;
                    case 5:
                        add(modeLabel);
                        add(mode);
                        break;
                    case 6:
                        add(modeLabel);
                        add(mode);
                        extraLabel.setText("Klucz:");
                        add(extraLabel);
                        add(key);
                        break;
                }
                revalidate();
                repaint();
            }
        });

        source = new JTextArea();
        jSP1 = new JScrollPane(source);

        jSP1.setBounds(50, 175, 485, 250);
        add(jSP1);

        result = new JTextArea();
        result.setEditable(false);
        jSP2 = new JScrollPane(result);

        jSP2.setBounds(50, 475, 485, 250);
        add(jSP2);

        run = new JButton("wykonaj");
        run.setBounds(175, 750, 100, 25);
        add(run);
        run.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listOfCiphers.getSelectedIndex() == 0 && mode.getSelectedIndex() == 0) {
                    result.setText("Pole szyfr jest wymagane\nPole tyb jest wymagane");
                    return;
                } else if (listOfCiphers.getSelectedIndex() == 0) {
                    result.setText("Pole szyfr jest wymagane");
                    return;
                } else if (mode.getSelectedIndex() == 0) {
                    result.setText("Pole tryb jest wymagane");
                    return;
                }
                if (listOfCiphers.getSelectedIndex() == 1 && NumberOfLevels.getText().isEmpty()) {
                    result.setText("Pole liczby poziomow jest wymagane");
                    return;
                } else if ((listOfCiphers.getSelectedIndex() == 3 || listOfCiphers.getSelectedIndex() == 4 || listOfCiphers.getSelectedIndex() == 6) && key.getText().isEmpty()) {
                    result.setText("Pole klucza jest wymagane");
                    return;
                }
                switch (listOfCiphers.getSelectedIndex()) {
                    case 1:
                        RailFence rf = new RailFence(Integer.parseInt(NumberOfLevels.getText()), inputData.standardizeData(source.getText()));
                        if (mode.getSelectedIndex() == 1) {
                            rf.code();
                        } else if (mode.getSelectedIndex() == 2) {
                            rf.decode();
                        }
                        result.setText(rf.toString());
                        break;
                    case 2:
                        transpositionCipher tc = new transpositionCipher(inputData.standardizeData(source.getText()));
                        if (mode.getSelectedIndex() == 1) {
                            tc.code();
                        } else if (mode.getSelectedIndex() == 2) {
                            tc.decode();
                        }
                        result.setText(tc.toString());
                        break;
                    case 3:
                        transpositionCipherEx2b tc2b = new transpositionCipherEx2b(inputData.standardizeData(key.getText()), inputData.standardizeData(source.getText()));
                        if (mode.getSelectedIndex() == 1) {
                            tc2b.code();
                        } else if (mode.getSelectedIndex() == 2) {
                            tc2b.decode();
                        }
                        result.setText(tc2b.toString());
                        break;
                    case 4:
                        transpositionCipherEx2c tc2c = new transpositionCipherEx2c(inputData.standardizeData(key.getText()), inputData.standardizeData(source.getText()));
                        if (mode.getSelectedIndex() == 1) {
                            tc2c.code();
                        } else if (mode.getSelectedIndex() == 2) {
                            tc2c.decode();
                        }
                        result.setText(tc2c.toString());
                        break;
                    case 5:
                        CaesarCipher cc = new CaesarCipher(inputData.standardizeData(source.getText()));
                        if (mode.getSelectedIndex() == 1) {
                            cc.code();
                        } else if (mode.getSelectedIndex() == 2) {
                            cc.decode();
                        }
                        result.setText(cc.toString());
                        break;
                    case 6:
                        VigenereCipher v = new VigenereCipher(inputData.standardizeData(key.getText()), inputData.standardizeData(source.getText()));
                        if (mode.getSelectedIndex() == 1) {
                            v.code();
                        } else if (mode.getSelectedIndex() == 2) {
                            v.decode();
                        }
                        result.setText(v.toString());
                        break;
                }
            }
        });

        exit = new JButton("wyjscie");
        exit.setBounds(325, 750, 100, 25);
        add(exit);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        revalidate();
        repaint();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void digitFilter() {
        doc = new PlainDocument();
        doc.setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int off, String str, AttributeSet attr)
                    throws BadLocationException {
                fb.insertString(off, str.replaceAll("\\D++", ""), attr);  // remove non-digits
            }

            @Override
            public void replace(FilterBypass fb, int off, int len, String str, AttributeSet attr)
                    throws BadLocationException {
                fb.replace(off, len, str.replaceAll("\\D++", ""), attr);  // remove non-digits
            }
        });
    }

    public static void main(String[] args) {
        new Ciphers();
    }

    @Override
    public void componentResized(ComponentEvent e) {
        //System.out.println(this.getSize());
        if(this.getSize().getHeight() < 810){
            run.setBounds(400, 25, 100, 25);
            exit.setBounds(400, 60, 100, 25);
            flag = 1;
        }
        else if (flag == 1){
            run.setBounds(175, 750, 100, 25);
            exit.setBounds(325, 750, 100, 25);
        }
    }

    @Override
    public void componentMoved(ComponentEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void componentShown(ComponentEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void componentHidden(ComponentEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
