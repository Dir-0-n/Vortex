package vortexclient;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;
import vortexserver.RMIServer;
import vortexserver.IRMIServer;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Diron Gunasekara
 */
public class ServerOpen extends javax.swing.JFrame {
    boolean streamStarted;
    Registry reg;
    IRMIServer stubServer;
    private static RMIServer server;
    int streamDuration;
    private static ScheduledExecutorService executor;
    private static ScheduledExecutorService autoShutdownExecutor;
    public Random clientId; 
    
    /**
     * Creates new form ServerOpen
     */
    public ServerOpen() {
        initComponents();
        
        streamDuration = 0;
        streamStarted = false;
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jFileChooser1 = new javax.swing.JFileChooser();
        jFrame2 = new javax.swing.JFrame();
        jFrame3 = new javax.swing.JFrame();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        streamPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblClientsCount = new javax.swing.JLabel();
        lblStreamDuration = new javax.swing.JLabel();
        lblHrs = new javax.swing.JLabel();
        chkAutoDuration = new javax.swing.JCheckBox();
        cmbStreamHours = new javax.swing.JComboBox<>();
        btnStartStreaming = new javax.swing.JButton();
        streamStatus = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        connectPanel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        ConnectGUIText = new javax.swing.JTextField();
        ConnectGUIButton = new javax.swing.JButton();
        transferFilesPanel = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtFileName = new javax.swing.JTextField();
        browseFile = new javax.swing.JButton();
        sendFile = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame2Layout = new javax.swing.GroupLayout(jFrame2.getContentPane());
        jFrame2.getContentPane().setLayout(jFrame2Layout);
        jFrame2Layout.setHorizontalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame2Layout.setVerticalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame3Layout = new javax.swing.GroupLayout(jFrame3.getContentPane());
        jFrame3.getContentPane().setLayout(jFrame3Layout);
        jFrame3Layout.setHorizontalGroup(
            jFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame3Layout.setVerticalGroup(
            jFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Vortex 2.0");
        setBackground(new java.awt.Color(65, 69, 81));
        setForeground(new java.awt.Color(65, 69, 81));
        setPreferredSize(new java.awt.Dimension(555, 450));
        setResizable(false);

        jLabel3.setFont(new java.awt.Font("Verdana", 2, 36)); // NOI18N
        jLabel3.setText("Vortex Desktop Sharing ");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Number of Devices Connected:");
        jLabel1.setName("devicesCount"); // NOI18N

        lblClientsCount.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblClientsCount.setText("0");
        lblClientsCount.setName("lblDevicesCount"); // NOI18N

        lblStreamDuration.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblStreamDuration.setText("Stream Duration :");
        lblStreamDuration.setEnabled(false);
        lblStreamDuration.setName("streamDuration"); // NOI18N

        lblHrs.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblHrs.setText("hr(s)");
        lblHrs.setToolTipText("");
        lblHrs.setEnabled(false);
        lblHrs.setName("Hrs"); // NOI18N

        chkAutoDuration.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        chkAutoDuration.setText("Auto Duration ON/OFF");
        chkAutoDuration.setName("chkAutoDuration"); // NOI18N
        chkAutoDuration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAutoDurationActionPerformed(evt);
            }
        });

        cmbStreamHours.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8" }));
        cmbStreamHours.setEnabled(false);
        cmbStreamHours.setName("streamHours"); // NOI18N

