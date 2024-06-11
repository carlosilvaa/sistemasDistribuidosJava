
package gui;

import cliente.Cliente;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

@SuppressWarnings("serial")
public class ClienteView extends javax.swing.JFrame {

    private Cliente cliente;

    public ClienteView() {
    	try {
            // Aplicar tema Metal
        	UIManager.setLookAndFeel(new NimbusLookAndFeel());

            // Customize NimbusLookAndFeel
            UIManager.put("nimbusBase", new Color(255, 255, 255)); // Set background color to white
            UIManager.put("nimbusBlueGrey", new Color(137, 177, 177)); // Set blue-grey color to dark grey
            UIManager.put("controlFont", new Font("Arial", Font.BOLD, 14)); // Set font to Arial bold 14
            // UIManager.setLookAndFeel(new WindowsLookAndFeel());
        } catch (Exception e) {
            System.err.println("Erro ao aplicar tema: " + e.getMessage());
        }
        initComponents();
    }

    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        inpIP = new javax.swing.JTextField();
        inpIP.setText("localhost");
        bttnConectar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("IP do servidor");

        inpIP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inpIPActionPerformed(evt);
            }
        });

        bttnConectar.setText("Conectar");
        bttnConectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnConectarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bttnConectar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(inpIP)))
                .addGap(33, 33, 33))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(inpIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(bttnConectar)
                .addGap(17, 17, 17))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void inpIPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inpIPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inpIPActionPerformed

    private void bttnConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnConectarActionPerformed
        String ipServidor = this.inpIP.getText();

        this.cliente = new Cliente(ipServidor);
        boolean isConnected = this.cliente.connect();
        if (isConnected) {
            new Login(cliente).setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(ClienteView.this, "Erro ao conectar ao servidor. Verifique o IP e tente novamente.", "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_bttnConectarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bttnConectar;
    private javax.swing.JTextField inpIP; 
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
