package com.dmitryoskin.parallel.forms;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.text.DefaultCaret;

/**
 *
 * @author doskin
 */
public class RuntimeExecutionDialog extends javax.swing.JFrame {

	private InputStream in;
	private OutputStream out;
	private ExecutorService executor;
		
		
	
	/**
	 * Creates new form RuntimeExecutionDialog
	 */
	public RuntimeExecutionDialog() {
		initComponents();
		((DefaultCaret)txtOut.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);		
	}
	
	public void startExecution() {
		executor = Executors.newFixedThreadPool(1);
		executor.execute(new Runnable() {
			@Override
			public void run() {
				try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
					String line = null;

					while ((line = reader.readLine()) != null) {
						txtOut.append(line);
						txtOut.append("\n");
					}
						
				} catch(IOException ex) {
					System.out.println(ex.getMessage());
				}
			}
		});		
	}
	
	public void shutdownExecution() {
		if (executor != null) {
			executor.shutdown();
		}
	}
	
	public void reInit(InputStream in, OutputStream out) {
		shutdownExecution();
		
		setInputStream(in);
		setOutputStream(out);
		
		startExecution();
	}

	private void setInputStream(InputStream in) {
		this.in = in;
	}
	
	private void setOutputStream(OutputStream out) {
		this.out = out;
	}
	
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtOut = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        commandTxt = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setLayout(new java.awt.BorderLayout());

        txtOut.setColumns(20);
        txtOut.setEditable(false);
        txtOut.setRows(5);
        jScrollPane1.setViewportView(txtOut);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel1.setText(">");
        jPanel2.add(jLabel1);

        commandTxt.setPreferredSize(new java.awt.Dimension(350, 30));
        commandTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                commandTxtKeyReleased(evt);
            }
        });
        jPanel2.add(commandTxt);

        getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

	private void commandTxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_commandTxtKeyReleased
		if (evt.getKeyCode() == 10) {
			try {
				out.write(commandTxt.getText().getBytes());
			} catch(IOException ex) {
				System.out.println(ex.getMessage());
			}
			
			commandTxt.setText("");
		}
	}//GEN-LAST:event_commandTxtKeyReleased

	private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
		shutdownExecution();
	}//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField commandTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtOut;
    // End of variables declaration//GEN-END:variables
}
