package gui;

import cliente.Cliente;
import entities.Empresa;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JOptionPane;
import org.json.JSONObject;

@SuppressWarnings("serial")
public class Login extends javax.swing.JFrame {

    private Cliente cliente;

    public Login(Cliente cliente) {
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
        this.cliente = cliente;
    }

    private void initComponents() {

        inpUsuario = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        rbCandidato = new javax.swing.JRadioButton();
        rbEmpresa = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        inpEmail = new javax.swing.JTextField();
        inpEmail.setText("duda@email.com");
        jLabel3 = new javax.swing.JLabel();
        inpSenha = new javax.swing.JTextField();
        inpSenha.setText("senha123");
        bttnCadastrar = new javax.swing.JButton();
        bttnLogar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Login");

        jLabel4.setText("Usuario");

        inpUsuario.add(rbCandidato);
        rbCandidato.setSelected(true);
        rbCandidato.setText("Candidato");

        inpUsuario.add(rbEmpresa);
        rbEmpresa.setText("Empresa");
        rbEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbEmpresaActionPerformed(evt);
            }
        });

        jLabel5.setText("E-mail");

        inpEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inpEmailActionPerformed(evt);
            }
        });

        jLabel3.setText("Senha");

        inpSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inpSenhaActionPerformed(evt);
            }
        });

        bttnCadastrar.setText("Cadastrar");
        bttnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnCadastrarActionPerformed(evt);
            }
        });

        bttnLogar.setText("Logar");
        bttnLogar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnLogarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(89, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bttnCadastrar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bttnLogar))
                            .addComponent(inpSenha)
                            .addComponent(inpEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(86, 86, 86))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(rbCandidato)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbEmpresa)
                            .addComponent(jLabel1))
                        .addGap(232, 232, 232))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbEmpresa)
                    .addComponent(rbCandidato)
                    .addComponent(jLabel4))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inpEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inpSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bttnCadastrar)
                    .addComponent(bttnLogar))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void inpEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inpEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inpEmailActionPerformed

    private void inpSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inpSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inpSenhaActionPerformed

    private void rbEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbEmpresaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbEmpresaActionPerformed

    private void bttnLogarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnLogarActionPerformed
        // TODO add your handling code here:

        String usuario = (this.rbCandidato.isSelected()) ? "Candidato" : "Empresa";
        String email = this.inpEmail.getText();
        String senha = this.inpSenha.getText();

        JSONObject request = new JSONObject();

        request.put("operacao", "login" + usuario);
        request.put("email", email);
        request.put("senha", senha);

        String response = this.cliente.callServer(request);
        JSONObject json = new JSONObject(response);
        if (json.getInt("status") == 200) {
        	if(usuario.equals("Candidato")) {
        		new CandidatoHome(cliente, usuario, email, json.getString("token")).setVisible(true);
        	}else {
        		new EmpresaHome(cliente, usuario, email, json.getString("token")).setVisible(true);
        	}
            dispose();
        } else {
            JOptionPane.showMessageDialog(Login.this, json.getString("mensagem"), "Erro Login", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_bttnLogarActionPerformed

    private void bttnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnCadastrarActionPerformed
        new Cadastrar(cliente).setVisible(true);
        dispose();
    }//GEN-LAST:event_bttnCadastrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bttnCadastrar;
    private javax.swing.JButton bttnLogar;
    private javax.swing.JTextField inpEmail;
    private javax.swing.JTextField inpSenha;
    private javax.swing.ButtonGroup inpUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JRadioButton rbCandidato;
    private javax.swing.JRadioButton rbEmpresa;
    // End of variables declaration//GEN-END:variables
}