        btnStartStreaming.setText("Start Streaming");
        btnStartStreaming.setActionCommand("");
        btnStartStreaming.setMaximumSize(new java.awt.Dimension(150, 36));
        btnStartStreaming.setMinimumSize(new java.awt.Dimension(105, 36));
        btnStartStreaming.setName("btnStartStreaming"); // NOI18N
        btnStartStreaming.setPreferredSize(new java.awt.Dimension(120, 36));
        btnStartStreaming.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartStreamingActionPerformed(evt);
            }
        });

        streamStatus.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        streamStatus.setText("Ready to stream");
        streamStatus.setName("lblStreamStatus"); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Status :");
        jLabel5.setName("lblStatus"); // NOI18N

        javax.swing.GroupLayout streamPanelLayout = new javax.swing.GroupLayout(streamPanel);
        streamPanel.setLayout(streamPanelLayout);
        streamPanelLayout.setHorizontalGroup(
            streamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(streamPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(streamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(streamPanelLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(streamStatus))
                    .addGroup(streamPanelLayout.createSequentialGroup()
                        .addComponent(lblStreamDuration)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbStreamHours, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(lblHrs)
                        .addGap(18, 18, 18)
                        .addGroup(streamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkAutoDuration)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, streamPanelLayout.createSequentialGroup()
                                .addComponent(btnStartStreaming, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24))))
                    .addGroup(streamPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblClientsCount, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(144, Short.MAX_VALUE))
        );
        streamPanelLayout.setVerticalGroup(
            streamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(streamPanelLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(streamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStreamDuration)
                    .addComponent(lblHrs)
                    .addComponent(chkAutoDuration)
                    .addComponent(cmbStreamHours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(streamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblClientsCount))
                .addGap(50, 50, 50)
                .addComponent(btnStartStreaming, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addGroup(streamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(streamStatus))
                .addGap(76, 76, 76))
        );

        jTabbedPane1.addTab("Stream", streamPanel);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Broadcaster IP:");

        ConnectGUIButton.setText("Connect");
        ConnectGUIButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConnectGUIButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout connectPanelLayout = new javax.swing.GroupLayout(connectPanel);
        connectPanel.setLayout(connectPanelLayout);
        connectPanelLayout.setHorizontalGroup(
            connectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(connectPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ConnectGUIText, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ConnectGUIButton, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(99, Short.MAX_VALUE))
        );
        connectPanelLayout.setVerticalGroup(
            connectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(connectPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(connectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ConnectGUIText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ConnectGUIButton))
                .addContainerGap(287, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Connect", connectPanel);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("File:");

        browseFile.setText("Browse");
        browseFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseFileActionPerformed(evt);
            }
        });

        sendFile.setText("Send");
        sendFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendFileActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Ebrima", 0, 18)); // NOI18N
        jLabel2.setText("<html>\nFile Transfer Utility <br>\n<font size=\"3\"><center>Server-to-Client only.");

        javax.swing.GroupLayout transferFilesPanelLayout = new javax.swing.GroupLayout(transferFilesPanel);
        transferFilesPanel.setLayout(transferFilesPanelLayout);
        transferFilesPanelLayout.setHorizontalGroup(
            transferFilesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(transferFilesPanelLayout.createSequentialGroup()
                .addGroup(transferFilesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(transferFilesPanelLayout.createSequentialGroup()
                        .addGap(165, 165, 165)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(transferFilesPanelLayout.createSequentialGroup()
                        .addGap(210, 210, 210)
                        .addComponent(sendFile, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(transferFilesPanelLayout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFileName, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(browseFile)))
                .addContainerGap(82, Short.MAX_VALUE))
        );
        transferFilesPanelLayout.setVerticalGroup(
            transferFilesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(transferFilesPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(transferFilesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFileName, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(browseFile, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(sendFile, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(136, 136, 136))
        );

        jTabbedPane1.addTab("Transfer Files", transferFilesPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPane1)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(15, 15, 15)
                .addComponent(jLabel3)
                .addGap(37, 37, 37)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(135, 135, 135))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartStreamingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartStreamingActionPerformed
        if(!streamStarted) 
        {
            if(streamDuration != 0) 
            {
                startServer();
                autoShutdownExecutor = Executors.newSingleThreadScheduledExecutor();
                Runnable holdServer = () -> {
                    shutDownServer();
                    System.out.println("shutting down..");
                };
                autoShutdownExecutor.schedule(holdServer, streamDuration, TimeUnit.HOURS);
                autoShutdownExecutor.shutdown();    // Scheduled task will be terminated when it's completed.
            }
            else 
            {
                startServer();
            }
        }
        else
        {
            shutDownServer();
        }        
    }//GEN-LAST:event_btnStartStreamingActionPerformed

    private void browseFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseFileActionPerformed
        JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int returnVal = fc.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            txtFileName.setText(fc.getSelectedFile().getAbsolutePath());
        }
    }//GEN-LAST:event_browseFileActionPerformed

    private void chkAutoDurationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAutoDurationActionPerformed
        autoDurationHandler();
    }//GEN-LAST:event_chkAutoDurationActionPerformed

    private void sendFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendFileActionPerformed
        if(!streamStarted) 
        {
            JOptionPane.showMessageDialog(rootPane, "The live stream is offline.", "Error", JOptionPane.ERROR_MESSAGE, null);
        }
        else try {
            if(server.getClients().isEmpty())
            {
                JOptionPane.showMessageDialog(rootPane, "There must be at least one active connection.", "Error", JOptionPane.ERROR_MESSAGE, null);
            }
            else if(!"".equals(txtFileName.getText()))
            {
                File file = new File(txtFileName.getText());
                byte [] mydata=new byte[(int) file.length()];
                
                try {
                    FileInputStream in = new FileInputStream(file);
                    in.read(mydata, 0, mydata.length);
                    
                    // Send files to all the connected devices.
                    for (IClientCallback value : server.getClients().values()) {
                        value.receiveFile(mydata, file.getName(), (int) file.length());
                    }
                    
                    JOptionPane.showMessageDialog(rootPane, "File sent.", "Info", JOptionPane.INFORMATION_MESSAGE, null);
                    in.close();
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(rootPane, "File not found.", "Error", JOptionPane.ERROR_MESSAGE, null);
                } catch (IOException ex) {
                    Logger.getLogger(ServerOpen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(rootPane, "Please select a file first.", "Error", JOptionPane.ERROR_MESSAGE, null);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(ServerOpen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_sendFileActionPerformed

    private void ConnectGUIButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConnectGUIButtonActionPerformed
        if(ConnectGUIText.getText().matches("(\\d+\\.\\d+\\.\\d+\\.\\d+):(\\d+)")) {
            InitializeConnection();
        }
        else {
            JOptionPane.showMessageDialog(rootPane, "The IP address provided is invalid.", "Invalid IP", JOptionPane.ERROR_MESSAGE, null);
        }
    }//GEN-LAST:event_ConnectGUIButtonActionPerformed

    private void InitializeConnection() {
        System.setProperty("java.security.policy","file:security.policy");
        String[] clientIpPort = ConnectGUIText.getText().split(":");
        try {
            IClientCallback client = new ClientCallback();
            String clientId = new RandomString(12).nextString();
            UnicastRemoteObject.exportObject(client, 0);
            Registry registry = LocateRegistry.getRegistry(clientIpPort[0],Integer.parseInt(clientIpPort[1]));
            stubServer = (IRMIServer) registry.lookup("rmi://" + ConnectGUIText.getText() + "/MasterServer");
            stubServer.registerClient(clientId, client);
            
            ViewportWindow vw = new ViewportWindow(stubServer, this, clientIpPort[0], clientId);
            vw.setVisible(true);
            this.setVisible(false);
        }
        catch(NotBoundException | RemoteException e) {
            JOptionPane.showOptionDialog(rootPane, "Connection failed. " + e.getMessage(),"Info", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null);
            ConnectGUIButton.setEnabled(true);
        }
    }
    
    private void autoDurationHandler() {
        if(chkAutoDuration.isSelected()) {
            lblStreamDuration.setEnabled(true);
            cmbStreamHours.setEnabled(true);
            lblHrs.setEnabled(true);
            streamDuration = Integer.parseInt(cmbStreamHours.getSelectedItem().toString());
        } else {
            lblStreamDuration.setEnabled(false);
            cmbStreamHours.setEnabled(false);
            lblHrs.setEnabled(false);
            streamDuration = 0;
        }
    }
    
    private void enableComponents() {
        for(int i = 0; i < streamPanel.getComponentCount(); i++) {
            if("streamDuration".equals(streamPanel.getComponent(i).getName()) ||
                    "streamHours".equals(streamPanel.getComponent(i).getName()) ||
                    "Hrs".equals(streamPanel.getComponent(i).getName()))
            {
                continue;
            }
            streamPanel.getComponent(i).setEnabled(true);
        }
    }
    
    private void disableComponents() {
        for(int i = 0; i < streamPanel.getComponentCount(); i++) {
            if("lblStatus".equals(streamPanel.getComponent(i).getName()) ||
                    "btnStartStreaming".equals(streamPanel.getComponent(i).getName()) ||
                    "devicesCount".equals(streamPanel.getComponent(i).getName()) ||
                    "lblDevicesCount".equals(streamPanel.getComponent(i).getName()) ||
                    "lblStreamStatus".equals(streamPanel.getComponent(i).getName()))
            {
                continue;
            }
            streamPanel.getComponent(i).setEnabled(false);
        }
    }
    
    private void startServer() {
        try {
            server = new RMIServer(Inet4Address.getLocalHost().getHostAddress(), 3940);
            server.open();
            
            disableComponents();
            streamStarted = true;
            btnStartStreaming.setText(("Cancel"));
            streamStatus.setText("Streaming...");
            
            // Check the total number of clients connected every two seconds.
            Runnable clientsCounter = () -> {
                try {
                    lblClientsCount.setText(Integer.toString(server.totalClients()));
                } catch (RemoteException ex) {
                    Logger.getLogger(ServerOpen.class.getName()).log(Level.SEVERE, null, ex);
                }
            };
            executor = Executors.newScheduledThreadPool(1);
            executor.scheduleAtFixedRate(clientsCounter, 0, 2, TimeUnit.SECONDS);
        } catch (RemoteException | UnknownHostException ex) {
            JOptionPane.showOptionDialog(rootPane, "An error occured while starting the server.", "Fatal Error", JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE, null, new Object[]{}, null);
        }    
    }
    
    private void shutDownServer() {
        try {
            server.close();
        
            streamStarted = false;
            enableComponents();
            executor.shutdownNow(); // Immediately terminate the scheduled task.
            lblClientsCount.setText("0");
            btnStartStreaming.setText(("Start Streaming"));
            streamStatus.setText("Ready to stream");
        } catch (RemoteException ex) {
            JOptionPane.showOptionDialog(rootPane, "An error occured while stopping the server.", "Fatal Error", JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE, null, new Object[]{}, null);
            Logger.getLogger(ServerOpen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ServerOpen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServerOpen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServerOpen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerOpen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            ServerOpen serverOpen = new ServerOpen();
            serverOpen.setVisible(true);
            
        });  
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ConnectGUIButton;
    private javax.swing.JTextField ConnectGUIText;
    private javax.swing.JButton browseFile;
    private javax.swing.JButton btnStartStreaming;
    private javax.swing.JCheckBox chkAutoDuration;
    private javax.swing.JComboBox<String> cmbStreamHours;
    private javax.swing.JPanel connectPanel;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JFrame jFrame2;
    private javax.swing.JFrame jFrame3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblClientsCount;
    private javax.swing.JLabel lblHrs;
    private javax.swing.JLabel lblStreamDuration;
    private javax.swing.JButton sendFile;
    private javax.swing.JPanel streamPanel;
    private javax.swing.JLabel streamStatus;
    private javax.swing.JPanel transferFilesPanel;
    private javax.swing.JTextField txtFileName;
    // End of variables declaration//GEN-END:variables
}
