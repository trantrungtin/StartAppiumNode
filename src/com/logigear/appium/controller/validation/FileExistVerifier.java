/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logigear.appium.controller.validation;

import java.awt.Color;
import java.io.File;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Tin
 */
public class FileExistVerifier extends InputVerifier{
    private JLabel lblWarning = null;
    private String msg = null;
    
    public FileExistVerifier(JLabel lblWarning, String msg) {
        this.lblWarning = lblWarning;
        this.msg = msg;
    }

    @Override
    public boolean verify(JComponent input) {
        JTextField textField = (JTextField) input;
        textField.setBackground(Color.WHITE);
        textField.setToolTipText(null);
        
        if (!textField.isEnabled()) {
            return true;
        }
        
        File file = new File(textField.getText());
        if (file.exists()) {
            return true;
        }
        else {
            textField.setBackground(Color.RED);
            textField.setToolTipText("<html><b><font color=red>"
                    + msg + "</font></b></html>");
        }
        return false;
    }    
}
