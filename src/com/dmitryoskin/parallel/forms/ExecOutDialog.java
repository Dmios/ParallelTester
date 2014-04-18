package com.dmitryoskin.parallel.forms;

import com.dmitryoskin.parallel.core.TestType;
import com.dmitryoskin.parallel.core.Util;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.DefaultCaret;

/**
 *
 * @author Дмитрий
 */
public class ExecOutDialog extends javax.swing.JDialog {

    private InputStream in;
    
    public ExecOutDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        ((DefaultCaret)execOutTxt.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
    }

    public void processExec(final InputStream in, 
                            final TestType type, 
                            String testName, 
                            UUID testId,
                            boolean saveOutput) {        
        this.in = in;
        
        try {            
            long startTime = System.nanoTime();
            
            execOutTxt.append("OK\n\n");
            String line = null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            while ((line = reader.readLine()) != null) {            
                execOutTxt.append(line);
                execOutTxt.append("\n");
            }
            
            long endTime = System.nanoTime();
            closeThreads();
            
            Path result = Util.createNewResultFile(type, testName, testId);
            final BufferedWriter out = 
                    Files.newBufferedWriter(result, Charset.forName("UTF-8"), StandardOpenOption.WRITE);
            
            double totalTime = (endTime - startTime) / 1_000_000_000.0;
                        
            out.write("Ran test configuration: " + getTitle() + "\n");
            out.write("Total execution time in JVM: " + totalTime  + " sec\n\n");
            
            if (saveOutput) {
                out.write(execOutTxt.getText());
            }
            out.flush();
            out.close();
            
            execOutTxt.append("\nExecution time: " + totalTime + " sec");
            execOutTxt.append("\n\nGather runtime configuration from nodes...\n");
        } catch (IOException ex) {
            Logger.getLogger(ExecOutDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void closeThreads() {
        execOutTxt.append("\nTest complete");
        try {
            if (in != null) {
                in.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void setWarmingUp() {
        execOutTxt.append("Warming Up...\n");
    }
    
    public void setOK() {
        execOutTxt.append("OK\n");
    }
    
    public void appendString(String append) {
        execOutTxt.append(append);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        execOutTxt = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        copyBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationByPlatform(true);
        setMinimumSize(new java.awt.Dimension(700, 500));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        execOutTxt.setColumns(20);
        execOutTxt.setEditable(false);
        execOutTxt.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        execOutTxt.setRows(5);
        jScrollPane1.setViewportView(execOutTxt);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel3.setPreferredSize(new java.awt.Dimension(350, 40));

        copyBtn.setText("Скопировать");
        copyBtn.addActionListener(this::copyBtnActionPerformed);
        jPanel3.add(copyBtn);

        jPanel1.add(jPanel3);

        getContentPane().add(jPanel1, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        closeThreads();
    }//GEN-LAST:event_formWindowClosing

    private void copyBtnActionPerformed(java.awt.event.ActionEvent evt) {
        StringSelection ss = new StringSelection(execOutTxt.getText());
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton copyBtn;
    private javax.swing.JTextArea execOutTxt;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
