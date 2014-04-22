package com.dmitryoskin.parallel.forms;

import com.dmitryoskin.parallel.core.GraphData;
import com.dmitryoskin.parallel.core.TestType;
import com.dmitryoskin.parallel.core.UserSet;
import com.dmitryoskin.parallel.core.Util;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Дмитрий
 */
public class Main extends javax.swing.JFrame {

    private static int CHECKBOX_COLUMN = 0;
    private static int HOST_COLUMN = 1;
    private static String DEFAULT_HOST = "192.168.1.1";
    private static String EMPTY = "";
    private static String TEST_LIST_META_FILE = "test.list";
    private static String TEST_COMMAND_META_FILE = "test.sh";
    private UserSet set;

    public Main() {
        initComponents();

        try {
            set = Util.loadUserSet();
            for (Map.Entry<String, Boolean> entry : set.getHosts().entrySet()) {
                addHostsTableRow(entry.getValue(), entry.getKey());
            }

            processCountSpn.setValue(set.getProcessCount());

//            File testsDir = new File(Util.getTestsPath());
//            File[] dirs = testsDir.listFiles();
//            Arrays.sort(dirs, new Comparator<File>() {
//                @Override
//                public int compare(File o1, File o2) {
//                    return o1.getName().compareTo(o2.getName());
//                }
//            
//            });
//            
//            for (File testDir : dirs) {
//                if (testDir.isDirectory()) {
//                    cmbTestType.addItem(testDir.getName());
//                }
//            }

            hostsTable.getModel().addTableModelListener(e -> saveUserSetBtn.setEnabled(true));

            saveUserSetBtn.setEnabled(false);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addHostsTableRow(boolean checked, String host) {
        DefaultTableModel model = (DefaultTableModel) hostsTable.getModel();
        model.addRow(new Object[]{checked, host});
    }

    private void removeHostsTableRow() {
        DefaultTableModel model = (DefaultTableModel) hostsTable.getModel();
        model.removeRow(model.getRowCount() - 1);
    }

    private Process runProcess(String processName) {
        try {
            return Runtime.getRuntime().exec(processName, null, 
                    Paths.get(getCurrentTestDirectory()).toFile());
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private String getActiveHosts() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < hostsTable.getRowCount(); i++) {
            if ((Boolean) hostsTable.getValueAt(i, CHECKBOX_COLUMN)) {
                builder.append(hostsTable.getValueAt(i, HOST_COLUMN)).append(',');
            }
        }
        final String hosts = builder.toString();
        return hosts.substring(0, hosts.length() - 1);       
    }

    private void updateUserSet() {
        final Map<String, Boolean> hosts = set.getHosts();
        hosts.clear();
        for (int i = 0; i < hostsTable.getRowCount(); i++) {
            hosts.put(
                (String) hostsTable.getValueAt(i, HOST_COLUMN),
                (Boolean) hostsTable.getValueAt(i, CHECKBOX_COLUMN)
            );
        }

        int pCount = 0;
        try {
            pCount = (Integer) processCountSpn.getValue();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        set.setProcessCount(pCount);
    }
    
    private String getCurrentTestDirectory() {
        return new StringBuilder(Util.getTestsPath())
                .append(File.separator)
                .append(cmbTestType.getSelectedItem())
                .append(File.separator).toString();
    }
    
    private Path getTestMetaFile(String metaFileName) {        
        return Paths.get(new StringBuilder(getCurrentTestDirectory())
                .append(metaFileName).toString());
    }
    
    private String getStartTestCommand(String processCount, 
                                       String hosts,
                                       String additionalParams,
                                       String testName,
                                       String optionalTestDir) {
        Path cmd = getTestMetaFile(TEST_COMMAND_META_FILE);
        String command = null;
        try {
            command = new StringBuilder(Util.getMpiExecPath())
                    .append(File.separator)
                    .append(new String(Files.readAllBytes(cmd)))
                    .toString();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return command.replace("?1", processCount)
                        .replace("?2", hosts)
                        .replace("?3", additionalParams)
                        .replace("?4", testName)
                        .replace("?5", optionalTestDir);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        addHostBtn = new javax.swing.JButton();
        removeHostBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        hostsTable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cmbTestType = new javax.swing.JComboBox();
        chosenTestCmb = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        descriptionTxt = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        processCountSpn = new javax.swing.JSpinner();
        jPanel8 = new javax.swing.JPanel();
        startTestBtn = new javax.swing.JButton();
        saveUserSetBtn = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        sshConfigBtn = new javax.swing.JButton();
        sshMasterConfigCbx = new javax.swing.JCheckBox();
        saveOutputCbx = new javax.swing.JCheckBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        chooseFileMenuItem = new javax.swing.JMenuItem();
        generateGraph = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Тестер распределенной вычислительной сети");
        setLocationByPlatform(true);
        setMinimumSize(new java.awt.Dimension(600, 470));
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Сеть"));
        jPanel1.setMaximumSize(new java.awt.Dimension(200, 32767));
        jPanel1.setPreferredSize(new java.awt.Dimension(220, 400));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        addHostBtn.setText("+");
        addHostBtn.addActionListener(this::addHostBtnActionPerformed);
        jPanel1.add(addHostBtn);

        removeHostBtn.setText("-");
        removeHostBtn.addActionListener(this::removeHostBtnActionPerformed);
        jPanel1.add(removeHostBtn);

        jScrollPane1.setBorder(null);
        jScrollPane1.setAutoscrolls(true);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(200, 300));

        hostsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Хост"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        hostsTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(hostsTable);
        hostsTable.getColumnModel().getColumn(0).setResizable(false);
        hostsTable.getColumnModel().getColumn(0).setPreferredWidth(40);
        hostsTable.getColumnModel().getColumn(1).setResizable(false);
        hostsTable.getColumnModel().getColumn(1).setPreferredWidth(155);

        jPanel1.add(jScrollPane1);

        getContentPane().add(jPanel1, java.awt.BorderLayout.WEST);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Тест"));
        jPanel2.setPreferredSize(new java.awt.Dimension(400, 450));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel3.setPreferredSize(new java.awt.Dimension(416, 70));
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel2.setText("Выбрать тест:");
        jPanel3.add(jLabel2);

        cmbTestType.setPreferredSize(new java.awt.Dimension(150, 30));
        cmbTestType.addActionListener(this::cmbTestTypeActionPerformed);
        jPanel3.add(cmbTestType);

        chosenTestCmb.setPreferredSize(new java.awt.Dimension(300, 30));
        chosenTestCmb.addActionListener(this::chosenTestCmbActionPerformed);
        jPanel3.add(chosenTestCmb);

        jPanel2.add(jPanel3, java.awt.BorderLayout.NORTH);

        jPanel4.setPreferredSize(new java.awt.Dimension(506, 130));
        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jScrollPane2.setPreferredSize(new java.awt.Dimension(380, 120));

        descriptionTxt.setEditable(false);
        descriptionTxt.setColumns(20);
        descriptionTxt.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        descriptionTxt.setLineWrap(true);
        descriptionTxt.setRows(5);
        descriptionTxt.setMinimumSize(new java.awt.Dimension(350, 100));
        descriptionTxt.setPreferredSize(new java.awt.Dimension(350, 100));
        jScrollPane2.setViewportView(descriptionTxt);

        jPanel4.add(jScrollPane2);

        jPanel2.add(jPanel4, java.awt.BorderLayout.CENTER);

        jPanel5.setPreferredSize(new java.awt.Dimension(400, 250));
        jPanel5.setLayout(new java.awt.BorderLayout());

        jPanel6.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel3.setText("Кол-во процессов:");
        jLabel3.setPreferredSize(new java.awt.Dimension(140, 14));
        jPanel6.add(jLabel3);

        processCountSpn.setPreferredSize(new java.awt.Dimension(50, 20));
        processCountSpn.setValue(1);
        processCountSpn.addChangeListener(this::processCountSpnStateChanged);
        jPanel6.add(processCountSpn);

        jPanel5.add(jPanel6, java.awt.BorderLayout.NORTH);

        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        startTestBtn.setText("Запустить тест");
        startTestBtn.addActionListener(this::startTestBtnActionPerformed);
        jPanel8.add(startTestBtn);

        saveUserSetBtn.setText("Сохранить настройки");
        saveUserSetBtn.addActionListener(this::saveUserSetBtnActionPerformed);
        jPanel8.add(saveUserSetBtn);

        jPanel5.add(jPanel8, java.awt.BorderLayout.SOUTH);

        jPanel9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel4.setText("Дополнительные параметры:");
        jLabel4.setPreferredSize(new java.awt.Dimension(300, 14));
        jPanel9.add(jLabel4);

        jScrollPane3.setPreferredSize(new java.awt.Dimension(380, 50));

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setPreferredSize(new java.awt.Dimension(300, 50));
        jScrollPane3.setViewportView(jTextArea1);

        jPanel9.add(jScrollPane3);

        sshConfigBtn.setText("SSH конфигурация");
        sshConfigBtn.addActionListener(this::sshConfigBtnActionPerformed);
        jPanel9.add(sshConfigBtn);
        sshConfigBtn.getAccessibleContext().setAccessibleDescription("");

        sshMasterConfigCbx.setText("настройка мастер-хоста");
        jPanel9.add(sshMasterConfigCbx);

        saveOutputCbx.setSelected(true);
        saveOutputCbx.setText("Сохранять вывод");
        jPanel9.add(saveOutputCbx);

        jPanel5.add(jPanel9, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel5, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        jMenu1.setText("Файл");

        chooseFileMenuItem.setText("Выбрать файл(ы)");
        jMenu1.add(chooseFileMenuItem);

        chooseFileMenuItem.addActionListener(this::chooseFileMenuItemActionPerformed);

        generateGraph.setText("Сформировать график");
        jMenu1.add(generateGraph);

        generateGraph.addActionListener(this::generateGraphActionPerformed);

        jMenuItem1.setText("Выход");
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Правка");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chooseFileMenuItemActionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser(Util.getResultsPath());
        chooser.setMultiSelectionEnabled(true);
        chooser.setDialogTitle("Выбрать файлы с результатами тестирования");
        chooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return !f.getName().endsWith(".prf");
            }

            @Override
            public String getDescription() {
                return "Файлы результатов тестирования";
            }
        });

        if (chooser.showDialog(this, "Ок") == JFileChooser.APPROVE_OPTION) {
            boolean success = true;

            File[] files = chooser.getSelectedFiles();
            for (File file : files) {
                try {
                    Util.parseAndSaveData(file.toPath());
                } catch (Exception ex) {
                    ex.printStackTrace();
                    success = false;
                    JOptionPane.showMessageDialog(this,
                            "Ошибка при разборе файла " + file.getName(),
                            "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }

            if (success) {
                JOptionPane.showMessageDialog(this,
                        "Файлы обработаны успешно",
                        "Результат", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void generateGraphActionPerformed(ActionEvent evt) {
        JFileChooser chooser = new JFileChooser(Util.getResultsPath());
        chooser.setMultiSelectionEnabled(true);
        chooser.setDialogTitle("Выбрать файл(ы) для формирования графика");
        chooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.getName().endsWith(".prf") || f.isDirectory();
            }

            @Override
            public String getDescription() {
                return "Файлы с обработанными данными";
            }
        });

        if (chooser.showDialog(this, "Ок") == JFileChooser.APPROVE_OPTION) {
            boolean success = true;

            try {
                List<GraphData> graphData = Util.extractGraphData(chooser.getSelectedFiles());
                XYSeriesCollection dataset = new XYSeriesCollection();

                graphData.forEach(data -> {
                    XYSeries xy = new XYSeries(data.getLineName());
                    List<Double> x = data.getX();
                    List<Double> y = data.getY();

                    for (int i = 0; i < x.size(); i++) {
                        xy.add(x.get(i), y.get(i));
                    }

                    dataset.addSeries(xy);
                });

                GraphData example = graphData.stream().findFirst().get();


                JFreeChart chart = ChartFactory.createXYLineChart(
                        example.getName(),
                        example.getXAxisLabel(),
                        example.getYAxisLabel(),
                        dataset,
                        PlotOrientation.VERTICAL,
                        true,
                        true,
                        false
                );

                ChartFrame frame = new ChartFrame(example.getType().toString(), chart);
                frame.pack();
                frame.setVisible(true);

            } catch (Exception ex) {
                ex.printStackTrace();
                success = false;
                JOptionPane.showMessageDialog(this,
                        "Ошибка при разборе файлов",
                        "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void cmbTestTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTestTypeActionPerformed
        chosenTestCmb.removeAllItems();  
        Path dir = getTestMetaFile(TEST_LIST_META_FILE);
        try {
            List<String> tests = Files.readAllLines(dir, Charset.forName("UTF-8"));
            tests.forEach(chosenTestCmb::addItem);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }                
    }//GEN-LAST:event_cmbTestTypeActionPerformed

    private void sshConfigBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sshConfigBtnActionPerformed
        final String master = new StringBuilder(Util.getDeployPath())
                    .append(File.separator)
                    .append("sshMasterConfig.sh")
                    .toString();
        final StringBuilder node = new StringBuilder(Util.getDeployPath())
                    .append(File.separator)
                    .append("nodeConfig.sh");
        
        SwingWorker worker = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {                
                RuntimeExecutionDialog dialog = 
                                   new RuntimeExecutionDialog();
                dialog.setVisible(true);
				
                if (sshMasterConfigCbx.isSelected()) {
                    runProcess(master);       
                }
                
                for (int i = 0; i < hostsTable.getRowCount(); i++) {					
                    node.append(' ')
                       .append('\"')
                       .append(hostsTable.getValueAt(i, HOST_COLUMN))
                       .append('\"');
                    System.out.println(node.toString());
				   
                    Process p = runProcess(node.toString());
                    dialog.reInit(p.getInputStream(), p.getOutputStream());
                }

                return null;
            }
        };
        worker.execute();
                
    }//GEN-LAST:event_sshConfigBtnActionPerformed

    private void gatherInfoFromNodes(TestType type, 
                                        UUID testId, 
                                        String suffix, 
                                        ExecOutDialog userOut) throws IOException {
        for (int i = 0; i < hostsTable.getRowCount(); i++) {
            if ((Boolean) hostsTable.getValueAt(i, CHECKBOX_COLUMN)) {
                String host = (String)hostsTable.getValueAt(i, HOST_COLUMN);

                userOut.appendString("Call " + host + "...");
                StringBuilder builder = new StringBuilder();
                
                if (i != 0) {
                    builder.append("ssh root@")
                            .append(host)
                            .append(' ');
                }
                
                String command = builder.toString();

                Process p = runProcess(command.concat("free"));

                BufferedWriter out = 
                    Files.newBufferedWriter(
                        Util.createNewResultEnvironmentFile(type, host, testId, suffix), 
                        Charset.forName("UTF-8"), StandardOpenOption.WRITE);

                out.append("[memory]\n\n");
                Util.readFromInfoStream(p.getInputStream(), out);
                out.append("\n\n[procs]\n\n");

                p = runProcess(command.concat("ps aux"));

                Util.readFromInfoStream(p.getInputStream(), out);

                out.flush();
                out.close();
                
                userOut.appendString(" OK\n");
            }
        }	
    }
	
    private void startTestBtnActionPerformed(java.awt.event.ActionEvent evt) {
        final TestType testType = 
                TestType.valueOf(String.valueOf(cmbTestType.getSelectedItem()).toUpperCase());
        final String testName = String.valueOf(chosenTestCmb.getSelectedItem());
        
        final String exec = getStartTestCommand(
                String.valueOf(processCountSpn.getValue()), 
                getActiveHosts(), 
                EMPTY, 
                testName,
                getCurrentTestDirectory());

        System.out.println(exec);
        final ExecOutDialog execOut = new ExecOutDialog(Main.this, false);
        execOut.setTitle(exec.replace("\n", EMPTY));
        execOut.setVisible(true);

        SwingWorker worker = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {                               
                execOut.setWarmingUp();
                
                UUID uuid = UUID.randomUUID();
                gatherInfoFromNodes(testType, uuid, "1", execOut);			
                                
                Process p = runProcess(exec);
                execOut.processExec(p.getInputStream(), testType, testName, uuid, saveOutputCbx.isSelected());
                
                gatherInfoFromNodes(testType, uuid, "2", execOut);
                execOut.appendString("\nAll actions completed");
                return null;
            }
        };
        worker.execute();
    }

    private void addHostBtnActionPerformed(java.awt.event.ActionEvent evt) {
        addHostsTableRow(true, DEFAULT_HOST);
        saveUserSetBtn.setEnabled(true);
    }

    private void removeHostBtnActionPerformed(java.awt.event.ActionEvent evt) {
        removeHostsTableRow();
        saveUserSetBtn.setEnabled(true);
    }

    private void saveUserSetBtnActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            updateUserSet();

            Util.saveUserSet(set);
            saveUserSetBtn.setEnabled(false);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void chosenTestCmbActionPerformed(java.awt.event.ActionEvent evt) {
        File testDir = new File(Util.getTestsPath() + chosenTestCmb.getSelectedItem());
        File[] descriptions = testDir.listFiles(DESCRIPTION_FILTER);

        if (descriptions != null && descriptions.length > 0) {
            File description = descriptions[0];
            descriptionTxt.setText(EMPTY);
            try {
                Scanner sc = new Scanner(new FileInputStream(description));
                StringBuilder builder = new StringBuilder();
                while (sc.hasNextLine()) {
                    builder.append(sc.nextLine()).append('\n');
                }
                descriptionTxt.setText(builder.toString());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void processCountSpnStateChanged(javax.swing.event.ChangeEvent evt) {
        saveUserSetBtn.setEnabled(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if (info.getName().contains("Nimbus")) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                }            
            }
            
            
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new Main().setVisible(true));
    }
    private static FilenameFilter DESCRIPTION_FILTER = (dir, name) -> "description.txt".equals(name);
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addHostBtn;
    private javax.swing.JComboBox chosenTestCmb;
    private javax.swing.JComboBox cmbTestType;
    private javax.swing.JTextArea descriptionTxt;
    private javax.swing.JTable hostsTable;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem chooseFileMenuItem;
    private javax.swing.JMenuItem generateGraph;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JSpinner processCountSpn;
    private javax.swing.JButton removeHostBtn;
    private javax.swing.JCheckBox saveOutputCbx;
    private javax.swing.JButton saveUserSetBtn;
    private javax.swing.JButton sshConfigBtn;
    private javax.swing.JCheckBox sshMasterConfigCbx;
    private javax.swing.JButton startTestBtn;
    // End of variables declaration//GEN-END:variables
}
